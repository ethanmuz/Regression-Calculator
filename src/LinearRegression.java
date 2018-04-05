import java.util.ArrayList;

public class LinearRegression {
    private ArrayList<Double> x;
    private ArrayList<Double> y;

    private Double slope;
    private Double yIntercept;
    private Double r;

    private Double xbar;
    private Double ybar;
    private Double Sx;
    private Double Sy;

    LinearRegression(ArrayList<Double> x, ArrayList<Double> y){
        this.x = x; this.y = y;
        findXbar(); findYbar(); findSx(); findSy(); findR();
        findSlope(); findYintercept();
    }

    private void findXbar(){
        Double total = 0.0;
        for (Double i: x)
            total+=i;
        xbar = total/x.size();
    }

    private void findSx(){
        Double total = 0.0;
        for (Double i: x)
            total+=Math.pow(i-xbar,2);
        Sx = Math.sqrt(total/(x.size()-1));
    }

    private void findSy(){
        Double total = 0.0;
        for (Double i: y)
            total+=Math.pow(i-ybar,2);
        Sy = Math.sqrt(total/(y.size()-1));
    }

    private void findYbar(){
        Double total = 0.0;
        for (Double i: y)
            total+=i;
        ybar = total/y.size();
    }

    private void findR(){
        Double numerator = 0.0;
        for (int i = 0; i < x.size(); i++)
            numerator += (x.get(i)-xbar)*(y.get(i)-ybar);

        Double xMinusXbar2 = 0.0;
        for (Double i: x)
            xMinusXbar2+=Math.pow(i-xbar,2);

        Double yMinusYbar2 = 0.0;
        for (Double i: y)
            yMinusYbar2+=Math.pow(i-ybar,2);

        Double denominator = Math.sqrt(xMinusXbar2 * yMinusYbar2);

        r = numerator/denominator;
    }

    private void findSlope(){
        slope = r * (Sy/Sx);
    }

    private void findYintercept(){
        yIntercept = ybar - (slope*xbar);
    }

    public Double getSlope(){
        return slope;
    }

    public Double getyIntercept(){
        return yIntercept;
    }

    public Double getR2(){
        return r*r;
    }
}
