package me.therin.math451;

import java.util.ArrayList;
import java.util.List;

public class NewtonsMethod implements RootFindingAlgorithm {

    @Override
    public double findRoot(DifferentiableFunction func, double start, double end) {
        DifferentiableFunction funcDiff = func.differentiate();
        
        for (int i = 0; i < 100; i++) {
            double fVal = func.evaluate(start);
            double fDiffVal = funcDiff.evaluate(start);
            
            if (Math.abs(fDiffVal) < Polynomial.epsilon)
                throw new IllegalArgumentException("Derivative is possibly zero at root, cannot continue.");
                
            start -= fVal / fDiffVal;
            
            if (Math.abs(fVal) < Polynomial.epsilon)
                break;
            
            System.out.println(start);
        }
        
        return start;
    }
    
    public static void main(String args[]) {
        List<Double> coeffs = new ArrayList<Double>() {{
            add(4d);
            add(5d);
            add(-2d);
        }};
        
        Polynomial poly = new Polynomial(coeffs);
        NewtonsMethod nm = new NewtonsMethod();
        
        System.out.println(nm.findRoot(poly, 800000, 0));
    }
}
