import java.util.Random;
public class Atleta implements Runnable {
    private final int numero;
    private final String nome;
    private double metri = 0;
    private boolean ritirato = false;
    private boolean concluso = false;
    public static double lunghezzaPercorso;
    private final Giudice giudice;
    private final Random random = new Random();

    public Atleta(String nome, int numero, Giudice giudice) {
        this.numero = numero;
        this.nome = nome;
        this.giudice = giudice;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void run() {
        while (!ritirato && !concluso) {
            double velocita = random.nextDouble() * 10;
            metri += velocita;
            System.out.printf("%s metri percorsi: %.2f\n", nome, metri);
            if (metri >= lunghezzaPercorso) {
                concluso = true;
                giudice.registraArrivo(this);
                break;
            }
            if (random.nextInt(100) < 4) { //possibilita di ritiro
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
