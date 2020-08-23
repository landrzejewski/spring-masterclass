package pl.training.tdd;

public enum Player {

    CIRCLE, CROSS;

    public Player reverse() {
        return this == CROSS ? CIRCLE : CROSS;
    }

}