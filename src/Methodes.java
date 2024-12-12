import java.util.*;

public class Methodes {

    public static Scanner scanner = new Scanner(System.in);

    public static void generationPlateau(char[][] plateau){
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int col = 0; col < plateau[ligne].length; col++) {
                plateau[ligne][col] = '~';
            }
        }
        for (int col = 0; col < plateau[0].length;col++){
            plateau[0][col] = Character.valueOf((char)('A' + col));
        }
    }

    public static void afficherPlateau(char[][] plateau){
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int col = 0; col < plateau[ligne].length; col++) {
                if (ligne != 0 && col == 0){
                    System.out.printf("%2d\t",ligne);
                }
                if (ligne == 0 && col == 0){
                    System.out.print("\t");
                }
                System.out.print(plateau[ligne][col] + "\t");
            }
            System.out.println();
        }
    }

    public static void textBatailleNavale(){
        System.out.println("______       _        _ _ _        _   _                  _      \n" +
                "| ___ \\     | |      (_) | |      | \\ | |                | |     \n" +
                "| |_/ / __ _| |_ __ _ _| | | ___  |  \\| | __ ___   ____ _| | ___ \n" +
                "| ___ \\/ _` | __/ _` | | | |/ _ \\ | . ` |/ _` \\ \\ / / _` | |/ _ \\\n" +
                "| |_/ / (_| | || (_| | | | |  __/ | |\\  | (_| |\\ V / (_| | |  __/\n" +
                "\\____/ \\__,_|\\__\\__,_|_|_|_|\\___| \\_| \\_/\\__,_| \\_/ \\__,_|_|\\___|\n" +
                "                                                                 \n" +
                "                                                                 \n" +
                "\n");
    }

    public static void menuPrincipalText(){
        System.out.println("1. Démarer une partie\n" +
                           "2. Redécouvrir les règles\n" +
                           "3. Quitter\n");

        System.out.print("==>");
    }

    public static void menuPrincipal(){
        int choix = 0;
        do {
            menuPrincipalText();
            choix = scanner.nextInt();

            switch (choix){
                case 1:
                    avantPartie();
                    break;
                case 2:
                    break;
            }

        }while (choix != 3);

    }

    public static void avantPartie(){
        int choixBat;
        System.out.println("Saisissez le bateau à placer:\n ");
        System.out.println("1. Porte-avions (5 cases)\n" +
                           "2. Croiseur (4 cases)\n" +
                           "3. Contre-torpilleurs (3 cases)\n" +
                           "4. Torpilleur (2 cases)");

        choixBat = scanner.nextInt();
        while(choixBat != 1 && choixBat != 2 && choixBat != 3 && choixBat != 4){
            System.out.println("Saisie invalide! \n" +
                               "Saisir à nouveau!");
            choixBat = scanner.nextInt();
        }
        if (choixBat == 1){
            System.out.println("Choisir la position de placement de votre Porte-avions (verticale: v ou horizontal: h): ");
        }
    }


    if ( ((plateau.length < y + taille) && (direction=='v')) || ((plateau[0].length < x + taille) && (direction == 'h')) ){
            System.out.println("Placement invalide!");
        }

}
