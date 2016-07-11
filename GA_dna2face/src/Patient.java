import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Patient {
	double[] snp;
	double[] pc;
	
	public Patient(int snpsize, int pcsize, BufferedReader bufferedReadersnp, BufferedReader bufferedReaderpc) throws IOException{
		snp = new double[snpsize];
		pc = new double[pcsize];
		
		String linesnp = bufferedReadersnp.readLine();
		if(linesnp != null){
			Scanner scannersnp = new Scanner(linesnp);
			for(int i = 0; i < snpsize; i++){
				snp[i] = scannersnp.nextDouble();
			}
		}

		String linepc = bufferedReaderpc.readLine();
		if(linepc != null){
			Scanner scannerpc = new Scanner(linepc);
			for(int i = 0; i < pcsize; i++){
				pc[i] = scannerpc.nextDouble();
			}
		}
		
	}
}
