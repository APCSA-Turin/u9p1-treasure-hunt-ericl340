package com.example.project;

import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Wall[] walls;
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
        Sprite[][] g;//creates the variable to store the grid
        int intialHP = player.getLives();
        while(true){
            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.display();//displays the grid
            System.out.println("Health: " + player.getLives() + "❤️" + "  Treasures Collected: " + player.getTreasureCount() + "/" + treasures.length);
            System.out.print("WASD to move: ");
            String direction = scanner.next();//gets user input
            if (direction.equals("q")) {//forfeit on q
                scanner.close();
                break;
            }
            if (player.isValid(size, direction)) {//checks if movement is allowed
                g = grid.getGrid();//sets g to the grid
                int x = player.getX();//gets grid indices
                int y = size - player.getY() - 1;
                switch (direction) {//changes coords based off direction
                    case "w"://nessesary to not fail test cases since can't modify is valid to check walls
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
                Sprite obj = g[y][x];//gets object at location player is attempting to move to
                if (!(obj instanceof Wall)) {
                    player.interact(10, direction, treasures.length, obj);//interacts with objects
                    player.move(direction);//sets player coords
                    grid.placeSprite(player, direction);//update coords on grid
                    if (player.getWin() || player.getLives() <= 0) {//moves on when game ends
                        scanner.close();
                        break;
                    }
                    grid.placeSprite(trophy);//makes sure trophy doesn't get deleted
                }
            }
        }
        clearScreen();//renders W/L screen
        if (player.getWin()) {
            grid.win();
        }else {
            grid.gameover();
        }
    }

    public void initialize(){//places the sprites
        grid = new Grid(size);//creates all the sprite objects
        player = new Player(0, 0);
        enemies = new Enemy[9];
        enemies[0] = new Enemy(1, 2);
        enemies[1] = new Enemy(3, 2);
        enemies[2] = new Enemy(4, 6);
        enemies[3] = new Enemy(5, 6);
        enemies[4] = new Enemy(6, 3);
        enemies[5] = new Enemy(7, 5);
        enemies[6] = new Enemy(7, 0);
        enemies[7] = new Enemy(9, 3);
        enemies[8] = new Enemy(9, 9);
        treasures = new Treasure[5];
        treasures[0] = new Treasure(2, 0);
        treasures[1] = new Treasure(4, 0);
        treasures[2] = new Treasure(5, 0);
        treasures[3] = new Treasure(6, 5);
        treasures[4] = new Treasure(8, 3);
        walls = new Wall[33];
        walls[0] = new Wall(1, 8);
        walls[1] = new Wall(1, 6);
        walls[2] = new Wall(1, 5);
        walls[3] = new Wall(1, 4);
        walls[4] = new Wall(1, 3);
        walls[5] = new Wall(1, 0);
        walls[6] = new Wall(2, 8);
        walls[7] = new Wall(2, 2);
        walls[8] = new Wall(3, 8);
        walls[9] = new Wall(3, 7);
        walls[10] = new Wall(3, 6);
        walls[11] = new Wall(3, 5);
        walls[12] = new Wall(3, 4);
        walls[13] = new Wall(3, 1);
        walls[14] = new Wall(3, 0);
        walls[15] = new Wall(4, 8);
        walls[16] = new Wall(4, 7);
        walls[17] = new Wall(4, 3);
        walls[18] = new Wall(5, 5);
        walls[19] = new Wall(6, 8);
        walls[20] = new Wall(6, 7);
        walls[21] = new Wall(6, 6);
        walls[22] = new Wall(6, 2);
        walls[23] = new Wall(6, 0);
        walls[24] = new Wall(7, 8);
        walls[25] = new Wall(7, 4);
        walls[26] = new Wall(7, 3);
        walls[27] = new Wall(8, 7);
        walls[28] = new Wall(8, 6);
        walls[29] = new Wall(8, 5);
        walls[30] = new Wall(8, 2);
        walls[31] = new Wall(8, 0);
        walls[32] = new Wall(9, 2);
        trophy = new Trophy(9, 0);
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        grid.placeSprite(player);//places all the sprites into the grid
        for (Enemy e: enemies) {
            grid.placeSprite(e);
        }
        for (Treasure t: treasures) {
            grid.placeSprite(t);
        }
        for (Wall w: walls) {
            grid.placeSprite(w);
        }
        grid.placeSprite(trophy);
    }

    public static void main(String[] args) {
        new Game(10);//creates a game
    }
}