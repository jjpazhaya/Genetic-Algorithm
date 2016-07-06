import java.util.Random;

public class Population {
	
	Chromosome[] chromosomes;
	Chromosome[] children;
	Chromosome[] genePool;
	Chromosome[] newChromosomes;
	double genAvg;
	double genavg;
	double sumfitness;
	double sumFitness;
	
	public Population(int popsize, int lchrom){
		chromosomes = new Chromosome[popsize];
		
		for(int j = 0; j < popsize; j++){
			chromosomes[j] = new Chromosome(lchrom);
			chromosomes[j].fitness = chromosomes[j].getFitness(chromosomes[j]);
		}
		// chromosomes = new Chromosome[numChromosomes];
		
	}
	
	private int rouletteWheel(int popsize) {
		double partsum = 0.0;
		int spin = 0;
		int found = 0;
		double percent = 0.0;
		sumfitness = 0.0;
		
		for(int j = 0; j < popsize ; j++){
			sumfitness += chromosomes[j].fitness;
		}
		genAvg = sumfitness/popsize;
		
		Random rand = new Random();
		percent = (rand.nextDouble() * sumfitness);
		for(int j = 0; (j < popsize) && (found == 0); j++){
			partsum += chromosomes[j].fitness;
			if(partsum >= percent){
				found = 1;
				spin = j;
			}
		}
		return spin;
	}
	
	public Chromosome[] crossover(int lchrom, int popsize, double pmutation){
		Random rand = new Random();
		children = new Chromosome[popsize];
		int p2, p1;
		for(int i = 0; i < popsize; i += 2){
			p1 = rouletteWheel(popsize);
			p2 = rouletteWheel(popsize);
		
			// attempt to generate unique parents
			while(p1 == p2) {
				p2 = rouletteWheel(popsize);
			}
			Chromosome parent1 = chromosomes[p1];
			Chromosome parent2 = chromosomes[p2];
			// establish cross site to split chromosome
			int jcross = rand.nextInt(lchrom);
		
			// generate children by crossing parent chromosomes
			Chromosome child1 = new Chromosome(lchrom);
			Chromosome child2 = new Chromosome(lchrom);
			for(int j=0; j<jcross; j++) {
				child1.allele[j] = parent2.allele[j];
				child2.allele[j] = parent1.allele[j];
			}
			for(int j=jcross; j<lchrom; j++) {
				child1.allele[j] = parent1.allele[j];
				child2.allele[j] = parent2.allele[j];
			}
		
			// mutate children
			mutation(pmutation,lchrom, child1);
			mutation(pmutation,lchrom, child2);
			
			children[i] = child1;
			children[i+1] = child2;
		}
		for(int j = 0; j < (popsize); j++){
			children[j].fitness = children[j].getFitness(children[j]);
		}
		
		//for(int j = 0; j < (popsize); j++){
		//	System.out.println("PARENT " + j + ": " + printArray(chromosomes[j].allele) + "\tFitness: " + chromosomes[j].fitness);
		//}
		//System.out.println("GENERATION AVERAGE: " + genAvg);
		//for(int j = 0; j < (popsize); j++){
		//	System.out.println("CHILD " + j + ": " + printArray(children[j].allele) + "\tFitness: " + children[j].fitness);
		//}
		
		return children;
	}
	
	// randomly mutate an allele
	private void mutation(double pmutation, int lchrom, Chromosome c){
		Random rand = new Random();
		for(int j=0; j<lchrom; j++) {
			if(rand.nextDouble() < pmutation) 
				c.allele[j] = flip(c.allele[j]);
		}
	}
	
	public Chromosome[] mostFit(int lchrom, int popsize, Chromosome[] offspring){
		genePool = new Chromosome[popsize*2];
		int minIndex = 0;
		for(int i = 0; i < popsize; i++){
			genePool[i] = chromosomes[i];
			genePool[i].fitness = chromosomes[i].fitness;
			genePool[i+popsize] = offspring[i];
			genePool[i+popsize].fitness = offspring[i].fitness;
		}
		for(int i = 0; i < popsize*2; i++){
			minIndex = findMin(i, popsize);
			swap(genePool[i], popsize, minIndex);
		}
		
		//for(int j = 0; j < (popsize*2); j++){
		//	System.out.println("GENEPOOL MEMBER " + j + ": " + printArray(genePool[j].allele) + "\tFitness: " + genePool[j].fitness);
		//}
	
		return genePool;
	
	}
	private int findMin(int n,int popsize){
		double min = 0;
		int minIndex = n;
		min = genePool[n].fitness;
		for(int i = n; i < popsize*2; i++){
			if(min > genePool[i].fitness){
			min = genePool[i].fitness;
			minIndex = i;
			}
		}
		return minIndex;
	}
	private void swap(Chromosome currentChromosome, int popsize, int minIndex){
		int[] temp;
		double dtemp;
		
			temp = currentChromosome.allele;
			currentChromosome.allele = genePool[minIndex].allele;
			genePool[minIndex].allele = temp;
		
			dtemp = currentChromosome.fitness;
			currentChromosome.fitness = genePool[minIndex].fitness;
			genePool[minIndex].fitness = dtemp;	
	}
	// helper function to flip element
	private int flip(int num) {
		int newnum = 0;
		Random rand = new Random();
		newnum = (rand.nextInt(21)-10);
		while(newnum == num){
			newnum = (rand.nextInt(21)-10);	
		}
		return newnum;
	}
	public Chromosome[] createPopulation(int lchrom, int popsize, double pmutation){
		Chromosome[] offspring;
		offspring = new Chromosome[popsize];
		Chromosome[] doublePop;
		doublePop = new Chromosome[popsize*2];
		newChromosomes = new Chromosome[popsize];
		
		offspring = crossover(lchrom, popsize, pmutation);
		doublePop = mostFit(lchrom, popsize, offspring);
		
		for(int i = 0; i < popsize; i++){	
			newChromosomes[i] = doublePop[i];
			newChromosomes[i].fitness = doublePop[i].fitness;
		}
		for(int j = 0; j < popsize ; j++){
			sumFitness += newChromosomes[j].fitness;
		}
		genavg = sumFitness/popsize;
		
		//for(int j = 0; j < (popsize); j++){
		//	System.out.println("NewParent " + j + ": " + printArray(newChromosomes[j].allele) + "\tFitness: " + newChromosomes[j].fitness);
		//}
		System.out.println("GENERATION AVERAGE: " + genavg);
		//System.out.println("\n\n");
		
		
		return newChromosomes;
	}
	public static String printArray(int[] array) {
		String s = "";
		for(int j = 0; j < array.length; j++) {
			s  = s + array[j] + ", ";
		}
		return s;
	}
	
}
