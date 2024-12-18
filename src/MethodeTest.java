import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MethodeTest {

    @Test
    public void testTousCoules() {

        char[][] plateau1 = {
                {'~', '~', '~'},
                {'~', '~', '~'},
                {'~', '~', '~'}
        };
        assertTrue(Methodes.tousCoulés(plateau1), "Tous les navires sont coulés, devrait retourner true.");

        char[][] plateau2 = {
                {'P', 'C', 'T'},
                {'C', 'T', 'C'},
                {'T', 'P', 'C'}
        };
        assertFalse(Methodes.tousCoulés(plateau2), "Aucun navire n'est coulé, devrait retourner false.");

        char[][] plateau3 = {
                {'~', '~', '~'},
                {'~', 'C', '~'},
                {'~', '~', '~'}
        };
        assertFalse(Methodes.tousCoulés(plateau3), "Un navire n'est pas coulé, devrait retourner false.");

        char[][] plateau4 = {
                {'P', '~', '~'},
                {'~', 'C', '~'},
                {'~', '~', 'T'}
        };
        assertFalse(Methodes.tousCoulés(plateau4), "Des navires sont encore sur le plateau, devrait retourner false.");
    }

    @Test
    public void testPlacementNavire() {
        char[][] plateau1 = new char[10][10];
        Methodes.generationPlateau(plateau1);

        assertTrue(Methodes.placementNavire(plateau1, 3, 1, 2, 'h'), "Placement horizontal valide, devrait retourner true.");
        assertEquals('c', plateau1[2][1], "Le navire doit être correctement placé à la position [2][1].");
        assertEquals('c', plateau1[2][2], "Le navire doit être correctement placé à la position [2][2].");
        assertEquals('c', plateau1[2][3], "Le navire doit être correctement placé à la position [2][3].");


        char[][] plateau2 = new char[10][10];
        Methodes.generationPlateau(plateau2);

        assertTrue(Methodes.placementNavire(plateau2, 4, 2, 0, 'v'), "Placement vertical valide, devrait retourner true.");
        assertEquals('C', plateau2[0][2], "Le navire doit être correctement placé à la position [0][2].");
        assertEquals('C', plateau2[1][2], "Le navire doit être correctement placé à la position [1][2].");
        assertEquals('C', plateau2[2][2], "Le navire doit être correctement placé à la position [2][2].");
        assertEquals('C', plateau2[3][2], "Le navire doit être correctement placé à la position [3][2].");



        char[][] plateau3 = new char[10][10];
        Methodes.generationPlateau(plateau3);
        Methodes.placementNavire(plateau3, 4, 1, 2, 'h');

        assertFalse(Methodes.placementNavire(plateau3, 3, 1, 2, 'h'), "Collision détectée, devrait retourner false.");



        char[][] plateau4 = new char[10][10];
        Methodes.generationPlateau(plateau4);

        assertFalse(Methodes.placementNavire(plateau4, 5, 10, 2, 'h'), "Placement hors limites horizontal, devrait retourner false.");



        char[][] plateau5 = new char[10][10];
        Methodes.generationPlateau(plateau5);

        assertFalse(Methodes.placementNavire(plateau5, 5, 2, 10, 'v'), "Placement hors limites vertical, devrait retourner false.");
    }

}
