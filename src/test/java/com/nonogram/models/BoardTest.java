package com.nonogram.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.nonogram.models.Cell.CLEAR;
import static com.nonogram.models.Cell.EMPTY;
import static com.nonogram.models.Cell.FILLED;


public class BoardTest {

    private final List<Cell> zerothRow = Arrays.asList(FILLED, FILLED, FILLED);
    private final List<Cell> firstRow = Arrays.asList(CLEAR, EMPTY, FILLED);
    private final List<Cell> secondRow = Arrays.asList(FILLED, CLEAR, EMPTY);

    private final Board sut = new Board(Arrays.asList(zerothRow, firstRow, secondRow));

    @Test
    public void getRowForIndexOfFirstRowExpectSuccess() {
        Assertions.assertEquals(zerothRow, sut.getRowForIndex(0));
    }

    @Test
    public void getRowForIndexOfFinalRowExpectSuccess() {
        Assertions.assertEquals(secondRow, sut.getRowForIndex(2));
    }

    @Test
    public void getColumnForIndexOfFirstColumnExpectSuccess() {
        List<Cell> expected = List.of(FILLED, CLEAR, FILLED);
        Assertions.assertEquals(expected, sut.getColumnForIndex(0));
    }

    @Test
    public void getColumnForIndexOfFinalColumnExpectSuccess() {
        List<Cell> expected = List.of(FILLED, FILLED, EMPTY);
        Assertions.assertEquals(expected, sut.getColumnForIndex(2));
    }

    @Test
    public void updateFirstEntryExpectSuccess() {
        Assertions.assertEquals(FILLED, sut.getCell(0, 0));
        sut.updateCell(0, 0, EMPTY);
        Assertions.assertEquals(EMPTY, sut.getCell(0, 0));
    }

    @Test
    public void updateFinalEntryExpectSuccess() {
        Assertions.assertEquals(EMPTY, sut.getCell(2, 2));
        sut.updateCell(2, 2, FILLED);
        Assertions.assertEquals(FILLED, sut.getCell(2, 2));
    }

    @Test
    public void updateArbitraryEntryExpectSuccess() {
        Assertions.assertEquals(FILLED, sut.getCell(1, 2));
        sut.updateCell(1, 2, CLEAR);
        Assertions.assertEquals(CLEAR, sut.getCell(1, 2));
    }

}