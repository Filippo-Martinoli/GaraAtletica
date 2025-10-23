const parole = {
    facile: ["luna", "riso", "zaino", "pane", "lago", "tenda"],
    medio: ["museo", "pianeta", "chitarra", "montagna", "sabato", "cappotto", "finestra"],
    difficile: ["programmazione", "astronautica", "intelligenza", "architetturale", "distribuzione", "telecomunicare"]
};

let parolaScelta = "";
let nascosta = [];
let tentativi = 6;

function iniziaGioco() {
    const livello = document.getElementById("livello").value;
    const lista = parole[livello];

    
    parolaScelta = lista[Math.floor(Math.random() * lista.length)];
    nascosta = [];

    for (let i = 0; i < parolaScelta.length; i++) {
        nascosta.push("_");
    }

    tentativi = 6;

    document.getElementById("parolaNascosta").innerHTML = nascosta.join(" ");
    document.getElementById("tentativi").innerHTML = tentativi;
    document.getElementById("messaggio").innerHTML = "";
    document.getElementById("gioco").style.display = "block";
    document.getElementById("lettera").disabled = false;
}

function provaLettera() {
    const lettera = document.getElementById("lettera").value.toLowerCase();
    document.getElementById("lettera").value = "";

    if (lettera.length !== 1 || !lettera.match(/[a-z]/i)) {
        document.getElementById("messaggio").innerHTML = "Inserisci una lettera valida.";
        return;
    }

    let trovata = false;

    for (let i = 0; i < parolaScelta.length; i++) {
        if (parolaScelta[i] === lettera) {
            nascosta[i] = lettera;
            trovata = true;
        }
    }

    if (!trovata) {
        tentativi--;
    }

    document.getElementById("parolaNascosta").innerHTML = nascosta.join(" ");
    document.getElementById("tentativi").innerHTML = tentativi;

    if (nascosta.join("") === parolaScelta) {
        document.getElementById("messaggio").innerHTML = " Hai vinto!";
        document.getElementById("lettera").disabled = true;
    } else if (tentativi === 0) {
        document.getElementById("messaggio").innerHTML = " Hai perso! La parola era: " + parolaScelta;
        document.getElementById("lettera").disabled = true;
    }
}