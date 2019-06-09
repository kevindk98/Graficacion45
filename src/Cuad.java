
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
public class Cuad extends Figura{
    Point pCD1;
    Point pCD2;
    Point pCD3;
    Point pCD4;
    Cuad(Point p1, Point p2,Point p3, Point p4, Color _color){
    pCD1=new Point(p1.x,p1.y);
    pCD2=new Point(p2.x,p2.y);
    pCD3=new Point(p3.x,p3.y);
    pCD4=new Point(p4.x,p4.y);
    color=_color;
    }
}
