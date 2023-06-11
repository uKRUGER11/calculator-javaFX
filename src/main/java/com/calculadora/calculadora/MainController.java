package com.calculadora.calculadora;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    @FXML private Pane titlePane;
    @FXML private ImageView btnMinimize, btnClose;
    @FXML private Label labelResult;

    private double x, y;
    private double num1 = 0.0;
    private String operation = "+";

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    @FXML
    void onBtNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("bt", ""));
        labelResult.setText(Double.parseDouble(labelResult.getText()) == 0 ? String.valueOf((double)value) : String.valueOf(Double.parseDouble(labelResult.getText()) * 10 + value));
    }

    @FXML
    void onBtSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("bt", "");

        if (symbol.equals("Equal")) {
            double num2 = Double.parseDouble(labelResult.getText());
            switch(operation) {
                case "+" -> labelResult.setText((num1 + num2) + "");
                case "-" -> labelResult.setText((num1 - num2) + "");
                case "*" -> labelResult.setText((num1 * num2) + "");
                case "/" -> labelResult.setText((num1 / num2) + "");
            }
            operation = ".";
        } else if (symbol.equals("Clear")) {
            labelResult.setText(String.valueOf(0.0));
            operation = ".";
        } else {
            switch (symbol) {
                case "Plus" -> operation = "+";
                case "Minus" -> operation = "-";
                case "Multiply" -> operation = "*";
                case "Divide" -> operation = "/";
            }
            num1 = Double.parseDouble(labelResult.getText());
            labelResult.setText(String.valueOf(0.0));
        }
    }
}