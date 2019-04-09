package Driver;

import Objects.Entrance;
import Objects.Garden;
import Objects.Plant;
import Utility.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;


public class Main extends Application {

    Label infoLabel;
    LabelSlider widthLabel;
    LabelSlider heightLabel;
    LabelSlider numOfGardensLabel;

    LabelSlider grassToDirt;
    LabelSlider plantTypes;
    LabelSlider landShape;
    LabelSlider overallAesthetics;
    //LabelSlider colourScheme;
    Separator separator = new Separator();
    Label selectedPlant = new Label("Selected Plant: ");

    public int grassWidth;
    public int grassHeight;
    Canvas canvas;
    public GraphicsContext gc;
    public int tileSize = 32;
    VBox settingsList;
    VBox rightArea;
    HBox bottomRightArea;
    VBox ratingColumn;
    HBox ratingRow1;
    HBox ratingRow2;
    String buttonClicked = "";
    Button placeEntranceButton;
    Button generateButton;
    Button nextGarden;
    Button saveGarden;
    Button createUserGarden;
    public ArrayList<Garden> gardens;
    public ArrayList<Entrance> entrances;
    public ArrayList<Plant> plants;
    Boolean widthChanged = false;
    Boolean heightChanged = false;
    int numberOfGardens = 1;
    int currentGarden = 0;

    public ImageLoader imageLoader = new ImageLoader();
    public TextOutputWindow textOutputWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("A creative garden generator");
        primaryStage.setScene(new Scene(root, 1200, 900));
        //primaryStage.setMaximized(true);

        gardens = new ArrayList<Garden>();
        entrances = new ArrayList<Entrance>();
        plants = new ArrayList<Plant>();


        infoLabel = new Label("Select a width and a height for your\n garden. Click place entrance to add\n an entry point to the garden. Then\n click generate to create the \ngardens.\n ");
        widthLabel = new LabelSlider("Width(m): 025", 4, 50, 25);
        heightLabel = new LabelSlider("Height(m): 015", 4, 30, 15);
        placeEntranceButton = new Button("Place entrance");
        generateButton = new Button("Generate");
        numOfGardensLabel = new LabelSlider("Number of Gardens: ", 1, 20, 1);
        generateButton.setDisable(true);
        nextGarden = new Button("Next Garden");
        saveGarden = new Button("Save Garden");
        createUserGarden = new Button("Create Your Own Garden");
        createUserGarden.setDisable(true);

        grassToDirt = new LabelSlider("Grass to dirt ratio: ", 1, 5, 1);
        plantTypes = new LabelSlider("Plant choices: ", 1, 5, 1);
        landShape = new LabelSlider("Shape of land: ", 1, 5, 1);
        overallAesthetics = new LabelSlider("Overall Aesthetics: ", 1, 5, 1);
        //colourScheme = new LabelSlider("Colour Scheme: ", 1, 5, 1);

        settingsList = new VBox(); //Column left area
        rightArea = new VBox();
        bottomRightArea = new HBox();
        bottomRightArea.setDisable(true);
        ratingColumn = new VBox();
        ratingRow1 = new HBox();
        ratingRow2 = new HBox();
        HBox mainArea = new HBox(); //Row

        separator.setPadding(new Insets(10, 10, 10, 10));

        settingsList.getChildren().addAll(infoLabel, widthLabel.getContianer(), heightLabel.getContianer(), placeEntranceButton, numOfGardensLabel.getContianer(), generateButton, separator, selectedPlant, saveGarden, createUserGarden);  //add the HBox(Row) to the VBox(column)

        root.getChildren().add(mainArea); //add the HBox to root

        canvas = new Canvas(25 * tileSize, 15 * tileSize);
        gc = canvas.getGraphicsContext2D();

        rightArea.getChildren().addAll(canvas, bottomRightArea);
        bottomRightArea.getChildren().addAll(ratingColumn);
        ratingColumn.getChildren().addAll(ratingRow1, ratingRow2);
        ratingRow1.getChildren().addAll(grassToDirt.getContianer(), plantTypes.getContianer(), landShape.getContianer(), nextGarden);
        ratingRow2.getChildren().addAll(overallAesthetics.getContianer());

        mainArea.getChildren().addAll(settingsList, rightArea); //add button and canvas to Hbox

        grassWidth = 50 * tileSize;
        grassHeight = 50 * tileSize;
        drawGrass(gc, 25 * tileSize, 15 * tileSize);

        primaryStage.show();
        addListeners();

        //TreeSorrel sorrel = new TreeSorrel();
        //TreeBirch birch = new TreeBirch();
        //plants.add(new TreeBirch((int) (Math.random() * grassWidth * tileSize), (int) (Math.random() * grassHeight * tileSize)));
        //plants.add(new TreeRowan());
    }

    public void drawGrass(GraphicsContext gc, int width, int height){
        gc.setFill(Color.web("#3167bf"));
        gc.fillRect(0,0, width, height);
        drawGrid(gc, width, height);
    }

    public void drawGrid(GraphicsContext gc, int width, int height){
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(2);
        for(int i = 0; i < width; i = i + tileSize) {
            gc.strokeLine(i, 0, i, canvas.getHeight());
        }
        for(int i = 0; i < height; i = i + tileSize) {
            gc.strokeLine(0, i, canvas.getWidth(), i);
        }
    }


    public void addListeners(){
        widthLabel.getSlider().valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                widthChanged = true;
                if(heightChanged == true){
                    generateButton.setDisable(false);
                    createUserGarden.setDisable(false);
                }

                widthLabel.getLabel().setText("Width(m): " + String.format("%03d", new_val.intValue()));
                canvas.setWidth((int)widthLabel.getSlider().getValue() * tileSize);
                grassWidth = new_val.intValue();
                drawGrass(gc, (int)canvas.getWidth(), (int)canvas.getHeight());
                entrances.clear();

            }
        });

        heightLabel.getSlider().valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                heightChanged = true;
                if(widthChanged == true){
                    generateButton.setDisable(false);
                    createUserGarden.setDisable(false);
                }

                heightLabel.getLabel().setText("Height(m): " + String.format("%03d", new_val.intValue()));
                canvas.setHeight((int)heightLabel.getSlider().getValue() * tileSize);
                grassHeight = new_val.intValue();
                drawGrass(gc, (int)canvas.getWidth(), (int)canvas.getHeight());
                entrances.clear();

            }
        });

        numOfGardensLabel.getSlider().valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                numOfGardensLabel.getLabel().setText("Number of Gardens: " + String.format("%02d", new_val.intValue()));
                numberOfGardens = new_val.intValue();
            }
        });


        //Ratings
        grassToDirt.getSlider().valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                grassToDirt.getLabel().setText("Grass to dirt ratio: " + String.format("%01d", new_val.intValue()) + "/5");
            }
        });

        plantTypes.getSlider().valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                plantTypes.getLabel().setText("Plant choices: " + String.format("%01d", new_val.intValue()) + "/5");
            }
        });

        landShape.getSlider().valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                landShape.getLabel().setText("Shape of land: " + String.format("%01d", new_val.intValue()) + "/5");
            }
        });

        overallAesthetics.getSlider().valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                overallAesthetics.getLabel().setText("Overall Aesthetics: " + String.format("%01d", new_val.intValue()) + "/5");
            }
        });

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("x " + (event.getSceneX() - settingsList.getWidth()));
                System.out.println("y " + event.getSceneY());
                //buttonClicked = "entrance";
                canvasClicked((event.getSceneX() - settingsList.getWidth()), event.getSceneY());
            }
        });

        EventHandler<ActionEvent> placeEntranceButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                placeEntranceButton.setText("Click the map to place entrance");
                buttonClicked = "entrance";
                event.consume();
            }
        };
        placeEntranceButton.setOnAction(placeEntranceButtonHandler);

        EventHandler<ActionEvent> generateButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonClicked = "generate";

                bottomRightArea.setDisable(false);

                plants.clear();
                //simplexResult = new double[grassWidth * tileSize][grassHeight * tileSize];
                Generator gn = new Generator();
                //gn.generate(Main.this, gc, this);
                //renderPlants();
                gardens.clear();
                //currentGarden = 0;

                //todo multiple gardens
                for(int i = 0; i < numberOfGardens; i++){
                    gardens.add(new Garden(gn, Main.this, gc, (float)((Math.random() * 2) - 1), (int)Math.random() * 1000000));
                }

                renderPlants();

                event.consume();
            }
        };

        EventHandler<ActionEvent> nextGardenHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonClicked = "next";
                if(currentGarden < gardens.size()) {
                    gardens.get(currentGarden).setRatings((int) grassToDirt.getSlider().getValue(), (int) plantTypes.getSlider().getValue(), (int) landShape.getSlider().getValue(), (int) overallAesthetics.getSlider().getValue());
                }
                currentGarden++;
                if(currentGarden == gardens.size() - 1){
                    nextGarden.setText("Next Generation");
                }
                if(currentGarden == gardens.size()){
                    //todo generate the next generation
                    System.out.println("Creating new generation");
                    textOutputWindow = new TextOutputWindow();
                    currentGarden = 0;

                    Evaluator evaluator = new Evaluator(Main.this);
                    evaluator.evaluate();

                    //for(int i = 0; i < gardens.size(); i++){
                    //    if(gardens.get(i).regenPlants == true){
                    //        gardens.remove(i);
                    //    }
                    //}

                    //gardens.clear();
                    evaluator.regenerate(numberOfGardens, gc);

                    nextGarden.setText("Next Garden");
                }

                renderPlants();

                event.consume();
            }
        };

        EventHandler<ActionEvent> saveGardenHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SnapshotParameters params = new SnapshotParameters();
                final Image img = canvas.snapshot(params, null);

                File file = new File("autogarden.png");


                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
                } catch (Exception s) {
                }

                event.consume();
            }
        };

        EventHandler<ActionEvent> makeOwnGardenHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HumanDesigner humanDesigner = new HumanDesigner(grassWidth * tileSize, grassHeight * tileSize, imageLoader);

                event.consume();
            }
        };

        createUserGarden.setOnAction(makeOwnGardenHandler);
        generateButton.setOnAction(generateButtonHandler);
        nextGarden.setOnAction(nextGardenHandler);
        saveGarden.setOnAction(saveGardenHandler);

    }

    public void renderPlants() {

        for(int i = 0; i < grassWidth * tileSize; i++) {//FULL RES map draw it to gc (with changes)
            for (int j = 0; j < grassHeight * tileSize; j++) {
                if(gardens.get(currentGarden).simplexResult[i][j] < gardens.get(currentGarden).grassThreshold && gardens.get(currentGarden).simplexResult[i][j] > -1){ //512 because blocky zoom was 16, tilesize = 32 -> 16*32 = 512 //GRASS
                    gc.setFill(new Color(0.75, ((Math.random()+7.5)/8.5)-0.05, 0.5, 1));
                    //gc.setFill(new Color(0.75, 0.8, 0.5, 1));
                    gc.fillRect(i, j, 1, 1);
                }else if(gardens.get(currentGarden).simplexResult[i][j] > gardens.get(currentGarden).grassThreshold && gardens.get(currentGarden).simplexResult[i][j] < 1){ //DIRT
                    gc.setFill(new Color(0.35, 0.16, 0.05, 1));
                    gc.fillRect(i, j, 1, 1);
                }else if(gardens.get(currentGarden).simplexResult[i][j] == 2){ //GRAVEL
                    gc.setFill(new Color(((Math.random()+7.5)/8.5)-0.05, ((Math.random()+7.5)/8.5)-0.05, ((Math.random()+7.5)/8.5)-0.2, 1));
                    gc.fillRect(i, j, 1, 1);
                }
            }
        }

        for(int i = 0; i < gardens.get(currentGarden).plants.size(); i++){

            gc.drawImage(gardens.get(currentGarden).plants.get(i).getImage(), gardens.get(currentGarden).plants.get(i).x, gardens.get(currentGarden).plants.get(i).y);
        }

    }


    public void canvasClicked(double x, double y){
        switch(buttonClicked) {
            case "entrance" :
                //work out closer side? NESW
                double n = y;
                double e = canvas.getWidth() - x;
                double s = canvas.getHeight() - y;
                double w = x;

                if(x < 32 && y < 32 || x < 32 && y > canvas.getHeight() - 32 || y < 32 && x > canvas.getWidth() - 32 || x > canvas.getWidth() - 32 && y > canvas.getHeight() - 32){
                    //if in the corners then do nothing
                }else {

                    double lowestDist = y;
                    String lowestDistIndex = "n";

                    if (e < lowestDist) {
                        lowestDist = e;
                        lowestDistIndex = "e";
                    }
                    if (s < lowestDist) {
                        lowestDist = s;
                        lowestDistIndex = "s";
                    }
                    if (w < lowestDist) {
                        lowestDist = w;
                        lowestDistIndex = "w";
                    }

                    switch (lowestDistIndex) {
                        case "n":
                            placeEntrance(x - tileSize / 2, 0, 0);
                            break;
                        case "e":
                            placeEntrance(canvas.getWidth() - tileSize, y - tileSize / 2, 1);
                            break;
                        case "s":
                            placeEntrance(x - tileSize / 2, canvas.getHeight() - tileSize, 2);
                            break;
                        case "w":
                            placeEntrance(0, y - tileSize / 2, 3);
                            break;
                    }
                }

                buttonClicked = "";
                placeEntranceButton.setText("Place entrance");

                break; // optional

            case "water" :
                // Statements
                break; // optional

            // You can have any number of case statements.
            default : // Optional
                for(int i = 0; i < gardens.get(currentGarden).plants.size(); i ++){
                    if(x > gardens.get(currentGarden).plants.get(i).x && x < gardens.get(currentGarden).plants.get(i).x + gardens.get(currentGarden).plants.get(i).getImage().getWidth()){
                        if(y > gardens.get(currentGarden).plants.get(i).y && y < gardens.get(currentGarden).plants.get(i).y + gardens.get(currentGarden).plants.get(i).getImage().getHeight()){
                            selectedPlant.setText("Selected Plant: " + gardens.get(currentGarden).plants.get(i).name);
                        }

                    }
                }
                // Statements
        }

    }

    public void placeEntrance(double x, double y, int direction){
        int tileX = (int)(tileSize*(Math.round(x/tileSize)));
        int tileY = (int)(tileSize*(Math.round(y/tileSize)));
        entrances.add(new Entrance(tileX, tileY, direction));
        gc.setFill(Color.web("#7ca859"));
        gc.fillRect(tileX, tileY, tileSize, tileSize); //draw a green square
    }

    public static void main(String[] args) {
        launch(args);
    }
}
