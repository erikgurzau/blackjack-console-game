package it.blackjack.game;
import it.blackjack.deck.*;
import it.blackjack.player.*;
import it.blackjack.game.utils.OutputColors;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe che implementa il gioco del BlackJack sul terminale
 * @author Erik Gurzau
 * @see <a href="www.linkedin.com/in/erikgurzau">Linkedin</a>
 * @see <a mailto="gurzau10@gmail.com">Email</a>
 */
public class BlackJack {
    /**
     * Mazzo iniziale del gioco composta da un numero di carte multiplo di 52 fino a 416
     */
    private final Deck Deck;
    /**
     * Giocatore di blackjack
     */
    private Player player;
    /**
     * Giocatore detto "Dealer" o "Banco" controllato dal computer
     */
    private final Player dealer;
    /**
     * Valore massimo delle carte per vincere
     */
    public static final int MAX_CARDS_VALUE = 21;
    /**
     * Scommessa o puntata minima per giocare
     */
    public static final int MIN_BET = 2;
    /**
     * Scommessa o puntata massima per giocare
     */
    public static final int MAX_BET = 500;
    /**
     * Quantità mininma di default del mazzo di carte di blackjack
     */
    private static final int MIN_DEFAULT_CARDS = 104;
    /**
     * Millisecondi di pausa tra una pescata e l'altra
     */
    private static final int MILLISECONDS_SLEEP = 1200;
    /**
     * Valore booleano dell'attivazione del gioco
     */
    private boolean active;
    /**
     * Valore booleano del turno del giocatore
     */
    private boolean isTurnoPlayer;


    /**
     * Costruttore di un BlackJack
     */
    public BlackJack(){
        Deck = new Deck(MIN_DEFAULT_CARDS);
        Deck.shuffle();
        dealer = new Player("Dealer");
        active = true;
        isTurnoPlayer = true;
    }

    /**
     * Costruttore di un BlackJack con un giocatore già inizializzato
     */
    public BlackJack(Player player){
        Deck = new Deck(MIN_DEFAULT_CARDS);
        Deck.shuffle();
        dealer = new Player("Dealer");
        this.player = player;
        active = true;
        isTurnoPlayer = true;
    }


    /**
     * Metodo setter per sostituire il giocatore attuale con uno nuovo
     * @param player Giocatore di blackjack
     */
    public void setPlayer(Player player) { this.player = player; }

    /**
     * Metodo per scandire i turni del giocatore, ogni turno viene chiesto al giocatore se vuole una
     * nuova carta oppure fermarsi e passare il turno ad un altro giocatore
     */
    public void turnPlayer(){
        if (active) {
            Scanner sc = new Scanner(System.in);
            String risp;

            if (player.countCards() < 2)
                player.addCard(Deck.pop());
            else {
                if (player.cardValue() != 21) {
                    try {
                        System.out.print(OutputColors.ANSI_YELLOW + "\n Carta (+) o Stai (-)? " + OutputColors.ANSI_RESET);
                        risp = sc.next();
                        if (risp.equals("+")) player.addCard(Deck.pop());
                        else isTurnoPlayer = false;

                        if (player.cardValue() == MAX_CARDS_VALUE) isTurnoPlayer = false;
                    } catch (InputMismatchException e) { }

                    if (player.cardValue() > MAX_CARDS_VALUE) {
                        isTurnoPlayer = false;
                        active = false;
                    }
                }else isTurnoPlayer = false;
            }
        }
    }

    /**
     * Metodo per scandire i turni del dealer controllato dal computer e pesca una carta casuale dal mazzo
     * e si ferma quando raggiunge o supera il valore delle carte del l'altro giocatore
     */
    public void turnDealer(){
        if (dealer.countCards() < 2) {
            dealer.addCard(Deck.pop());
        } else {
            if (dealer.cardValue() <= player.cardValue()) dealer.addCard(Deck.pop());
            else active = false;
        }

        if (dealer.countCards() >= 2 && dealer.cardValue() >= player.cardValue() || dealer.cardValue() >= MAX_CARDS_VALUE)
            active = false;

    }

    /**
     * Metodo per controllare chi ha vinto la partita
     * 1 = giocatore
     * -1 = dealer
     * 0 = pareggio
     * @return Valore intero dell'esito della partita
     */
    public int resultGame(){
        if (dealer.cardValue() > MAX_CARDS_VALUE) return -1;
        else if (player.cardValue() > MAX_CARDS_VALUE) return 1;
        else if (dealer.cardValue() > player.cardValue() && dealer.cardValue() <= MAX_CARDS_VALUE) return 1;
        else if (dealer.cardValue() < player.cardValue() && player.cardValue() <= MAX_CARDS_VALUE) return -1;
        else return 0;
    }

    /**
     * Metodo per stampare sul terminale lo stato della partita, ovvero le carte dei giocatori e il loro totale
     */
    public void printStatus(){
        System.out.println("\n "+dealer.getName()+"\t\t"+dealer.getCards()+" = "+dealer.cardValue());
        System.out.println(" "+player.getName()+"\t\t"+player.getCards()+" = "+player.cardValue());
        try{
            Thread.sleep(MILLISECONDS_SLEEP);
        }catch (InterruptedException e) { }
    }

    /**
     * Metodo per stampare sul terminale il risultato della partita
     */
    private void printResult(int esito){
        if (esito > 0){
            System.out.println(OutputColors.ANSI_RED + "\n\n ✘ "+player.getName()+" perde €"+player.getBet() + OutputColors.ANSI_RESET);
            player.updateMoney(-player.getBet());
        }
        else if (esito < 0) {
            System.out.println(OutputColors.ANSI_BLUE + "\n\n ✔ "+player.getName()+" vince €"+player.getBet()*2 + OutputColors.ANSI_RESET);
            player.updateMoney(player.getBet());
        }
        else System.out.println(OutputColors.ANSI_YELLOW + "\n\n Pareggio, le puntate sono state rimborsate" + OutputColors.ANSI_RESET);

        try{
            Thread.sleep(MILLISECONDS_SLEEP);
        }catch (InterruptedException e) { }
    }

    /**
     * Metodo per iniziare a giocare, continua ad andare finche il gioco è attivo
     */
    public void run(){
        turnDealer();
        while (active){
            while (isTurnoPlayer) {
                turnPlayer();
                printStatus();
            }
            if (active) {
                turnDealer();
                printStatus();
            }
        }
        printResult(resultGame());

        player.clearMano();
        dealer.clearMano();
    }

}
