import java.util.*;

public class Main {
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter each datapoint as \"x,y\". When you are done, please type \"done\"");

        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        String entry = scan.nextLine();
        while (!entry.equals("done") && !entry.equals("Done")){
            x.add(Double.parseDouble(entry.split(",")[0]));
            y.add(Double.parseDouble(entry.split(",")[1]));
            entry = scan.nextLine();
        }

        // x = {};
        // y = {};

        LinearRegression LR = new LinearRegression(x, y);
        System.out.println("y = " + LR.getSlope() + "x + " + LR.getyIntercept());
        System.out.println("r^2 = " + LR.getR2());
    }
}
