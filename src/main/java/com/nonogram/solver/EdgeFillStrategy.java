package com.nonogram.solver;

import com.nonogram.models.Cell;
import com.nonogram.models.ClueList;
import com.nonogram.strategy.AbstractStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Requirements: Existing row has its start or end cell filled
 * Strategy: Given the block starts at the far left or far right of a row, the first or
 * last clue can be filled immediately.
 * <p>
 * Example: clue list = [2, 2, 3], row length = 10, existing row = [X, _, ..., _, X]
 * The first clue must start at the left edge. Similarly, the last clue must stop at the right edge.
 * Result: [X, X, _, _, _, _, _, _, _, X, X, X]
 */
public class EdgeFillStrategy extends AbstractStrategy {

    @Override
    public List<Cell> apply(ClueList clueList, List<Cell> existingRow) {
        int rowLength = existingRow.size();
        Cell firstCell = existingRow.get(0);
        Cell lastCell = existingRow.get(rowLength - 1);

        if (firstCell != Cell.FILLED && lastCell != Cell.FILLED) {
            System.out.println("Start or end of row are now filled in. No update.");
            return existingRow;
        }

        List<Cell> result = new ArrayList<>(existingRow);
        if (firstCell == Cell.FILLED) {
            int firstClue = clueList.getClues().get(0);
            IntStream.range(0, firstClue)
                    .forEach(index -> result.set(index, Cell.FILLED));
        }

        if (lastCell == Cell.FILLED) {
            int lastClue = clueList.getClues().get(rowLength - 1);
            IntStream.range(rowLength - lastClue, rowLength)
                    .forEach(index -> result.set(index, Cell.FILLED));
        }

        return result;
    }

}
