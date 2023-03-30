package grak.geometry;

import org.ejml.simple.SimpleMatrix;

public class Point extends SimpleMatrix {
    
    public Point(double x, double y, double z) {
        super(new double[][] {
            {x}, {y}, {z}, {1.}
        });
    }

    public Point(SimpleMatrix mat) {
        super(new double[][] {
            {mat.get(0)}, {mat.get(1)}, {mat.get(2)}, {mat.get(3)}
        });
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

    public double w() {
        return get(3);
    }

}
