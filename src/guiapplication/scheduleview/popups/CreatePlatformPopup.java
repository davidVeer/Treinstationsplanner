package guiapplication.scheduleview.popups;

import data.*;
import guiapplication.PopupView;
import guiapplication.ReturnableView;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class CreatePlatformPopup extends PopupView {

    private ScheduleBuilder scheduleBuilder;

    public CreatePlatformPopup(ReturnableView mainView, Schedule schedule) {
        super(mainView);
        this.scheduleBuilder = new ScheduleBuilder(schedule);
    }

    @Override
    public Node getNode() {
        BorderPane pane = new BorderPane();

        Label infoLabel = new Label("Voer platform nummer in:");
        TextField inputField = new TextField();
        VBox inputBox = new VBox(infoLabel, inputField);

        Button cancelButton = new Button("Annuleer");
        cancelButton.setOnAction(e -> super.callMainView());
        Button saveButton = new Button("Voeg toe");
        saveButton.setOnAction(e -> {
            try {//try-catch zodat er geen karakters of letters kunnen worden gebruikt. Het geeft dan een warning
                this.scheduleBuilder.createPlatform(Integer.parseInt(inputField.getText()));
                inputField.clear();
                super.callMainView();
            }catch (Exception numberNotFound){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Error, Het kan zijn dat je iets anders hebt neergezet dan een nummer");
                alert.showAndWait();
            }
        });
        FlowPane buttonBar = new FlowPane(cancelButton, saveButton);
        buttonBar.setPadding(new Insets(10));

        pane.setCenter(inputBox);
        pane.setBottom(buttonBar);
        return pane;
    }
}
