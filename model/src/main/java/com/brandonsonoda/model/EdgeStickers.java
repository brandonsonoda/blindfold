package com.brandonsonoda.model;

import com.brandonsonoda.utilities.UnsupportedCaseException;
import com.google.common.collect.ImmutableSet;

public final class EdgeStickers {
  public static EdgeSticker flip(EdgeSticker original) {
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

    throw new UnsupportedCaseException(original);
  }

  /**
    * Returns the EdgeSticker that has the given stickerColor and the sticker on the flip side has oppositeColor.
    */
  public static EdgeSticker identifyEdge(Color stickerColor, Color oppositeColor) {
    switch (stickerColor) {
      case WHITE:
        switch (oppositeColor) {
          case ORANGE:
            return EdgeSticker.D_EDGE;
          case GREEN:
            return EdgeSticker.C_EDGE;
          case RED:
            return EdgeSticker.B_EDGE;
          case BLUE:
            return EdgeSticker.A_EDGE;
        }
        break;
      case ORANGE:
        switch (oppositeColor) {
          case WHITE:
            return EdgeSticker.E_EDGE;
          case GREEN:
            return EdgeSticker.F_EDGE;
          case BLUE:
            return EdgeSticker.H_EDGE;
          case YELLOW:
            return EdgeSticker.G_EDGE;
        }
        break;
      case GREEN:
        switch (oppositeColor) {
          case WHITE:
            return EdgeSticker.I_EDGE;
          case ORANGE:
            return EdgeSticker.L_EDGE;
          case RED:
            return EdgeSticker.J_EDGE;
          case YELLOW:
            return EdgeSticker.K_EDGE;
        }
        break;
      case RED:
        switch (oppositeColor) {
          case WHITE:
            return EdgeSticker.M_EDGE;
          case GREEN:
            return EdgeSticker.P_EDGE;
          case BLUE:
            return EdgeSticker.N_EDGE;
          case YELLOW:
            return EdgeSticker.O_EDGE;
        }
        break;
      case BLUE:
        switch (oppositeColor) {
          case WHITE:
            return EdgeSticker.Q_EDGE;
          case ORANGE:
            return EdgeSticker.R_EDGE;
          case RED:
            return EdgeSticker.T_EDGE;
          case YELLOW:
            return EdgeSticker.S_EDGE;
        }
        break;
      case YELLOW:
        switch (oppositeColor) {
          case ORANGE:
            return EdgeSticker.X_EDGE;
          case GREEN:
            return EdgeSticker.U_EDGE;
          case RED:
            return EdgeSticker.V_EDGE;
          case BLUE:
            return EdgeSticker.W_EDGE;
        }
        break;
    }

    throw new IllegalStateException(String.format("Unexpected edge sticker combination: %s-%s", stickerColor, oppositeColor));
  }

  public static ImmutableSet<EdgeSticker> getEdgesOnFace(Face face) {
    switch (face) {
      case UP:
        return ImmutableSet.of(
            EdgeSticker.A_EDGE,
            EdgeSticker.B_EDGE,
            EdgeSticker.C_EDGE,
            EdgeSticker.D_EDGE);
      case LEFT:
        return ImmutableSet.of(
            EdgeSticker.E_EDGE,
            EdgeSticker.F_EDGE,
            EdgeSticker.G_EDGE,
            EdgeSticker.H_EDGE);
      case FRONT:
        return ImmutableSet.of(
            EdgeSticker.I_EDGE,
            EdgeSticker.J_EDGE,
            EdgeSticker.K_EDGE,
            EdgeSticker.L_EDGE);
      case RIGHT:
        return ImmutableSet.of(
            EdgeSticker.M_EDGE,
            EdgeSticker.N_EDGE,
            EdgeSticker.O_EDGE,
            EdgeSticker.P_EDGE);
      case BACK:
        return ImmutableSet.of(
            EdgeSticker.Q_EDGE,
            EdgeSticker.R_EDGE,
            EdgeSticker.S_EDGE,
            EdgeSticker.T_EDGE);
      case DOWN:
        return ImmutableSet.of(
            EdgeSticker.U_EDGE,
            EdgeSticker.V_EDGE,
            EdgeSticker.W_EDGE,
            EdgeSticker.X_EDGE);
    }

    throw new UnsupportedCaseException(face);
  }
}
