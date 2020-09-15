import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

public class DNA {
	String genes;
	double fitness;
	
	DNA(int length){
		genes = "";
		for(int i = 0; i < length; i++) {
			genes += getRandomChar();
		}
	}
	
	public String getRandomChar(){
		Random r = new Random();
		int cInt = 1 + r.nextInt(54);
		if(cInt == 1) {
			return " ";
		}else if(cInt == 2) {
			return ".";
		}else if(cInt > 2 && cInt <= 28) {
			return String.valueOf((char) (62 + cInt));
		}else{
			return String.valueOf((char) (68 + cInt));
		}
	}
	
	public void calculateFitness(String target) {
		double correctness = 0;
		for(int i = 0; i < genes.length(); i++) {
			if(genes.charAt(i) == target.charAt(i)) {
				correctness++;
			}
		}
		//fitness = correctness/target.length();
		
		//BETTER FITNESS
		fitness = Math.pow(correctness, 3);
	}
	
	public DNA crossOver(DNA otherDNA) {
		Random r = new Random();
		DNA dna = new DNA(genes.length());
		dna.genes = "";
		for(int i = 0; i < genes.length(); i++) {
			if(r.nextInt(2) == 0) {
				dna.genes += genes.charAt(i);
			}else {
				dna.genes += otherDNA.genes.charAt(i);
			}
		}
		return dna;
	}
	
	public void mutate(double mutationRate) {
		Random r = new Random();
		String newGene = "";
		for(int i = 0; i < genes.length(); i++) {
			if(r.nextDouble() < mutationRate) {
				newGene += getRandomChar();
			}else {
				newGene += genes.charAt(i);
			}
		}
		genes = newGene;
	}
	
	public void draw(Graphics2D g, int x, int y, int fontSize) {
		g.setColor(new Color(60, 59, 64));
		g.setFont(new Font("Courier New", Font.BOLD, fontSize));
		g.drawString(genes, x, y);
	}
}
