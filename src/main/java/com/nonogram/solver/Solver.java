package com.nonogram.solver;

import com.nonogram.models.Board;
import com.nonogram.models.Puzzle;
import com.nonogram.models.Solution;

import java.util.function.Function;

public class Solver implements Function<Puzzle, Solution> {


    @Override
    public Solution apply(Puzzle puzzle) {
        Board board = new Board(puzzle.getGridSize());


        return new Solution(puzzle, board);
    }
}
