package com.brandonsonoda.model;

import com.brandonsonoda.utilities.UnsupportedCaseException;

/**
 * Wrapper class around FixedRotationPieceCube that supports rotational moves.
 */
public class VariableRotationPieceCube extends Cube {
  private final FixedRotationPieceCube delegateCube;

  private Color upColor;
  private Color frontColor;

  public VariableRotationPieceCube(FixedRotationPieceCube delegateCube) {
    this.delegateCube = delegateCube;
    this.setCubeToSolved();
  }

  public VariableRotationPieceCube() {
    this(new FixedRotationPieceCube());
  }

  @Override
  public void setCubeToSolved() {
    delegateCube.setCubeToSolved();
    orient(delegateCube.getStickerColor(Face.UP), delegateCube.getStickerColor(Face.FRONT));
  }

  @Override
  public boolean isCubeSolved() {
    return delegateCube.isCubeSolved();
  }

  @Override
  void rotateCube(Face referenceFace) {
    switch (referenceFace) {
      case RIGHT:
        this.orient(getStickerColor(Face.FRONT), getStickerColor(Face.DOWN));
        return;
      case UP:
        this.orient(getStickerColor(Face.UP), getStickerColor(Face.RIGHT));
        return;
      case FRONT:
        this.orient(getStickerColor(Face.LEFT), getStickerColor(Face.FRONT));
        return;
    }

    throw new UnsupportedCaseException(referenceFace);
  }

  @Override
  void turnFace(Face face) {
    // Translate relative face to green-front white-top face
    Color relativeColor = getStickerColor(face);
    Face defaultFaceWithThatColor = Converters.defaultFace(relativeColor);
    delegateCube.turnFace(defaultFaceWithThatColor);
  }

  @Override
  public EdgeSticker getSticker(EdgeSticker homeSticker) {
    return delegateCube.getSticker(getTranslatedSticker(homeSticker));
  }

  @Override
  public CornerSticker getSticker(CornerSticker homeSticker) {
    return delegateCube.getSticker(getTranslatedSticker(homeSticker));
  }

  @Override
  public Color getStickerColor(Face homeSticker) {
    switch (homeSticker) {
      case UP:
        return upColor;
      case LEFT:
        return CornerStickers.rotateCw(CornerStickers.identifyCorner(upColor, frontColor)).color;
      case FRONT:
        return frontColor;
      case RIGHT:
        return CornerStickers.rotateCw(CornerStickers.identifyCorner(frontColor, upColor)).color;
      case BACK:
        return Colors.opposite(frontColor);
      case DOWN:
        return Colors.opposite(upColor);
    }

    throw new UnsupportedCaseException(homeSticker);
  }

  @Override
  public String toString() {
    return CubePrinter.stringify(this);
  }

  private void orient(Color upColor, Color frontColor) {
    this.upColor = upColor;
    this.frontColor = frontColor;
  }

  private EdgeSticker getTranslatedSticker(EdgeSticker homeSticker) {
    // Get the green-front white-top colors associated with this edge
    Color fixedPrimaryColor = homeSticker.color;
    Color fixedSecondaryColor = EdgeStickers.flip(homeSticker).color;

    // Convert this into the faces of the rotated cube
    Face primaryFace = Converters.defaultFace(fixedPrimaryColor);
    Face secondaryFace = Converters.defaultFace(fixedSecondaryColor);

    // Get the current colors of these faces
    Color primaryEdgeColor = getStickerColor(primaryFace);
    Color secondaryEdgeColor = getStickerColor(secondaryFace);

    // Identify the edge made of those colors
    return EdgeStickers.identifyEdge(primaryEdgeColor, secondaryEdgeColor);
  }

  private CornerSticker getTranslatedSticker(CornerSticker homeSticker) {
    // Get the green-front white-top colors associated with this corner
    Color fixedPrimaryColor = homeSticker.color;
    Color fixedSecondaryColor = CornerStickers.rotateCcw(homeSticker).color;

    // Convert this into the faces of the rotated cube
    Face primaryFace = Converters.defaultFace(fixedPrimaryColor);
    Face secondaryFace = Converters.defaultFace(fixedSecondaryColor);

    // Get the current colors of these faces
    Color primaryCornerColor = getStickerColor(primaryFace);
    Color secondaryCornerColor = getStickerColor(secondaryFace);

    // Identify the edge made of those colors
    return CornerStickers.identifyCorner(primaryCornerColor, secondaryCornerColor);
  }

  private Face getTranslatedSticker(Face homeSticker) {
    return Converters.defaultFace(getStickerColor(homeSticker));
  }
}
