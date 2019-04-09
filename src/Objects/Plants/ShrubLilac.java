package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class ShrubLilac extends Plant {
    public ShrubLilac(int x, int y) throws IOException {
        super(x, y);
        init();
    }

    public ShrubLilac(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Lilac";
        minSpread = 2.5f;
        maxSpread = 4f;
        this.width = (minSpread + maxSpread)/2;

        foliageType = 2; //deciduous
        type = 1; //shrub
        plantingPos = 3; //full/partial
        hardiness = 5; //ultra

    }
}
