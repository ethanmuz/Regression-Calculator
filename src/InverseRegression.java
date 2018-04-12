import java.util.ArrayList;

public class InverseRegression {
    private ArrayList<Double> x;    // ArrayList for x-values of the dataset
    private ArrayList<Double> y;    // ArrayList for Y-values of the dataset

    private Double invXbar;
    private Double ybar;

    private Double Sxx;
    private Double Sxy;
    private Double Syy;

    private Double a;
    private Double b;
    private Double r;

    InverseRegression(ArrayList<Double> x, ArrayList<Double> y){
        this.x = x; this.y = y; // Store x and y values
        findInvXBar(); findYBar();
        findSxx(); findSxy(); findSyy();
        findA(); findB(); findR();
    }

    private void findInvXBar(){
        Double total = 0.0;
        for (Double i:x)
            total+=(1.0/i);
        invXbar = total/x.size();
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
            total+=Math.pow(1.0/i,2);
        Sxx = total / x.size() - Math.pow(invXbar, 2);
    }

    private void findSxy(){
        Double total = 0.0;
        for (int i = 0; i < x.size(); i++)
            total+=(1.0/x.get(i))*y.get(i);
        Sxy = total / x.size() - (invXbar*ybar);
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
        b = ybar-(a*invXbar);
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

    public Double getR2(){
        return r*r;
    }
}
