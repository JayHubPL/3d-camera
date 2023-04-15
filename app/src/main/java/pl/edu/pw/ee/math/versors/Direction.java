package pl.edu.pw.ee.math.versors;

import pl.edu.pw.ee.math.Vector;

public enum Direction {

    FORWARD(new Vector(0, 0, -1)),
    BACKWARD(new Vector(0, 0, 1)),
    LEFT(new Vector(1, 0, 0)),
    RIGHT(new Vector(-1, 0, 0)),
    UPWARD(new Vector(0, 1, 0)),
    DOWNWARD(new Vector(0, -1, 0));

    private final Vector versor;

    private Direction(Vector vec) {
        this.versor = vec;
    }

    public Vector getVersor() {
        return versor;
    }

}
