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

        System.out.print("==>");
        int choix=0;
        choix = scanner.nextInt();
        switch (choix){
            case 1: Methodes.avantPartie();break;
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
        System.out.print("=>");
        choix = scanner.nextInt();

        switch (choix){
            case 1:avantPartie();break;
        }
    }
    public static void avantPartie(){
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
                System.out.println(joueur + ", placez " + typeNavire + " de taille " + tailleNavire[i] + " (format : A1) :");
                String position = scanner.next().toUpperCase();
                System.out.println("sens ? (h/v)");
                char direction = scanner.next().toLowerCase().charAt(0);

                int y = Integer.parseInt(position.substring(1)) - 1; // converti "A1" en indice : position.substring(1) permet d'avoir "1" puis Integer.parseInt() convertit
                int x = position.charAt(0) - 'A';

                estplacer = placementNavire(plateau, tailleNavire[i], x, y, direction);
                if (!estplacer) {
                    System.out.println("Réessayez.");
                }
            }
            afficherPlateau(plateau);
        }
    }

    public static boolean placementNavire(char[][] plateau, int taille, int x, int y, char direction){
        for (int i = 0; i < taille; i++) {
            if (direction == 'h' && plateau[y][x + i] == 'B') {
                System.out.println("Collision détectée!");
                return false;
            }
            if (direction == 'v' && plateau[y + i][x] == 'B') {
                System.out.println("Collision détectée!");
                return false;
            }
        }
        if ( ((plateau.length < y + taille) && (direction=='v')) || ((plateau[0].length < x + taille) && (direction == 'h'))  ){
            System.out.println("Placement invalide!");
            return false;
        }
        else {
            for (int ligne = 0; ligne < plateau.length; ligne++) {
                for (int col = 0; col < plateau[ligne].length; col++) {
                    if (ligne == y && col == x) {
                        if (direction == 'h') {
                            for (int i = 0; i < taille; i++) {
                                plateau[ligne][col + i] = 'B';
                            }
                        }
                        else{
                            for (int i = 0; i < taille; i++) {
                                plateau[ligne+i][col] = 'B';
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    //rajouté par marvin
    public static boolean tousCoulés(char[][] plateau){
        for (int i = 0;i< plateau.length; i++) {
            for (int j = 0; i<plateau[i].length; j++) {
                if (plateau[i][j] == 'B') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void espaceEntrePlacementJoueur(){
        for (int i = 0; i < 200; i++) {
            System.out.println();
        }
    }

}
