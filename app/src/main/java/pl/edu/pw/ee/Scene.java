package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

import pl.edu.pw.ee.display.Canvas;
import pl.edu.pw.ee.drawable.Drawable;
import pl.edu.pw.ee.math.Point;
import pl.edu.pw.ee.math.versors.Direction;
import pl.edu.pw.ee.math.versors.Rotation;
import pl.edu.pw.ee.solid.Cuboid;

public class Scene {

    private static Scene instance = null;

    private final List<Drawable> shapes = new ArrayList<>();

    private Scene() {
        shapes.addAll(new Cuboid(new Point(1., 2., 3.), new Point(-1., 0., 1.)).toDrawables());
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

    public void addShape(Drawable shape) {
        synchronized(Scene.class) {
            if (!shapes.contains(shape)) {
                shapes.add(shape);
            }
        }
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
