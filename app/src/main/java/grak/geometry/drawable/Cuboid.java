package grak.geometry.drawable;

import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

import grak.canvas.Shape;
import grak.geometry.Point;

public class Cuboid implements Drawable {

    private List<Line> edges = new ArrayList<>();

    public Cuboid(Point p1, Point p2) {
        Point p3 = new Point(p1.x(), p1.y(), p2.z());
        Point p4 = new Point(p1.x(), p2.y(), p1.z());
        Point p5 = new Point(p2.x(), p1.y(), p1.z());
        Point p6 = new Point(p1.x(), p2.y(), p2.z());
        Point p7 = new Point(p2.x(), p1.y(), p2.z());
        Point p8 = new Point(p2.x(), p2.y(), p1.z());
        edges.addAll(List.of(
            new Line(p1, p3),
            new Line(p1, p4),
            new Line(p1, p5),
            new Line(p2, p6),
            new Line(p2, p7),
            new Line(p2, p8),
            new Line(p3, p6),
            new Line(p3, p7),
            new Line(p4, p6),
            new Line(p4, p8),
            new Line(p5, p7),
            new Line(p5, p8)
        ));
    }

    public Cuboid(double x1, double y1, double z1, double x2, double y2, double z2) {
        this(new Point(x1, y1, z1), new Point(x2, y2, z2));
    }

    @Override
    public List<Shape> toDrawShapes() {
        return edges.stream().map(Line::toDrawShapes).flatMap(List::stream).toList();
    }

    @Override
    public void translate(SimpleMatrix translationMatrix) {
        edges.forEach(edge -> edge.translate(translationMatrix));
    }

    @Override
    public void rotate(SimpleMatrix rotationMatrix) {
        edges.forEach(edge -> edge.rotate(rotationMatrix));
    }
    
}
