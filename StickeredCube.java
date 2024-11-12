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
            new CornerUpdate(1, CornerStickers.rotateCw(corners[2])),
            new CornerUpdate(6, CornerStickers.rotateCcw(corners[1])),
            new CornerUpdate(5, CornerStickers.rotateCw(corners[6])),
            new CornerUpdate(2, CornerStickers.rotateCcw(corners[5])));
        updateEdges(
            new EdgeUpdate(1, edges[5]),
            new EdgeUpdate(6, EdgeStickers.flip(edges[1])),
            new EdgeUpdate(9, EdgeStickers.flip(edges[6])),
            new EdgeUpdate(5, edges[9]));
        return;
      case F:
        updateCorners(
            new CornerUpdate(3, CornerStickers.rotateCcw(corners[4])),
            new CornerUpdate(2, CornerStickers.rotateCw(corners[3])),
            new CornerUpdate(5, CornerStickers.rotateCcw(corners[2])),
            new CornerUpdate(4, CornerStickers.rotateCw(corners[5])));
        updateEdges(
            new EdgeUpdate(2, edges[4]),
            new EdgeUpdate(4, edges[8]),
            new EdgeUpdate(8, EdgeStickers.flip(edges[5])),
            new EdgeUpdate(5, EdgeStickers.flip(edges[2])));
        return;
      case L:
        updateCorners(
            new CornerUpdate(0, CornerStickers.rotateCcw(corners[7])),
            new CornerUpdate(7, CornerStickers.rotateCw(corners[4])),
            new CornerUpdate(4, CornerStickers.rotateCcw(corners[3])),
            new CornerUpdate(3, CornerStickers.rotateCw(corners[0])));
        updateEdges(
            new EdgeUpdate(3, edges[7]),
            new EdgeUpdate(7, edges[11]),
            new EdgeUpdate(11, EdgeStickers.flip(edges[4])),
            new EdgeUpdate(4, EdgeStickers.flip(edges[3])));
        return;
      case B:
        updateCorners(
            new CornerUpdate(0, CornerStickers.rotateCw(corners[1])),
            new CornerUpdate(1, CornerStickers.rotateCcw(corners[6])),
            new CornerUpdate(6, CornerStickers.rotateCw(corners[7])),
            new CornerUpdate(7, CornerStickers.rotateCcw(corners[0])));
        updateEdges(
            new EdgeUpdate(0, edges[6]),
            new EdgeUpdate(6, edges[10]),
            new EdgeUpdate(10, EdgeStickers.flip(edges[7])),
            new EdgeUpdate(7, EdgeStickers.flip(edges[0])));
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
        throw new IllegalArgumentException("StickeredCube does not support turn: " + t);
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

    return EdgeStickers.flip(getEdgeSticker(EdgeStickers.flip(homeSticker)));
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

    return CornerStickers.rotateCcw(getCornerSticker(CornerStickers.rotateCw(homeSticker)));
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
