package com.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;


public class SnakeApp extends Application {
  int width = 600;
  int height = 600;
  Color background = Color.BLUE;
  Color bodyColor = Color.PINK;
  Color cHead = Color.RED;
  Canvas canvas;
  Pane canvasPane;
  Timeline timeline;
  Snake snake;
  Point headPosition = new Point(300, 300, 10, cHead);
  List<Point> bodyPosition = new ArrayList<>();
  Fruit fruit = new Fruit(0, 0, 0, null);
  int xHead = headPosition.getX();
  int yHead = headPosition.getY();
  int rHead = headPosition.getR();
  Point tailBefore = new Point(xHead, yHead, rHead, bodyColor);
  Point headNow = new Point(xHead, yHead, rHead, cHead);
  Point bodyNow = new Point(xHead, yHead, rHead, bodyColor);
  Point headBefore = new Point(xHead, yHead, rHead, cHead);
  KeyCode lastPressedKey = KeyCode.D;
  Scene scene;
  double speed;
  final double initialSpeed = 200;
  String loseText;
  int[] movement = new int[2];
  boolean pauseCheck = false;
  int increaseSpeed = 0;

  @Override
  public void start(Stage primaryStage) throws Exception {
    snake = new Snake(headPosition, bodyPosition);
    canvas = new Canvas(width, height);
    fruit = fruit.createSmallFruit();
    canvasPane = new Pane();
    canvasPane.getChildren().add(canvas);
    scene = new Scene(canvasPane);
    primaryStage.setTitle("Snake");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
    initializeGameObjects();
    initializeTimer();
  }

  private void moveUp() {
    movement[0] = 00;
    movement[1] = -20;
  }

  private void moveLeft() {
    movement[0] = -20;
    movement[1] = 00;
  }

  private void moveDown() {
    movement[0] = 00;
    movement[1] = 20;
  }

  private void moveRight() {
    movement[0] = 20;
    movement[1] = 00;
  }

  private void move(){
    if (bodyPosition.size()>0){
      bodyPosition.add(0,bodyNow);
      tailBefore = bodyPosition.get(bodyPosition.size()-1);
      bodyPosition.remove(bodyPosition.size()-1);
    }
    headBefore = new Point(xHead, yHead, rHead, bodyColor);
    xHead = xHead + movement[0];
    yHead = yHead + movement[1];
    headNow = new Point(xHead, yHead, rHead, cHead);
    snake.setHead(headNow);
    bodyNow = new Point(xHead, yHead, rHead, bodyColor);
  }

  private void initializeTimer() {
    timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> mainLoop()),
        new KeyFrame(Duration.millis(speed)));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  private void initializeGameObjects() {
    // snake initialization
    headPosition = new Point(300, 300, 10, cHead);
    xHead = 300;
    yHead = 300;
    snake.body.clear();
    snake.head = headPosition;
    lastPressedKey = KeyCode.D;
    speed = initialSpeed;
  }

  private void preventTheFruitToSummonOverTheSnake(){
    while(fruit.intersects(snake)){
      if (snake.body.size()%5 == 0 && snake.body.size()>0){
        fruit = fruit.createBigFruit();
      }else{
        fruit = fruit.createSmallFruit();
      }
    }
  }

  private void createNewFruitOnceTheSnakeEats(){
    if (snake.head.intersects(fruit)){
      if (bodyPosition.size()==0){
        bodyPosition.add(headBefore);
      }else{
        bodyPosition.add(tailBefore);
      }
    }
  }

  private void mainLoop() {
    for (Point point:bodyPosition){
      if (snake.head.getX() == point.getX() && snake.head.getY() == point.getY()){
        loseText="Il serpente si è morso la coda e si è avvelenato! Clicca per ricominciare.";
        lose();
        return;
      }
    }
    if (snake.head.getX()<0 || snake.head.getX()>=width-10 || snake.head.getY()<0 || snake.head.getY()>=height-10){
      loseText="Il serpente ha sbattuto la testa contro al muro! Clicca per ricominciare.";
      lose();
    }
    createNewFruitOnceTheSnakeEats();
    preventTheFruitToSummonOverTheSnake();
    switch (lastPressedKey) {
      case W:
        moveUp();
        break;
      case A:
        moveLeft();
        break;
      case S:
        moveDown();
        break;
      case D:
        moveRight();
        break;
      default:
        break;
    }
    move();

    scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
      if (key.getCode() == KeyCode.W) {
        if (lastPressedKey != KeyCode.S){
          lastPressedKey = KeyCode.W;
        }
      }
      if (key.getCode() == KeyCode.A) {
        if (lastPressedKey != KeyCode.D){
          lastPressedKey = KeyCode.A;
        }
      }
      if (key.getCode() == KeyCode.S) {
        if (lastPressedKey != KeyCode.W){
          lastPressedKey = KeyCode.S;
        }
      }
      if (key.getCode() == KeyCode.D) {
        if (lastPressedKey != KeyCode.A){
          lastPressedKey = KeyCode.D;
        }
      }
      if (key.getCode() == KeyCode.SPACE) {
        if (pauseCheck == false) {
          timeline.pause();
          pauseCheck = true;
        }
        else {
          timeline.play();
          pauseCheck = false;
        }
      }
    });

    paint();
  }

  private void paint() {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    // background
    gc.setFill(background);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    // fruit
    fruit.paint(gc);
    // snake
    snake.paint(gc);
  }

  private void lose (){
    timeline.stop();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(null);
    alert.setHeaderText(null);
    alert.setContentText(loseText);
    alert.setOnHidden(evt -> {
    initializeGameObjects();
    initializeTimer();
    });
    alert.show();
    initializeGameObjects();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
