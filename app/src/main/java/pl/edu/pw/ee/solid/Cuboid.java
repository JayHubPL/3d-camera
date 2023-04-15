package pl.edu.pw.ee.solid;

import java.util.List;

import pl.edu.pw.ee.display.Color;
import pl.edu.pw.ee.drawable.Drawable;
import pl.edu.pw.ee.drawable.Triangle;
import pl.edu.pw.ee.math.Point;

public class Cuboid {
    
    private final List<Triangle> triangles;

    public Cuboid(Point p1, Point p2) {
        Point p3 = new Point(p1.x(), p1.y(), p2.z());
        Point p4 = new Point(p1.x(), p2.y(), p1.z());
        Point p5 = new Point(p2.x(), p1.y(), p1.z());
        Point p6 = new Point(p1.x(), p2.y(), p2.z());
        Point p7 = new Point(p2.x(), p1.y(), p2.z());
        Point p8 = new Point(p2.x(), p2.y(), p1.z());
        triangles = List.of(
            new Triangle(p1, p6, p4, Color.BLUE),
            new Triangle(p1, p3, p6, Color.BLUE),
            new Triangle(p2, p7, p5, Color.RED),
            new Triangle(p2, p5, p8, Color.RED),
            new Triangle(p1, p8, p5, Color.GREEN),
            new Triangle(p1, p4, p8, Color.GREEN),
            new Triangle(p2, p3, p7, Color.MAGENTA),
            new Triangle(p2, p6, p3, Color.MAGENTA),
            new Triangle(p2, p8, p6, Color.YELLOW),
            new Triangle(p4, p6, p8, Color.YELLOW),
            new Triangle(p1, p5, p3, Color.ORANGE),
            new Triangle(p3, p5, p7, Color.ORANGE)
        );
    }

    public List<? extends Drawable> toDrawables() {
        return triangles;
    }

}
