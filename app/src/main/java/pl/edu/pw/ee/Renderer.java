package pl.edu.pw.ee;

import org.ejml.simple.SimpleMatrix;

import pl.edu.pw.ee.display.DisplaySize;
import pl.edu.pw.ee.math.Vector;
import pl.edu.pw.ee.math.versors.Direction;
import pl.edu.pw.ee.math.versors.Rotation;

public class Renderer {
    
    private static final double DEFAULT_FOV = Math.toRadians(100);
    private static final double Z_NEAR = 0.5;
    private static final double Z_FAR = 100;
    private static final double ASPECT_RATIO = (double)DisplaySize.WIDTH / DisplaySize.HEIGHT;
    private static final double MIN_FOV = Math.toRadians(10);
    private static final double MAX_FOV = Math.toRadians(120);
    private static final double ZOOM_INCREMENT = Math.toRadians(2);
    private static final double ROTATION_INCREMENT = Math.toRadians(2);
    private static final double TRANSLATION_INCREMENT = 0.5;

    private static Renderer instance = null;

    private double fieldOfView = DEFAULT_FOV;
    private SimpleMatrix projectionMatrix = null;

    private Renderer() {
        calculateProjectionMatrix();
    }

    public static Renderer getInstance() {
        if (instance == null) {
            synchronized(Renderer.class) {
                if (instance == null) {
                    instance = new Renderer();
                }
            }
        }
        return instance;
    }

    public SimpleMatrix getTranslationVector(Direction direction) {
        return direction.getVersor().scale(TRANSLATION_INCREMENT);
    }

    public SimpleMatrix getRotationMatrix(Rotation rotation) {
        Vector rotationVec = rotation.getRotation();
        double angle = ROTATION_INCREMENT;
        return switch (rotation) {
            case FORWARD:
            case BACKWARD:
                angle *= rotationVec.x();
                yield new SimpleMatrix(new double[][] {
                    {1., 0., 0.},
                    {0., Math.cos(angle), -Math.sin(angle)},
                    {0., Math.sin(angle), Math.cos(angle)}
                });
            case LEFT:
            case RIGHT:
                angle *= rotationVec.y();
                yield new SimpleMatrix(new double[][] {
                    {Math.cos(angle), 0., Math.sin(angle)},
                    {0., 1., 0.},
                    {-Math.sin(angle), 0., Math.cos(angle)}
                });
            case LEAN_LEFT:
            case LEAN_RIGHT:
                angle *= rotationVec.z();
                yield new SimpleMatrix(new double[][] {
                    {Math.cos(angle), -Math.sin(angle), 0.},
                    {Math.sin(angle), Math.cos(angle), 0.},
                    {0., 0., 1.}
                });
        };
    }

    private void calculateProjectionMatrix() {
        projectionMatrix = new SimpleMatrix(new double[][] {
            { 1. / (ASPECT_RATIO * Math.tan(fieldOfView / 2.)), 0., 0., 0. },
            { 0., 1. / Math.tan(fieldOfView / 2.), 0., 0. },
            { 0., 0., Z_FAR / (Z_FAR - Z_NEAR), -Z_NEAR * Z_FAR / (Z_FAR - Z_NEAR) },
            { 0., 0., 1., 0. }
        });
    }

    public SimpleMatrix getProjectionMatrix() {
        return projectionMatrix;
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

}
