# Sujet

Développement d’une WEB API d’une gestion de contacts dans une entreprise.
 
## Features : 
 
- Création d’un contact.
-	Mise à jour d’un contact.
-	Suppression d’un contact.
-	Création d’une entreprise.
-	Mise à jour d’une entreprise.
-	Mise à jour des détails d’une entreprise (adresse).
-	Possibilité d’ajouter des adresses à une entreprise et y définir son siège central.
 
## Contraintes Business : 
 
-	Un contact doit avoir une adresse, un nom et un prénom
-	Un contact travaille dans une ou plusieurs entreprises
-	Une entreprise a au moins une adresse Siège central mais peut aussi avoir d’autres adresses (entités satellites).
-	Une entreprise a un numéro de TVA
-	Un contact peut être employé ou freelance
  	S’il est freelance, il doit avoir un numéro de TVA.
 
## Contraintes techniques : 
 
-	WEB API REST (JSON) synchrone
-	Utilisation de beans Spring
-	L’utilisation de routes Camel est un plus, mais n’est pas obligatoire
-	Architecture la plus maintenable possible.
-	JPA - Hibernate
-	La base de données est une DB en mémoire (HSQL ou H2)
-	Faire quelques tests unitaires seulement si nécessaire.
-	La solution doit compiler.
-	L’API doit répondre via des requêtes POSTMAN
-	Repo de sources public (genre Github) ou sources dans un zip à m’envoyer par email (sans le target).

