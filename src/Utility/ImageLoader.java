package Utility;

import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class ImageLoader {
    Image achilleaImage;
    Image asterImage;
    Image geraniumImage;
    Image lavenderImage;
    Image mollisImage;
    Image buddlejaImage;
    Image forsythiaImage;
    Image hydrangeaImage;
    Image lilacImage;
    Image mockOrangeImage;
    Image wiegelaImage;
    Image acerImage;
    Image birchImage;
    Image floweringCherryImage;
    Image magnoliaImage;
    Image rowanImage;

    public ImageLoader(){
        
        
        URL url = getClass().getResource("/res/achillea.png");
        
        try {
            achilleaImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/aster.png");
        try {
            asterImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        url = getClass().getResource("/res/geranium.png");
        try {
            geraniumImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/lavender.png");
        try {
            lavenderImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/mollis.png");
        try {
            mollisImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/buddleja.png");
        try {
            buddlejaImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/forsythia.png");
        try {
            forsythiaImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/hydrangea.png");
        try {
            hydrangeaImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
  
        url = getClass().getResource("/res/lilac.png");
        try {
            lilacImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/mockOrange.png");
        try {
            mockOrangeImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        url = getClass().getResource("/res/weigela.png");
        try {
            wiegelaImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/acer.png");
        try {
            acerImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/birch.png");
        try {
            birchImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/floweringCherry.png");
        try {
            floweringCherryImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/magnolia.png");
        try {
            magnoliaImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = getClass().getResource("/res/rowan.png");
        try {
            rowanImage = new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Image getImage(String name){
        switch (name){
            case "Achillea":
                return achilleaImage;
            
            case "Aster":
                return asterImage;

            case "Geranium":
                return geraniumImage;
            

            case "Lavender":
                return lavenderImage;
            

            case "Mollis":
                return mollisImage;
            

            case "Buddleja":
                return buddlejaImage;
            

            case "Forsythia":
                return forsythiaImage;
            

            case "Hydrangea":
                return hydrangeaImage;
            

            case "Lilac":
                return lilacImage;
            

            case "Mock Orange":
                return mockOrangeImage;
            

            case "Weigela":
                return wiegelaImage;
            

            case "Acer":
                return acerImage;
            

            case "Birch":
                return birchImage;
            

            case "Flowering Cherry":
                return floweringCherryImage;
            

            case "Magnolia":
                return magnoliaImage;
            

            case "Rowan":
                return rowanImage;
            
        }
        System.out.println("OH FUCK " + name);
        return null;
    }
}
