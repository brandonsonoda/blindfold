import java.util.List;
import java.util.stream.Stream;

/**
  * A Cube with a data representation of each individual sticker.
  */
class StickeredCube implements Cube {
  private Color[][] state = new Color[6][9]; //nice

  public StickeredCube() {
    setCubeToSolved();
  }

  @Override
  public void setCubeToSolved() {
    for (int i = 0; i < 9; i++) {
      state[Color.WHITE.code][i] = Color.WHITE;
      state[Color.ORANGE.code][i] = Color.ORANGE;
      state[Color.GREEN.code][i] = Color.GREEN;
      state[Color.RED.code][i] = Color.RED;
      state[Color.BLUE.code][i] = Color.BLUE;
      state[Color.YELLOW.code][i] = Color.YELLOW;
    }
  }

  @Override
  public boolean isCubeSolved() {
    return Stream.of(state)
      .allMatch(face -> Stream.of(face)
          .allMatch(color -> color == face[0]));
  }

  @Override
  public Color getStickerColor(CornerSticker homeSticker) {
    switch (homeSticker) {
      case A_CORNER:
        return state[0][0];
      case B_CORNER:
        return state[0][2];
      case C_CORNER:
        return state[0][8];
      case D_CORNER:
        return state[0][6];
      case E_CORNER:
        return state[1][0];
      case F_CORNER:
        return state[1][2];
      case G_CORNER:
        return state[1][8];
      case H_CORNER:
        return state[1][6];
      case I_CORNER:
        return state[2][0];
      case J_CORNER:
        return state[2][2];
      case K_CORNER:
        return state[2][8];
      case L_CORNER:
        return state[2][6];
      case M_CORNER:
        return state[3][0];
      case N_CORNER:
        return state[3][2];
      case O_CORNER:
        return state[3][8];
      case P_CORNER:
        return state[3][6];
      case Q_CORNER:
        return state[4][0];
      case R_CORNER:
        return state[4][2];
      case S_CORNER:
        return state[4][8];
      case T_CORNER:
        return state[4][6];
      case U_CORNER:
        return state[5][0];
      case V_CORNER:
        return state[5][2];
      case W_CORNER:
        return state[5][8];
      case X_CORNER:
        return state[5][6];
    }

    throw new UnsupportedCaseException(homeSticker);
  }

  @Override
  public Color getStickerColor(EdgeSticker homeSticker) {
    switch (homeSticker) {
      case A_EDGE:
        return state[0][1];
      case B_EDGE:
        return state[0][5];
      case C_EDGE:
        return state[0][7];
      case D_EDGE:
        return state[0][3];
      case E_EDGE:
        return state[1][1];
      case F_EDGE:
        return state[1][5];
      case G_EDGE:
        return state[1][7];
      case H_EDGE:
        return state[1][3];
      case I_EDGE:
        return state[2][1];
      case J_EDGE:
        return state[2][5];
      case K_EDGE:
        return state[2][7];
      case L_EDGE:
        return state[2][3];
      case M_EDGE:
        return state[3][1];
      case N_EDGE:
        return state[3][5];
      case O_EDGE:
        return state[3][7];
      case P_EDGE:
        return state[3][3];
      case Q_EDGE:
        return state[4][1];
      case R_EDGE:
        return state[4][5];
      case S_EDGE:
        return state[4][7];
      case T_EDGE:
        return state[4][3];
      case U_EDGE:
        return state[5][1];
      case V_EDGE:
        return state[5][5];
      case W_EDGE:
        return state[5][7];
      case X_EDGE:
        return state[5][3];
    }

    throw new UnsupportedCaseException(homeSticker);
  }

  @Override
  public Color getStickerColor(Face homeSticker) {
    switch (homeSticker) {
      case UP:
        return state[0][4];
      case DOWN:
        return state[5][4];
      case LEFT:
        return state[1][4];
      case RIGHT:
        return state[3][4];
      case FRONT:
        return state[2][4];
      case BACK:
        return state[4][4];
    }

    throw new UnsupportedCaseException(homeSticker);
  }

  @Override
  public void apply(Turn t) {
    switch (t) {
      case U:
        // FACE CORNERS
        rotateFour(encodePosition(0, 0), encodePosition(0, 2), encodePosition(0, 8), encodePosition(0, 6));
        // FACE EDGES
        rotateFour(encodePosition(0, 1), encodePosition(0, 5), encodePosition(0, 7), encodePosition(0, 3));
        // ADJACENT BAND
        rotateFour(encodePosition(1, 2), encodePosition(4, 2), encodePosition(3, 2), encodePosition(2, 2));
        rotateFour(encodePosition(1, 1), encodePosition(4, 1), encodePosition(3, 1), encodePosition(2, 1));
        rotateFour(encodePosition(1, 0), encodePosition(4, 0), encodePosition(3, 0), encodePosition(2, 0));
        return;
      case R:
        // FACE CORNERS
        rotateFour(encodePosition(3, 0), encodePosition(3, 2), encodePosition(3, 8), encodePosition(3, 6));
        // FACE EDGES
        rotateFour(encodePosition(3, 1), encodePosition(3, 5), encodePosition(3, 7), encodePosition(3, 3));
        // ADJACENT BAND
        rotateFour(encodePosition(0, 8), encodePosition(4, 0), encodePosition(5, 8), encodePosition(2, 8));
        rotateFour(encodePosition(0, 5), encodePosition(4, 3), encodePosition(5, 5), encodePosition(2, 5));
        rotateFour(encodePosition(0, 2), encodePosition(4, 6), encodePosition(5, 2), encodePosition(2, 2));
        return;
      case F:
        // FACE CORNERS
        rotateFour(encodePosition(2, 0), encodePosition(2, 2), encodePosition(2, 8), encodePosition(2, 6));
        // FACE EDGES
        rotateFour(encodePosition(2, 1), encodePosition(2, 5), encodePosition(2, 7), encodePosition(2, 3));
        // ADJACENT BAND
        rotateFour(encodePosition(0, 6), encodePosition(3, 0), encodePosition(5, 2), encodePosition(1, 8));
        rotateFour(encodePosition(0, 7), encodePosition(3, 3), encodePosition(5, 1), encodePosition(1, 5));
        rotateFour(encodePosition(0, 8), encodePosition(3, 6), encodePosition(5, 0), encodePosition(1, 2));
        return;
      case L:
        // FACE CORNERS
        rotateFour(encodePosition(1, 0), encodePosition(1, 2), encodePosition(1, 8), encodePosition(1, 6));
        // FACE EDGES
        rotateFour(encodePosition(1, 1), encodePosition(1, 5), encodePosition(1, 7), encodePosition(1, 3));
        // ADJACENT BAND
        rotateFour(encodePosition(0, 0), encodePosition(2, 0), encodePosition(5, 0), encodePosition(4, 8));
        rotateFour(encodePosition(0, 3), encodePosition(2, 3), encodePosition(5, 3), encodePosition(4, 5));
        rotateFour(encodePosition(0, 6), encodePosition(2, 6), encodePosition(5, 6), encodePosition(4, 2));
        return;
      case B:
        // FACE CORNERS
        rotateFour(encodePosition(4, 0), encodePosition(4, 2), encodePosition(4, 8), encodePosition(4, 6));
        // FACE EDGES
        rotateFour(encodePosition(4, 1), encodePosition(4, 5), encodePosition(4, 7), encodePosition(4, 3));
        // ADJACENT BAND
        rotateFour(encodePosition(0, 2), encodePosition(1, 0), encodePosition(5, 6), encodePosition(3, 8));
        rotateFour(encodePosition(0, 1), encodePosition(1, 3), encodePosition(5, 7), encodePosition(3, 5));
        rotateFour(encodePosition(0, 0), encodePosition(1, 6), encodePosition(5, 8), encodePosition(3, 2));
        return;
      case D:
        // FACE CORNERS
        rotateFour(encodePosition(5, 0), encodePosition(5, 2), encodePosition(5, 8), encodePosition(5, 6));
        // FACE EDGES
        rotateFour(encodePosition(5, 1), encodePosition(5, 5), encodePosition(5, 7), encodePosition(5, 3));
        // ADJACENT BAND
        rotateFour(encodePosition(1, 6), encodePosition(2, 6), encodePosition(3, 6), encodePosition(4, 6));
        rotateFour(encodePosition(1, 7), encodePosition(2, 7), encodePosition(3, 7), encodePosition(4, 7));
        rotateFour(encodePosition(1, 8), encodePosition(2, 8), encodePosition(3, 8), encodePosition(4, 8));
        return;
      case x:
        // R FACE
        rotateFour(encodePosition(3, 0), encodePosition(3, 2), encodePosition(3, 8), encodePosition(3, 6));
        rotateFour(encodePosition(3, 1), encodePosition(3, 5), encodePosition(3, 7), encodePosition(3, 3));
        // L' FACE
        rotateFour(encodePosition(1, 6), encodePosition(1, 8), encodePosition(1, 2), encodePosition(1, 0));
        rotateFour(encodePosition(1, 1), encodePosition(1, 3), encodePosition(1, 7), encodePosition(1, 5));
        // BAND (R)
        rotateFour(encodePosition(0, 8), encodePosition(4, 0), encodePosition(5, 8), encodePosition(2, 8));
        rotateFour(encodePosition(0, 5), encodePosition(4, 3), encodePosition(5, 5), encodePosition(2, 5));
        rotateFour(encodePosition(0, 2), encodePosition(4, 6), encodePosition(5, 2), encodePosition(2, 2));
        // BAND (L')
        rotateFour(encodePosition(0, 0), encodePosition(4, 8), encodePosition(5, 0), encodePosition(2, 0));
        rotateFour(encodePosition(0, 3), encodePosition(4, 5), encodePosition(5, 3), encodePosition(2, 3));
        rotateFour(encodePosition(0, 6), encodePosition(4, 2), encodePosition(5, 6), encodePosition(2, 6));
        // BAND (M')
        rotateFour(encodePosition(0, 7), encodePosition(4, 1), encodePosition(5, 7), encodePosition(2, 7));
        rotateFour(encodePosition(0, 4), encodePosition(4, 4), encodePosition(5, 4), encodePosition(2, 4));
        rotateFour(encodePosition(0, 1), encodePosition(4, 7), encodePosition(5, 1), encodePosition(2, 1));
        return;
      case y:
        // U FACE
        rotateFour(encodePosition(0, 0), encodePosition(0, 2), encodePosition(0, 8), encodePosition(0, 6));
        rotateFour(encodePosition(0, 1), encodePosition(0, 5), encodePosition(0, 7), encodePosition(0, 3));
        // D' FACE
        rotateFour(encodePosition(5, 0), encodePosition(5, 6), encodePosition(5, 8), encodePosition(5, 2));
        rotateFour(encodePosition(5, 1), encodePosition(5, 3), encodePosition(5, 7), encodePosition(5, 5));
        // BAND (U)
        rotateFour(encodePosition(1, 2), encodePosition(4, 2), encodePosition(3, 2), encodePosition(2, 2));
        rotateFour(encodePosition(1, 1), encodePosition(4, 1), encodePosition(3, 1), encodePosition(2, 1));
        rotateFour(encodePosition(1, 0), encodePosition(4, 0), encodePosition(3, 0), encodePosition(2, 0));
        // BAND (D')
        rotateFour(encodePosition(1, 6), encodePosition(4, 6), encodePosition(3, 6), encodePosition(2, 6));
        rotateFour(encodePosition(1, 7), encodePosition(4, 7), encodePosition(3, 7), encodePosition(2, 7));
        rotateFour(encodePosition(1, 8), encodePosition(4, 8), encodePosition(3, 8), encodePosition(2, 8));
        // BAND (E')
        rotateFour(encodePosition(1, 3), encodePosition(4, 3), encodePosition(3, 3), encodePosition(2, 3));
        rotateFour(encodePosition(1, 4), encodePosition(4, 4), encodePosition(3, 4), encodePosition(2, 4));
        rotateFour(encodePosition(1, 5), encodePosition(4, 5), encodePosition(3, 5), encodePosition(2, 5));
        return;
      case z:
        // F FACE
        rotateFour(encodePosition(2, 0), encodePosition(2, 2), encodePosition(2, 8), encodePosition(2, 6));
        rotateFour(encodePosition(2, 1), encodePosition(2, 5), encodePosition(2, 7), encodePosition(2, 3));
        // B' FACE
        rotateFour(encodePosition(4, 6), encodePosition(4, 8), encodePosition(4, 2), encodePosition(4, 0));
        rotateFour(encodePosition(4, 3), encodePosition(4, 7), encodePosition(4, 5), encodePosition(4, 1));
        // BAND (F)
        rotateFour(encodePosition(0, 6), encodePosition(3, 0), encodePosition(5, 2), encodePosition(1, 8));
        rotateFour(encodePosition(0, 7), encodePosition(3, 3), encodePosition(5, 1), encodePosition(1, 5));
        rotateFour(encodePosition(0, 8), encodePosition(3, 6), encodePosition(5, 0), encodePosition(1, 2));
        // BAND (B')
        rotateFour(encodePosition(3, 8), encodePosition(5, 6), encodePosition(1, 0), encodePosition(0, 2));
        rotateFour(encodePosition(3, 5), encodePosition(5, 7), encodePosition(1, 3), encodePosition(0, 1));
        rotateFour(encodePosition(3, 2), encodePosition(5, 8), encodePosition(1, 6), encodePosition(0, 0));
        // BAND (S)
        rotateFour(encodePosition(0, 3), encodePosition(3, 1), encodePosition(5, 5), encodePosition(1, 7));
        rotateFour(encodePosition(0, 4), encodePosition(3, 4), encodePosition(5, 4), encodePosition(1, 4));
        rotateFour(encodePosition(0, 5), encodePosition(3, 7), encodePosition(5, 3), encodePosition(1, 1));
        return;
      default:
        throw new IllegalArgumentException("Not a basic turn: " + t);
    }
  }

  private void rotateFour(int position1, int position2, int position3, int position4) {
    Color buffer = state[getFace(position1)][getFacePosition(position1)];
    state[getFace(position1)][getFacePosition(position1)] = state[getFace(position4)][getFacePosition(position4)];
    state[getFace(position4)][getFacePosition(position4)] = state[getFace(position3)][getFacePosition(position3)];
    state[getFace(position3)][getFacePosition(position3)] = state[getFace(position2)][getFacePosition(position2)];
    state[getFace(position2)][getFacePosition(position2)] = buffer;
  }

  @Override
  public String toString() {
    // 2-side PLL String
    // return printCube(0b000000111000000111000000000000000000111111111L);
    return CubePrinter.stringify(this);
  }

  private String getState(long mask, int face, int facePosition) {
    return (mask >> encodePosition(face, facePosition) & 1) == 0
      ? " X " : state[face][facePosition].toString();
  }

  private static final int encodePosition(int face, int facePosition) {
    return face * 9 + facePosition;
  }

  private static final int getFace(int encodedPosition) {
    return encodedPosition / 9;
  }

  private static final int getFacePosition(int encodedPosition) {
    return encodedPosition % 9;
  }
}
