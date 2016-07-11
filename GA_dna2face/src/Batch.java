import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Batch {
	Patient[] patients;
	double[] coefficients;
	
	double fitness;
	
	public Batch(int snpsize, int pcsize, int batchsize, int pcnum, BufferedReader bufferedReadersnp, BufferedReader bufferedReaderpc) throws IOException{
		patients = new Patient[batchsize];
		coefficients = new double[snpsize+1];
		double sum = 0.0, normalizer = 0.0;
		
		for(int i = 0; i < batchsize; i++){
			patients[i] = new Patient(snpsize, pcsize, bufferedReadersnp, bufferedReaderpc);	
		}
		
		Random rand = new Random();
		
		for(int i = 0; i < (snpsize+1); i++){
			coefficients[i] = (rand.nextDouble()*100);
			sum += coefficients[i];
		}
		normalizer = sum/100;
		for(int i = 0; i < (snpsize+1); i++){
			coefficients[i] = coefficients[i]/normalizer;
		}
	}
}
