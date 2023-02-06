package com.snake;

public class TestFruit {
  public static void main(String[] args) {
    Fruit f = new Fruit(0, 0, 0, null);
    f = f.createSmallFruit();

    System.out.println(f.getX());
    System.out.println(f.getY());
    System.out.println(f.getR());
    System.out.println(f.getC());
  }
}
