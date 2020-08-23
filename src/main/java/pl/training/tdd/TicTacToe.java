package pl.training.tdd;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.disjoint;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;
import static pl.training.tdd.Player.CROSS;

public class TicTacToe {

    private static final int BOARD_SIZE = 9;

    private static final Set<Set<Integer>> winningSequences = Set.of(
            Set.of(1, 2, 3), Set.of(4, 5, 6), Set.of(7, 8, 9),
            Set.of(1, 4, 7), Set.of(2, 5, 8), Set.of(3, 6, 9),
            Set.of(1, 5, 9), Set.of(3, 5, 7));

    private final Set<Integer> crossFields;
    private final Set<Integer> circleFields;

    private Player player = CROSS;

    public TicTacToe() {
        this(Set.of(), Set.of());
    }

    public TicTacToe(Set<Integer> crossFields, Set<Integer> circleFields) {
        this.crossFields = new HashSet<>(crossFields);
        this.circleFields = new HashSet<>(circleFields);
        validateInitialGameState();
    }

    private void validateInitialGameState() {
        if (!disjoint(crossFields, circleFields) || takenFields().size() >= BOARD_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isGameOver() {
        return allFieldsAreTaken() || playerTookWinningSequence();
    }

    private boolean allFieldsAreTaken() {
        return BOARD_SIZE - takenFields().size() == 0;
    }

    private Set<Integer> takenFields() {
        return concat(crossFields.stream(), circleFields.stream()).collect(toSet());
    }

    private boolean playerTookWinningSequence() {
        return winningSequences.stream().anyMatch(sequence -> playerFields().containsAll(sequence));
    }

    private Set<Integer> playerFields() {
        return player == CROSS ? crossFields : circleFields;
    }

    public boolean makeMove(int field) {
        if (isTaken(field)) {
            return false;
        }
        playerFields().add(field);
        player = player.reverse();
        return true;
    }

    private boolean isTaken(int field) {
        return takenFields().contains(field);
    }

    public Player getPlayer() {
        return player;
    }

    public Set<Integer> getCrossFields() {
        return Collections.unmodifiableSet(crossFields);
    }

    public Set<Integer> getCircleFields() {
        return Collections.unmodifiableSet(circleFields);
    }

}