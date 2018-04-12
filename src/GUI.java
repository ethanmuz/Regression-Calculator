/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ethan
 */
public class GUI extends Application {
    TextField[][] values;
    String formulas = "";
    Label label;


    @Override
    public void start(Stage primaryStage) {
        BorderPane gui = new BorderPane();
        BorderPane left = new BorderPane();
        GridPane grid = new GridPane();
        values = new TextField[2][20];
        for (int i = 0; i<values.length; i++)
            for (int j = 0; j<values[i].length; j++){
                values[i][j] = new TextField("");
                grid.add(values[i][j], i, j);
            }
        label = new Label();
        Button go = new Button("Calculate");
        go.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                fillValues();
            }
        });

        gui.setLeft(left);
        left.setTop(grid);
        left.setCenter(go);
        gui.setCenter(label);

        Scene scene = new Scene(gui, 300, 250);

        primaryStage.setTitle("Regression Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void fillValues() {
        Scanner scan = new Scanner(System.in);  // Scanner to retrieve data from command line

        //System.out.println("For each point in your data, enter it as \"x,y\", and then press enter. " +
        //       "When you are done entering data, please type \"done\".");

        ArrayList<Double> x;    // ArrayList to hold the x values of the plot
        ArrayList<Double> y;    // ArrayList to hold the y values of the plot
        /*String entry = scan.nextLine(); // Get the first datapoint from the user
        while (!entry.equals("done") && !entry.equals("Done")){ // Continue accepting datapoints until the user enters "done"
            x.add(Double.parseDouble(entry.replace(" ","").split(",")[0])); // Parse the x value
            y.add(Double.parseDouble(entry.replace(" ","").split(",")[1])); // Parse the y value
            entry = scan.nextLine();    // Accept a new point
        }

        // These are here in case you want to put your data points directly into these arrays
        // x = {};
        // y = {};
        */
        ArrayList<Double> nx = new ArrayList<>();    // ArrayList to hold the x values of the plot
        ArrayList<Double> ny = new ArrayList<>();

        for (TextField i : values[0])
            if (!i.getText().equals(""))
                nx.add(Double.parseDouble(i.getText()));
        for (TextField i : values[1])
            if (!i.getText().equals(""))
                ny.add(Double.parseDouble(i.getText()));

        x = new ArrayList<>(nx);
        y = new ArrayList<>(ny);

        HashMap<Object, Double> hash = new HashMap<>();
        Map.Entry<Object, Double> biggestR2 = null;



        LinearRegression LR = new LinearRegression(x, y);   // Pass the x and y values to LinearRegression class
        if (!LR.getR2().isNaN()) {

            hash.put(LR, LR.getR2());
        }


        QuadraticRegression QR = new QuadraticRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        if (!QR.getR2().isNaN()) {
            hash.put(QR, QR.getR2());
        }

        LogarithmicRegression LogR = new LogarithmicRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        if (!LogR.getR2().isNaN()) {

            hash.put(LogR, LogR.getR2());
        }

        ExponentialRegression ExpR = new ExponentialRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        if (!ExpR.getR2().isNaN()) {

            hash.put(ExpR, ExpR.getR2());
        }
        eExponentialRegression eExpR = new eExponentialRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        if (!eExpR.getR2().isNaN()) {

            hash.put(eExpR, eExpR.getR2());
        }
        PowerRegression PowR = new PowerRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        if (!PowR.getR2().isNaN()) {

            hash.put(PowR, PowR.getR2());
        }
        InverseRegression InvR = new InverseRegression(x, y);   // Pass the x and y values to QuadraticRegression class
        if (!InvR.getR2().isNaN())
            hash.put(InvR, InvR.getR2());

        int num = hash.keySet().size();
        for (int i = 0; i < num;i++) {
            biggestR2 = null;
            for (Map.Entry<Object, Double> e : hash.entrySet())
                if (biggestR2 == null || e.getValue().compareTo(biggestR2.getValue()) > 0)
                    biggestR2 = e;

            if (biggestR2.getKey() == LR) {
                formulas += "\n" + "\nLinear Regression Formula:";
                formulas += "\n" + ("y = " + LR.getSlope() + "x + " + LR.getyIntercept());   // Return the regression line y=mx+b
                formulas += "\n" + ("r^2 = " + LR.getR2());  // Return the r^2 value
            }

            if (biggestR2.getKey() == QR) {
                formulas += "\n" + ("\nQuadratic Regression Formula:");
                formulas += "\n" + ("y = " + QR.getA() + "x^2 + " + QR.getB() + "x + " + QR.getC());   // Return the regression line y=ax^2+bx+c
                formulas += "\n" + ("r^2 = " + QR.getR2());  // Return the r^2 value
            }

            if (biggestR2.getKey() == LogR) {
                formulas += "\n" + ("\nLogarithmic Regression Formula:");
                formulas += "\n" + ("y = " + LogR.getA() + "lnx + " + LogR.getB());   // Return the regression line y=ax^2+bx+c
                formulas += "\n" + ("r^2 = " + LogR.getR2());  // Return the r^2 value
            }

            if (biggestR2.getKey() == ExpR) {
                formulas += "\n" + ("\nExponential Regression Formula:");
                formulas += "\n" + ("y = " + ExpR.getA() + " * " + ExpR.getB() + "^x");   // Return the regression line y=ax^2+bx+c
                formulas += "\n" + ("r^2 = " + ExpR.getR2());  // Return the r^2 value
            }

            if (biggestR2.getKey() == eExpR) {
                formulas += "\n" + ("\ne-Exponential Regression Formula:");
                formulas += "\n" + ("y = " + eExpR.getA() + "e^(" + eExpR.getB() + "x)");   // Return the regression line y=ax^2+bx+c
                formulas += "\n" + ("r^2 = " + eExpR.getR2());  // Return the r^2 value
            }

            if (biggestR2.getKey() == PowR) {
                formulas += "\n" + ("\nPower Regression Formula:");
                formulas += "\n" + ("y = " + PowR.getA() + "x^" + PowR.getB());   // Return the regression line y=ax^2+bx+c
                formulas += "\n" + ("r^2 = " + PowR.getR2());  // Return the r^2 value
            }

            if (biggestR2.getKey() == InvR) {
                formulas += "\n" + ("\nInverse-x Regression Formula:");
                formulas += "\n" + ("y = " + InvR.getA() + "/x + " + InvR.getB());   // Return the regression line y=ax^2+bx+c
                formulas += "\n" + ("r^2 = " + InvR.getR2());  // Return the r^2 value
            }
            hash.remove(biggestR2.getKey());
        }
        label.setText(formulas);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
