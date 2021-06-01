package com.nonogram.models;

import lombok.Value;

import java.util.List;

@Value
public class Solution {

    Puzzle puzzle;
    Board board;

    @Override
    public String toString() {
        String s = "";
        s = s.concat("\n");
        for (ClueList colClueList : puzzle.getColClueLists()) {
            s = s.concat(colClueList.toString());
        }
        s = s.concat("\n");

        for (int i = 0; i < puzzle.getGridSize(); i++) {
            s = s.concat("|");
            List<Cell> row = board.getRowForIndex(i);
            for (Cell cell : row) {
                s = s.concat(cell.toString());
            }
            s = s.concat("| ");
            ClueList rowClueList = puzzle.getRowClueLists().get(0);
            s = s.concat(rowClueList.toString());
            s = s.concat("\n");
        }
        return s;
    }
}
