
public class Generation {
	Population[] generation;
	
	public Generation(int popsize, int gensize, int lchrom){
		generation = new Population[gensize];
		for(int i = 0; i < gensize; i++){
			generation[i] = new Population(popsize, lchrom);
		}
	}
	public void createGeneration(int popsize, int gensize, int lchrom, double pmutation){
		/*for(int i = 0; i < gensize; i++){
			System.out.println("GENERATION " + i);
			System.out.println("---------------------------------------------------------------------------");
			for(int j = 0; j < popsize; j++){
				System.out.println("- Population Member" + j + ": " + printArray(generation[i].chromosomes[j].allele) + "\tFitness: " + generation[i].chromosomes[j].fitness);
			}
		}*/
		for(int i = 1; i < gensize; i++){
			//System.out.println("GENERATION" + i + ": \n" + "---------------------");
			generation[i].chromosomes = generation[i-1].createPopulation(lchrom, popsize, pmutation);
			/*for(int j = 0; j < popsize; j++){
				System.out.println("BEFORE---" + printArray(generation[i].chromosomes[j].allele) + "||" + generation[i].chromosomes[j].fitness +"\t" + printArray(generation[i-1].newChromosomes[j].allele) + "||" + generation[i-1].chromosomes[j].fitness);
				generation[i].chromosomes[j] = generation[i-1].newChromosomes[j];
				System.out.println(printArray(this.generation[i].chromosomes[j].allele) + ": " + this.generation[i].chromosomes[j].fitness);
				/System.out.println("AFTER---" + printArray(generation[i].chromosomes[j].allele) + "||" + generation[i].chromosomes[j].fitness +"\t" + printArray(generation[i-1].newChromosomes[j].allele) + "||" + generation[i-1].chromosomes[j].fitness);
			}
			System.out.println("---");
			
			System.out.println("\n\n");
			for(int j = 0; j < popsize; j++){
				System.out.println("- Population Member" + j + ": " + printArray(generation[i].chromosomes[j].allele) + "\tFitness: " + generation[i].chromosomes[j].fitness);
			}*/
		}
		System.out.println("\n\n");
		/*for(int i = 0; i < gensize-1; i++){
			System.out.println("GENERATION " + i);
			System.out.println("---------------------------------------------------------------------------");
			for(int j = 0; j < popsize; j++){
				System.out.println("- Population Member" + j + ": " + printArray(generation[i].newChromosomes[j].allele) + "\tFitness: " + generation[i].newChromosomes[j].fitness);
			}
		}*/
		}
	
	public static String printArray(int[] array) {
		String s = "";
		for(int j = 0; j < array.length; j++) {
			s  = s + array[j] + ", ";
		}
		return s;
	}
}
