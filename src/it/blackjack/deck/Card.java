package it.blackjack.deck;
import java.util.Random;

/**
 * Classe che implementa una carta da gioco con
 * un valore e un proprio seme
 * @author Erik Gurzau
 * @see <a href="www.linkedin.com/in/erikgurzau">Linkedin</a>
 * @see <a mailto="gurzau10@gmail.com">Email</a>
 */
public class Card implements Comparable {
    /**
     * Valore intero della carta
     */
    private final int value;
    /**
     * Carattere del seme
     */
    private final int seed;
    /**
     * Stringa contenente i semi delle carte
     */
    public final static String S = "♥♦♣♠";
    /**
     * String contenente i valori delle carte
     */
    public final static String V = "123456789JQKA";

    /**
     * Costruttore di una carta generata randomicamente
     */
    public Card(){
        Random rand = new Random();
        value = rand.nextInt(V.length());
        seed = rand.nextInt(S.length());
    }

    /**
     * Costruttore di una carta con un valore e un seme determinato
     * @param value
     * @param seed
     */
    public Card(int value, int seed){
        this.value = value;
        this.seed = seed;
    }

    /**
     * Metodo getter per il valore intero della carta
     * @return Valore intero della carta
     */
    public int getValue() {
        return value;
    }

    /**
     * Metodo getter per il seme della carta
     * @return Char del seme della carta
     */
    public int getSeed() {
        return seed;
    }


    /**
     * Metodo per controllare se la carta è un asso 'A'
     * @return Valore booleano dell'essere un asso
     */
    public boolean isAce(){
        return value == V.length() - 1;
    }

    /**
     * Metodo per controllare se la carta è una figure 'J', 'Q', 'K'
     * @return Valore booleano dell'essere una figura
     */
    public boolean isFigure(){
        return value >= 9 && value <= 11;
    }


    /**
     * Metodo per confrontare due carte
     * @param obj Carta da confrontare con questa
     * @return Valore intero dell'esito del confronto
     */
    public int compareTo(Object obj){
        Card other = (Card)obj;
        if (value > other.getValue()) return 1;
        else if (value < other.getValue()) return -1;
        else return 0;
    }

    /**
     * Metodo per unire le informazione della carta in una stringa
     * @return Stringa contenente le informazioni della carta
     */
    public String toString(){
        return V.charAt(value)+" "+S.charAt(seed);
    }
}
