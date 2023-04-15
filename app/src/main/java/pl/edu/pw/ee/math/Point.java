package pl.edu.pw.ee.math;

import org.ejml.simple.SimpleMatrix;

import pl.edu.pw.ee.Renderer;

public class Point extends SimpleMatrix implements Movable {

    public Point(double x, double y, double z) {
        super(new double[][] {
            {x},
            {y},
            {z}
        });
    }

    protected Point(double[][] mat) {
        super(mat);
    }

    public static Point fromMatrix(SimpleMatrix mat) {
        return new Point(mat.get(0), mat.get(1), mat.get(2));
    }

    public NormalizedPoint toNormalizedDeviceCoords() {
        return NormalizedPoint.fromMatrix(Renderer.getInstance().getProjectionMatrix().mult(new NormalizedPoint(this)));
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

    @Override
    public void translate(SimpleMatrix translationVector) {
        this.mat = this.plus(translationVector).getMatrix();
    }

    @Override
    public void rotate(SimpleMatrix rotationMatrix) {
        this.mat = rotationMatrix.mult(this).getMatrix();
    }

}
