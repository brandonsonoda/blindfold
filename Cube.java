import java.util.stream.Stream;
import java.util.Iterator;

class Cube {
  private static final String EMPTY_SLOT = "         ";
  private static final String SINGLE_FACE =  "[%s][%s][%s]";
  private Color[][] state = new Color[6][9]; //nice

  public Cube() {
    setCubeToSolved();
  }

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

  public boolean isCubeSolved() {
    return Stream.of(state)
      .allMatch(face -> Stream.of(face)
          .allMatch(color -> color == face[0]));
  }

  public void apply(Turn... turns) {
    for (Turn turn : turns) {
      for (Turn basicTurn : turn.basicTurns) {
        applySimple(basicTurn);
      }
    }
  }

  public void undo(Turn... turns) {
    for (int i = turns.length - 1; i >= 0; i--) {
      this.apply(turns[i].reverse());
    }
  }

  public void applySimple(Turn t) {
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

  private static String printRow(Color a, Color b, Color c) {
    return String.format("[%s][%s][%s]", a.code, b.code, c.code);
  }

  public String toString() {
    return
      // TOP FACE
      EMPTY_SLOT + String.format(SINGLE_FACE, state[0][0], state[0][1], state[0][2]) + "\n" +
      EMPTY_SLOT + String.format(SINGLE_FACE, state[0][3], state[0][4], state[0][5]) + "\n" + 
      EMPTY_SLOT + String.format(SINGLE_FACE, state[0][6], state[0][7], state[0][8]) + "\n" +
      //  Middle Band 1
      String.format(SINGLE_FACE, state[1][0], state[1][1], state[1][2]) +
      String.format(SINGLE_FACE, state[2][0], state[2][1], state[2][2]) +
      String.format(SINGLE_FACE, state[3][0], state[3][1], state[3][2]) + 
      String.format(SINGLE_FACE, state[4][0], state[4][1], state[4][2]) +  "\n" +
      //  Middle Band 2
      String.format(SINGLE_FACE, state[1][3], state[1][4], state[1][5]) +
      String.format(SINGLE_FACE, state[2][3], state[2][4], state[2][5]) +
      String.format(SINGLE_FACE, state[3][3], state[3][4], state[3][5]) +
      String.format(SINGLE_FACE, state[4][3], state[4][4], state[4][5]) + "\n" + 
      //  Middle Band 2
      String.format(SINGLE_FACE, state[1][6], state[1][7], state[1][8]) +
      String.format(SINGLE_FACE, state[2][6], state[2][7], state[2][8]) +
      String.format(SINGLE_FACE, state[3][6], state[3][7], state[3][8]) +
      String.format(SINGLE_FACE, state[4][6], state[4][7], state[4][8]) +  "\n" +
      // BOTTOM FACE
      EMPTY_SLOT + String.format(SINGLE_FACE, state[5][0], state[5][1], state[5][2]) + "\n" +
      EMPTY_SLOT + String.format(SINGLE_FACE, state[5][3], state[5][4], state[5][5]) + "\n" + 
      EMPTY_SLOT + String.format(SINGLE_FACE, state[5][6], state[5][7], state[5][8]);
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
