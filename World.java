import java.util.ArrayList;

public class World {

    private ArrayList<Food> foods;


    public World() {

            this.foods = new ArrayList<Food>();
    }

    // Add food to given position
    public void addFood(int x, int y) {
        foods.add(new Food(x, y));
    }

    //TODO try catch ???
    // Remove given PigeonSquare.Food
    public void removeFood(int x, int y) {
        Food tmpFood = null;
        boolean test = false;
        for (Food food : foods) {
            if ((food.getX() == x) && (food.getY() == y)) {
                tmpFood = food;
                test = true;
            }
        }
        if (test) {
            foods.remove(tmpFood);
        }
    }

    //TODO try catch ???
    // Remove given PigeonSquare.Food
    public void removeFood(Food f) {
        foods.remove(f);
    }


    // Remove all old PigeonSquare.Food
    public void cleanOldFood() {
        ArrayList<Food> tmpFood = new ArrayList<>();

        for (Food food : foods) {
            if (food.isGood()) {
                tmpFood.add(food);
            }
        }

        for (Food food : tmpFood) {
            removeFood(food);
        }

    }

    // Return if there is PigeonSquare.Food on this position
    public boolean isGoodFood(int x, int y) {
        boolean test = false;
        for (Food food : foods) {
            if ((food.getX() == x) && (food.getY() == y) && (food.isGood())) {
                test = true;
            }
        }
        return test;
    }

// Return nearest PigeonSquare.Food from given position
    public Food getNearestGoodFood(int x, int y){
        double min = -1;
        Food tmpFood = null;

        for(Food food:foods){
            if (food.isGood()){
                double tmp = Math.pow(food.getX()-x, 2.0)+Math.pow(food.getY()-y, 2.0);
                if (min < 0 || min > tmp){
                    tmpFood = food;
                    min = tmp;
                }
            }
        }
        return tmpFood;
    }

}
