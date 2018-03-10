package PigeonSquare;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

public class Fenetre {
    private static final long serialVersionUID = 1L;
    private static int width = 800;
    private static int heigth = 600;
    private Panneau panneau;

    public static int getHeigth() {
        return heigth;
    }

    public static int getWidth() {
        return width;
    }

    public Fenetre(int numberBiset, int numberColombin, int numberRamier){
        panneau = new Panneau(initBird(numberBiset,numberColombin,numberRamier));
    }

    private List<Bird> initBird(int b, int C, int R){
        List<Bird> list = new ArrayList<>();
        for(int i = 0; i < b; i++){
            list.add(new Biset());
        }
        return list;
    }



}
