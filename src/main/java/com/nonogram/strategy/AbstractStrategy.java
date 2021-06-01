package com.nonogram.strategy;

import com.nonogram.models.Cell;
import com.nonogram.models.ClueList;

import java.util.List;

/**
 * Abstract class for applying a strategy to a single row and clue
 */
public abstract class AbstractStrategy {

    /**
     * @param clueLists - list of clueLists for a given row or column
     * @param existingRow - current state of a row or column, corresponding to the given clueLists
     * @return new state of row or column. If the strategy is a no-op, the state will be unchanged
     */
    public abstract List<Cell> apply(ClueList clueLists, List<Cell> existingRow);

}
