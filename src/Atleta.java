import java.util.Random;

/**
 * Rappresenta un atleta che partecipa a una gara. Ogni atleta viene eseguito come
 * un thread separato e simula l'avanzamento nel percorso, con possibilità di
 * completare la gara o ritirarsi casualmente.
 */
public class Atleta implements Runnable {

    /** Numero identificativo dell'atleta */
    private final int numero;

    /** Nome dell'atleta */
    private final String nome;

    /** Metri percorsi dall'atleta durante la gara */
    private double metri = 0;

    /** Indica se l'atleta si è ritirato dalla gara */
    private boolean ritirato = false;

    /** Indica se l'atleta ha completato la gara */
    private boolean concluso = false;

    /** Riferimento al giudice che gestisce la gara */
    private final Giudice giudice;

    /** Generatore di numeri casuali per simulare velocità e ritiro */
    private final Random random = new Random();

    /**
     * Costruttore della classe Atleta.
     * @param nome nome dell'atleta
     * @param numero numero identificativo dell'atleta
     * @param giudice riferimento al giudice che gestisce la gara
     */
    public Atleta(String nome, int numero, Giudice giudice) {
        this.numero = numero;
        this.nome = nome;
        this.giudice = giudice;
    }

    /**
     * Restituisce il nome dell'atleta.
     * @return il nome dell'atleta
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo eseguito dal thread dell'atleta. Simula la corsa avanzando di una
     * quantità casuale di metri ogni secondo. L'atleta può
     * concludere la gara se raggiunge la lunghezza del percorso
     * ritirarsi con una probabilità del 4%
     * Alla conclusione, il metodo avvisa il giudice del risultato.
     */
    @Override
    public void run() {
        while (!ritirato && !concluso) {
            double velocita = random.nextDouble() * 10;
            metri += velocita;
            System.out.printf("%s metri percorsi: %.2f\n", nome, metri);

            // Controllo se ha completato il percorso
            if (metri >= giudice.lunghezzaPercorso) {
                concluso = true;
                giudice.registraArrivo(this);
                break;
            }

            // Probabilità di ritiro
            if (random.nextInt(100) < 4) {
                ritirato = true;
                giudice.registraRitiro(this);
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Errore sleep");
            }
        }
    }
}
