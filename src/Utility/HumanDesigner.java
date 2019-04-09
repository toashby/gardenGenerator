package Utility;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HumanDesigner {
    public Label outputLabel;
    Button drawGrass;
    Button drawDirt;
    Button placePlant;
    Button saveGarden;
    GraphicsContext gc;
    Image grassImage;
    Boolean drawingDirt = false;
    Boolean drawingGrass = false;
    Boolean placingPlant = false;
    ImageLoader imageLoader;
    Image img; //temp image
    int currentPlant = 1;
    Canvas canvas;
    SnapshotParameters params = new SnapshotParameters();
    VBox vBox = new VBox();
    public HumanDesigner(int width, int height, ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        URL url = getClass().getResource("/res/grassNoise.png");

        try {
            grassImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawGrass = new Button("Draw Grass");
        drawDirt = new Button("Draw Dirt");
        placePlant = new Button("Place Plants");
        saveGarden = new Button("Save Garden");

        Stage primaryStage = new Stage();
        primaryStage.setWidth(1200);
        primaryStage.setHeight(900);
        outputLabel =new Label("When placing plants use\n the scroll wheel to select\n a plant. Click and drag\n with draw dirt/grass selected\n to draw to the canvas.\n save image will save the\n garden to the location\n of the program.");

        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(new Color(0.35, 0.16, 0.05, 1));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

    HBox root = new HBox();
    Scene scene = new Scene(root, 1200, 900);
        primaryStage.setScene(scene);
        vBox.getChildren().addAll(outputLabel, drawGrass, drawDirt, placePlant, saveGarden);
        root.getChildren().addAll(canvas, vBox);

        primaryStage.setTitle("Human Garden Maker");
        primaryStage.setScene(scene);
        primaryStage.show();


        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("x " + (event.getSceneX()));
                System.out.println("y " + event.getSceneY());
                //buttonClicked = "entrance";
                canvasClicked(event.getSceneX(), event.getSceneY());
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("x " + (event.getSceneX()));
                System.out.println("y " + event.getSceneY());
                //buttonClicked = "entrance";
                canvasClicked(event.getSceneX(), event.getSceneY());
            }
        });

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(placingPlant == true){
                    //hover a plant


                    gc.drawImage(img, 0, 0);

                    gc.drawImage(getCurrentPlant(), event.getX(), event.getY());

                }
            }
        });

        canvas.setOnScroll((ScrollEvent event) -> {
            currentPlant = currentPlant + 1;
            if(currentPlant == 16){
                currentPlant = 1;
            }
            gc.drawImage(img, 0, 0);
            gc.drawImage(getCurrentPlant(), event.getX(), event.getY());
                });

        EventHandler<ActionEvent> drawGrassHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawingGrass = true;
                drawingDirt = false;
                placingPlant = false;
                event.consume();
            }
        };

        drawGrass.setOnAction(drawGrassHandler);


        EventHandler<ActionEvent> drawDirtHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawingGrass = false;
                drawingDirt = true;
                placingPlant = false;
                event.consume();
            }
        };

        drawDirt.setOnAction(drawDirtHandler);

        EventHandler<ActionEvent> placePlantHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawingGrass = false;
                drawingDirt = false;
                placingPlant = true;


                img = canvas.snapshot(params, null);

                event.consume();
            }
        };

        placePlant.setOnAction(placePlantHandler);


        EventHandler<ActionEvent> saveGardenHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SnapshotParameters params = new SnapshotParameters();
                final Image img = canvas.snapshot(params, null);

                File file = new File("aHumangarden.png");


                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
                } catch (Exception s) {
                }

                event.consume();
            }
        };
        saveGarden.setOnAction(saveGardenHandler);

    }

    public void canvasClicked(double sceneX, double sceneY){
        if(drawingGrass == true) {
            gc.drawImage(grassImage, sceneX - 32, sceneY - 32);
        }
        if(drawingDirt == true){
            gc.setFill(new Color(0.35, 0.16, 0.05, 1));
            gc.fillOval(sceneX - 32, sceneY - 32, 64, 64);
        }
        if(placingPlant == true){
            gc.drawImage(getCurrentPlant(), sceneX, sceneY);
            img = canvas.snapshot(params, null);
        }
    }

    public Image getCurrentPlant() {

        switch (currentPlant){
            case 1:
                return imageLoader.achilleaImage;

            case 2:
                return imageLoader.asterImage;

            case 3:
                return imageLoader.geraniumImage;


            case 4:
                return imageLoader.lavenderImage;


            case 5:
                return imageLoader.mollisImage;


            case 6:
                return imageLoader.buddlejaImage;


            case 7:
                return imageLoader.forsythiaImage;


            case 8:
                return imageLoader.hydrangeaImage;


            case 9:
                return imageLoader.lilacImage;


            case 10:
                return imageLoader.mockOrangeImage;


            case 11:
                return imageLoader.wiegelaImage;


            case 12:
                return imageLoader.acerImage;


            case 13:
                return imageLoader.birchImage;


            case 14:
                return imageLoader.floweringCherryImage;


            case 15:
                return imageLoader.magnoliaImage;


            case 16:
                return imageLoader.rowanImage;

        }
        return null;
    }
}
