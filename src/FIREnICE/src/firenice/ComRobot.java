package firenice;

import enigma.core.Enigma;

public class ComRobot {

	enigma.console.Console cn = Enigma.getConsole("FireandIce", 75, 25, 25, 10);

	private int posX;
	private int posY;
	private int prevPosX;
	private int prevPosY;
	private int computerScore;
	private int lifePoints;
	private char symbol;
	private final char[][] map;

	public ComRobot(char[][] map, int posX, int posY, int computerScore) {
		this.map = map;
		this.posX = posX;
		this.posY = posY;
		this.prevPosX = posX;
		this.prevPosY = posY;
		this.lifePoints = 1000;
		this.symbol = 'C';
		this.computerScore = computerScore;
	}

	public void moveTowardsTreasure(int playerPosX, int playerPosY) {
		prevPosX = posX; // Önceki konumu kaydet
		prevPosY = posY;

		int deltaX = playerPosX - posX;
		int deltaY = playerPosY - posY;

		int moveX = Integer.compare(deltaX, 0);
		int moveY = Integer.compare(deltaY, 0);

		if (moveX != 0 || moveY != 0) {
			if (map[posY + moveY][posX] != '#') {
				posY += moveY;
			} else if (map[posY][posX + moveX] != '#') {
				posX += moveX;
			}
		}
		// Önceki konumu boş hücre yap
		map[prevPosY][prevPosX] = ' ';
	}

	public int getComputerCount(char[][] map) {

		int count = 0;

		for (int col = 0; col < map.length; col++) {
			for (int row = 0; row < map[col].length; row++) {
				if (map[col][row] == 'C') {
					count++;
				}
			}
		}
		return count;
	}

	public void reduceLifePoints(int amount) {
		lifePoints -= amount;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPrevPosX() {
		return prevPosX;
	}

	public void setPrevPosX(int prevPosX) {
		this.prevPosX = prevPosX;
	}

	public int getPrevPosY() {
		return prevPosY;
	}

	public void setPrevPosY(int prevPosY) {
		this.prevPosY = prevPosY;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public char getSymbol() {
		return symbol;
	}

	public int getComputerScore() {
		return computerScore;
	}

	public void setComputerScore(int computerScore) {
		this.computerScore = computerScore;
	}

	public void displayRobotAttributes() {

		cn.getTextWindow().setCursorPosition(68, 14);
		System.out.print(getComputerScore());

	}

	private int disereddirection() {// this method will return the desired direction in int format with respect to treasures on the map
		
	int distance;
	int deltax = 0;
	int deltay;
	//1=up;2=right;3=down;4=left;
	
	
		for (distance = 1; distance < 30; distance++) {
			deltax = 0;
			deltay = distance - deltax;
			for (int i = 0; i < distance; i++) {
				deltay--;
				deltax++;
				if ((posY + deltay>-1&&map.length>posY + deltay&&posX + deltax>-1&&map[1].length>posX + deltax)&&(map[posY + deltay][posX + deltax] == '1'||map[posY + deltay][posX + deltax] == '2'||map[posY + deltay][posX + deltax] == '3')) {
					if(deltay>=deltax)
					{return 1;}
					else 
					{
						return 2;	
					}
					
				}
			}
			
			for (int i = 0; i < distance; i++) {
				deltay--;
				deltax--;
				if ((posY + deltay>-1&&map.length>posY + deltay&&posX + deltax>-1&&map[1].length>posX + deltax)&&(map[posY + deltay][posX + deltax] == '1'||map[posY + deltay][posX + deltax] == '2'||map[posY + deltay][posX + deltax] == '3')) {
					if((-deltay)>=deltax)
					{return 3;}
					else 
					{
						return 2;	
					}
									
				}
			}
			
			for (int i = 0; i < distance; i++) {
				deltay++;
				deltax--;
				if ((posY + deltay>-1&&map.length>posY + deltay&&posX + deltax>-1&&map[1].length>posX + deltax)&&(map[posY + deltay][posX + deltax] == '1'||map[posY + deltay][posX + deltax] == '2'||map[posY + deltay][posX + deltax] == '3')) {
					if(deltay>=deltax)
					{return 4;}
					else 
					{
						return 3;	
					}
					
					// we got the nearest
				}
			}
			
			for (int i = 0; i < distance; i++) {
				deltay++;
				deltax++;
				if ((posY + deltay>-1&&map.length>posY + deltay&&posX + deltax>-1&&map[1].length>posX + deltax)&&(map[posY + deltay][posX + deltax] == '1'||map[posY + deltay][posX + deltax] == '2'||map[posY + deltay][posX + deltax] == '3')) {
					if(deltay>=(-deltax))
					{return 1;}
					else 
					{
						return 4;	
					}
					
					// we got the nearest
				}
			}
			
		}
		return 0;
	}

}
