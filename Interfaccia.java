import java.util.ArrayList;
import java.util.Scanner;

public class Interfaccia {
    private Distributore distributore;
    private Scanner myObj;

    public Interfaccia() {
        distributore = new Distributore();
        myObj = new Scanner(System.in);
    }

    private void aggiungiProdotto() {
        try {
            System.out.println("Inserisci Id Prodotto:");
            int id = myObj.nextInt();
            // Consuma la riga vuota lasciata da nextInt()
            myObj.nextLine();
            System.out.println("Inserisci Nome Prodotto:");
            String nome = myObj.nextLine();
            System.out.println("Inserisci Prezzo Prodotto:");
            double prezzo = myObj.nextDouble();
            // Consuma la riga vuota lasciata da nextDouble()
            myObj.nextLine();
            System.out.println("Inserisci Descrizione Prodotto:");
            String descrizione = myObj.nextLine();

            System.out.println("Inserisci Quantità Prodotto:");
            int quantita = myObj.nextInt();
            // Consuma la riga vuota lasciata da nextInt()
            myObj.nextLine();

            System.out.println("Inserisci Tipo Prodotto (CAFFE, SNACK):");
            String tipoProdotto = myObj.nextLine().toUpperCase();
            TipoProdotto tipo = TipoProdotto.valueOf(tipoProdotto);

            Prodotto prodotto;
            if (tipo == TipoProdotto.CAFFE) {
                prodotto = new Caffe(id, nome, prezzo, descrizione, quantita);
            } else if (tipo == TipoProdotto.SNACK) {
                prodotto = new Snack(id, nome, prezzo, descrizione, quantita);
            } else {
                throw new IllegalArgumentException("Tipo prodotto non valido.");
            }

            distributore.aggiungiProdotto(prodotto, true);
            System.out.println(String.format("Prodotto %s Inserito con successo", prodotto));
        } catch (IllegalArgumentException e) {
            System.out.println("Errore durante l'inserimento prodotto: " + e.getMessage());
        }
    }

    private void rimuoviProdotto() {
        try {
            System.out.println("Inserisci Id Prodotto:");
            int id = myObj.nextInt();
            distributore.rimuoviProdotto(id, true);
            System.out.println("Prodotto rimosso con successo");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore durante rimozione prodotto: " + e.getMessage());
        }
    }

    /* private void acquistaProdotto() {
        try {
            System.out.println("Inserisci Id Prodotto:");
            int id = myObj.nextInt();
            System.out.println("Inserisci Importo:");
            double importo = myObj.nextDouble();
            double resto = distributore.acquistaProdotto(id, importo);
            System.out.printf("Acquisto riuscito. Resto: %.2f€\n", resto);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore durante l'acquisto: " + e.getMessage());
        }
    } */

    private void acquistaProdottoConMonete() {
        try {
            System.out.println("Inserisci ID prodotto:");
            int id = myObj.nextInt();

            // Cerco Prodotto con ID (Recupero i dati relativi al prodotto)
            Prodotto prodotto = distributore.cercaProdotto(id);

            int prezzoProdotto = (int) (prodotto.getPrezzo() * 100); // Converto in centesimi per confronto

            ArrayList<Moneta> moneteInserite = new ArrayList<>();
            //boolean fineInserimento = false;

            int totaleInserito = 0;

            while (totaleInserito < prezzoProdotto) {
                System.out.println("Inserisci moneta (5, 10, 20, 50, 100, 200) ");

                int differenza = prezzoProdotto - totaleInserito;

                System.out.println(String.format("Credito insufficiente: hai inserito %.2f€, ma servono %.2f€. Mancano %.2f€.",
                totaleInserito / 100.0, prezzoProdotto / 100.0, differenza / 100.0));

                int valore = myObj.nextInt();

                totaleInserito += valore;

                /* if (valore == 0) {
                    fineInserimento = true;
                } else { */
                    try {
                        moneteInserite.add(new Moneta(valore));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Moneta non valida: " + e.getMessage());
                    }
                // }
            }

            double resto = distributore.acquistaProdottoConMonete(id, moneteInserite);
            System.out.printf("Acquisto riuscito. Resto: %.2f€\n", resto);
        } catch (Exception e) {
            System.out.println("Errore durante l'acquisto: " + e.getMessage());
        }
    }

    private void gestisciAzioneAdmin(int azione) {
        switch (azione) {
            case 0:
                aggiungiProdotto();
                break;
            case 1:
                rimuoviProdotto();
                break;
            /* case 2:
                System.out.println("Conteggio Caffè: " + distributore.getContaCaffe());
                break;
            case 3:
                System.out.println("Conteggio Snack: " + distributore.getContaSnack());
                break; */
            default:
                System.out.println("Azione non valida");
        }
    }

    private void gestisciAzioneUtente(int azione) {
        switch (azione) {
            case 1:
                // acquistaProdotto();
                acquistaProdottoConMonete();
                break;
            default:
                System.out.println("Azione non valida");
        }
    }

    private void renderMenu(boolean admin) {
        System.out.println("==========> Lista Prodotti <=============\n");
        System.out.println(distributore + "\n");
        System.out.println("==========> Lista Azioni <=============\n");
        if (admin) {
            System.out.println("0: Aggiungi Prodotto");
            System.out.println("1: Rimuovi Prodotto");
            // System.out.println("2: Conteggio Caffè");
            //System.out.println("3: Conteggio Snack");
        } else {
            // System.out.println("0: Inserisci Monete");
            // System.out.println("1: Acquista Prodotto");
            System.out.println("1: Acquista Prodotto Con Monete");
        }
    }

    public void start() {
        System.out.println("Sei Admin?");
        String adminInput = myObj.nextLine();

        boolean admin = false;

        if (adminInput.toLowerCase().equals("si")) {
            System.out.println("Inserisci email per fare il login:");
            String emailInput = myObj.nextLine();
            System.out.println("Inserisci password per fare il login:");
            String passwordInput = myObj.nextLine();
            admin = distributore.loginAdmin(emailInput, passwordInput);
        }

        // boolean admin = (adminInput.toLowerCase().equals("si")) ? true : false;
        boolean uscita = false;
        while (!uscita) {
            renderMenu(admin);
            System.out.println("Inserisci numero per azione o -1 per uscire:");
            int userInput = myObj.nextInt();
            if (userInput == -1) {
                uscita = true;
            } else if (admin) {
                gestisciAzioneAdmin(userInput);
            } else {
                gestisciAzioneUtente(userInput);
            }
        }
        myObj.close();
    }
}