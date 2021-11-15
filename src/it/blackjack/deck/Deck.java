package it.blackjack.deck;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe che implementa un mazzo di carte
 * @author Erik Gurzau
 * @see <a href="www.linkedin.com/in/erikgurzau">Linkedin</a>
 * @see <a mailto="gurzau10@gmail.com">Email</a>
 */
public class Deck {
    /**
     * Lista delle carte
     */
    private final ArrayList<Card> deck;
    /**
     * Numero di default di un mazzo di carte di BlackJack
     */
    public static final int DEFAULT_CARDS = 52;
    /**
     * Numero di default per le mescolate delle carte
     */
    public static final int DEFAULT_NUM_SHUFFLE = 7;

    /**
     * Costruttore di un mazzo
     */
    Deck(){
        deck = initDeck(DEFAULT_CARDS);
    }

    /**
     * Costruttore di un mazzo con un numero determinato di carte
     * @param numCards Numero di carte del mazzo
     */
    public Deck(int numCards){
        deck = initDeck(numCards);
    }

    /**
     * Metodo per inizializzare il mazzo di carte
     * @param numCards Numero di carte
     * @return Lista di carte inizializzata
     */
    private ArrayList<Card> initDeck(int numCards){
        ArrayList<Card> vet = new ArrayList<>(numCards);
        int k = 0;
        while (k < numCards) {
            for (int i = 0; i < Card.S.length(); i++) {
                for (int j = 0; j < Card.V.length(); j++) {
                    vet.add(new Card(j, i));
                    k++;
                }
            }
        }
        return vet;
    }


    /**
     * Metodo per contare il numero di carte nel mazzo
     * @return
     */
    public int size() { return deck.size(); }

    /**
     * Metodo per mescolare le carte
     */
    public void shuffle(){
        shuffle(DEFAULT_NUM_SHUFFLE);
    }

    /**
     * Metodo per mescolare le carte n volte
     * @param times Numero di volte che le carte deveno essere mescolate
     */
    public void shuffle(int times){
        Random rand = new Random();
        int x, y;
        Card tmp;
        for (int t=0; t<times; t++) {
            for (int i = 0; i < deck.size(); i++) {
                x = rand.nextInt(deck.size());
                y = rand.nextInt(deck.size());
                tmp = deck.get(x);
                deck.set(x, deck.get(y));
                deck.set(y, tmp);
            }
        }
    }

    /**
     * Metodo per prendere la prima carta del mazzo e rimuoverla
     * @return La prima carta del mazzo
     */
    public Card pop(){
        Card c = deck.get(0);
        deck.remove(0);
        return c;
    }

    /**
     * Metodo per prendere la prima carta del mazzo senza rimuoverla
     * @return La prima carta del mazzo
     */
    public Card pull(){
        return get(0);
    }

    /**
     * Metodo per prendere una carta in una posizione specifica del mazzo
     * @param index Posizione della carta nel mazzo
     * @return Una carta in una posizione specifica del mazzo
     */
    public Card get(int index){
        return deck.get(index);
    }

    /**
     * Metodo per prendere una carta in una posizione random del mazzo
     * @return Una carta del mazzo nella posizione randomica estratta
     */
    public Card pick(){
        Random rand = new Random();
        return deck.get(rand.nextInt(deck.size()));
    }


    /**
     * Metodo per unire le informazione del mazzo in una stringa
     * @return Stringa contenente le informazioni del mazzo
     */
    public String toString(){
        String s = "\n";
        int i=1;
        for (Card c: deck) {
            s += c.toString() + " ";
            if (i%Card.V.length() == 0) s += "\n";
            i++;
        }
        return s;
    }
}
