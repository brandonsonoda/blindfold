package com.brandonsonoda.model;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public enum Algorithm {
  F_PERM(Type.PLL, "R' U' F' R U R' U' R' F R2 U' R' U' R U R' U R"),
  T_PERM(Type.PLL, "R U R' U' R' F R2 U' R' U' R U R' F'"),
  Z_PERM(Type.PLL, "R' U' R2 U R U R' U' R U R U' R U' R'"),
  CORNER_SWAP(Type.BLINDFOLD, "R U' R' U' R U R' F' R U R' U' R' F R"),
  PARITY_ALGORITHM(Type.BLINDFOLD, "R U R' F' R U2 R' U2 R' F R U R U2 R' U'");

  final Type type;
  final List<Turn> turns;

  public enum Type {
    MISC,
    BLINDFOLD,
    PLL
  }

  private Algorithm(Type type, String notation) {
    this.type = type;
    this.turns = Stream.of(notation.trim().split("\\s+"))
      .map(Turn::fromString)
      .collect(toList());
  }
}
