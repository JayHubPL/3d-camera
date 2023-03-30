package grak.geometry;

import org.ejml.simple.SimpleMatrix;

public class Vector3D extends SimpleMatrix {

    public Vector3D(double x, double y, double z) {
        super(new double[][] {
            {x}, {y}, {z}
        });
    }

    public Vector3D(SimpleMatrix mat) {
        this(mat.get(0), mat.get(1), mat.get(2));
    }

    public double x() {
        return get(0);
    }

    public double y() {
        return get(1);
    }

    public double z() {
        return get(2);
    }

}
