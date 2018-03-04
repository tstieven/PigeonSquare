import java.util.ArrayList;

public class World {

    private ArrayList<Food> foods;

    public World(){
        this.foods = new ArrayList<Food>();
    }

    // Add food to given position
    public void addFood(int x, int y){
        foods.add(new Food(x,y));
    }

    //TODO try catch ???
    // Remove given Food
    public void removeFood(int x, int y){
        Food tmpFood = null;
        boolean test =false ;
       for(Food food : foods){
           if ((food.x == x) && (food.y == y)) {
               tmpFood = food;
               test= true;
           }
        }
        if (test){
            foods.remove(tmpFood);
        }
    }

    //TODO try catch ???
    // Remove given Food
    public void removeFood(Food f){
        foods.remove(f);
    }


    // Remove all old Food
    public void cleanOldFood(){
        ArrayList<Food> tmpFood = new ArrayList<>();

        for(Food food: foods){
            if (food.isGood()){
                tmpFood.add(food);
            }
        }

        for (Food food : tmpFood){
            removeFood(food);
        }

    }

    // Return if there is Food on this position
    public boolean isGoodFood(int x, int y){
        boolean test = false;
        for(Food food : foods) {
            if ((food.x == x) && (food.y == y) && (food.isGood())) {
                test = true;
            }
        }
        return test;
    }

    // Return nearest Food from given position
    public Food getNearestGoodFood(int x, int y){
        int min = 999999;
        Food tmpFood = null;

        for(Food food:foods){
            if (food.isGood()){
                int tmp= food.x *food.x + food.y + food.y;
                if (min > tmp){
                    tmpFood = food;
                    min = tmp;
                }
            }
        }
       return tmpFood;
    }
}
