/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rf010
 */
import java.awt.BorderLayout;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dibujos dibujos = new Dibujos();
            ContadorClicks contadorClicks = new ContadorClicks();
            Pizarra pizarra = new Pizarra(dibujos, contadorClicks);
            BotonesListener botonesListener = new BotonesListener(pizarra, contadorClicks);

            // Configuración de la interfaz gráfica
            JFrame frame = new JFrame("Pizarra App");
            frame.setSize(800, 600); // Ajusta el tamaño según tus necesidades
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // Configuración del layout
            frame.setLayout(new BorderLayout());
            frame.add(pizarra, BorderLayout.CENTER);

            JButton btnDibujar = new JButton("Dibujar");
            JButton btnBorrar = new JButton("Borrar");

            // Configuración de los botones y sus listeners
            btnDibujar.addActionListener(botonesListener);
            btnBorrar.addActionListener(botonesListener);

            // Configuración del panel de botones
            JPanel panelBotones = new JPanel();
            panelBotones.add(btnDibujar);
            panelBotones.add(btnBorrar);
            frame.add(panelBotones, BorderLayout.SOUTH);

            // Hacer visible la aplicación
            frame.setVisible(true);
        });
    }
}
