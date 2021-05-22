package com.nonogram.validators;

import com.nonogram.models.Clue;
import com.nonogram.models.Puzzle;

import java.util.List;
import java.util.function.Predicate;

public class PuzzleValidator implements Predicate<Puzzle> {

    @Override
    public boolean test(Puzzle puzzle) {
        int gridSize = puzzle.getGridSize();
        return testClueListSize(puzzle.getColClues(), gridSize) &&
                testClueListSize(puzzle.getRowClues(), gridSize);
    }

    private boolean testClueListSize(List<Clue> clues, int gridSize) {
        if (clues.size() != gridSize) {
            System.out.println("Puzzle dimensions do not match number of clues.");
            return false;
        }

        return testClueListInRange(clues, gridSize);
    }

    private boolean testClueListInRange(List<Clue> clues, int gridSize) {
        boolean success = clues.stream().allMatch(clue -> testClueInRange(clue, gridSize));
        if (!success) {
            System.out.println("Clue is out of bounds.");
        }
        return success;
    }

    private boolean testClueInRange(Clue clue, int gridSize) {
        int numberOfEmptySpaces = clue.getClues().size() - 1;
        int sumOfClues = clue.getClues().stream().mapToInt(x -> x).sum();
        return numberOfEmptySpaces + sumOfClues <= gridSize;
    }
}
