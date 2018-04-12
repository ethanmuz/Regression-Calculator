import java.util.ArrayList;

public class LogarithmicRegression {
    private ArrayList<Double> x;    // ArrayList for x-values of the dataset
    private ArrayList<Double> y;    // ArrayList for Y-values of the dataset

    private Double lnxbar;
    private Double ybar;

    private Double Sxx;
    private Double Sxy;
    private Double Syy;

    private Double a;
    private Double b;
    private Double r;

    LogarithmicRegression(ArrayList<Double> x, ArrayList<Double> y){
        this.x = x; this.y = y; // Store x and y values
        findLnxBar(); findYBar();
        findSxx(); findSxy(); findSyy();
        findA(); findB(); findR();
    }

    private void findLnxBar(){
        Double total = 0.0;
        for (Double i:x)
            total+=Math.log(i);
        lnxbar = total/x.size();
    }

    private void findYBar(){
        Double total = 0.0;
        for (Double i:y)
            total+=i;
        ybar = total/y.size();
    }

    private void findSxx(){
        Double total = 0.0;
        for (Double i:x)
            total+=Math.log(i)*Math.log(i);
        Sxx = total / x.size() - Math.pow(lnxbar, 2);
    }

    private void findSxy(){
        Double total = 0.0;
        for (int i = 0; i < x.size(); i++)
            total+=Math.log(x.get(i))*y.get(i);
        Sxy = total / x.size() - (lnxbar*ybar);
    }

    private void findSyy(){
        Double total = 0.0;
        for (Double i:y)
            total+=Math.pow(i,2);
        Syy = total / y.size() - Math.pow(ybar, 2);
    }

    private void findA(){
        a = Sxy/Sxx;
    }

    private void findB(){
        b = ybar - (a*lnxbar);
    }

    private void findR(){
        r = Sxy/(Math.sqrt(Sxx)*Math.sqrt(Syy));
    }

    public double getA(){
        return a;
    }

    public double getB(){
        return b;
    }

    public double getR2(){
        return r*r;
    }
}
