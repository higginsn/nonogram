package com.nonogram.models;

import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Value
public class Solution {

    Puzzle puzzle;
    Board board;

    public Solution(Puzzle puzzle) {
        this.puzzle = puzzle;

        List<List<Cell>> board = new ArrayList<>();
        for (int i = 0; i < puzzle.getGridSize(); i++) {
            board.add(Collections.nCopies(puzzle.getGridSize(), Cell.EMPTY));
        }
        this.board = new Board(board);
    }

    @Override
    public String toString() {
        String s = "";
        s = s.concat("\n");
        for (Clue colClue : puzzle.getColClues()) {
            s = s.concat(colClue.toString());
        }
        s = s.concat("\n");

        for (int i = 0; i < puzzle.getGridSize(); i++) {
            s = s.concat("|");
            List<Cell> row = board.getRowForIndex(i);
            for (Cell cell : row) {
                s = s.concat(cell.toString());
            }
            s = s.concat("| ");
            Clue rowClue = puzzle.getRowClues().get(0);
            s = s.concat(rowClue.toString());
            s = s.concat("\n");
        }
        return s;
    }
}
