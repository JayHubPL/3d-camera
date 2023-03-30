package grak;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class KeyboardControl {
    
    private static volatile boolean wPressed = false;
    private static volatile boolean aPressed = false;
    private static volatile boolean sPressed = false;
    private static volatile boolean dPressed = false;
    private static volatile boolean shiftPressed = false;
    private static volatile boolean controlPressed = false;
    private static volatile boolean iPressed = false;
    private static volatile boolean kPressed = false;
    private static volatile boolean jPressed = false;
    private static volatile boolean lPressed = false;
    private static volatile boolean uPressed = false;
    private static volatile boolean oPressed = false;
    private static volatile boolean rPressed = false;
    private static volatile boolean fPressed = false;

    static {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                synchronized (KeyboardControl.class) {
                    switch (keyEvent.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            switch (keyEvent.getKeyCode()) {
                                case KeyEvent.VK_W -> wPressed = true;
                                case KeyEvent.VK_A -> aPressed = true;
                                case KeyEvent.VK_S -> sPressed = true;
                                case KeyEvent.VK_D -> dPressed = true;
                                case KeyEvent.VK_SHIFT -> shiftPressed = true;
                                case KeyEvent.VK_CONTROL -> controlPressed = true;
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
                                case KeyEvent.VK_SHIFT -> shiftPressed = false;
                                case KeyEvent.VK_CONTROL -> controlPressed = false;
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

    public static boolean isWPressed() {
        synchronized (KeyboardControl.class) {
            return wPressed;
        }
    }

    public static boolean isAPressed() {
        synchronized (KeyboardControl.class) {
            return aPressed;
        }
    }

    public static boolean isSPressed() {
        synchronized (KeyboardControl.class) {
            return sPressed;
        }
    }

    public static boolean isDPressed() {
        synchronized (KeyboardControl.class) {
            return dPressed;
        }
    }

    public static boolean isShiftPressed() {
        synchronized (KeyboardControl.class) {
            return shiftPressed;
        }
    }

    public static boolean isControlPressed() {
        synchronized (KeyboardControl.class) {
            return controlPressed;
        }
    }

    public static boolean isIPressed() {
        synchronized (KeyboardControl.class) {
            return iPressed;
        }
    }

    public static boolean isKPressed() {
        synchronized (KeyboardControl.class) {
            return kPressed;
        }
    }

    public static boolean isJPressed() {
        synchronized (KeyboardControl.class) {
            return jPressed;
        }
    }

    public static boolean isLPressed() {
        synchronized (KeyboardControl.class) {
            return lPressed;
        }
    }

    public static boolean isUPressed() {
        synchronized (KeyboardControl.class) {
            return uPressed;
        }
    }

    public static boolean isOPressed() {
        synchronized (KeyboardControl.class) {
            return oPressed;
        }
    }
    
    public static boolean isRPressed() {
        synchronized (KeyboardControl.class) {
            return rPressed;
        }
    }

    public static boolean isFPressed() {
        synchronized (KeyboardControl.class) {
            return fPressed;
        }
    }

}
