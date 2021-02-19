package simon;
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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Simon implements ActionListener, MouseListener
{
    public static int WIDTH = 600, HEIGHT = 600;

    public static Simon game;
    public static Random random;
    public static Renderer renderer;

    public ArrayList<Integer> pattern;

    public int flashed = 0, wait = 3, ticks = 0, ip = 0;

    public boolean show = true, gameOver = false;

    public Simon()
    {
        JFrame frame = new JFrame();
        Timer timer = new Timer(20, this);

        Dimension d = new Dimension(WIDTH + 4, HEIGHT + 28);

        renderer = new Renderer();

        frame.setSize(d);
        frame.setMaximumSize(d);
        frame.setPreferredSize(d);
        frame.add(renderer);
        frame.addMouseListener(this);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        start();

        timer.start();
    }

    public void start()
    {
        random = new Random();

        pattern = new ArrayList<Integer>();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        ticks++;

        if (ticks % 20 == 0)
            wait--;

        if (wait <= 0)
        {
            if (show)
            {
                if (ip < pattern.size() && flashed == 0)
                {
                    flashed = pattern.get(ip);
                    ip++;
                }
                else if (ip == pattern.size() && flashed == 0)
                {
                    flashed = random.nextInt(400) % 4 + 1;
                    pattern.add(flashed);
                    ip = 0;
                    show = false;
                }
                else
                    flashed = 0;

            }
            else
                flashed = 0;

            wait = 1;
        }

        renderer.repaint();
    }

    public void paintComponent(Graphics2D g)
    {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (flashed == 1)
            g.setColor(Color.GREEN);
        else
            g.setColor(Color.GREEN.darker().darker());
        
        g.fillRect(0, 0, WIDTH/2, HEIGHT/2);

        if (flashed == 2)
            g.setColor(Color.RED);
        else
            g.setColor(Color.RED.darker().darker());
        
        g.fillRect(WIDTH/2, 0, WIDTH/2, HEIGHT/2);

        if (flashed == 3)
            g.setColor(Color.YELLOW);
        else
            g.setColor(Color.YELLOW.darker().darker());
        
        g.fillRect(0, HEIGHT/2, WIDTH/2, HEIGHT/2);

        if (flashed == 4)
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.BLUE.darker().darker());
        
        g.fillRect(WIDTH/2, HEIGHT/2, WIDTH/2, HEIGHT/2);

        Area outter = new Area(new Rectangle(0, 0, WIDTH, HEIGHT));
        Ellipse2D inner = new Ellipse2D.Float(0, 0, WIDTH, HEIGHT);
        outter.subtract(new Area(inner));

        g.setColor(Color.BLACK);
        g.fill(outter);

        g.fillRect(0, HEIGHT/2-30, WIDTH, 60);
        g.fillRect(WIDTH/2-30, 0, 60, HEIGHT);
        g.fillRoundRect(WIDTH/2-125, HEIGHT/2-125, 250, 250, 125, 125);

        if (gameOver)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 120));
            g.drawString(":(", WIDTH/2-80, HEIGHT/2 + 40);
        }
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e)
    {
        int x = e.getX(), y = e.getY();

        if (x < WIDTH/2 && y < HEIGHT/2)
            flashed = 1;

        else if (x > WIDTH/2 && y < HEIGHT/2)
            flashed = 2;

        else if (x < WIDTH/2 && y > HEIGHT/2)
            flashed = 3;

        else if (x > WIDTH/2 && y > HEIGHT/2)
            flashed = 4;

        if (gameOver)
        {
            gameOver = false;
            show = true;
            flashed = 0;
            wait = 3;
            ip = 0;
            start();
        }
        else
        {
            if (!show && flashed == pattern.get(ip))
            {
                if (ip >= pattern.size() - 1)
                {
                    show = true;
                    ip = 0;
                    wait = 1;
                }
                else
                    ip++;
            }
            else if (flashed != pattern.get(ip))
                gameOver = true;
        }
    }

    public static void main(String[] args)
    {
        game = new Simon();

        game.start();
    }
}
