import java.util.Random;

public class Chromosome {
	
	int[] allele;
	double fitness;
	
	public Chromosome(int lchrom) {
		// chromosome = ...;
		allele = new int[lchrom];
		Random rand = new Random();
		
		for(int j = 0; j < lchrom; j++){
			allele[j] = (rand.nextInt(21) - 10);
		}
	}
	
	public double getFitness(Chromosome chromosome){
		double fitness = 0;
		double[] partfit;
		double sum = 0.0, normalizer = 0.0;
		partfit = new double[allele.length];
		double[] weight;
		weight = new double[allele.length];
		int x,y;
		double squared = 0, denominator = 0, numerator = 0;
		Random rand = new Random();
		for(int j = 0; j < (allele.length); j++){
			weight[j] = (rand.nextDouble()*100);
			sum += weight[j];
		}
		normalizer = sum/100;
		for(int j = 0; j < (allele.length); j++){
			weight[j] = weight[j]/normalizer;
		}
		for(int j = 0; j < allele.length  ; j++){
			//System.out.println(printArray(chromosome.allele));
			x = chromosome.allele[j%allele.length];
			y = chromosome.allele[(j+1)%allele.length];
			//System.out.println("weight " + j + ": " + weight[j]);
			//System.out.println("x: " + x + "\ty: " + y );
			squared = (x*x) + (y*y);
			//System.out.println("SQUARED: " + squared);
			numerator = (Math.sin(Math.sqrt(squared)) * Math.sin(Math.sqrt(squared))) - 0.5;
			//System.out.println("NUMERATOR: " + numerator);
			denominator = (1 + (0.001*squared)) * (1+(0.001*squared));
			//System.out.println("DENOMINATOR: " + denominator );
			partfit[j] = ((double)weight[j]/100)*(0.5 + (numerator/denominator)); //
			//System.out.println("PARTFIT" + j +": " + partfit[j] + "\n");
			fitness += partfit[j]; 
		}
		
		return fitness;
	}

	public static String printArray(int[] array) {
		String s = "";
		for(int j = 0; j < array.length; j++) {
			s  = s + array[j] + ", ";
		}
		return s;
	}
	
}
