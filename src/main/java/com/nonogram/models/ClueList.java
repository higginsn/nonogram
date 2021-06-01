package com.nonogram.models;

import lombok.Value;
import org.junit.jupiter.api.Assertions;

import java.util.List;

/**
 * List of integer clueLists corresponding to an individual row or column
 */
@Value
public class ClueList {

    List<Integer> clues;

    public ClueList(List<Integer> clueLists) {
        Assertions.assertFalse(clueLists.isEmpty());
        this.clues = clueLists;
    }

    @Override
    public String toString() {
        return clues.toString();
    }


}
