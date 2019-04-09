package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class TreeFloweringCherry extends Plant {
    public TreeFloweringCherry(int x, int y) throws IOException {
        super(x, y);
        init();
    }

    public TreeFloweringCherry(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Flowering Cherry";
        minSpread = 4;
        maxSpread = 8;
        this.width = (minSpread + maxSpread)/2;

        foliageType = 2; //deciduous
        type = 0;
        plantingPos = 0; // full sun
        hardiness = 4; //very

    }
}
