package grak;

import java.util.List;

import org.ejml.simple.SimpleMatrix;

import grak.canvas.Canvas;
import grak.geometry.Direction;
import grak.geometry.Rotation;
import grak.geometry.drawable.Cuboid;
import grak.geometry.drawable.Drawable;
import grak.geometry.drawable.Line;

public class Scene {

    private static final Scene scene = new Scene();

    private final List<Drawable> shapes;

    private Scene() {
        shapes = List.of(
            new Line(0.9, 0, 0, 0.9, 0, 10),
            new Line(-0.9, 0, 0, -0.9, 0, 10),
            new Cuboid(1, 0, 1, 2, -1, 2),
            new Cuboid(-1, 0, 1, -2, -3, 2),
            new Cuboid(1, 0, 2.1, 2, -2, 4.1),
            new Cuboid(-1, 0, 2.1, -1.5, -1.5, 5)
        );
    }

    public static Scene getInstance() {
        return scene;
    }

    public void redraw() {
        Canvas canvas = Canvas.getInstance();
        canvas.clear();
        shapes.stream().map(Drawable::toDrawShapes).flatMap(List::stream).forEach(canvas::show);
    }

    public void addShape(Drawable shape) {
        if (!shapes.contains(shape)) {
            shapes.add(shape);
        }
    }

    public void translate(Direction direction) {
        SimpleMatrix translationMatrix = Renderer.getInstance().getTranslationMatrix(direction);
        shapes.forEach(shape -> shape.translate(translationMatrix));
    }

    public void rotate(Rotation rotation) {
        SimpleMatrix rotationMatrix = Renderer.getInstance().getRotationMatrix(rotation);
        shapes.forEach(shape -> shape.rotate(rotationMatrix));
    }

}
