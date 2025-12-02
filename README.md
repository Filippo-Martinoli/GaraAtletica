Descrizione del progetto
L’idea del progetto è simulare una gara di atletica in cui ogni atleta corre in modo indipendente, proprio come nella realtà.
Per ottenere questo comportamento sono stati utilizzati i thread: ogni atleta è infatti un thread separato che avanza autonomamente.
Funzionamento generale
All’avvio del programma, l’utente sceglie:
il numero di atleti partecipanti,
la lunghezza del percorso.
Per ogni atleta vengono inseriti:
il nome,
il numero identificativo.
La gara inizia e:
ogni atleta avanza di una distanza casuale ogni secondo,
esiste una piccola probabilità che un atleta si ritiri,
ogni gara ha quindi un risultato diverso e imprevedibile.
Ruolo delle classi
Giudice
Registra gli arrivi degli atleti.
Gestisce i ritiri.
Stabilisce quando la gara è conclusa.
Mostra la classifica finale e il podio.
Salva il risultato su un file di testo.
GestoreFile
All’avvio legge la classifica precedente (se presente).
Al termine salva la nuova classifica.
Per la sua implementazione è stato preso spunto da un codice fornito dal prof. Amendola per un altro progetto.
GaraAtletica
Gestisce l’interazione con l’utente.
Crea gli atleti e avvia i loro thread.
Contesto scolastico
Questo progetto è stato realizzato come attività scolastica nelle ore di TPSIT presso l’ITTS A. Volta di Perugia.
