import java.util.Random;

public abstract class Bird implements Runnable {

    //PigeonSquare.Bird position
    private int x;
    private int y;
    private int eaten = 0;
    private Food target = null;



    //TODO define if name is useless or not
    //protected String name;
    private int pigeonID;
    private int size;
    private int cat;
    private int maxSpeed = 10;

    private Thread pThread;



    private int maxFear = 10;

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

    public int getMaxFear() {
        return maxFear;
    }


    public int getSpeed() {
        return speed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMaxFear(int maxFear) {
        this.maxFear = maxFear;
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



    //TODO
    //Update bird's position to move closer to good food
    public void move(){

        searchFood();
        if(target != null){

            this.x = (target.getX() < this.x) ? this.x - this.speed/2 : this.x + this.speed/2;
            this.y = (target.getY() < this.y) ? this.y - this.speed/2 : this.y + this.speed/2;

        }
        refreshWindow();
    }

    //TODO
    // Search in the environnement the closest good food
    public void searchFood(){
        //target = gameBoard.getNearestGoodFood(getX(), getY());
        double min = -1;
        Food tmpFood = null;

        for(Food food:Panneau.getFoods()){
            if (food.isGood()){
                double tmp = Math.pow(food.getX()-this.x, 2.0)+Math.pow(food.getY()-this.y, 2.0);
                if (min < 0 || min > tmp){
                    tmpFood = food;
                    min = tmp;
                }
            }
        }
        target = tmpFood;
    }

    //TODO
    // Has to test if the food is still available and update the environnement if the food is eaten
    public void eat(){
        gameBoard.removeFood(target);
        target = null;
        eaten ++;

    }

    //TODO
    // Determine if the bird is afraid
    public boolean getAfraid(){
        return false;
    }

    //TODO
    // Update bird's position if it's afraid
    public void fear(){
        refreshWindow();
    }

    //TODO
    // Set the bird to a sleep mode
    public void sleep(){

    }

    //TODO
    // Refresh the graphic window with new position
    public void refreshWindow(){

    }

    public void start(){
        if (pThread == null){
            pThread = new Thread(this, "Pigeon " + pigeonID);
            pThread.start();
        }
    }

    @Override
    public void run() {
        while (true) {

            if (getAfraid()){
                fear();
            }else{
                System.out.println("Foods: "+Panneau.getFoods().size());
                if (Panneau.getFoods().size() != 0){
                    move();
                    if (target != null && target.getX() < getSize() /2 && target.getY() < getSize() /2){
                        eat();
                    }
                }else{
                    sleep();
                }

            }

        }
    }


}
