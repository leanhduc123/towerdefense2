package sample.Entity.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Mountain {
    private int posX;
    private int posY;

    public Mountain(GraphicsContext gc, int posX, int posY, String name){
        this.posX = posX;
        this.posY = posY;
        gc.drawImage(new Image("file:src/resources/tile/" + name + ".png"),posX,posY);
    }
}
