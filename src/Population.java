import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Population {
	ArrayList<DNA> population;
	String target;
	double mutationRate;
	String bestGene;
	boolean isFinished = false;
	double averageFitness;
	
	public Population(String target, double mutationRate, int populationCount) {
		this.target = target;
		this.mutationRate = mutationRate;
		population = new ArrayList<DNA>();
		initialise(populationCount);
	}
	
	public void initialise(int populationCount) {
		for(int i = 0; i < populationCount; i++) {
			population.add(new DNA(target.length()));
		}
	}

	public void calculateFitness() {
		DNA bestDNA = population.get(0);
		for(DNA dna: population) {
			dna.calculateFitness(target);
			if(dna.fitness > bestDNA.fitness) {
				bestDNA = dna;
			}
		}
		bestGene = bestDNA.genes;
		if(bestDNA.genes.equals(target)) {
			isFinished = true;
		}
	}
	
	public DNA getParent() {
		Random r = new Random();
		double fitnessTotal = 0;
		for(DNA dna: population) {
			fitnessTotal += dna.fitness;
		}
		
		//
		DNA bestDNA = new DNA(target.length());
		bestDNA.genes = target;
		bestDNA.calculateFitness(target);
		averageFitness = (fitnessTotal/population.size())/bestDNA.fitness;
		//
		
		double pickRandom = r.nextDouble()*fitnessTotal;
		DNA parent = new DNA(target.length());
		for(DNA dna: population) {
			pickRandom -= dna.fitness;
			if(pickRandom < 0) {
				parent = dna;
				break;
			}
		}
		return parent;
	}
	
	public void generateNewPopulation() {
		calculateFitness();
		ArrayList<DNA> newPopulation = new ArrayList<DNA>();
		for(int i = 0; i < population.size(); i++) {
			DNA dna = getParent().crossOver(getParent());
			dna.mutate(mutationRate);
			newPopulation.add(dna);
		}
		population = newPopulation;
	}
	
	public void draw(Graphics2D g, int x, int y, int fontSize) {
		for(DNA dna: population) {
			dna.draw(g, x, y, fontSize);
			y += fontSize;
		}
	}
}
