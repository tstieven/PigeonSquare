package PigeonSquare;

public class Food {

    private int foodGoodLimit;

    private int x;
    private int y;

    private long spawningTime;

    public Food (int x, int y){
        this.setX(x);
        this.setY(y);
        this.spawningTime = System.currentTimeMillis();
    }


    //return if the food is still good
    public boolean isGood(){
        return System.currentTimeMillis() - this.spawningTime < getFoodGoodLimit();
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
