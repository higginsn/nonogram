package com.nonogram.solver;

import com.nonogram.models.Board;
import com.nonogram.models.Cell;
import com.nonogram.models.Puzzle;
import com.nonogram.models.Solution;

import java.util.List;
import java.util.function.Function;

public class Solver implements Function<Puzzle, Solution> {

    private static final BasicFillStrategy BASIC_FILL_STRATEGY = new BasicFillStrategy();

    @Override
    public Solution apply(Puzzle puzzle) {
        Board board = new Board(puzzle.getGridSize());

        // Run all rows through basic fill strategy
        for (int i = 0; i < board.getRowList().size(); i++) {
            List<Cell> existingRow = board.getRowForIndex(i);
            List<Cell> newRow = BASIC_FILL_STRATEGY.apply(puzzle.getRowClueLists().get(i), existingRow);
            board.updateRow(i, newRow);
        }

        // Run all columns through basic fill strategy
        for (int i = 0; i < board.getRowList().size(); i++) {
            List<Cell> existingRow = board.getColumnForIndex(i);
            List<Cell> newRow = BASIC_FILL_STRATEGY.apply(puzzle.getColClueLists().get(i), existingRow);
            board.updateRow(i, newRow);
        }

        return new Solution(puzzle, board);
    }
}
