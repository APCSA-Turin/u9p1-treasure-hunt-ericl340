package com.example.project;
public class Wall extends Sprite {
    public Wall(int x, int y) {
        super(x, y);
    }
    public String getRowCol(int size){ //return "Wall:"+row col
    return "Wall:" + super.getRowCol(size);
}
}
