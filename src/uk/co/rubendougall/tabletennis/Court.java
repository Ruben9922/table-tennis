package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Court {
    void render(GraphicsContext gc) {
        Canvas canvas = gc.getCanvas();

        // Draw court edge
        gc.setStroke(Color.grayRgb(140));
        Point2D edgeSpacing = new Point2D(20, 20);
        gc.strokeRect(edgeSpacing.getX(), edgeSpacing.getY(), canvas.getWidth() - (2 * edgeSpacing.getX()),
                canvas.getHeight() - (2 * edgeSpacing.getY()));

        // Draw centre line
        gc.setStroke(Color.grayRgb(140));
        double centreLineSpacing = 30;
        gc.strokeLine(canvas.getWidth() / 2, centreLineSpacing, canvas.getWidth() / 2,
                canvas.getHeight() - centreLineSpacing);
    }
}
