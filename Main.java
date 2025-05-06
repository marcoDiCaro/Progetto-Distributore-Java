public class Main {
    public static void main(String[] args) {

        Interfaccia interfaccia = new Interfaccia();
        interfaccia.start();

        /* System.out.println("==========> Test Distributore <=============\n\n");

        Distributore distributore = new Distributore();

        // Creo dei prodotti
        Prodotto caffe1 = new Caffe(1, "Caffè Espresso", 1.0, "Un classico espresso italiano");
        Prodotto caffe2 = new Caffe(2, "Caffè Lungo", 1.2, "Caffè più leggero");

        // Aggiunta dei prodotti (come admin)
        distributore.aggiungiProdotto(caffe1, true);
        distributore.aggiungiProdotto(caffe2, true);

        // Stampa dei prodotti
        System.out.println(distributore);

        // Verifica contaCaffe
        System.out.println("Conteggio Caffè: " + distributore.getContaCaffe()); // Deve essere 2

        // Acquisto prodotto
        try {
            double resto = distributore.acquistaProdotto(2, 2.0);
            System.out.printf("Acquisto riuscito. Resto: %.2f€\n", resto);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore durante l'acquisto: " + e.getMessage());
        }

        // Verifica contaCaffe dopo acquisto
        System.out.println("Conteggio Caffè: " + distributore.getContaCaffe()); // Deve essere 1

        // Rimozione prodotto
        distributore.rimuoviProdotto(1, true);

        // Verifica contaCaffe dopo rimozione
        System.out.println("Conteggio Caffè dopo rimozione: " + distributore.getContaCaffe()); // Deve essere 0
        */
    }
}