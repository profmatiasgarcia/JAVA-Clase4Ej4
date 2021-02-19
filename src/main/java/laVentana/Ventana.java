package laVentana;
/**
 * @author Prof Matias Garcia.
 * <p> Copyright (C) 2017 para <a href = "https://www.profmatiasgarcia.com.ar/"> www.profmatiasgarcia.com.ar </a>
 * - con licencia GNU GPL3.
 * <p> Este programa es software libre. Puede redistribuirlo y/o modificarlo bajo los términos de la
 * Licencia Pública General de GNU según es publicada por la Free Software Foundation, 
 * bien con la versión 3 de dicha Licencia o bien (según su elección) con cualquier versión posterior. 
 * Este programa se distribuye con la esperanza de que sea útil, pero SIN NINGUNA GARANTÍA, 
 * incluso sin la garantía MERCANTIL implícita o sin garantizar la CONVENIENCIA PARA UN PROPÓSITO
 * PARTICULAR. Véase la Licencia Pública General de GNU para más detalles.
 * Debería haber recibido una copia de la Licencia Pública General junto con este programa. 
 * Si no ha sido así ingrese a <a href = "http://www.gnu.org/licenses/"> GNU org </a>
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Ventana extends JFrame {

    private JPanel panelContenedor;
    private JPanel panelBotones;
    private JLabel titulo;
    private JList listaIzq;
    private JList listaDer;
    private JButton btnDer;
    private JButton btnIzq;

    public Ventana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        panelContenedor = new JPanel();
        panelContenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelContenedor.setLayout(new BorderLayout(0, 0));
        setContentPane(panelContenedor);

        titulo = new JLabel();
        titulo.setText("Forma eficiente de ejecutar una Interfaz Grafica");
        titulo.setFont(new Font("Arial", 2, 20));

        panelContenedor.add(titulo, BorderLayout.NORTH);

        listaIzq = new JList();
        listaIzq.setPreferredSize(new Dimension(100, 200));
        listaIzq.setBorder(new LineBorder(Color.BLUE));
        DefaultListModel modelo = new DefaultListModel();
        modelo.addElement("Argentina");
        modelo.addElement("Chile");
        modelo.addElement("Uruguay");
        listaIzq.setModel(modelo);
        listaIzq.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        listaDer = new JList();
        listaDer.setPreferredSize(new Dimension(100, 200));
        listaDer.setBorder(new LineBorder(Color.BLUE));
        DefaultListModel modelo2 = new DefaultListModel();
        listaDer.setModel(modelo2);
        listaDer.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        panelContenedor.add(listaIzq, BorderLayout.WEST);
        panelContenedor.add(listaDer, BorderLayout.EAST);

        btnDer = new JButton("   >>   ");
        btnDer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(modelo.isEmpty())) {
                    modelo2.addElement(modelo.get(0));
                    modelo.remove(0); // borra el elem 1
                }
            }

        });

        btnIzq = new JButton("   <<   ");
        btnIzq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(modelo2.isEmpty())) {
                    modelo.addElement(modelo2.get(0));
                    modelo2.remove(0); // borra el elem 1
                }
            }

        });

        panelBotones = new JPanel();
        panelBotones.add(btnIzq);
        panelBotones.add(btnDer);

        panelContenedor.add(panelBotones, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ventana frame = new Ventana();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
