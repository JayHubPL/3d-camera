package grak;

import java.util.List;

import grak.geometry.Direction;
import grak.geometry.Rotation;
import grak.geometry.drawable.Cuboid;
import grak.geometry.drawable.Drawable;
import grak.geometry.drawable.Line;

public class Main {
    
    private static final long KEY_PRESS_DELAY = 50;

    public static void main(String[] args) throws InterruptedException {
        initializeScene();
        while (true) {
            if (reactToControls()) {
                Scene.getInstance().redraw();
                Thread.sleep(KEY_PRESS_DELAY);
            }
        }
    }

    private static void initializeScene() {
        List<Drawable> drawables = List.of(
            new Line(0.9, 0, 0, 0.9, 0, 10),
            new Line(-0.9, 0, 0, -0.9, 0, 10),
            new Cuboid(1, 0, 1, 2, -1, 2),
            new Cuboid(-1, 0, 1, -2, -3, 2),
            new Cuboid(1, 0, 2.1, 2, -2, 4.1),
            new Cuboid(-1, 0, 2.1, -1.5, -1.5, 5)
        );
        drawables.forEach(Scene.getInstance()::addShape);
        Scene.getInstance().redraw();
    }

    private static boolean reactToControls() {
        if (KeyboardControl.isWPressed()) {
            Scene.getInstance().translate(Direction.FORWARD);
        } else if (KeyboardControl.isAPressed()) {
            Scene.getInstance().translate(Direction.LEFT);
        } else if (KeyboardControl.isSPressed()) {
            Scene.getInstance().translate(Direction.BACKWARD);
        } else if (KeyboardControl.isDPressed()) {
            Scene.getInstance().translate(Direction.RIGHT);
        } else if (KeyboardControl.isShiftPressed()) {
            Scene.getInstance().translate(Direction.UPWARD);
        } else if (KeyboardControl.isControlPressed()) {
            Scene.getInstance().translate(Direction.DOWNWARD);
        } else if (KeyboardControl.isIPressed()) {
            Scene.getInstance().rotate(Rotation.FORWARD);
        } else if (KeyboardControl.isKPressed()) {
            Scene.getInstance().rotate(Rotation.BACKWARD);
        } else if (KeyboardControl.isJPressed()) {
            Scene.getInstance().rotate(Rotation.LEFT);
        } else if (KeyboardControl.isLPressed()) {
            Scene.getInstance().rotate(Rotation.RIGHT);
        } else if (KeyboardControl.isUPressed()) {
            Scene.getInstance().rotate(Rotation.LEAN_LEFT);
        } else if (KeyboardControl.isOPressed()) {
            Scene.getInstance().rotate(Rotation.LEAN_RIGHT);
        } else if (KeyboardControl.isRPressed()) {
            Renderer.getInstance().zoomIn();
        } else if (KeyboardControl.isFPressed()) {
            Renderer.getInstance().zoomOut();
        } else {
            return false;
        }
        return true;
    }

}
