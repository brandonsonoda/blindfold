import java.util.List;
import java.util.Arrays;

/**
 * Cube implementation that tracks stickered pieces using the Speffz scheme.
 *
 * See https://www.speedsolving.com/wiki/index.php?title=Speffz
 */
class StickeredCube implements Cube {
  private static final String EMPTY_SLOT = "         ";
  private static final String SINGLE_FACE =  "%s%s%s";

  // Tracking of the minimal number of edge stickers
  // {A, B, C, D, F, J, N, R, U, V, W, X}
  private final EdgeSticker[] edges = new EdgeSticker[12];
  // Tracking of the minimal number of corner stickers
  // {A, B, C, D, U, V, W, X}
  private final CornerSticker[] corners = new CornerSticker[8];

  public StickeredCube() {
    this.setCubeToSolved();
  }

  @Override
  public void setCubeToSolved() {
    edges[0] = EdgeSticker.A_EDGE;
    edges[1] = EdgeSticker.B_EDGE;
    edges[2] = EdgeSticker.C_EDGE;
    edges[3] = EdgeSticker.D_EDGE;
    edges[4] = EdgeSticker.F_EDGE;
    edges[5] = EdgeSticker.J_EDGE;
    edges[6] = EdgeSticker.N_EDGE;
    edges[7] = EdgeSticker.R_EDGE;
    edges[8] = EdgeSticker.U_EDGE;
    edges[9] = EdgeSticker.V_EDGE;
    edges[10] = EdgeSticker.W_EDGE;
    edges[11] = EdgeSticker.X_EDGE;

    corners[0] = CornerSticker.A_CORNER;
    corners[1] = CornerSticker.B_CORNER;
    corners[2] = CornerSticker.C_CORNER;
    corners[3] = CornerSticker.D_CORNER;
    corners[4] = CornerSticker.U_CORNER;
    corners[5] = CornerSticker.V_CORNER;
    corners[6] = CornerSticker.W_CORNER;
    corners[7] = CornerSticker.X_CORNER;
  }

  @Override
  public boolean isCubeSolved() {
    return this.serialize().equals("ABCDFJNRUVWXabcduvwx");
  }

  @Override
  public void apply(Turn t) {
    switch (t) {
      case U:
        updateCorners(
            new CornerUpdate(0, corners[3]),
            new CornerUpdate(1, corners[0]),
            new CornerUpdate(2, corners[1]),
            new CornerUpdate(3, corners[2]));
        updateEdges(
            new EdgeUpdate(0, edges[3]),
            new EdgeUpdate(1, edges[0]),
            new EdgeUpdate(2, edges[1]),
            new EdgeUpdate(3, edges[2]));
        return;
      case R:
        updateCorners(
            new CornerUpdate(1, rotateCw(corners[2])),
            new CornerUpdate(6, rotateCcw(corners[1])),
            new CornerUpdate(5, rotateCw(corners[6])),
            new CornerUpdate(2, rotateCcw(corners[5])));
        updateEdges(
            new EdgeUpdate(1, edges[5]),
            new EdgeUpdate(6, flip(edges[1])),
            new EdgeUpdate(9, flip(edges[6])),
            new EdgeUpdate(5, edges[9]));
        return;
      case F:
        updateCorners(
            new CornerUpdate(3, rotateCcw(corners[4])),
            new CornerUpdate(2, rotateCw(corners[3])),
            new CornerUpdate(5, rotateCcw(corners[2])),
            new CornerUpdate(4, rotateCw(corners[5])));
        updateEdges(
            new EdgeUpdate(2, edges[4]),
            new EdgeUpdate(4, edges[8]),
            new EdgeUpdate(8, flip(edges[5])),
            new EdgeUpdate(5, flip(edges[2])));
        return;
      case L:
        updateCorners(
            new CornerUpdate(0, rotateCcw(corners[7])),
            new CornerUpdate(7, rotateCw(corners[4])),
            new CornerUpdate(4, rotateCcw(corners[3])),
            new CornerUpdate(3, rotateCw(corners[0])));
        updateEdges(
            new EdgeUpdate(3, edges[7]),
            new EdgeUpdate(7, edges[11]),
            new EdgeUpdate(11, flip(edges[4])),
            new EdgeUpdate(4, flip(edges[3])));
        return;
      case B:
        updateCorners(
            new CornerUpdate(0, rotateCw(corners[1])),
            new CornerUpdate(1, rotateCcw(corners[6])),
            new CornerUpdate(6, rotateCw(corners[7])),
            new CornerUpdate(7, rotateCcw(corners[0])));
        updateEdges(
            new EdgeUpdate(0, edges[6]),
            new EdgeUpdate(6, edges[10]),
            new EdgeUpdate(10, flip(edges[7])),
            new EdgeUpdate(7, flip(edges[0])));
        return;
      case D:
        updateCorners(
            new CornerUpdate(4, corners[7]),
            new CornerUpdate(7, corners[6]),
            new CornerUpdate(6, corners[5]),
            new CornerUpdate(5, corners[4]));
        updateEdges(
            new EdgeUpdate(8, edges[11]),
            new EdgeUpdate(11, edges[10]),
            new EdgeUpdate(10, edges[9]),
            new EdgeUpdate(9, edges[8]));
        return;
      case x:
      case y:
      case z:
      default:
        throw new IllegalArgumentException("Not a basic turn: " + t);
    }
  }

  @Override
  public String toString() {
    return printCube(-1);
  }

  @Override
  public String printCube(long mask) {
    return
      // TOP FACE
      EMPTY_SLOT + getRow(CornerSticker.A_CORNER, EdgeSticker.A_EDGE, CornerSticker.B_CORNER) + "\n" +
      EMPTY_SLOT + getRow(EdgeSticker.D_EDGE, Color.WHITE, EdgeSticker.B_EDGE) + "\n" + 
      EMPTY_SLOT + getRow(CornerSticker.D_CORNER, EdgeSticker.C_EDGE, CornerSticker.C_CORNER) + "\n" +
      //  Middle Band 1
      getRow(CornerSticker.E_CORNER, EdgeSticker.E_EDGE, CornerSticker.F_CORNER) +
      getRow(CornerSticker.I_CORNER, EdgeSticker.I_EDGE, CornerSticker.J_CORNER) +
      getRow(CornerSticker.M_CORNER, EdgeSticker.M_EDGE, CornerSticker.N_CORNER) +
      getRow(CornerSticker.Q_CORNER, EdgeSticker.Q_EDGE, CornerSticker.R_CORNER) + "\n" +
      //  Middle Band 2
      getRow(EdgeSticker.H_EDGE, Color.ORANGE, EdgeSticker.F_EDGE) + 
      getRow(EdgeSticker.L_EDGE, Color.GREEN, EdgeSticker.J_EDGE) + 
      getRow(EdgeSticker.P_EDGE, Color.RED, EdgeSticker.N_EDGE) + 
      getRow(EdgeSticker.T_EDGE, Color.BLUE, EdgeSticker.R_EDGE) + "\n" + 
      //  Middle Band 3
      getRow(CornerSticker.H_CORNER, EdgeSticker.G_EDGE, CornerSticker.G_CORNER) +
      getRow(CornerSticker.L_CORNER, EdgeSticker.K_EDGE, CornerSticker.K_CORNER) +
      getRow(CornerSticker.P_CORNER, EdgeSticker.O_EDGE, CornerSticker.O_CORNER) +
      getRow(CornerSticker.T_CORNER, EdgeSticker.S_EDGE, CornerSticker.S_CORNER) + "\n" +
      // BOTTOM FACE
      EMPTY_SLOT + getRow(CornerSticker.U_CORNER, EdgeSticker.U_EDGE, CornerSticker.V_CORNER) + "\n" +
      EMPTY_SLOT + getRow(EdgeSticker.X_EDGE, Color.YELLOW, EdgeSticker.V_EDGE) + "\n" + 
      EMPTY_SLOT + getRow(CornerSticker.X_CORNER, EdgeSticker.W_EDGE, CornerSticker.W_CORNER);
  }

  public String serialize() {
    StringBuilder serializedCube = new StringBuilder();
    Arrays.stream(edges).forEachOrdered(edge -> serializedCube.append(edge.serializedCharacter));
    Arrays.stream(corners).forEachOrdered(corner -> serializedCube.append(corner.serializedCharacter));
    return serializedCube.toString();
  }

  private EdgeSticker getEdgeSticker(EdgeSticker homeSticker) {
    switch (homeSticker) {
      case A_EDGE:
        return edges[0];
      case B_EDGE:
        return edges[1];
      case C_EDGE:
        return edges[2];
      case D_EDGE:
        return edges[3];
      case F_EDGE:
        return edges[4];
      case J_EDGE:
        return edges[5];
      case N_EDGE:
        return edges[6];
      case R_EDGE:
        return edges[7];
      case U_EDGE:
        return edges[8];
      case V_EDGE:
        return edges[9];
      case W_EDGE:
        return edges[10];
      case X_EDGE:
        return edges[11];
    }

    return flip(getEdgeSticker(flip(homeSticker)));
  }

  private CornerSticker getCornerSticker(CornerSticker homeSticker) {
    switch (homeSticker) {
      case A_CORNER:
        return corners[0];
      case B_CORNER:
        return corners[1];
      case C_CORNER:
        return corners[2];
      case D_CORNER:
        return corners[3];
      case U_CORNER:
        return corners[4];
      case V_CORNER:
        return corners[5];
      case W_CORNER:
        return corners[6];
      case X_CORNER:
        return corners[7];
    }

    return rotateCcw(getCornerSticker(rotateCw(homeSticker)));
  }

  private enum EdgeSticker {
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

  private enum CornerSticker {
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

    final char serializedCharacter;
    final Color color;

    CornerSticker(char serializedCharacter, Color color) {
      this.serializedCharacter = serializedCharacter;
      this.color = color;
    }
  }

  private static EdgeSticker flip(EdgeSticker original) {
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

  private static CornerSticker rotateCw(CornerSticker original) {
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

  private static CornerSticker rotateCcw(CornerSticker original) {
    return rotateCw(rotateCw(original));
  }

  private String getRow(CornerSticker firstCorner, EdgeSticker edgeSticker, CornerSticker secondCorner) {
    return String.format(SINGLE_FACE, getCornerSticker(firstCorner).color.toString(),
        getEdgeSticker(edgeSticker).color.toString(),
        getCornerSticker(secondCorner).color.toString());
  }

  private String getRow(EdgeSticker firstEdge, Color center, EdgeSticker secondEdge) {
    return String.format(SINGLE_FACE, getEdgeSticker(firstEdge).color.toString(),
        center.toString(),
        getEdgeSticker(secondEdge).color.toString());
  }

  private void updateEdges(EdgeUpdate... edgeUpdates) {
    for (EdgeUpdate edgeUpdate : edgeUpdates) {
      edges[edgeUpdate.index] = edgeUpdate.newSticker;
    }
  }

  private void updateCorners(CornerUpdate... cornerUpdates) {
    for (CornerUpdate cornerUpdate : cornerUpdates) {
      corners[cornerUpdate.index] = cornerUpdate.newSticker;
    }
  }

  private static class EdgeUpdate {
    final int index;
    final EdgeSticker newSticker;

    EdgeUpdate(int index, EdgeSticker newSticker) {
      this.index = index;
      this.newSticker = newSticker;
    }
  }

  private static class CornerUpdate {
    final int index;
    final CornerSticker newSticker;

    CornerUpdate(int index, CornerSticker newSticker) {
      this.index = index;
      this.newSticker = newSticker;
    }
  }
}
