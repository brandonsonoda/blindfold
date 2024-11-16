package com.brandonsonoda.model;

import java.util.function.Consumer;
import java.util.function.Function;

enum CubeMutation {
  U_TURN(cube -> cube::turnFace, Face.UP),
  L_TURN(cube -> cube::turnFace, Face.LEFT),
  F_TURN(cube -> cube::turnFace, Face.FRONT),
  R_TURN(cube -> cube::turnFace, Face.RIGHT),
  B_TURN(cube -> cube::turnFace, Face.BACK),
  D_TURN(cube -> cube::turnFace, Face.DOWN),
  X_ROTATION(cube -> cube::rotateCube, Face.RIGHT),
  Y_ROTATION(cube -> cube::rotateCube, Face.UP),
  Z_ROTATION(cube -> cube::rotateCube, Face.FRONT);

  final Function<Cube, Consumer<Face>> operation;
  final Face referenceFace;

  CubeMutation(Function<Cube, Consumer<Face>> operation, Face referenceFace) {
    this.operation = operation;
    this.referenceFace = referenceFace;
  }

  void apply(Cube c) {
    operation.apply(c).accept(referenceFace);
  }
}
