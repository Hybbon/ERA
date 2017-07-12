package ec.app.gpra;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ec.Population;
import ec.Subpopulation;
import ec.gp.koza.KozaFitness;

public class MyStatistics {
	
	HashMap<String,Integer> popTerminalsCount;
	private double averageFitnessTest;
	private double averageFitness;
	private double averageFitnessVal;
	private double bestFitnessVal;
	private double bestFitnessTest;
	private double bestHits;
	private double bestHits_use;
	private double prec_5[] = new double[2];
	private double prec_10[] = new double[2];
	private double map_5[] = new double[2];
	private double map_10[] = new double[2];
	
	
	
	public Map<String, Integer> getPopTerminalsCount() {
		return popTerminalsCount;
	}

	public double getAverageFitnessTest() {
		return averageFitnessTest;
	}

	public double getAverageFitnessVal() {
		return averageFitnessVal;
	}

	public double getbestFitnessVal() {
		return bestFitnessVal;
	}




	public MyStatistics(Population pop){
		
		popTerminalsCount = new HashMap<String, Integer>();
		
		//inicialmente so considero uma subpopulacao
		Subpopulation subpop = pop.subpops[0];
		double avgFitTest = 0;
		double avgFitVal = 0;
		double avgFit = 0;
		int subpopSize = subpop.individuals.length;
		double bestFit = 1000; //TODO alterar pra receber inf
		double bestFitTest = 1000;
		double bestHits_x = 0;
		double bestHits_use_x = 0;
		MyIndividual bestInd = null;
		
		for(int i  = 0; i < subpopSize; i++){

			MyIndividual myind = (MyIndividual) subpop.individuals[i];
			//################################################
			//retrieve the couting o terminals
			/*String s = subpop.individuals[i].genotypeToString();
			 
			
			HashMap<String,Integer> indTerminals = myind.getTerminalsCount();
			
			Iterator<String> iter = indTerminals.keySet().iterator();
			while(iter.hasNext()){
				String key = iter.next();
				
				if(popTerminalsCount.containsKey(key)){
					//atualiza o numero de terminais da populacao com o numero de terminais do individuo
					popTerminalsCount.put(key,popTerminalsCount.get(key)+indTerminals.get(key));
					
				}else
				{
					popTerminalsCount.put(key, indTerminals.get(key));
				}
			}*/
		
			//######################################################



		}
	}
	
	
	
	public String toString(){
		
		String s = "";

		return s;
		
		
	}

}
