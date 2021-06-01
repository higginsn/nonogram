package com.nonogram.validators;

import com.nonogram.models.Cell;
import com.nonogram.models.ClueList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class RowValidatorTest {

    private static final int GRID_SIZE = 10;
    private static final ClueList EMPTY_CLUE_LIST = new ClueList(List.of(0));
    private static final ClueList FILLED_CLUE_LIST = new ClueList(List.of(GRID_SIZE));

    private final RowValidator sut = new RowValidator();

    @Test
    public void applyEmptyRowWithEmptyClueExpectSuccess() {
        List<Cell> emptyRow = Collections.nCopies(GRID_SIZE, Cell.EMPTY);
        Assertions.assertTrue(sut.apply(EMPTY_CLUE_LIST, emptyRow));
    }

    @Test
    public void applyClearRowWithEmptyClueExpectSuccess() {
        List<Cell> clearRow = Collections.nCopies(GRID_SIZE, Cell.CLEAR);
        Assertions.assertTrue(sut.apply(EMPTY_CLUE_LIST, clearRow));
    }

    @Test
    public void applyFilledRowWithFilledClueExpectSuccess() {
        List<Cell> filledRow = Collections.nCopies(GRID_SIZE, Cell.FILLED);
        Assertions.assertTrue(sut.apply(FILLED_CLUE_LIST, filledRow));
    }

    @Test
    public void applyOneBlockAsPrefixOfRowExpectSuccess() {
        ClueList clueList = new ClueList(List.of(3));
        List<Cell> row = List.of(Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.CLEAR, Cell.CLEAR);
        Assertions.assertTrue(sut.apply(clueList, row));
    }

    @Test
    public void applyOneBlockAsSubsetOfRowExpectSuccess() {
        ClueList clueList = new ClueList(List.of(3));
        List<Cell> row = List.of(Cell.CLEAR, Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.CLEAR);
        Assertions.assertTrue(sut.apply(clueList, row));
    }

    @Test
    public void applyOneBlockAsSuffixOfRowExpectSuccess() {
        ClueList clueList = new ClueList(List.of(3));
        List<Cell> row = List.of(Cell.CLEAR, Cell.CLEAR, Cell.FILLED, Cell.FILLED, Cell.FILLED);
        Assertions.assertTrue(sut.apply(clueList, row));
    }

    @Test
    public void applyManyBlocksAsSubsetsOfRowExpectSuccess() {
        ClueList clueList = new ClueList(List.of(2, 2, 2));
        List<Cell> row = List.of(Cell.FILLED, Cell.FILLED, Cell.CLEAR, Cell.FILLED, Cell.FILLED,
                Cell.CLEAR, Cell.CLEAR, Cell.FILLED, Cell.FILLED, Cell.CLEAR);
        Assertions.assertTrue(sut.apply(clueList, row));
    }

    @Test
    public void applyEmptyRowWithNonEmptyClueExpectFailure() {
        List<Cell> emptyRow = List.of();
        Assertions.assertFalse(sut.apply(FILLED_CLUE_LIST, emptyRow));
    }

    @Test
    public void applySingleBlockWithManyCluesExpectFailure() {
        ClueList clueList = new ClueList(List.of(2, 2));
        List<Cell> row = List.of(Cell.FILLED, Cell.CLEAR, Cell.CLEAR);
        Assertions.assertFalse(sut.apply(clueList, row));
    }

    @Test
    public void applyMultipleBlocksWithSingleClueExpectFailure() {
        List<Cell> row = List.of(Cell.FILLED, Cell.CLEAR, Cell.FILLED, Cell.CLEAR, Cell.FILLED);
        Assertions.assertFalse(sut.apply(EMPTY_CLUE_LIST, row));
    }

}