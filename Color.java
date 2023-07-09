  enum Color {
    WHITE(0),
    ORANGE(1),
    GREEN(2),
    RED(3),
    BLUE(4),
    YELLOW(5);

    final int code;

    Color(int code) {
      this.code = code;
    }

    public String toString() {
      return String.format("%d", code);
    }
  }
