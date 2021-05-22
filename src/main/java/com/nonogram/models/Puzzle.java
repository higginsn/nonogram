package com.nonogram.models;

import com.nonogram.validators.PuzzleValidator;
import lombok.Value;
import org.junit.jupiter.api.Assertions;

import java.util.List;

@Value
public class Puzzle {

    private static final PuzzleValidator VALIDATOR = new PuzzleValidator();

    List<Clue> colClues;
    List<Clue> rowClues;
    int gridSize;

    public Puzzle(List<Clue> colClues, List<Clue> rowClues, int gridSize) {
        this.colClues = colClues;
        this.rowClues = rowClues;
        this.gridSize = gridSize;
        Assertions.assertTrue(VALIDATOR.test(this));
    }

    //@VisibleForTesting
    public Puzzle(List<Clue> colClues, List<Clue> rowClues, int gridSize, boolean assertion) {
        this.colClues = colClues;
        this.rowClues = rowClues;
        this.gridSize = gridSize;
    }

}
