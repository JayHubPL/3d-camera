package grak.geometry.drawable;

import java.util.List;

import org.ejml.simple.SimpleMatrix;

public interface Drawable {
    
    public List<grak.canvas.Shape> toDrawShapes();

    public void translate(SimpleMatrix translationMatrix);

    public void rotate(SimpleMatrix rotationMatrix);

}
