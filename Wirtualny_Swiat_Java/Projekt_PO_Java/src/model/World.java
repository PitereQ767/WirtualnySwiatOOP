package model;

public class World {
    private final int width, height;

    public World(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

}
