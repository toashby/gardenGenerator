package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;


public class ShrubWeigela extends Plant {
    public ShrubWeigela(int x, int y) throws IOException {
        super(x, y);
        init();
    }

    public ShrubWeigela(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Weigela";
        minSpread = 1.5f;
        maxSpread = 2.5f;
        this.width = (minSpread + maxSpread)/2;

        foliageType = 2; //deciduous
        type = 1; //shrub
        plantingPos = 5; //any
        hardiness = 5; //ultra

    }
}
