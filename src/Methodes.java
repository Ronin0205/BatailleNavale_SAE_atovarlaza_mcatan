import java.util.Scanner;
public class Methodes {
    public static Scanner scanner = new Scanner(System.in);

/**  Initialise les plateaux des joueurs et les plateaux de tir en remplissant
    toutes les cases avec le caractère '~' (cellule vide)
 */
    public static void generationDesPlateau(char[][] plateaujoueur1, char[][] plateaujoueur2, char[][] plateauTirJoueur1, char[][] plateauTirJoueur2) {
        for (int ligne = 0; ligne < plateaujoueur1.length; ligne++) {
            for (int col = 0; col < plateaujoueur1[ligne].length; col++) {
                plateaujoueur1[ligne][col] = '~';
                plateaujoueur2[ligne][col] = '~';
                plateauTirJoueur1[ligne][col] = '~';
                plateauTirJoueur2[ligne][col] = '~';
            }
        }
    }

    /**
     * Affiche un plateau de jeu avec des couleurs correspondant aux différents types de cases.
     * Les colonnes sont étiquetées de 'A' à 'J' et les lignes sont numérotées.
     * - '~' : Bleu, représente une case vide.
     * - 'P', 'C', 'c', 'S', 'T' : Jaune, représente différents types de navires.
     * - 'X' : Rouge, représente une case touchée.
     * - 'O' : Vert, représente une case manquée.
     */
    public static void afficherPlateau(char[][] plateau){
        int compteur =1;
        System.out.println("    "+"A  "+"B  "+"C  "+"D  "+"E  "+"F  "+"G  "+"H  "+"I  "+"J");
        for (int i = 0; i < plateau.length; i++) {
            System.out.printf("%2d  ",compteur);
            compteur++;
            for (int j = 0; j < plateau[i].length; j++) {
                switch (plateau[i][j]) {
                    case '~':System.out.print("\u001B[34m~" + "  ");break;
                    case 'P':System.out.print("\u001B[33mP" + "  ");break;
                    case 'C':System.out.print("\u001B[33mC" + "  ");break;
                    case 'c':System.out.print("\u001B[33mc" + "  ");break;
                    case 'S':System.out.print("\u001B[33mS" + "  ");break;
                    case 'T':System.out.print("\u001B[33mT" + "  ");break;
                    case 'X':System.out.print("\u001B[31mX" + "  ");break;
                    case 'O':System.out.print("\u001B[32m0" + "  ");break;
                    default:System.out.print(plateau[i][j] + "  ");
                }
            }
            System.out.print("\u001B[0m");
            System.out.println();
        }
        System.out.println();
    }

/**
 * Prépare la partie en demandant les pseudos des deux joueurs,
 * en vérifiant qu'ils sont différents, et en procédant à la phase
 * de placement des navires pour chaque joueur.*/
    public static void preparationPartie(){

        System.out.println("\nJoueur 1 entrez votre pseudo :");
        GestionBatailleNaval.pseudoJoueur1 = scanner.next();

        do {
            System.out.println("\nJoueur 2 entrez votre pseudo :");
            GestionBatailleNaval.pseudoJoueur2 = scanner.next();
        }while (GestionBatailleNaval.pseudoJoueur1.equals(GestionBatailleNaval.pseudoJoueur2));


        sautDelignes();

        afficherPlateau(GestionBatailleNaval.plateaujoueur1);
        placementNavireJoueur(GestionBatailleNaval.pseudoJoueur1, GestionBatailleNaval.plateaujoueur1);

        sautDelignes();

        System.out.println("À TOI " + GestionBatailleNaval.pseudoJoueur2.toUpperCase() + " !!\n");

        afficherPlateau(GestionBatailleNaval.plateaujoueur2);
        placementNavireJoueur(GestionBatailleNaval.pseudoJoueur2, GestionBatailleNaval.plateaujoueur2);

        sautDelignes();
    }

/**
 * Permet à un joueur de placer ses navires sur son plateau.
 * - Demande la position (format "A1") et la direction (h/v).
 * - Vérifie et place chaque navire en s'assurant qu'il ne dépasse pas les limites ou ne chevauche pas d'autres navires.
 * - Répète jusqu'à un placement valide et affiche le plateau après chaque placement.
 */
    public static void placementNavireJoueur(String joueur, char [][]plateau){
        int[] tailleNavire = {5, 4, 3, 3, 2}; // Longueurs des bateaux
        int compteur=0;
        String typeNavire;
        for (int i=0; i< tailleNavire.length;i++) {
            String position;
            char direction;
            int li;
            int c;
            boolean estplacer = false;

            switch (tailleNavire[i]){
                case 5:typeNavire="le Porte-avion";break;
                case 4:typeNavire="le Croiseur";break;
                case 3:if (compteur==2){
                    typeNavire="le Contre-torpilleur";break;
                }
                else {
                    typeNavire="le Sous-marin";break;
                }
                case 2:typeNavire="le torpilleur";break;
                default:typeNavire="le navire";
            }

            while (!estplacer) {

                do {
                    System.out.println(joueur + ", place " + typeNavire + " de taille " + tailleNavire[i] + " (format : A1) :");
                    position = scanner.next().toUpperCase();
                } while (position.length() > 3);

                do {
                    System.out.println("sens ? (h/v)");
                    direction = scanner.next().toLowerCase().charAt(0);
                }while (direction != 'v' && direction != 'h');

                 li = Integer.parseInt(position.substring(1)) - 1; // converti "A1" en indice : position.substring(1) permet d'avoir "1" puis Integer.parseInt() convertit
                 c = position.charAt(0) - 'A';

                estplacer = placementNavire(plateau, tailleNavire[i],compteur, c, li, direction);
                if (!estplacer) {
                    System.out.println("Réessayez.\n");
                    do{
                        do {
                            System.out.println(joueur + ", place " + typeNavire + " de taille " + tailleNavire[i] + " (format : A1) :");
                            position = scanner.next().toUpperCase();
                        } while (position.length() > 3);

                        do {
                            System.out.println("sens ? (h/v)");
                            direction = scanner.next().toLowerCase().charAt(0);
                        }while (direction != 'v' && direction != 'h');

                         li = Integer.parseInt(position.substring(1)) - 1; // converti "A1" en indice : position.substring(1) permet d'avoir "1" puis Integer.parseInt() convertit
                         c = position.charAt(0) - 'A';

                        estplacer = placementNavire(plateau, tailleNavire[i],compteur, c, li, direction);
                    }while (!estplacer);
                }
                compteur++;
            }
            afficherPlateau(plateau);
        }
    }

    /**
     * Place un navire sur le plateau de jeu si les conditions sont respectées.
     * - Vérifie que le navire ne dépasse pas les limites du plateau.
     * - Vérifie qu'il n'y a pas de collision avec d'autres navires.
     * - Place le navire en fonction de sa taille et de sa direction ('h' ou 'v').
     * - retourne true si le placement est réussi, false sinon.
     */
    public static boolean placementNavire(char[][] plateau, int taille, int compteurs, int c, int li, char direction){

        if ( ((plateau.length < li + taille) && (direction=='v')) || ((plateau[0].length < c + taille) && (direction == 'h'))  ){
            System.out.println("Placement invalide!");
            return false;
        }
        else {
            // Vérification des collisions
            for (int i = 0; i < taille; i++) {
                if ((direction == 'h' && plateau[li][c + i] != '~') ||
                        (direction == 'v' && plateau[li + i][c] != '~')) {
                    System.out.println("Collision détectée! Une case est déjà occupée.");
                    return false;
                }
            }

            //placement porte avion
            if (taille==5){
                if (direction == 'h') {
                    for (int i = 0; i < taille; i++) {
                        plateau[li][c + i] = 'P';
                    }
                }
                else{
                    for (int i = 0; i < taille; i++) {
                        plateau[li+i][c] = 'P';
                    }

                }
            }
            else if (taille==4) {
                if (direction == 'h') {
                    for (int i = 0; i < taille; i++) {
                        plateau[li][c + i] = 'C';
                    }
                }
                else{
                    for (int i = 0; i < taille; i++) {
                        plateau[li+i][c] = 'C';
                    }

                }
            }
            else if (taille==3 && compteurs==2) {
                if (direction == 'h') {
                    for (int i = 0; i < taille; i++) {
                        plateau[li][c + i] = 'c';
                    }
                }
                else{
                    for (int i = 0; i < taille; i++) {
                        plateau[li+i][c] = 'c';
                    }

                }
            }
            else if (taille==3 && compteurs==3) {
                if (direction == 'h') {
                    for (int i = 0; i < taille; i++) {
                        plateau[li][c + i] = 'S';
                    }
                }
                else{
                    for (int i = 0; i < taille; i++) {
                        plateau[li+i][c] = 'S';
                    }

                }
            }
            else {
                if (direction == 'h') {
                    for (int i = 0; i < taille; i++) {
                        plateau[li][c + i] = 'T';
                    }
                }
                else{
                    for (int i = 0; i < taille; i++) {
                        plateau[li+i][c] = 'T';
                    }

                }
            }

        }
        return true;
    }

    /**
     * Gère le déroulement des tours dans la partie.
     * - Alterne entre les deux joueurs jusqu'à ce que tous les navires d'un joueur soient coulés.
     * - Chaque joueur visualise le plateau caché de l'adversaire et effectue un tir.
     * - Affiche le résultat du tir (touché ou manqué) et les navires coulés.
     * - Déclare le gagnant lorsque tous les navires d'un joueur sont coulés et termine la partie.
     */
    public static void gestionTour(char[][] plateauJoueur1,char[][] plateauJoueur2,char[][] plateauJoueur1cachee,char[][] plateauJoueur2cachee){
        int i = 1;
        boolean toucher;
        while(!tousCoules(plateauJoueur1) && !tousCoules(plateauJoueur2)){
            if (i % 2 != 0){
                System.out.println("\nTour de " + GestionBatailleNaval.pseudoJoueur1 + ": ");
            }
            else {
                System.out.println("\nTour de " + GestionBatailleNaval.pseudoJoueur2 + ": ");
            }

            if (i % 2 != 0){
                System.out.println();
                afficherPlateau(plateauJoueur2cachee);
                toucher = gestionTir(plateauJoueur2, plateauJoueur2cachee);
                afficherPlateau(plateauJoueur2cachee);
                if (toucher){
                    System.out.println(GestionBatailleNaval.pseudoJoueur1 + " a touché un navire !!");
                }
                else {
                    System.out.println("A l'eau !! ");
                }
                affichageDesNaviresCoules(plateauJoueur2);
            }
            else {
                System.out.println();
                afficherPlateau(plateauJoueur1cachee);
                toucher = gestionTir(plateauJoueur1,plateauJoueur1cachee);
                afficherPlateau(plateauJoueur1cachee);
                if (toucher){
                    System.out.println(GestionBatailleNaval.pseudoJoueur2 + " a touché un navire !!");
                }
                else {
                    System.out.println("A l'eau !! \n");
                }
                affichageDesNaviresCoules(plateauJoueur1);
            }

            if (tousCoules(plateauJoueur2)){
                sautDelignes();
                System.out.println("\nVictore de " + GestionBatailleNaval.pseudoJoueur1 + " en " + i + " tours\n");
                GestionBatailleNaval.finDePartie();
            }
            else if (tousCoules(plateauJoueur1)) {
                sautDelignes();
                System.out.println("\nVictore de " + GestionBatailleNaval.pseudoJoueur2 + " en " + i + " tours\n");
                GestionBatailleNaval.finDePartie();
            }
            i++;
        }
    }

    /**
     * Gère un tir effectué par un joueur sur le plateau de l'adversaire.
     * - Demande la position du tir (format "A1") et vérifie sa validité.
     * - Marque la case comme touchée ('X') si un navire est présent, ou comme manquée ('O') sinon.
     * - Empêche de tirer plusieurs fois sur la même case.
     * - Retourne true si un navire a été touché, false si le tir a manqué.
     */
    public static boolean gestionTir(char[][] plateau, char[][] plateauCachee){
        String position;
        do {
            System.out.println("Entrez tir (format : A1): ");
            position = scanner.next().toUpperCase();
        }while (position.length() > 3);


        int li = Integer.parseInt(position.substring(1)) - 1; // converti "A1" en indice : position.substring(1) permet d'avoir "1" puis Integer.parseInt() convertit
        int c = position.charAt(0) - 'A';

        while (plateau[li][c] == 'X'){
            System.out.println("Tir invalide vous avez déja tirer ici !!  ");
            System.out.println("Entrez tir (format : A1): ");
            position = scanner.next().toUpperCase();
            li = Integer.parseInt(position.substring(1)) - 1; // converti "A1" en indice : position.substring(1) permet d'avoir "1" puis Integer.parseInt() convertit
            c = position.charAt(0) - 'A';
        }

        if (plateau[li][c] == 'P' || plateau[li][c] == 'C' || plateau[li][c] == 'c' || plateau[li][c] == 'S' || plateau[li][c] == 'T'){
            plateauCachee[li][c] = 'X';
            plateau[li][c] = 'X';
            return true;
        }
        else {
            plateauCachee[li][c] = 'O';
            plateau[li][c] = 'O';
            return false;
        }

    }

    /**
     * Vérifie et affiche les navires coulés sur un plateau donné.
     * - Parcourt le plateau pour déterminer si chaque type de navire ('P', 'C', 'c', 'S', 'T') est entièrement détruit.
     * - Affiche un message pour chaque navire coulé.
     */
    public static void affichageDesNaviresCoules(char [][]plateau){
        boolean PAcoules = true;
        boolean CroiseurCoules = true;
        boolean CTorpilleurCoules = true;
        boolean torpilleurCoules = true;
        boolean sMarinCoules = true;

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == 'P') {
                    PAcoules = false;
                    break;
                }
            }
            if (!PAcoules) {
                break;
            }
        }

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == 'C') {
                    CroiseurCoules = false;
                    break;
                }
            }
            if (!CroiseurCoules) {
                break;
            }
        }

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == 'c') {
                    CTorpilleurCoules = false;
                    break;
                }
            }
            if (!CTorpilleurCoules) {
                break;
            }
        }

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == 'S') {
                    sMarinCoules = false;
                    break;
                }
            }
            if (!sMarinCoules) {
                break;
            }
        }

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == 'T') {
                    torpilleurCoules = false;
                    break;
                }
            }
            if (!torpilleurCoules) {
                break;
            }
        }

        if (PAcoules) {
            System.out.println("\nLe Porte avion a été coulé.");
        }

        if (CroiseurCoules) {
            System.out.println("\nLe Croiseur a été coulé.");
        }

        if (sMarinCoules) {
            System.out.println("\nLe sous-marin a été coulé.");
        }

        if (CTorpilleurCoules) {
            System.out.println("\nLe Contre-Torpilleur a été coulé.");
        }

        if (torpilleurCoules) {
            System.out.println("\nLe Torpilleur a été coulé.");
        }
    }

    /**
     * Vérifie si tous les navires d'un plateau sont coulés.
     * - Parcourt le plateau pour détecter les cases contenant des parties de navires ('P', 'C', 'c', 'S', 'T').
     * - Si au moins une case contient un navire, retourne false.
     * - Retourne true si aucun navire n'est trouvé (tous coulés), false sinon.
     */
    public static boolean tousCoules(char[][] plateau){
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == 'P' || plateau[i][j] == 'C' || plateau[i][j] == 'c' || plateau[i] [j] == 'S' || plateau[i][j] == 't' || plateau[i][j] == 'T'){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Insère un grand nombre de lignes vides pour nettoyer la console.
     * - Affiche 200 lignes vides pour simuler un "nettoyage" de l'écran.
     */

    public static void sautDelignes(){
        for (int i = 0; i < 200; i++) {
            System.out.println();
        }
    }

//    public static void placementAutomatiqueNavire(char[][] plateauJoueur1,char[][] plateauJoueur2){
//        for (int i = 5; i > 0; i--) {
//            int li = (int)(Math.random()*(10-1+1)+1);
//            int c = (int)(Math.random()*(10-1+1)+1);
//            int direction = (int)(Math.random()*1);
//
//            if (direction == 0){
//                placementNavire(plateauJoueur1, i , c, li, 'h');
//                placementNavire(plateauJoueur2, i , c, li, 'h');
//            }
//            else{
//                placementNavire(plateauJoueur1, i , c, li, 'v');
//                placementNavire(plateauJoueur2, i , c, li, 'v');
//            }
//        }
//        afficherPlateau(plateauJoueur1);
//        afficherPlateau(plateauJoueur2);
//    }

}
