
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gmendez
 */
public class PizarraPrin extends javax.swing.JFrame {

    /**
     * Creates new form PizarraSec
     */
    static final int LINEA = 0;
    static final int TRIANGULO = 1;
    static final int CUADRADO = 2;
    static final int CIRCULO = 3;
    static final int ELIPSE = 4;

    static final int ANCHO = 640;
    static final int ALTO = 480;
    static final int oX = 320;
    static final int oY = 240;
    Raster raster;

    Point p1, p2, p3;
    boolean bP1 = false, bP2 = false, bP3 = false;
    int figura = LINEA;

    JPanel panelRaster;
    JPanel panelControles;
    JPanel panelFiguras;
    JScrollPane scrollFiguras;

    JList listFiguras;
    DefaultListModel listModel;
    ArrayList<Figura> aFiguras;
    ArrayList<Lin> lineas;

    JButton btnColor;
    JToggleButton rbLinea;
    JToggleButton rbTriang;
    JToggleButton rbCuad;
    JToggleButton rbCirc;
    JToggleButton rbElip;
    private JButton escalar;
    private JButton rotar;
    private JButton trasladar;
    JTextField textoEscalar;
    JTextField textoRotar;
    JTextField textoTrasladarX;
    JTextField textoTrasladarY;
    JTextField textoNumeroFigura;
    JLabel escalarT;
    JLabel angulo;
    JLabel trasladarX;
    JLabel trasladarY;
    JLabel numeroFigura;

    ButtonGroup bg;

    Color color;
    JColorChooser colorChooser;
    JButton btnGuardarRast;
    JButton btnGuardarVect;

    public PizarraPrin() {
        p1 = new Point();
        p2 = new Point();
        p3 = new Point();

        bP1 = false;
        bP2 = false;
        bP3 = false;

        raster = new Raster(ANCHO, ALTO);

        panelRaster = new MyPanel(raster);

        panelControles = new JPanel();
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        color = Color.black;

        btnGuardarRast = new JButton("Guardar");
        btnColor = new JButton("Color");

        btnColor.setBorderPainted(false);
        btnColor.setFocusPainted(false);

        btnColor.setBackground(color);
        btnColor.setForeground(color);

        rbLinea = new JToggleButton("Línea");
        rbTriang = new JToggleButton("Triángulo");
        rbCuad = new JToggleButton("Cuadrado");
        rbCirc = new JToggleButton("Círculo");
        rbElip = new JToggleButton("Elipse");
        escalar = new JButton("Escalar");
        rotar = new JButton("Rotar");
        trasladar = new JButton("Trasladar");
        textoEscalar = new JTextField();
        escalarT = new JLabel("Escala: ");
        textoRotar = new JTextField();
        angulo = new JLabel("Ángulo: ");
        textoTrasladarX = new JTextField();
        textoTrasladarY = new JTextField();
        trasladarX = new JLabel("X: ");
        trasladarY = new JLabel("Y: ");
        textoNumeroFigura = new JTextField();
        numeroFigura = new JLabel("Número de Figura: ");

        textoEscalar.setColumns(3);
        textoEscalar.setText("1");
        textoRotar.setColumns(3);
        textoRotar.setText("0");
        textoTrasladarX.setColumns(3);
        textoTrasladarX.setText("0");
        textoTrasladarY.setColumns(3);
        textoTrasladarY.setText("0");
        textoNumeroFigura.setColumns(3);
        textoNumeroFigura.setText("1");
        bg = new ButtonGroup();

        escalar.setName("escalar");
        rotar.setName("rotar");
        trasladar.setName("trasladar");

        rbLinea.setSelected(true);
        bg.add(rbLinea);
        bg.add(rbTriang);
        bg.add(rbCuad);
        bg.add(rbCirc);
        bg.add(rbElip);

        this.panelRaster.setBackground(Color.white);
        this.add(panelRaster, BorderLayout.CENTER);

        this.panelControles.add(rbLinea);
        this.panelControles.add(rbTriang);
        this.panelControles.add(rbCuad);
        this.panelControles.add(rbCirc);
        this.panelControles.add(rbElip);
        this.panelControles.add(new JSeparator());
        this.panelControles.add(numeroFigura);
        this.panelControles.add(textoNumeroFigura);
        this.panelControles.add(new JSeparator());

        JPanel panelTransformaciones = new JPanel();
        panelTransformaciones.setLayout(new GridLayout(3, 1));
        JPanel panelEscalar = new JPanel();
        panelEscalar.setLayout(new GridLayout(3, 1));
        JPanel panelEscalarNorte = new JPanel();
        JPanel panelEscalarSur = new JPanel();
        panelEscalarNorte.setLayout(new FlowLayout());
        panelEscalarNorte.add(escalar);
        panelEscalarSur.setLayout(new FlowLayout());
        panelEscalarSur.add(escalar);
        panelEscalarSur.add(textoEscalar);
        panelEscalar.add(panelEscalarNorte);
        panelEscalar.add(panelEscalarSur);
        panelEscalar.add(new JSeparator());

        JPanel panelRotar = new JPanel();
        panelRotar.setLayout(new GridLayout(3, 1));
        JPanel panelRotarNorte = new JPanel();
        JPanel panelRotarSur = new JPanel();
        panelRotarNorte.setLayout(new FlowLayout());
        panelRotarNorte.add(rotar);
        panelRotarSur.setLayout(new FlowLayout());
        panelRotarSur.add(angulo);
        panelRotarSur.add(textoRotar);
        panelRotar.add(panelRotarNorte);
        panelRotar.add(panelRotarSur);
        panelRotar.add(new JSeparator());

        JPanel panelTrasladar = new JPanel();
        panelTrasladar.setLayout(new GridLayout(3, 1));
        JPanel panelTrasladarNorte = new JPanel();
        JPanel panelTrasladarSur = new JPanel();
        panelTrasladarNorte.setLayout(new FlowLayout());
        panelTrasladarNorte.add(trasladar);
        panelTrasladarSur.setLayout(new FlowLayout());
        panelTrasladarSur.add(trasladarX);
        panelTrasladarSur.add(textoTrasladarX);
        panelTrasladarSur.add(trasladarY);
        panelTrasladarSur.add(textoTrasladarY);
        panelTrasladar.add(panelTrasladarNorte);
        panelTrasladar.add(panelTrasladarSur);
        panelTrasladar.add(new JSeparator());

        panelTransformaciones.add(panelEscalar);
        panelTransformaciones.add(panelRotar);
        panelTransformaciones.add(panelTrasladar);

        this.panelControles.add(panelTransformaciones);

        this.panelControles.add(btnColor);
        this.panelControles.add(btnGuardarRast);

        // Ahora el panel de figuras
        btnGuardarVect = new JButton("Guardar");

        scrollFiguras = new JScrollPane();
        panelFiguras = new JPanel();

        panelFiguras.setLayout(new BoxLayout(panelFiguras, BoxLayout.Y_AXIS));

        listFiguras = new JList();
        listFiguras.setModel(new DefaultListModel());
        listModel = (DefaultListModel) listFiguras.getModel();

        scrollFiguras.setViewportView(listFiguras);

        scrollFiguras.setPreferredSize(new Dimension(50, 100));
        panelFiguras.add(scrollFiguras);
        panelFiguras.add(new JSeparator());
        panelFiguras.add(btnGuardarVect);

        aFiguras = new ArrayList<Figura>();
        lineas = new ArrayList<Lin>();

        this.add(panelFiguras, BorderLayout.EAST);
        this.add(panelControles, BorderLayout.WEST);

        this.panelRaster.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        this.panelRaster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {

                
            }
        });

        this.btnColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = JColorChooser.showDialog(null, "Seleccione un color", color);
                btnColor.setBackground(color);
            }
        });

        this.btnGuardarRast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarImagen();
            }
        });

        this.btnGuardarVect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarVectores();
            }
        });

        this.setVisible(true);
        this.pack();

    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    public void guardarImagen() {

        BufferedImage img = toBufferedImage(raster.toImage(this));
        try {
            File outputfile = new File("saved.png");
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {

        }
    }

    public void guardarVectores() {
        FileWriter fw = null;
        String linea = "";
        try {
            fw = new FileWriter("vectores.txt");
            for (int i = 0; i < aFiguras.size(); i++) {
                Figura f = aFiguras.get(i);

                if (f instanceof Lin) {
                    Lin l = (Lin) f;
                    linea = String.format("L,%.0f,%.0f,%.0f,%.0f,%x\n", l.punto1.getX(), l.punto1.getY(),
                            l.punto2.getX(), l.punto2.getY(),
                            l.color.getRGB());
                }

                if (f instanceof TrianguloR) {
                    TrianguloR t = (TrianguloR) f;
                    linea = String.format("T,%d,%d,%d,%d,%d,%d,%x\n", t.v[0].x, t.v[0].y,
                            t.v[1].x, t.v[1].y,
                            t.v[2].x, t.v[2].y,
                            t.color_int);
                }

                if (f instanceof Circ) {
                    Circ cr = (Circ) f;
                    linea = String.format("Cr,%.0f,%.0f,%.0f,%.0f,%x\n", cr.puntoC1.getX(), cr.puntoC1.getY(),
                            cr.puntoC2.getX(), cr.puntoC2.getY(), cr.color.getRGB());
                }

                if (f instanceof Cuad) {
                    Cuad cd = (Cuad) f;
                    linea = String.format("Cd,%d,%d,%d,%d,%x\n", cd.pCD1.getX(), cd.pCD1.getY(),
                            cd.pCD2.getX(), cd.pCD2.getY(), cd.color.getRGB());
                }
                if (f instanceof Elip) {
                    Elip el = (Elip) f;
                    linea = String.format("E,%.0f,%.0f,%.0f,%.0f,%.0f,PA,%.0f,%x\n", el.pE1.getX(), el.pE1.getY(),
                            el.pE2.getX(), el.pE2.getY(), el.pE3.getX(), el.pE3.getY(), el.color.getRGB()
                    );

                }
                fw.write(linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(PizarraSec.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(PizarraSec.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void clear() {
        int s = raster.size();
        for (int i = 0; i < s; i++) {
            raster.pixel[i] ^= 0x00ffffff;
        }
        repaint();
        return;
    }

    public void lineaSimple(int x0, int y0, int x1, int y1, Color color) {
        int pix = color.getRGB();
        int dx = x1 - x0;
        int dy = y1 - y0;

        raster.setPixel(pix, x0, y0);

        if (dx != 0) {
            float m = (float) dy / (float) dx;
            float b = y0 - m * x0;

            dx = (x1 > x0) ? 1 : -1;

            while (x0 != x1) {
                x0 += dx;
                y0 = Math.round(m * x0 + b);
                raster.setPixel(pix, x0, y0);
            }
        }
    }

    public void lineaMejorada(int x0, int y0, int x1, int y1, Color color) {
        int pix = color.getRGB();
        int dx = x1 - x0;
        int dy = y1 - y0;
        raster.setPixel(pix, x0, y0);
        if (Math.abs(dx) > Math.abs(dy)) {     // inclinacion < 1
            float m = (float) dy / (float) dx; // calcular inclinacion
            float b = y0 - m * x0;
            dx = (dx < 0) ? -1 : 1;
            while (x0 != x1) {
                x0 += dx;
                raster.setPixel(pix, x0, Math.round(m * x0 + b));
            }
        } else {
            if (dy != 0) {                         // inclinacion >= 1
                float m = (float) dx / (float) dy; // Calcular inclinacion
                float b = x0 - m * y0;
                dy = (dy < 0) ? -1 : 1;
                while (y0 != y1) {
                    y0 += dy;
                    raster.setPixel(pix, Math.round(m * y0 + b), y0);
                }
            }
        }
    }

    public void lineFast(int x0, int y0, int x1, int y1, Color color) {
        int pix = color.getRGB();
        int dy = y1 - y0;
        int dx = x1 - x0;
        int stepx, stepy;
        if (dy < 0) {
            dy = -dy;
            stepy = -raster.width;
        } else {
            stepy = raster.width;
        }
        if (dx < 0) {
            dx = -dx;
            stepx = -1;
        } else {
            stepx = 1;
        }
        dy <<= 1;
        dx <<= 1;
        y0 *= raster.width;
        y1 *= raster.width;
        raster.pixel[x0 + y0] = pix;
        if (dx > dy) {
            int fraction = dy - (dx >> 1);
            while (x0 != x1) {
                if (fraction >= 0) {
                    y0 += stepy;
                    fraction -= dx;
                }
                x0 += stepx;
                fraction += dy;
                raster.pixel[x0 + y0] = pix;
            }
        } else {
            int fraction = dx - (dy >> 1);
            while (y0 != y1) {
                if (fraction >= 0) {
                    x0 += stepx;
                    fraction -= dy;
                }
                y0 += stepy;
                fraction += dx;
                raster.pixel[x0 + y0] = pix;
            }
        }
    }

    public void dibujarLinea(Point _p1, Point _p2, Color color, boolean b) {
        //long inicio = 0, fin = 0;
        //inicio = System.nanoTime();
        lineaMejorada(_p1.x, _p1.y, _p2.x, _p2.y, color);
        //  fin = System.nanoTime();

        //System.out.printf("Tiempo transcurrido, simple: %d\n", (fin - inicio));
        //inicio = System.nanoTime();
        // lineFast(_p1.x, _p1.y, _p2.x, _p2.y, color);
        // fin = System.nanoTime();
        // System.out.printf("Tiempo transcurrido, fast  : %d\n", (fin - inicio));
        if (b) {
            Lin l = new Lin(_p1, _p2, color);
            aFiguras.add(l);
        }
    }

    public void dibujarTriangulo(Point p1, Point p2, Point p3, Color c, Boolean b) {
        // TODO add your handling code here:
        Vertex2D v1 = new Vertex2D(p1.x, p1.y, c.getRGB());
        Vertex2D v2 = new Vertex2D(p2.x, p2.y, c.getRGB());
        Vertex2D v3 = new Vertex2D(p3.x, p3.y, c.getRGB());

        TrianguloR tri = new TrianguloR(v1, v2, v3, c);

        tri.dibujar(raster);

        if (b) {
            aFiguras.add(tri);
            listModel.addElement("Triangulo");
        }
    }

    public void dibujarCuadrado(Point p1, Point p2, Color c, Boolean b) {
        Point v1 = new Point(p1.x, p2.y);
        Point v2 = new Point(p2.x, p1.y);
        dibujarTriangulo(p1, p2, v1, c, false);
        dibujarTriangulo(p1, p2, v2, c, false);
        if (b) {
            Cuad cd = new Cuad(p1, p2,v1,v2, color);
            aFiguras.add(cd);
        }
    }

    public void dibujarCirculo(Point pc, Point pr, Color color, boolean b) {
        int pix = color.getRGB();
        int r = (int) calcularRadio(pc, pr);
        Point pc1 = new Point(pc.x,pc.y);
        Point pr1 = new Point(pr.x,pr.y);
        if (b) {
            Circ cr = new Circ(pc, pr, color);
            aFiguras.add(cr);
        }
        double x2, y2;
        double angulo = 0;
        do {
            do {
                x2 = pc1.x + r * Math.cos(angulo);
                y2 = pc1.y + r * Math.sin(angulo);
                pr1.x = (int) x2;
                pr1.y = (int) y2;
                angulo += 0.005;
                raster.setPixel(pix, (int) x2, (int) y2);
                //dibujarLinea(p1, p2, color);
            } while (angulo < 6.28);
            r--;
            angulo = 0;
        } while (r != 0);
    }

    public void dibujarElipse(Point pc, Point pr, Point pa, Color color, boolean t) {
        if (t) {
            Elip el = new Elip(pc, pr, pa, color);
            aFiguras.add(el);
        }
        Point pr1 = new Point(pr.x,pr.y);
        Point pc1 = new Point(pc.x,pc.y);
        Point pa1 = new Point(pa.x,pa.y);
        
        double a, b, angulo, x3, y3, dis, teta;
        int xr, yr, altura = Math.abs(pa1.y - pc1.y);
        int pix = color.getRGB();
        a = Math.abs(pr1.x - pc1.x);
        xr = pc1.x + (int) (a * Math.cos(0));
        yr = pc1.y;
        dis = Math.sqrt(Math.pow(pr1.x - xr, 2));
        teta = Math.asin((dis / 2) / a);
        teta = 2 * teta;
        do {
            for (angulo = 0; angulo <= 360; angulo++) {
                x3 = pc1.x + a * Math.cos(angulo + teta);
                y3 = pc1.y + altura * Math.sin(angulo);
                pr1.x = (int) x3;
                pr1.y = (int) y3;
                raster.setPixel(pix, (int) Math.round(x3), (int) Math.round(y3));
                dibujarLinea(pc1, pr1, color, false);
            }
            a--;
            altura--;

        } while (a != 0 && altura != 0);
    }

    public double calcularRadio(Point p1, Point p2) {
        double radio;
        radio = (Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y))));
        return radio;
    }


    public void addEventos(OyenteEventoBoton oyente) {
        escalar.addActionListener(oyente);
        rotar.addActionListener(oyente);
        trasladar.addActionListener(oyente);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        if (rbTriang.isSelected() && bP1 && bP2 && !bP3) {
            p3.x = evt.getX();
            p3.y = evt.getY();
            bP3 = true;
            System.out.println("Tercer punto");

        }
        if (rbElip.isSelected() && bP1 && bP2 && !bP3) {
            p3.x = evt.getX();
            p3.y = evt.getY();
            bP3 = true;
            System.out.println("Tercer punto");

        }

        if (bP1 && !bP2) {
            p2.x = evt.getX();
            p2.y = evt.getY();
            bP2 = true;
            System.out.println("Segundo punto");
            dibujarLinea(p2, p2, color, false);
            if (rbLinea.isSelected()) {

                listModel.addElement("Linea");
            }
        }

        if (!bP1) {
            p1.x = evt.getX();
            p1.y = evt.getY();
            bP1 = true;
            System.out.println("Primer punto");
            dibujarLinea(p1, p1, color, false);
        }

        if (rbLinea.isSelected() && bP1 && bP2) {
            dibujarLinea(p1, p2, color, true);
            bP1 = false;
            bP2 = false;
            bP3 = false;
        }

        if (rbTriang.isSelected() && bP1 && bP2 && bP3) {
            System.out.println("Dibujando triangulo");
            dibujarTriangulo(p1, p2, p3, color, true);
            bP1 = false;
            bP2 = false;
            bP3 = false;
        }

        if (rbCirc.isSelected() && bP1 && bP2) {
            dibujarCirculo(p1, p2, color, true);
            System.out.println("Dibujando círculo");
            bP1 = false;
            bP2 = false;
            bP3 = false;
            listModel.addElement("Circulo");
        }
        if (rbCuad.isSelected() && bP1 && bP2) {
            dibujarCuadrado(p1, p2, color, true);
            System.out.println("Dibujando cuadrado");
            bP1 = false;
            bP2 = false;
            bP3 = false;
            listModel.addElement("Cuadrado");
        }
        if (rbElip.isSelected() && bP1 && bP2 && bP3) {

            dibujarElipse(p1, p2, p3, color, true);
            System.out.println("Dibujando elipse");
            bP1 = false;
            bP2 = false;
            bP3 = false;
            listModel.addElement("Elipse");

        }
    }//GEN-LAST:event_jPanel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PizarraPrin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PizarraPrin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PizarraPrin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PizarraPrin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PizarraPrin pizarra = new PizarraPrin();
                OyenteEventoBoton oyente = new OyenteEventoBoton(pizarra);
                pizarra.addEventos(oyente);
                pizarra.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
