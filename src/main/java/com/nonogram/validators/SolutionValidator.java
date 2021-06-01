package com.nonogram.validators;

import com.nonogram.models.Board;
import com.nonogram.models.Cell;
import com.nonogram.models.Clue;
import com.nonogram.models.Puzzle;
import com.nonogram.models.Solution;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class SolutionValidator implements Predicate<Solution> {

    private static final RowValidator ROW_VALIDATOR = new RowValidator();

    @Override
    public boolean test(Solution solution) {
        boolean isCorrect = IntStream.range(0, solution.getPuzzle().getGridSize())
                .allMatch(i -> testIndex(solution, i));
        System.out.println(isCorrect ? "Solution confirmed!" : "Solution incorrect!");
        return isCorrect;
    }

    private boolean testIndex(Solution solution, int index) {
        Puzzle puzzle = solution.getPuzzle();
        Board board = solution.getBoard();
        return testRow(puzzle.getRowClues().get(index), board.getRowForIndex(index))
                && testRow(puzzle.getColClues().get(index), board.getColumnForIndex(index));
    }

    private boolean testRow(Clue clueList, List<Cell> row) {
        return ROW_VALIDATOR.apply(clueList, row);
    }

}
