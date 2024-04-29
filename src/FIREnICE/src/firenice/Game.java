package firenice;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import enigma.console.TextAttributes;
import enigma.core.Enigma;
import fireAndice.ComRobot;
import fireAndice.Player;
import fireAndice.circularqueue;
import fireAndice.screen;

public class Game {

	TextAttributes redback = new TextAttributes(Color.white, Color.RED);
	TextAttributes blackback = new TextAttributes(Color.white, Color.black);

	public enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard",75, 25,25,10);

	Random rnd = new Random();
	screen screen = new screen();
	char[][] map;
	public KeyListener klis;
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)

	player player1;

	LinkedList Robots;

	public Game() throws InterruptedException {
		screen.readTxtFile();
		map = screen.getMap();

		// queue yi doldurma-----------------------
		circularqueue inputs = new circularqueue(10);
		for (int i = 0; i < 10; i++) {
			inputs.enqueue(screen.getinput());
		}
		// ------------------------------------------
		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);
		// --------------------------------------------------
		int threadSleep = 0;
		int time = 0;
		int px;
		int py;
		while (true) {
			px = rnd.nextInt(51) + 1;
			py = rnd.nextInt(21) + 1;
			if (map[py][px] == ' ') {
				break;
			}
		}

		player1 = new player(py, px, 0);
		cn.getTextWindow().output(px, py, 'P');
		for (int i = 0; i < inputs.size(); i++) {
			cn.getTextWindow().setCursorPosition(i + 56, 2);
			System.out.println(inputs.peek());
			inputs.enqueue(inputs.dequeue());
		}
		Robots=new LinkedList();
		// -------------------------------------------------
		while (true) {
			

			player1.displayAttributes();

			if (keypr == 1) {

				// if keyboard button pressed
				if (rkey == KeyEvent.VK_LEFT && map[py][px - 1] != '#') {
					player1.move(4, map);
					
				}

				if (rkey == KeyEvent.VK_RIGHT && map[py][px + 1] != '#') {
					player1.move(2, map);
					
				}

				if (rkey == KeyEvent.VK_UP && map[py - 1][px] != '#') {
					player1.move(1, map);
					
				}
				if (rkey == KeyEvent.VK_DOWN && map[py + 1][px] != '#') {
					player1.move(3, map);
					
				}
				char rckey = (char) rkey;

				//// left right up down
				// if (rckey == '%' || rckey == '\'' || rckey == '&' || rckey == '(')
				// cn.getTextWindow().output(px, py, 'P'); // VK kullanmadan test teknigi
				// else
				// cn.getTextWindow().output(rckey);

				keypr = 0; // last action
			}
			//////////////
			Thread.sleep(98);
			//////////
			// writing time -------------------------------
			threadSleep++;
			cn.getTextWindow().setCursorPosition(65, 6);
			System.out.println(time);
			if (threadSleep % 10 == 0) {
				time++;
			}

			// queue den alıp map e yazdırma -----------------------------------
			if (threadSleep % 20 == 0) {
				inputQueue(map, inputs);
			}
			
			if (threadSleep % 10 == 0) {

				moveComputer(map);

			}
			//////////////rewriting map
			reconstructmap();
			rewritemap();
			////////////
		}
	}

	public void inputQueue(char[][] map, circularqueue inputs) {
		int xinput;
		int yinput;
		while (true) {
			xinput = rnd.nextInt(51) + 1;
			yinput = rnd.nextInt(21) + 1;
			if (map[yinput][xinput] == ' ') {
				break;
			}
		}
		if ((char) inputs.peek() == 'C') {
			cn.setTextAttributes(redback);
			Robots.add(new Comrobot(yinput, xinput));
		}
		map[yinput][xinput] = (char) inputs.dequeue();
		cn.getTextWindow().setCursorPosition(xinput, yinput);
		System.out.println(map[yinput][xinput]);
		cn.setTextAttributes(blackback);
		inputs.enqueue(screen.getinput());
		for (int i = 0; i < inputs.size(); i++) {
			cn.getTextWindow().setCursorPosition(i + 56, 2);
			System.out.println(inputs.peek());
			inputs.enqueue(inputs.dequeue());
		}

	}

	public void reconstructmap() {
		if (map[player1.getPreposy()][player1.getPreposx()] == 'P') {
			map[player1.getPreposy()][player1.getPreposx()] = ' ';
		}
		map[player1.getPosy()][player1.getPosx()] = 'P';
		Node temp = Robots.getHead();
		
		while (temp != null) {
			try {
			if (map[temp.getData().getPreposy()][temp.getData().getPreposx()] == 'C') {
				map[temp.getData().getPreposy()][temp.getData().getPreposx()] = ' ';
			}
			map[temp.getData().getPosy()][temp.getData().getPosx()] = 'C';
			temp = temp.getLink();
			}
			catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	public void moveComputer(char[][] map) {

		Node temp=Robots.getHead();
		while(temp!=null) 
		{
			temp.getData().move2Treusere(map);
			temp=temp.getLink();
		}
		
	}

	public void rewritemap() {

		for (int i = 0; i < map.length; i++) {
			cn.getTextWindow().setCursorPosition(0, i);
			for (int j = 0; j < map[1].length; j++) {
				cn.getTextWindow().output(map[i][j]);
			}
		}
	}
}
