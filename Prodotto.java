/*
 * Classe Prodotto
 * ATTRIBUTI:
 * - id: intero positivo
 * - nome: stringa non vuota
 * - prezzo: numero decimale 
 * - descrizione: stringa non vuota
 * 
 * METODI:
 * - costruttore che prende gli attributi in input: controlla i valori, eventualmente sollevando eccezioni
 * - getters: metodi per restituire gli attributi singolarmente
 * - setters: metodi per modificare gli attributi singoalrmente, controlla opportunamente valori, eventualmente sollevando eccezioni
 * - toString: rappresentazione a stringa dell'oggetto Prodotto
 * - getTipo: Restituisce il tipo dell’oggetto (la classe a cui appartiene)
 */

public abstract class Prodotto {
    protected int id;
    protected String nome;
    protected double prezzo;
    protected String descrizione;
    protected TipoProdotto tipo;

    public Prodotto(int id, String nome, double prezzo, String descrizione, TipoProdotto tipo) {
        if (id < 0) {
            throw new IllegalArgumentException(String.format("Il parametro %d deve essere un intero positivo.", id));
        }
        this.id = id;

        if (nome.equals("")) {
            throw new IllegalArgumentException(
                    String.format("Il parametro %s deve essere una stringa non vuota.", nome));
        }
        this.nome = nome;

        if (prezzo <= 0) {
            throw new IllegalArgumentException(
                    String.format("Il parametro %.2f deve essere un numero positivo.", prezzo));
        }
        this.prezzo = prezzo;

        if (descrizione.equals("")) {
            throw new IllegalArgumentException(
                    String.format("Il parametro %s deve essere una stringa non vuota.", descrizione));
        }
        this.descrizione = descrizione;

        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException(String.format("Il parametro %d deve essere un intero positivo.", id));
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.equals("")) {
            throw new IllegalArgumentException(
                    String.format("Il parametro %s deve essere una stringa non vuota.", nome));
        }
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0) {
            throw new IllegalArgumentException(
                    String.format("Il parametro %.2f deve essere un numero positivo.", prezzo));
        }
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        if (descrizione.equals("")) {
            throw new IllegalArgumentException(
                    String.format("Il parametro %s deve essere una stringa non vuota.", descrizione));
        }
        this.descrizione = descrizione;
    }

    public TipoProdotto getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Nome: %s, Prezzo: %.2f, Descrizione: %s", id, nome, prezzo, descrizione);
    }
}
