package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Bat {
    static final double BAT_LENGTH = 80;
    private static final Point2D VELOCITY = new Point2D(20, 0);
    private static final double BAT_SPACING_Y = 20;

    private Point2D position;

    public Bat(Point2D position) {
        this.position = position;
    }

    public void update(GraphicsContext gc, double delta) {
        Canvas canvas = gc.getCanvas();

        // Clamp y-position
        position = new Point2D(position.getX(), Utilities.constrain(position.getY(), BAT_SPACING_Y, canvas.getHeight() - BAT_SPACING_Y - BAT_LENGTH));
    }

    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.grayRgb(255));
        gc.strokeLine(position.getX(), position.getY(), position.getX(), position.getY() + BAT_LENGTH);
    }
}
