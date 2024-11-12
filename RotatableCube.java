/**
 * Wrapper class around FixedCube that supports rotational moves.
 */
class RotatableCube implements Cube {
  private final FixedCube delegateCube;

  RotatableCube() {
    this.delegateCube = new FixedCube();
  }

  @Override
  public void setCubeToSolved() {
    delegateCube.setCubeToSolved();
  }

  @Override
  public boolean isCubeSolved() {
    return delegateCube.isCubeSolved();
  }

  @Override
  public void apply(Turn t) {
    // TODO: translate faces
    switch (t) {
      case x:
      case y:
      case z:
        throw new UnsupportedOperationException("RotatableCube does not support turn: " + t);
    }

    // TODO: will need a translation layer to handle cube rotations
    delegateCube.apply(t);
  }

  @Override
  public Color getStickerColor(EdgeSticker homeSticker) {
    return delegateCube.getStickerColor(getTranslatedSticker(homeSticker));
  }

  @Override
  public Color getStickerColor(CornerSticker homeSticker) {
    return delegateCube.getStickerColor(getTranslatedSticker(homeSticker));
  }

  @Override
  public Color getStickerColor(Face homeSticker) {
    return delegateCube.getStickerColor(getTranslatedSticker(homeSticker));
  }

  @Override
  public String toString() {
    return CubePrinter.stringify(this);
  }

  private EdgeSticker getTranslatedSticker(EdgeSticker homeSticker) {
    return homeSticker;
  }

  private CornerSticker getTranslatedSticker(CornerSticker homeSticker) {
    return homeSticker;
  }

  private Face getTranslatedSticker(Face homeSticker) {
    return homeSticker;
  }
}
