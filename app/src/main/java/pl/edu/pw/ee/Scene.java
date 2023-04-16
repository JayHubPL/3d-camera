package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

import pl.edu.pw.ee.display.Canvas;
import pl.edu.pw.ee.display.Color;
import pl.edu.pw.ee.drawable.Drawable;
import pl.edu.pw.ee.drawable.Triangle;
import pl.edu.pw.ee.math.Point;
import pl.edu.pw.ee.math.versors.Direction;
import pl.edu.pw.ee.math.versors.Rotation;
import pl.edu.pw.ee.solid.Cuboid;

public class Scene {

    private static Scene instance = null;

    private final List<Drawable> shapes = new ArrayList<>();

    private Scene() {
    }

    public static Scene getInstance() {
        if (instance == null) {
            synchronized(Scene.class) {
                if (instance == null) {
                    instance = new Scene();
                }
            }
        }
        return instance;
    }

    public void redraw() {
        Canvas canvas = Canvas.getInstance();
        canvas.clear();
        synchronized(Scene.class) {
            Collections.sort(shapes);
            shapes.stream().map(Drawable::toShapes).flatMap(List::stream).forEach(canvas::show);
        }
    }

    public void loadCubeDemo() {
        shapes.addAll(new Cuboid(new Point(10., 20., 30.), new Point(-10., 0., 10.)).toDrawables());
    }

    public void loadTrianglesDemo() {
        Point p1 = new Point(-20., 0., 30.);
        Point p2 = new Point(-30., -30., 50.);
        Point p3 = new Point(0., -40., 60.);
        Point p4 = new Point(-20., -10., 30.);
        Point p5 = new Point(-30., -40., 50.);
        Point p6 = new Point(0., -50., 60.);
        Point p7 = new Point(-20., -20., 30.);
        Point p8 = new Point(-30., -50., 50.);
        Point p9 = new Point(0., -60., 60.);
        shapes.add(new Triangle(p1, p2, p3, Color.CYAN));
        shapes.add(new Triangle(p4, p5, p6, Color.MAGENTA));
        shapes.add(new Triangle(p7, p8, p9, Color.YELLOW));
    }

    public void zoomIn() {
        Renderer.getInstance().zoomIn();
    }

    public void zoomOut() {
        Renderer.getInstance().zoomOut();
    }

    public void translate(Direction direction) {
        SimpleMatrix translationMatrix = Renderer.getInstance().getTranslationVector(direction);
        synchronized(Scene.class) {
            shapes.forEach(shape -> shape.translate(translationMatrix));
        }
    }

    public void rotate(Rotation rotation) {
        SimpleMatrix rotationMatrix = Renderer.getInstance().getRotationMatrix(rotation);
        synchronized(Scene.class) {
            shapes.forEach(shape -> shape.rotate(rotationMatrix));
        }
    }

}
