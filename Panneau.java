package PigeonSquare;

import javafx.scene.shape.Circle;
import javax.swing.*;
import java.awt.*;
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
    private static List<Bird> pigeons = null;
    //représente les nourritures mangeables
    private static List<Food> foods = new ArrayList<Food>();
    //représente les nourritures non mangeables
    private static List<Food> badFood = new ArrayList<Food>();

    public static List<Food> getBadFood() {
        return badFood;
    }

    public static void setBadFood(List<Food> badFood) {
        Panneau.badFood = badFood;
    }




    public Thread getPanelThread() {
        return panelThread;
    }



    public static List<Bird> getPigeons(){
        return pigeons;
    }

    public static List<Food> getFoods() {
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
                if (!f.getEaten()) {
                    x = f.getX();
                    y = f.getY();


                    if (f.isGood()) {
                        drawCircleByCenter(g, x, y, 10, new Color(0, 255, 0));
                    } else {
                        drawCircleByCenter(g, x, y, 10, new Color(255, 0, 0));
                    }
                }


            }
        }

        if(badFood != null && !badFood.isEmpty()){
            int x,y;
            for(Food f : badFood) {
                if (!f.getEaten()) {
                    x = f.getX();
                    y = f.getY();


                    if (f.isGood()) {
                        drawCircleByCenter(g, x, y, 10, new Color(0, 255, 0));
                    } else {
                        drawCircleByCenter(g, x, y, 10, new Color(255, 0, 0));
                    }
                }


            }
        }

        if (pigeons != null && !pigeons.isEmpty()) {
            int x,y;
            for (Bird p : pigeons) {
                if(pigeons != null) {
                    x = p.getX();
                    y = p.getY();

                    switch (p.getCat()){

                        case 1:
                            drawCircleByCenter(g,x,y,p.getSize()/2,new Color(51,255,255));
                            break;
                        case 2 :
                            drawCircleByCenter(g,x,y,p.getSize()/2,new Color(51,153,255));
                            break;
                        case 3:
                            drawCircleByCenter(g,x,y,p.getSize()/2,new Color(51,51,255));
                            break;

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

    void drawCircleByCenter(Graphics g, int x, int y, int radius, Color color){
        g.setColor(color);
        g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
    }


    @Override
    public void mouseClicked(MouseEvent input) {
        int button = input.getButton();
        int x = input.getX();
        int y = input.getY();
        if(x >= 0 && x < 800 && y >= 0 && y < 600 ) {

            switch (button){
                case MouseEvent.BUTTON1:
                    Food tmpFood = new Food(x,y);
                    foods.add(tmpFood);
                    break;
                case MouseEvent.BUTTON3:
                    Caillou tmpCaillou = new Caillou(x,y);
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

    //TODO try catch ???
    // Remove given PigeonSquare.Food
    public void removeFood(Food f) {
        System.out.println(foods.size());
        //this.foods.remove(this.foods.indexOf(f));
        System.out.println(foods.size());
    }


}


