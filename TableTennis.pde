Ball ball;

void setup() {
  size(800, 500);
  stroke(255);
  
  ball = new Ball();
}

void draw() {
  final int defaultStrokeWeight = 4;
  final int courtEdgeSpacingX = 20;
  final int courtEdgeSpacingY = 10;
  final int centreLineSpacing = 20;
  final int batSpacing = 30;
  final int batLength = 50;
  final int batLimit = 20;
  
  background(50);
  strokeWeight(defaultStrokeWeight);
  
  // Draw court edge
  stroke(127);
  fill(255, 0);
  rectMode(CORNERS);
  rect(courtEdgeSpacingX, courtEdgeSpacingY, width - courtEdgeSpacingX, height - courtEdgeSpacingY);
  
  // Draw centre line
  stroke(127);
  line(width / 2, centreLineSpacing, width / 2, height - centreLineSpacing);
  
  // Draw bats
  stroke(255);
  int leftBatStart = constrain(mouseY, batLimit, height - batLimit - batLength);
  int rightBatStart = constrain(mouseX, batLimit, height - batLimit - batLength);
  line(batSpacing, leftBatStart, batSpacing, leftBatStart + batLength);
  line(width - batSpacing, rightBatStart, width - batSpacing, rightBatStart + batLength);
  
  // Check whether ball is on bats and/or court edges and change velocity accordingly
  if ((ball.positionX == batSpacing && ball.positionY >= leftBatStart && ball.positionY <= leftBatStart + batLength)
      || (ball.positionX == width - batSpacing && ball.positionY >= rightBatStart && ball.positionY <= rightBatStart + batLength)) {
    ball.velocityX *= -1;
  }
  if ((ball.positionY == courtEdgeSpacingY || ball.positionY == height - courtEdgeSpacingY)
      && ball.positionX >= courtEdgeSpacingX && ball.positionX <= width - courtEdgeSpacingX) {
    ball.velocityY *= -1;
  }
  
  // Check whether ball is on left or right side of court; if it does, reset ball
  if ((ball.positionX == courtEdgeSpacingX || ball.positionX == width - courtEdgeSpacingX)
      && ball.positionY >= courtEdgeSpacingY && ball.positionY <= height - courtEdgeSpacingY) {
    ball.reset();
  }
  
  // Draw ball
  stroke(255);
  strokeWeight(8);
  ball.positionX += ball.velocityX;
  ball.positionY += ball.velocityY;
  point(ball.positionX, ball.positionY);
  strokeWeight(defaultStrokeWeight);
}

class Ball {
  static final int ballSpeed = 2;
  
  int positionX;
  int positionY;
  int velocityX;
  int velocityY;
  
  Ball(int positionX, int positionY, int velocityX, int velocityY) {
    this.positionX = positionX;
    this.positionY = positionY;
    this.velocityX = velocityX;
    this.velocityY = velocityY;
  }
  
  Ball(int positionX, int positionY) {
    this(positionX, positionY, 0, 0);
  }
  
  Ball() {
    reset();
  }
  
  void reset() {
    positionX = width / 2;
    positionY = height / 2;
    velocityX = floor(random(2)) == 0 ? ballSpeed : -ballSpeed;
    velocityY = floor(random(5) - 2);
  }
}