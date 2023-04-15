package pl.edu.pw.ee.drawable;

import java.util.List;

import org.ejml.simple.SimpleMatrix;

import pl.edu.pw.ee.display.Shape;
import pl.edu.pw.ee.math.Point;

public class Line implements Drawable {
    
    private Point p1;
    private Point p2;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(double x1, double y1, double z1, double x2, double y2, double z2) {
        this(new Point(x1, y1, z1), new Point(x2, y2, z2));
    }

    @Override
    public List<Shape> toShapes() {
        pl.edu.pw.ee.display.Point p1Coords = p1.toNormalizedDeviceCoords().toScreenCoords();
        pl.edu.pw.ee.display.Point p2Coords = p2.toNormalizedDeviceCoords().toScreenCoords();
        return List.of(new pl.edu.pw.ee.display.Line(p1Coords.x(), p1Coords.y(), p2Coords.x(), p2Coords.y()));
    }

    @Override
    public void translate(SimpleMatrix translationVector) {
        p1 = Point.fromMatrix(p1.plus(translationVector));
        p2 = Point.fromMatrix(p2.plus(translationVector));
    }

    @Override
    public void rotate(SimpleMatrix rotationMatrix) {
        p1 = Point.fromMatrix(rotationMatrix.mult(p1));
        p2 = Point.fromMatrix(rotationMatrix.mult(p2));
    }

    @Override
    public int compareTo(Drawable other) {
        return 0;
    }
    
}
