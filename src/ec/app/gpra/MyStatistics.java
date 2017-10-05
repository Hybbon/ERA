package ec.app.gpra;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ec.Population;
import ec.Subpopulation;
import ec.multiobjective.MultiObjectiveFitness;

public class MyStatistics {

    public double getbestFitnessVal() {
        return 0.;
    }


    private static String indFitnessString(MyIndividual ind) {
        MultiObjectiveFitness f = (MultiObjectiveFitness) ind.fitness;
        double[] objectives = f.getObjectives();
        return "[" + objectives[0] + ", " + objectives[1] + ", " + objectives[2] + "]";
    }

    public MyStatistics(Population pop) {

        Subpopulation subpop = pop.subpops[0];
        int subpopSize = subpop.individuals.length;

        for (int i = 0; i < subpopSize; i++) {

            MyIndividual ind = (MyIndividual) subpop.individuals[i];

//			System.out.println("Individual " + i);
//			System.out.println(indFitnessString(ind));


        }
    }


    public String toString() {

        String s = "";

        return s;


    }

}
