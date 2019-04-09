package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class ShrubHydrangea extends Plant {
    public ShrubHydrangea(int x, int y) throws IOException {
        super(x, y);
        init();
    }

    public ShrubHydrangea(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Hydrangea";
        minSpread = 1f;
        maxSpread = 2.5f;
        this.width = (minSpread + maxSpread)/2;

        foliageType = 2; //deciduous
        type = 1; //shrub
        plantingPos = 3; //full/partial
        hardiness = 5; //ultra

    }
}
