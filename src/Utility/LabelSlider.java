package Utility;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class LabelSlider {
    HBox contianer = new HBox();
    Slider slider;
    Label label;

    public LabelSlider(String Label, int min, int max, int def){
        label = new Label(Label);
        slider = new Slider(min, max, def);
        contianer.getChildren().addAll(label, slider);
    }

    public HBox getContianer() {
        return contianer;
    }

    public Slider getSlider() {
        return slider;
    }

    public Label getLabel() {
        return label;
    }
}
