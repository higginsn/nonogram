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

public class SolverFunctionalTests {

    private static final Solver SOLVER = new Solver();
    private static final SolutionValidator VALIDATOR = new SolutionValidator();

    @Test
    public void filled3x3() {
        int gridSize = 3;
        ClueList everythingFilledClueList = new ClueList(List.of(gridSize));
        List<ClueList> clueLists = Collections.nCopies(gridSize, everythingFilledClueList);
        Puzzle puzzle = new Puzzle(clueLists, clueLists, gridSize);
        solve(puzzle);
    }

    private void solve(Puzzle puzzle) {
        Solution solution = SOLVER.apply(puzzle);
        assertTrue(VALIDATOR.test(solution));
    }

}
