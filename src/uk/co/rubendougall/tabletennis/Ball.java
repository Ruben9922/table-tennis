package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

// TODO: Fix ball not starting from centre when application started
// TODO: Fix ball disappearing
class Ball {
    private Canvas canvas;
    private Point2D position;
    private Point2D velocity;
    private Random random = new Random();
    private Circle shape = new Circle(4, Color.grayRgb(255));

    Ball(Canvas canvas) {
        this.canvas = canvas;

        boolean negateSpeed = random.nextInt() >= 0.5;
        reset(negateSpeed);
    }

    Circle getShape() {
        return shape;
    }

    void reset(boolean negateSpeed) {
        final double speedX = 0.3;
        final double maxSpeedY = 0.5;

        position = new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 2);
        velocity = new Point2D(negateSpeed ? -speedX : speedX, (random.nextDouble() * maxSpeedY) - (maxSpeedY / 2));
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
