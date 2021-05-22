package com.nonogram.models;

import lombok.Value;
import org.junit.jupiter.api.Assertions;

import java.util.List;

/**
 * List of integer clues corresponding to an individual row or column
 */
@Value
public class Clue {

    List<Integer> clues;

    public Clue(List<Integer> clues) {
        Assertions.assertFalse(clues.isEmpty());
        this.clues = clues;
    }

    @Override
    public String toString() {
        return clues.toString();
    }


}
