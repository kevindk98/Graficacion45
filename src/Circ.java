
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KevinDK
 */
public class Circ extends Figura {
    Point2D puntoC1;
    Point2D puntoC2;
    
     Circ(Point pc,Point pr, Color _color){
         puntoC1= new Point(pc.x,pc.y);
         puntoC2= new Point(pr.x,pr.y);
         color=_color;
     }

}
