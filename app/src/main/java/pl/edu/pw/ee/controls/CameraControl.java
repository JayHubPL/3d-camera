package pl.edu.pw.ee.controls;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import pl.edu.pw.ee.Renderer;
import pl.edu.pw.ee.Scene;
import pl.edu.pw.ee.math.versors.Direction;
import pl.edu.pw.ee.math.versors.Rotation;

public class CameraControl {

    private static volatile boolean wPressed = false;
    private static volatile boolean aPressed = false;
    private static volatile boolean sPressed = false;
    private static volatile boolean dPressed = false;
    private static volatile boolean ePressed = false;
    private static volatile boolean qPressed = false;
    private static volatile boolean iPressed = false;
    private static volatile boolean kPressed = false;
    private static volatile boolean jPressed = false;
    private static volatile boolean lPressed = false;
    private static volatile boolean uPressed = false;
    private static volatile boolean oPressed = false;
    private static volatile boolean rPressed = false;
    private static volatile boolean fPressed = false;

    private static CameraControl instance = null;

    public static CameraControl getInstance() {
        if (instance == null) {
            synchronized(CameraControl.class) {
                if (instance == null) {
                    instance = new CameraControl();
                }
            }
        }
        return instance;
    }

    private CameraControl() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                synchronized (CameraControl.class) {
                    switch (keyEvent.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            switch (keyEvent.getKeyCode()) {
                                case KeyEvent.VK_W -> wPressed = true;
                                case KeyEvent.VK_A -> aPressed = true;
                                case KeyEvent.VK_S -> sPressed = true;
                                case KeyEvent.VK_D -> dPressed = true;
                                case KeyEvent.VK_E -> ePressed = true;
                                case KeyEvent.VK_Q -> qPressed = true;
                                case KeyEvent.VK_I -> iPressed = true;
                                case KeyEvent.VK_K -> kPressed = true;
                                case KeyEvent.VK_J -> jPressed = true;
                                case KeyEvent.VK_L -> lPressed = true;
                                case KeyEvent.VK_U -> uPressed = true;
                                case KeyEvent.VK_O -> oPressed = true;
                                case KeyEvent.VK_R -> rPressed = true;
                                case KeyEvent.VK_F -> fPressed = true;
                            }
                            break;
                        case KeyEvent.KEY_RELEASED:
                            switch (keyEvent.getKeyCode()) {
                                case KeyEvent.VK_W -> wPressed = false;
                                case KeyEvent.VK_A -> aPressed = false;
                                case KeyEvent.VK_S -> sPressed = false;
                                case KeyEvent.VK_D -> dPressed = false;
                                case KeyEvent.VK_E -> ePressed = false;
                                case KeyEvent.VK_Q -> qPressed = false;
                                case KeyEvent.VK_I -> iPressed = false;
                                case KeyEvent.VK_K -> kPressed = false;
                                case KeyEvent.VK_J -> jPressed = false;
                                case KeyEvent.VK_L -> lPressed = false;
                                case KeyEvent.VK_U -> uPressed = false;
                                case KeyEvent.VK_O -> oPressed = false;
                                case KeyEvent.VK_R -> rPressed = false;
                                case KeyEvent.VK_F -> fPressed = false;
                            }
                            break;
                    }
                    return false;
                }
            }
            
        });
    }
    
    public boolean reactToControls() {
        synchronized(CameraControl.class) {
            if (wPressed) {
                Scene.getInstance().translate(Direction.FORWARD);
            } else if (aPressed) {
                Scene.getInstance().translate(Direction.LEFT);
            } else if (sPressed) {
                Scene.getInstance().translate(Direction.BACKWARD);
            } else if (dPressed) {
                Scene.getInstance().translate(Direction.RIGHT);
            } else if (ePressed) {
                Scene.getInstance().translate(Direction.UPWARD);
            } else if (qPressed) {
                Scene.getInstance().translate(Direction.DOWNWARD);
            } else if (iPressed) {
                Scene.getInstance().rotate(Rotation.FORWARD);
            } else if (kPressed) {
                Scene.getInstance().rotate(Rotation.BACKWARD);
            } else if (jPressed) {
                Scene.getInstance().rotate(Rotation.LEFT);
            } else if (lPressed) {
                Scene.getInstance().rotate(Rotation.RIGHT);
            } else if (uPressed) {
                Scene.getInstance().rotate(Rotation.LEAN_LEFT);
            } else if (oPressed) {
                Scene.getInstance().rotate(Rotation.LEAN_RIGHT);
            } else if (rPressed) {
                Renderer.getInstance().zoomIn();
            } else if (fPressed) {
                Renderer.getInstance().zoomOut();
            } else {
                return false;
            }
            return true;
        }
    }

}
