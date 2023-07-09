import java.util.Scanner;

public class Main {
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
        Blindfold.apply(c, line.substring(BLIND_PREFIX.length()));
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
}
