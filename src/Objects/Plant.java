package Objects;

import Driver.Main;
import Utility.Point2D;
import javafx.scene.image.Image;

public abstract class Plant {
    public String name;
    public int x;
    public int y;
    public int hardiness; //0-5 tender, semi, moderate, moderate/very, very, ultra
    public int type; //tree, shrub, or perennial
    public int foliageType; //evergreen, semi, or deciduous/herbaceous
    public int plantingPos; //full sun, partial shade, full shade, sun/partial, partial/full, any
    public int season;
    public static float minSpread;
    public static float maxSpread;
    //public Image image;
    public float width;
    public Main main;

    public Plant(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Plant(Point2D pnt, Main main){
        this.x = pnt.x;
        this.y = pnt.y;
        this.main = main;
    }

    public Plant() {

    }

    public float getWidth(){
        return width;
    }

    public Image getImage(){
        return main.imageLoader.getImage(name);
    }

}
