
import java.util.*;

public class Neg {

	public static void main(String[] args) throws Exception {
		//read source file
		java.io.File file=new java.io.File("/Users/yangyiming/Desktop/Assignment1-GeneFeature/vertebrates.txt");
		//create output files
		java.io.File negfile=new java.io.File("/Users/yangyiming/Desktop/neg.txt");
		java.io.PrintWriter negout=new java.io.PrintWriter(negfile);
		Scanner input=new Scanner(file);
		//read the given file verterbrates.txt
		while(input.hasNext()){
			int length=input.nextInt();
			input.nextLine();
			String s1="";
			
			for(int i=1;i<=Math.ceil((float)length/80);i++){
				s1=s1+input.next();
				input.nextLine();
			}
		   
			String s2="";
			for(int j=1;j<=Math.ceil((float)length/80);j++){
				
				s2=s2+input.next();
				input.nextLine();
			}
			
			int index=0;
			for(int k=0;k<s2.length();k++){
				if (s2.charAt(k)=='i'){
					index=k;
					break;}
				
			}
			
			int count=0;
			for(int i=0;i<s1.length()-2;i++){
				if(s1.charAt(i)=='A'&&i!=index&&s1.charAt(i+1)=='T'&&s1.charAt(i+2)=='G'){
				    count++;
				    negout.println("> "+length+" +1_Index("+index+")	"+count);
				    String s1copy=new String(s1);
				    int ii=i;
				    //in case that DNA string does not have enough down-stream flanking nucleotides
				    if((s1copy.length()-ii)<(99+3)){
						int diff=99-s1copy.length()+ii+3;
						for (int j=0;j<diff;j++)s1copy=s1copy+'N';
					}
					//in case that DNA string does not have enough up-stream flanking nucleotides
					if(ii<99){
						int diff=99-ii;
						ii=99;
						for(int m=0;m<diff;m++)s1copy='N'+s1copy;
					}
					negout.println(s1copy.substring(ii-99,ii+99+3));
				
				}	
			}

	}
	input.close();
	negout.close();
	}
}


