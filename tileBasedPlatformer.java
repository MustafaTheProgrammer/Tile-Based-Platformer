import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.ArrayList;

class tileBasedPlatformer extends JFrame implements KeyListener{
    //variables
    public static int width = 50;
    public static int height = 25;
    public static char[][] pixels = new char[height][width];
    public static boolean leftKey;
    public static boolean rightKey;
    public static boolean upKey;
    public static boolean downKey;
    public static int[] pos = {22, -10};
    public static int playerHeight = 4;
    public static int playerHp = 4;
    public static int iframes = 0;
    public static int[] camPos = {14, -5};
    public static int camXVel = 0;
    public static int[] camVel = {1, 1};
    public static int falling = 0;
    public static int animationTimer = 0;
    public static float timer = 0.0f;
    public static ArrayList<Integer> tiles = new ArrayList<Integer>();
    public static String gameState = "game";

    //level
    public static int[][] level = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 4, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 0, 3, 1, 0, 3, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0},
                                   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 0, 0, 0, 1, 1, 1, 5, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                   {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 4, 0, 0, 0, 5, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 4, 4, 4, 0, 0, 3, 3, 0, 0, 2, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                   {1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 0, 5, 5, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    /*
    
    1 = ####    2 =         3 =         4 = xxxx    5 = ^^^^    6 = ****
`       ####                                xxxx        ^^^^        ****
        ####        ####        xxxx                    ^^^^        ****
        ####        ####        xxxx                    ^^^^        ****

    */

    //sprites
    public static char[][] tile1 = {{'_', '_', '_', '_'},
                                    {'v', 'v', 'v', 'v'},
                                    {'#', '#', '#', '#'},
                                    {'#', '#', '#', '#'}};

    public static char[][] tile2 = {{'_', '_', '_', '_'},
                                    {'v', 'v', 'v', 'v'}};

    public static char[][] tile3 = {{'x', 'x', 'x', 'x'},
                                    {'X', 'X', 'X', 'X'}};

    public static char[][] tile4 = {{'X', 'X', 'X', 'X'},
                                    {'x', 'x', 'x', 'x'}};

    public static char[][] tile5 = {{'_', '_', '_', '_'},
                                    {'|', '/', '\\', '|'},
                                    {'|', '|', '|', '|'},
                                    {'-', '-', '-', '-'}};

    public static char[][] tile6 = {{'x', 'x', '*', 'x'},
                                    {'*', '*', '*', '*'},
                                    {'x', '*', '*', 'x'},
                                    {'*', 'x', '*', 'x'}};

    public static char[][] playerGround = {{'x', '_', '_', '_'},
                                           {'|', 'o', 'x', 'o'},
                                           {'|', 'x', 'x', '|'},
                                           {'-', '-', '-', '-'}};
    
    public static char[][] playerJump = {{'x', 'o', 'x', 'o'},
                                           {'|', 'x', 'x', '|'},
                                           {'|', 'x', 'x', '|'},
                                           {'-', '-', '-', '-'}};
    
    public static char[][] playerCrouch = {{'x', 'x', 'x', 'x'},
                                           {'x', 'x', 'x', 'x'},
                                           {'_', '_', '_', '_'},
                                           {'-', '-', '-', '-'}};

    public static char[][] playerHit = {{'x', '_', '_', '_'},
                                        {'|', '>', 'x', '<'},
                                        {'|', 'x', 'x', '|'},
                                        {'-', '-', '-', '-'}};

    public static char[][] playerDead1 = {{'x', '_', '_', '_'},
                                          {'|', '.', 'x', '.'},
                                          {'|', 'x', 'x', '|'},
                                          {'-', '-', '-', '-'}};

    public static char[][] playerDead2 = {{'x', '*', '*', 'x'},
                                          {'*', '*', '*', '*'},
                                          {'*', '*', '*', '*'},
                                          {'x', '*', '*', 'x'}};

    public static char[][] playerDead3 = {{'x', '*', '*', 'x'},
                                          {'*', 'x', 'x', '*'},
                                          {'*', 'x', 'x', '*'},
                                          {'x', '*', '*', 'x'}};

    public static char[][] gameOverScreen = {{'G', 'A', 'M', 'E', 'x', 'O', 'V', 'E', 'R'},
                                             {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                                             {'x', 'x', 'x', 'x', 'x', 'x', '_', '_', '_'},
                                             {'x', 'x', 'x', 'x', 'x', '|', 'U', 'x', 'U'},
                                             {'x', 'x', 'x', 'x', 'x', '|', 'x', 'x', '|'},
                                             {'x', 'x', 'x', 'x', 'x', '-', '-', '-', '-'}};

    public static char[][] winScreen = {{'Y', 'O', 'U', 'x', 'W', 'I', 'N', '!', '!'},
                                        {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                                        {'x', 'x', 'x', 'x', 'x', 'x', '_', '_', '_'},
                                        {'x', 'x', 'x', 'x', 'x', '|', '^', 'x', '^'},
                                        {'x', 'x', 'x', 'x', 'x', '|', 'x', 'x', '|'},
                                        {'x', 'x', 'x', 'x', 'x', '-', '-', '-', '-'}};

    public tileBasedPlatformer(){
        this.setTitle("tile based platformer");
        this.setSize(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
        gameLoop();
    }

    static void gameLoop(){
        //add level tiles
        for (int i = 0; i < level.length; i++){
            for (int j = 0; j < level[0].length; j++){
                if (level[i][j] == 1){
                    addTile(4*j, -4*i, 1);
                }
                else if (level[i][j] == 2){
                    addTile(4*j, -4*i, 2);
                }
                else if (level[i][j] == 3){
                    addTile(4*j, -4*i, 3);
                }
                else if (level[i][j] == 4){
                    addTile(4*j, -4*i, 4);
                }
                else if (level[i][j] == 5){
                    addTile(4*j, -4*i, 5);
                }
                else if (level[i][j] == 6){
                    addTile(4*j, -4*i, 6);
                }
            }
        }
        
        while (1==1){
            //clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            //reset buffer
            for (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    pixels[i][j] = ' ';
                }
            }
            
            if (gameState == "game"){
                //player stuff
                if (playerHp > 0){
                    if (leftKey){
                        camXVel = 2;
                        camPos[0] += camXVel;
                    }
                    if (rightKey){
                        camXVel = -2;
                        camPos[0] += camXVel;
                    }

                    if (downKey && falling == 0){
                        playerHeight = 2;
                    }
                    else {
                        playerHeight = 4;
                    }

                    if (upKey && falling == 0 && playerHeight == 4){
                        camVel[1] = -5;
                    }

                    //animating player
                    if (iframes >= 11){
                        drawSprite(playerHit, pos[0], pos[1], 4, 4, 'x', camXVel > 0, true);
                    }
                    else{
                        if (falling > 0){
                            drawSprite(playerJump, pos[0], pos[1], 4, 4, 'x', camXVel > 0, true);
                        }
                        else{
                            if (downKey){
                                drawSprite(playerCrouch, pos[0], pos[1], 4, 4, 'x', camXVel > 0, true);
                            }
                            else{
                                drawSprite(playerGround, pos[0], pos[1], 4, 4, 'x', camXVel > 0, true);
                            }
                        }
                    }

                    camVel[1]++;
                    falling++;
                    camPos[1] += camVel[1];

                    if (iframes > 0){
                        iframes--;
                    }
                    if (camPos[1] > 35){
                    playerHp = 0;
                    }
                }
                else{
                    animationTimer++;
                    if (animationTimer <= 10){
                        drawSprite(playerDead1, pos[0], pos[1], 4, 4, 'x', camXVel > 0, true);
                    }
                    else if (animationTimer >= 11 && animationTimer <= 13){
                        drawSprite(playerDead2, pos[0], pos[1], 4, 4, 'x', false, true);
                    }
                    else if (animationTimer >= 14 && animationTimer <= 16){
                        drawSprite(playerDead3, pos[0], pos[1], 4, 4, 'x', false, true);
                    }
                    if (animationTimer > 25){
                        gameState = "game over";
                    }
                }
                
                //tiles
                for (int i = 0; i < tiles.size(); i += 3){
                    if (tiles.get(i+2) == 1){                        
                        if (pos[0] <= tiles.get(i) + 3 + camPos[0] && pos[0] + 3 >= tiles.get(i) + camPos[0] && pos[1] <= tiles.get(i+1) + 3 + camPos[1] && pos[1] + (playerHeight-1) >= tiles.get(i+1) + camPos[1]){
                            while (tiles.get(i+1) + 3 + camPos[1] >= pos[1]){
                                camPos[1]--;
                            }
                            camVel[1] = 0;
                            falling = 0;
                        }
                    }
                    if (tiles.get(i+2) == 2){                        
                        if (pos[0] <= tiles.get(i) + 3 + camPos[0] && pos[0] + 3 >= tiles.get(i) + camPos[0] && pos[1] <= tiles.get(i+1) + 1 + camPos[1] && pos[1] + (playerHeight-1) >= tiles.get(i+1) + camPos[1]){
                            while (tiles.get(i+1) + 1 + camPos[1] >= pos[1]){
                                camPos[1]--;
                            }
                            camVel[1] = 0;
                            falling = 0;
                        }
                    }
                    if (tiles.get(i+2) == 3){                        
                        if (pos[0] <= tiles.get(i) + 3 + camPos[0] && pos[0] + 3 >= tiles.get(i) + camPos[0] && pos[1] <= tiles.get(i+1) + 1 + camPos[1] && pos[1] + (playerHeight-1) >= tiles.get(i+1) + camPos[1]){
                            if (playerHp > 0 && iframes == 0){
                                playerHp--;
                                iframes = 15;
                            }
                        }
                    }
                    if (tiles.get(i+2) == 4){                        
                        if (pos[0] <= tiles.get(i) + 3 + camPos[0] && pos[0] + 3 >= tiles.get(i) + camPos[0] && pos[1] <= tiles.get(i+1) + 3 + camPos[1] && pos[1] + (playerHeight-1) >= tiles.get(i+1) + 2 + camPos[1]){
                            if (playerHp > 0 && iframes == 0){
                                playerHp--;
                                iframes = 15;
                            }
                        }
                    }
                    if (tiles.get(i+2) == 5){                        
                        if (pos[0] <= tiles.get(i) + 3 + camPos[0] && pos[0] + 3 >= tiles.get(i) + camPos[0] && pos[1] <= tiles.get(i+1) + 3 + camPos[1] && pos[1] + (playerHeight-1) >= tiles.get(i+1) + camPos[1]){
                            while (tiles.get(i+1) + 3 + camPos[1] >= pos[1]){
                                camPos[1]--;
                            }
                            camVel[1] = -6;
                        }
                    }
                    if (tiles.get(i+2) == 6){                        
                        if (pos[0] <= tiles.get(i) + 3 + camPos[0] && pos[0] + 3 >= tiles.get(i) + camPos[0] && pos[1] <= tiles.get(i+1) + 3 + camPos[1] && pos[1] + (playerHeight-1) >= tiles.get(i+1) + camPos[1]){
                            gameState = "end";
                        }
                    }
                    
                }
                //rendering tiles
                for (int i = 0; i < tiles.size(); i += 3){
                    if (tiles.get(i+2) == 1){
                        drawSprite(tile1, tiles.get(i)+camPos[0], tiles.get(i+1)+camPos[1], 4, 4, 'x', false, true);
                    }
                    if (tiles.get(i+2) == 2){
                        drawSprite(tile2, tiles.get(i)+camPos[0], tiles.get(i+1)+camPos[1], 4, 2, 'x', false, true);
                    }
                    if (tiles.get(i+2) == 3){
                        drawSprite(tile3, tiles.get(i)+camPos[0], tiles.get(i+1)+camPos[1], 4, 2, ' ', false, true);
                    }
                    if (tiles.get(i+2) == 4){
                        drawSprite(tile4, tiles.get(i)+camPos[0], tiles.get(i+1) + 2 + camPos[1], 4, 2, ' ', false, true);
                    }
                    if (tiles.get(i+2) == 5){
                        drawSprite(tile5, tiles.get(i)+camPos[0], tiles.get(i+1)+camPos[1], 4, 4, 'x', false, true);
                    }
                    if (tiles.get(i+2) == 6){
                        drawSprite(tile6, tiles.get(i)+camPos[0], tiles.get(i+1)+camPos[1], 4, 4, 'x', false, true);
                    }
                }
                
                //timer
                timer += 0.075;
            }
            else if (gameState == "game over"){
                drawSprite(gameOverScreen, 19, -15, 9, 6, 'x', false, true);
            }
            else if (gameState == "end"){
                drawSprite(winScreen, 19, -15, 9, 6, 'x', false, true);
            }

            //draw everything
            if (gameState == "game"){
                System.out.println("Health: " + playerHp + " Timer: " + Math.round(timer));
            }
            for (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    System.out.print(pixels[i][j]);
                }
                System.out.println();
            }
            if (gameState == "end"){
                System.out.print("Beaten in " + Math.round(timer) + " seconds");
            }

            //delay
            try{
                Thread.sleep(75);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args){
        new tileBasedPlatformer();
    }

    static void setPixel(int x, int y, char sym){
        if (x >= 0 && x <= width-1 && y <= 0 && y >= (height-1)*-1){
            pixels[Math.abs(y)][x] = sym;
        }
    }

    static void drawRect(int x, int y, int width, int height, char sym){
        for (int i = x; i < x + width; i++){
            for (int j = y; j < y + height; j++){
                setPixel(i, j, sym);
            }
        }
    }

    static void drawSprite(char[][] sprite, int x, int y, int width, int height, char transperant, boolean hFlip, boolean vFlip){
        for (int i = y; i < y + height; i++){
            for (int j = x; j < x + width; j++){
                if (!hFlip && !vFlip){
                    if (sprite[i-y][j-x] != transperant){
                        setPixel(j, i, sprite[i-y][j-x]);
                    }
                }
                else if (hFlip && !vFlip){
                    if (sprite[i-y][Math.abs(j-(x+(width-1)))] != transperant){
                        setPixel(j, i, sprite[i-y][Math.abs(j-(x+(width-1)))]);
                    }
                }
                else if (!hFlip && vFlip){
                    if (sprite[Math.abs(i-(y+(height-1)))][j-x] != transperant){
                        setPixel(j, i, sprite[Math.abs(i-(y+(height-1)))][j-x]);
                    }
                }
                else if (hFlip && vFlip){
                    if (sprite[Math.abs(i-(y+(height-1)))][Math.abs(j-(x+(width-1)))] != transperant){
                        setPixel(j, i, sprite[Math.abs(i-(y+(height-1)))][Math.abs(j-(x+(width-1)))]);
                    }
                }
            }
        }
    }

    static void addTile(int x, int y, int type){
        tiles.add(x);
        tiles.add(y);
        tiles.add(type);
    }

    public void keyTyped(KeyEvent e){
        
    }
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case 37: leftKey = true;
            break;
            case 39: rightKey = true;
            break;
            case 38: upKey = true;
            break;
            case 40: downKey = true;
            break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case 37: leftKey = false;
            break;
            case 39: rightKey = false;
            break;
            case 38: upKey = false;
            break;
            case 40: downKey = false;
            break;
        }
    }
}