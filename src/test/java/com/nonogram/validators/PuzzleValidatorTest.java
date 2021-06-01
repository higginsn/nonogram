package com.nonogram.validators;

import com.nonogram.models.ClueList;
import com.nonogram.models.Puzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

/**
 * NEED TO USE MOCKED PUZZLE
 */
public class PuzzleValidatorTest {

    private static final int GRID_SIZE = 3;
    private static final ClueList INVALID_CLUE_LIST_SINGLE_ELEMENT = new ClueList(Collections.singletonList(GRID_SIZE + 1));
    private static final ClueList INVALID_CLUE_LIST_MANY_ELEMENTS = new ClueList(List.of(GRID_SIZE, GRID_SIZE));
    private static final ClueList VALID_CLUE_LIST_SINGLE_ELEMENT = new ClueList(Collections.singletonList(GRID_SIZE));
    private static final ClueList VALID_CLUE_LIST_MANY_ELEMENTS = new ClueList(List.of(1, 1));
    private static final List<ClueList> EMPTY_CLUE_LIST = Collections.emptyList();
    private static final List<ClueList> VALID_CLUE_LIST = Collections.nCopies(GRID_SIZE, VALID_CLUE_LIST_SINGLE_ELEMENT);

    private final PuzzleValidator sut = new PuzzleValidator();

    @Test
    public void cluesMatchGridSizeExpectValid() {
        Puzzle puzzle = arrangePuzzle(VALID_CLUE_LIST, VALID_CLUE_LIST);
        Assertions.assertTrue(sut.test(puzzle));
    }

    @Test
    public void cluesWithSingleElementExpectValid() {
        Puzzle puzzle = arrangePuzzle(VALID_CLUE_LIST, VALID_CLUE_LIST);
        Assertions.assertTrue(sut.test(puzzle));
    }

    @Test
    public void cluesWithManyElementsExpectValid() {
        List<ClueList> validClueList = Collections.nCopies(GRID_SIZE, VALID_CLUE_LIST_MANY_ELEMENTS);
        Puzzle puzzle = arrangePuzzle(validClueList, validClueList);
        Assertions.assertTrue(sut.test(puzzle));
    }

    @Test
    public void noRowCluesExpectInvalid() {
        Puzzle puzzle = arrangePuzzle(EMPTY_CLUE_LIST, VALID_CLUE_LIST);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void noColCluesExpectInvalid() {
        Puzzle puzzle = arrangePuzzle(VALID_CLUE_LIST, EMPTY_CLUE_LIST);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void tooManyRowCluesExpectInvalid() {
        List<ClueList> tooManyRowClueLists = Collections.nCopies(GRID_SIZE + 1, VALID_CLUE_LIST_SINGLE_ELEMENT);
        Puzzle puzzle = arrangePuzzle(tooManyRowClueLists, VALID_CLUE_LIST);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void tooManyColCluesExpectInvalid() {
        List<ClueList> tooManyColClueLists = Collections.nCopies(GRID_SIZE + 1, VALID_CLUE_LIST_SINGLE_ELEMENT);
        Puzzle puzzle = arrangePuzzle(VALID_CLUE_LIST, tooManyColClueLists);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void singleLargeRowClueExpectInvalid() {
        List<ClueList> invalidRowClueLists = Collections.nCopies(GRID_SIZE, INVALID_CLUE_LIST_SINGLE_ELEMENT);
        Puzzle puzzle = arrangePuzzle(invalidRowClueLists, VALID_CLUE_LIST);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void singleLargeColClueExpectInvalid() {
        List<ClueList> invalidColClueLists = Collections.nCopies(GRID_SIZE, INVALID_CLUE_LIST_SINGLE_ELEMENT);
        Puzzle puzzle = arrangePuzzle(VALID_CLUE_LIST, invalidColClueLists);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void singleRowClueWithManyElementsExpectInvalid() {
        List<ClueList> invalidRowClueLists = Collections.nCopies(GRID_SIZE, INVALID_CLUE_LIST_MANY_ELEMENTS);
        Puzzle puzzle = arrangePuzzle(invalidRowClueLists, VALID_CLUE_LIST);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void singleColClueWithManyElementsExpectInvalid() {
        List<ClueList> invalidColClueLists = Collections.nCopies(GRID_SIZE, INVALID_CLUE_LIST_MANY_ELEMENTS);
        Puzzle puzzle = arrangePuzzle(VALID_CLUE_LIST, invalidColClueLists);
        Assertions.assertFalse(sut.test(puzzle));
    }

    private Puzzle arrangePuzzle(List<ClueList> rowClueLists, List<ClueList> colClueLists) {
        return new Puzzle(rowClueLists, colClueLists, GRID_SIZE, false);
    }

}