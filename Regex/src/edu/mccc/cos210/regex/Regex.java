package edu.mccc.cos210.regex;
import java.io.*;
import edu.mccc.cos210.ds.DoublyLinkedList;

public class Regex{
	public static void main(String[] sa) throws Exception {
		new Regex("sally").doIt();
	}
	
	private void doIt() throws Exception {
		
	}

	public Regex(String regularExpression) throws IOException {

		BufferedReader br = stringToBR(regularExpression);
		int c;
		while ((c = br.read()) != -1) {
			// TODO process Regex depending on char to build graph
			System.out.println((char) c);
		}
		br.close();

		

	}

	// private helper methods:
	private static BufferedReader stringToBR(String str){
		InputStream is = new ByteArrayInputStream(str.getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return br;
	}


	private static String fileToString(String pathToFile) {
        String result = null;
        DataInputStream in = null;
        try {
            File f = new File(pathToFile);
            byte[] buffer = new byte[(int) f.length()];
            in = new DataInputStream(new FileInputStream(f));
            in.readFully(buffer);
            result = new String(buffer, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("IO problem in fileToString", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) { 
                    e.printStackTrace();
            }
        }
        return result;
    }
}

	