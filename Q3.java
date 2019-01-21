
public class Q3
{
	public static void main(String[] args) throws Exception {
		/*NOTICE: 1.this program does not count the ATG in concern as upstream or downstream ATG
		 2.the feature vectors for pos are generated first, followed by the feature vectors for neg, because this's their sequence in the arff file
		 3.The feature vectors generated are for 3-gram*/
		//read file 
		java.io.File file=new java.io.File("/Users/yangyiming/Desktop/Assignment1-GeneFeature/pos.fasta");
		java.io.File file2=new java.io.File("/Users/yangyiming/Desktop/Assignment1-GeneFeature/neg.fasta");
		//create output files
		java.io.File q3=new java.io.File("/Users/yangyiming/Desktop/Q3.txt");
		java.io.PrintWriter output=new java.io.PrintWriter(q3);
		int index=99;//according to the way pos and neg are stored
		Scanner input=new Scanner(file);
		for(int i=0;i<2;i++){//record feature vectors for pos first, then for neg 
			if(i==1)input=new Scanner(file2);
			while(input.hasNext()){
			//skip the description line, if the description is needed, this line can be read and processed  
				input.nextLine();
			//create a dictionary for upstream 3-gram 
				Map<String, Integer> upstream = new HashMap<>();
			//create a dictionary for downstream 3-gram
				Map<String, Integer> downstream=new HashMap<>();
			//read the the dna string
				String s=input.nextLine();
			//upstream
				for(int k=index-3;k>=0;k=k-3){
					String key=s.substring(k,k+3);
				
					if(key.charAt(0)=='N')break;//no more in front
				
					if(upstream.containsKey(key))upstream.put(key, upstream.get(key)+1);
						else upstream.put(key,1);
			}
			//downstream
				for(int m=index+3;m<s.length()-2;m=m+3){
					String key=s.substring(m,m+3);
				    if(key.charAt(0)=='N')break;//no more afterwards
				    
					if(downstream.containsKey(key))downstream.put(key,downstream.get(key)+1);
						else downstream.put(key,1);
			}
			//output
				char[] four={'A','C','G','T'};
				for(int i1=0;i1<4;i1++){
					for(int i2=0;i2<4;i2++){
						for(int i3=0;i3<4;i3++){
							int result=0;
							String key=new StringBuilder().append(four[i1]).append(four[i2]).append(four[i3]).toString();
							if(upstream.containsKey(key))result=upstream.get(key);
							output.print(result+",");
						}
					}
				}	
				for(int j1=0;j1<4;j1++){
					for(int j2=0;j2<4;j2++){
						for(int j3=0;j3<4;j3++){
							int result=0;
							String key=new StringBuilder().append(four[j1]).append(four[j2]).append(four[j3]).toString();
							if(downstream.containsKey(key))result=downstream.get(key);
							output.print(result+",");
						}
					}
				}
			if(i==0)output.println("pos\n");
				else output.println("neg\n");
			
	}
		}
		
		input.close();
		output.close();
		}
	}