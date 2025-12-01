import java.util.*;

/**
 * Classe principale che gestisce la simulazione di una gara atletica.
 * Permette di impostare il numero di partecipanti, la lunghezza del percorso,
 * creare gli atleti e avviare la gara.
 */
public class GaraAtletica {

    /**
     * Metodo principale che esegue la simulazione della gara
     * Il metodo permette di:
     * selezionare il numero di partecipanti con vincoli minimi e massimi
     * selezionare la lunghezza del percorso con un minimo
     * creare gli atleti chiedendo nome e numero
     * avviare la gara tramite thread separati per ogni atleta
     * @param args argomenti della riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
        int MIN_PART = 2;   // numero minimo di partecipanti
        int MAX_PART = 6;   // numero massimo di partecipanti
        int MIN_LUNGH = 5;  // lunghezza minima del percorso in metri
        Scanner sc = new Scanner(System.in);
        int nPartecipanti;
        int lunghezzaPercorso;

        System.out.println("Benvenuto alla gara atletica");

        // Scelta del numero di partecipanti con controllo dei limiti
        do {
            System.out.print("Numero partecipanti: ");
            nPartecipanti = sc.nextInt();
            if (nPartecipanti < MIN_PART || nPartecipanti > MAX_PART) {
                System.out.printf("I partecipanti devono essere almeno %d e massimo %d\n", MIN_PART, MAX_PART);
            }
        } while (nPartecipanti < MIN_PART || nPartecipanti > MAX_PART);
        sc.nextLine();

        // Creazione del giudice che gestirà la gara
        Giudice giudice = new Giudice(nPartecipanti);

        // Scelta della lunghezza del percorso con controllo minimo
        do {
            System.out.printf("Lunghezza del percorso (min %d metri): ", MIN_LUNGH);
            lunghezzaPercorso = sc.nextInt();
            if (lunghezzaPercorso < MIN_LUNGH)
                System.out.printf("La lunghezza del percorso deve essere di almeno %d metri\n", MIN_LUNGH);
        } while (lunghezzaPercorso < MIN_LUNGH);
        giudice.lunghezzaPercorso = lunghezzaPercorso;

        // Creazione della lista degli atleti
        List<Atleta> listaAtleti = new ArrayList<>();
        for (int i = 0; i < nPartecipanti; i++) {
            System.out.printf("Nome atleta %d: ", (i + 1));
            String nome = sc.next();

            System.out.printf("Numero atleta %d: ", (i + 1));
            int numero = sc.nextInt();

            listaAtleti.add(new Atleta(nome, numero, giudice));
        }

        // Dichiarazione dell'inizio della gara
        giudice.dichiaraInizio();

        // Avvio dei thread per ogni atleta
        for (int i = 0; i < listaAtleti.size(); i++) {
            Thread t = new Thread(listaAtleti.get(i));
            t.start();
        }

        sc.close();
    }
}
