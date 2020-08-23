package pl.training.tdd;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class TicTacToeTest {

    private TicTacToe ticTacToe = new TicTacToe();

    @Test
    public void shouldEndGameWhenAllFieldsAreTaken() {
        ticTacToe = new TicTacToe(Set.of(1, 3, 5, 8), Set.of(2, 4, 6, 7));
        ticTacToe.makeMove(9);
        assertTrue(ticTacToe.isGameOver());
    }

    @Test
    public void shouldEndGameWhenPlayerTookWinningSequence() {
        ticTacToe = new TicTacToe(Set.of(1, 2, 3), Set.of(4, 8, 9));
        assertTrue(ticTacToe.isGameOver());
    }

    @Test
    public void shouldAllowOnlyFreeFieldsToBeTaken() {
        ticTacToe.makeMove(1);
        assertFalse(ticTacToe.makeMove(1));
    }

    @Test
    public void shouldChangePlayerAfterFieldIsTaken() {
        Player player = ticTacToe.getPlayer();
        ticTacToe.makeMove(1);
        assertNotEquals(ticTacToe.getPlayer(), player);
    }

    @Test
    public void shouldNotChangePlayerAfterFieldIsNotTaken() {
        ticTacToe.makeMove(1);
        Player player = ticTacToe.getPlayer();
        ticTacToe.makeMove(1);
        assertEquals(ticTacToe.getPlayer(), player);
    }

    @Test
    public void shouldThrowExceptionWhenInitialGameStateIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new TicTacToe(Set.of(1, 3, 5, 8), Set.of(1, 4, 6, 7, 9)));
    }

}