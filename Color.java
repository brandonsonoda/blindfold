  enum Color {
   WHITE(0, "\u001B[37m"),
   ORANGE(1, "\u001B[38;2;255;88;0m"),
   GREEN(2, "\u001B[38;2;0;155;72m"),
   RED(3, "\u001B[38;2;183;18;52m"),
   BLUE(4, "\u001B[38;2;0;70;173m"),
   YELLOW(5, "\u001B[38;2;255;210;0m");

   public static final String ANSI_RESET = "\u001B[0m";

   final int code;
   final String ansiCode;

   enum PrintMode {
     TEXT, COLOR
   }

   private static PrintMode printMode = PrintMode.COLOR;

    Color(int code, String ansiCode) {
      this.code = code;
      this.ansiCode = ansiCode;
    }

    public String toString() {
      return
        printMode == PrintMode.TEXT
        ? String.format("%s[%d]", ANSI_RESET, code)
        : String.format(" %s■%s ", ansiCode, ANSI_RESET);
    }

    static void setPrintMode(PrintMode printMode) {
      Color.printMode = printMode;
    }
  }
