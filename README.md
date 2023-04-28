# rentmanager
Java project 4A

## Fonctionnalités implémentées

- Page d'accueil avec affichage du nombre de clients, véhicules et réservations
- Création client, véhicule, réservation
- Suppression client, véhicule, réservation
- Mise à jour client, véhicule, réservation
- Page détails client avec nombre réservations et véhicules, liste réservations et véhicules
- Projet sous Spring 
- Mise en place des tests 

## Contraintes réalisées

- un client n'ayant pas 18 ans ne peut pas être créé.
- un client ayant une adresse mail déjà prise ne peut pas être créé.
- le nom et le prénom d'un client doivent faire au moins 3 caractères.
- une voiture ne peut pas être réservée 2 fois le même jour.
- une voiture ne peut pas être réservée plus de 7 jours de suite par le même utilisateur.
- si un client ou un véhicule est supprimé, alors il faut supprimer les réservations associées.
- une voiture doit avoir un modèle et un constructeur, son nombre de place doit être compris entre 2 et 9.

## Choix et axes d'amélioration

- j'ai choisi de rajouter le modèle de voiture dans la base de données pour que l'utilisateur de l'application dispose de plus d'informations.
- Pour que les contraintes de champs soient comprises par l'utilisateur, des messages d'erreur s'affichent sur les pages en fonction des contraintes qu'il n'a pas respectées.
- J'ai implémenté la suppression en cascade en modifiant la base de données SQL pour qu'une réservation soit supprimée si sa voiture ou son client venaientà être supprimés de la BDD.
