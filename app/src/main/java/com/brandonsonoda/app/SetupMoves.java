package com.brandonsonoda.app;

import com.brandonsonoda.model.Turn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SetupMoves {
  static final HashMap<String, List<Turn>> EDGE_SETUPS;
  static final HashMap<String, List<Turn>> CORNER_SETUPS;
  private static final List<Turn> EMPTY = new ArrayList<>();

  static {
    EDGE_SETUPS = new HashMap<>();
    EDGE_SETUPS.put("A", Algorithms.compile("l2 D' l2"));
    EDGE_SETUPS.put("C", Algorithms.compile("l2 D l2"));
    EDGE_SETUPS.put("D", EMPTY);
    EDGE_SETUPS.put("E", Algorithms.compile("L d' L"));
    EDGE_SETUPS.put("F", Algorithms.compile("d' L"));
    EDGE_SETUPS.put("G", Algorithms.compile("L' d' L"));
    EDGE_SETUPS.put("H", Algorithms.compile("d L'"));
    EDGE_SETUPS.put("I", Algorithms.compile("l D' L2"));
    EDGE_SETUPS.put("J", Algorithms.compile("d2 L"));
    EDGE_SETUPS.put("K", Algorithms.compile("l D L2"));
    EDGE_SETUPS.put("L", Algorithms.compile("L'"));
    EDGE_SETUPS.put("N", Algorithms.compile("d L"));
    EDGE_SETUPS.put("O", Algorithms.compile("D' l D L2"));
    EDGE_SETUPS.put("P", Algorithms.compile("d' L'"));
    EDGE_SETUPS.put("Q", Algorithms.compile("l' D L2"));
    EDGE_SETUPS.put("R", Algorithms.compile("L"));
    EDGE_SETUPS.put("S", Algorithms.compile("l' D' L2"));
    EDGE_SETUPS.put("T", Algorithms.compile("d2 L'"));
    EDGE_SETUPS.put("U", Algorithms.compile("D' L2"));
    EDGE_SETUPS.put("V", Algorithms.compile("D2 L2"));
    EDGE_SETUPS.put("W", Algorithms.compile("D L2"));
    EDGE_SETUPS.put("X", Algorithms.compile("L2"));
    
    CORNER_SETUPS = new HashMap<>();
    CORNER_SETUPS.put("B", Algorithms.compile("R2"));
    CORNER_SETUPS.put("C", Algorithms.compile("F2 D"));
    CORNER_SETUPS.put("D", Algorithms.compile("F2"));
    CORNER_SETUPS.put("F", Algorithms.compile("F' D"));
    CORNER_SETUPS.put("G", Algorithms.compile("F'"));
    CORNER_SETUPS.put("H", Algorithms.compile("D' R"));
    CORNER_SETUPS.put("I", Algorithms.compile("F R'"));
    CORNER_SETUPS.put("J", Algorithms.compile("R'"));
    CORNER_SETUPS.put("K", Algorithms.compile("F' R'"));
    CORNER_SETUPS.put("L", Algorithms.compile("F2 R'"));
    CORNER_SETUPS.put("M", Algorithms.compile("F"));
    CORNER_SETUPS.put("N", Algorithms.compile("R' F"));
    CORNER_SETUPS.put("O", Algorithms.compile("R2 F"));
    CORNER_SETUPS.put("P", Algorithms.compile("R F"));
    CORNER_SETUPS.put("Q", Algorithms.compile("R D'"));
    CORNER_SETUPS.put("S", Algorithms.compile("D F'"));
    CORNER_SETUPS.put("T", Algorithms.compile("R"));
    CORNER_SETUPS.put("U", Algorithms.compile("D"));
    CORNER_SETUPS.put("V", EMPTY);
    CORNER_SETUPS.put("W", Algorithms.compile("D'"));
    CORNER_SETUPS.put("X", Algorithms.compile("D2"));
  }
}
