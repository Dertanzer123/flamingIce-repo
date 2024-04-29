package firenice;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class screen {
	 TextAttributes green =new TextAttributes(Color.green);
	  TextAttributes black =new TextAttributes(Color.black);
	  TextAttributes white =new TextAttributes(Color.white);
	  TextAttributes whiteback=new TextAttributes(Color.white,Color.white);
	   TextAttributes blackback=new TextAttributes(Color.white,Color.black);
	   
	 enigma.console.Console cn = Enigma.getConsole("FireandIce",75, 25,25,10 );
	Random rn=new Random();
	private char[][] map;

	
	  public screen() {
		  map=new char[23][53];
		  
	}
	 
	public char getinput() {
		
		 int number=rn.nextInt(30);
		 char data=' ';
		 if (number<5) {
			data='1';
		}
		 else if (number<10) {
			 data='2';
		}
		 else if (number<15) {
			 data='3';
		}
		 else if (number<21) {
			 data='-';
		}
		 else if (number<27) {
			 data='@';
		}
		 else if (number<30) {
			 data='C';
		}
		 
		 
		 return data;
	}
	  
	
	
	public void readTxtFile() {
		  
		

	        try {
	            // Create a FileReader to read the file
	            FileReader fileReader = new FileReader("C:\\Users\\emin\\Desktop\\map1.txt");
	            BufferedReader bufferedReader = new BufferedReader(fileReader);

	            // Find out how many lines the file has
	            int lineCount = 0;
	            while (bufferedReader.readLine() != null) {
	                lineCount++;
	            }
	            bufferedReader.close();

	            // Create a 2D char array
	             map = new char[lineCount][];

	            // Reopen the file to read again
	            fileReader = new FileReader("C:\\Users\\emin\\Desktop\\map1.txt");
	            bufferedReader = new BufferedReader(fileReader);

	            // Read each line from the file and store it in the char array
	            String line;
	            int lineIndex = 0;
	            while ((line = bufferedReader.readLine()) != null) {
	                // Convert the line to a char array
	                char[] charLine = line.toCharArray();
	                // Add the char array to the 2D array
	                map[lineIndex] = charLine;
	                lineIndex++;
	            }
	            // Close the BufferedReader after reading
	            bufferedReader.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  
	        cn.setTextAttributes(green);
	        for (int i = 0; i < map.length; i++) {   
	            for (int j = 0; j < map[i].length; j++) {
	               
	                    System.out.print(map[i][j]);    
	            }
	            System.out.println();
	        }
	        cn.setTextAttributes(white);
	        cn.getTextWindow().setCursorPosition(56,0);
			  System.out.println("INPUT");	 
			  cn.setTextAttributes(green);
			  cn.getTextWindow().setCursorPosition(56,1);
			  System.out.println("<<<<<<<<<<");
			  cn.getTextWindow().setCursorPosition(56,3);
			  System.out.println("<<<<<<<<<<");
			  cn.setTextAttributes(blackback);
			  cn.setTextAttributes(white);
			  cn.getTextWindow().setCursorPosition(56,6);
			  System.out.println("Time    :");
			  cn.getTextWindow().setCursorPosition(56,9);
			  System.out.println("P.Score :");
			  cn.getTextWindow().setCursorPosition(56,10);
			  System.out.println("P.Life  :");
			  cn.getTextWindow().setCursorPosition(56,11);
			  System.out.println("P.Ice   :");
			  cn.getTextWindow().setCursorPosition(56,14);
			  System.out.println("C.Score :");
			  cn.getTextWindow().setCursorPosition(56,15);
			  System.out.println("C.Robots:");
			 
		  

		
		}



	public char[][] getMap() {
		return map;
	}

}
