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
}
