package Utility;

import Driver.Main;
import Objects.Garden;
import Objects.Plants.*;

public class Gardener {
    static Boolean placePlant;
    
    public static void placeRandomTree(Main main, Garden garden, int area){

        int numTrees;
        if(area < 37){ //if smaller than 37m^2(6x6) then don't add trees
            numTrees = 0;
        }else {//else work out the area, divide it by 100, add a random number to it between -1 and 1
            numTrees = area / 100;
            numTrees = numTrees + ((int) (Math.random() * 2.9999) - 1);


            //original start
            for (int i = 0; i < numTrees; i++) { //add the calculated number of trees
                int treeNum = (int)(Math.random()*4.9999);//rounds down (=0 to 4) // pick random tree
                placePlant = true;
                Point2D p;
                switch (treeNum){ // switch treetype i
                    case 0:
                        garden.treeTypes.add(0);
                        p = new Point2D(findSpace(112, main, garden));
                        if(placePlant == true) {
                            garden.plants.add(new TreeBirch(p, main));
                        }
                        break;

                    case 1:
                        garden.treeTypes.add(1);
                        p = new Point2D(findSpace(208, main, garden));
                        if(placePlant == true) {
                            garden.plants.add(new TreeMagnolia(p, main));
                        }
                        break;

                    case 2:
                        garden.treeTypes.add(2);
                        p = new Point2D(findSpace(192, main, garden));
                        if(placePlant == true) {
                            garden.plants.add(new TreeRowan(p, main));
                        }
                        break;
                    case 3:
                        garden.treeTypes.add(3);
                        p = new Point2D(findSpace(192, main, garden));
                        if(placePlant == true) {
                            garden.plants.add(new TreeFloweringCherry(p, main));
                        }
                        break;
                    case 4:
                        garden.treeTypes.add(4);
                        p = new Point2D(findSpace(144, main, garden));
                        if(placePlant == true) {
                            garden.plants.add(new TreeAcer(p, main));
                        }
                        break;
                }
            }
        }
    }


    public static void placeRandomTree(Main main, Garden garden, int area, Boolean b){
            //original start
            for (int i = 0; i < garden.treeTypes.size(); i++) { //add the calculated number of trees
                int treeNum = (int)(Math.random()*4.9999);//rounds down (=0 to 4) // pick random tree
                placePlant = true;
                Point2D p;
                switch (garden.treeTypes.get((int)(Math.random() * garden.treeTypes.size()))){ // switch treetype i
                    case 0:
                        //garden.treeTypes.add(0);
                        p = new Point2D(findSpace(112, main, garden));
                        if(placePlant == true) {
                            garden.plants.add(new TreeBirch(p, main));
                        }
                        break;

                    case 1:
                        //garden.treeTypes.add(1);
                        p = new Point2D(findSpace(208, main, garden));
                        if(placePlant == true) {
                            garden.plants.add(new TreeMagnolia(p, main));
                        }
                        break;

                    case 2:
                       // garden.treeTypes.add(2);
                        p = new Point2D(findSpace(192, main, garden));
                        if(placePlant == true) {
                            garden.plants.add(new TreeRowan(p, main));
                        }
                        break;
                    case 3:
                        //garden.treeTypes.add(3);
                        p = new Point2D(findSpace(192, main, garden));
                        if(placePlant == true) {
                            garden.plants.add(new TreeFloweringCherry(p, main));
                        }
                        break;
                    case 4:
                        //garden.treeTypes.add(4);
                        p = new Point2D(findSpace(144, main, garden));
                        if(placePlant == true) {
                            garden.plants.add(new TreeAcer(p, main));
                        }
                        break;
                }
            }
        }


    public static void placeRandomShrubs(Main main, int numShrubTypes, int numShrubs, Generator generator, Garden garden){
        //choose shrub types
        int[] shrubTypes = new int[numShrubTypes];
        shrubTypes[0] = (int)(Math.random()*5.9999);//rounds down (=0 to 2)
        for(int i = 1; i < shrubTypes.length; i++){
            shrubTypes[i] = shrubTypes[i - 1];
            while(shrubTypes[i] == shrubTypes[i - 1]){
                shrubTypes[i] = (int)(Math.random() * 5.9999);
            }
        }

        //add those shrubs
        Point2D p;
        Boolean placePlant;
        for(int s = 0; s < numShrubs; s++) {
            placePlant = true;
            switch (shrubTypes[ (int)(Math.random() * (shrubTypes.length - 0.0001))]) {//choose where in array(shrubtypes) to pick from
                case 0:
                    garden.shrubTypes.add(0);
                    p = findSpace(104, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 52][p.y + 52] < generator.grassThreshold){
                            p = findSpace(104, main, garden);
                        }else if(garden.simplexResult[p.x + 52][p.y + 52] > generator.grassThreshold && garden.simplexResult[p.x + 52][p.y + 52] < 1.0001){
                            i = 256;//exit loop
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubBuddleja(p, main));
                    }
                    break;

                case 1:
                    garden.shrubTypes.add(1);
                    p = findSpace(104, main, garden);
                    //make sure the space found is also on dirt todo add attempts to stop inifinte loops
                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 52][p.y + 52] < generator.grassThreshold){
                            p = findSpace(104, main, garden);
                        }else if(garden.simplexResult[p.x + 52][p.y + 52] > generator.grassThreshold && garden.simplexResult[p.x + 52][p.y + 52] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubLilac(p, main));
                    }
                    break;

                case 2:
                    garden.shrubTypes.add(2);
                    p = findSpace(64, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 32][p.y + 32] < generator.grassThreshold){
                            p = findSpace(64, main, garden);
                        }else if(garden.simplexResult[p.x + 32][p.y + 32] > generator.grassThreshold && garden.simplexResult[p.x + 32][p.y + 32] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubWeigela(p, main));
                    }
                    break;

                case 3:
                    garden.shrubTypes.add(3);
                    p = findSpace(64, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 32][p.y + 32] < generator.grassThreshold){
                            p = findSpace(64, main, garden);
                        }else if(garden.simplexResult[p.x + 32][p.y + 32] > generator.grassThreshold && garden.simplexResult[p.x + 32][p.y + 32] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubMockOrange(p, main));
                    }
                    break;

                case 4:
                    garden.shrubTypes.add(4);
                    p = findSpace(64, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 32][p.y + 32] < generator.grassThreshold){
                            p = findSpace(64, main, garden);
                        }else if(garden.simplexResult[p.x + 32][p.y + 32] > generator.grassThreshold && garden.simplexResult[p.x + 32][p.y + 32] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubForsythia(p, main));
                    }
                    break;

                case 5:
                    garden.shrubTypes.add(5);
                    p = findSpace(56, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 28][p.y + 28] < generator.grassThreshold){
                            p = findSpace(56, main, garden);
                        }else if(garden.simplexResult[p.x + 28][p.y + 28] > generator.grassThreshold && garden.simplexResult[p.x + 28][p.y + 28] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubHydrangea(p, main));
                    }
                    break;
            }
        }

    }

    public static void placeRandomShrubs(Main main, int numShrubTypes, int numShrubs, Generator generator, Garden garden, Boolean b){
        //choose shrub types
        int[] shrubTypes = new int[numShrubTypes];
        shrubTypes[0] = (int)(Math.random()*5.9999);//rounds down (=0 to 2)
        for(int i = 1; i < shrubTypes.length; i++){
            shrubTypes[i] = shrubTypes[i - 1];
            while(shrubTypes[i] == shrubTypes[i - 1]){
                shrubTypes[i] = (int)(Math.random() * 5.9999);
            }
        }

        //add those shrubs
        Point2D p;
        Boolean placePlant;
        for(int s = 0; s < numShrubs; s++) {
            placePlant = true;
            switch (garden.shrubTypes.get((int)(Math.random() * shrubTypes.length))) {//choose where in array(shrubtypes) to pick from
                case 0:
                    p = findSpace(104, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 52][p.y + 52] < generator.grassThreshold){
                            p = findSpace(104, main, garden);
                        }else if(garden.simplexResult[p.x + 52][p.y + 52] > generator.grassThreshold && garden.simplexResult[p.x + 52][p.y + 52] < 1.0001){
                            i = 256;//exit loop
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubBuddleja(p, main));
                    }
                    break;

                case 1:
                    p = findSpace(104, main, garden);
                    //make sure the space found is also on dirt todo add attempts to stop inifinte loops
                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 52][p.y + 52] < generator.grassThreshold){
                            p = findSpace(104, main, garden);
                        }else if(garden.simplexResult[p.x + 52][p.y + 52] > generator.grassThreshold && garden.simplexResult[p.x + 52][p.y + 52] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubLilac(p, main));
                    }
                    break;

                case 2:
                    p = findSpace(64, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 32][p.y + 32] < generator.grassThreshold){
                            p = findSpace(64, main, garden);
                        }else if(garden.simplexResult[p.x + 32][p.y + 32] > generator.grassThreshold && garden.simplexResult[p.x + 32][p.y + 32] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubWeigela(p, main));
                    }
                    break;

                case 3:
                    p = findSpace(64, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 32][p.y + 32] < generator.grassThreshold){
                            p = findSpace(64, main, garden);
                        }else if(garden.simplexResult[p.x + 32][p.y + 32] > generator.grassThreshold && garden.simplexResult[p.x + 32][p.y + 32] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubMockOrange(p, main));
                    }
                    break;

                case 4:
                    p = findSpace(64, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 32][p.y + 32] < generator.grassThreshold){
                            p = findSpace(64, main, garden);
                        }else if(garden.simplexResult[p.x + 32][p.y + 32] > generator.grassThreshold && garden.simplexResult[p.x + 32][p.y + 32] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubForsythia(p, main));
                    }
                    break;

                case 5:
                    p = findSpace(56, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 28][p.y + 28] < generator.grassThreshold){
                            p = findSpace(56, main, garden);
                        }else if(garden.simplexResult[p.x + 28][p.y + 28] > generator.grassThreshold && garden.simplexResult[p.x + 28][p.y + 28] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                            i = 256;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new ShrubHydrangea(p, main));
                    }
                    break;
            }
        }

    }

    public static void placeRandomPlants(Main main, int numPlantTypes, int numPlants, Generator generator, Garden garden) {
        //choose plant types
        int[] plantTypes = new int[numPlantTypes];
        plantTypes[0] = (int)(Math.random()*4.9999);//rounds down (=0 to 2)
        for(int i = 1; i < plantTypes.length; i++){
            plantTypes[i] = plantTypes[i - 1];
            while(plantTypes[i] == plantTypes[i - 1]){
                plantTypes[i] = (int)(Math.random() * 4.9999);
            }
        }

        //place those plants
        Point2D p;
        for(int s = 0; s < numPlants; s++) {
            placePlant = true;
            switch (plantTypes[ (int)(Math.random() * (plantTypes.length - 0.0001))]) {//choose where in array(shrubtypes) to pick from
                case 0:
                    garden.plantTypes.add(0);
                    p = findSpace(20, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 10][p.y + 10] < generator.grassThreshold){
                            p = findSpace(20, main, garden);
                        }else if(garden.simplexResult[p.x + 10][p.y + 10] > generator.grassThreshold && garden.simplexResult[p.x + 10][p.y + 10] < 1.0001){
                            i = 256;//exit loop. found a space
                        }
                        if(i == 255){
                            placePlant = false;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new PlantAster(p, main));
                    }
                    break;

                case 1:
                    garden.plantTypes.add(1);
                    p = findSpace(16, main, garden);
                    
                    //make sure the space found is also on dirt todo add attempts to stop inifinte loops
                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 8][p.y + 8] < generator.grassThreshold){

                            p = findSpace(16, main, garden);
                        }else if(garden.simplexResult[p.x + 8][p.y + 8] > generator.grassThreshold && garden.simplexResult[p.x + 8][p.y + 8] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new PlantGeranium(p, main));
                    }
                    break;

                case 2:
                    garden.plantTypes.add(2);
                    p = findSpace(16, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 8][p.y + 8] < generator.grassThreshold){
                            p = findSpace(16, main, garden);
                        }else if(garden.simplexResult[p.x + 8][p.y + 8] > generator.grassThreshold && garden.simplexResult[p.x + 8][p.y + 8] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new PlantMollis(p, main));
                    }
                    break;

                case 3:
                    garden.plantTypes.add(3);
                    p = findSpace(32, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 16][p.y + 16] < generator.grassThreshold){
                            p = findSpace(32, main, garden);
                        }else if(garden.simplexResult[p.x + 16][p.y + 16] > generator.grassThreshold && garden.simplexResult[p.x + 16][p.y + 16] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new PlantAchillea(p, main));
                    }
                    break;

                case 4:
                    garden.plantTypes.add(4);
                    p = findSpace(32, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 16][p.y + 16] < generator.grassThreshold){
                            p = findSpace(32, main, garden);
                        }else if(garden.simplexResult[p.x + 16][p.y + 16] > generator.grassThreshold && garden.simplexResult[p.x + 16][p.y + 16] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new PlantLavender(p, main));
                    }
                    break;
            }
        }
    }


    public static void placeRandomPlants(Main main, int numPlantTypes, int numPlants, Generator generator, Garden garden, Boolean b) {
        //choose plant types
        int[] plantTypes = new int[numPlantTypes];
        plantTypes[0] = (int)(Math.random()*4.9999);//rounds down (=0 to 2)
        for(int i = 1; i < plantTypes.length; i++){
            plantTypes[i] = plantTypes[i - 1];
            while(plantTypes[i] == plantTypes[i - 1]){
                plantTypes[i] = (int)(Math.random() * 4.9999);
            }
        }

        //place those plants
        Point2D p;
        for(int s = 0; s < numPlants; s++) {
            placePlant = true;
            switch (garden.plantTypes.get((int)(Math.random() * garden.plantTypes.size()))) {//choose where in array(shrubtypes) to pick from
                case 0:
                    p = findSpace(20, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 10][p.y + 10] < generator.grassThreshold){
                            p = findSpace(20, main, garden);
                        }else if(garden.simplexResult[p.x + 10][p.y + 10] > generator.grassThreshold && garden.simplexResult[p.x + 10][p.y + 10] < 1.0001){
                            i = 256;//exit loop. found a space
                        }
                        if(i == 255){
                            placePlant = false;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new PlantAster(p, main));
                    }
                    break;

                case 1:
                    p = findSpace(16, main, garden);

                    //make sure the space found is also on dirt todo add attempts to stop inifinte loops
                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 8][p.y + 8] < generator.grassThreshold){

                            p = findSpace(16, main, garden);
                        }else if(garden.simplexResult[p.x + 8][p.y + 8] > generator.grassThreshold && garden.simplexResult[p.x + 8][p.y + 8] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new PlantGeranium(p, main));
                    }
                    break;

                case 2:
                    p = findSpace(16, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 8][p.y + 8] < generator.grassThreshold){
                            p = findSpace(16, main, garden);
                        }else if(garden.simplexResult[p.x + 8][p.y + 8] > generator.grassThreshold && garden.simplexResult[p.x + 8][p.y + 8] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new PlantMollis(p, main));
                    }
                    break;

                case 3:
                    p = findSpace(32, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 16][p.y + 16] < generator.grassThreshold){
                            p = findSpace(32, main, garden);
                        }else if(garden.simplexResult[p.x + 16][p.y + 16] > generator.grassThreshold && garden.simplexResult[p.x + 16][p.y + 16] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new PlantAchillea(p, main));
                    }
                    break;

                case 4:
                    p = findSpace(32, main, garden);

                    for(int i = 0; i < 256; i++){
                        if(garden.simplexResult[p.x + 16][p.y + 16] < generator.grassThreshold){
                            p = findSpace(32, main, garden);
                        }else if(garden.simplexResult[p.x + 16][p.y + 16] > generator.grassThreshold && garden.simplexResult[p.x + 16][p.y + 16] < 1.0001){
                            i = 256;
                        }
                        if(i == 255){
                            placePlant = false;
                        }
                    }

                    if(placePlant == true) {
                        garden.plants.add(new PlantLavender(p, main));
                    }
                    break;
            }
        }
    }


    private static Point2D findSpace(float width, Main main, Garden garden) {
        int x = 0;
        int y = 0;
        boolean collided;


        for(int i = 0; i < 256; i++){
            collided = false;
            x = (int)((Math.random() * (main.grassWidth*main.tileSize - 0.0001 - width)));
            y = (int)((Math.random() * (main.grassHeight*main.tileSize - 0.0001 - width)));

            for(int t = 0; t < garden.plants.size(); t++){
                if(x < garden.plants.get(t).x + garden.plants.get(t).getImage().getWidth() && x > garden.plants.get(t).x - width){
                    if(y < garden.plants.get(t).y + garden.plants.get(t).getImage().getHeight() && y > garden.plants.get(t).y - width){
                        collided = true;
                    }
                }
            }
            if(collided == false){
                i = 256;
            }
            if(i == 255){
                placePlant = false;
            }

        }

        return new Point2D(x, y);
    }
}
