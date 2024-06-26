package guiapplication.scheduleview.popups.create;

import data.*;
import guiapplication.ReturnableView;
import guiapplication.scheduleview.popups.SchedulePopupView;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class CreateWagonPopup extends SchedulePopupView {

    public CreateWagonPopup(ReturnableView mainView, Schedule schedule) {
        super(mainView, schedule);
        this.schedule = schedule;
    }

    @Override
    public Node getNode() {
        BorderPane pane = new BorderPane();

        Label idNumberWagon = new Label("Voer het ID nummer van de wagon in:");
        TextField idNumberWagonInput = new TextField();
        VBox idNumberWagonBox = new VBox(idNumberWagon, idNumberWagonInput);

        Label wagonCapacity = new Label("Voer maximum capaciteit (75 max) in:");
        TextField wagonCapacityInput = new TextField();
        VBox wagonCapacityBox = new VBox(wagonCapacity, wagonCapacityInput);

        Button saveButton = new Button("Voeg toe");
        saveButton.setOnAction(e -> {
            try {
                if (wagonCapacityInput.getText().isEmpty() || idNumberWagonInput.getText().isEmpty() || Integer.parseInt(wagonCapacityInput.getText()) > 75) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Error, je bent data vergeten in te vullen of hebt de capaciteit van 75 overschreden");
                    alert.showAndWait();
                } else {
                    schedule.addWagon(new Wagon(
                            idNumberWagonInput.getText(),
                            Integer.parseInt(wagonCapacityInput.getText())
                    ));
                    super.callMainView();
                }
            } catch (Exception numberNotFound) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Error, Het kan zijn dat je iets anders hebt neergezet dan een nummer");
                alert.showAndWait();
            }
        });

        FlowPane buttonBar = new FlowPane(super.getCloseButton(), saveButton);

        VBox inputBox = new VBox(idNumberWagonBox, wagonCapacityBox);
        pane.setCenter(inputBox);
        pane.setBottom(buttonBar);
        return pane;
    }
}
