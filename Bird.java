package PigeonSquare;
import java.util.ArrayList;
import java.util.Random;

public abstract class Bird implements Runnable {

    //PigeonSquare.Bird position
    private int x;
    private int y;
    private int eaten = 0;
    private Food target = null;
    private Food belief = null;
    private double targetDistance;
    private long afraidTime;
    private long afraidSpawnTime;
    private int pigeonID;
    private int size;
    private int cat;
    private int maxSpeed = 15;

    private Thread pThread;


    public boolean isAfraid() {
        return isAfraid;
    }

    public void setAfraid(boolean afraid, long afraidSpawnTime) {
        isAfraid = afraid;
        Random rand = new Random();
        afraidTime = rand.nextInt(2000);
        this.afraidSpawnTime = afraidSpawnTime;
        System.out.println(pigeonID);
    }

    private boolean isAfraid = false;

    private int speed;

    private static int pigeonCount;

    private Panneau gameBoard;

    public Thread getPThread() { return pThread; }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public int getPigeonID() {return pigeonID;}

    public void setPigeonID(int id) {this.pigeonID = id;}

    public int getPigeonCount(){
        return pigeonCount;
    }


    public int getSpeed() {
        return speed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Bird(){
        Random rand = new Random();
        x = rand.nextInt(Fenetre.width-30)+15;
        y = rand.nextInt(Fenetre.height-20)+10;
        speed = rand.nextInt(this.maxSpeed/2)+this.maxSpeed/2;
        pigeonCount++;
        this.pigeonID = pigeonCount;
        this.start();
    }

    public Bird(int x, int y){
        Random rand = new Random();
        this.x = x;
        this.y = y;
        speed = rand.nextInt(this.maxSpeed/2)+this.maxSpeed/2;
        pigeonCount++;
        this.pigeonID = pigeonCount;
        this.start();
    }



    public String toString(){
        return this.getClass().getName() + " en position ("+this.x+","+this.y+")" ;
    }



    //Update bird's position to move closer to good food
    public void move(){
        searchFood();
        if(belief != null){
            this.x = (belief.getX() < this.x) ? this.x - this.speed/2 : this.x + this.speed/2;
            this.y = (belief.getY() < this.y) ? this.y - this.speed/2 : this.y + this.speed/2;
        }
    }


    // Search in the environnement the closest good food
    public void searchFood(){
        //target = gameBoard.getNearestGoodFood(getX(), getY());
        double min = -1;
        Food tmpFood = null;

        ArrayList<Food> tmpList = new ArrayList<>();
        tmpList.addAll(Panneau.getFoods());
        for(Food food:tmpList){
            if (food != null && food.isGood()){
                double tmp = Math.pow(food.getX()-this.x, 2.0)+Math.pow(food.getY()-this.y, 2.0);
                if (min < 0 || min > tmp){
                    tmpFood = food;
                    min = tmp;
                }
            }
        }
        targetDistance = min;
        target = tmpFood;
        if (target != null) {
            belief = target.clone();
        }
    }


    // Has to test if the food is still available and update the environnement if the food is eaten
    public void eat(){
        try {
            if(target != null){
                Panneau.getFoods().remove(target);
            }
        }catch (Exception e){
            System.out.println("Trop tard, Pigeon n°"+this.pigeonID);
        }
        targetDistance = 0;
        eaten ++;

    }


    // Update bird's position if it's afraid
    public void fear(){
        if (System.currentTimeMillis() > this.afraidTime+this.afraidSpawnTime){
            setAfraid(false,0);
        }else{
            Random rand = new Random();
            int xAlea = rand.nextInt(800);
            int yAlea = rand.nextInt(600);
            this.x = (xAlea < this.x) ? this.x - this.speed/2 : this.x + this.speed/2;
            this.y = (yAlea < this.y) ? this.y - this.speed/2 : this.y + this.speed/2;
        }
    }





    public void start(){
        if (pThread == null){
            pThread = new Thread(this, "Pigeon " + pigeonID);
            pThread.start();
        }
    }


    @Override
    public void run() {
        Random rand = new Random();
        while (true) {

            if (this.isAfraid){
                fear();
            }else{
                if (Panneau.getFoods().size() > 0){
                    move();
                    double tmp = Math.pow(belief.getX()-this.x, 2.0)+Math.pow(belief.getY()-this.y, 2.0);
                    if (target != null && tmp < this.getSize()){
                        eat();
                    }

                }

            }
            try{
                Thread.sleep(50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }



        }
    }


}
