package Objects.Plants;

import Driver.Main;
import Objects.Plant;
import Utility.Point2D;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.URL;

public class TreeBirch extends Plant {
    public TreeBirch(int x, int y){
        super(x, y);
        init();
    }
    public TreeBirch(Point2D pnt, Main main){
        super(pnt, main);
        init();
    }

    public void init(){
        name = "Birch";
        minSpread = 3;
        maxSpread = 4;
        this.width = (minSpread + maxSpread)/2;

        foliageType = 2; //deciduous
        type = 0; //tree
        plantingPos = 3; //sun or partial shade
        hardiness = 4; //very

    }
}
