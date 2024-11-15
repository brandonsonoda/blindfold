package com.brandonsonoda.app;

class CubePrinter {
  private static final String EMPTY_SLOT = "         ";
  private static final String SINGLE_FACE =  "%s%s%s";

  // TODO: support masks
  static String stringify(Cube cube) {
    return
      // TOP FACE
      EMPTY_SLOT + getRow(cube, CornerSticker.A_CORNER, EdgeSticker.A_EDGE, CornerSticker.B_CORNER) + "\n" +
      EMPTY_SLOT + getRow(cube, EdgeSticker.D_EDGE, Face.UP, EdgeSticker.B_EDGE) + "\n" + 
      EMPTY_SLOT + getRow(cube, CornerSticker.D_CORNER, EdgeSticker.C_EDGE, CornerSticker.C_CORNER) + "\n" +
      //  Middle Band 1
      getRow(cube, CornerSticker.E_CORNER, EdgeSticker.E_EDGE, CornerSticker.F_CORNER) +
      getRow(cube, CornerSticker.I_CORNER, EdgeSticker.I_EDGE, CornerSticker.J_CORNER) +
      getRow(cube, CornerSticker.M_CORNER, EdgeSticker.M_EDGE, CornerSticker.N_CORNER) +
      getRow(cube, CornerSticker.Q_CORNER, EdgeSticker.Q_EDGE, CornerSticker.R_CORNER) + "\n" +
      //  Middle Band 2
      getRow(cube, EdgeSticker.H_EDGE, Face.LEFT, EdgeSticker.F_EDGE) + 
      getRow(cube, EdgeSticker.L_EDGE, Face.FRONT, EdgeSticker.J_EDGE) + 
      getRow(cube, EdgeSticker.P_EDGE, Face.RIGHT, EdgeSticker.N_EDGE) + 
      getRow(cube, EdgeSticker.T_EDGE, Face.BACK, EdgeSticker.R_EDGE) + "\n" + 
      //  Middle Band 3
      getRow(cube, CornerSticker.H_CORNER, EdgeSticker.G_EDGE, CornerSticker.G_CORNER) +
      getRow(cube, CornerSticker.L_CORNER, EdgeSticker.K_EDGE, CornerSticker.K_CORNER) +
      getRow(cube, CornerSticker.P_CORNER, EdgeSticker.O_EDGE, CornerSticker.O_CORNER) +
      getRow(cube, CornerSticker.T_CORNER, EdgeSticker.S_EDGE, CornerSticker.S_CORNER) + "\n" +
      // BOTTOM FACE
      EMPTY_SLOT + getRow(cube, CornerSticker.U_CORNER, EdgeSticker.U_EDGE, CornerSticker.V_CORNER) + "\n" +
      EMPTY_SLOT + getRow(cube, EdgeSticker.X_EDGE, Face.DOWN, EdgeSticker.V_EDGE) + "\n" + 
      EMPTY_SLOT + getRow(cube, CornerSticker.X_CORNER, EdgeSticker.W_EDGE, CornerSticker.W_CORNER);
  }

  private static String getRow(Cube cube, CornerSticker firstCorner, EdgeSticker edgeSticker, CornerSticker secondCorner) {
    return String.format(SINGLE_FACE, cube.getStickerColor(firstCorner).toString(),
        cube.getStickerColor(edgeSticker).toString(),
        cube.getStickerColor(secondCorner).toString());
  }

  private static String getRow(Cube cube, EdgeSticker firstEdge, Face centerFace, EdgeSticker secondEdge) {
    return String.format(SINGLE_FACE, cube.getStickerColor(firstEdge).toString(),
        cube.getStickerColor(centerFace).toString(),
        cube.getStickerColor(secondEdge).toString());
  }
}
