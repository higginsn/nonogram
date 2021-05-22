package com.nonogram.models;

public enum Cell {
    EMPTY('_'),
    CLEAR('X'),
    FILLED('O');

    private final char character;

    Cell(char character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
