package sample.Entity.Tower;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import sample.Entity.Enemy.Enemy;
import sample.Gamefield;

import java.util.ArrayList;
import java.util.List;


public abstract class Tower {
    private int posX;
    private int posY;
    private int speed;
    private int range;
    private int damage;
    private Gamefield field;
    private List<Enemy> enemyNear = new ArrayList<>();

    public Tower(int posX,int posY,int speed, int range, int damage, Gamefield field){
        this.posX     = posX;
        this.posY     = posY;
        this.speed  = speed;
        this.range  = range;
        this.damage = damage;
        this.field  = field;
    }

    public int getPosX() {
        return posX;
    }


    public int getPosY() {
        return posY;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public Gamefield getField() {
        return field;
    }

    public List<Enemy> getEnemyNear() {
        return enemyNear;
    }

    public void onDestroy(ImageView imageView){
        imageView.setFitHeight(0);
        imageView.setFitWidth(0);
    }

    public abstract void drawTower(Group root);

    public abstract void shooting(Group root);

    public abstract boolean distance(double dt);

    public void inCircle(){
        List<Enemy> enemyList = field.getEnemyList();
        double dt;
        for (int i = 0; i < enemyList.size(); i++){
            double ePosX = enemyList.get(i).getCanvas().getTranslateX();
            double ePosY = enemyList.get(i).getCanvas().getTranslateY();
            dt = Math.sqrt(Math.pow(posX-ePosX,2) + Math.pow(posY-ePosY,2));
            if (distance(dt)) enemyNear.add(enemyList.get(i));
        }

        while(enemyNear.size() > 0){
            double ePosX = enemyNear.get(0).getCanvas().getTranslateX();
            double ePosY = enemyNear.get(0).getCanvas().getTranslateY();
            dt = Math.sqrt(Math.pow(posX-ePosX,2) + Math.pow(posY-ePosY,2));
            if (!distance(dt) || enemyNear.get(0).hasDestroyed() == true) enemyNear.remove(0);
            else break;
        }
    }
}
