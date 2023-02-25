# Loup Garou

Loup Garou est un plugin minecraft, actuellement en 1.19.3.

## Description

Un jeu de société disponible sur Minecraft pour jouer avec vos amis :) ! 

Votre objectif est simple survivre dans ce village, des loups s'y cachent et se cachent, vous avez un rôle qui vous donne des capacités spéciales durant la partie.
Utilisé vos dispositions avec intelligences.

## Les catégories

Ces deux camps sont divisés dans plusieurs rôles.
Pour les "méchants", les loups :
- Loup classique (jusqu'à 4 joueurs)
- Le père des loups (1 joueur)
- Le méchant loup (1 joueur)
- Le loup blanc (1 joueur)


Pour les "gentils", les villageois :

- Le villageois classique (jusqu'à 7 joueurs)
- La voyante (1 joueur)
- La sorcière (1 joueur)
- L'ange (1 joueur)


## Les rôles

- Le villageois n'a pas d'interaction a effectué
- La voyante peut voir le rôle d'un autre joueur.
- La sorcière peut tuer un joueur aux choix ou ressusciter le joueur qui a été visé par le camp des loups.
- L'ange est un joueur solo, pour qu'il gagne, il doit se faire éliminer dès le premier vote après la première nuit.

- Les loups doivent être un nombre supérieur ou égal que celui des villageois pour gagner (par exemple 4 loups et 4 villageois).
- Le père loup peut désigner une personne après que les loups ont dévoré quelqu'un.
- Le méchant loup peut dévorer un membre du village, tout sauf dans le camp des loups.
- Le loup blanc a pour but d'être le dernier loup, toute les 2 nuits, il peut dévorer un loup classique.

## Règles

Le but des loups est d'éliminer tout le village, comparé aux villageois qui doivent trouver les loups parmis eux.
Aucun joueur ne doit divulger son rôle pendant la partie.

On trouve un maître du jeu qui vous donnera votre rôle et vous informera des dernières actualités pendant la partie. 
Il garde les rôles des joueurs pour lui et ne doit en aucun cas influencer les joueurs.

Le MJ (Maitre du Jeu) met les rôles et faire dormir le village pour que le camp des loups attaquent. 
Il fait interagir les différents rôles dans un certain ordre : Loup classique, père des loups, méchant loup, loup blanc, voyante, sorcière, ange. 
Les villageois ne peuvent pas se réveiller.
Ensuite au réveillé il annonce les actualités. Si quelqu'un est mort, il le dit à voix haute ou dans le chat du jeu, le joueur tué doit révéler son rôle aux autres.

Les loups doivent être un nombre supérieur ou égal que celui des villageois pour gagner (par exemple 4 loups et 4 villageois).
Le loup blanc ne peut dévorer qu'un loup classique, s'il est le dernier survivant du village, il gagne.
Le père des loups désigne qu'un infecté qu'une fois dans la partie.


## Documentation

### day, jour

Réveiller le village : `day` ou `jour`

### night, nuit

Faire dormir le village : `nuit` ou `night`

### partie

Remettre à 0, redémarrer une partie: `partie reset`

Donner un rôle à un joueur: `partie role NomDuJoueur Role`

Retirer le rôle à un joueur: `partie role NomDuJoueur retirer`