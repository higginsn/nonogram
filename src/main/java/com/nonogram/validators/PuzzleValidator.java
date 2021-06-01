package com.nonogram.validators;

import com.nonogram.models.ClueList;
import com.nonogram.models.Puzzle;

import java.util.List;
import java.util.function.Predicate;

public class PuzzleValidator implements Predicate<Puzzle> {

    @Override
    public boolean test(Puzzle puzzle) {
        int gridSize = puzzle.getGridSize();
        return testClueListSize(puzzle.getColClueLists(), gridSize) &&
                testClueListSize(puzzle.getRowClueLists(), gridSize);
    }

    private boolean testClueListSize(List<ClueList> clueLists, int gridSize) {
        if (clueLists.size() != gridSize) {
            System.out.println("Puzzle dimensions do not match number of clueLists.");
            return false;
        }

        return testClueListInRange(clueLists, gridSize);
    }

    private boolean testClueListInRange(List<ClueList> clueLists, int gridSize) {
        boolean success = clueLists.stream().allMatch(clue -> testClueInRange(clue, gridSize));
        if (!success) {
            System.out.println("Clue is out of bounds.");
        }
        return success;
    }

    private boolean testClueInRange(ClueList clueList, int gridSize) {
        int numberOfEmptySpaces = clueList.getClues().size() - 1;
        int sumOfClues = clueList.getClues().stream().mapToInt(x -> x).sum();
        return numberOfEmptySpaces + sumOfClues <= gridSize;
    }
}
