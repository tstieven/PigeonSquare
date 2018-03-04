public class Food {

    public int foodGoodLimit;

    public int x;
    public int y;

    public long spawningTime;

    public Food (int x, int y){
        this.x = x;
        this.y = y;
        this.spawningTime = System.currentTimeMillis();
    }

    //return if the food is still good
    public boolean isGood(){
        return System.currentTimeMillis() - this.spawningTime < foodGoodLimit;
    }
}
