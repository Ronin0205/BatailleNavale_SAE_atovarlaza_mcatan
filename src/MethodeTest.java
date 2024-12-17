import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MethodeTest {
    @Test
    public final void placementNavire() {
        // Plateau vide de 10x10
        char[][] plateau = new char[10][10];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = '~';
            }
        }

        //bon placement horizontal
        assertTrue(Methodes.placementNavire(plateau, 3, 2, 2, 'h'));
        assertEquals('B', plateau[2][2]);
        assertEquals('B', plateau[2][3]);
        assertEquals('B', plateau[2][4]);

        //bon Placement vertical
        assertTrue(Methodes.placementNavire(plateau, 4, 5, 5, 'v'));
        assertEquals('B', plateau[5][5]);
        assertEquals('B', plateau[6][5]);
        assertEquals('B', plateau[7][5]);
        assertEquals('B', plateau[8][5]);

        //collision
        assertFalse(Methodes.placementNavire(plateau, 3, 2, 2, 'h')); // Navire déjà placé ici


        // pas encore prêt
        //
//        //Placement hors limites horizontal
//        assertFalse(Methodes.placementNavire(plateau, 5, 8, 0, 'h'));
//
//        //Placement hors limites vertical
//        assertFalse(Methodes.placementNavire(plateau, 6, 0, 8, 'v'));


    }
}
