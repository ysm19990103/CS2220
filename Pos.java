import java.util.*;
public class Pos {

	public static void main(String[] args) throws Exception {
		//read source file
		java.io.File file=new java.io.File("/Users/yangyiming/Desktop/Assignment1-GeneFeature/vertebrates.txt");
		//create output files
		java.io.File posfile=new java.io.File("/Users/yangyiming/Desktop/pos.txt");
		java.io.PrintWriter posout=new java.io.PrintWriter(posfile);
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
			
			posout.println("> "+length+" +1_Index("+index+")");
			//in case that DNA string does not have enough down-stream flanking nucleotides
			if((s1.length()-index-3)<99){
				int diff=99-s1.length()+index+3;
				for (int j=0;j<diff;j++)s1=s1+'N';
			}
			//in case that DNA string does not have enough up-stream flanking nucleotides
			if(index<99){
				int diff=99-index;
				index=99;
				for(int i=0;i<diff;i++)s1='N'+s1;
			}
			posout.println(s1.substring(index-99,index+99+2+1));//201 letters in total, the char at index+winsize+2 is not included in this substring
	  
	}
		input.close();
		posout.close();
		
	}
}
