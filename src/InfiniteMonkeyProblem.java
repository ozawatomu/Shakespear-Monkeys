import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class InfiniteMonkeyProblem extends JPanel implements ActionListener{
	Timer timer = new Timer(1, this);
	static int screenSizeWidth = 1620;
	static int screenSizeHeight = 1620;
	static String target = "To be or not to be";
	static double mutation = 0.01;
	static int populationCount = 200;
	
	static int generationCount = 1;
	static Population population;
	
	public static void main(String[] args) {
		population = new Population(target, mutation, populationCount);
		
		JFrame jFrame = new JFrame();
		jFrame.setTitle("Shakespear Monkeys");
		jFrame.setSize(screenSizeWidth, screenSizeHeight);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		InfiniteMonkeyProblem infiniteMonkeyProblem = new InfiniteMonkeyProblem();
		infiniteMonkeyProblem.setPreferredSize(new Dimension(screenSizeWidth, screenSizeHeight));
		jFrame.add(infiniteMonkeyProblem);
		jFrame.pack();
	}

	public void paintComponent(Graphics g) {
		population.generateNewPopulation();
		generationCount++;
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(233, 231, 233));
		g2d.fillRect(0, 0, screenSizeWidth, screenSizeHeight);
		g2d.setColor(new Color(60, 59, 64));
		g2d.setFont(new Font("Courier New", Font.BOLD, 40));
		g2d.drawString("Best Phrase:", 60, 100);
		g2d.drawString(population.bestGene, 108, 150);
		g2d.setFont(new Font("Courier New", Font.BOLD, 30));
		g2d.drawString("Population Count: " + populationCount, 60, 220);
		DecimalFormat df = new DecimalFormat("#.#");
		g2d.drawString("Mutation Rate: " + df.format(mutation*100) + "%", 60, 260);
		g2d.drawString("Average Fitness: " + df.format(population.averageFitness*100) + "%", 60, 300);
		g2d.drawString("Generation: " + generationCount, 60, 340);
		
		population.draw(g2d, 900, 50, 35);
		
		if(!population.isFinished) {
			timer.start();
		}else {
			timer.stop();
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
}
