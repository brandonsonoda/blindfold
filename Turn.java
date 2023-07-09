public enum Turn {
  U("U"),
  U2("U2", U, U),
  U_PRIME("U'", U, U, U),
  R("R"),
  R2("R2", R, R),
  R_PRIME("R'", R, R, R),
  F("F"),
  F2("F2", F, F),
  F_PRIME("F'", F, F, F),
  L("L"),
  L2("L2", L, L),
  L_PRIME("L'", L, L, L),
  B("B"),
  B2("B2", B, B),
  B_PRIME("B'", B, B, B),
  D("D"),
  D2("D2", D, D),
  D_PRIME("D'", D, D, D),
  x("x"),
  x2("x2", x, x),
  x_PRIME("x'", x, x, x),
  y("y"),
  y2("y2", y, y),
  y_PRIME("x'", y, y, y),
  z("z"),
  z2("z2", z, z),
  z_PRIME("z'", z, z, z),
  u("u", D, y),
  u2("u2", D, D, y, y),
  u_PRIME("u'", D, D, D, y, y, y),
  r("r", L, x),
  r2("r2", L, L, x, x),
  r_PRIME("r'", L, L, L, x, x, x),
  f("f", B, z),
  f2("f2", B, B, z, z),
  f_PRIME("f'", B, B, B, z, z, z),
  l("l", R, x, x, x),
  l2("l2", R, R, x, x),
  l_PRIME("l'", R, R, R, x),
  b("b", F, z, z, z),
  b2("b2", F, F, z, z),
  b_PRIME("b'", F, F, F, z),
  d("d", U, y, y, y),
  d2("d2", U, U, y, y),
  d_PRIME("d'", U, U, U, y);

  final String notation;
  final Turn[] basicTurns;

  Turn(String notation, Turn... basicTurns) {
    this.notation = notation;
    this.basicTurns = basicTurns.length == 0 ? new Turn[]{this} : basicTurns;
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
