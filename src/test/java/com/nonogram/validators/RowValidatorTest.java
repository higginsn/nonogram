package com.nonogram.validators;

import com.nonogram.models.Cell;
import com.nonogram.models.Clue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class RowValidatorTest {

    private static final int GRID_SIZE = 10;
    private static final Clue EMPTY_CLUE = new Clue(List.of(0));
    private static final Clue FILLED_CLUE = new Clue(List.of(GRID_SIZE));

    private final RowValidator sut = new RowValidator();

    @Test
    public void applyEmptyRowWithEmptyClueExpectSuccess() {
        List<Cell> emptyRow = Collections.nCopies(GRID_SIZE, Cell.EMPTY);
        Assertions.assertTrue(sut.apply(EMPTY_CLUE, emptyRow));
    }

    @Test
    public void applyClearRowWithEmptyClueExpectSuccess() {
        List<Cell> clearRow = Collections.nCopies(GRID_SIZE, Cell.CLEAR);
        Assertions.assertTrue(sut.apply(EMPTY_CLUE, clearRow));
    }

    @Test
    public void applyFilledRowWithFilledClueExpectSuccess() {
        List<Cell> filledRow = Collections.nCopies(GRID_SIZE, Cell.FILLED);
        Assertions.assertTrue(sut.apply(FILLED_CLUE, filledRow));
    }

    @Test
    public void applyOneBlockAsPrefixOfRowExpectSuccess() {
        Clue clue = new Clue(List.of(3));
        List<Cell> row = List.of(Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.CLEAR, Cell.CLEAR);
        Assertions.assertTrue(sut.apply(clue, row));
    }

    @Test
    public void applyOneBlockAsSubsetOfRowExpectSuccess() {
        Clue clue = new Clue(List.of(3));
        List<Cell> row = List.of(Cell.CLEAR, Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.CLEAR);
        Assertions.assertTrue(sut.apply(clue, row));
    }

    @Test
    public void applyOneBlockAsSuffixOfRowExpectSuccess() {
        Clue clue = new Clue(List.of(3));
        List<Cell> row = List.of(Cell.CLEAR, Cell.CLEAR, Cell.FILLED, Cell.FILLED, Cell.FILLED);
        Assertions.assertTrue(sut.apply(clue, row));
    }

    @Test
    public void applyManyBlocksAsSubsetsOfRowExpectSuccess() {
        Clue clue = new Clue(List.of(2, 2, 2));
        List<Cell> row = List.of(Cell.FILLED, Cell.FILLED, Cell.CLEAR, Cell.FILLED, Cell.FILLED,
                Cell.CLEAR, Cell.CLEAR, Cell.FILLED, Cell.FILLED, Cell.CLEAR);
        Assertions.assertTrue(sut.apply(clue, row));
    }

    @Test
    public void applyEmptyRowWithNonEmptyClueExpectFailure() {
        List<Cell> emptyRow = List.of();
        Assertions.assertFalse(sut.apply(FILLED_CLUE, emptyRow));
    }

    @Test
    public void applySingleBlockWithManyCluesExpectFailure() {
        Clue clue = new Clue(List.of(2, 2));
        List<Cell> row = List.of(Cell.FILLED, Cell.CLEAR, Cell.CLEAR);
        Assertions.assertFalse(sut.apply(clue, row));
    }

    @Test
    public void applyMultipleBlocksWithSingleClueExpectFailure() {
        List<Cell> row = List.of(Cell.FILLED, Cell.CLEAR, Cell.FILLED, Cell.CLEAR, Cell.FILLED);
        Assertions.assertFalse(sut.apply(EMPTY_CLUE, row));
    }

}