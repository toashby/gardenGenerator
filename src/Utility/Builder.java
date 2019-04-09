package Utility;

import Driver.Main;
import Objects.Garden;

public class Builder {
    Garden garden;
    public void buildPath(Generator generator, Main main, Garden garden) {
        this.garden = garden;
        int pathType = (int)(Math.random() * 1.999); //2 path types
        switch (pathType){
            case 0:
                solidPath(generator, main, 2); //gravel
                break;
            case 1:
                solidPath(generator, main, 0); //grass
                break;
        }

    }


    public void solidPath(Generator generator, Main main, int pathType){
        for(int i = 0; i < main.entrances.size(); i++){
            Boolean onGrass = false;
            Boolean foundDirt;
            int pos;

            switch (main.entrances.get(i).direction){
                case 0:
                    pos = 0;

                    while (pos < main.grassHeight * main.tileSize && onGrass == false){
                        foundDirt = false;
                        for(int j = 0; j < 32; j++){
                            if(garden.simplexResult[(int)(main.entrances.get(i).x + j)][pos] > generator.grassThreshold){
                                foundDirt = true;
                                garden.simplexResult[(int)(main.entrances.get(i).x + j)][pos] = pathType;
                            }
                        }
                        if(foundDirt == false){
                            onGrass = true;
                        }
                        pos++;
                    }
                    break;
                case 1:
                    pos = (main.grassWidth * main.tileSize) - 1;
                    while (pos > 0 && onGrass == false){
                        foundDirt = false;
                        for(int j = 0; j < 32; j++){
                            if(garden.simplexResult[pos][(int)(main.entrances.get(i).y + j)] > generator.grassThreshold){
                                foundDirt = true;
                                garden.simplexResult[pos][(int)(main.entrances.get(i).y + j)] = pathType;
                            }
                        }
                        if(foundDirt == false){
                            onGrass = true;
                        }
                        pos--;
                    }
                    break;
                case  2:
                    pos = (main.grassHeight * main.tileSize) - 1;

                    while (pos > 0 && onGrass == false){
                        foundDirt = false;
                        for(int j = 0; j < 32; j++){
                            if(garden.simplexResult[(int)(main.entrances.get(i).x + j)][pos] > generator.grassThreshold){
                                foundDirt = true;
                                garden.simplexResult[(int)(main.entrances.get(i).x + j)][pos] = pathType;
                            }
                        }
                        if(foundDirt == false){
                            onGrass = true;
                        }
                        pos--;
                    }
                    break;
                case 3:
                    pos = 0;
                    while (pos < main.grassWidth * main.tileSize && onGrass == false){
                        foundDirt = false;
                        for(int j = 0; j < 32; j++){
                            if(garden.simplexResult[pos][(int)(main.entrances.get(i).y + j)] > generator.grassThreshold){
                                foundDirt = true;
                                garden.simplexResult[pos][(int)(main.entrances.get(i).y + j)] = pathType;
                            }
                        }
                        if(foundDirt == false){
                            onGrass = true;
                        }
                        pos++;
                    }
                    break;
            }
        }
    }
}
