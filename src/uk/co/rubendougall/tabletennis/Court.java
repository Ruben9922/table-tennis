package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class Court {
    private Canvas canvas;
    private Line topEdge;
    private Line bottomEdge;
    private Line leftEdge;
    private Line rightEdge;
    private Line centreLine;

    Court(Canvas canvas) {
        this.canvas = canvas;

        // Draw court edge
        Color colour = Color.grayRgb(140);
        Point2D edgeSpacing = new Point2D(20, 20);
        topEdge = new Line(edgeSpacing.getX(), edgeSpacing.getY(), canvas.getWidth() - edgeSpacing.getX(), edgeSpacing.getY());
        topEdge.setStroke(colour);
        bottomEdge = new Line(edgeSpacing.getX(), canvas.getHeight() - edgeSpacing.getY(), canvas.getWidth() - edgeSpacing.getX(), canvas.getHeight() - edgeSpacing.getY());
        bottomEdge.setStroke(colour);
        leftEdge = new Line(edgeSpacing.getX(), edgeSpacing.getY(), edgeSpacing.getX(), canvas.getHeight() - edgeSpacing.getY());
        leftEdge.setStroke(colour);
        rightEdge = new Line(canvas.getWidth() - edgeSpacing.getX(), edgeSpacing.getY(), canvas.getWidth() - edgeSpacing.getX(), canvas.getHeight() - edgeSpacing.getY());
        rightEdge.setStroke(colour);

        // Draw centre line
        double centreLineSpacing = 30;
        centreLine = new Line(canvas.getWidth() / 2, centreLineSpacing, canvas.getWidth() / 2,
                canvas.getHeight() - centreLineSpacing);
        centreLine.setStroke(colour);
    }

    Line getTopEdge() {
        return topEdge;
    }

    Line getBottomEdge() {
        return bottomEdge;
    }

    Line getLeftEdge() {
        return leftEdge;
    }

    Line getRightEdge() {
        return rightEdge;
    }

    Line getCentreLine() {
        return centreLine;
    }
}
