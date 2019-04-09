package Utility;

import Driver.Main;
import Objects.Garden;
import javafx.scene.canvas.GraphicsContext;
import jdk.nashorn.internal.ir.BlockLexicalContext;

public class Generator {
    static int area;
    public Simplex2D sm;
    public float grassThreshold;
    public int zoom;

    public void generateLand(Main main, GraphicsContext gc, Garden garden, float grassThreshold, int seed) {
        this.grassThreshold = grassThreshold;
        zoom = 16;
        area = main.grassWidth * main.grassHeight;
        sm = new Simplex2D(seed); //1 billion seeds should be enough

        for(int i = 0; i < main.grassWidth * main.tileSize; i++){//Generate the FULL RES map
            for(int j = 0; j < main.grassHeight * main.tileSize; j++){
                garden.simplexResult[i][j] = sm.noise(i, j, zoom * main.tileSize);
            }
        }

        Builder builder = new Builder();
        builder.buildPath(this, main, garden);
    }

    public void generatePlants(Main main, Garden garden) {
        placeTrees(main, garden);
        placeShrubs(main, garden);
        placePlants(main, garden);
    }

    public void generatePlants(Main main, Garden garden, Boolean b) {
        placeTrees(main, garden, b);
        placeShrubs(main, garden, b);
        placePlants(main, garden, b);
    }


    public static void placeTrees(Main main, Garden garden) {

        Gardener.placeRandomTree(main, garden, area);
    }

    public static void placeTrees(Main main, Garden garden, Boolean b) {

        Gardener.placeRandomTree(main, garden, area, b);
    }

    public void placeShrubs(Main main, Garden garden){
        //pick 1-3? random shrubs
        int numShrubTypes = (int)((Math.random()*2.9999) + 1); //1-3 types of shrub
        //place between 0 and 15? do some division again
        int numShrubs = area / 100;
        numShrubs = numShrubs + ((int)(Math.random() * 2.9999) - 1); //add a number between -1 and 1
        //call the gardener to do collisions of the trees (if shady tree, dont do collisions/ reduce bounds)
        Gardener.placeRandomShrubs(main, numShrubTypes, numShrubs, this, garden);
    }

    public void placePlants(Main main, Garden garden){
        //use attempts for this
        //basically tell the gardener to fill in all the gaps
        //pick two or three
        //clump them together, place one, check the simplex grid around it for dirt,but also collisions, clupms of up to 3
        int numPlantTypes = (int)((Math.random()*2.9999) + 3); //3-5 types of shrub
        //place between 0 and 15? do some division again
        int numPlants = area / 5;
        numPlants = numPlants + ((int)(Math.random() * 2.9999) - 1);

        Gardener.placeRandomPlants(main, numPlantTypes, numPlants, this, garden);
    }


    //keep same
    public void placeShrubs(Main main, Garden garden, Boolean b){
        //pick 1-3? random shrubs
        int numShrubTypes = (int)((Math.random()*2.9999) + 1); //1-3 types of shrub
        //place between 0 and 15? do some division again
        int numShrubs = area / 100;
        numShrubs = numShrubs + ((int)(Math.random() * 2.9999) - 1); //add a number between -1 and 1
        //call the gardener to do collisions of the trees (if shady tree, dont do collisions/ reduce bounds)
        Gardener.placeRandomShrubs(main, numShrubTypes, numShrubs, this, garden, b);
    }

    public void placePlants(Main main, Garden garden, Boolean b){
        //use attempts for this
        //basically tell the gardener to fill in all the gaps
        //pick two or three
        //clump them together, place one, check the simplex grid around it for dirt,but also collisions, clupms of up to 3
        int numPlantTypes = (int)((Math.random()*2.9999) + 3); //3-5 types of shrub
        //place between 0 and 15? do some division again
        int numPlants = area / 5;
        numPlants = numPlants + ((int)(Math.random() * 2.9999) - 1);

        Gardener.placeRandomPlants(main, numPlantTypes, numPlants, this, garden, b);
    }


}
