import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Converter extends Application {
    private final String[] UNITS = {"Meters", "Kilometers", "Centimeters", "Millimeters"};
    private ComboBox<String> fromComboBox;
    private ComboBox<String> toComboBox;
    private TextField inputTextField;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Metric Converter");
       
        Label fromLabel = new Label("From:");
        fromComboBox = new ComboBox<>();
        fromComboBox.getItems().addAll(UNITS);
        fromComboBox.getSelectionModel().selectFirst();

        Label toLabel = new Label("To:");
        toComboBox = new ComboBox<>();
        toComboBox.getItems().addAll(UNITS);
        toComboBox.getSelectionModel().selectLast();

        Label inputLabel = new Label("Value:");
        inputTextField = new TextField();

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convert());

        resultLabel = new Label();
        resultLabel.setWrapText(true);
        

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(titleLabel, 0, 0, 2, 1);

        gridPane.add(fromLabel, 0, 1);
        gridPane.add(fromComboBox, 1, 1);

        gridPane.add(toLabel, 0, 2);
        gridPane.add(toComboBox, 1, 2);

        gridPane.add(inputLabel, 0, 3);
        gridPane.add(inputTextField, 1, 3);

        gridPane.add(convertButton, 0, 4, 2, 1);

        VBox resultBox = new VBox();
        resultBox.setAlignment(Pos.CENTER);
        resultBox.getChildren().add(resultLabel);

        HBox mainBox = new HBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().addAll(gridPane, resultBox);

        Scene scene = new Scene(mainBox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Metric Converter");
        primaryStage.show();
    }

    private void convert() {
        String fromUnit = fromComboBox.getValue();
        String toUnit = toComboBox.getValue();
        String inputValue = inputTextField.getText().trim();
        double input;

        // Check if input is valid
        try {
            input = Double.parseDouble(inputValue);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input");
            return;
        }

        double result;

        //from "fromUnit" to meters
        switch (fromUnit) {
            case "Meters":
                result = input;
                break;
            case "Kilometers":
                result = input * 1000;
                break;
            case "Centimeters":
                result = input / 100;
                break;
            case "Millimeters":
                result = input / 1000;
                break;
            default:
                resultLabel.setText("Unsupported unit: " + fromUnit);
                return;
        }

        //from meters to "toUnit"
    switch (toUnit) {
        case "Meters":
            break;
        case "Kilometers":
            result /= 1000;
            break;
        case "Centimeters":
            result *= 100;
            break;
        case "Millimeters":
            result *= 1000;
            break;
        default:
            resultLabel.setText("Unsupported unit: " + toUnit);
            return;
    }

    resultLabel.setText(inputValue + " " + fromUnit + " = " + String.format("%.2f", result) + " " + toUnit);

}
}