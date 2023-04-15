package pl.edu.pw.ee;

import pl.edu.pw.ee.controls.CameraControl;

public class App {
    
    private static final long FRAME_DELAY = 50;

    public static void main(String[] args) throws InterruptedException {
        Scene.getInstance().redraw();
        while (true) {
            if (CameraControl.getInstance().reactToControls()) {
                Scene.getInstance().redraw();
                Thread.sleep(FRAME_DELAY);
            }
        }
    }

}
