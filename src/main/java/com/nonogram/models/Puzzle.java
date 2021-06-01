package com.nonogram.models;

import com.nonogram.validators.PuzzleValidator;
import lombok.Value;
import org.junit.jupiter.api.Assertions;

import java.util.List;

@Value
public class Puzzle {

    private static final PuzzleValidator VALIDATOR = new PuzzleValidator();

    List<ClueList> colClueLists;
    List<ClueList> rowClueLists;
    int gridSize;

    public Puzzle(List<ClueList> colClueLists, List<ClueList> rowClueLists, int gridSize) {
        this.colClueLists = colClueLists;
        this.rowClueLists = rowClueLists;
        this.gridSize = gridSize;
        Assertions.assertTrue(VALIDATOR.test(this));
    }

    //@VisibleForTesting
    public Puzzle(List<ClueList> colClueLists, List<ClueList> rowClueLists, int gridSize, boolean assertion) {
        this.colClueLists = colClueLists;
        this.rowClueLists = rowClueLists;
        this.gridSize = gridSize;
    }

}
