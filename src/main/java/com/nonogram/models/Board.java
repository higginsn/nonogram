package com.nonogram.models;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public final class Board {

    private final List<List<Cell>> rowList;

    public List<Cell> getRowForIndex(int index) {
        return rowList.get(index);
    }

    public List<Cell> getColumnForIndex(int index) {
        return rowList.stream().map(row -> row.get(index)).collect(Collectors.toList());
    }

}
