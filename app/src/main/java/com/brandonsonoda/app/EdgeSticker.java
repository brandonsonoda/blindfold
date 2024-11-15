package com.brandonsonoda.app;

enum EdgeSticker {
    A_EDGE('A', Color.WHITE),
    B_EDGE('B', Color.WHITE),
    C_EDGE('C', Color.WHITE),
    D_EDGE('D', Color.WHITE),
    E_EDGE('E', Color.ORANGE),
    F_EDGE('F', Color.ORANGE),
    G_EDGE('G', Color.ORANGE),
    H_EDGE('H', Color.ORANGE),
    I_EDGE('I', Color.GREEN),
    J_EDGE('J', Color.GREEN),
    K_EDGE('K', Color.GREEN),
    L_EDGE('L', Color.GREEN),
    M_EDGE('M', Color.RED),
    N_EDGE('N', Color.RED),
    O_EDGE('O', Color.RED),
    P_EDGE('P', Color.RED),
    Q_EDGE('Q', Color.BLUE),
    R_EDGE('R', Color.BLUE),
    S_EDGE('S', Color.BLUE),
    T_EDGE('T', Color.BLUE),
    U_EDGE('U', Color.YELLOW),
    V_EDGE('V', Color.YELLOW),
    W_EDGE('W', Color.YELLOW),
    X_EDGE('X', Color.YELLOW);

    final char serializedCharacter;
    final Color color;

    EdgeSticker(char serializedCharacter, Color color) {
      this.serializedCharacter = serializedCharacter;
      this.color = color;
    }
}
