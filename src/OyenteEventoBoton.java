
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mi
 */
public class OyenteEventoBoton implements ActionListener {

    private PizarraPrin panel;

    public OyenteEventoBoton(PizarraPrin panel) {

        this.panel = panel;

    }

    public void dibujarFiguras() {
        for (int i = 0; i < panel.aFiguras.size(); i++) {
            Figura f = panel.aFiguras.get(i);

            if (f instanceof Lin) {

                Lin l = (Lin) f;
                panel.dibujarLinea((Point) l.punto1, (Point) l.punto2, l.color, false);
            }
            if (f instanceof Cuad) {
                Cuad cd = (Cuad) f;
                panel.dibujarTriangulo(cd.pCD1, cd.pCD2, cd.pCD3,cd.color, false);
                panel.dibujarTriangulo(cd.pCD1, cd.pCD2, cd.pCD4,cd.color, false);
            }
            if (f instanceof Circ) {
                Circ cr = (Circ) f;
                panel.dibujarCirculo((Point) cr.puntoC1, (Point) cr.puntoC2, cr.color, false);
            }
            if (f instanceof TrianguloR) {
                TrianguloR t = (TrianguloR) f;
                Point p1 = new Point();
                Point p2 = new Point();
                Point p3 = new Point();
                p1.setLocation(t.v[0].x, t.v[0].y);
                p2.setLocation(t.v[1].x, t.v[1].y);
                p3.setLocation(t.v[2].x, t.v[2].y);
                panel.dibujarTriangulo(p1, p2, p3, t.color, false);
            }
            if (f instanceof Elip) {
                Elip el = (Elip) f;
                panel.dibujarElipse((Point) el.pE1, (Point) el.pE2, (Point) el.pE3, el.color, false);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton origen = (JButton) e.getSource();

        if (origen.getName().equals("escalar")) {

            System.out.println("Escalar");
            int n = (Integer.parseInt(panel.textoNumeroFigura.getText())) - 1;

            Figura f = panel.aFiguras.get(n);

            if (f instanceof Lin) {
                Lin l = (Lin) f;
                panel.dibujarLinea((Point) l.punto1, (Point) l.punto2, Color.WHITE, false);
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX;
                int transY;
                double esc = Double.parseDouble(panel.textoEscalar.getText());

                transX = ((int) l.punto1.getX() - panel.oX) * (-1);
                transY = ((int) l.punto1.getY() - panel.oY) * (-1);

                punto[0] = l.punto1.getX();
                punto[1] = l.punto1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY);
                punto[0] = l.punto1.getX() - panel.oX;
                punto[1] = l.punto1.getY() - panel.oY;
                punto[2] = 1;
                m.escalar(esc);
                m.traslacion(transX * (-1), transY * (-1));
                punto2 = m.pprima(punto);
                l.punto1.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                punto[0] = l.punto2.getX();
                punto[1] = l.punto2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY);
                punto[0] = l.punto2.getX() - panel.oX;
                punto[1] = l.punto2.getY() - panel.oY;
                punto[2] = 1;
                m1.escalar(esc);
                m1.traslacion(transX * (-1), transY * (-1));
                punto2 = m1.pprima(punto);
                l.punto2.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                //panel.dibujarLinea((Point) l.pCD1, (Point) l.pCD2, l.color, false);
                dibujarFiguras();

            }

            if (f instanceof TrianguloR) {
                TrianguloR t = (TrianguloR) f;
                Point p1 = new Point();
                Point p2 = new Point();
                Point p3 = new Point();
                p1.setLocation(t.v[0].x, t.v[0].y);
                p2.setLocation(t.v[1].x, t.v[1].y);
                p3.setLocation(t.v[2].x, t.v[2].y);
                panel.dibujarTriangulo(p1, p2, p3, Color.WHITE, false);

                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX;
                int transY;
                double esc = Double.parseDouble(panel.textoEscalar.getText());

                transX = ((int) p1.x - panel.oX) * (-1);
                transY = ((int) p1.y - panel.oY) * (-1);

                punto[0] = p1.getX();
                punto[1] = p1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY);
                punto[0] = p1.getX() - panel.oX;
                punto[1] = p1.getY() - panel.oY;
                punto[2] = 1;
                m.escalar(esc);
                m.traslacion(transX * (-1), transY * (-1));
                punto2 = m.pprima(punto);
                p1.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                punto[0] = p2.getX();
                punto[1] = p2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY);
                punto[0] = p2.getX() - panel.oX;
                punto[1] = p2.getY() - panel.oY;
                punto[2] = 1;
                m1.escalar(esc);
                m1.traslacion(transX * (-1), transY * (-1));
                punto2 = m1.pprima(punto);
                p2.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                punto[0] = p3.getX();
                punto[1] = p3.getY();
                punto[2] = 1;

                Matriz m2 = new Matriz();
                m2.traslacion(transX, transY);
                punto[0] = p3.getX() - panel.oX;
                punto[1] = p3.getY() - panel.oY;
                punto[2] = 1;
                m2.escalar(esc);
                m2.traslacion(transX * (-1), transY * (-1));
                punto2 = m2.pprima(punto);
                p3.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                Vertex2D v1 = new Vertex2D(p1.x, p1.y, t.color.getRGB());
                Vertex2D v2 = new Vertex2D(p2.x, p2.y, t.color.getRGB());
                Vertex2D v3 = new Vertex2D(p3.x, p3.y, t.color.getRGB());

                TrianguloR tri = new TrianguloR(v1, v2, v3, t.color);
                panel.aFiguras.set(n, tri);
                //     panel.dibujarTriangulo(p1, p2, p3, t.color, false);
                dibujarFiguras();
            }

            if (f instanceof Circ) {
                Circ cr = (Circ) f;
                panel.dibujarCirculo((Point) cr.puntoC1, (Point) cr.puntoC2, Color.WHITE, false);
                System.out.println(cr.puntoC1.getX() + "," + cr.puntoC2.getX());
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX;
                int transY;
                double esc = Double.parseDouble(panel.textoEscalar.getText());

                transX = ((int) cr.puntoC1.getX() - panel.oX) * (-1);
                transY = ((int) cr.puntoC1.getY() - panel.oY) * (-1);

                punto[0] = cr.puntoC1.getX();
                punto[1] = cr.puntoC1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY);
                punto[0] = cr.puntoC1.getX() - panel.oX;
                punto[1] = cr.puntoC1.getY() - panel.oY;
                punto[2] = 1;
                m.escalar(esc);
                m.traslacion(transX * (-1), transY * (-1));
                punto2 = m.pprima(punto);
                cr.puntoC1.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                punto[0] = cr.puntoC2.getX();
                punto[1] = cr.puntoC2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY);
                punto[0] = cr.puntoC2.getX() - panel.oX;
                punto[1] = cr.puntoC2.getY() - panel.oY;
                punto[2] = 1;
                m1.escalar(esc);
                m1.traslacion(transX * (-1), transY * (-1));
                punto2 = m1.pprima(punto);
                cr.puntoC2.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                dibujarFiguras();
            }

            if (f instanceof Cuad) {
                Cuad cd = (Cuad) f;
                panel.dibujarTriangulo(cd.pCD1, cd.pCD2, cd.pCD3,Color.WHITE, false);
                panel.dibujarTriangulo(cd.pCD1, cd.pCD2, cd.pCD4,Color.WHITE, false);
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX;
                int transY;
                int pxm = (int) ((int) cd.pCD1.getX() + cd.pCD2.getX()) / 2;
                int pym = (int) ((int) cd.pCD1.getY() + cd.pCD2.getY()) / 2;;
                double escalar = Double.parseDouble(panel.textoEscalar.getText());

                transX = (pxm - panel.oX) * (-1);
                transY = (pym - panel.oY) * (-1);

                punto[0] = cd.pCD1.getX();
                punto[1] = cd.pCD1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY);
                punto[0] = cd.pCD1.getX() - panel.oX;
                punto[1] = cd.pCD1.getY() - panel.oY;
                punto[2] = 1;
                m.escalar(escalar);
                m.traslacion(transX * (-1), transY * (-1));
                punto2 = m.pprima(punto);
                cd.pCD1.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                punto[0] = cd.pCD2.getX();
                punto[1] = cd.pCD2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY);
                punto[0] = cd.pCD2.getX() - panel.oX;
                punto[1] = cd.pCD2.getY() - panel.oY;
                punto[2] = 1;
                m1.escalar(escalar);
                m1.traslacion(transX * (-1), transY * (-1));
                punto2 = m1.pprima(punto);
                cd.pCD2.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);
                
                punto[0] = cd.pCD3.getX();
                punto[1] = cd.pCD3.getY();
                punto[2] = 1;

                Matriz m2 = new Matriz();
                m2.traslacion(transX, transY);
                punto[0] = cd.pCD3.getX() - panel.oX;
                punto[1] = cd.pCD3.getY() - panel.oY;
                punto[2] = 1;
                m2.escalar(escalar);
                m2.traslacion(transX * (-1), transY * (-1));
                punto2 = m2.pprima(punto);
                cd.pCD3.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);
                
                punto[0] = cd.pCD3.getX();
                punto[1] = cd.pCD3.getY();
                punto[2] = 1;

                Matriz m3 = new Matriz();
                m3.traslacion(transX, transY);
                punto[0] = cd.pCD4.getX() - panel.oX;
                punto[1] = cd.pCD4.getY() - panel.oY;
                punto[2] = 1;
                m3.escalar(escalar);
                m3.traslacion(transX * (-1), transY * (-1));
                punto2 = m3.pprima(punto);
                cd.pCD4.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                //    panel.dibujarCuadrado((Point) cd.pCD1, (Point) cd.pCD2, cd.color, false);
                dibujarFiguras();
            }
            if (f instanceof Elip) {
                Elip el = (Elip) f;
                panel.dibujarElipse((Point) el.pE1, (Point) el.pE2,(Point)el.pE3, Color.WHITE, false);
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX;
                int transY;
                double esc = Double.parseDouble(panel.textoEscalar.getText());

                transX = ((int) el.pE1.getX() - panel.oX) * (-1);
                transY = ((int) el.pE1.getY() - panel.oY) * (-1);

                punto[0] = el.pE1.getX();
                punto[1] = el.pE1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY);
                punto[0] = el.pE1.getX() - panel.oX;
                punto[1] = el.pE1.getY() - panel.oY;
                punto[2] = 1;
                m.escalar(esc);
                m.traslacion(transX * (-1), transY * (-1));
                punto2 = m.pprima(punto);
                el.pE1.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                punto[0] = el.pE2.getX();
                punto[1] = el.pE2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY);
                punto[0] = el.pE2.getX() - panel.oX;
                punto[1] = el.pE2.getY() - panel.oY;
                punto[2] = 1;
                m1.escalar(esc);
                m1.traslacion(transX * (-1), transY * (-1));
                punto2 = m1.pprima(punto);
                el.pE2.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);
                
                Matriz m2 = new Matriz();
                m2.traslacion(transX, transY);
                punto[0] = el.pE3.getX() - panel.oX;
                punto[1] = el.pE3.getY() - panel.oY;
                punto[2] = 1;
                m2.escalar(esc);
                m2.traslacion(transX * (-1), transY * (-1));
                punto2 = m2.pprima(punto);
                el.pE3.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                dibujarFiguras();

            }

        } else if (origen.getName().equals("rotar") && panel.textoNumeroFigura.getText() != null) {

            System.out.println("Rotar");
            int n = Integer.parseInt(panel.textoNumeroFigura.getText()) - 1;
            Figura f = panel.aFiguras.get(n);

            if (f instanceof Lin) {
                Lin l = (Lin) f;
                panel.dibujarLinea((Point) l.punto1, (Point) l.punto2, Color.WHITE, false);
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX;
                int transY;
                int pmx = (int) ((int) (l.punto1.getX() + l.punto2.getX())) / 2;
                int pmy = (int) ((int) (l.punto1.getY() + l.punto2.getY())) / 2;
                int rotar = Integer.parseInt(panel.textoRotar.getText());

                transX = (pmx - panel.oX) * (-1);
                transY = (pmy - panel.oY) * (-1);

                Matriz m = new Matriz();
                m.traslacion(transX, transY);
                punto[0] = l.punto1.getX() - panel.oX;
                punto[1] = l.punto1.getY() - panel.oY;
                punto[2] = 1;
                m.rotacion(rotar);
                m.traslacion(transX * (-1), transY * (-1));
                punto2 = m.pprima(punto);
                l.punto1.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                punto[0] = l.punto2.getX();
                punto[1] = l.punto2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY);
                punto[0] = l.punto2.getX() - panel.oX;
                punto[1] = l.punto2.getY() - panel.oY;
                punto[2] = 1;
                m1.rotacion(rotar);
                m1.traslacion(transX * (-1), transY * (-1));
                punto2 = m1.pprima(punto);
                l.punto2.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                //    panel.dibujarLinea((Point) l.pCD1, (Point) l.pCD2, l.color, false);
                dibujarFiguras();
            }

            if (f instanceof TrianguloR) {
                TrianguloR t = (TrianguloR) f;
                Point p1 = new Point();
                Point p2 = new Point();
                Point p3 = new Point();
                p1.setLocation(t.v[0].x, t.v[0].y);
                p2.setLocation(t.v[1].x, t.v[1].y);
                p3.setLocation(t.v[2].x, t.v[2].y);
                panel.dibujarTriangulo(p1, p2, p3, Color.WHITE, false);

                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX;
                int transY;
                int pmx = (p1.x + p2.x + p3.x) / 3;
                int pmy = (p1.y + p2.y + p3.y) / 3;
                int rotar = Integer.parseInt(panel.textoRotar.getText());

                transX = (pmx - panel.oX) * (-1);
                transY = (pmy - panel.oY) * (-1);

                punto[0] = p1.getX();
                punto[1] = p1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY);
                punto[0] = p1.getX() - panel.oX;
                punto[1] = p1.getY() - panel.oY;
                punto[2] = 1;
                m.rotacion(rotar);
                m.traslacion(transX * (-1), transY * (-1));
                punto2 = m.pprima(punto);
                p1.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                punto[0] = p2.getX();
                punto[1] = p2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY);
                punto[0] = p2.getX() - panel.oX;
                punto[1] = p2.getY() - panel.oY;
                punto[2] = 1;
                m1.rotacion(rotar);
                m1.traslacion(transX * (-1), transY * (-1));
                punto2 = m1.pprima(punto);
                p2.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                punto[0] = p3.getX();
                punto[1] = p3.getY();
                punto[2] = 1;

                Matriz m2 = new Matriz();
                m2.traslacion(transX, transY);
                punto[0] = p3.getX() - panel.oX;
                punto[1] = p3.getY() - panel.oY;
                punto[2] = 1;
                m2.rotacion(rotar);
                m2.traslacion(transX * (-1), transY * (-1));
                punto2 = m2.pprima(punto);
                p3.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                Vertex2D v1 = new Vertex2D(p1.x, p1.y, t.color.getRGB());
                Vertex2D v2 = new Vertex2D(p2.x, p2.y, t.color.getRGB());
                Vertex2D v3 = new Vertex2D(p3.x, p3.y, t.color.getRGB());

                TrianguloR tri = new TrianguloR(v1, v2, v3, t.color);
                panel.aFiguras.set(n, tri);
                //    panel.dibujarTriangulo(p1, p2, p3, t.color, false);
                dibujarFiguras();
            }

            if (f instanceof Circ) {
                Circ cr = (Circ) f;
                dibujarFiguras();
            }

            if (f instanceof Cuad) {
                Cuad cd = (Cuad) f;
                //panel.dibujarCuadrado((Point) cd.pCD1, (Point) cd.pCD2, Color.WHITE, false);
                panel.dibujarTriangulo(cd.pCD1, cd.pCD2, cd.pCD3,Color.WHITE, false);
                panel.dibujarTriangulo(cd.pCD1, cd.pCD2, cd.pCD4,Color.WHITE, false);
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX;
                int transY;
                int pxm = (int) ((int) cd.pCD1.getX() + cd.pCD2.getX()) / 2;
                int pym = (int) ((int) cd.pCD1.getY() + cd.pCD2.getY()) / 2;;
                int rotar = Integer.parseInt(panel.textoRotar.getText());

                transX = (pxm - panel.oX) * (-1);
                transY = (pym - panel.oY) * (-1);

                punto[0] = cd.pCD1.getX();
                punto[1] = cd.pCD1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY);
                punto[0] = cd.pCD1.getX() - panel.oX;
                punto[1] = cd.pCD1.getY() - panel.oY;
                punto[2] = 1;
                m.rotacion(rotar);
                m.traslacion(transX * (-1), transY * (-1));
                punto2 = m.pprima(punto);
                cd.pCD1.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                punto[0] = cd.pCD2.getX();
                punto[1] = cd.pCD2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY);
                punto[0] = cd.pCD2.getX() - panel.oX;
                punto[1] = cd.pCD2.getY() - panel.oY;
                punto[2] = 1;
                m1.rotacion(rotar);
                m1.traslacion(transX * (-1), transY * (-1));
                punto2 = m1.pprima(punto);
                cd.pCD2.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);
                
                punto[0] = cd.pCD3.getX();
                punto[1] = cd.pCD3.getY();
                punto[2] = 1;

                Matriz m2 = new Matriz();
                m2.traslacion(transX, transY);
                punto[0] = cd.pCD3.getX() - panel.oX;
                punto[1] = cd.pCD3.getY() - panel.oY;
                punto[2] = 1;
                m2.rotacion(rotar);
                m2.traslacion(transX * (-1), transY * (-1));
                punto2 = m2.pprima(punto);
                cd.pCD3.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);
                
                punto[0] = cd.pCD3.getX();
                punto[1] = cd.pCD3.getY();
                punto[2] = 1;

                Matriz m3 = new Matriz();
                m3.traslacion(transX, transY);
                punto[0] = cd.pCD4.getX() - panel.oX;
                punto[1] = cd.pCD4.getY() - panel.oY;
                punto[2] = 1;
                m3.rotacion(rotar);
                m3.traslacion(transX * (-1), transY * (-1));
                punto2 = m3.pprima(punto);
                cd.pCD4.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);

                //    panel.dibujarCuadrado((Point) cd.pCD1, (Point) cd.pCD2, cd.color, false);
                dibujarFiguras();
            }
            if (f instanceof Elip) {
                Elip el = (Elip) f;
                panel.dibujarElipse((Point) el.pE1, (Point) el.pE2,(Point)el.pE3, Color.WHITE, false);
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX;
                int transY;
                transX = ((int) el.pE1.getX() - panel.oX) * (-1);
                transY = ((int) el.pE1.getY() - panel.oY) * (-1);

                int rotar = Integer.parseInt(panel.textoRotar.getText());


        /*       punto[0] = el.puntoC1.getX();
                punto[1] = el.puntoC1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY);
                punto[0] = el.puntoC1.getX() - panel.oX;
                punto[1] = el.puntoC1.getY() - panel.oY;
                punto[2] = 1;
                m.rotacion(rotar);
                m.traslacion(transX * (-1), transY * (-1));
                pCD2 = m.pprima(punto);
                el.puntoC1.setLocation(pCD2[0] + panel.oX, pCD2[1] + panel.oY);*/

                punto[0] = el.pE2.getX();
                punto[1] = el.pE2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY);
                punto[0] = el.pE2.getX() - panel.oX;
                punto[1] = el.pE2.getY() - panel.oY;
                punto[2] = 1;
                m1.rotacion(rotar);
                m1.traslacion(transX * (-1), transY * (-1));
                punto2 = m1.pprima(punto);
                el.pE2.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);
                
                punto[0] = el.pE3.getX();
                punto[1] = el.pE3.getY();
                punto[2] = 1;

                Matriz m2 = new Matriz();
                m2.traslacion(transX, transY);
                punto[0] = el.pE3.getX() - panel.oX;
                punto[1] = el.pE3.getY() - panel.oY;
                punto[2] = 1;
                m2.rotacion(rotar);
                m2.traslacion(transX * (-1), transY * (-1));
                punto2 = m2.pprima(punto);
                el.pE3.setLocation(punto2[0] + panel.oX, punto2[1] + panel.oY);
                
               
                dibujarFiguras();
            }

        } else {

            System.out.println("Trasladar");
            int n = Integer.parseInt(panel.textoNumeroFigura.getText()) - 1;
            Figura f = panel.aFiguras.get(n);

            if (f instanceof Lin) {
                Lin l = (Lin) f;
                panel.dibujarLinea((Point) l.punto1, (Point) l.punto2, Color.WHITE, false);
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX = Integer.parseInt(panel.textoTrasladarX.getText());
                int transY = Integer.parseInt(panel.textoTrasladarY.getText());

                punto[0] = l.punto1.getX();
                punto[1] = l.punto1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY * (-1));
                punto2 = m.pprima(punto);
                l.punto1.setLocation(punto2[0], punto2[1]);

                punto[0] = l.punto2.getX();
                punto[1] = l.punto2.getY();
                punto[2] = 1;

                punto2 = new double[3];
                punto2 = m.pprima(punto);
                l.punto2.setLocation(punto2[0], punto2[1]);

                //  panel.dibujarLinea((Point) l.pCD1, (Point) l.pCD2, l.color, false);
                dibujarFiguras();
            }

            if (f instanceof TrianguloR) {
                TrianguloR t = (TrianguloR) f;
                Point p1 = new Point();
                Point p2 = new Point();
                Point p3 = new Point();
                p1.setLocation(t.v[0].x, t.v[0].y);
                p2.setLocation(t.v[1].x, t.v[1].y);
                p3.setLocation(t.v[2].x, t.v[2].y);
                panel.dibujarTriangulo(p1, p2, p3, Color.WHITE, false);

                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX = Integer.parseInt(panel.textoTrasladarX.getText());
                int transY = Integer.parseInt(panel.textoTrasladarY.getText());

                punto[0] = p1.getX();
                punto[1] = p1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY * (-1));
                punto2 = m.pprima(punto);
                p1.setLocation(punto2[0], punto2[1]);

                punto[0] = p2.getX();
                punto[1] = p2.getY();
                punto[2] = 1;

                punto2 = new double[3];
                punto2 = m.pprima(punto);
                p2.setLocation(punto2[0], punto2[1]);

                punto[0] = p3.getX();
                punto[1] = p3.getY();
                punto[2] = 1;

                punto2 = new double[3];
                punto2 = m.pprima(punto);
                p3.setLocation(punto2[0], punto2[1]);

                Vertex2D v1 = new Vertex2D(p1.x, p1.y, t.color.getRGB());
                Vertex2D v2 = new Vertex2D(p2.x, p2.y, t.color.getRGB());
                Vertex2D v3 = new Vertex2D(p3.x, p3.y, t.color.getRGB());

                TrianguloR tri = new TrianguloR(v1, v2, v3, t.color);
                panel.aFiguras.set(n, tri);
                //  panel.dibujarTriangulo(p1, p2, p3, t.color, false);
                dibujarFiguras();
            }

            if (f instanceof Circ) {
                Circ cr = (Circ) f;
                panel.dibujarCirculo((Point) cr.puntoC1, (Point) cr.puntoC2, Color.WHITE, false);
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX = Integer.parseInt(panel.textoTrasladarX.getText());
                int transY = Integer.parseInt(panel.textoTrasladarY.getText());

                punto[0] = cr.puntoC1.getX();
                punto[1] = cr.puntoC1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY * (-1));
                punto2 = m.pprima(punto);
                cr.puntoC1.setLocation(punto2[0], punto2[1]);

                punto[0] = cr.puntoC2.getX();
                punto[1] = cr.puntoC2.getY();
                punto[2] = 1;

                punto2 = new double[3];
                punto2 = m.pprima(punto);
                cr.puntoC2.setLocation(punto2[0], punto2[1]);
                dibujarFiguras();

            }

            if (f instanceof Cuad) {
                Cuad cd = (Cuad) f;
                panel.dibujarTriangulo(cd.pCD1, cd.pCD2, cd.pCD3,Color.WHITE, false);
                panel.dibujarTriangulo(cd.pCD1, cd.pCD2, cd.pCD4,Color.WHITE, false);
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX = Integer.parseInt(panel.textoTrasladarX.getText());
                int transY = Integer.parseInt(panel.textoTrasladarY.getText());

                

                punto[0] = cd.pCD1.getX();
                punto[1] = cd.pCD1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY* (-1));
                punto2 = m.pprima(punto);
                cd.pCD1.setLocation(punto2[0], punto2[1]);

                punto[0] = cd.pCD2.getX();
                punto[1] = cd.pCD2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY* (-1));
                punto2 = m1.pprima(punto);
                cd.pCD2.setLocation(punto2[0], punto2[1] );
                
                punto[0] = cd.pCD3.getX();
                punto[1] = cd.pCD3.getY();
                punto[2] = 1;

                Matriz m2 = new Matriz();
                m2.traslacion(transX, transY* (-1));
                punto2 = m2.pprima(punto);
                cd.pCD3.setLocation(punto2[0] , punto2[1] );
                
                punto[0] = cd.pCD4.getX();
                punto[1] = cd.pCD4.getY();
                punto[2] = 1;

                Matriz m3 = new Matriz();
                m3.traslacion(transX, transY* (-1));
                punto2 = m3.pprima(punto);
                cd.pCD4.setLocation(punto2[0] , punto2[1] );

                //    panel.dibujarCuadrado((Point) cd.pCD1, (Point) cd.pCD2, cd.color, false);
                dibujarFiguras();
            }
            if (f instanceof Elip) {
                Elip el = (Elip) f;
                panel.dibujarElipse((Point) el.pE1, (Point) el.pE2,(Point)el.pE3, Color.WHITE, false);
                double[] punto = new double[3];
                double[] punto2 = new double[3];
                int transX = Integer.parseInt(panel.textoTrasladarX.getText());
                int transY = Integer.parseInt(panel.textoTrasladarY.getText());
                
                

                punto[0] = el.pE1.getX();
                punto[1] = el.pE1.getY();
                punto[2] = 1;

                Matriz m = new Matriz();
                m.traslacion(transX, transY* (-1));
                punto2 = m.pprima(punto);
                el.pE1.setLocation(punto2[0], punto2[1]);

                punto[0] = el.pE2.getX();
                punto[1] = el.pE2.getY();
                punto[2] = 1;

                Matriz m1 = new Matriz();
                m1.traslacion(transX, transY* (-1));
                punto2 = m1.pprima(punto);
                el.pE2.setLocation(punto2[0], punto2[1] );
                
                punto[0] = el.pE3.getX();
                punto[1] = el.pE3.getY();
                punto[2] = 1;

                Matriz m2 = new Matriz();
                m2.traslacion(transX, transY* (-1));
                punto2 = m2.pprima(punto);
                el.pE3.setLocation(punto2[0] , punto2[1] );
                
                dibujarFiguras();
            }

        }
    }

}
