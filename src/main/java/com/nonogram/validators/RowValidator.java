package com.nonogram.validators;

import com.nonogram.models.Cell;
import com.nonogram.models.Clue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class RowValidator implements BiFunction<Clue, List<Cell>, Boolean> {

    /**
     * Computes if a row is a valid solution for the given clue list. This equality check is made by
     * computing the number and size of "blocks", or filled cells, that are present in the row.
     * If the solution is valid, the list of blocks will be the same format as the list of clues.
     */
    @Override
    public Boolean apply(Clue clueList, List<Cell> row) {
        List<Integer> clues = clueList.getClues();

        // Empty row base case
        if (clues.equals(Collections.singletonList(0))) {
            return row.stream().allMatch(cell -> cell.equals(Cell.CLEAR) || cell.equals(Cell.EMPTY));
        }

        // Compute the size of "blocks" in each row, in order.
        List<Integer> blocks = new ArrayList<>();
        int block = 0;
        for (Cell cell : row) {
            if (cell.equals(Cell.FILLED)) {
                block++;
                continue;
            }

            if (block > 0) {
                blocks.add(block);
                block = 0;
            }
        }

        if (block > 0) {
            blocks.add(block);
        }

        return clues.equals(blocks);
    }

}
