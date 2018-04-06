import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.security.MessageDigest;

class SGSum {
	
	int MessageDigest(int nodeId, int lenghtOfSynopsis) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String text = Integer.toString(nodeId);
			md.update(text.getBytes("UTF-8"));
			byte[] digest = md.digest();
			String temp = digest.toString();
			String binaryFormat = new BigInteger(temp.getBytes()).toString(2);
			int stringLength = binaryFormat.length();
			char lastBit = binaryFormat.charAt(stringLength-1);	
			int finalBit = Character.getNumericValue(lastBit);
			return finalBit;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	 int CT (int nodeId, int lenghtOfSynopsis) {
	 	int i = 0;
	 	while((i < lenghtOfSynopsis - 1) && (MessageDigest(nodeId, lenghtOfSynopsis) == 0)) {
	 		i = i+1;
	 	}
	 	return i;
	 }
	
	 int [] syno (int node,int sensedValue, int synopsisLength) {
			int i = 1;
			int [] localSynopsis = new int[synopsisLength];
				
			while (i <= sensedValue ) {
					
					int nodeId = Integer.valueOf(String.valueOf(node) + String.valueOf(i));
				int j = CT(nodeId, synopsisLength);
				localSynopsis[j] = 1;
					i=i+1;
			}
				for(int g =0; g < synopsisLength; g++) {
					System.out.print(" " +localSynopsis[g]);
				}	
			System.out.println();
			return localSynopsis;
		}
		
		public static void main(String args[]) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter number of nodes");
			int node = sc.nextInt();
			//System.out.println("Enter Sensed value");
			//int sensedValue = sc.nextInt();
			System.out.println("Enter Synopsis length");
			int synoposisLenght = sc.nextInt();
			
			SGSum synopsis = new SGSum();
			int [] finalSynopsis = new int [synoposisLenght];
			int i = 1;
			while (i <= node) {
				
				Random r = new Random();
				int min =10;
				int max =1000;
				int sensedValue=r.nextInt(max - min + 1) + min;
				System.out.println("Sensed value ="+sensedValue);
				
				int [] temp = new int[synoposisLenght];
				temp = synopsis.syno(i, sensedValue, synoposisLenght);
				i++;
				for(int l = 0; l < finalSynopsis.length; l++) {
					if(temp[l] != 0) {
						finalSynopsis[l] = 1;
					}
				}
			}
			System.out.print("Base Synopsis value: ");
			for (int m =0; m < finalSynopsis.length; m++) {
				System.out.print(finalSynopsis[m]);	
			}
		}
	}