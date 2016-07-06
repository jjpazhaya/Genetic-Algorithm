import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tester {
	private static long startTime = System.currentTimeMillis();
	public static void main(String[] args) throws IOException{
		// set up file reading
		String filename = "C:\\Users\\Jan\\workspace\\GA_original\\in.txt";
		FileReader filereader = new FileReader(filename); 
		BufferedReader bufferedReader = new BufferedReader(filereader);
		
		// read and assign parameters for generations and chromosomes
		String line = bufferedReader.readLine();
		int popsize = Integer.parseInt(line); //string to int
		int gensize = Integer.parseInt(bufferedReader.readLine());
		int lchrom = Integer.parseInt(bufferedReader.readLine());
		double pmutation = Double.parseDouble(bufferedReader.readLine());
		
		Population pop = new Population(popsize, lchrom);
		Chromosome chrom = new Chromosome(lchrom);
		Generation gen = new Generation(popsize, gensize, lchrom);

		gen.createGeneration(popsize, gensize, lchrom, pmutation);
		
		long endTime = System.currentTimeMillis();
		System.out.println((endTime - startTime));
		/*for(int i=0; i<gensize; i++) {
			for(Chromosome c : gen.generation[i].chromosomes)
				System.out.println("ALLELE: " + printArray(c.allele) + "  FITNESS: " + c.fitness);
			System.out.println("--");
		}
		/*
		for(int i = 0; i < gensize; i++){
			System.out.println("GENERATION " + i);
			System.out.println("---------------------------------------------------------------------------");
			for(int j = 0; j < popsize; j++){
				System.out.println("- Population Member" + j + ": " + printArray(gen.generation[i].chromosomes[j].allele) + "\tFitness: " + gen.generation[i].chromosomes[j].fitness);
			}
		}
		*/
	}
	public static String printArray(int[] array) {
		String s = "";
		for(int j = 0; j < array.length; j++) {
			s  = s + array[j];
		}
		return s;
	}
}
