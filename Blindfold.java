import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Iterator;
import java.util.Scanner;

// Blind fold notation
// Scramble, Blindfold notation
public class Blindfold {
  private static final String BLIND_PREFIX = "blind: ";
  public static void main(String[] args) {
    Cube c = new Cube();
    Scanner scanner = new Scanner(System.in);
    while (sneakyPrint() && scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.equalsIgnoreCase("print") || line.equalsIgnoreCase("ls")) {
        System.out.println(c);
        continue;
      }

      if (line.equalsIgnoreCase("clear")) {
        c.setCubeToSolved();
        continue;
      }

      if (line.equalsIgnoreCase("is_solved")) {
        System.out.println(c.isCubeSolved());
        continue;
      }

      if (line.toLowerCase().startsWith(BLIND_PREFIX.toLowerCase())) {
        blindfoldSolve(c, line.substring(BLIND_PREFIX.length()));
        continue;
      }

      try {
        c.apply(Algorithms.compile(line));
      } catch (IllegalArgumentException e) {
        System.out.println("invalid notation");
      }
    }
    System.out.println();
  }

  private static boolean sneakyPrint() {
    System.out.print(">: ");
    return true;
  }

  public static void blindfoldSolve(Cube c, String memo) {
    String[] split = memo.trim().split("\\s+");
    if (split.length > 3) {
      throw new IllegalArgumentException("Too many arguments to blind!");
    }

    String cornerMemo = "";
    String edgeMemo = "";
    boolean parity = false;

    if (split.length != 2 && split.length != 3) {
      throw new IllegalArgumentException("Must contain 2 or 3 arguments");
    }

    if (split.length == 2) {
      cornerMemo = split[0];
      edgeMemo = split[1];
    }

    if (split.length == 3) {
      if (!split[1].equals("#")) {
        throw new IllegalArgumentException("3 Argument memo must contain # as second arg");
      }
      parity = true;
      cornerMemo = split[0];
      edgeMemo = split[2];
    }

    System.out.printf("Memo: corners:%s, edges:%s, hasParity:%b%n", cornerMemo, edgeMemo, parity);
    for (char edgeChar : edgeMemo.toCharArray()) {
      String edge = "" + edgeChar;
      c.apply(SetupMoves.EDGE_SETUPS.get(edge));
      c.apply(Algorithms.T_PERM);
      c.undo(SetupMoves.EDGE_SETUPS.get(edge));
    }

    if (parity) {
      c.apply(Algorithms.PARITY_ALGORITHM);
    }

    for (char cornerChar : cornerMemo.toCharArray()) {
      String corner = "" + cornerChar;
      c.apply(SetupMoves.CORNER_SETUPS.get(corner));
      c.apply(Algorithms.CORNER_SWAP);
      c.undo(SetupMoves.CORNER_SETUPS.get(corner));
    }

  }
}
