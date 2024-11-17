package com.brandonsonoda.app;

import com.brandonsonoda.model.Color;
import com.brandonsonoda.model.CornerSticker;
import com.brandonsonoda.model.EdgeSticker;
import com.brandonsonoda.model.EdgeStickers;
import com.brandonsonoda.model.Face;
import com.brandonsonoda.model.FixedRotationPieceCube;
import com.brandonsonoda.model.Cube;

class PetrusAnalyzer {
    private static final String A_CORNER_2x2 = "A..D...R....a.......";
    private static final String B_CORNER_2x2 = "AB....N......b......";
    private static final String C_CORNER_2x2 = ".BC..J........c.....";
    private static final String D_CORNER_2x2 = "..CDF..........d....";
    private static final String U_CORNER_2x2 = "....F...U..X....u...";
    private static final String V_CORNER_2x2 = ".....J..UV.......v..";
    private static final String W_CORNER_2x2 = "......N..VW,......w.";
    private static final String X_CORNER_2x2 = ".......R..WX.......x";

  enum Step {
    UNKNOWN,
    NO_PROGRESS,
    SMALL_BLOCK_BUILT,
    LARGE_BLOCK_BUILT,
    EDGES_ORIENTED,
    F2L_COMPLETE,
    OLL_COMPLETE,
    SOLVED;
  }
  
  static Step getStep(FixedRotationPieceCube cube) {
    if (cube.isCubeSolved()) {
      return Step.SOLVED;
    }

    Step largeBlockBuiltOrEo = checkLargeBlockBuiltAndEo(cube);
    Step f2lOrOll = checkF2lAndOll(cube);
    if (largeBlockBuiltOrEo == Step.EDGES_ORIENTED && f2lOrOll != Step.UNKNOWN) {
      return f2lOrOll;
    }

    if (largeBlockBuiltOrEo != Step.UNKNOWN) {
      return largeBlockBuiltOrEo;
    }

    if (smallBlockBuilt(cube)) {
      return Step.SMALL_BLOCK_BUILT;
    }

    return Step.NO_PROGRESS;
  }

  private static boolean smallBlockBuilt(FixedRotationPieceCube cube) {
    String serializedCube = cube.serialize();

    return serializedCube.matches(A_CORNER_2x2)
      || serializedCube.matches(B_CORNER_2x2)
      || serializedCube.matches(C_CORNER_2x2)
      || serializedCube.matches(D_CORNER_2x2)
      || serializedCube.matches(U_CORNER_2x2)
      || serializedCube.matches(V_CORNER_2x2)
      || serializedCube.matches(W_CORNER_2x2)
      || serializedCube.matches(X_CORNER_2x2);
  }

  private static Step checkLargeBlockBuiltAndEo(FixedRotationPieceCube cube) {
    String serializedCube = cube.serialize();
    // Sorted according to shared edge
    if (serializedCube.matches(A_CORNER_2x2) && serializedCube.matches(B_CORNER_2x2)) { 
      // A edge (check green and yellow faces for EO)
      return checkEo(cube, Face.FRONT, Face.DOWN) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(B_CORNER_2x2) && serializedCube.matches(C_CORNER_2x2)) { 
      // B edge (check yellow and orange faces for EO)
      return checkEo(cube, Face.DOWN, Face.LEFT) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(C_CORNER_2x2) && serializedCube.matches(D_CORNER_2x2)) { 
      // C edge (check yellow and blue faces for EO)
      return checkEo(cube, Face.DOWN, Face.BACK) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(A_CORNER_2x2) && serializedCube.matches(D_CORNER_2x2)) { 
      // D edge (check yellow and red faces for EO)
      return checkEo(cube, Face.DOWN, Face.RIGHT) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(D_CORNER_2x2) && serializedCube.matches(U_CORNER_2x2)) { 
      // F edge (check red and blue faces for EO)
      return checkEo(cube, Face.RIGHT, Face.BACK) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(C_CORNER_2x2) && serializedCube.matches(V_CORNER_2x2)) { 
      // J edge (check blue and orange faces for EO)
      return checkEo(cube, Face.BACK, Face.LEFT) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(B_CORNER_2x2) && serializedCube.matches(W_CORNER_2x2)) { 
      // N edge (check orange and green faces for EO)
      return checkEo(cube, Face.LEFT, Face.FRONT) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(A_CORNER_2x2) && serializedCube.matches(X_CORNER_2x2)) { 
      // R edge (check green and red faces for EO)
      return checkEo(cube, Face.FRONT, Face.RIGHT) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(U_CORNER_2x2) && serializedCube.matches(V_CORNER_2x2)) { 
      // U edge (check white and blue faces for EO)
      return checkEo(cube, Face.UP, Face.BACK) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(V_CORNER_2x2) && serializedCube.matches(W_CORNER_2x2)) { 
      // V edge (check white and orange faces for EO)
      return checkEo(cube, Face.UP, Face.LEFT) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(W_CORNER_2x2) && serializedCube.matches(X_CORNER_2x2)) { 
      // W edge (check white and green faces for EO)
      return checkEo(cube, Face.UP, Face.FRONT) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    if (serializedCube.matches(X_CORNER_2x2) && serializedCube.matches(U_CORNER_2x2)) { 
      // X edge (check white and red faces for EO)
      return checkEo(cube, Face.UP, Face.RIGHT) ? Step.EDGES_ORIENTED : Step.LARGE_BLOCK_BUILT;
    }

    return Step.UNKNOWN;
  }

  private static boolean checkEo(Cube cube, Face face1, Face face2) {
    Color color1 = cube.getStickerColor(face1);
    Color color2 = cube.getStickerColor(face2);

    return !faceHasBadEdges(cube, face1, color1, color2)
      && !faceHasBadEdges(cube, face2, color2, color1);
  }

  /**
    * Check if a face has bad edges.
    *
    * An edge is bad:
    * - if the cubie has the color of the face on it, but is not facing up relative to the given face
    * - if the cubie has the color of the other face on it, but is facing up relative to the given face
    */
  private static boolean faceHasBadEdges(Cube cube, Face thisFace, Color thisColor, Color otherColor) {
    return EdgeStickers.getEdgesOnFace(thisFace)
      .stream()
      .map(cube::getSticker)
      .anyMatch(sticker -> {
          return EdgeStickers.flip(sticker).color == thisColor || sticker.color == otherColor;
      });
  }

  private static Step checkF2lAndOll(FixedRotationPieceCube cube) {
    // Check all faces + top layer
    return Step.UNKNOWN;
  }
}
