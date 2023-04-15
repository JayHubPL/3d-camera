package pl.edu.pw.ee.math.versors;

import pl.edu.pw.ee.math.Vector;

public enum Rotation {
    
    FORWARD(new Vector(1, 0, 0)),
    BACKWARD(new Vector(-1, 0, 0)),
    LEFT(new Vector(0, 1, 0)),
    RIGHT(new Vector(0, -1, 0)),
    LEAN_LEFT(new Vector(0, 0, 1)),
    LEAN_RIGHT(new Vector(0, 0, -1));

    private final Vector rotation;

    private Rotation(Vector rotation) {
        this.rotation = rotation;
    }

    public Vector getRotation() {
        return rotation;
    }

}
