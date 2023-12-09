/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rf010
 */
import java.awt.Graphics;

public class Figura {
    
    // Dibuja un círculo centrado en (x, y) con radio r
    public static void dibujarCirculo(Graphics g, int x, int y, int r) {
        g.drawOval(x - r, y - r, 2 * r, 2 * r);
    }

    // Dibuja una línea desde (x1, y1) hasta (x2, y2)
    public static void dibujarLinea(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    // Dibuja un cuadrado con esquina superior izquierda en (x, y) y longitud de lado l
    public static void dibujarCuadrado(Graphics g, int x, int y, int l) {
        g.drawRect(x, y, 100, 100);
    }

    // Dibuja un triángulo con vértices en (x1, y1), (x2, y2), (x3, y3)
    public static void dibujarTriangulo(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x3, y3, x1, y1);
    }

    // Dibuja un pentágono con vértices en (x1, y1), (x2, y2), ..., (x5, y5)
    public static void dibujarPentagono(Graphics g, int[] xPoints, int[] yPoints) {
        g.drawPolygon(xPoints, yPoints, 5);
    }
    
    
}
