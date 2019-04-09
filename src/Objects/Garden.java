package Objects;

import Driver.Main;
import Utility.Generator;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Garden {
    public ArrayList<Plant> plants;
    public double[][] simplexResult;
    public float grassThreshold = 0.3f;
    public int seed = (int)(Math.random()*1000000);

    public int rateGrassToDirt;
    public int ratePlantChoices;
    public int rateShapeOfLand;
    public int overallAesthetics;
    public Boolean regenPlants = false;
    public ArrayList<Integer> plantTypes = new ArrayList<>();
    public ArrayList<Integer> shrubTypes = new ArrayList<>();
    public ArrayList<Integer> treeTypes = new ArrayList<>();
    Generator gn;


    public Garden(Generator gn, Main m, GraphicsContext gc, float grassThreshold, int seed){
        this.seed = seed;
        this.gn = gn;
        this.grassThreshold = grassThreshold;
        plants = new ArrayList<Plant>();
        simplexResult = new double[m.grassWidth * m.tileSize][m.grassHeight * m.tileSize];
        gn.generateLand(m, gc, this, grassThreshold, seed);
        gn.generatePlants(m, this);
    }

    public void GenerateLand(Main m, GraphicsContext gc){
        gn.generateLand(m, gc, this, grassThreshold, seed);

    }

    public void GeneratePlants(Main m, GraphicsContext gc){
        if(regenPlants == true){
            gn.generatePlants(m, this);
        }else{
            gn.generatePlants(m, this, true);
        }

    }

    public Garden(Generator gn, Main m, GraphicsContext gc, float grassThreshold, int seed, Boolean b){
        this.seed = seed;
        this.grassThreshold = grassThreshold;
        plants = new ArrayList<Plant>();
        simplexResult = new double[m.grassWidth * m.tileSize][m.grassHeight * m.tileSize];
        gn.generateLand(m, gc, this, grassThreshold, seed);
        gn.generatePlants(m, this, b);

    }


    public void setRatings(int rateGrassToDirt, int ratePlantChoices, int rateShapeOfLand, int overallAesthetics){
        this.rateGrassToDirt = rateGrassToDirt;
        this.ratePlantChoices = ratePlantChoices;
        this.rateShapeOfLand = rateShapeOfLand;
        this.overallAesthetics = overallAesthetics;
    }

}

