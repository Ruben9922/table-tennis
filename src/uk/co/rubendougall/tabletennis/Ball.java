package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

class Ball {
    private Canvas canvas;
    private Point2D position;
    private Point2D velocity;
    private Random random = new Random();
    private Circle shape = new Circle(4, Color.grayRgb(255));

    Ball(Canvas canvas) {
        this.canvas = canvas;

        reset();
    }

    Circle getShape() {
        return shape;
    }

    void reset() {
        final double speedX = 0.3;
        final double maxSpeedY = 0.5;

        // TODO: Choose x-direction properly
        position = new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 2);
        velocity = new Point2D(speedX, (random.nextDouble() * maxSpeedY) - (maxSpeedY / 2));
    }

    void update(double delta) {
        position = position.add(velocity.multiply(delta));

        shape.setCenterX(position.getX());
        shape.setCenterY(position.getY());
    }

    void changeXDirection() {
        velocity = new Point2D(-velocity.getX(), velocity.getY());
    }

    void changeYDirection() {
        velocity = new Point2D(velocity.getX(), -velocity.getY());
    }
}
