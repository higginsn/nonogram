package com.nonogram.models;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public final class Board {

    @NonNull
    private List<List<Cell>> rowList;

    public Board(int gridSize) {
        List<List<Cell>> board = new ArrayList<>();
        for (int i = 0; i < gridSize; i++) {
            board.add(Collections.nCopies(gridSize, Cell.EMPTY));
        }
        this.rowList = board;
    }

    public Cell getCell(int row, int col) {
        return rowList.get(row).get(col);
    }
    public void updateCell(int row, int col, Cell element) {
        rowList.get(row).set(col, element);
    }

    public List<Cell> getRowForIndex(int index) {
        return rowList.get(index);
    }

    public List<Cell> getColumnForIndex(int index) {
        return rowList.stream()
                .map(row -> row.get(index))
                .collect(Collectors.toList());
    }

}
