public abstract class Bird implements Runnable {

    //Bird position
    public int x;
    public int y;
    private int eaten = 0;

    //TODO define if name is useless or not
    //protected String name;
    public int size;
    public int maxSpeed;
    public int maxFear;


    public World gameBoard;
    public boolean foodAvailable = false;

    //Closest good food position
    public int xFood;
    public int yFood;

    //TODO
    //Update bird's position to move closer to good food
    public void move(){

        refreshWindow();
    }

    //TODO
    // Search in the environnement the closest good food
    public void searchFood(){
        gameBoard.getNearestGoodFood(x,y);
    }

    //TODO
    // Has to test if the food is still available and update the environnement if the food is eaten
    public void eat(){
        if (gameBoard.isGoodFood(x,y)){
            gameBoard.removeFood(x,y);
            eaten ++;
        }
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


    @Override
    public void run() {
        while (true) {
            searchFood();
            if (getAfraid()){
                fear();
            }else{
                if (foodAvailable){
                    move();
                    if ((xFood < size/2) && (yFood < size/2)){
                        eat();
                    }
                }else{
                    sleep();
                }

            }

        }
    }

}
