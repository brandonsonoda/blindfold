package com.brandonsonoda.app;

import com.brandonsonoda.model.Algorithm;
import com.brandonsonoda.model.Cube;

public class Blindfold {
  public static void apply(Cube c, String memo) {
    String[] split = memo.trim().split("\\s+");
    if (split.length > 3) {
      throw new IllegalArgumentException("Too many arguments to blind!");
    }

    String cornerMemo = "";
    String edgeMemo = "";
    boolean parity = false;

    if (split.length != 2 && split.length != 3) {
      throw new IllegalArgumentException("Must contain 2 or 3 arguments");
    }

    if (split.length == 2) {
      cornerMemo = split[0];
      edgeMemo = split[1];
    }

    if (split.length == 3) {
      if (!split[1].equals("#")) {
        throw new IllegalArgumentException("3 Argument memo must contain # as second arg");
      }
      parity = true;
      cornerMemo = split[0];
      edgeMemo = split[2];
    }

    System.out.printf("Memo: corners:%s, edges:%s, hasParity:%b%n", cornerMemo, edgeMemo, parity);
    for (char edgeChar : edgeMemo.toCharArray()) {
      String edge = "" + edgeChar;
      c.apply(SetupMoves.EDGE_SETUPS.get(edge));
      c.apply(Algorithm.T_PERM);
      c.undo(SetupMoves.EDGE_SETUPS.get(edge));
    }

    if (parity) {
      c.apply(Algorithm.PARITY_ALGORITHM);
    }

    for (char cornerChar : cornerMemo.toCharArray()) {
      String corner = "" + cornerChar;
      c.apply(SetupMoves.CORNER_SETUPS.get(corner));
      c.apply(Algorithm.CORNER_SWAP);
      c.undo(SetupMoves.CORNER_SETUPS.get(corner));
    }
  }
}
