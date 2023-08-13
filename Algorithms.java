import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Algorithms {
  static List<Turn> compile(String notation) {
    return Stream.of(notation.trim().split("\\s+"))
      .map(Turn::fromString)
      .collect(toList());
  }
}
