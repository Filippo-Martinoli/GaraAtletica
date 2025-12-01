import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta il giudice della gara atletica
 * Si occupa di gestire la classifica, registrare arrivi e ritiri,
 * dichiarare l'inizio e la fine della gara, stampare il podio
 * e salvare la classifica su file
 */
public class Giudice {

    /** Atleta vincitore della gara */
    private Atleta vincitore = null;

    /** Lunghezza del percorso della gara (metri) */
    public static double lunghezzaPercorso;

    /** Lista degli atleti in ordine di arrivo o ritiro */
    private final List<Atleta> classifica = new ArrayList<>();

    /** Numero totale di atleti partecipanti */
    private final int atletiTotali;

    /** Numero di atleti che hanno completato la gara */
    private int arrivati = 0;

    /** Gestore del file per leggere/salvare la classifica */
    private final GestoreFile gestoreFile = new GestoreFile();

    /**
     * Costruttore del giudice
     * @param numeroAtleti numero totale di atleti partecipanti alla gara
     */
    public Giudice(int numeroAtleti) {
        this.atletiTotali = numeroAtleti;
    }

    /**
     * Dichiarazione dell'inizio della gara
     * Legge l'ultima classifica salvata e comunica l'inizio
     */
    public void dichiaraInizio() {
        gestoreFile.leggiClassificaPrecedente();
        System.out.println("La gara è iniziata.");
    }

    /**
     * Registra l'arrivo di un atleta e aggiorna la classifica
     * Se è il primo ad arrivare, viene segnato come vincitore
     * @param atleta atleta che ha concluso la gara
     */
    public synchronized void registraArrivo(Atleta atleta) {
        classifica.add(arrivati, atleta);
        arrivati++;

        if (vincitore == null) {
            vincitore = atleta;
        }
        verificaFineGara();
    }

    /**
     * Registra il ritiro di un atleta e aggiorna la classifica
     * @param atleta atleta che si è ritirato dalla gara
     */
    public synchronized void registraRitiro(Atleta atleta) {
        classifica.add(atleta);
        System.out.println(atleta.getNome() + " si è ritirato.");
        verificaFineGara();
    }

    /**
     * Controlla se tutti gli atleti hanno concluso o ritirato,
     * in tal caso dichiara la fine della gara
     */
    private synchronized void verificaFineGara() {
        if (classifica.size() == atletiTotali) {
            dichiaraFine();
        }
    }

    /**
     * Dichiarazione della fine della gara
     * Stampa vincitore, classifica finale, podio e salva la classifica su file
     */
    private synchronized void dichiaraFine() {
        System.out.println("\nGara terminata.");

        if (vincitore != null) {
            System.out.println("Vincitore: " + vincitore.getNome());
        }

        stampaClassifica();
        verificaPodio();
        salvaClassificaSuFile();
    }

    /**
     * Stampa la classifica finale a video
     * Gli atleti ritirati sono indicati con "(ritirato)"
     */
    public void stampaClassifica() {
        System.out.println("\nClassifica finale:");
        for (int i = 0; i < classifica.size(); i++) {
            String riga = (i + 1) + "° " + classifica.get(i).getNome();
            if (i >= arrivati) {
                riga += " (ritirato)";
            }
            System.out.println(riga);
        }
    }

    /**
     * Stampa il podio (prime tre posizioni) a video
     */
    public void verificaPodio() {
        System.out.println("\nPodio:");
        for (int i = 0; i < classifica.size() && i < 3; i++) {
            System.out.println((i + 1) + "° posto: " + classifica.get(i).getNome());
        }
    }

    /**
     * Salva la classifica finale della gara su file
     */
    private void salvaClassificaSuFile() {
        gestoreFile.scriviClassifica(classifica, arrivati);
    }
}
