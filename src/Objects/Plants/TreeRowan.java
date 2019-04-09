package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.URL;

public class TreeRowan extends Plant {
    public TreeRowan(int x, int y) throws IOException {
        super(x, y);
        init();
    }

    public TreeRowan(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Rowan";
        minSpread = 4;
        maxSpread = 8;
        this.width = (minSpread + maxSpread)/2;

        foliageType = 2; //deciduous
        type = 0;
        plantingPos = 0;
        hardiness = 4; //very
    }
}
