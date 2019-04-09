package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class PlantLavender extends Plant {
    public PlantLavender(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Lavender";
        minSpread = 0.5f;
        maxSpread = 1.5f;
        this.width = (minSpread + maxSpread)/2;

        foliageType =0; //evergreen
        type = 2; //perrenial
        plantingPos = 0; //sun
        hardiness = 5; //ultra

    }
}
