final class CornerStickers {
  static CornerSticker rotateCw(CornerSticker original) {
    switch (original) {
      case A_CORNER:
        return CornerSticker.R_CORNER;
      case B_CORNER:
        return CornerSticker.N_CORNER;
      case C_CORNER:
        return CornerSticker.J_CORNER;
      case D_CORNER:
        return CornerSticker.F_CORNER;
      case E_CORNER:
        return CornerSticker.A_CORNER;
      case F_CORNER:
        return CornerSticker.I_CORNER;
      case G_CORNER:
        return CornerSticker.U_CORNER;
      case H_CORNER:
        return CornerSticker.S_CORNER;
      case I_CORNER:
        return CornerSticker.D_CORNER;
      case J_CORNER:
        return CornerSticker.M_CORNER;
      case K_CORNER:
        return CornerSticker.V_CORNER;
      case L_CORNER:
        return CornerSticker.G_CORNER;
      case M_CORNER:
        return CornerSticker.C_CORNER;
      case N_CORNER:
        return CornerSticker.Q_CORNER;
      case O_CORNER:
        return CornerSticker.W_CORNER;
      case P_CORNER:
        return CornerSticker.K_CORNER;
      case Q_CORNER:
        return CornerSticker.B_CORNER;
      case R_CORNER:
        return CornerSticker.E_CORNER;
      case S_CORNER:
        return CornerSticker.X_CORNER;
      case T_CORNER:
        return CornerSticker.O_CORNER;
      case U_CORNER:
        return CornerSticker.L_CORNER;
      case V_CORNER:
        return CornerSticker.P_CORNER;
      case W_CORNER:
        return CornerSticker.T_CORNER;
      case X_CORNER:
        return CornerSticker.H_CORNER;
    }

    throw new IllegalStateException("enumerated all corners");
  }

  static CornerSticker rotateCcw(CornerSticker original) {
    return rotateCw(rotateCw(original));
  }
}
