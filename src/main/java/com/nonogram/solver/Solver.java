package com.nonogram.solver;

import com.nonogram.models.Board;
import com.nonogram.models.Cell;
import com.nonogram.models.Puzzle;
import com.nonogram.models.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Solver implements Function<Puzzle, Solution> {


    @Override
    public Solution apply(Puzzle puzzle) {
        List<List<Cell>> board = new ArrayList<>();
        for (int i = 0; i < puzzle.getGridSize(); i++) {
            board.add(Collections.nCopies(puzzle.getGridSize(), Cell.FILLED));
        }

        return new Solution(puzzle, new Board(board));
    }
}
