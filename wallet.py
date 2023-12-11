class Portefeuille:
    def __init__(self):
        self.solde = 0
        self.transactions = []
        self.cin_info = {"nom": "", "prenom": "", "numeroCIN": ""}
        self.current_category = "Money"

    def deposer(self, montant):
        self.solde += montant
        self.transactions.append({"type": "Recharge", "amount": montant})
        print("Dépôt réussi")

    def retirer(self, montant):
        if montant <= self.solde:
            self.solde -= montant
            self.transactions.append({"type": "Retrait", "amount": montant})
            print("Retrait réussi")
        else:
            print("Fonds insuffisants")

    def consulter_solde(self):
        return self.solde

    def total_depenses(self):
        return sum(transaction["amount"] for transaction in self.transactions if transaction["type"] == "Retrait")

    def suivi_transactions(self):
        print("Suivi des transactions :")
        for transaction in self.transactions:
            print(f"{transaction['type']}: {transaction['amount']}")

    def set_cin_info(self, nom, prenom, numero_cin):
        self.cin_info = {"nom": nom, "prenom": prenom, "numeroCIN": numero_cin}
        print("Informations CIN enregistrées avec succès")

    def afficher_cin_info(self):
        print("Informations CIN :")
        print(f"Nom: {self.cin_info['nom']}")
        print(f"Prénom: {self.cin_info['prenom']}")
        print(f"Numéro CIN: {self.cin_info['numeroCIN']}")


mon_portefeuille1 = Portefeuille()
mon_portefeuille2 = Portefeuille()

while True:
    print("Menu :")
    print("1. Money")
    print("2. CIN")
    print("0. Quitter")

    category_choice = input("Choisissez une catégorie : ")

    if category_choice == '1':
        mon_portefeuille1.current_category = 'Money'
        while True:
            print("Money Menu :")
            print("1. Recharge")
            print("2. Retrait")
            print("3. Consultation de solde")
            print("4. Total des dépenses")
            print("5. Suivi des transactions")
            print("0. Retour au menu principal")

            choix = input("Choisissez une option : ")

            if choix == '1':
                montant_depot = float(input("Entrez le montant à déposer : "))
                mon_portefeuille1.deposer(montant_depot)
            elif choix == '2':
                montant_retrait = float(input("Entrez le montant à retirer : "))
                mon_portefeuille1.retirer(montant_retrait)
            elif choix == '3':
                print("Solde actuel :", mon_portefeuille1.consulter_solde())
            elif choix == '4':
                print("Total des dépenses :", mon_portefeuille1.total_depenses())
            elif choix == '5':
                mon_portefeuille1.suivi_transactions()
            elif choix == '0':
                break
            else:
                print("Option invalide. Veuillez choisir une option valide.")
    elif category_choice == '2':
        mon_portefeuille1.current_category = 'CIN'
        while True:
            print("CIN Menu :")
            print("1. Enregistrer les informations CIN")
            print("2. Afficher les informations CIN")
            print("0. Retour au menu principal")

            choix = input("Choisissez une option : ")

            if choix == '1':
                nom = input("Entrez votre nom : ")
                prenom = input("Entrez votre prénom : ")
                numero_cin = input("Entrez votre numéro CIN : ")
                mon_portefeuille1.set_cin_info(nom, prenom, numero_cin)
            elif choix == '2':
                mon_portefeuille1.afficher_cin_info()
            elif choix == '0':
                break
            else:
                print("Option invalide. Veuillez choisir une option valide.")
    elif category_choice == '0':
        print("Au revoir !")
        break
    else:
        print("Option invalide. Veuillez choisir une option valide.")
