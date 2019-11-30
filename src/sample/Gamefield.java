package sample;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private int myHealth = 3;

    public Gamefield(){
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
        for (int i = 0; i < 18; i++){
            enemyList.add(new NormalEnemy(Config.NORMAL_ENEMY_HEALTH, Config.NORMAL_ENEMY_ARMOR, Config.NORMAL_ENEMY_SPEED, Config.NORMAL_ENEMY_REWARD, this));
        }
        for (int i = 0; i < 9; i++){
            enemyList.add(new SmallEnemy(Config.SMALL_ENEMY_HEALTH, Config.SMALL_ENEMY_ARMOR, Config.SMALL_ENEMY_SPEED, Config.SMALL_ENEMY_REWARD, this));
        }
        for (int i = 0; i < 9; i++){
            enemyList.add(new TankerEnemy(Config.TANK_ENEMY_HEALTH, Config.TANK_ENEMY_ARMOR, Config.TANK_ENEMY_SPEED, Config.TANK_ENEMY_REWARD, this));
        }
        enemyList.add(new BossEnemy(Config.BOSS_ENEMY_HEALTH,Config.BOSS_ENEMY_ARMOR, Config.BOSS_ENEMY_SPEED, Config.BOSS_ENEMY_REWARD, this));
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
        int movex = 30;
        int movey = 30;
        System.out.println("hello");
        return remove;
    }
}

//    public static void buyTower(Tower tower, Pane root) {
//        tower.imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                tower.imageView.setX(event.getX()-20);
//                tower.imageView.setY(event.getY()-20);
//            }
//        });
//        tower.imageView.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                tower.imageView.setX(event.getX()-20);
//                tower.imageView.setY(event.getY()-20);
//            }
//        });
//        tower.imageView.setOnMouseReleased(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if(tower.getName().equals("MachineGunTower") && GameStage.Matrix[(int)tower.imageView.getY()/40][(int)tower.imageView.getX()/40]==0 && tower.imageView.getX()<=800 && tower.imageView.getY()<=720){
//                    Tower newTower = new MachineGunTower((int)tower.imageView.getX()/40,(int)tower.imageView.getY()/40);
//                    root.getChildren().add(newTower.imageView);
//                    towers.add(newTower);
//
//                } else if(tower.getName().equals("NormalTower") && GameStage.Matrix[(int)tower.imageView.getY()/40][(int)tower.imageView.getX()/40]==0 && tower.imageView.getX()<=800 && tower.imageView.getY()<=720){
//                    Tower newTower = new NormalTower((int)tower.imageView.getX()/40,(int)tower.imageView.getY()/40);
//                    root.getChildren().add(newTower.imageView);
//                    towers.add(newTower);
//
//                } else if(tower.getName().equals("SniperTower") && GameStage.Matrix[(int)tower.imageView.getY()/40][(int)tower.imageView.getX()/40]==0 && tower.imageView.getX()<=800 && tower.imageView.getY()<=720){
//                    Tower newTower = new SniperTower((int)tower.imageView.getX()/40,(int)tower.imageView.getY()/40);
//                    root.getChildren().add(newTower.imageView);
//                    towers.add(newTower);
//                } else {
//                    tower.imageView.setX(tower.getX());
//                    tower.imageView.setY(tower.getY());
//                }
//
//                tower.imageView.setX(tower.getX());
//                tower.imageView.setY(tower.getY());
//
//            }
//        });
//    }