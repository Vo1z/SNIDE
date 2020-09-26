package view.settings;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class OptionHBox extends HBox
{
    private Label label;
    private CheckBox checkBox;

    OptionHBox(String description)
    {
        //Elements configuration
        this.checkBox = new CheckBox();
        this.checkBox.setSelected(false);
        this.label = new Label();
        this.label.setText(description);

        //Adding to layout
        this.getChildren().addAll(label, checkBox);
    }

    //Getters
    public String getDescription()
    {
        return label.getText();
    }

    public boolean isChecked()
    {
        return checkBox.isSelected();
    }

    public void setSelected(boolean isChecked)
    {
        this.checkBox.setSelected(isChecked);
    }
}
