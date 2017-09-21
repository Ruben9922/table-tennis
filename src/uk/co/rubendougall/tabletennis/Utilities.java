package uk.co.rubendougall.tabletennis;

final class Utilities {
    static double constrain(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
