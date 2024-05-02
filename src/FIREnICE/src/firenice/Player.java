package firenice;

import enigma.core.Enigma;

public class player {
	enigma.console.Console cn = Enigma.getConsole("FireandIce",75, 25,25,10 );
	private int posx;
	private int posy;
	private int preposx;
	private int preposy;
	private int playerlife;
	private int playerice;
	private int playerscore;
	public player(int posy,int posx,int score) 
	{
		this.posx=posx;
		this.posy=posy;
		playerscore=score;
		playerlife=1000;
		playerice=0;
		preposx=posx;
		preposy=posy;
	}
	public void move(int direction,char[][] map)//1=up,2=right,3=down,4=left 
	{	preposx=posx;
		preposy=posy;
		switch (direction) {
		case 1:
			if(map[posy-1][posx]!='#'&&map[posy-1][posx]!='C')
			{
								
				posy--;
			}
			break;
		case 2:
			if(map[posy][posx+1]!='#'&&map[posy][posx+1]!='C')
			{
				posx++;
			}
			break;
		case 3:
			if(map[posy+1][posx]!='#'&&map[posy+1][posx]!='C')
			{
				posy++;
			}
			break;
		case 4:
			if(map[posy][posx-1]!='#'&&map[posy][posx-1]!='C')
			{
				
				posx--;
			}
			break;
		}
	}
	public int getPreposx() {
		return preposx;
	}
	public void setPreposx(int preposx) {
		this.preposx = preposx;
	}
	public int getPreposy() {
		return preposy;
	}
	public void setPreposy(int preposy) {
		this.preposy = preposy;
	}
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
	}
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
	}
	public int getPlayerlife() {
		return playerlife;
	}
	public void setPlayerlife(int playerlife) {
		this.playerlife = playerlife;
	}
	public int getPlayerice() {
		return playerice;
	}
	public void setPlayerice(int playerice) {
		this.playerice = playerice;
	}
	public int getPlayerscore() {
		return playerscore;
	}
	public void setPlayerscore(int playerscore) {
		this.playerscore = playerscore;
	}
	
	 public void displayAttributes() {
			
			cn.getTextWindow().setCursorPosition(68,9);
			System.out.print(playerscore);
			cn.getTextWindow().setCursorPosition(68,10);
			System.out.print(playerlife);
			cn.getTextWindow().setCursorPosition(68,11);
			System.out.print(playerice);
		}
	
}

