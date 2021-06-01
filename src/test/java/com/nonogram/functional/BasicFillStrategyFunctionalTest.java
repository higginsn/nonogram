package com.nonogram.functional;

import com.nonogram.models.ClueList;
import com.nonogram.models.Puzzle;
import com.nonogram.models.Solution;
import com.nonogram.solver.Solver;
import com.nonogram.validators.SolutionValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO: parameterize across grid size
public class BasicFillStrategyFunctionalTest {

    private static final Solver SOLVER = new Solver();
    private static final SolutionValidator VALIDATOR = new SolutionValidator();

    @Test
    public void filled1x1() {
        int gridSize = 1;
        Puzzle puzzle = createPuzzleOfSize(gridSize);
        solveAndAssertSolution(puzzle);
    }

    @Test
    public void filled3x3() {
        int gridSize = 3;
        Puzzle puzzle = createPuzzleOfSize(gridSize);
        solveAndAssertSolution(puzzle);
    }

    @Test
    public void filled10x10() {
        int gridSize = 10;
        Puzzle puzzle = createPuzzleOfSize(gridSize);
        solveAndAssertSolution(puzzle);
    }

    private void solveAndAssertSolution(Puzzle puzzle) {
        Solution solution = SOLVER.apply(puzzle);
        assertTrue(VALIDATOR.test(solution));
    }

    private Puzzle createPuzzleOfSize(int gridSize) {
        ClueList everythingFilledClueList = new ClueList(List.of(gridSize));
        List<ClueList> clueLists = Collections.nCopies(gridSize, everythingFilledClueList);
        return new Puzzle(clueLists, clueLists, gridSize);
    }

}
