/*
 * Classe Moneta
 * ATTRIBUTI
 * - valore: intero con valore 5, 10, 20, 50, 100, 200
 * 
 * METODI:
 * - costruttore che prende gli attributi in input: controlla i valori, eventualmente sollevando eccezioni
 * - getters: metodi per restituire gli attributi singolarmente
 * - toString: rappresentazione a stringa dell'oggetto Prodotto
 * 
 * Non abbiamo i setters perchè valore è una costante
 */

public class Moneta {

    private final int valore;

    public Moneta(int valore) {
        if (valore != 5 && valore != 10 && valore != 20 && valore != 50 && valore != 100 && valore != 200) {
            throw new IllegalArgumentException(String.format("Il parametro %d deve contenere uno tra 5, 10, 20, 50, 100, 200.", valore));
        }
        this.valore = valore;
    }

    public int getValore() {
        return valore;
    }

    @Override
    public String toString() {
        return String.format("Moneta da %.2f€", valore / 100.0);
    }

}