package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2
        super(x, y);
        numLives = 2;
    }


    public int getTreasureCount(){return treasureCount;}//returns treasureCount
    public int getLives(){return numLives;}//returns numLives
    public boolean getWin(){return win;}//returns win

  
    //move method should override parent class, sprite
    public void move(String direction) { //move the (x,y) coordinates of the player
        switch (direction) {//changes coords based off direction
            case "w":
                setY(getY() + 1);
                break;
            case "a":
                setX(getX() - 1);
                break;
            case "s":
                setY(getY() - 1);
                break;
            case "d":
                setX(getX() + 1);
                break;
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
        if (!(obj instanceof Dot)) {//make sure obj is something interactable
            if (obj instanceof Enemy) {//decreases hp for enemies
                numLives--;
            }else if (obj instanceof Trophy){
                if (treasureCount == numTreasures) {//only win if all treasures have been collected
                    win = true;
                }
            }else{//everything else is a normal treasure so increases count
                treasureCount++;
            }
        }
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        int t;
        switch (direction) {//seats t to the changed coord value
            case "w":
                t = getY() + 1;
                break;
            case "a":
                t = getX() - 1;
                break;
            case "s":
                t = getY() - 1;
                break;
            case "d":
                t = getX() + 1;
                break;
            default:
                return false;//returns false for invalid direction
        }
        return t >= 0 && t < size;//returns true only if its within the size of the grid
    }

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)" override for player
        return "Player:" + super.getCoords();
    }
    public String getRowCol(int size){ //returns the row and column of the player -> "Player:[row][col]"
        return "Player:" + super.getRowCol(size);
    }
}



