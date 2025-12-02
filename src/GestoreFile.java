import java.io.*;
import java.util.List;

/**
 * Classe per gestire la lettura e scrittura della classifica su file
 * Permette di salvare la classifica finale della gara e leggere
 * l'ultima classifica salvata.
 */
public class GestoreFile {

    /** Nome del file su cui salvare la classifica */
    private final String fileName = "classifica.txt";

    /**
     * Legge l'ultima classifica salvata (se esiste) e la stampa a video
     * Il metodo è sincronizzato per evitare problemi di concorrenza
     * se più thread tentano di accedere al file
     */
    public synchronized void leggiClassificaPrecedente() {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Nessuna classifica precedente trovata.");
            return;
        }

        System.out.println("\nUltima classifica salvata:");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("Fine della classifica precedente.\n");

        } catch (IOException e) {
            System.err.println("Errore nella lettura della classifica: " + e.getMessage());
        }
    }

    /**
     * Scrive la classifica finale della gara sul file
     * Gli atleti che non hanno concluso la gara vengono segnati come ritirati
     * Il metodo è sincronizzato per evitare conflitti di accesso al file
     * @param classifica lista degli atleti in ordine di arrivo
     * @param arrivati   numero di atleti che hanno completato la gara
     */
    public synchronized void scriviClassifica(List<Atleta> classifica, int arrivati) {
        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write("RESOCONTO CLASSIFICA DELLA GARA:");

            for (int i = 0; i < classifica.size(); i++) {
                Atleta atleta = classifica.get(i);

                String riga = (i + 1) + "° posizione - " + atleta.getNome();

                if (i >= arrivati) {
                    riga += " (ritirato)";
                }

                writer.write( "\n" + riga + "\n");

            }

            System.out.println("Classifica salvata correttamente su file.");

        } catch (IOException e) {
            System.err.println("Errore nella scrittura della classifica: " + e.getMessage());
        }
    }
}
