public class GestionBatailleNavale {
    public static void gestionBataille(){
        char[][] plateauJoueur1 = new char[11][10];
        char[][] plateauJoueur2 = new char[11][10];
        char[][] plateauJoueur1Cachee = new char[11][10];
        char[][] plateauJoueur2Cachee = new char[11][10];
        Methodes.generationPlateau(plateauJoueur1);
        Methodes.generationPlateau(plateauJoueur2);
        Methodes.generationPlateau(plateauJoueur1Cachee);
        Methodes.generationPlateau(plateauJoueur2Cachee);

        Methodes.placementNavire(plateauJoueur1, 5, 9, 2 , 'h');
        Methodes.afficherPlateau(plateauJoueur1);

        //Methodes.afficherPlateau(plateauJoueur1);
        Methodes.textBatailleNavale();
        Methodes.menuPrincipal();
    }

}