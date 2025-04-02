package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;
    public Grid(int size) {
        this.size = size;
        grid = new Sprite[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int k = 0; k < grid[i].length; k++) {//fills created 2d array with Dot objects
                grid[i][k] = new Dot(i, k);
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}//returns grid



    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size - 1 - s.getY()][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        int x = s.getX();//converts coords to indices
        int y = size - 1 - s.getY();
        grid[y][x] = s;//places player in location
        switch (direction) {//gets previous location
            case "w":
                y++;
                break;
            case "a":
                x++;
                break;
            case "s":
                y--;
                break;
            case "d":
                x--;
                break;
        }
        grid[y][x] = new Dot(x, size - 1 - y);//fills previous location with dot
    }


    public void display() { //print out the current grid to the screen
        String res = "";
        for (Sprite[] x: grid) {//for each line
            for (Sprite s: x) {//adds the emojis⬜
                if (s instanceof Dot) {
                    res += "⬛";
                }else if (s instanceof Wall) {
                    res += "⬜";
                }else if (s instanceof Enemy) {
                    res += "🦂";
                }else if (s instanceof Player) {
                    res += "🦄";
                }else if (s instanceof Trophy) {
                    res += "🏆";
                }else {
                    res += "🌈";
                }
            }
            res += "\n";//moves to next line
        }
        res += "WASD to move: ";
        System.out.print(res);
    }
    
    public void gameover(){ //use this method to display a loss
        String res = "";
        for (Sprite[] x: grid) {//adds the emojis
            for (Sprite s: x) {
                if (s instanceof Player) {
                    res += "🦄";
                }else {
                    res += "💀";
                }
            }
            res += "\n";
        }
        res += "YOU LOSE";
        System.out.println(res);//moves to next line
    }

    public void win(){ //use this method to display a win 
        String res = "";
        for (Sprite[] x: grid) {//adds the emojis
            for (Sprite s: x) {
                if (s instanceof Player) {
                    res += "🦄";
                }else {
                    res += "🌈";
                }
            }
            res += "\n";//moves to next line
        }
        res += "YOU WIN";
        System.out.println(res);
    }


}