import static java.util.stream.Collectors.toList;
import java.util.Collections;

import java.util.List;

interface Cube {
  /** Resets cube to solved state. */
  public void setCubeToSolved();

  /** Returns true if this cube is solved, false otherwise. */
  public boolean isCubeSolved();

  /** Applies a single turn to this cube. */
  public void apply(Turn t);

  /** Prints the current state of this cube. */
  public String printCube(long mask);

  /** Apply an algorithm to this cube. */
  public default void apply(Algorithm alg) {
    apply(alg.turns);
  }

  /** Apply a sequence of turns to this cube. */
  public default void apply(List<Turn> turns) {
    for (Turn turn : turns) {
      for (Turn basicTurn : turn.basicTurns) {
        apply(basicTurn);
      }
    }
  }

  /** Undos application of a sequence of turns to this cube. */
  public default void undo(List<Turn> turns) {
    List<Turn> reversedTurns =
      turns.stream()
      .map(Turn::reverse)
      .collect(toList());
    Collections.reverse(reversedTurns);

    apply(reversedTurns);
  }

}
