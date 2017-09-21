package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Bat {
    private static final double BAT_LENGTH = 80;
    private static final Point2D VELOCITY = new Point2D(20, 0);

    private Point2D position;

    Bat(GraphicsContext gc, final double positionX) {
        Canvas canvas = gc.getCanvas();

        position = new Point2D(positionX, (canvas.getHeight() - Bat.BAT_LENGTH) / 2);
    }

    void update(GraphicsContext gc, double delta) {
        Canvas canvas = gc.getCanvas();

        // Clamp y-position
        double BAT_SPACING = 30;
        position = new Point2D(position.getX(), Utilities.constrain(position.getY(), BAT_SPACING, canvas.getHeight() - BAT_SPACING - BAT_LENGTH));
    }

    void draw(GraphicsContext gc) {
        gc.setStroke(Color.grayRgb(255));
        gc.strokeLine(position.getX(), position.getY(), position.getX(), position.getY() + BAT_LENGTH);
    }
}
