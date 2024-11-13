class Colors {
  static Color opposite(Color color) {
    switch (color) {
      case WHITE:
        return Color.YELLOW;
      case ORANGE:
        return Color.RED;
      case GREEN:
        return Color.BLUE;
      case RED:
        return Color.ORANGE;
      case BLUE:
        return Color.GREEN;
      case YELLOW:
        return Color.WHITE;
    }

    throw new IllegalStateException(String.format("Unexpected color: ", color));
  }
}
