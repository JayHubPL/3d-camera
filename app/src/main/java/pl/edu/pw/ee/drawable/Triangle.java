package pl.edu.pw.ee.drawable;

import java.util.List;

import org.ejml.simple.SimpleMatrix;

import pl.edu.pw.ee.display.Color;
import pl.edu.pw.ee.display.Shape;
import pl.edu.pw.ee.math.Point;
import pl.edu.pw.ee.math.Vector;

public class Triangle implements Drawable {

    private List<Point> points;
    private final Color color;
    private Vector normal;
    private double D;

    public Triangle(List<Point> points, Color color) {
        this.points = points;
        this.color = color;
        normal = Vector.fromMatrix(p1().minus(p2())).cross(Vector.fromMatrix(p3().minus(p2()))).normalize();
        D = -(normal.x()*p2().x() + normal.y()*p2().y() + normal.z()*p2().z());
    }

    public Triangle(Point p1, Point p2, Point p3, Color color) {
        this(List.of(p1, p2, p3), color);
    }

    @Override
    public void translate(SimpleMatrix translationVector) {
        points = points.stream().map(p -> Point.fromMatrix(translationVector.plus(p))).toList();
        D = -(normal.x()*p2().x() + normal.y()*p2().y() + normal.z()*p2().z());
    }

    @Override
    public void rotate(SimpleMatrix rotationMatrix) {
        points = points.stream().map(p -> Point.fromMatrix(rotationMatrix.mult(p))).toList();
        normal = Vector.fromMatrix(rotationMatrix.mult(normal));
    }

    @Override
    public List<Shape> toShapes() {
        double tmp = p2().scale(-1.).dot(normal);
        if (tmp <= 0.) {
            return List.of();
        }
        return List.of(new pl.edu.pw.ee.display.Polygon(points.stream().map(p -> p.toNormalizedDeviceCoords().toScreenCoords()).toList(), color));
    }

    @Override
    public int compareTo(Drawable other) {
        if (other instanceof Triangle) {
            Triangle otherTriangle = (Triangle)other;
            boolean isBehindPlain = otherTriangle.points.stream().anyMatch(p -> normal.dot(p) + D > 0.);
            return isBehindPlain ? -1 : 1;
        }
        return 0;
    }

    private Point p1() {
        return points.get(0);
    }

    private Point p2() {
        return points.get(1);
    }

    private Point p3() {
        return points.get(2);
    }
    
}
