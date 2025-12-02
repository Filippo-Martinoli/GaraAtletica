# Gara Atletica in Java

## Descrizione del progetto
L’idea del progetto è che ogni atleta corra in modo indipendente, proprio come succede in una gara reale.  
Per ottenere questo comportamento, ogni atleta è rappresentato da un thread separato che procede autonomamente lungo il percorso.  

Il programma permette all’utente di scegliere:
- Il numero di atleti partecipanti
- La lunghezza del percorso

Per ogni atleta vengono poi inseriti il nome e il numero.  
Durante la gara, ogni atleta avanza di una distanza casuale ogni secondo e ha anche una piccola probabilità di ritirarsi.  
Questo rende ogni gara diversa dalle altre e il risultato finale non è mai prevedibile.

## Funzionalità principali

1. **Gestione degli atleti**
   - Ogni atleta è un thread separato.
   - Movimento casuale lungo il percorso ogni secondo.
   - Possibilità di ritiro durante la gara.

2. **Gestione della gara**
   - La gara è coordinata dal Giudice.
   - Registrazione degli arrivi e dei ritiri.
   - Determinazione automatica del completamento della gara da parte di tutti gli atleti.
   - Visualizzazione della classifica completa e del podio.
   - Salvataggio della classifica su file di testo.

3. **Gestione file**
   - La classe GestoreFile legge la classifica precedente all’avvio, se presente.
   - Salva la classifica finale al termine della gara.
   - Ispirata al codice del prof. Amendola per un altro progetto scolastico.

4. **Interattività**
   - Inserimento del numero di atleti e lunghezza del percorso.
   - Inserimento del nome e numero di ciascun atleta.
   - Avvio della gara con aggiornamenti in tempo reale sui progressi degli atleti.

## Crediti
La classe GestoreFile e il salvataggio della classifica finale su file sono stati ispirati dal progetto del prof. Amendola.  
Il progetto scolastico Gara_Atletica è stato svolto presso l'ITTS A. Volta di Perugia nelle ore di TPSIT.
