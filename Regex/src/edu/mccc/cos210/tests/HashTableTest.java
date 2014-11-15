package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.HashTable;
import java.io.*;
import java.net.URL;

public class HashTableTest{
	public HashTableTest() {};
	public static void main(String[] sa)throws IOException{
		HashTableTest htt = new HashTableTest();
		htt.doit();

	}
	public void doit()throws IOException{
			URL resource = getClass().getResource("/img/all.txt");

			File file = new File(resource.getPath());
			double filesize = (double) file.length()/1024/1024;
			System.out.println("the file is this big: "+filesize);
	        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	        String line = null;
	        int count = 0;

			long startTime = System.currentTimeMillis( );
			
	        HashTable<String, String> ht = new HashTable();
	        while( (line = br.readLine())!= null ){
	                // \\s+ means any number of whitespaces between tokens
	            String [] tokens = line.split("\\s+");

	            for(int i = 0; i < tokens.length; i++){
	              ht.put(tokens[i], tokens[i]);
	              count++;
	            }
	        }
	        long endTime = System.currentTimeMillis( );
        
        	System.out.println( "Elapsed time: " + (endTime - startTime) );
	        System.out.println("number of words in the file: "+count);
	        System.out.println("number of keys: "+ht.getSize());
	        System.out.println("number of buckets: "+ht.getBuckeSize());
	        System.out.println("table length: "+ht.getTableLength());
	        System.out.println(ht.get("unpleasantly."));
		}
}