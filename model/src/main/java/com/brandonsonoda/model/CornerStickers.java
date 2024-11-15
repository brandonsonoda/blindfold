package com.brandonsonoda.model;

import com.brandonsonoda.utilities.UnsupportedCaseException;

public final class CornerStickers {
  public static CornerSticker rotateCw(CornerSticker original) {
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

    throw new UnsupportedCaseException(original);
  }

  public static CornerSticker rotateCcw(CornerSticker original) {
    switch (original) {
      case A_CORNER:
        return CornerSticker.E_CORNER;
      case B_CORNER:
        return CornerSticker.Q_CORNER;
      case C_CORNER:
        return CornerSticker.M_CORNER;
      case D_CORNER:
        return CornerSticker.I_CORNER;
      case E_CORNER:
        return CornerSticker.R_CORNER;
      case F_CORNER:
        return CornerSticker.D_CORNER;
      case G_CORNER:
        return CornerSticker.L_CORNER;
      case H_CORNER:
        return CornerSticker.X_CORNER;
      case I_CORNER:
        return CornerSticker.F_CORNER;
      case J_CORNER:
        return CornerSticker.C_CORNER;
      case K_CORNER:
        return CornerSticker.P_CORNER;
      case L_CORNER:
        return CornerSticker.U_CORNER;
      case M_CORNER:
        return CornerSticker.J_CORNER;
      case N_CORNER:
        return CornerSticker.B_CORNER;
      case O_CORNER:
        return CornerSticker.T_CORNER;
      case P_CORNER:
        return CornerSticker.V_CORNER;
      case Q_CORNER:
        return CornerSticker.N_CORNER;
      case R_CORNER:
        return CornerSticker.A_CORNER;
      case S_CORNER:
        return CornerSticker.H_CORNER;
      case T_CORNER:
        return CornerSticker.W_CORNER;
      case U_CORNER:
        return CornerSticker.G_CORNER;
      case V_CORNER:
        return CornerSticker.K_CORNER;
      case W_CORNER:
        return CornerSticker.O_CORNER;
      case X_CORNER:
        return CornerSticker.S_CORNER;
    }

    throw new UnsupportedCaseException(original);
  }

  /**
    * Returns the CornerSticker such that corner.color is stickerColor and rotateCcw(corner).color is ccwStickerColor
    */
  public static CornerSticker identifyCorner(Color stickerColor, Color ccwStickerColor) {
    switch (stickerColor) {
      case WHITE:
        switch (ccwStickerColor) {
          case ORANGE:
            return CornerSticker.A_CORNER;
          case GREEN:
            return CornerSticker.D_CORNER;
          case RED:
            return CornerSticker.C_CORNER;
          case BLUE:
            return CornerSticker.B_CORNER;
        }
        break;
      case ORANGE:
        switch (ccwStickerColor) {
          case WHITE:
            return CornerSticker.F_CORNER;
          case GREEN:
            return CornerSticker.G_CORNER;
          case BLUE:
            return CornerSticker.E_CORNER;
          case YELLOW:
            return CornerSticker.H_CORNER;
        }
        break;
      case GREEN:
        switch (ccwStickerColor) {
          case WHITE:
            return CornerSticker.J_CORNER;
          case ORANGE:
            return CornerSticker.I_CORNER;
          case RED:
            return CornerSticker.K_CORNER;
          case YELLOW:
            return CornerSticker.L_CORNER;
        }
        break;
      case RED:
        switch (ccwStickerColor) {
          case WHITE:
            return CornerSticker.N_CORNER;
          case GREEN:
            return CornerSticker.M_CORNER;
          case BLUE:
            return CornerSticker.O_CORNER;
          case YELLOW:
            return CornerSticker.P_CORNER;
        }
        break;
      case BLUE:
        switch (ccwStickerColor) {
          case WHITE:
            return CornerSticker.R_CORNER;
          case ORANGE:
            return CornerSticker.S_CORNER;
          case RED:
            return CornerSticker.Q_CORNER;
          case YELLOW:
            return CornerSticker.T_CORNER;
        }
        break;
      case YELLOW:
        switch (ccwStickerColor) {
          case ORANGE:
            return CornerSticker.U_CORNER;
          case GREEN:
            return CornerSticker.V_CORNER;
          case RED:
            return CornerSticker.W_CORNER;
          case BLUE:
            return CornerSticker.X_CORNER;
        }
        break;
    }

    throw new IllegalStateException(String.format("Unexpected corner sticker combination: %s-%s", stickerColor, ccwStickerColor));
  }
}
