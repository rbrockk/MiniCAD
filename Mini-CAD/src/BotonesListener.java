/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rf010
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BotonesListener implements ActionListener, KeyListener {
    private Pizarra pizarra;

    public BotonesListener(Pizarra pizarra, ContadorClicks contadorClicks) {
        this.pizarra = pizarra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Acciones cuando se presiona un botón
        if (e.getActionCommand().equals("Borrar")) {
            // Limpiar la pizarra
            pizarra.limpiarPizarra();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Método de la interfaz KeyListener, no se utiliza en este caso
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Verificar las teclas Q y W para rotar a la izquierda y derecha, respectivamente
        if (keyCode == KeyEvent.VK_Q || keyCode == KeyEvent.VK_W) {
            // Obtener las coordenadas actuales de la figura
            int x = pizarra.getCoordenadaX(0);
            int y = pizarra.getCoordenadaY(0);

            // Determinar el ángulo de rotación según la tecla presionada
            double angulo = (keyCode == KeyEvent.VK_Q) ? -Math.toRadians(10) :
                            (keyCode == KeyEvent.VK_W) ? Math.toRadians(10) : 0;

            // Rotar las coordenadas de la figura
            int[] coordenadasRotadas = rotarCoordenadas(x, y, angulo);

            // Actualizar las coordenadas de la figura
            pizarra.setCoordenadaX(0, coordenadasRotadas[0]);
            pizarra.setCoordenadaY(0, coordenadasRotadas[1]);

            // Redibujar la pizarra
            pizarra.repaint();

            // Imprimir la matriz de rotación
            
        } else if (keyCode == KeyEvent.VK_PLUS || keyCode == KeyEvent.VK_ADD) {
            // Tecla "+" para escalar hacia arriba
            escalarFigura(1.1);
        } else if (keyCode == KeyEvent.VK_MINUS || keyCode == KeyEvent.VK_SUBTRACT) {
            // Tecla "-" para escalar hacia abajo
            escalarFigura(0.9);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Método de la interfaz KeyListener, no se utiliza en este caso
    }

    // Método para rotar coordenadas alrededor del origen
    private int[] rotarCoordenadas(int x, int y, double angulo) {
        int[] coordenadasRotadas = new int[2];
        coordenadasRotadas[0] = (int) (x * Math.cos(angulo) - y * Math.sin(angulo));
        coordenadasRotadas[1] = (int) (x * Math.sin(angulo) + y * Math.cos(angulo));
        return coordenadasRotadas;
    }

    // Método para escalar la figura
    private void escalarFigura(double factorEscala) {
        for (int i = 0; i < pizarra.getPuntos().size(); i++) {
            int x = pizarra.getCoordenadaX(i);
            int y = pizarra.getCoordenadaY(i);

            // Aplicar la escala mediante una matriz
            int[][] matrizEscala = {{(int) (factorEscala * 100), 0, 0},
                                    {0, (int) (factorEscala * 100), 0},
                                    {0, 0, 1}};
            int[] coordenadasEscaladas = transformarCoordenadas(x, y, matrizEscala);

            // Actualizar las coordenadas de la figura
            pizarra.setCoordenadaX(i, coordenadasEscaladas[0]);
            pizarra.setCoordenadaY(i, coordenadasEscaladas[1]);
        }

        // Redibujar la pizarra
        pizarra.repaint();
    }


   

    // Método para transformar coordenadas utilizando una matriz de transformación
    private int[] transformarCoordenadas(int x, int y, int[][] matriz) {
        int[] coordenadasTransformadas = new int[2];
        coordenadasTransformadas[0] = (matriz[0][0] * x + matriz[0][1] * y + matriz[0][2]) / 100;
        coordenadasTransformadas[1] = (matriz[1][0] * x + matriz[1][1] * y + matriz[1][2]) / 100;
        return coordenadasTransformadas;
    }
}
