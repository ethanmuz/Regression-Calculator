public class LinearRegression {
    private double[] x;
    private double[] y;

    private double slope;
    private double yIntercept;
    private double r;

    private double xbar;
    private double ybar;
    private double Sx;
    private double Sy;

    public LinearRegression(double[] x, double[] y){
        this.x = x; this.y = y;
        findXbar(); findYbar(); findSx(); findSy(); findR();
        findSlope(); findYintercept();
    }

    private void findXbar(){
        double total = 0.0;
        for (double i: x)
            total+=i;
        xbar = total/x.length;
    }

    private void findSx(){
        double total = 0.0;
        for (double i: x)
            total+=Math.pow(i-xbar,2);
        Sx = Math.sqrt(total/(x.length-1));
    }

    private void findSy(){
        double total = 0.0;
        for (double i: y)
            total+=Math.pow(i-ybar,2);
        Sy = Math.sqrt(total/(y.length-1));
    }

    private void findYbar(){
        double total = 0.0;
        for (double i: y)
            total+=i;
        ybar = total/y.length;
    }

    private void findR(){
        double numerator = 0.0;
        for (int i = 0; i < x.length; i++)
            numerator += (x[i]-xbar)*(y[i]-ybar);

        double xMinusXbar2 = 0.0;
        for (double i: x)
            xMinusXbar2+=Math.pow(i-xbar,2);

        double yMinusYbar2 = 0.0;
        for (double i: y)
            yMinusYbar2+=Math.pow(i-ybar,2);

        double denominator = Math.sqrt(xMinusXbar2 * yMinusYbar2);

        r = numerator/denominator;
    }

    public void findSlope(){
        slope = r * (Sy/Sx);
    }

    public void findYintercept(){
        yIntercept = ybar - (slope*xbar);
    }

    public double getSlope(){
        return slope;
    }

    public double getyIntercept(){
        return yIntercept;
    }

    public double getR2(){
        return r*r;
    }
}
