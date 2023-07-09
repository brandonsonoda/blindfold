import java.util.stream.Stream;

public class Algorithms {
  public static final Turn[] F_PERM = compile("R' U' F' R U R' U' R' F R2 U' R' U' R U R' U R");
  public static final Turn[] T_PERM = compile("R U R' U' R' F R2 U' R' U' R U R' F'");
  public static final Turn[] CORNER_SWAP = compile("R U' R' U' R U R' F' R U R' U' R' F R");
  public static final Turn[] PARITY_ALGORITHM = compile("R U R' F' R U2 R' U2 R' F R U R U2 R' U'");
  public static final Turn[] Z_PERM = compile("R' U' R2 U R U R' U' R U R U' R U' R'");

  static Turn[] compile(String notation) {
    return Stream.of(notation.trim().split("\\s+"))
      .map(Turn::fromString)
      .toArray(Turn[]::new);
  }
}
