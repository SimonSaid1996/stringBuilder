// CS 0445 Spring 2018
// Assignment 2 Driver program to test MyStringBuilder class.  The output
// from your execution should exactly match that shown in the file
// A2Out.txt.  Look at the output and match it line by line with the
// println() statements in this program to see which output corresponds to
// which println().

// Some additional comments follow in individual code segments
import java.lang.*;

public class Assig2B
{
	public static void main(String [] args)
	{
		if(args.length != 1){
			System.out.println("invalid input");
		}
		else{	//valid input
			int N = Integer.parseInt( args[ 0 ] ) ;
			MyStringBuilder myBuilder = new MyStringBuilder();
			long startTime = System.nanoTime();
			for(int i = 0; i < N; i++){
				myBuilder.append('A');
			}
			long endTime = System.nanoTime();
			long usedTime = endTime - startTime;
			System.out.println("mystringbuilder appending used time is "+usedTime);

			startTime = endTime;
			for(int j = 0; j<N; j++){
				myBuilder.delete(0,1);

			}
			endTime = System.nanoTime();
			usedTime = endTime - startTime;
			System.out.println("mystringbuilder deleting used time is "+usedTime);
			

			startTime = endTime;
			for(int k = 0; k <N; k++){
				int midpoint = myBuilder.returnLength()/2; //might have issues
				myBuilder.insert(midpoint,'A');
				
			}
			endTime = System.nanoTime();
			usedTime = endTime - startTime;
			System.out.println("mystringbuilder inserting used time is "+usedTime+"\n");
			///////////////
			
			startTime = endTime;
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < N; i++){
				sb.append('A');
			}
			endTime = System.nanoTime();
			usedTime = endTime - startTime;
			System.out.println("stringbuilder appending used time is "+usedTime);
			
			startTime = endTime;
			for(int j = 0; j<N; j++){
				sb.delete(0,1);
			}
			endTime = System.nanoTime();
			usedTime = endTime - startTime;
			System.out.println("stringbuilder deleting used time is "+usedTime);
			
			startTime = endTime;
			for(int k = 0; k <N; k++){
				int midpoint = sb.length()/2; //might have issues
				
				sb.insert(midpoint,'A');
			}
			endTime = System.nanoTime();
			usedTime = endTime - startTime;
			System.out.println("stringbuilder inserting used time is "+usedTime+"\n");
			
			
			///////////////
			String s = "";
			startTime = endTime;
			startTime = System.nanoTime();
			for(int i = 0; i < N; i++){
				s = s+'A';
			}
			endTime = System.nanoTime();
			usedTime = endTime - startTime;
			System.out.println("string appending used time is "+usedTime);
			
			startTime = endTime;
			for(int j = 0; j<N; j++){
				s = s.substring(1,s.length());//might need to -1
			}
			endTime = System.nanoTime();
			usedTime = endTime - startTime;
			System.out.println("string deleting used time is "+usedTime);
			
			startTime = endTime;
			for(int k = 0; k <N; k++){
				int midpoint = s.length()/2; //might have issues, think about the case where 0 chars are in the string
				String sBefore= s.substring(0,midpoint);
				String Safter = null;
				if(s.length() ==0){
					Safter = s.substring(0,s.length() );  //need to fix the bugs here, sometimes print negative
				}
				else{
					if(midpoint == 0 && s.length() !=0){
						Safter = s.substring(0,s.length() );  //need to fix the bugs here, sometimes print negative
					}
					else{
						Safter = s.substring(midpoint,s.length() );  //need to fix the bugs here, sometimes print negative
					}
				}				
				s = new String (sBefore + 'A' + Safter) ;
			}
			endTime = System.nanoTime();
			usedTime = endTime - startTime;
			System.out.println("string inserting used time is "+usedTime);
			
			// endTime = System.nanoTime();
			// usedTime = (long)Math.abs(endTime - startTime);
			//System.out.println("string used time is "+usedTime+" the start time is "+startTime+" the end time is "+endTime);
			
			
			
			//iterate 3 individual times and use all the operations for all of the three classes
			
		}
	}
}