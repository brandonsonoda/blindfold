final class EdgeStickers {
  static EdgeSticker flip(EdgeSticker original) {
    switch (original) {
      case A_EDGE:
        return EdgeSticker.Q_EDGE;
      case B_EDGE:
        return EdgeSticker.M_EDGE;
      case C_EDGE:
        return EdgeSticker.I_EDGE;
      case D_EDGE:
        return EdgeSticker.E_EDGE;
      case E_EDGE:
        return EdgeSticker.D_EDGE;
      case F_EDGE:
        return EdgeSticker.L_EDGE;
      case G_EDGE:
        return EdgeSticker.X_EDGE;
      case H_EDGE:
        return EdgeSticker.R_EDGE;
      case I_EDGE:
        return EdgeSticker.C_EDGE;
      case J_EDGE:
        return EdgeSticker.P_EDGE;
      case K_EDGE:
        return EdgeSticker.U_EDGE;
      case L_EDGE:
        return EdgeSticker.F_EDGE;
      case M_EDGE:
        return EdgeSticker.B_EDGE;
      case N_EDGE:
        return EdgeSticker.T_EDGE;
      case O_EDGE:
        return EdgeSticker.V_EDGE;
      case P_EDGE:
        return EdgeSticker.J_EDGE;
      case Q_EDGE:
        return EdgeSticker.A_EDGE;
      case R_EDGE:
        return EdgeSticker.H_EDGE;
      case S_EDGE:
        return EdgeSticker.W_EDGE;
      case T_EDGE:
        return EdgeSticker.N_EDGE;
      case U_EDGE:
        return EdgeSticker.K_EDGE;
      case V_EDGE:
        return EdgeSticker.O_EDGE;
      case W_EDGE:
        return EdgeSticker.S_EDGE;
      case X_EDGE:
        return EdgeSticker.G_EDGE;
    }

    throw new IllegalStateException("enumerated all edges");
  }
}
