# /roles

Format: ``/roles [info/set/random] [role/player ?] [index ?]``

## *Arguments*

Chaque fois que vous voyez ``[ ]``, cela signifie que vous devez préciser un *argument*.

## Contexte

Récupère les informations sur un rôle : ``/roles info [nom du rôle]``

Met à jour le rôle d'un joueur (permet de dépasser le nombre maximum de joueur(s) par rôle normalement atteignable ; par exemple, il est possible d'avoir 5 loups-garous au lieu de 3) 
manuellement : ``/roles set [jouuer] [index du rôle]``

Attribut à tous les joueurs connectés un rôle de façon aléatoire, tout en respectant le nombre maximum de joueur(s) par rôle : ``/roles random``

### /roles info

Cette commande donne des informations sur le rôle que vous avez indiqué en *argument*.

Par ailleurs, vous **ne devez pas** mettre d'espace mais des tirets ("-") !

Voici ce que vous pouvez mettre comme *argument* : 

- Loup-Garou
- Infect-Père-des-Loups
- Grand-Méchant-Loup 
- Villageois
- Cupidon
- Voyante
- Soeurs ou Sœurs
- Petite-Fille
- Renard
- Chevalier-Epée-Rouillée ou Chevalier-Épée-Rouillée
- Ancien
- Sorcière
- Montreur-Ours
- Chasseur
- Enfant-Sauvage
- Chien-Loup
- Loup-Garou-Blanc
- Joueur-Flûte
- Ange


Exemple : ``/roles info Ange``

Ici, vous recevrez comme message : "S'il est éliminé lors du premier tour, après la première nuit : il gagne la partie !"

### /roles set

Cette commande donne des informations sur le rôle que vous avez indiqué en *argument*.

Par ailleurs, le premier argument est **le nom du joueur** puis on a l'index du rôle, voici les arguments que vous pouvez mettre (en fonction du rôle) :

- Loup-Garou -> ``1``
- Infect Père des loups -> ``2``
- Grand Méchant loup -> ``3``
- Villageois -> ``4``
- Cupidon -> ``5``
- Voyante -> ``6``
- Soeurs -> ``7``
- Petite fille -> ``8``
- Renard -> ``9``
- Chevalier à l'épée rouillée -> ``10``
- Ancien -> ``11`` 
- Sorcière -> ``12``
- Montreur d'ours -> ``13``
- Chasseur -> ``14``
- L'enfant sauvage -> ``15``
- Chien Loup -> ``16``
- Loup Garou Blanc -> ``17``
- Joueur de flûte  -> ``18``
- Ange -> ``19``

Exemple: ``/roles set Program132 1``

Ici le joueur Program132 aura le rôle Loup Garou.