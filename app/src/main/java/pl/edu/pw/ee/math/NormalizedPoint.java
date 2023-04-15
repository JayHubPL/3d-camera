package pl.edu.pw.ee.math;

import org.ejml.simple.SimpleMatrix;

import pl.edu.pw.ee.display.DisplaySize;

public class NormalizedPoint extends Point {

    private final byte outcode;

    public NormalizedPoint(Point p) {
        this(p.x(), p.y(), p.z(), 1.);
    }

    private NormalizedPoint(double x, double y, double z, double w) {
        super(new double[][] {
            {x},
            {y},
            {z},
            {w}
        });
        outcode = calculateOutcode();
    }

    public static NormalizedPoint fromMatrix(SimpleMatrix mat) {
        return new NormalizedPoint(mat.get(0), mat.get(1), mat.get(2), mat.get(3));
    }

    public pl.edu.pw.ee.display.Point toScreenCoords() {
        double x = x() / w() * DisplaySize.HALF_WIDTH + DisplaySize.HALF_WIDTH;
        double y = y() / w() * DisplaySize.HALF_HEIGHT + DisplaySize.HALF_HEIGHT;
        return new pl.edu.pw.ee.display.Point((int)x, (int)y);
    }

    private byte calculateOutcode() {
        byte outcode = 0;
        if (x() > 1)
            outcode += Outcode.RIGHT;
        else if (x() < -1)
            outcode += Outcode.LEFT;
        if (y() < -1)
            outcode += Outcode.UP;
        else if (y() > 1)
            outcode += Outcode.DOWN;
        if (z() < -1)
            outcode += Outcode.FRONT;
        else if (z() > 1)
            outcode += Outcode.BACK;
        return outcode;
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

    public byte getOutcode() {
        return outcode;
    }

    public static class Outcode {

        public static final byte LEFT = 1;
        public static final byte RIGHT = 2;
        public static final byte UP = 4;
        public static final byte DOWN = 8;
        public static final byte FRONT = 16;
        public static final byte BACK = 32;

    }
    
}
