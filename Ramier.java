public class Ramier extends Bird {

    public Ramier(int xpos, int ypos, World world){
        x = xpos;
        y = ypos;
        maxSpeed = 2;
        maxFear = 4;
        this.gameBoard = world;
    }
}
