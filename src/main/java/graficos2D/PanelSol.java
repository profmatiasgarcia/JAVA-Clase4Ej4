package graficos2D;
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
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelSol extends JPanel {

    public void paintComponent(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(100, 100, 200, 200);

        for (double d = 0; d < 2 * Math.PI; d += 0.1) {
            int xEnd = (int) (200 + 150 * Math.cos(d));
            int yEnd = (int) (200 + 150 * Math.sin(d));
            g.drawLine(200, 200, xEnd, yEnd);
        }
        g.setColor(Color.BLACK);
        g.drawArc(150, 150, 100, 100, 230, 80);
        g.fillOval(150, 150, 20, 20);
        g.fillOval(230, 150, 20, 20);
    }
}
