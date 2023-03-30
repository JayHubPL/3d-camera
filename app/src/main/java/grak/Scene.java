package grak;

import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

import grak.canvas.Canvas;
import grak.geometry.Direction;
import grak.geometry.Rotation;
import grak.geometry.drawable.Drawable;

public class Scene {

    private static Scene scene = new Scene();

    private List<Drawable> shapes = new ArrayList<>();

    private Scene() {
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
