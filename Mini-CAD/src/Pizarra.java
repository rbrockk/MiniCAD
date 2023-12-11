/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rf010
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Pizarra extends JPanel {
    private Dibujos dibujos;
    private ContadorClicks contadorClicks;
    private List<Point> puntos;

    private static final int DESPLAZAMIENTO = 10; // Ajusta según sea necesario

    public Pizarra(Dibujos dibujos, ContadorClicks contadorClicks) {
        this.dibujos = dibujos;
        this.contadorClicks = contadorClicks;
        this.puntos = new ArrayList<>();
        setBackground(Color.WHITE);

        // Agregar MouseListener al panel
        addMouseListener(new PizarraMouseListener());

        // Agregar KeyListener a la Pizarra
        setFocusable(true);
        addKeyListener(new PizarraKeyListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar las figuras basadas en el número de clics
        if (!puntos.isEmpty()) {
            int size = puntos.size();
    
            if (size == 1) {
                Figura.dibujarCirculo(g, puntos.get(0).x, puntos.get(0).y, 20);
            } else if (size == 2) {
                Figura.dibujarLinea(g, puntos.get(0).x, puntos.get(0).y, puntos.get(1).x, puntos.get(1).y);
            } else if (size == 3) {
                Figura.dibujarTriangulo(g, puntos.get(0).x, puntos.get(0).y,
                        puntos.get(1).x, puntos.get(1).y,
                        puntos.get(2).x, puntos.get(2).y);
            } else {
                // Dibujar un polígono cerrado con los puntos de clics
                int[] xPoints = new int[size];
                int[] yPoints = new int[size];
                for (int i = 0; i < size; i++) {
                    xPoints[i] = puntos.get(i).x;
                    yPoints[i] = puntos.get(i).y;
                }
                g.drawPolygon(xPoints, yPoints, size);
            }
        }
    }    

    private class PizarraMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Obtener las coordenadas del clic
            int x = e.getX();
            int y = e.getY();

            // Imprimir las coordenadas en la consola
            System.out.println("Clic en (" + x + ", " + y + ")");

            // Agregar las coordenadas a la lista
            puntos.add(new Point(x, y));

            // Incrementar el contador y actualizar la etiqueta
            contadorClicks.incrementarContador();

            // Redibujar la pizarra con las nuevas coordenadas
            repaint();
        }

        // Resto de los métodos de la interfaz MouseListener
        // No es necesario implementar para este ejemplo
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private class PizarraKeyListener implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();

        switch (keyCode) {
                case KeyEvent.VK_UP:
                    trasladarFigura(0, -DESPLAZAMIENTO);
                    imprimirMatrizTraslacion(0, -DESPLAZAMIENTO);
                    break;
                case KeyEvent.VK_DOWN:
                    trasladarFigura(0, DESPLAZAMIENTO);
                    imprimirMatrizTraslacion(0, DESPLAZAMIENTO);
                    break;
                case KeyEvent.VK_LEFT:
                    trasladarFigura(-DESPLAZAMIENTO, 0);
                    imprimirMatrizTraslacion(-DESPLAZAMIENTO, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    trasladarFigura(DESPLAZAMIENTO, 0);
                    imprimirMatrizTraslacion(DESPLAZAMIENTO, 0);
                break;
            case KeyEvent.VK_Q:
                rotarFigura(-Math.toRadians(10)); // Rotar 10 grados a la izquierda
                imprimirMatrizRotacion(-Math.toRadians(10));
                break;
            case KeyEvent.VK_W:
                rotarFigura(Math.toRadians(10)); // Rotar 10 grados a la derecha
                imprimirMatrizRotacion(Math.toRadians(10));
                break;
            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_ADD:
                // Tecla "+" para escalar hacia arriba
                escalarFigura(1.1);
                imprimirMatrizEscalamiento(1.1);
                break;
            case KeyEvent.VK_MINUS:
            case KeyEvent.VK_SUBTRACT:
                // Tecla "-" para escalar hacia abajo
                escalarFigura(0.9);
                imprimirMatrizEscalamiento(0.9);
                break;
        }

        repaint();
    }    


        // Resto de los métodos de la interfaz KeyListener
        // No es necesario implementar para este ejemplo
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    // Métodos para obtener y establecer las coordenadas de la figura
    public int getCoordenadaX(int index) {
        if (index < puntos.size()) {
            return puntos.get(index).x;
        }
        return -1; // Valor por defecto si el índice está fuera de rango
    }

    public int getCoordenadaY(int index) {
        if (index < puntos.size()) {
            return puntos.get(index).y;
        }
        return -1; // Valor por defecto si el índice está fuera de rango
    }

    public void setCoordenadaX(int index, int x) {
        if (index < puntos.size()) {
            puntos.get(index).x = x;
        }
    }

    public void setCoordenadaY(int index, int y) {
        if (index < puntos.size()) {
            puntos.get(index).y = y;
        }
    }

    // Método para limpiar la lista de coordenadas
    public void limpiarPizarra() {
        puntos.clear();
        repaint();
    }
    
    public List<Point> getPuntos() {
    return puntos;
    }
    
     private void trasladarFigura(int dx, int dy) {
        for (int i = 0; i < puntos.size(); i++) {
            Point punto = puntos.get(i);
            punto.setLocation(punto.getX() + dx, punto.getY() + dy);
        }
    }

    private void rotarFigura(double angulo) {
        if (puntos.size() > 0) {
            Point centro = puntos.get(0); // Considera el primer punto como el centro de rotación
            for (int i = 1; i < puntos.size(); i++) {
                Point punto = puntos.get(i);
                double x = punto.getX() - centro.getX();
                double y = punto.getY() - centro.getY();
                double xRotado = x * Math.cos(angulo) - y * Math.sin(angulo);
                double yRotado = x * Math.sin(angulo) + y * Math.cos(angulo);
                punto.setLocation(xRotado + centro.getX(), yRotado + centro.getY());
            }
        }
    }
    
    private void escalarFigura(double factorEscala) {
    if (!puntos.isEmpty()) {
        Point centro = puntos.get(0);

        for (int i = 1; i < puntos.size(); i++) {
            Point punto = puntos.get(i);
            double x = punto.getX() - centro.getX();
            double y = punto.getY() - centro.getY();

            // Aplicar el factor de escala a las coordenadas
            x *= factorEscala;
            y *= factorEscala;

            punto.setLocation(x + centro.getX(), y + centro.getY());
            }
        }
    }
    
    // Método para imprimir la matriz de traslación en la consola
    private void imprimirMatrizTraslacion(int dx, int dy) {
        System.out.println("Matriz de traslación:");
        System.out.println("[ 1   0   " + dx + " ]");
        System.out.println("[ 0   1   " + dy + " ]");
        System.out.println("[ 0   0   1 ]");
}

    
    private void imprimirMatrizRotacion(double angulo) {
        System.out.println("Matriz de rotación:");
        System.out.println("[ " + Math.cos(angulo) + "  -" + Math.sin(angulo) + " ]");
        System.out.println("[ " + Math.sin(angulo) + "   " + Math.cos(angulo) + " ]");
        System.out.println("[ 0                   0                   1 ]");
    }

    // Método para imprimir la matriz de escalamiento en la consola
    private void imprimirMatrizEscalamiento(double factorEscala) {
        System.out.println("Matriz de escalamiento:");
        System.out.println("[ " + factorEscala + "   0   0 ]");
        System.out.println("[ 0   " + factorEscala + "   0 ]");
        System.out.println("[ 0   0   1 ]");
    }

}
