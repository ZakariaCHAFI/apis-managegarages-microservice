# Use case : Microservice de gestion des garages, véhicules et accessoires

# Specification fonctionnel

Développer un microservice de gestion des informations relatives aux garages
affiliés à son réseau. Ce microservice doit répondre à plusieurs besoins
fonctionnels et inclure des contraintes métiers spécifiques.
Objectifs Fonctionnels
1. Gestion des garages :
   2. Création, modification et suppression de garages.
   3. Récupération d’un garage spécifique (par ID).
   4. Liste paginée de tous les garages, avec possibilité de tri (par nom,
      ville, etc.).
2. Gestion des véhicules :
   3. Ajout, modification et suppression de véhicules associés à un
      garage.
   4. Lister les véhicules d’un garage spécifique.
   5. Possibilité de lister tous les véhicules d’un modèle donné dans
      plusieurs garages.
3. Gestion des accessoires :
   4. Ajout, modification et suppression d&#39;accessoires associés à un
      véhicule. 
   5. Lister les accessoires d’un véhicule.
4. Requêtes de recherche :
   5. Rechercher des garages en fonction de critères spécifiques, tels
      que :
      6. Type de véhicule pris en charge. 
      7. Disponibilité d&#39;un accessoire particulier dans au moins un
         véhicule.

## Contraintes Métiers
* Quota de stockage des véhicules dans un garage :
   Chaque garage peut stocker au maximum 50 véhicules. 
* Partage des modèles de véhicules :
Un même modèle de véhicule peut être stocké dans plusieurs garages. 
* Informations obligatoires :
   Garage : name, address, telephone, email, horaires_ouverture.
horaires_ouverture =&gt; Map&lt;DayOfWeek,
   List&lt;OpeningTime&gt;&gt;
OpeningTime =&gt; {startTime : LocalDate, endTime :
   LocalDate}

Véhicule : brand, année de fabrication, type_carburant.

Accessoire : nom, description, prix, type.

## Tâches Techniques attendus

Modélisation du problème 
Développement au moins d&#39;une API REST complète 
Gestion des contraintes métiers 
Tests Unitaires et Intégration
