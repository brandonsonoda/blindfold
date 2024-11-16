package com.brandonsonoda.model;

import static java.util.stream.Collectors.toList;
import java.util.Collections;

import java.util.List;

public abstract class Cube {
  /** Resets cube to solved state. */
  public abstract void setCubeToSolved();

  /** Returns true if this cube is solved, false otherwise. */
  public abstract boolean isCubeSolved();

  /** Return the sticker that is currently in the provided location. */
  public abstract EdgeSticker getSticker(EdgeSticker homeSticker);

  /** Return the color of the sticker in the provided location. */
  public Color getStickerColor(EdgeSticker homeSticker) {
    return getSticker(homeSticker).color;
  }

  /** Return the sticker that is currently in the provided location. */
  public abstract CornerSticker getSticker(CornerSticker homeSticker);

  /** Return the color of the sticker in the provided location. */
  public Color getStickerColor(CornerSticker homeSticker) {
    return getSticker(homeSticker).color;
  }

  /** Return the color of the sticker in the provided location. */
  public abstract Color getStickerColor(Face homeSticker);

  /** Apply a sequence of turns to this cube. */
  public final void apply(List<Turn> turns) {
    for (Turn turn : turns) {
      for (CubeMutation mutation : turn.mutations) {
        mutation.apply(this);
      }
    }
  }

  /** Apply an algorithm to this cube. */
  public final void apply(Algorithm alg) {
    apply(alg.turns);
  }

  /** Undos application of a sequence of turns to this cube. */
  public final void undo(List<Turn> turns) {
    List<Turn> reversedTurns =
      turns.stream()
      .map(Turn::reverse)
      .collect(toList());
    Collections.reverse(reversedTurns);

    apply(reversedTurns);
  }

  /** Rotate this cube clockwise as if looking at referenceFace. */
  abstract void rotateCube(Face referenceFace);

  /** Turn specific face 90 clockwise. */
  abstract void turnFace(Face face);

}
