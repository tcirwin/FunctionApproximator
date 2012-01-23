package me.therin.test.math451;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import me.therin.math451.Polynomial;

import org.junit.BeforeClass;
import org.junit.Test;

public class PolynomialTest {

    private List<PolyTestItem> tests = new ArrayList<PolyTestItem>() {{
        add(new PolyTestItem(new ArrayList<Double>() {{
            add(2d); add(5d); add(-8d);
        }}, new HashMap<Double, Double>() {{
            put(5d, 67d); put(4d, 44d); put(3d, 25d);
        }}, new HashMap<Double, Double>() {{
            put(5d, 25d); put(4d, 21d); put(3d, 17d);
        }}));
    }};
    
    private class PolyTestItem {
        public List<Double> coeffs;
        public Map<Double, Double> testPoints;
        public Map<Double, Double> diffTestPoints;
        
        public PolyTestItem(List<Double> coeffs, Map<Double, Double> hashMap, Map<Double, Double> diff) {
            this.coeffs = coeffs;
            this.testPoints = hashMap;
            this.diffTestPoints = diff;
        }
    }
    
    @Test
    public void evalTest() {
        Polynomial pn;
        
        for (PolyTestItem test : tests) {
            pn = new Polynomial(test.coeffs);
            
            for (Double key : test.testPoints.keySet()) {
                assertTrue(Math.abs(pn.evaluate(key) - test.testPoints.get(key)) < Polynomial.epsilon); 
            }
        }
    }
    
    @Test
    public void diffTest() {
        Polynomial pn;
        Polynomial tpn;
        
        for (PolyTestItem test : tests) {
            int terms = test.coeffs.size() - 1;
            pn = new Polynomial(test.coeffs);
            tpn = pn.differentiate();
            Iterator<Double> iter = test.coeffs.iterator();
            
            for (Double ct : tpn.getTermCoeffs()) {
                assertTrue(Math.abs(iter.next() * terms - ct) < Polynomial.epsilon);
                terms--;
            }
            
            for (Double key : test.diffTestPoints.keySet()) {
                assertTrue(Math.abs(tpn.evaluate(key) - test.diffTestPoints.get(key)) < Polynomial.epsilon); 
            }
        }
    }
}
