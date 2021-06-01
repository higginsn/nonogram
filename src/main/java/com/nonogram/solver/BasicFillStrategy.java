package com.nonogram.solver;

import com.nonogram.models.Cell;
import com.nonogram.models.ClueList;
import com.nonogram.strategy.AbstractStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Requirements: Clue with a single entry that is greater than half the number of cells in a row
 * Strategy: Assuming the block starts at the far left or far right of a row, there
 * must be overlap. Fill in this overlap.
 *
 * Example: clue = 4, row length = 6
 * [O, O, O, O, _, _] and [_, _, O, O, O, O] have a guaranteed overlapping block
 * Result: [_, _, 0, 0, _, _]
 */
public class BasicFillStrategy extends AbstractStrategy {

    @Override
    public List<Cell> apply(ClueList clueList, final List<Cell> existingRow) {
        if (clueList.getClues().size() > 1) {
            System.out.println("Clue list is greater than one. No update.");
            return existingRow;
        }

        int clue = clueList.getClues().get(0);
        int rowLength = existingRow.size();
        if (clue <= rowLength / 2) {
            System.out.println("Clue is not more than have the row length. No update.");
            return existingRow;
        }

        if (clue == rowLength) {
            return Collections.nCopies(clue, Cell.FILLED);
        }

        return updateRow(clue, existingRow);
    }

    /**
     * Update row based on strategy.
     *
     * To compute number of overlapping cells in the block:
     * If even: 2 * (clue - rowLength / 2)
     * If odd: 2 * (clue - rowLength / 2) - 1
     *
     * Examples:
     * clue = 4, row length = 6, overlap = 2, empty = 2
     * clue = 5, row length = 6, overlap = 4, empty = 1
     * clue = 6, row length = 6, overlap = 6, empty = 0
     *
     * clue = 4, row length = 7, overlap = 1, empty = 3
     * clue = 5, row length = 7, overlap = 3, empty = 2
     * clue = 6, row length = 7, overlap = 5, empty = 1
     * clue = 7, row length = 7, overlap = 7, empty = 0
     */
    private List<Cell> updateRow(int clue, List<Cell> existingRow) {
        int emptySpaceCount = existingRow.size() - clue;

        List<Cell> result = new ArrayList<>(existingRow);
        IntStream.range(emptySpaceCount, clue)
                .forEach(index -> result.set(index, Cell.FILLED));
        return result;
    }


}

