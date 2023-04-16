package pl.edu.pw.ee;

import java.util.Scanner;

import pl.edu.pw.ee.controls.CameraControl;

public class App {
    
    private static final long FRAME_DELAY = 50;

    public static void main(String[] args) throws InterruptedException {
        loadDemo();
        while (true) {
            if (CameraControl.getInstance().reactToControls()) {
                Scene.getInstance().redraw();
                Thread.sleep(FRAME_DELAY);
            }
        }
    }

    private static void loadDemo() {
        try (Scanner input = new Scanner(System.in)) {
            switch (input.nextInt()) {
                case 1 -> Scene.getInstance().loadTrianglesDemo();
                default -> Scene.getInstance().loadCubeDemo();
            }
        }
        Scene.getInstance().redraw();
    }

}
