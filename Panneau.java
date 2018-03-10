package PigeonSquare;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Panneau extends JPanel implements Runnable, MouseListener {

    private static final long serialVersionUID = 1L;
    private Thread panelThread;
    private List<Bird> pigeons = null;
    private List<Food> foods = null;


    public Thread getPanelThread() {
        return panelThread;
    }



    public List<Bird> getPigeons(){
        return pigeons;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFood(Food food, int index) {
        this.foods.set(index,food);
    }

    public Panneau(List<Bird> pigeons){
        addMouseListener(this);
        this.pigeons = pigeons;
        if(foods==null) foods = new ArrayList<Food>();
        this.start();


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(0, 600, 800, 600);
        g.drawLine(800, 0, 800, 600);
        if(foods != null && !foods.isEmpty()) {
            int x,y;
            for(Food f : foods) {
                x = f.getX();
                y = f.getY();
                /*if(!f.getIsEaten()) {
                    try {
                        Image burger;
                        if(f.getIsFresh()) {
                            burger = ImageIO.read(new File("burger.png"));
                        }
                        else {
                            burger = ImageIO.read(new File("trashburger.png"));
                        }
                        g.drawImage(burger, x, y, this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        }

        if (pigeons != null && !pigeons.isEmpty()) {
            int x,y;
            for (Bird p : pigeons) {
                if(pigeons != null) {
                    x = p.getX();
                    y = p.getY();
                    try {

                        Image pigeon = ImageIO.read(new File("pigeon.png"));
                        g.drawImage(pigeon, x, y, this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
/*
        if (pedestrian != null) {
            if(!pedestrian.getIsArrived()) {
                try {
                    Image deadpool = ImageIO.read(new File("deadpool.png"));
                    g.drawImage(deadpool, pedestrian.getX(), pedestrian.getY(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                pedestrian = null;
            }

        }*/
    }

    @Override
    public void mouseClicked(MouseEvent input) {
        int button = input.getButton();
        int x = input.getX();
        int y = input.getY();
        if(x >= 0 && x < 800 && y >= 0 && y < 600 ) {

            switch (button){
                case MouseEvent.BUTTON1:
                    foods.add(new Food(x,y));
                    break;
                case MouseEvent.BUTTON3:
                    break;
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {

    }

    private void start() {
        if (panelThread == null) {
            panelThread = new Thread(this, "Window");
            panelThread.start();
        }
    }

    @Override
    public void run() {
        while(true) {
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
