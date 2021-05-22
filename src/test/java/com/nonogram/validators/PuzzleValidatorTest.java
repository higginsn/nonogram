package com.nonogram.validators;

import com.nonogram.models.Clue;
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
    private static final Clue INVALID_CLUE_SINGLE_ELEMENT = new Clue(Collections.singletonList(GRID_SIZE + 1));
    private static final Clue INVALID_CLUE_MANY_ELEMENTS = new Clue(List.of(GRID_SIZE, GRID_SIZE));
    private static final Clue VALID_CLUE_SINGLE_ELEMENT = new Clue(Collections.singletonList(GRID_SIZE));
    private static final Clue VALID_CLUE_MANY_ELEMENTS = new Clue(List.of(1, 1));
    private static final List<Clue> EMPTY_CLUE_LIST = Collections.emptyList();
    private static final List<Clue> VALID_CLUE_LIST = Collections.nCopies(GRID_SIZE, VALID_CLUE_SINGLE_ELEMENT);

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
        List<Clue> validClueList = Collections.nCopies(GRID_SIZE, VALID_CLUE_MANY_ELEMENTS);
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
        List<Clue> tooManyRowClues = Collections.nCopies(GRID_SIZE + 1, VALID_CLUE_SINGLE_ELEMENT);
        Puzzle puzzle = arrangePuzzle(tooManyRowClues, VALID_CLUE_LIST);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void tooManyColCluesExpectInvalid() {
        List<Clue> tooManyColClues = Collections.nCopies(GRID_SIZE + 1, VALID_CLUE_SINGLE_ELEMENT);
        Puzzle puzzle = arrangePuzzle(VALID_CLUE_LIST, tooManyColClues);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void singleLargeRowClueExpectInvalid() {
        List<Clue> invalidRowClues = Collections.nCopies(GRID_SIZE, INVALID_CLUE_SINGLE_ELEMENT);
        Puzzle puzzle = arrangePuzzle(invalidRowClues, VALID_CLUE_LIST);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void singleLargeColClueExpectInvalid() {
        List<Clue> invalidColClues = Collections.nCopies(GRID_SIZE, INVALID_CLUE_SINGLE_ELEMENT);
        Puzzle puzzle = arrangePuzzle(VALID_CLUE_LIST, invalidColClues);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void singleRowClueWithManyElementsExpectInvalid() {
        List<Clue> invalidRowClues = Collections.nCopies(GRID_SIZE, INVALID_CLUE_MANY_ELEMENTS);
        Puzzle puzzle = arrangePuzzle(invalidRowClues, VALID_CLUE_LIST);
        Assertions.assertFalse(sut.test(puzzle));
    }

    @Test
    public void singleColClueWithManyElementsExpectInvalid() {
        List<Clue> invalidColClues = Collections.nCopies(GRID_SIZE, INVALID_CLUE_MANY_ELEMENTS);
        Puzzle puzzle = arrangePuzzle(VALID_CLUE_LIST, invalidColClues);
        Assertions.assertFalse(sut.test(puzzle));
    }

    private Puzzle arrangePuzzle(List<Clue> rowClues, List<Clue> colClues) {
        return new Puzzle(rowClues, colClues, GRID_SIZE, false);
    }

}