void setup() {
  size(800, 500);
  stroke(255);
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
}