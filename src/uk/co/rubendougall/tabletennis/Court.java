package uk.co.rubendougall.tabletennis;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class Court {
    private Line topLine;
    private Line bottomLine;
    private Line leftLine;
    private Line rightLine;
    private Line centreLine;

    Court(Dimension2D dimensions) {
        // Draw court edge
        final Point2D edgeSpacing = new Point2D(20, 20);
        final Color colour = Color.grayRgb(140);
        topLine = new Line(edgeSpacing.getX(), edgeSpacing.getY(), dimensions.getWidth() - edgeSpacing.getX(), edgeSpacing.getY());
        topLine.setStroke(colour);
        bottomLine = new Line(edgeSpacing.getX(), dimensions.getHeight() - edgeSpacing.getY(), dimensions.getWidth() - edgeSpacing.getX(), dimensions.getHeight() - edgeSpacing.getY());
        bottomLine.setStroke(colour);
        leftLine = new Line(edgeSpacing.getX(), edgeSpacing.getY(), edgeSpacing.getX(), dimensions.getHeight() - edgeSpacing.getY());
        leftLine.setStroke(colour);
        rightLine = new Line(dimensions.getWidth() - edgeSpacing.getX(), edgeSpacing.getY(), dimensions.getWidth() - edgeSpacing.getX(), dimensions.getHeight() - edgeSpacing.getY());
        rightLine.setStroke(colour);

        // Draw centre line
        final double centreLineSpacing = 30;
        centreLine = new Line(dimensions.getWidth() / 2, centreLineSpacing, dimensions.getWidth() / 2,
                dimensions.getHeight() - centreLineSpacing);
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
