package com.brandonsonoda.model;

public enum CornerSticker {
    A_CORNER('a', Color.WHITE),
    B_CORNER('b', Color.WHITE),
    C_CORNER('c', Color.WHITE),
    D_CORNER('d', Color.WHITE),
    E_CORNER('e', Color.ORANGE),
    F_CORNER('f', Color.ORANGE),
    G_CORNER('g', Color.ORANGE),
    H_CORNER('h', Color.ORANGE),
    I_CORNER('i', Color.GREEN),
    J_CORNER('j', Color.GREEN),
    K_CORNER('k', Color.GREEN),
    L_CORNER('l', Color.GREEN),
    M_CORNER('m', Color.RED),
    N_CORNER('n', Color.RED),
    O_CORNER('o', Color.RED),
    P_CORNER('p', Color.RED),
    Q_CORNER('q', Color.BLUE),
    R_CORNER('r', Color.BLUE),
    S_CORNER('s', Color.BLUE),
    T_CORNER('t', Color.BLUE),
    U_CORNER('u', Color.YELLOW),
    V_CORNER('v', Color.YELLOW),
    W_CORNER('w', Color.YELLOW),
    X_CORNER('x', Color.YELLOW);

    public final char serializedCharacter;
    public final Color color;

    CornerSticker(char serializedCharacter, Color color) {
      this.serializedCharacter = serializedCharacter;
      this.color = color;
    }
}
