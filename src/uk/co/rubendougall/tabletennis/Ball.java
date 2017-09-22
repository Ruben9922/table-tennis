package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

class Ball {
    private Point2D position;
    private Point2D velocity;
    private Random random = new Random();
    private Circle shape = new Circle(4, Color.grayRgb(255));

    Ball(GraphicsContext gc) {
        reset(gc);
    }

    Circle getShape() {
        return shape;
    }

    void reset(GraphicsContext gc) {
        Canvas canvas = gc.getCanvas();

        final double speedX = 0.3;
        final double maxSpeedY = 0.5;

        // TODO: Choose x-direction properly
        position = new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 2);
        velocity = new Point2D(speedX, (random.nextDouble() * maxSpeedY) - (maxSpeedY / 2)); // TODO: Check this
    }

    void update(double delta) {
        // TODO: Check if on bat or top/bottom edge
        // TODO: Check for goal

        position = position.add(velocity.multiply(delta));

        shape.setCenterX(position.getX());
        shape.setCenterY(position.getY());
    }
}
