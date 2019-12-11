package sample;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameStage {
    private Canvas canvas = new Canvas(Config.SCREEN_WIDTH,Config.SCREEN_HEIGHT);
    private Group root = new Group();
    private Gamefield field = new Gamefield();
    private GameStage stage = this;
    public GameStage(){
        mainMenu();
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public void restart(String name){
        ImageView img = new ImageView(new Image("file:src/resources/mainmenu.png"));
        img.setFitHeight(Config.SCREEN_HEIGHT);
        img.setFitWidth(Config.SCREEN_WIDTH);
        root.getChildren().add(img);

        Title title = new Title(name);
        title.setTranslateX(450);
        title.setTranslateY(200);

        Text score = new Text("Score: " + field.getMyScore());
        score.setFill(Color.WHITE);
        score.setFont(Font.font("Tw Cen MT Condensed",FontWeight.SEMI_BOLD,53));

        score.setTranslateX(560);
        score.setTranslateY(330);

        MenuItem soundItem;
        if (field.sound()) soundItem = new MenuItem("Sound: Off");
        else soundItem = new MenuItem("Sound: On");
        MenuBox menuBox = new MenuBox(
                new MenuItem("Play again"),
                soundItem,
                new MenuItem("Exit")
        );
        menuBox.setTranslateX(480);
        menuBox.setTranslateY(350);
        root.getChildren().addAll(title,score,menuBox);
    }

    //Main Menu
    public void mainMenu(){
        ImageView img = new ImageView(new Image("file:src/resources/mainmenu.png"));
        img.setFitHeight(Config.SCREEN_HEIGHT);
        img.setFitWidth(Config.SCREEN_WIDTH);
        root.getChildren().add(img);

        Title title = new Title("Tower Defense");
        title.setTranslateX(450);
        title.setTranslateY(200);

        MenuBox menuBox = new MenuBox(
                new MenuItem("New Game"),
                new MenuItem("Sound: On"),
                new MenuItem("Exit")
        );
        menuBox.setTranslateX(480);
        menuBox.setTranslateY(300);
        root.getChildren().addAll(title,menuBox);
    }

    private class Title extends StackPane{
        public Title(String name){
            Rectangle bg = new Rectangle(350,75);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(2);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Tw Cen MT Condensed",FontWeight.SEMI_BOLD,75));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg,text);
        }
    }

    private class MenuBox extends VBox {
        public MenuBox(MenuItem... items){
            getChildren().add(creatSeparator());

            for(MenuItem item : items){
                getChildren().addAll(item,creatSeparator());
            }
        }
        private Line creatSeparator(){
            Line sep = new Line();
            sep.setEndX(200);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }

    private class MenuItem extends StackPane {
        public MenuItem(String name){
            LinearGradient gradient = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,new Stop[]{
                    new Stop(0, Color.DARKVIOLET),
                    new Stop(0.1,Color.BLACK),
                    new Stop(0.9,Color.BLACK),
                    new Stop(1,Color.DARKVIOLET)
            });

            Rectangle bg = new Rectangle(300,60);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg,text);

            setOnMouseEntered(event ->{
                bg.setFill(gradient);
                text.setFill(Color.WHITE);
            });

            setOnMouseExited(event ->{
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });

            setOnMousePressed(event ->{
                bg.setFill(Color.DARKVIOLET);
            });

            setOnMouseReleased(event ->{
                bg.setFill(gradient);
                if (name == "New Game") {
                    field.drawMap(canvas.getGraphicsContext2D());
                    root.getChildren().add(canvas);
                    Controller controller = new Controller(root,field,stage);
                    controller.start();
                }
                else if (name == "Exit") System.exit(0);
                else if (name == "Play again"){
                    canvas = new Canvas(Config.SCREEN_WIDTH,Config.SCREEN_HEIGHT);
                    boolean isMute = field.sound();
                    field = new Gamefield();
                    field.setMute(isMute);
                    field.drawMap(canvas.getGraphicsContext2D());
                    root.getChildren().add(canvas);
                    field.setMyHealth(3);
                    Controller controller = new Controller(root,field,stage);
                    controller.start();
                }
                 else if (text.getText() == "Sound: On"){
                    text.setText("Sound: Off");
                    System.out.println("Off");
                    field.setMute(true);
                }
                 else if (text.getText() == "Sound: Off"){
                    text.setText("Sound: On");
                    System.out.println("On");
                    field.setMute(false);
                }
            });
        }
    }
}
