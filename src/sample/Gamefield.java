package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Entity.Enemy.*;
import sample.Entity.Map.Mountain;
import sample.Entity.Map.Road;
import sample.Entity.Map.Spawner;
import sample.Entity.Map.Target;

import java.util.ArrayList;
import java.util.List;


public class Gamefield {
    private List<Point> pointList = new ArrayList<>();
    private List<Enemy> enemyList = new ArrayList<>();
    private int myHealth = 4;
    private int myScore = 0;
    private int myGold = Config.myGold;
    private boolean isMute = false;

    public Gamefield(){
    }

    public int getMyScore() {
        return myScore;
    }

    public void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    public void setMute(boolean mute) {
        isMute = mute;
    }

    public int getMyGold() {
        return myGold;
    }

    public void setMyGold(int myGold) {
        this.myGold = myGold;
    }

    public boolean sound(){
        return isMute;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setMyHealth(int myHealth) {
        this.myHealth = myHealth;
    }

    public int getMyHealth() {
        return myHealth;
    }

    public void drawMap(GraphicsContext gc){
        for (int i = 0; i < Config.MAP_SPRITES.length;i++)
            for (int j = 0; j < Config.MAP_SPRITES[0].length; j++){
                if (Config.MAP_SPRITES[i][j] == "0"){
                    new Spawner(gc, j*Config.TILE_SIZE, i*Config.TILE_SIZE);
                    Config.spawner.setPosX(j*Config.TILE_SIZE);
                    Config.spawner.setPosY(i*Config.TILE_SIZE);
                }
                else if (Config.MAP_SPRITES[i][j] == "10"){
                    new Target(gc, j*Config.TILE_SIZE, i*Config.TILE_SIZE);
                    Config.target.setPosX(j*Config.TILE_SIZE);
                    Config.target.setPosY(i*Config.TILE_SIZE);
                }
                else if (Config.MAP_SPRITES[i][j] == "1") {
                    new Road(gc, j*Config.TILE_SIZE, i*Config.TILE_SIZE);
                }
                else {
                    new Mountain(gc, j*Config.TILE_SIZE, i*Config.TILE_SIZE, Config.MAP_SPRITES[i][j]);
                }
            }
        //pointList
        pointList.add(new Point(Config.spawner.getPosX(),Config.spawner.getPosY()));
        pointList.add(new Point(Config.spawner.getPosX(),Config.spawner.getPosY()));
        int j = Config.spawner.getPosX()/60;
        int i = Config.spawner.getPosY()/60;
        int size = pointList.size();

        while (j < Config.SCREEN_WIDTH/60 && i < Config.SCREEN_HEIGHT/60){
            if (Config.MAP_SPRITES[i+1][j] == "1" && i+1 != pointList.get(size-2).getY()/60){
                pointList.add(new Point(j *60,(i+1)*60));
                i++;
                size++;
                System.out.println(i + " " + j);
            }
            //Dung khi den target
             else if (Config.MAP_SPRITES[i+1][j] == "10"){
                pointList.add(new Point(j*60,(i+1)*60));
                System.out.println((i+1) + " " + j);
                break;
            }
            else if (i > 0 && Config.MAP_SPRITES[i-1][j] == "1" && i-1 != pointList.get(size-2).getY()/60){
                pointList.add(new Point(j*60,(i-1)*60));
                i--;
                size++;
                System.out.println(i + " " + j);
            }
            else if (i > 0 && Config.MAP_SPRITES[i-1][j] == "10"){
                pointList.add(new Point(j*60,(i-1)*60));
                System.out.println((i-1) + " " + j);
                break;
            }
            else if (j > 0 && Config.MAP_SPRITES[i][j-1] == "1" && j-1 != pointList.get(size-2).getX()/60){
                pointList.add(new Point((j-1)*60,i*60));
                j--;
                size++;
                System.out.println(i + " " + j);
            }
            else if (j > 0 && Config.MAP_SPRITES[i][j-1] == "10"){
                pointList.add(new Point((j-1)*60,i*60));
                System.out.println(i + " " + (j+1));
                break;
            }
            else if (pointList.get(size-2).getX()/60 != j+1 && Config.MAP_SPRITES[i][j+1] == "1"){
                pointList.add(new Point((j+1)*60,i*60));
                j++;
                size++;
                System.out.println(i + " " + j);
            }
            else if (Config.MAP_SPRITES[i][j+1] == "10"){
                pointList.add(new Point((j+1)*60,i*60));
                System.out.println(i + " " + (j+1));
                break;
            }
        }
        pointList.remove(0);
        pointList.remove(0);
    }

    public void addEnemy(){
        for (int i = 0; i < 16; i++){
            enemyList.add(new NormalEnemy(this));
        }
        for (int i = 0; i < 4; i++){
            enemyList.add(new SmallEnemy(this));
        }

        for (int i = 0; i < 4; i++){
            enemyList.add(new NormalEnemy(this));
        }
        for (int i = 0; i < 6; i++){
            enemyList.add(new SmallEnemy(this));
        }

        for (int i = 0; i < 3; i++){
            enemyList.add(new NormalEnemy(this));
        }
        for (int i = 0; i < 4; i++){
            enemyList.add(new SmallEnemy(this));
        }
        for (int i = 0; i < 3; i++){
            enemyList.add(new TankerEnemy(this));
        }

        for (int i = 0; i < 2; i++){
            enemyList.add(new NormalEnemy(this));
        }
        for (int i = 0; i < 3; i++){
            enemyList.add(new SmallEnemy(this));
        }
        for (int i = 0; i < 4; i++){
            enemyList.add(new TankerEnemy(this));
        }
        enemyList.add(new BossEnemy(this));

        for (int i = 0; i < 4; i++){
            enemyList.add(new TankerEnemy(this));
        }
        for (int i = 0; i < 3; i++){
            enemyList.add(new SmallEnemy(this));
        }
        enemyList.add(new BossEnemy(this));
        enemyList.add(new TankerEnemy(this));
        enemyList.add(new BossEnemy(this));

    }

    public void removeEnemy(int k){
        enemyList.remove(k);
    }

    public ImageView drawHeart(int posX, int posY){
        ImageView heart = new ImageView(new Image("file:src/resources/tile/heart.png"));
        heart.setX(posX);
        heart.setY(posY);
        return heart;
    }
    public ImageView removeHeart(int posX, int posY){
        ImageView remove = new ImageView(new Image("file:src/resources/tile/2.png"));
        ImageView remove2 = new ImageView(new Image("file:src/resources/tile/2.png"));
        remove.setX(posX);
        remove.setY(posY);
        return remove;
    }

}
