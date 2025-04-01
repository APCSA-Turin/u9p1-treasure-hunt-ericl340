package com.example.project;

import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                //System.out.print("\033[H\033[2J");
                //System.out.flush();
                new ProcessBuilder("clear").inheritIO().start().waitFor();
                //uses bash clear command, original doesn't clear first line
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);
        Sprite[][] g;
        while(true){
            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.display();
            String direction = scanner.next();
            if (direction.equals("q")) {//forfeit on q
                break;
            }
            if (player.isValid(size, direction)) {
                g = grid.getGrid();
                int x = player.getX();
                int y = size - player.getY() - 1;
                switch (direction) {//changes coords based off direction
                    case "w":
                        y--;
                        break;
                    case "a":
                        x--;
                        break;
                    case "s":
                        y++;
                        break;
                    case "d":
                        x++;
                        break;
                }
                player.interact(10, direction, treasures.length, g[y][x]);
                player.move(direction);
                grid.placeSprite(player, direction);
                if (player.getWin() || player.getLives() <= 0) {//moves on when game ends
                    scanner.close();
                    break;
                }
                grid.placeSprite(trophy);//makes sure trophy doesn't get deleted
            }
        }
        clearScreen();
        if (player.getWin()) {
            grid.win();
        }else {
            grid.gameover();
        }
    }

    public void initialize(){
        grid = new Grid(size);
        player = new Player(0, 0);
        enemies = new Enemy[2];
        enemies[0] = new Enemy(5, 5);
        enemies[1] = new Enemy(7, 8);
        treasures = new Treasure[2];
        treasures[0] = new Treasure(2, 2);
        treasures[1] = new Treasure(0, 7);
        trophy = new Trophy(9, 9);
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        grid.placeSprite(player);
        for (Enemy e: enemies) {
            grid.placeSprite(e);
        }
        for (Treasure t: treasures) {
            grid.placeSprite(t);
        }
        grid.placeSprite(trophy);
    }

    public static void main(String[] args) {
        Game g = new Game(10);
    }
}