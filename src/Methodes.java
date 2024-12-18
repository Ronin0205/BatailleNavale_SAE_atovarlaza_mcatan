import java.util.*;
public class Methodes {
    public static Scanner scanner = new Scanner(System.in);

    public static void generationPlateau(char[][] plateau){
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int col = 0; col < plateau[ligne].length; col++) {
                plateau[ligne][col] = '~';
            }
        }
    }


    public static void afficherPlateau(char[][] plateau){
        int compteur =1;
        System.out.println("    "+"A  "+"B  "+"C  "+"D  "+"E  "+"F  "+"G  "+"H  "+"I  "+"J");
        for (int i = 0; i < plateau.length; i++) {
            System.out.printf("%2d  ",compteur);
            compteur++;
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == '~') {
                    System.out.print("\u001B[34m~" + "  ");
                }
                else if (plateau[i][j] == 'B') {
                    System.out.print("\u001B[33mB" + "  ");
                } else {
                    System.out.print(plateau[i][j] + "  ");
                }
            }
            System.out.print("\u001B[0m");
            System.out.println();
        }
        System.out.println();
    }
//System.out.print(plateau[i][j] + "  ");

    public static void menu(){
        String marron = "\u001B[33m"; // Couleur marron
        String reset = "\u001B[0m";

        String texte = """
              ______       _        _ _ _        _   _                  _      
             | ___ \\     | |      (_) | |      | \\ | |                | |     
             | |_/ / __ _| |_ __ _ _| | | ___  |  \\| | __ ___   ____ _| | ___ 
             | ___ \\/ _` | __/ _` | | | |/ _ \\ | . ` |/ _` \\ \\ / / _` | |/ _ \\
             | |_/ / (_| | || (_| | | | |  __/ | |\\  | (_| |\\ V / (_| | |  __/
             \\____/ \\__,_|\\__\\__,_|_|_|_|\\___| \\_| \\_/\\__,_| \\_/ \\__,_|_|\\___|  
        """;

        System.out.println(marron + texte + reset);
        System.out.println();
        System.out.println("1. Démarer une partie\n" +
                "2. Redécouvrir les règles\n" +
                "3. Quitter\n");

        System.out.print("==>  ");
        int choix=0;
        choix = scanner.nextInt();
        switch (choix){
            case 1: Methodes.preparationPartie();break;
            case 2:Methodes.regles();
        }
    }

    public static void regles(){
        System.out.println("Règles du Jeu : Bataille Navale\n \n" +
                "Objectif du Jeu\n \n" +
                "\tL'objectif est de couler tous les navires de l'adversaire en devinant leur position sur sa grille.\n\tLe premier joueur à couler tous les navires ennemis remporte la partie."
        );
        System.out.println();
        System.out.println("Mise en Place");
        System.out.println();
        System.out.println("\t1. Plateaux de jeu :");
        System.out.println();
        System.out.println("\t \tChaque joueur a une grille (par exemple, 10x10) où placer ses navires.");
        System.out.println("\t \tLes colonnes sont identifiées par des lettres (A à J) et les lignes par des chiffres (1 à 10).");
        System.out.println();
        System.out.println("\t2. Placement des navires :");
        System.out.println();
        System.out.println("\t \tChaque joueur dispose de plusieurs navires de tailles différentes :");
        System.out.println();
        System.out.println("\t \t \t- Porte-avions (5 cases)");
        System.out.println("\t \t \t- Croiseur (4 cases)");
        System.out.println("\t \t \t- Contre-torpilleur (3 cases)");
        System.out.println("\t \t \t- Sous-marin (3 cases)");
        System.out.println("\t \t \t- Torpilleur (2 cases)");
        System.out.println();
        System.out.println("\t \tLes navires doivent être placés horizontalement ou verticalement (jamais en diagonale).");
        System.out.println("\t \tLes navires ne peuvent pas se chevaucher ni dépasser les bords de la grille.");
        System.out.println();
        System.out.println("\t3. Grille vide :");
        System.out.println();
        System.out.println("\t\tAvant de placer leurs navires, les joueurs peuvent consulter une grille vide pour planifier leur stratégie");
        System.out.println();
        System.out.println("Déroulement du jeu");
        System.out.println();
        System.out.println("\t1. Tour par tour\n");
        System.out.println("\t \tChaque joueur joue à tour de rôle.\n" +
                "\t \tÀ son tour, un joueur :\n\n" +
                "\t \t \tDevine une position sur la grille ennemie (par exemple, B5).\n" +
                "\t \t \tLe résultat est annoncé :\n\n" +
                "\t \t \t \t- Touché : Un navire ennemi occupe cette case.\n" +
                "\t \t \t \t- À l'eau : Cette case est vide.\n" +
                "\t \t \t \t- Coulé : Si toutes les cases d’un navire sont touchées, ce navire est détruit."
        );
        System.out.println();
        System.out.println("\t2. Grille cachée :");
        System.out.println();
        System.out.println("\t \tChaque joueur voit sa propre grille avec ses navires et les attaques ennemies");
        System.out.println("\t \tLa grille de l’adversaire montre seulement les attaques réussies (Touché ou À l'eau).");
        System.out.println();
        System.out.println("Conditions de Victoire");
        System.out.println();
        System.out.println("\tLe jeu se termine dès qu'un joueur a coulé tous les navires de son adversaire.");
        System.out.println("\tCe joueur est déclaré vainqueur.");
        System.out.println();
        System.out.println("Règles Supplémentaires");
        System.out.println();
        System.out.println("\t1. Validité des tirs :");
        System.out.println();
        System.out.println("\t\tUn joueur ne peut pas tirer deux fois sur la même case.");
        System.out.println("\t\tSi cela se produit, il est averti, et il doit choisir une nouvelle position.");
        System.out.println();
        System.out.println("\t2. Stratégie de placement :");
        System.out.println();
        System.out.println("\t\tLes navires doivent être placés de manière stratégique pour éviter de révéler des schémas évidents à l’adversaire.");
        System.out.println();
        System.out.println("\t3. Visualisation");
        System.out.println();
        System.out.println("\t\tAprès chaque tour, la grille ennemie mise à jour est affichée avec les résultats des attaques (X pour touché, O pour à l'eau).");
        System.out.println();
        int choix=0;
        System.out.println("1. Lancez une partie");
        System.out.println("2. Quittez");
        System.out.println();
        System.out.print("=>  ");
        choix = scanner.nextInt();

        switch (choix){
            case 1:preparationPartie();break;
        }
    }
    public static void preparationPartie(){
        System.out.println();
        afficherPlateau(GestionBatailleNaval.plateaujoueur1);
        placementNavireJoueur("Joueur 1", GestionBatailleNaval.plateaujoueur1);
        espaceEntrePlacementJoueur();
        System.out.println("AU TOUR DU JOUEUR 2 !!");
        System.out.println();
        afficherPlateau(GestionBatailleNaval.plateaujoueur2);
        placementNavireJoueur("Joueur 2", GestionBatailleNaval.plateaujoueur2);
    }
    public static void placementNavireJoueur(String joueur, char [][]plateau){
        int[] tailleNavire = {5, 4, 3, 3, 2}; // Longueurs des bateaux
        String typeNavire="un navire";
        for (int i=0; i< tailleNavire.length;i++) {
            boolean estplacer = false;
            if (tailleNavire[i] == 5){
                typeNavire="le Porte-avion";
            }
            else if (tailleNavire[i] == 4){
                typeNavire="le Croiseur";
            }
            else if (tailleNavire[i] == 3){
                typeNavire="un des Contre-torpilleur";
            }
            else if (tailleNavire[i] == 2){
                typeNavire="le torpilleur";
            }
            while (!estplacer) {
                String position;
                do {
                   System.out.println(joueur + ", placez " + typeNavire + " de taille " + tailleNavire[i] + " (format : A1) :");
                   position = scanner.next().toUpperCase();
                } while (position.length() != 2);

                char direction;
                do {
                    System.out.println("sens ? (h/v)");
                    direction = scanner.next().toLowerCase().charAt(0);
                }while (direction != 'v' && direction != 'h');

                int li = Integer.parseInt(position.substring(1)) - 1; // converti "A1" en indice : position.substring(1) permet d'avoir "1" puis Integer.parseInt() convertit
                int c = position.charAt(0) - 'A';

                estplacer = placementNavire(plateau, tailleNavire[i], c, li, direction);
                if (!estplacer) {
                    System.out.println("Réessayez.");
                }
            }
            afficherPlateau(plateau);
        }
    }
    public static boolean placementNavire(char[][] plateau, int taille, int c, int li, char direction){

        if ( ((plateau.length < li + taille) && (direction=='v')) || ((plateau[0].length < c + taille) && (direction == 'h'))  ){
            System.out.println("Placement invalide!");
            return false;
        }
        else {
            for (int i = 0; i < taille; i++) {
                if (direction == 'h' && plateau[li][c + i] == 'B') {
                    System.out.println("Collision détectée!");
                    return false;
                }
                if (direction == 'v' && plateau[li + i][c] == 'B') {
                    System.out.println("Collision détectée!");
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
            else if (taille==3) {
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
    //rajouté par marvin
    public static boolean tousCoulés(char[][] plateau){
        if(CoulésPorteAvion(plateau) && CoulésContreTorpilleur(plateau) && CoulésTorpilleur(plateau) && CoulésCroiseur(plateau)){
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean unContreTorpilleurCoulés(char[][]plateau){
        int compteur = 0;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j]=='c'){
                    compteur++;
                }
            }
        }
        if (compteur>=3){
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean CoulésContreTorpilleur(char[][] plateau){
        boolean valide = true;

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j]=='c'){
                    valide = false;
                }
            }
        }
        if (valide){
            System.out.println("Contre Torpilleur coulée");
        }
        return valide;
    }
    public static boolean CoulésPorteAvion(char[][] plateau){
        boolean valide = true;

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j]=='P'){
                    valide = false;
                }
            }
        }
        if (valide){
            System.out.println("Porte-Avion coulée");
        }

        return valide;
    }

    public static boolean CoulésCroiseur(char[][] plateau){
        boolean valide = true;

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j]=='C'){
                    valide = false;
                }
            }
        }
        if (valide){
            System.out.println("Croiseur coulée");
        }
        return valide;
    }
    public static boolean CoulésTorpilleur(char[][] plateau){
        boolean valide = true;

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j]=='T'){
                    valide = false;
                }
            }
        }
        if (valide){
            System.out.println("Torpilleur coulée");
        }
        return valide;
    }
    

    public static void espaceEntrePlacementJoueur(){
        for (int i = 0; i < 200; i++) {
            System.out.println();
        }
    }

    public static void gestionTir(char[][] plateau, char[][] plateauCachee){
        System.out.println("Entrez tir (format : A1): ");
        String position = scanner.next().toUpperCase();

        int li = Integer.parseInt(position.substring(1)) - 1; // converti "A1" en indice : position.substring(1) permet d'avoir "1" puis Integer.parseInt() convertit
        int c = position.charAt(0) - 'A';

        if (plateau[li][c] == 'P' || plateau[li][c] == 'C' || plateau[li][c] == 'c' || plateau[li][c] == 'T'){
            plateauCachee[li][c] = 'X';
            plateau[li][c] = 'X';
        }
        else {
            plateauCachee[li][c] = 'O';
            plateau[li][c] = 'O';
        }

    }

    public static void gestionTour(char[][] plateauJoueur1,char[][] plateauJoueur2,char[][] plateauJoueur1cachee,char[][] plateauJoueur2cachee){
        int i = 1;

        while(!tousCoulés(plateauJoueur1cachee) && !tousCoulés(plateauJoueur2cachee)){
            System.out.println("Tour " + i + ": ");
            if (i % 2 != 0){
                System.out.println("Joueur 1 --");
                afficherPlateau(plateauJoueur2cachee);
                gestionTir(plateauJoueur2, plateauJoueur2cachee);
                afficherPlateau(plateauJoueur2cachee);
                verifierCoules(plateauJoueur2);
            }
            else {
                System.out.println("Joueur 2 --");
                afficherPlateau(plateauJoueur1cachee);
                gestionTir(plateauJoueur1,plateauJoueur1cachee);
                afficherPlateau(plateauJoueur1cachee);
                verifierCoules(plateauJoueur1);
            }

            i++;
        }
    }

    public static void verifierCoules(char[][] plateau){
        CoulésPorteAvion(plateau);
        CoulésCroiseur(plateau);
        CoulésContreTorpilleur(plateau);
        CoulésTorpilleur(plateau);
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
