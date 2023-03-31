package grak;

import org.ejml.simple.SimpleMatrix;

import grak.geometry.Direction;
import grak.geometry.Point;
import grak.geometry.Rotation;
import grak.geometry.Vector3D;

public class Renderer {
    
    private static final double DEFAULT_FOV = Math.toRadians(100);
    private static final double DEFAULT_Z_NEAR = 0.5;
    private static final double DEFAULT_Z_FAR = 100;
    private static final int SCREEN_WIDTH = 1280;
    private static final int SCREEN_HEIGHT = 720;
    private static final double ASPECT_RATIO = SCREEN_WIDTH / SCREEN_HEIGHT;
    private static final double MIN_FOV = Math.toRadians(10);
    private static final double MAX_FOV = Math.toRadians(120);
    private static final double ZOOM_INCREMENT = Math.toRadians(2);
    private static final double ROTATION_INCREMENT = Math.toRadians(2);
    private static final double TRANSLATION_INCREMENT = 0.05;

    private double fieldOfView = DEFAULT_FOV;
    private double zNear = DEFAULT_Z_NEAR;
    private double zFar = DEFAULT_Z_FAR;
    private SimpleMatrix projectionMatrix = null;

    private static Renderer renderer = new Renderer();

    private Renderer() {
        calculateProjectionMatrix();
    }

    public static Renderer getInstance() {
        return renderer;
    }

    public SimpleMatrix getTranslationMatrix(Direction direction) {
        Vector3D translation = new Vector3D(direction.getVersor().scale(TRANSLATION_INCREMENT));
        SimpleMatrix translationMatrix = SimpleMatrix.identity(4);
        translationMatrix.set(0, 3, translation.get(0));
        translationMatrix.set(1, 3, translation.get(1));
        translationMatrix.set(2, 3, translation.get(2));
        return translationMatrix;
    }

    public SimpleMatrix getRotationMatrix(Rotation rotation) {
        Vector3D rotationVec = rotation.getRotation();
        double angle = ROTATION_INCREMENT;
        return switch (rotation) {
            case FORWARD:
            case BACKWARD:
                angle *= rotationVec.x();
                yield new SimpleMatrix(new double[][] {
                    {1., 0., 0., 0.},
                    {0., Math.cos(angle), -Math.sin(angle), 0.},
                    {0., Math.sin(angle), Math.cos(angle), 0.},
                    {0., 0., 0., 1.} 
                });
            case LEFT:
            case RIGHT:
                angle *= rotationVec.y();
                yield new SimpleMatrix(new double[][] {
                    {Math.cos(angle), 0., Math.sin(angle), 0.},
                    {0., 1., 0., 0.},
                    {-Math.sin(angle), 0., Math.cos(angle), 0.},
                    {0., 0., 0., 1.} 
                });
            case LEAN_LEFT:
            case LEAN_RIGHT:
                angle *= rotationVec.z();
                yield new SimpleMatrix(new double[][] {
                    {Math.cos(angle), -Math.sin(angle), 0., 0.},
                    {Math.sin(angle), Math.cos(angle), 0., 0.},
                    {0., 0., 1., 0.},
                    {0., 0., 0., 1.} 
                });
        };
    }

    public ScreenCoords mapPointToScreenCoords(Point p) {
        return new ScreenCoords(new Point(projectionMatrix.mult(p)));
    }

    public void zoomIn() {
        if (fieldOfView > MIN_FOV) {
            fieldOfView -= ZOOM_INCREMENT;
            calculateProjectionMatrix();
        }
    }

    public void zoomOut() {
        if (fieldOfView < MAX_FOV) {
            fieldOfView += ZOOM_INCREMENT;
            calculateProjectionMatrix();
        }
    }

    private void calculateProjectionMatrix() {
        projectionMatrix = new SimpleMatrix(new double[][] {
            { 1. / (ASPECT_RATIO * Math.tan(fieldOfView / 2.)), 0., 0., 0. },
            { 0., 1. / Math.tan(fieldOfView / 2.), 0., 0. },
            { 0., 0., zFar / (zFar - zNear), -zNear * zFar / (zFar - zNear) },
            { 0., 0., 1., 0. }
        });
    }

    public static class ScreenCoords {

        private static final int HALF_SCREEN_WIDTH = SCREEN_WIDTH >> 1;
        private static final int HALF_SCREEN_HEIGHT = SCREEN_HEIGHT >> 1;
        
        private double x;
        private double y;
        private double depth;

        public ScreenCoords(Point p) {
            x = p.x() / p.w() * HALF_SCREEN_WIDTH + HALF_SCREEN_WIDTH;
            y = p.y() / p.w() * HALF_SCREEN_HEIGHT + HALF_SCREEN_HEIGHT;
            depth = p.z() / p.w();
        }

        public double x() {
            return x;
        }

        public double y() {
            return y;
        }

        public double depth() {
            return depth;
        }

    }

}
