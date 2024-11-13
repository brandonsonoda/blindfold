import java.util.Scanner;

public class Main {
  private static final String BLIND_PREFIX = "blind: ";

  public static void main(String[] args) {
    Cube c = new RotatableCube();
    Scanner scanner = new Scanner(System.in);
    while (sneakyPrint() && scanner.hasNextLine()) {
      String line = scanner.nextLine();
      // Print the current state of the cube
      if (line.equalsIgnoreCase("print") || line.equalsIgnoreCase("ls")) {
        System.out.println(c);
        continue;
      }

      // Return the current cube to the solved position
      if (line.equalsIgnoreCase("clear")) {
        c.setCubeToSolved();
        continue;
      }

      // Switch to rendering the cube as colored squares
      if (line.equalsIgnoreCase("color")) {
        Color.setPrintMode(Color.PrintMode.COLOR);
        continue;
      }

      // Switch to rendering the cube as numbered squares
      if (line.equalsIgnoreCase("text")) {
        Color.setPrintMode(Color.PrintMode.TEXT);
        continue;
      }

      // Returns true if the cube is solved, false otherwise
      if (line.equalsIgnoreCase("is_solved")) {
        System.out.println(c.isCubeSolved());
        continue;
      }

      // Applies blind solving notation to the cube
      if (line.toLowerCase().startsWith(BLIND_PREFIX.toLowerCase())) {
        Blindfold.apply(c, line.substring(BLIND_PREFIX.length()));
        continue;
      }

      // Default apply notation to the cube
      try {
        c.apply(Algorithms.compile(line));
      } catch (IllegalArgumentException e) {
        System.out.println("invalid notation");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    System.out.println();
  }

  private static boolean sneakyPrint() {
    System.out.print(">: ");
    return true;
  }
}
