package sample.Entity.Enemy;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import sample.Config;
import sample.Entity.Destroyable;
import sample.Gamefield;

public abstract class Enemy implements Destroyable {
    private int health;
    private int armor;
    private int speed;
    private int reward;
    private Gamefield field;
    private boolean isDestroyed = false;

    public Enemy(){

    }
    @Override
    public void onDestroy(Canvas canvas){
        canvas.setHeight(0);
        canvas.setWidth(0);
    }

    public Enemy(int health, int armor, int speed, int reward, Gamefield field){
        this.health = health;
        this.armor = armor;
        this.speed = speed;
        this.reward = reward;
        this.field = field;
    }

    //Draw enemy
    public abstract void EnemyAppear(Group root);

    public abstract Canvas getCanvas();

    //Di chuyen
    public Canvas enemyMovement(Canvas canvas){
        Path path = new Path();
        path.getElements().add(new MoveTo(Config.spawner.getPosX()+30,Config.spawner.getPosY()+30));
        for (int i = 0; i < field.getPointList().size(); i++){
            path.getElements().add(new LineTo(field.getPointList().get(i).getX()+30,field.getPointList().get(i).getY()+30));
        }
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(7000*speed));
        pathTransition.setNode(canvas);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!isDestroyed){
                    onDestroy(canvas);
                    field.setMyHealth(field.getMyHealth()-1);
                    field.removeEnemy(0);
                }
            }
        });
        pathTransition.setAutoReverse(false);
        pathTransition.play();
        return canvas;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public boolean hasDestroyed(){
        return isDestroyed;
    }
}
