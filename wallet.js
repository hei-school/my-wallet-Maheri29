const prompt = require('prompt-sync')();

class Wallet {
    constructor() {
        this.balance = 0;
        this.transactions = [];
        this.cinInfo = { nom: '', prenom: '', numeroCIN: '' };
        this.currentCategory = 'Money';
    }

    deposit(amount) {
        this.balance += amount;
        this.transactions.push({ type: 'Recharge', amount: amount });
        console.log("Dépôt réussi");
    }

    withdraw(amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            this.transactions.push({ type: 'Retrait', amount: amount });
            console.log("Retrait réussi");
        } else {
            console.log("Fonds insuffisants");
        }
    }

    getBalance() {
        return this.balance;
    }

    getTotalExpenses() {
        return this.transactions
            .filter(transaction => transaction.type === 'Retrait')
            .reduce((total, transaction) => total + transaction.amount, 0);
    }

    getTransactionHistory() {
        console.log("Suivi des transactions :");
        this.transactions.forEach(transaction => {
            console.log(`${transaction.type}: ${transaction.amount}`);
        });
    }

    setCINInfo(nom, prenom, numeroCIN) {
        this.cinInfo = { nom, prenom, numeroCIN };
        console.log("Informations CIN enregistrées avec succès");
    }

    displayCINInfo() {
        console.log("Informations CIN :");
        console.log(`Nom: ${this.cinInfo.nom}`);
        console.log(`Prénom: ${this.cinInfo.prenom}`);
        console.log(`Numéro CIN: ${this.cinInfo.numeroCIN}`);
    }
}

const monPortefeuille1 = new Wallet();
const monPortefeuille2 = new Wallet();

while (true) {
    console.log("Menu :");
    console.log("1. Money");
    console.log("2. CIN");
    console.log("0. Quitter");

    const categoryChoice = prompt("Choisissez une catégorie : ");

    switch (categoryChoice) {
        case '1':
            monPortefeuille1.currentCategory = 'Money';
            handleMoneyCategory(monPortefeuille1);
            break;
        case '2':
            monPortefeuille1.currentCategory = 'CIN';
            handleCINCategory(monPortefeuille1);
            break;
        case '0':
            console.log("Au revoir !");
            process.exit(0);
        default:
            console.log("Option invalide. Veuillez choisir une option valide.");
    }
}

function handleMoneyCategory(wallet) {
    while (true) {
        console.log("Money Menu :");
        console.log("1. Recharge");
        console.log("2. Retrait");
        console.log("3. Consultation de solde");
        console.log("4. Total des dépenses");
        console.log("5. Suivi des transactions");
        console.log("0. Retour au menu principal");

        const choix = prompt("Choisissez une option : ");

        switch (choix) {
            case '1':
                const montantDepot = parseFloat(prompt("Entrez le montant à déposer : "));
                wallet.deposit(montantDepot);
                break;
            case '2':
                const montantRetrait = parseFloat(prompt("Entrez le montant à retirer : "));
                wallet.withdraw(montantRetrait);
                break;
            case '3':
                console.log("Solde actuel :", wallet.getBalance());
                break;
            case '4':
                console.log("Total des dépenses :", wallet.getTotalExpenses());
                break;
            case '5':
                wallet.getTransactionHistory();
                break;
            case '0':
                return;
            default:
                console.log("Option invalide. Veuillez choisir une option valide.");
        }
    }
}

function handleCINCategory(wallet) {
    while (true) {
        console.log("CIN Menu :");
        console.log("1. Enregistrer les informations CIN");
        console.log("2. Afficher les informations CIN");
        console.log("0. Retour au menu principal");

        const choix = prompt("Choisissez une option : ");

        switch (choix) {
            case '1':
                const nom = prompt("Entrez votre nom : ");
                const prenom = prompt("Entrez votre prénom : ");
                const numeroCIN = prompt("Entrez votre numéro CIN : ");
                wallet.setCINInfo(nom, prenom, numeroCIN);
                break;
            case '2':
                wallet.displayCINInfo();
                break;
            case '0':
                return;
            default:
                console.log("Option invalide. Veuillez choisir une option valide.");
        }
    }
}
