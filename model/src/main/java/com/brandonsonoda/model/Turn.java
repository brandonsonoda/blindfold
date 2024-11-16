package com.brandonsonoda.model;

import static com.brandonsonoda.model.CubeMutation.U_TURN;
import static com.brandonsonoda.model.CubeMutation.L_TURN;
import static com.brandonsonoda.model.CubeMutation.F_TURN;
import static com.brandonsonoda.model.CubeMutation.R_TURN;
import static com.brandonsonoda.model.CubeMutation.B_TURN;
import static com.brandonsonoda.model.CubeMutation.D_TURN;
import static com.brandonsonoda.model.CubeMutation.X_ROTATION;
import static com.brandonsonoda.model.CubeMutation.Y_ROTATION;
import static com.brandonsonoda.model.CubeMutation.Z_ROTATION;

public enum Turn {
  U("U", U_TURN),
  U2("U2", U_TURN, U_TURN),
  U_PRIME("U'", U_TURN, U_TURN, U_TURN),
  R("R", R_TURN),
  R2("R2", R_TURN, R_TURN),
  R_PRIME("R'", R_TURN, R_TURN, R_TURN),
  F("F", F_TURN),
  F2("F2", F_TURN, F_TURN),
  F_PRIME("F'", F_TURN, F_TURN, F_TURN),
  L("L", L_TURN),
  L2("L2", L_TURN, L_TURN),
  L_PRIME("L'", L_TURN, L_TURN, L_TURN),
  B("B", B_TURN),
  B2("B2", B_TURN, B_TURN),
  B_PRIME("B'", B_TURN, B_TURN, B_TURN),
  D("D", D_TURN),
  D2("D2", D_TURN, D_TURN),
  D_PRIME("D'", D_TURN, D_TURN, D_TURN),
  x("x", X_ROTATION),
  x2("x2", X_ROTATION, X_ROTATION),
  x_PRIME("x'", X_ROTATION, X_ROTATION, X_ROTATION),
  y("y", Y_ROTATION),
  y2("y2", Y_ROTATION, Y_ROTATION),
  y_PRIME("y'", Y_ROTATION, Y_ROTATION, Y_ROTATION),
  z("z", Z_ROTATION),
  z2("z2", Z_ROTATION, Z_ROTATION),
  z_PRIME("z'", Z_ROTATION, Z_ROTATION, Z_ROTATION),
  u("u", D_TURN, Y_ROTATION),
  u2("u2", D_TURN, D_TURN, Y_ROTATION, Y_ROTATION),
  u_PRIME("u'", D_TURN, D_TURN, D_TURN, Y_ROTATION, Y_ROTATION, Y_ROTATION),
  r("r", L_TURN, X_ROTATION),
  r2("r2", L_TURN, L_TURN, X_ROTATION, X_ROTATION),
  r_PRIME("r'", L_TURN, L_TURN, L_TURN, X_ROTATION, X_ROTATION, X_ROTATION),
  f("f", B_TURN, Z_ROTATION),
  f2("f2", B_TURN, B_TURN, Z_ROTATION, Z_ROTATION),
  f_PRIME("f'", B_TURN, B_TURN, B_TURN, Z_ROTATION, Z_ROTATION, Z_ROTATION),
  l("l", R_TURN, X_ROTATION, X_ROTATION, X_ROTATION),
  l2("l2", R_TURN, R_TURN, X_ROTATION, X_ROTATION),
  l_PRIME("l'", R_TURN, R_TURN, R_TURN, X_ROTATION),
  b("b", F_TURN, Z_ROTATION, Z_ROTATION, Z_ROTATION),
  b2("b2", F_TURN, F_TURN, Z_ROTATION, Z_ROTATION),
  b_PRIME("b'", F_TURN, F_TURN, F_TURN, Z_ROTATION),
  d("d", U_TURN, Y_ROTATION, Y_ROTATION, Y_ROTATION),
  d2("d2", U_TURN, U_TURN, Y_ROTATION, Y_ROTATION),
  d_PRIME("d'", U_TURN, U_TURN, U_TURN, Y_ROTATION),
  M("M", X_ROTATION, X_ROTATION, X_ROTATION, L_TURN, L_TURN, L_TURN, R_TURN),
  M2("M2", L_TURN, L_TURN, R_TURN, R_TURN, X_ROTATION, X_ROTATION),
  M_PRIME("M'", X_ROTATION, L_TURN, R_TURN, R_TURN, R_TURN),
  E("E", Y_ROTATION, Y_ROTATION, Y_ROTATION, U_TURN, D_TURN, D_TURN, D_TURN),
  E2("E2", Y_ROTATION, Y_ROTATION, U_TURN, U_TURN, D_TURN, D_TURN),
  E_PRIME("E'", Y_ROTATION, U_TURN, U_TURN, U_TURN, D_TURN),
  S("S", Z_ROTATION, F_TURN, F_TURN, F_TURN, B_TURN),
  S2("S2", Z_ROTATION, Z_ROTATION, F_TURN, F_TURN, B_TURN, B_TURN),
  S_PRIME("S'", Z_ROTATION, Z_ROTATION, Z_ROTATION, F_TURN, B_TURN, B_TURN, B_TURN);

  final String notation;
  final CubeMutation[] mutations;

  Turn(String notation, CubeMutation... mutations) {
    this.notation = notation;
    this.mutations = mutations;
  }

  public static Turn fromString(String notation) {
    for (Turn t : Turn.values()) {
      if (t.notation.equals(notation)) {
        return t;
      }
    }

    throw new IllegalArgumentException("No Turn with notation: " + notation);
  }

  public Turn reverse() {
    if (notation.endsWith("2")) {
      return this;
    }

    if (notation.endsWith("'")) {
      return fromString(notation.substring(0, 1));
    }

    return fromString(notation + "'");
  }
}
