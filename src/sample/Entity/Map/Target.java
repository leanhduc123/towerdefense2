package sample.Entity.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Target {
    private int posX;
    private int posY;

    public Target(){

    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Target(GraphicsContext gc, int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        gc.drawImage(new Image("file:src/resources/tile/spawner/target.png"),posX,posY);
    }
}
