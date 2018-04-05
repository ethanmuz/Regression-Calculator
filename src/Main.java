public class Main {
    public static void main(String[]args){
        double[] x = {17,13,12,15,16,14,16,16,18,19};
        double[] y = {94,73,59,80,93,85,66,79,77,91};

        LinearRegression LR = new LinearRegression(x, y);
        System.out.println("y = " + LR.getSlope() + "x + " + LR.getyIntercept());
        System.out.println("r^2 = " + LR.getR2());
    }
}
