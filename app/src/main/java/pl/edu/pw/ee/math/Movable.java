package pl.edu.pw.ee.math;

import org.ejml.simple.SimpleMatrix;

public interface Movable {
    
    public void translate(SimpleMatrix translationVector);

    public void rotate(SimpleMatrix rotationMatrix);

}
