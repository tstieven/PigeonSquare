package PigeonSquare;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

public class Fenetre extends JFrame {
    private static final long serialVersionUID = 1L;
    public static int width = 800;
    public static int height = 600;
    private Panneau panneau;


    public Fenetre(int numberBiset, int numberColombin, int numberRamier){
        panneau = new Panneau(initBird(numberBiset,numberColombin,numberRamier));
        this.setTitle("PigeonSquare");
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panneau);
        this.setVisible(true);
        loop();



    }

    public static List<Bird> initBird(int b, int c, int r){
        List<Bird> birds = new ArrayList<>();
        for(int i = 0; i < b; i++){
            birds.add(new Biset());
            System.out.println(birds.size());
        }
        for(int i = 0 ; i<c ; i++){
            birds.add(new Colombin());
        }
        for (int i = 0; i<r;i++){
            birds.add(new Ramier());
        }
        System.out.println(birds.size());
        return birds;
    }

    private void loop(){
        while(true){
            try{
                Thread.sleep(30);
            }
            catch( InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String args[]){
        new Fenetre(2,3,4);

    }

}
