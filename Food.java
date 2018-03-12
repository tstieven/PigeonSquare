package PigeonSquare;

import java.util.Timer;
import java.util.TimerTask;

public class Food {

    private int foodGoodLimit=4000;



    private int x;
    private int y;

    public Boolean getEaten() {
        return isEaten;
    }

    public void setEaten(Boolean eaten) {
        isEaten = eaten;
    }

    private Boolean isEaten = false;

    private long spawningTime;

    public Food (int x, int y){
        this.setX(x);
        this.setY(y);
        this.spawningTime = System.currentTimeMillis();
        getsTooOld();
    }

    private boolean isGood = true;
    private Timer timer = new Timer();

    private void getsTooOld() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if( Panneau.getFoods().contains(Food.this)) {
                    isGood = false;
                    Panneau.getBadFood().add(Food.this);
                    Panneau.getFoods().remove(Food.this);
                }
            }
        };
        timer.schedule(task, foodGoodLimit);
    }

    //return if the food is still good
    public boolean isGood(){

        return isGood;
    }

    public int getFoodGoodLimit() {
        return foodGoodLimit;
    }

    public void setFoodGoodLimit(int foodGoodLimit) {
        this.foodGoodLimit = foodGoodLimit;
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public Food clone(){

        Food tmp = new Food(x,y);
        tmp.spawningTime = this.spawningTime;
        tmp.isEaten = this.isEaten;
        return tmp;

    }

}
