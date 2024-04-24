package firenice;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Screen {
	  TextAttributes blue =new TextAttributes(Color.green);
	  TextAttributes white =new TextAttributes(Color.white);
	  enigma.console.Console cn = Enigma.getConsole("FireandIce",75, 25,25,10 );
	  Random rn=new Random();
	 
	private char[][] map;

		
	  public Screen() {
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
	        FileReader fileReader = new FileReader("C:\\Users\\emin\\Desktop\\map1.txt");
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        int lineCount = 0;
	        while (bufferedReader.readLine() != null) {
	            lineCount++;
	        }
	        bufferedReader.close();

	        map = new char[lineCount][];

	        fileReader = new FileReader("C:\\Users\\emin\\Desktop\\map1.txt");
	        bufferedReader = new BufferedReader(fileReader);

	        String line;
	        int lineIndex = 0;
	        while ((line = bufferedReader.readLine()) != null) {
	            char[] charLine = line.toCharArray();
	            map[lineIndex] = charLine;
	            lineIndex++;
	        }
	        bufferedReader.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    
	    cn.setTextAttributes(blue);
	    for (int i = 0; i < map.length; i++) {
	        for (int j = 0; j < map[i].length; j++) {
	            System.out.print(map[i][j]);
	        }
	        System.out.println();
	    }
	    cn.setTextAttributes(white);
	    cn.getTextWindow().setCursorPosition(56, 0);
	    System.out.println("INPUT");
	    cn.getTextWindow().setCursorPosition(56, 1);
	    System.out.println("<<<<<<<<<<");
	    cn.getTextWindow().setCursorPosition(56, 3);
	    System.out.println("<<<<<<<<<<");
	    cn.getTextWindow().setCursorPosition(56, 6);
	    System.out.println("Time    :");
	    cn.getTextWindow().setCursorPosition(56, 9);
	    System.out.println("P.Score :");
	    cn.getTextWindow().setCursorPosition(56, 10);
	    System.out.println("P.Life  :");
	    cn.getTextWindow().setCursorPosition(56, 11);
	    System.out.println("P.Ice   :");
	    cn.getTextWindow().setCursorPosition(56, 14);
	    System.out.println("C.Score :");
	    cn.getTextWindow().setCursorPosition(56, 15);
	    System.out.println("C.Robots:");

	}

	public char[][] getMap() {
		return map;
	}
	public void setMap(char[][]map) {
		this.map = map;
		
	}



	





	
	  
	  
	  
	  
	  
	  
	  
	  
	  

	  
  }
