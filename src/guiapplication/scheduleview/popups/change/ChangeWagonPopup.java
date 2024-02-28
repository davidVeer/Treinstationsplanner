package guiapplication.scheduleview.popups.change;

import data.*;
import guiapplication.ReturnableView;
import guiapplication.scheduleview.popups.SchedulePopupView;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ChangeWagonPopup extends SchedulePopupView {

    private Schedule schedule;

    public ChangeWagonPopup(ReturnableView mainView, Schedule schedule) {
        super(mainView);
        this.schedule = schedule;
    }

    @Override
    public Node getNode() {
        BorderPane pane = new BorderPane();

        Label amountSelectionLable = new Label("Kies uit de hoeveelheid wagens:");
        ComboBox<Wagon> amountSelectionComboBox = new ComboBox<>(FXCollections.observableList(this.schedule.getWagonList()));
        VBox amountSelectionBox = new VBox(amountSelectionLable,amountSelectionComboBox);

        Label toChangeLabel = new Label("waar wilt u het nummer naar veranderen?:");
        TextField toChangeTextField = new TextField();
        VBox toChangeBox = new VBox(toChangeLabel, toChangeTextField);


        Button saveButton = new Button("Voeg toe");
        saveButton.setOnAction(e -> {
            if (false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Error, je bent data vergeten in te vullen");
                alert.showAndWait();
            } else {
                this.schedule.getWagonList().get(
                        this.schedule.getWagonList().indexOf(amountSelectionComboBox.getValue())
                ).setMaxCapacity(Integer.parseInt(toChangeTextField.getText()));
                super.callMainView();
            }
        });

        FlowPane buttonBar = new FlowPane(super.getCloseButton(), saveButton);

        VBox inputBox = new VBox(amountSelectionBox,toChangeBox);
        pane.setCenter(inputBox);

        amountSelectionComboBox.setOnAction((event) -> {

        });

        pane.setBottom(buttonBar);
        return pane;
    }
}
