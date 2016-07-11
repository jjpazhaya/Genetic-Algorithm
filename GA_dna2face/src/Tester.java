import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tester {
	
	public static void main(String[] args) throws IOException{
		//set up read file spec.txt
		String fileName = "C:\\Users\\Jan\\workspace\\GA_dna2face\\spec.txt";
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		//assigning read integers
		int snpsize = Integer.parseInt(bufferedReader.readLine());
		int pcsize = Integer.parseInt(bufferedReader.readLine());
		int batchsize = Integer.parseInt(bufferedReader.readLine());
		int collectionsize = Integer.parseInt(bufferedReader.readLine());
		int gensize = Integer.parseInt(bufferedReader.readLine());
		int pcnum = Integer.parseInt(bufferedReader.readLine());
		
		//set up read file snp.txt
		String fileNamesnp = "C:\\Users\\Jan\\workspace\\GA_dna2face\\snp.txt";
		FileReader fileReadersnp = new FileReader(fileNamesnp);
		BufferedReader bufferedReadersnp = new BufferedReader(fileReadersnp);

		//set up read file pc.txt
		String fileNamepc = "C:\\Users\\Jan\\workspace\\GA_dna2face\\pc.txt";
		FileReader fileReaderpc = new FileReader(fileNamepc);
		BufferedReader bufferedReaderpc = new BufferedReader(fileReaderpc);
		
		System.out.println(snpsize + "\n" + pcsize + "\n" + batchsize 
				+ "\n" + collectionsize + "\n" + gensize + "\n" + pcnum );
		
		Patient patient = new Patient(snpsize, pcsize, bufferedReadersnp, bufferedReaderpc);
		Batch batch = new Batch(snpsize, pcsize, batchsize, pcnum, bufferedReadersnp, bufferedReaderpc);
		
		for(int i = 0; i < 10; i++){
			System.out.println(printArray(batch.patients[i].snp));
		}
		for(int i = 0; i < 10; i++){
			System.out.println(printArray(batch.patients[i].pc));
		}
		for(int i = 0; i < (snpsize+1); i++){
			System.out.println(batch.coefficients[i]);
		}
	}
	public static String printArray(double[] array) {
		String s = "";
		for(int j = 0; j < array.length; j++) {
			s  = s + array[j] + ", ";
		}
		return s;
	}
}
