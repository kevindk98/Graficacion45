
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KevinDK
 */
public class Elip extends Figura {
    Point2D pE1;
    Point2D pE2;
    Point2D pE3;
    
     Elip(Point pc,Point pr,Point pa, Color _color){
         pE1=new Point(pc.x,pc.y);
         pE2=new Point(pr.x,pr.y);
         pE3=new Point (pa.x,pa.y);
         color=_color;
     }

}
