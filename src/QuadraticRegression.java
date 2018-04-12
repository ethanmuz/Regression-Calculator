import java.util.ArrayList;

public class QuadraticRegression {
    private ArrayList<Double> x;    // ArrayList for x-values of the dataset
    private ArrayList<Double> y;    // ArrayList for Y-values of the dataset

    private Double a;   // Calculated slope
    private Double b;  //Calculated y-intercept
    private Double c;
    private Double r2;   // r - residual analysis, goodness-of-fit index

    private Double xbar;    // Mean of x values
    private Double ybar;    // Mean of y values
    private Double x2bar;
    private Double Sxx;  // Standard deviation of x values
    private Double Sxx2;
    private Double Sxy;
    private Double Sx2y;
    private Double Sx2x2;

    QuadraticRegression(ArrayList<Double> x, ArrayList<Double> y){
        this.x = x; this.y = y; // Store x and y values
        findXbar(); findYbar(); findX2bar(); findSxx();
        findSxy(); findSxx2(); findSx2y(); findSx2x2(); //findR();    // Calculate the variables needed to find slope and Y-intercept
        findA(); findB(); findC();
        findR2();
    }

    /**
     * Calculates x-bar, which is the mean (average) of all the x values
     */
    private void findXbar(){
        Double total = 0.0;
        for (Double i: x)
            total+=i;   // Adds up all x values
        xbar = total/x.size();  // Divides total by number of elements
    }

    private void findX2bar(){
        Double total = 0.0;
        for (Double i: x)
            total+=Math.pow(i,2);   // Adds up all x values
        x2bar = total/x.size();  // Divides total by number of elements
    }

    /**
     * Calculates y-bar, which is the mean (average) of all the y values
     */
    private void findYbar(){
        Double total = 0.0;
        for (Double i: y)
            total+=i;   // Adds up all y values
        ybar = total/y.size();  // Divides total by number of elements
    }

    private void findSxx(){
        Double x2total = 0.0;
        for (Double i:x) {
            x2total += i * i;
        }
        Sxx = x2total/x.size() - Math.pow(xbar,2);
    }

    private void findSxy(){
        Double xytotal = 0.0;
        for (int i =0; i < x.size(); i++) {
            xytotal += x.get(i) * y.get(i);
        }
        Sxy = xytotal/x.size() - (xbar*ybar);
    }

    private void findSxx2(){
        Double x3total = 0.0;
        for (Double i:x)
            x3total+=Math.pow(i,3);
        Sxx2 = x3total/x.size()-(xbar*x2bar);
    }

    private void findSx2x2(){
        Double x4total = 0.0;
        for (Double i:x)
            x4total += Math.pow(i,4);
        Sx2x2 = x4total/x.size()-Math.pow(x2bar,2);
    }

    private void findSx2y(){
        Double x2ytotal = 0.0;
        for (int i =0; i< x.size(); i++)
            x2ytotal+=Math.pow(x.get(i),2)*y.get(i);
        Sx2y = x2ytotal/x.size()-(x2bar*ybar);
    }

    private void findA(){
        a = ((Sx2y*Sxx)-(Sxy*Sxx2))/((Sxx*Sx2x2)-(Math.pow(Sxx2,2)));
    }

    private void findB(){
        b = ((Sxy*Sx2x2)-(Sx2y*Sxx2))/((Sxx*Sx2x2)-(Math.pow(Sxx2,2)));
    }

    private void findC(){
        c = ybar-(b*xbar)-(a*x2bar);
    }

    public void findR2(){
        double numerator = 0.0;
        double denominator = 0.0;
        for (int i =0; i < x.size(); i++) {
            numerator += Math.pow(y.get(i) - (c + (b * x.get(i)) + (a * x.get(i) * x.get(i))), 2);
            denominator+=Math.pow(y.get(i)-ybar,2);
        }
        r2 = 1 - (numerator/denominator);
    }

    public Double getA(){
        return a;
    }

    public Double getB(){
        return b;
    }

    public Double getC(){
        return c;
    }

    /**
     * @return r^2: the goodness-of-fit index for the given regression line
     */
    public Double getR2(){
        return r2;
    }
}
