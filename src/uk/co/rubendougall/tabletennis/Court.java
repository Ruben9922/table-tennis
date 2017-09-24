package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class Court {
    private Line topLine;
    private Line bottomLine;
    private Line leftLine;
    private Line rightLine;
    private Line centreLine;

    Court(Canvas canvas) {
        // Draw court edge
        Color colour = Color.grayRgb(140);
        Point2D edgeSpacing = new Point2D(20, 20);
        topLine = new Line(edgeSpacing.getX(), edgeSpacing.getY(), canvas.getWidth() - edgeSpacing.getX(), edgeSpacing.getY());
        topLine.setStroke(colour);
        bottomLine = new Line(edgeSpacing.getX(), canvas.getHeight() - edgeSpacing.getY(), canvas.getWidth() - edgeSpacing.getX(), canvas.getHeight() - edgeSpacing.getY());
        bottomLine.setStroke(colour);
        leftLine = new Line(edgeSpacing.getX(), edgeSpacing.getY(), edgeSpacing.getX(), canvas.getHeight() - edgeSpacing.getY());
        leftLine.setStroke(colour);
        rightLine = new Line(canvas.getWidth() - edgeSpacing.getX(), edgeSpacing.getY(), canvas.getWidth() - edgeSpacing.getX(), canvas.getHeight() - edgeSpacing.getY());
        rightLine.setStroke(colour);

        // Draw centre line
        double centreLineSpacing = 30;
        centreLine = new Line(canvas.getWidth() / 2, centreLineSpacing, canvas.getWidth() / 2,
                canvas.getHeight() - centreLineSpacing);
        centreLine.setStroke(colour);
    }

    Line getTopLine() {
        return topLine;
    }

    Line getBottomLine() {
        return bottomLine;
    }

    Line getLeftLine() {
        return leftLine;
    }

    Line getRightLine() {
        return rightLine;
    }

    Line getCentreLine() {
        return centreLine;
    }
}
