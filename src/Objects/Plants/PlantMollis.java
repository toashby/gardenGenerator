package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class PlantMollis extends Plant {
    public PlantMollis(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Mollis";
        minSpread = 0f;
        maxSpread = 1f;
        this.width = (minSpread + maxSpread)/2;

        foliageType = 2; //deciduous
        type = 2; //perrenial
        plantingPos = 3; //sun or partial shade
        hardiness = 5; //ultra

    }
}
