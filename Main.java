import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	//This is where I have the graph, and this is where you can tweak the variables to change the graph to see if code works or not
    	String str = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
  
        Graph g = new Graph(str);
        System.out.println("Output #1: " + g.displayDistance("ABC"));
        System.out.println("Output #2: " + g.displayDistance("AD"));
        System.out.println("Output #3: " + g.displayDistance("ADC"));
        System.out.println("Output #4: " + g.displayDistance("AEBCD"));
        System.out.println("Output #5: " + g.displayDistance("AED"));
        System.out.println("Output #6: " + g.calculateTripsCount("C", "C", t -> t <= 3, 3));
        System.out.println("Output #7: " + g.calculateTripsCount("A", "C", t -> t == 4, 4));
        System.out.println("Output #8: " + g.calculateShortestPath("A", "C"));
        System.out.println("Output #9: " + g.calculateShortestPath("B", "B"));
        System.out.println("Output #10: " + g.calculateRoutesCount("C", "C", 30));
      
    }
    
} 
 