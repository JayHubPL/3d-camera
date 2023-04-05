package grak;

import grak.geometry.Direction;
import grak.geometry.Rotation;

public class Main {
    
    private static final long KEY_PRESS_DELAY = 50;

    public static void main(String[] args) throws InterruptedException {
        Scene.getInstance().redraw();
        mainLoop();
    }

    private static void mainLoop() throws InterruptedException {
        while (true) {
            if (reactToControls()) {
                Scene.getInstance().redraw();
                Thread.sleep(KEY_PRESS_DELAY);
            }
        }
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
