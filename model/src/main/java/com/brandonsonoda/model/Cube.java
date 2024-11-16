package com.brandonsonoda.model;

import static java.util.stream.Collectors.toList;
import java.util.Collections;

import java.util.List;

public abstract class Cube {
  /** Resets cube to solved state. */
  public abstract void setCubeToSolved();

  /** Returns true if this cube is solved, false otherwise. */
  public abstract boolean isCubeSolved();

  /** Applies a single turn to this cube. */
  protected abstract void apply(Turn t);

  /** Apply an algorithm to this cube. */
  public void apply(Algorithm alg) {
    apply(alg.turns);
  }

  /** Return the color of the sticker in this location. */
  public abstract Color getStickerColor(EdgeSticker homeSticker);

  /** Return the color of the sticker in this location. */
  public abstract Color getStickerColor(CornerSticker homeSticker);

  /** Return the color of the sticker in this location. */
  public abstract Color getStickerColor(Face homeSticker);

  /** Apply a sequence of turns to this cube. */
  public void apply(List<Turn> turns) {
    for (Turn turn : turns) {
      for (Turn basicTurn : turn.basicTurns) {
        apply(basicTurn);
      }
    }
  }

  /** Undos application of a sequence of turns to this cube. */
  public void undo(List<Turn> turns) {
    List<Turn> reversedTurns =
      turns.stream()
      .map(Turn::reverse)
      .collect(toList());
    Collections.reverse(reversedTurns);

    apply(reversedTurns);
  }

}
