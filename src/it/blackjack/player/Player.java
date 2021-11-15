package it.blackjack.player;
import it.blackjack.deck.Card;
import it.blackjack.game.BlackJack;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe del giocatore di blackjack
 * @author Erik Gurzau
 * @see <a href="www.linkedin.com/in/erikgurzau">Linkedin</a>
 * @see <a mailto="gurzau10@gmail.com">Email</a>
 */
public class Player {
    /**
     * Nome del giocatore
     */
    private String name;
    /**
     * Mano di carte del giocatore
     */
    private ArrayList<Card> cards;
    /**
     * Soldi del giocatore
     */
    private int money;
    /**
     * Puntata del giocatore
     */
    private int bet;
    /**
     * Denaro iniziale per ogni giocatore
     */
    private static final int DEFAULT_MONEY = 10;

    /**
     * Costruttore di un giocatore con un nome generato in modo randomico
     */
    public Player(){
        name = randName();
        cards = new ArrayList<>();
        money = DEFAULT_MONEY;
        bet = 0;
    }

    /**
     * Costruttore di un giocatore con un nome specifico
     * @param name Nome del giocatore
     */
    public Player(String name){
        if (name.length() == 0) this.name = randName();
        else this.name = name;
        cards = new ArrayList<>();
        money = DEFAULT_MONEY;
        bet = 0;
    }

    /**
     * Costruttore di un giocatore con un nome specifico e una puntata iniziale determinata
     * @param name
     * @param bet
     */
    public Player(String name, int bet){
        if (name.length() == 0) this.name = randName();
        else this.name = name;
        cards = new ArrayList<>();
        money = DEFAULT_MONEY;
        this.bet = bet;
    }

    /**
     * Metodo getter del nome
     * @return Nome attuale del giocatore
     */
    public String getName() {
        return name;
    }

    /**
     * Metodo setter del nome
     * @param name Nuovo nome del giocatore
     */
    public void setName(String name) {
        if (name.length() == 0) this.name = randName();
        else this.name = name;
    }

    /**
     * Metodo getter della mano di carte del giocatore
     * @return Lista contenente le carte del giocatore
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Metodo getter per la quantità di denaro attuale del giocatore
     * @return
     */
    public int getMoney() {
        return money;
    }

    /**
     * Metodo getter della puntata del giocatore
     * @return
     */
    public int getBet() {
        return bet;
    }

    /**
     * Metodo setter della puntata del giocatore
     * @param bet Nuova puntata del giocatore
     */
    public void setBet(int bet) {
        this.bet = bet;
    }

    /**
     * Nome del giocatore generato randomicamente
     * @return
     */
    private String randName(){
        Random rand = new Random();
        String letters = "abcdefghiljkmnopqrstuvwxyz";
        String name = "";
        int maxLung = 10, minLung = 3;
        int l = rand.nextInt(maxLung - minLung) + minLung;
        for (int i=0; i<l; i++){
            name += letters.charAt(rand.nextInt(letters.length()));
        }
        return name;
    }

    /**
     * Metodo per inizializzare nuovamente il denaro del giocatore al valore di default
     */
    public void resetMoney(){
        this.money = DEFAULT_MONEY;
    }

    /**
     * Metodo per aggiornare il denaro del giocatore
     * @param money Quantità da aggiugere (qta) o sottrarre (-qta) al giocatore
     */
    public void updateMoney(int money){
        this.money += money;
    }

    /**
     * Metodo per aggiungere una nuova carta alla mano del giocatore
     * @param c Nuova carta
     */
    public void addCard(Card c){
        cards.add(c);
    }

    /**
     * Metodo per aggiungere una nuova carta alla mano del giocatore
     * @param c Nuova carta
     */
    public  void add(Card c) { cards.add(c); }

    /**
     * Metodo per calcolare il valore totale delle carte secondo le regole del BlackJack
     * @return Valore intero della mano di carte del giocatore
     */
    public int cardValue(){
        ArrayList<Card> lista = ord(cards);
        int v = 0;
        for (Card c: lista){
            if (!c.isAce()){
                if (c.isFigure()) v += 10;
                else v += c.getValue() + 1;
            }
            else {
                if (v + 11 > BlackJack.MAX_CARDS_VALUE) v += 1;
                else v += 11;
            }
        }
        return v;
    }

    /**
     * Metodo per ordinare una lista di carte
     * @param mano Lista contenente le carte del giocatore
     * @return Nuova lista di carte ordinate per valore
     */
    private ArrayList<Card> ord(ArrayList<Card> mano){
        ArrayList<Card> lista = new ArrayList<>(mano);
        lista.sort(Comparable:: compareTo);
        return lista;
    }

    /**
     * Metodo per contare il numero di carte nella mano del giocatore
     * @return
     */
    public int countCards() { return cards.size(); }

    /**
     * Metodo per rimuovere tutte le carte nella mano del giocatore
     */
    public void clearMano(){
        cards.clear();
    }

    /**
     * Metodo per ricevere le informazioni del giocatore
     * @return
     */
    public String toString(){
        return "\n name: \t\t" + name + "\n Mano: \t\t" + cards.toString() + " = " + cardValue() + "\n money: \t€" + money;
    }
}
