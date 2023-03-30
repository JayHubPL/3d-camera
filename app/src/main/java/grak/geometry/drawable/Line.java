package grak.geometry.drawable;

import java.util.List;

import org.ejml.simple.SimpleMatrix;

import grak.Renderer;
import grak.Renderer.ScreenCoords;
import grak.canvas.Shape;
import grak.geometry.Point;

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
    public List<Shape> toDrawShapes() {
        ScreenCoords p1Coords = Renderer.getInstance().mapPointToScreenCoords(p1);
        ScreenCoords p2Coords = Renderer.getInstance().mapPointToScreenCoords(p2);
        return List.of(new grak.canvas.Line(p1Coords.x(), p1Coords.y(), p2Coords.x(), p2Coords.y()));
    }

    @Override
    public void translate(SimpleMatrix translationMatrix) {
        p1 = new Point(translationMatrix.mult(p1));
        p2 = new Point(translationMatrix.mult(p2));
    }

    @Override
    public void rotate(SimpleMatrix rotationMatrix) {
        p1 = new Point(rotationMatrix.mult(p1));
        p2 = new Point(rotationMatrix.mult(p2));
    }

}
