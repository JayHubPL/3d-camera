package grak.geometry;

public enum Direction {

    FORWARD(new Vector3D(0, 0, -1)),
    BACKWARD(new Vector3D(0, 0, 1)),
    LEFT(new Vector3D(1, 0, 0)),
    RIGHT(new Vector3D(-1, 0, 0)),
    UPWARD(new Vector3D(0, 1, 0)),
    DOWNWARD(new Vector3D(0, -1, 0));

    private final Vector3D versor;

    private Direction(Vector3D vec) {
        this.versor = vec;
    }

    public Vector3D getVersor() {
        return versor;
    }

}
