package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

class Bat {
    private static final double BAT_LENGTH = 80;
    private static final double VELOCITY = 1.2;

    private Point2D position;

    Bat(GraphicsContext gc, final double positionX) {
        Canvas canvas = gc.getCanvas();

        position = new Point2D(positionX, (canvas.getHeight() - Bat.BAT_LENGTH) / 2);
    }

    void update(GraphicsContext gc, double delta, Input input, KeyCode moveUpCode, KeyCode moveDownCode) {
        Canvas canvas = gc.getCanvas();

        if (input.isKeyPressed(moveUpCode)) {
            position = position.subtract(0, VELOCITY * delta);
        } else if (input.isKeyPressed(moveDownCode)) {
            position = position.add(0, VELOCITY * delta);
        }

        // Clamp y-position
        double BAT_SPACING = 30;
        position = new Point2D(position.getX(), Utilities.constrain(position.getY(), BAT_SPACING, canvas.getHeight() - BAT_SPACING - BAT_LENGTH));
    }

    void draw(GraphicsContext gc) {
        gc.setStroke(Color.grayRgb(255));
        gc.strokeLine(position.getX(), position.getY(), position.getX(), position.getY() + BAT_LENGTH);
    }
}
