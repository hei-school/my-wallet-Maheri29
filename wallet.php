<?php

class Wallet {
    private $balance;
    private $transactions;
    private $cinInfo;
    private $currentCategory;

    public function __construct() {
        $this->balance = 0;
        $this->transactions = [];
        $this->cinInfo = ['nom' => '', 'prenom' => '', 'numeroCIN' => ''];
        $this->currentCategory = 'Money';
    }

    public function deposit($amount) {
        $this->balance += $amount;
        $this->transactions[] = ['type' => 'Recharge', 'amount' => $amount];
        echo "Dépôt réussi\n";
    }

    public function withdraw($amount) {
        if ($amount <= $this->balance) {
            $this->balance -= $amount;
            $this->transactions[] = ['type' => 'Retrait', 'amount' => $amount];
            echo "Retrait réussi\n";
        } else {
            echo "Fonds insuffisants\n";
        }
    }

    public function getBalance() {
        return $this->balance;
    }

    public function getTotalExpenses() {
        $totalExpenses = 0;
        foreach ($this->transactions as $transaction) {
            if ($transaction['type'] === 'Retrait') {
                $totalExpenses += $transaction['amount'];
            }
        }
        return $totalExpenses;
    }

    public function getTransactionHistory() {
        echo "Suivi des transactions :\n";
        foreach ($this->transactions as $transaction) {
            echo "{$transaction['type']}: {$transaction['amount']}\n";
        }
    }

    public function setCINInfo($nom, $prenom, $numeroCIN) {
        $this->cinInfo = ['nom' => $nom, 'prenom' => $prenom, 'numeroCIN' => $numeroCIN];
        echo "Informations CIN enregistrées avec succès\n";
    }

    public function displayCINInfo() {
        echo "Informations CIN :\n";
        echo "Nom: {$this->cinInfo['nom']}\n";
        echo "Prénom: {$this->cinInfo['prenom']}\n";
        echo "Numéro CIN: {$this->cinInfo['numeroCIN']}\n";
    }
}

$myWallet1 = new Wallet();
$myWallet2 = new Wallet();

while (true) {
    echo "Menu :\n";
    echo "1. Money\n";
    echo "2. CIN\n";
    echo "0. Quitter\n";

    $categoryChoice = readline("Choisissez une catégorie : ");

    switch ($categoryChoice) {
        case '1':
            $myWallet1->currentCategory = 'Money';
            handleMoneyCategory($myWallet1);
            break;
        case '2':
            $myWallet1->currentCategory = 'CIN';
            handleCINCategory($myWallet1);
            break;
        case '0':
            echo "Au revoir !\n";
            exit(0);
        default:
            echo "Option invalide. Veuillez choisir une option valide.\n";
    }
}

function handleMoneyCategory($wallet) {
    while (true) {
        echo "Money Menu :\n";
        echo "1. Recharge\n";
        echo "2. Retrait\n";
        echo "3. Consultation de solde\n";
        echo "4. Total des dépenses\n";
        echo "5. Suivi des transactions\n";
        echo "0. Retour au menu principal\n";

        $choice = readline("Choisissez une option : ");

        switch ($choice) {
            case '1':
                $depositAmount = readline("Entrez le montant à déposer : ");
                $wallet->deposit((float)$depositAmount);
                break;
            case '2':
                $withdrawAmount = readline("Entrez le montant à retirer : ");
                $wallet->withdraw((float)$withdrawAmount);
                break;
            case '3':
                echo "Solde actuel : " . $wallet->getBalance() . "\n";
                break;
            case '4':
                echo "Total des dépenses : " . $wallet->getTotalExpenses() . "\n";
                break;
            case '5':
                $wallet->getTransactionHistory();
                break;
            case '0':
                return;
            default:
                echo "Option invalide. Veuillez choisir une option valide.\n";
        }
    }
}

function handleCINCategory($wallet) {
    while (true) {
        echo "CIN Menu :\n";
        echo "1. Enregistrer les informations CIN\n";
        echo "2. Afficher les informations CIN\n";
        echo "0. Retour au menu principal\n";

        $choice = readline("Choisissez une option : ");

        switch ($choice) {
            case '1':
                $nom = readline("Entrez votre nom : ");
                $prenom = readline("Entrez votre prénom : ");
                $numeroCIN = readline("Entrez votre numéro CIN : ");
                $wallet->setCINInfo($nom, $prenom, $numeroCIN);
                break;
            case '2':
                $wallet->displayCINInfo();
                break;
            case '0':
                return;
            default:
                echo "Option invalide. Veuillez choisir une option valide.\n";
        }
    }
}
