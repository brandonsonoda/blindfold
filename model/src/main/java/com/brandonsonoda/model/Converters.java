package com.brandonsonoda.model;

import com.brandonsonoda.utilities.UnsupportedCaseException;

class Converters {
  static Turn toBasicTurn(Face face) {
    switch (face) {
      case UP:
        return Turn.U;
      case LEFT:
        return Turn.L;
      case FRONT:
        return Turn.F;
      case RIGHT:
        return Turn.R;
      case BACK:
        return Turn.B;
      case DOWN:
        return Turn.D;
    }

    throw new UnsupportedCaseException(face);
  }

  static Face toFace(Turn turn) {
    switch (turn) {
      case U:
        return Face.UP;
      case L:
        return Face.LEFT;
      case F:
        return Face.FRONT;
      case R:
        return Face.RIGHT;
      case B:
        return Face.BACK;
      case D:
        return Face.DOWN;
    }

    throw new UnsupportedCaseException(turn);
  }

  static Face defaultFace(Color originalColor) {
    switch (originalColor) {
      case WHITE:
        return Face.UP;
      case ORANGE:
        return Face.LEFT;
      case GREEN:
        return Face.FRONT;
      case RED:
        return Face.RIGHT;
      case BLUE:
        return Face.BACK;
      case YELLOW:
        return Face.DOWN;
    }

    throw new UnsupportedCaseException(originalColor);
  }

  static Color defaultColor(Face face) {
    switch (face) {
      case UP:
        return Color.WHITE;
      case LEFT:
        return Color.ORANGE;
      case FRONT:
        return Color.GREEN;
      case RIGHT:
        return Color.RED;
      case BACK:
        return Color.BLUE;
      case DOWN:
        return Color.YELLOW;
    }

    throw new UnsupportedCaseException(face);
  }
}
