package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.URL;

public class TreeMagnolia extends Plant {
    public TreeMagnolia(int x, int y) throws IOException {
        super(x, y);
        init();
    }

    public TreeMagnolia(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Magnolia";
        minSpread = 3;
        maxSpread = 10;
        this.width = (minSpread + maxSpread)/2;

        foliageType = 2; //deciduous
        type = 0; //tree
        plantingPos = 3; //sun or partial shade
        hardiness = 4; //very

    }
}