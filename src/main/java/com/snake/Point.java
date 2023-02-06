package com.snake;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;


public class Point {
 int x;
 int y;
 int r;
 Color c;

 public Point(int x, int y, int r, Color c) {
   this.x = x;
   this.y = y;
   this.r = r;
   this.c = c;
 }

 public int getX() {
   return x;
 }

 public void setX(int x) {
   this.x = x;
 }

 public int getY() {
   return y;
 }

 public void setY(int y) {
   this.y = y;
 }

 public int getR() {
   return r;
 }

 public void setR(int r) {
   this.r = r;
 }

 public Color getC() {
   return c;
 }

 public void setC(Color c) {
   this.c = c;
 }

 public boolean intersects(Point other) {
  //System.out.println(getBoundsInParent());
  return getBounds().intersects(other.getBounds());
}
public boolean intersects(Snake other) {
  //System.out.println(getBoundsInParent());
  return getBounds().intersects(other.getBounds());
}
/*public boolean intersects(ArrayList<Point> other) {
  //System.out.println(getBoundsInParent());
  return getBounds().intersects(other.getBounds());
}*/


 public Rectangle2D getBounds(){
  return new Rectangle2D(x, y, r*2, r*2);
}

 @Override
 public String toString() {
   return "Point{" +
           "x=" + x +
           ", y=" + y +
           ", r=" + r +
           ", c=" + c +
           '}';
 }
}
