package com.example.project;

//only needs a constructor
public class Treasure extends Sprite { //child of Sprite
    public Treasure(int x, int y) {
        super(x, y);
    }
    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        return getClass().getSimpleName() + ":" + super.getRowCol(size);
    }
}