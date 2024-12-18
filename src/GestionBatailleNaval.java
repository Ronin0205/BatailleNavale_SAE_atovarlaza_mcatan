public class GestionBatailleNaval {

    public static char[][] plateaujoueur1 = new char[10][10];
    public static char[][] plateaujoueur2 = new char[10][10];
    public static char[][] plateaujoueur1cachee =new char[10][10];
    public static char[][] plateaujouer2cachee =new char[10][10];

    public static void gestionBataille(){

        Methodes.generationPlateau(plateaujoueur1);
        Methodes.generationPlateau(plateaujoueur2);
        Methodes.generationPlateau(plateaujoueur1cachee);
        Methodes.generationPlateau(plateaujouer2cachee);

        Methodes.menu();
        Methodes.gestionTour(plateaujoueur1,plateaujoueur2,plateaujoueur1cachee,plateaujouer2cachee);

    }
}