Readme.txt présente les informations essentiels sur :

		- L'organisation des répertoires
		- Le cheminement du projet
		- Les auteurs de ce projet



//ARBORESCENCE\\


.classpath permet d’exécuter et compiler le code sur eclipse
.project permet de créer une structure au sein d'eclipse pour accueillir un projet complet

/.settings/ pas d'importance au sein du projet

/bin/ contient les .class du projet

/doc/ contient la documentation afin de comprendre le projet

/src/ contient les .java du projet soit une accessibilité direct au code ainsi que les ressources nécessaires au jeu (Sons, images et feuille de style

	------------------


//CHEMINEMENT_DU_PROJET\\

Rôles :
	BARBET Florian 		:	 Chef de projet / Programmeur
	MURAT Edouard   	: 	 Concepteur / Programmeur
	LEICHT Theo 		: 	 Analyste Programmeur 
	BRUNI Xavier 		:	 Programmeur	
	SKAWAND Dylan   	: 	 Programmeur
	CORNET Thomas   	:	 Programmeur
	RICHARD Alexandre 	:	 Programmeur


/!\ petit plus à la partie conception du projet, elle a été réalisé par tout les membres du groupes mais par soucis de conformité nous avons désigné quelqu'un comme responsable de cette partie.

Déroulement Jalon1 :
	Interne à l'IUT>>

		Première séance		: Réunion d'organisation des tâches + mise en place de la conception + mise en place d'un moyen de partage des données et communication extérieur
		Deuxième séance		: Correction de la partie conception + mise en place des rôles(Et organisation du travail)
		Troisième séance		: Réalisation des classes(Problème de vision du code)(*)+modification de l'organisation	
				(*)Chacun voyaient le code de manières différentes ce qui a causé un mouvement de formation de groupes(Réglé suite à la modification de TOUTE la structure)

		Quatrième séance		: Réalisation de la version 2.0 de VirtualWar + Création de la classe "Maîtresse" Jouer(fait guise de main)
		*Dernière séance		: Debuggage de la version 2.0 + Mise en place d'une analyse pour la partie IA du jalon2

	Externe à l'IUT>>
		La plupart des classes réalisé à l’extérieur, les réunions lors des séances internes ont servi pour raccorder les codes ensembles, pour gérer l'organisation et se fixer des objectifs.
		Malgré une communication externe insuffisante, nous avons pu mener à bien ce projet.

		Cependant le temps ne nous a pas été favorable, en effet, peu d’expérience dans la gestion de projet, et l'organisation en groupe aussi conséquent ne nous à pas été facile.
		C'est pour cela qu'il réside encore certains "bug" au sein de notre version.

		Niveau partage des documents, après chaque travaux finis, un dépôt sur le drive est fait, ce qui a permis une bonne avance.

	Les choses que nous aurions dû faire>>

		Nous aurions dû créé un GANTT et un PERT afin de bien répartir les tâches, donner le rôle de chacun dès la première séance afin que le chef de projet désigne la bonne conception.
		Suite à cela, nous aurions du créé des Tests complets pour chaque classe et chaque méthode afin d'évaluer si ceux-ci fonctionne(faute de temps nous n'avons pas élu un testeur).
		Faute de matériel, nous aurions dû utiliser un tableau assez large afin d'évaluer une stratégie net et claire !

		L'encadrement aurait dû être plus "fort" afin que chacun fasse le travail demandé(pas plus) et sans envisager de modifier la structure sans l'avis ni d'un Analyste, ni du Chef de Projet!

		Enfin, nous aurions apprécié l'avis d'un expert afin de poser des questions technique.

	Conclusion >> 

		Bon groupe, peu de communication, mais fautes de ressources, et de compétences ; nous pouvons conclure que le déroulement s'est bien passé
		car malgré le fait que ce fût difficile à organiser, nous avons tous pu apprendre quelque chose.
		Malheureusement, il faudra être beaucoup plus organiser au Jalon 2 afin de pouvoir vraiment debugger tout le code, et de réaliser les tâches prévues !


Déroulement Jalon2 :
	Interne à l'IUT>>

		Première sèance : Surement la scèance la plus constructive que l'ont ait eu depuis le début, l'organisation s'est mise à renaitre.
		Deuxième scèance : Mise en place de l'UML, modification, accord, discussion, ensuite nous avons parler stratégie.
		Troisième scèance : Ecriture du code + javadoc
		Reste des scèances : Amelioration, nottament lors de la dernière, nous avons rendu l'intelligence plus aggresive de plus nous avons commençait une partie du jalon 3

	Externe à l'IUT>>
		Moins de travail externe, il s'agissait plus de debugging, ou de discussion. Nous avons pu nous mettre d'accord, sur certains points de nôtre organisation, afin de préserver une bonne entente
		et un bon travail.
		Cependant nous n'avons pas pu régler certaines fonctionnalité du premier jalon, mais avons trouvé la cause.

	Conclusion>>
		Une Organisation refaite, une bonne entente, un travail de qualité, de la discussion, ce jalon à été très productif, en terme de performance - "cout"(inexistant dans ce projet) - delais.


Déroulement Jalon3 :
	Interne à l'IUT>>
		Sur une quinzaine d'heures, nous nous sommes concertés afin d'établir les taches à attribuer pour le troisieme jalon.
Globalement, tout s'est bien passé : répartition des taches, entente, conception malgré quelques litiges au sein du groupe vers les dernieres séances (certainement dû à la pression du rendu)

	Externe à l'IUT>>
		Davantage de travail en exterieur dû au nombreuses modifications nécessaires au bon fonctionnement du code et de sa partie graphique.

	Conclusion>>
		Malgré les litiges qui auraient pu perturber la cohésion du groupe, nous avons mis d'avantages de moyens afin de régler les problèmes et d'arriver à un résultat le plus fidele à la demande du cahier des charges.

	Autres informations >>
		Prolème de compatiliblité avec les médias sous Linux non résolu (même si une méthode a été crée) ==> executer sous windows de préférence.
		Adaptation de l'IA du Jalon 2 non terminé pour le Jalon 3.
		Clique gauche pour séléctionner un robot, clique droit pour se déplacer/attaquer/miner.
		Les regles ne sont pas fini. La javadoc est fait, mais par un souci technique de derniere minute, elle n'a pas pu etre genere. Cependant, elle est generable sous un ordinateur possedant javadoc.exe

			
//AUTHORS\\

Tous droits réservés aux auteurs de ce projet : BARBET Florian ; LEICHT Theo ; MURAT Edouard ; SKAWAND Dylan ; BRUNI Xavier ; CORNET Thomas ; RICHARD Alexandre

Crée le 17/01/2017 à Lille.
