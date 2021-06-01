package com.nonogram.solver;

import com.nonogram.models.Cell;
import com.nonogram.models.ClueList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class BasicFillStrategyTest {

    private static final List<Cell> EMPTY_ROW_OF_SIX = Collections.nCopies(6, Cell.EMPTY);
    private static final List<Cell> EMPTY_ROW_OF_SEVEN = Collections.nCopies(7, Cell.EMPTY);

    private final BasicFillStrategy sut = new BasicFillStrategy();

    @Test
    public void applyClueListGreaterThanOneExpectNoneFilled() {
        ClueList clueList = new ClueList(List.of(1, 1));
        List<Cell> actual = sut.apply(clueList, EMPTY_ROW_OF_SIX);
        Assertions.assertEquals(EMPTY_ROW_OF_SIX, actual);
    }

    @Test
    public void applyClueLessThanHalfTheRowLengthExpectNoneFilled() {
        ClueList clueList = new ClueList(Collections.singletonList(1));
        List<Cell> actual = sut.apply(clueList, EMPTY_ROW_OF_SIX);
        Assertions.assertEquals(EMPTY_ROW_OF_SIX, actual);
    }

    @Test
    public void applyClueOneGreaterThanHalfTheEvenRowLengthExpectTwoFilled() {
        ClueList clueList = new ClueList(List.of(EMPTY_ROW_OF_SIX.size() / 2 + 1));
        List<Cell> expected = List.of(Cell.EMPTY, Cell.EMPTY, Cell.FILLED, Cell.FILLED, Cell.EMPTY, Cell.EMPTY);
        List<Cell> actual = sut.apply(clueList, EMPTY_ROW_OF_SIX);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyClueTwoGreaterThanHalfTheEvenRowLengthExpectFourFilled() {
        ClueList clueList = new ClueList(List.of(EMPTY_ROW_OF_SIX.size() / 2 + 2));
        List<Cell> expected = List.of(Cell.EMPTY, Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.EMPTY);
        List<Cell> actual = sut.apply(clueList, EMPTY_ROW_OF_SIX);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyClueEqualToEvenRowLengthExpectEntireRowFilled() {
        ClueList clueList = new ClueList(List.of(EMPTY_ROW_OF_SIX.size()));
        List<Cell> expected = Collections.nCopies(EMPTY_ROW_OF_SIX.size(), Cell.FILLED);
        List<Cell> actual = sut.apply(clueList, EMPTY_ROW_OF_SIX);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyClueOneGreaterThanHalfTheOddRowLengthExpectOneFilled() {
        ClueList clueList = new ClueList(List.of(EMPTY_ROW_OF_SEVEN.size() / 2 + 1));
        List<Cell> expected = List.of(Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.FILLED, Cell.EMPTY, Cell.EMPTY,
                Cell.EMPTY);
        List<Cell> actual = sut.apply(clueList, EMPTY_ROW_OF_SEVEN);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyClueTwoGreaterThanHalfTheOddRowLengthExpectThreeFilled() {
        ClueList clueList = new ClueList(List.of(EMPTY_ROW_OF_SEVEN.size() / 2 + 2));
        List<Cell> expected = List.of(Cell.EMPTY, Cell.EMPTY, Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.EMPTY,
                Cell.EMPTY);
        List<Cell> actual = sut.apply(clueList, EMPTY_ROW_OF_SEVEN);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void applyClueEqualToOddRowLengthExpectEntireRowFilled() {
        ClueList clueList = new ClueList(List.of(EMPTY_ROW_OF_SEVEN.size()));
        List<Cell> expected = List.of(Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.FILLED, Cell.FILLED,
                Cell.FILLED);
        List<Cell> actual = sut.apply(clueList, EMPTY_ROW_OF_SEVEN);
        Assertions.assertEquals(expected, actual);
    }

}