package PigeonSquare;

class Caillou {
    private int x;
    private int y;
    private long spawningTime;
    private int size = 100;


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

    public Caillou(int x, int y){
        this.x = x;
        this.y = y;
        this.spawningTime = System.currentTimeMillis();
        for (Bird bird : Panneau.getPigeons()){
            double dist = Math.pow(bird.getX()-this.x, 2.0)+Math.pow(bird.getY()-this.y, 2.0);
            if (dist<Math.pow(this.size,2)) {
                bird.setAfraid(true, this.spawningTime);
            }
        }
    }
}
