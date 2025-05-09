/*
 * Classe Distributore
 * ATTRIBUTI:
 * - listaProdotti: HasMap che ha come chiavi gli id dei prodotti e come valori gli oggetti Prodotto
 * - listaMonete: ArrayList che ha come elementi oggetti di tipo Moneta
 * - contaCaffe: Contatore sulla totalità degli oggetti Prodotto di tipo Caffe presenti nel distributore
 * 
 * METODI:
 * - costruttore: Inizializza listaProdotti, listaMonete e imposta contaCaffe a 0
 * - aggiungiProdotto: Aggiunge un prodotto al distributore, solo se l'utente ha privilegi da admin
 * - aggiungiMoneta: Aggiunge una moneta alla listaMonete
 * - cercaProdotto: Cerca un prodotto nel distributore in base all'id.
 * - rimuoviProdotto: Rimuove un prodotto dal distributore, solo se l'utente è admin.
 * - rimuoviMoneta: Rimuove una moneta di un valore specifico all'interno della listaMonete (Il valore deve contenere uno tra 5, 10, 20, 50)
 * - acquistaProdotto: Acquista un prodotto specificato per ID, se disponibile e se l'importo è sufficiente.
 * - contaCaffe: Restituisce il numero totale di prodotti di tipo Caffe disponibili.
 * - toString: Restituisce una rappresentazione a stringa del distributore e dei suoi prodotti.
 * - aggiungiContaTipo: Incrementa il contatore dei prodotti di un determinato tipo.
 * - sottraiContaTipo: Decrementa il contatore dei prodotti di un determinato tipo.
 * - getDisponibilitaTipo: Restituisce la disponibilità di un prodotto di un determinato tipo.
 */

import java.util.HashMap; // import the HashMap class
import java.util.ListIterator;
import java.util.ArrayList; // import the ArrayList class
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Distributore {
    private HashMap<Integer, Prodotto> listaProdotti;
    private ArrayList<Moneta> listaMonete;
    private ArrayList<Admin> listaAdmin;
    //private int contaCaffe;
    //private int contaSnack;

    /* private void aggiungiContaTipo(TipoProdotto tipo) {
        switch (tipo) {
            case CAFFE:
                contaCaffe += 1;
                break;
            case SNACK:
                contaSnack += 1;
                break;
        }
    } */

    /* private void sottraiContaTipo(TipoProdotto tipo) {
        switch (tipo) {
            case CAFFE:
                contaCaffe -= 1;
                break;
            case SNACK:
                contaSnack -= 1;
        }
    }

    private int getDisponibilitaTipo(TipoProdotto tipo) {
        int contaClasse = 0;
        switch (tipo) {
            case CAFFE:
                contaClasse = getContaCaffe();
                return contaClasse;
            case SNACK:
                contaClasse = getContaSnack();
                return contaClasse;
            default:
                return contaClasse;
        }
    } */

    private void caricaDati() {
        try {
            File myObj = new File("distributore.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] listaData = data.split(";");
                /*
                 * for (int i = 0; i < listaData.length; i++) {
                 * System.out.println(listaData[i]);
                 * }
                 */
                int id = Integer.parseInt(listaData[0].trim());
                String nome = listaData[1].trim();
                double prezzo = Double.parseDouble(listaData[2].trim().replace(',', '.'));
                String descrizione = listaData[3].trim();

                String tipoStr = listaData[4].trim(); // Tipo prodotto come stringa

                // Mappiamo la stringa al tipo corrispondente
                TipoProdotto tipo = TipoProdotto.valueOf(tipoStr.toUpperCase());
                Prodotto prodotto;

                int quantita = Integer.parseInt(listaData[5].trim());

                switch (tipo) {
                    case CAFFE:
                        prodotto = new Caffe(id, nome, prezzo, descrizione, quantita);
                        break;
                    case SNACK:
                        prodotto = new Snack(id, nome, prezzo, descrizione, quantita);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo di prodotto sconosciuto: " + tipo);
                }

                listaProdotti.put(id, prodotto);
                //aggiungiContaTipo(prodotto.getTipo());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void salvaSuFile() {
        try {
            FileWriter myWriter = new FileWriter("distributore.txt");
            // myWriter.write("Files in Java might be tricky, but it is fun enough!");
            // myWriter.close();
            listaProdotti.forEach((k, v) -> {
                String stringaDaScrivere = String.format("%d; %s; %.2f; %s; %s; %d\n", v.getId(), v.getNome(),
                        v.getPrezzo(), v.getDescrizione(), v.getTipo().name(), v.getQuantita()); // Salviamo il tipo come stringa
                try {
                    myWriter.write(stringaDaScrivere);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            myWriter.close();
            // System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Distributore() {
        listaProdotti = new HashMap<Integer, Prodotto>();
        listaMonete = new ArrayList<Moneta>();

        Admin admin = new Admin("mario.rossi@gmail.com", "MarioRossi123#");
        listaAdmin = new ArrayList<Admin>();
        listaAdmin.add(admin);

        //contaCaffe = 0;
        //contaSnack = 0;
        caricaDati();
    }

    /* public int getContaCaffe() {
        return contaCaffe;
    }

    public int getContaSnack() {
        return contaSnack;
    } */

    public boolean aggiungiProdotto(Prodotto prodotto, boolean isAdmin) {
        if (!isAdmin) {
            throw new IllegalArgumentException("Per aggiungere un prodotto bisogna avere il permesso da ADMIN!!");
        }
        if (listaProdotti.containsKey(prodotto.getId())) {
            /* throw new IllegalArgumentException(
                    String.format("Il prodotto con id %d è già presente.", prodotto.getId())); */
            int idProdotto = prodotto.getId();
            Prodotto prodottoDaModificare = listaProdotti.get(idProdotto);
            prodottoDaModificare.setQuantita(prodottoDaModificare.getQuantita() + prodotto.getQuantita());
        } else {
             listaProdotti.put(prodotto.getId(), prodotto);
        }
        // aggiungiContaTipo(prodotto.getTipo());
        salvaSuFile();
        return true;
    }

    public boolean aggiungiMoneta(Moneta moneta) {
        return listaMonete.add(moneta);
    }

    public Prodotto cercaProdotto(int id) {
        if (!listaProdotti.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Il prodotto con id %d non è presente.", id));
        }
        return listaProdotti.get(id);
    }

    public boolean loginAdmin(String email, String password) {
        for (Admin admin : listaAdmin) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean rimuoviProdotto(int id, boolean isAdmin) {
        if (!isAdmin) {
            throw new IllegalArgumentException("Per rimuovere un prodotto bisogna avere il permesso da ADMIN!!");
        }
        if (!listaProdotti.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Il prodotto con id %d non è presente.", id));
        }
        listaProdotti.remove(id);
        // sottraiContaTipo(prodottoRimosso.getTipo());
        salvaSuFile();
        return true;
    }

    /*
     * public double acquistaProdotto(int id, double importo) {
     * Prodotto prodotto = cercaProdotto(id);
     * if (getDisponibilitaTipo(prodotto.getTipo()) == 0) {
     * throw new IllegalArgumentException(
     * String.format("Il prodotto %s non è momentaneamente disponibile.",
     * prodotto.getTipo()));
     * }
     * if (importo < prodotto.getPrezzo()) {
     * double differenza = prodotto.getPrezzo() - importo;
     * throw new IllegalArgumentException(
     * String.
     * format("Credito insufficiente: hai inserito %.2f€, ma servono %.2f€. Mancano %.2f€."
     * ,
     * importo, prodotto.getPrezzo(), differenza));
     * }
     * sottraiContaTipo(prodotto.getTipo());
     * return importo - prodotto.getPrezzo(); // Restituisco il resto al cliente
     * }
     */

    private void rimuoviRestoListaMonete(int resto) {
        int restoDaRimuovere = resto;
        ListIterator<Moneta> iterator = listaMonete.listIterator();

        while (iterator.hasNext() && restoDaRimuovere > 0) {
            Moneta moneta = iterator.next();
            if (moneta.getValore() <= restoDaRimuovere) {
                restoDaRimuovere -= moneta.getValore();
                iterator.remove();
            }
        }
    }

    public double acquistaProdottoConMonete(int id, ArrayList<Moneta> moneteInserite) {
        Prodotto prodotto = cercaProdotto(id);
        if (prodotto.getQuantita() == 0) {
            throw new IllegalArgumentException(
                    String.format("Il prodotto %s non è momentaneamente disponibile.", prodotto.getTipo()));
        }

        int totaleInserito = 0;
        for (Moneta moneta : moneteInserite) {
            totaleInserito += moneta.getValore();
        }

        int prezzoProdotto = (int) (prodotto.getPrezzo() * 100); // Converto in centesimi per confronto

        if (totaleInserito < prezzoProdotto) {
            int differenza = prezzoProdotto - totaleInserito;
            throw new IllegalArgumentException(
                    String.format("Credito insufficiente: hai inserito %.2f€, ma servono %.2f€. Mancano %.2f€.",
                            totaleInserito / 100.0, prezzoProdotto / 100.0, differenza / 100.0));
        }

        // Aggiungo le monete al distributore
        for (Moneta moneta : moneteInserite) {
            aggiungiMoneta(moneta);
        }

        // Decremento il conteggio prodotto
        // sottraiContaTipo(prodotto.getTipo());

        prodotto.setQuantita(prodotto.getQuantita() - 1);

        // Rimuovo il prodotto
        // listaProdotti.remove(id);

        // Sovrascrivo distributore.txt con listaProdotti aggiornata
        salvaSuFile();

        int resto = totaleInserito - prezzoProdotto;

        // Rimuovo resto da restiture al cliente da listaMonete
        rimuoviRestoListaMonete(resto);

        return resto / 100.0; // Resto in euro
    }

    @Override
    public String toString() {
        StringBuilder stringaOutput = new StringBuilder();
        listaProdotti.forEach((k, v) -> {
            stringaOutput
                    .append(String.format("ID: %d, Nome: %s, Prezzo: %.2f, Descrizione: %s, Quantità: %d \n", v.getId(),
                            v.getNome(), v.getPrezzo(), v.getDescrizione(), v.getTipo().name(), v.getQuantita()));
        });
        return stringaOutput.toString();
    }
}