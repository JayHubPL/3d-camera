package pl.edu.pw.ee.drawable;

import java.util.List;

import pl.edu.pw.ee.display.Shape;
import pl.edu.pw.ee.math.Movable;

public interface Drawable extends Movable, Comparable<Drawable> {
    
    public List<Shape> toShapes();

}
