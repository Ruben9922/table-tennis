package uk.co.rubendougall.tabletennis;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

// TODO: Fix ball not starting from centre when application started
// TODO: Fix ball disappearing
// TODO: Fix ball rapidly changing x-direction when hitting bat
class Ball {
    private Dimension2D dimensions;
    private Point2D position;
    private Point2D velocity;
    private Random random = new Random();
    private Circle shape = new Circle(4, Color.grayRgb(255));

    Ball(Dimension2D dimensions) {
        this.dimensions = dimensions;

        boolean negateSpeed = random.nextInt() >= 0.5;
        reset(negateSpeed);
    }

    Circle getShape() {
        return shape;
    }

    void reset(boolean negateSpeed) {
        final double speedX = 0.3;
        final double maxSpeedY = 0.5;

        position = new Point2D(dimensions.getWidth() / 2, dimensions.getHeight() / 2);
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
