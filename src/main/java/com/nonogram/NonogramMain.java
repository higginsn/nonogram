package com.nonogram;

import com.nonogram.models.Clue;
import com.nonogram.models.Puzzle;
import com.nonogram.models.Solution;
import com.nonogram.solver.Solver;

import java.util.Collections;
import java.util.List;

public class NonogramMain {

    private static final Solver SOLVER = new Solver();

    public static void main(String[] args) {
        int gridSize = 3;
        Clue everythingFilledClue = new Clue(List.of(gridSize));
        List<Clue> clues = Collections.nCopies(gridSize, everythingFilledClue);
        Puzzle puzzle = new Puzzle(clues, clues, gridSize);
        Solution solution = SOLVER.apply(puzzle);

        System.out.println(solution);


    }

}
