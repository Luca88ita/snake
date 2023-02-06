package com.snake;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;


public class Snake {
 Point head;
 List<Point> body;

 public Snake(Point head, List<Point> body) {
   this.head = head;
   this.body = body;
 }


 public Point getHead() {
   return head;
 }


 public void setHead(Point head) {
   this.head = head;
 }


 public List<Point> getBody() {
   return body;
 }


 public void setBody(List<Point> body) {
   this.body = body;
 }


 public void paint(GraphicsContext gc){
   //gc.drawImage(head);
   for (Point segment:body) {
     gc.setFill(segment.getC());
     gc.fillOval(segment.getX(), segment.getY(), segment.getR()*2, segment.getR()*2);
   }
   gc.setFill(head.getC());
   gc.fillOval(head.getX(), head.getY(), head.getR()*2, head.getR()*2);
 }

 public boolean intersects(Fruit other) {
  //System.out.println(getBoundsInParent());
  return getBounds().intersects(other.getBounds());
}

 public Rectangle2D getBounds(){
   return new Rectangle2D(head.getX(), head.getY(), head.getR()*2, head.getR()*2);// image.getWidth(),image.getHeight());
 }


 @Override
 public String toString() {
   return "Snake{" +
           "head=" + head +
           ", body=" + body +
           '}';
 }
}
