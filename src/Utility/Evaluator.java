package Utility;

import Driver.Main;
import Objects.Garden;
import Objects.Plant;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {
    Main main;
    Generator gn = new Generator();
    float[] newGrassToDirts;
    int[] newShapeOfLands;
    ArrayList<ArrayList<Plant>> newPlants;
    float averageRating;

    public Evaluator(Main main){
    this.main = main;
    }

    public void evaluate(){
        newPlants = new ArrayList<ArrayList<Plant>>(main.gardens.size());

        evaluateOverall();

        evaluateGrassToDirt();
        evaluateShapeOfLand();
        evaluatePlantChoices();

    }

    private void evaluateOverall() {
        for (int i = 0; i < main.gardens.size(); i++) {
            if (main.gardens.get(i).overallAesthetics < 2){ //if rating is 1
                //set all to 1 star to regenerate
                main.gardens.get(i).rateGrassToDirt = 1;
                main.gardens.get(i).rateShapeOfLand = 1;
                main.textOutputWindow.outputLabel.setText(main.textOutputWindow.outputLabel.getText() + "\n" + "I am completely regenerating garden " + i + " because it looked ugly.");

            }
        }
    }

    private void evaluatePlantChoices() {
        averageRating = 0;
        for (int i = 0; i < main.gardens.size(); i++) {//get average rating
            averageRating += main.gardens.get(i).ratePlantChoices;
        }
        averageRating = averageRating / main.gardens.size();

        main.textOutputWindow.outputLabel.setText(main.textOutputWindow.outputLabel.getText() + "\n" + "The Average plant choice rating is " + averageRating + ". I will change plants in gardens with a lower rating.");

        //change plants
        Generator g = new Generator();
        for(int i = 0; i < main.gardens.size(); i++){
            if(main.gardens.get(i).ratePlantChoices < averageRating){
                main.textOutputWindow.outputLabel.setText(main.textOutputWindow.outputLabel.getText() + "\n" + "I am changing the plants in garden " + i + " because they were rated below average");

                main.gardens.get(i).regenPlants = true;
                //make new plants
                //Generator.placeTrees(main, main.gardens.get(i));
                //g.placeShrubs(main, main.gardens.get(i));
                //g.placePlants(main, main.gardens.get(i));
            }
        }
    }

    private void evaluateShapeOfLand() {
        newShapeOfLands = new int[main.gardens.size()];

        for(int i = 0; i < main.gardens.size(); i++){
            if(main.gardens.get(i).rateShapeOfLand < 3){
                newShapeOfLands[i] = (int)(Math.random() * 1000000);
                main.textOutputWindow.outputLabel.setText(main.textOutputWindow.outputLabel.getText() + "\n" + "I am changing the shape of the land in garden " + (i + 1) + " because it was rated poorly.");
            }
            if(main.gardens.get(i).rateShapeOfLand > 2){
                newShapeOfLands[i] = main.gardens.get(i).seed;
                System.out.println("keeping seed: " + main.gardens.get(i).seed);
            }
        }
    }

    private void evaluateGrassToDirt() {
        newGrassToDirts = new float[main.gardens.size()];

        for(int i = 0; i < main.gardens.size(); i++){
            if(main.gardens.get(i).rateGrassToDirt < 3){
                newGrassToDirts[i] = (float)(Math.random() * 2) - 1;
                main.textOutputWindow.outputLabel.setText(main.textOutputWindow.outputLabel.getText() + "\n" + "I am changing the ratio of grass to dirt in garden " + (i + 1) + " because it was rated poorly.");
            }
            if(main.gardens.get(i).rateGrassToDirt > 2){
                newGrassToDirts[i] = main.gardens.get(i).grassThreshold;
            }
            if(main.gardens.get(i).rateGrassToDirt == 3){//if 3 or 4 then add a random chance to mutate (20% for 3 and 10% for 4?)
                if((int)(Math.random() * 9.9999) < 2){//mutate
                    newGrassToDirts[i] = (float)(Math.random() * 2) - 1;
                    main.textOutputWindow.outputLabel.setText(main.textOutputWindow.outputLabel.getText() + "\n" + "The grass to dirt ratio in Garden " + (i + 1) + " has randomly mutated.");
                }
            }
            if(main.gardens.get(i).rateGrassToDirt == 4){
                if((int)(Math.random() * 9.9999) < 1){//mutate
                    newGrassToDirts[i] = (float)(Math.random() * 2) - 1;
                    main.textOutputWindow.outputLabel.setText(main.textOutputWindow.outputLabel.getText() + "\n" + "The grass to dirt ratio in Garden " + (i + 1) + " has randomly mutated.");
                }
            }
        }
    }

    public void addGardens(int numberOfGardens, GraphicsContext gc) {
        for(int i = 0; i < numberOfGardens; i++){
                main.gardens.add(new Garden(gn, main, gc, newGrassToDirts[i], newShapeOfLands[i]));

        }

        main.renderPlants();
    }

    public void regenerate(int numberOfGardens, GraphicsContext gc) {
        for(int i = 0; i < numberOfGardens; i++){
            //main.gardens.add(new Garden(gn, main, gc, newGrassToDirts[i], newShapeOfLands[i]));
            main.gardens.get(i).grassThreshold = newGrassToDirts[i];
            main.gardens.get(i).seed = newShapeOfLands[i];
            main.gardens.get(i).GenerateLand(main, gc);
            main.gardens.get(i).plants.clear();
            main.gardens.get(i).GeneratePlants(main, gc);
        }

        main.renderPlants();
    }
}
