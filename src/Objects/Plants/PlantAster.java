package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class PlantAster extends Plant {
    public PlantAster(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Aster";
        minSpread = 0f;
        maxSpread = 1.2f;
        this.width = (minSpread + maxSpread)/2;

        foliageType = 2; //deciduous
        type = 2; //perrenial
        plantingPos = 0; //sun
        hardiness = 5; //ultra

    }
}
