package com.nonogram.solver;

import com.nonogram.models.Cell;
import com.nonogram.models.ClueList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class EdgeFillStrategyTest {

    private static final int ROW_LENGTH = 7;
    private static final ClueList TWO_CLUES_IN_LIST = new ClueList(List.of(3, 3));

    private final EdgeFillStrategy sut = new EdgeFillStrategy();

    @Test
    public void applyWithEmptyRowExpectNoUpdate() {
        List<Cell> expected = Collections.nCopies(ROW_LENGTH, Cell.EMPTY);
        List<Cell> actual = sut.apply(TWO_CLUES_IN_LIST, expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyWithFilledCellsInMiddleOfRowExpectNoUpdate() {
        List<Cell> expected = List.of(Cell.EMPTY, Cell.FILLED, Cell.FILLED, Cell.EMPTY, Cell.FILLED, Cell.FILLED,
                Cell.EMPTY);
        List<Cell> actual = sut.apply(TWO_CLUES_IN_LIST, expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyWithSingleClueAtStartOfRowExpectClueFilled() {
        List<Cell> existingRow = List.of(Cell.FILLED, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY,
                Cell.EMPTY);
        List<Cell> expected = List.of(Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY,
                Cell.EMPTY);
        List<Cell> actual = sut.apply(TWO_CLUES_IN_LIST, existingRow);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyWithSingleClueAtStartAndMultipleEntriesFilledExpectClueFilled() {
        List<Cell> existingRow = List.of(Cell.FILLED, Cell.FILLED, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY,
                Cell.EMPTY);
        List<Cell> expected = List.of(Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY,
                Cell.EMPTY);
        List<Cell> actual = sut.apply(TWO_CLUES_IN_LIST, existingRow);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyWithSingleClueAtEndOfRowExpectClueFilled() {
        List<Cell> existingRow = List.of(Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY,
                Cell.FILLED);
        List<Cell> expected = List.of(Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.FILLED, Cell.FILLED,
                Cell.FILLED);
        List<Cell> actual = sut.apply(TWO_CLUES_IN_LIST, existingRow);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyWithSingleClueAtEndAndMultipleEntriesFilledExpectClueFilled() {
        List<Cell> existingRow = List.of(Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.FILLED,
                Cell.FILLED);
        List<Cell> expected = List.of(Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.FILLED, Cell.FILLED,
                Cell.FILLED);
        List<Cell> actual = sut.apply(TWO_CLUES_IN_LIST, existingRow);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyWithClueAtStartAndEndOfRowExpectBothCluesFilled() {
        List<Cell> existingRow = List.of(Cell.FILLED, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY,
                Cell.FILLED);
        List<Cell> expected = List.of(Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.EMPTY, Cell.FILLED, Cell.FILLED,
                Cell.FILLED);
        List<Cell> actual = sut.apply(TWO_CLUES_IN_LIST, existingRow);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyWithClueThatFillsEntireRowExpectRowFilled() {
        List<Cell> existingRow = List.of(Cell.FILLED, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY,
                Cell.EMPTY);
        List<Cell> expected = Collections.nCopies(ROW_LENGTH, Cell.FILLED);
        List<Cell> actual = sut.apply(TWO_CLUES_IN_LIST, existingRow);
        Assertions.assertEquals(expected, actual);
    }

}