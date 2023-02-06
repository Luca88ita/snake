package com.snake;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Fruit extends Point {
  int height = 600;
  int width = 600;
  
  Fruit bigFruit;
  int x = randomPosition(width);
  int y = randomPosition(height);
  int r = 10;
  int r2 = 15;
  Color c = Color.GREEN;
  Color c2 = Color.color(Math.random(), Math.random(), Math.random());
  Fruit smallFruit;

  public Fruit(int x, int y, int r, Color c) {
    super(x, y, r, c);
  }

  public Fruit createSmallFruit() {
    smallFruit = new Fruit(x, y, r, c);
    return smallFruit;
  }

  public Fruit createBigFruit() {
    bigFruit = new Fruit(x, y, r2, c2);
    return bigFruit;
  }

  private int randomPosition(int bound) {
    Random random = new Random();
    int n = random.nextInt(bound / 20) * 20;
    return n;
  }

  public void paint(GraphicsContext gc) {
    // gc.drawImage(smallFruit);
    //smallFruit = createSmallFruit();
    //gc.setFill(smallFruit.getC());
    //gc.fillOval(smallFruit.getX(), smallFruit.getY(), smallFruit.getR() * 2, smallFruit.getR() * 2);
    gc.setFill(c);
    if (r%10 == 0){
      gc.fillOval(x, y, r * 2, r * 2);
    }else{
      gc.fillOval((x+15), (y+15), r2 * 2, r2 * 2);
    }
  }

  public Rectangle2D getBounds(){
    return new Rectangle2D(x, y, r*2, r*2);// image.getWidth(),image.getHeight());
  }

}

