package firenice;

import java.util.Random;

public class Comrobot {

	static Random rnd =new Random();
	private int posx;
	private int posy;// first index is always y
	private int preposx;
	private int preposy;// first index is always y
	private int comlife;
	
	static  int comscore; 
	public Comrobot(int posy, int posx) {
		this.posx = posx;
		this.posy = posy;
		comlife=1000;
		preposx=posx;
		preposy=posy;
		
	}

	public void move2Treusere(char[][] map) {
	preposx=posx;
	preposy=posy;
		switch (desireddir(map)) {
		case 0:
			switch (rnd.nextInt(4)) {
			case 0:
				if(map[posy-1][posx]!='#'&&map[posy-1][posx]!='P'&&map[posy-1][posx]!='C')
				{	
					
					posy--;
				}
				break;
			case 1:
				if(map[posy][posx+1]!='#'&&map[posy][posx+1]!='P'&&map[posy][posx+1]!='C')
				{
					posx++;
				}
				break;
			case 2:
				if(map[posy+1][posx]!='#'&&map[posy+1][posx]!='P'&&map[posy+1][posx]!='C')
				{
					posy++;
				}
				break;
			case 3:
				if(map[posy][posx-1]!='#'&&map[posy][posx-1]!='P'&&map[posy][posx-1]!='C')
				{
					posx--;
				}
				break;

			}
			break;
		case 1:
			if(map[posy-1][posx]!='#'&&map[posy-1][posx]!='P'&&map[posy-1][posx]!='C')
			{
				posy--;
			}
			break;
		case 2:
			if(map[posy][posx+1]!='#'&&map[posy][posx+1]!='P'&&map[posy][posx+1]!='C')
			{
				posx++;
			}
			break;
		case 3:
			if(map[posy+1][posx]!='#'&&map[posy+1][posx]!='P'&&map[posy+1][posx]!='C')
			{
				posy++;
			}
			break;
		case 4:
			if(map[posy][posx-1]!='#'&&map[posy][posx-1]!='P'&&map[posy][posx-1]!='C')
			{
				posx--;
			}
			break;

		}
		
		if(map[posy][posx]=='1') 
		{
			comscore+=9;
		}
		else if(map[posy][posx]=='2') 
		{
			comscore+=30;
		}
		else if(map[posy][posx]=='3') 
		{
			comscore+=90;
		}
		
	}

	public int desireddir(char[][] map) {//1 = up,2=right,3=down,4=left 0=random
		int deltax;
		int deltay;
		for (int distance = 1; distance < 30; distance++) {
			deltax = 0;
			deltay = -distance;
			for (int i = 0; i < distance; i++) {
				
				if ((posy + deltay > 0 && posy + deltay < 22 && posx + deltax > 0
						&& posx + deltax < 52)
						&& (map[posy + deltay][posx + deltax] == '1' || map[posy + deltay][posx + deltax] == '2'
								|| map[posy + deltay][posx + deltax] == '3')) {
					if (-deltay > deltax) {
						if (map[posy - 1][posx] != '#') {
							return 1;
						} else if (map[posy][posx + 1] != '#') {
							return 2;
						} else {
							return 1;
						}
					} else {
						if (map[posy][posx + 1] != '#') {
							return 2;
						} else if (map[posy - 1][posx] != '#') {
							return 1;
						} else {
							return 2;

						}
					}
					

				}
				deltax++;
				deltay++;
			}
			for (int i = 0; i < distance; i++) {
				if ((posy + deltay > 0 && posy + deltay < 22 && posx + deltax > 0
						&& posx + deltax < 52)
						&& (map[posy + deltay][posx + deltax] == '1' || map[posy + deltay][posx + deltax] == '2'
								|| map[posy + deltay][posx + deltax] == '3')) {
					if (deltax > deltay) {
						if (map[posy][posx+1] != '#') {
							return 2;
						} else if (map[posy+1][posx] != '#') {
							return 3;
						} else {
							return 2;
						}
					} else {
						if (map[posy+1][posx] != '#') {
							return 3;
						} else if (map[posy][posx+1] != '#') {
							return 2;
						} else {
							return 3;

						}
					}
					

				}
				deltax--;
				deltay++;
			}
			for (int i = 0; i < distance; i++) {
				if ((posy + deltay > 0 && posy + deltay < 22 && posx + deltax > 0
						&& posx + deltax < 52)
						&& (map[posy + deltay][posx + deltax] == '1' || map[posy + deltay][posx + deltax] == '2'
								|| map[posy + deltay][posx + deltax] == '3')) {
					if (deltay > -deltax) {
						if (map[posy + 1][posx] != '#') {
							return 3;
						} else if (map[posy][posx - 1] != '#') {
							return 4;
						} else {
							return 3;
						}
					} else {
						if (map[posy][posx - 1] != '#') {
							return 4;
						} else if (map[posy + 1][posx] != '#') {
							return 3;
						} else {
							return 4;

						}
					}
					

				}
				deltax--;
				deltay--;
			}
			for (int i = 0; i < distance; i++) {
				if ((posy + deltay > 0 && posy + deltay < 22 && posx + deltax > 0
						&& posx + deltax < 52)
						&& (map[posy + deltay][posx + deltax] == '1' || map[posy + deltay][posx + deltax] == '2'
								|| map[posy + deltay][posx + deltax] == '3')) {
					if (-deltay > -deltax) {
						if (map[posy - 1][posx] != '#') {
							return 1;
						} else if (map[posy][posx - 1] != '#') {
							return 4;
						} else {
							return 1;
						}
					} else {
						if (map[posy][posx - 1] != '#') {
							return 4;
						} else if (map[posy - 1][posx] != '#') {
							return 1;
						} else {
							return 4;

						}
					}
					

				}
				deltax++;
				deltay--;
			}
		}
		return 0;
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

	public int getComlife() {
		return comlife;
	}

	public void setComlife(int comlife) {
		this.comlife = comlife;
	}
	
}
