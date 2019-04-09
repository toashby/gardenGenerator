package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class TreeAcer extends Plant {
    public TreeAcer(int x, int y) throws IOException {
        super(x, y);
        init();
    }

    public TreeAcer(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Acer";
        minSpread = 1;
        maxSpread = 8;
        this.width = (minSpread + maxSpread)/2;

        foliageType = 2; //deciduous
        type = 0;
        plantingPos = 3; // sun/partial
        hardiness = 4; //very

    }
}
