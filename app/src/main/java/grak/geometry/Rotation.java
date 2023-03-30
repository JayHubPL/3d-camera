package grak.geometry;

public enum Rotation {
    
    FORWARD(new Vector3D(1, 0, 0)),
    BACKWARD(new Vector3D(-1, 0, 0)),
    LEFT(new Vector3D(0, 1, 0)),
    RIGHT(new Vector3D(0, -1, 0)),
    LEAN_LEFT(new Vector3D(0, 0, 1)),
    LEAN_RIGHT(new Vector3D(0, 0, -1));

    private final Vector3D rotation;

    private Rotation(Vector3D rotation) {
        this.rotation = rotation;
    }

    public Vector3D getRotation() {
        return rotation;
    }

}
