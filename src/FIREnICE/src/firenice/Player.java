package firenice;

import enigma.core.Enigma;

public class Player {
	
	enigma.console.Console cn = Enigma.getConsole("FireandIce",75, 25,25,10 );
	
	private int playerScore;
	private int PlayerLife;
	private int playerIce;
	private int playerposx;
	private int playerposy;
	
    Player(int playerScore,int playerLife,int playerIce,int playerposx,int playerposy) {
		
		this.playerScore = playerScore;
		this.PlayerLife = playerLife;
		this.playerIce = playerIce;
		this.playerposx = playerposx;
		this.playerposy = playerposy;
				
	}
	
	public int getPlayerposx() {
		return playerposx;
	}

	public void setPlayerposx(int playerposx) {
		this.playerposx = playerposx;
	}

	public int getPlayerposy() {
		return playerposy;
	}

	public void setPlayerposy(int playerposy) {
		this.playerposy = playerposy;
	}

	
	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public int getPlayerLife() {
		return PlayerLife;
	}

	public void setPlayerLife(int playerLife) {
		PlayerLife = playerLife;
	}

	public int getPlayerIce() {
		return playerIce;
	}

	public void setPlayerIce(int playerIce) {
		this.playerIce = playerIce;
	}
	public void displayAttributes() {
		
		cn.getTextWindow().setCursorPosition(68,9);
		System.out.print(getPlayerScore());
		cn.getTextWindow().setCursorPosition(68,10);
		System.out.print(getPlayerLife());
		cn.getTextWindow().setCursorPosition(68,11);
		System.out.print(getPlayerIce());
	}
	
	
}