package firenice;

import enigma.core.Enigma;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import enigma.console.TextAttributes;

public class Game {

    TextAttributes redback = new TextAttributes(Color.white, Color.RED);
    TextAttributes blackback = new TextAttributes(Color.white, Color.black);

    public static enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard");

    Random random = new Random();
    Screen screen = new Screen();
    Player player = new Player(0, 1000, 0, 0, 0);

    ComRobot[] robots;
    int robotCount = 0;

    public int keypr;
    public int rkey;

    public KeyListener klis;

    Game() throws Exception {
        screen.readTxtFile();

        circularQueue inputs = new circularQueue(10);

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

        int threadSleep = 0;
        int time = 0;
        int px;
        int py;
        while (true) {
            px = random.nextInt(51) + 1;
            py = random.nextInt(21) + 1;
            if (screen.getMap()[py][px] == ' ') {
                break;
            }
        }
        player.setPlayerposx(px);
        player.setPlayerposy(py);

        CalculateRobotNumber(); // robotCount değişkenini güncellemek için

        for (int i = 0; i < 10; i++) {
            inputs.enqueue(screen.getinput());
        }

        char prevMovement = ' '; // Önceki hareketin saklanacağı değişken
        while (true) {
            // Check if key is pressed
            if (keypr == 1) {
                // Handle key movement
                if (rkey == KeyEvent.VK_LEFT && screen.getMap()[py][px - 1] != '#') {
                    cn.getTextWindow().output(px, py, ' ');
                    cn.getTextWindow().output(px - 1, py, 'P');
                    px--;
                } else if (rkey == KeyEvent.VK_RIGHT && screen.getMap()[py][px + 1] != '#') {
                    cn.getTextWindow().output(px, py, ' ');
                    cn.getTextWindow().output(px + 1, py, 'P');
                    px++;
                } else if (rkey == KeyEvent.VK_UP && screen.getMap()[py - 1][px] != '#') {
                    cn.getTextWindow().output(px, py, ' ');
                    cn.getTextWindow().output(px, py - 1, 'P');
                    py--;
                } else if (rkey == KeyEvent.VK_DOWN && screen.getMap()[py + 1][px] != '#') {
                    cn.getTextWindow().output(px, py, ' ');
                    cn.getTextWindow().output(px, py + 1, 'P');
                    py++;
                }

                char rckey = (char) rkey;
                if (rckey == '%' || rckey == '\'' || rckey == '&' || rckey == '(')
                    cn.getTextWindow().output(px, py, 'P');
                else
                    cn.getTextWindow().output(rckey);

                keypr = 0;
            }

            threadSleep++;
            cn.getTextWindow().setCursorPosition(65, 6);
            System.out.println(time);
            if (threadSleep % 20 == 0) {
                time++;
            }

            if (threadSleep % 40 == 0) {
                int xinput;
                int yinput;
                while (true) {
                    xinput = random.nextInt(51) + 1;
                    yinput = random.nextInt(21) + 1;
                    if (screen.getMap()[yinput][xinput] == ' ') {
                        break;
                    }
                }

                screen.getMap()[yinput][xinput] = (char) inputs.dequeue();
                cn.getTextWindow().setCursorPosition(xinput, yinput);
                if ((char) inputs.peek() == 'C') {
                    cn.setTextAttributes(redback);

                } else {
                    cn.setTextAttributes(blackback);
                }
                System.out.println(screen.getMap()[yinput][xinput]);
                cn.setTextAttributes(blackback);
                inputs.enqueue(screen.getinput());
                for (int i = 0; i < inputs.size(); i++) {
                    cn.getTextWindow().setCursorPosition(i + 56, 2);
                    System.out.println(inputs.peek());
                    inputs.enqueue(inputs.dequeue());
                }

                CalculateRobotNumber(); // Robot sayısını güncellemek için
            }

            if (threadSleep % 5 == 0) { // Her 5 adımda bir hareket etsin
                if (robots != null) {
                    for (int i = 0; i < robots.length; i++) {
                        ComRobot robot = robots[i];
                        if (robot != null) {
                            moveCs(screen.getMap(), threadSleep); // Robotları rastgele hareket ettir
                        }
                    }
                }
            }

            if (robots != null) {
                for (int i = 0; i < robots.length; i++) {
                    ComRobot robot = robots[i];
                    if (robot != null) {
                        cn.getTextWindow().output(robot.getPosX(), robot.getPosY(), robot.getSymbol());
                    } else {
                        System.out.println("Robot at index " + i + " is null");
                    }
                }
            } else {
                System.out.println("Robots array is null");
            }

            // Player özelliklerini ekrana yazdır
            player.displayAttributes();

            // Sadece C hareket ettiğinde haritanın temizlenip tekrar yazdırılması
            if (screen.getMap()[py][px] == 'C' && prevMovement != 'C') {
                clearScreen();
                printMap(screen.getMap());
            }

            prevMovement = screen.getMap()[py][px]; // Önceki hareketi güncelle

            // Thread.sleep() kullanarak döngü adımlarını kontrol etme
            if (screen.getMap()[py][px] != 'C') {
                try {
                    Thread.sleep(20); // P'nin hareketi 20 milisaniye bekletme
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(100); // C'nin hareketi 100 milisaniye bekletme
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void CalculateRobotNumber() {

        int totalScore = 0;
        robotCount = 0;

        for (int y = 0; y < screen.getMap().length; y++) {
            for (int x = 0; x < screen.getMap()[y].length; x++) {
                if (screen.getMap()[y][x] == 'C') {
                    robotCount++;
                    // Her yeni robot oluşturulduğunda toplam skoru güncelle
                    ComRobot newRobot = new ComRobot(screen.getMap(), x, y, 0);
                    totalScore += newRobot.getComputerScore();
                }
            }
        }

        robots = new ComRobot[robotCount];
        int index = 0;
        for (int y = 0; y < screen.getMap().length; y++) {
            for (int x = 0; x < screen.getMap()[y].length; x++) {
                if (screen.getMap()[y][x] == 'C') {
                    robots[index] = new ComRobot(screen.getMap(), x, y, 0);
                    index++;
                }
            }
        }

        displayRobotAttributes(robotCount, totalScore); // Robot sayısını ve toplam skoru güncellemek için
    }

    public void displayRobotAttributes(int totalRobots, int totalScore) {
        cn.getTextWindow().setCursorPosition(68, 14);
        System.out.print(totalScore);
        cn.getTextWindow().setCursorPosition(68, 15);
        System.out.print(totalRobots);
    }

    public static void moveCs(char[][] map, int threadSleep) {
        if (threadSleep % 100 == 0) { // Her 100 adımda bir C'lerin hareketini yap (Player'ın 5 katı)
            Random random = new Random();

            for (int vertical = 0; vertical < map.length; vertical++) {
                for (int horizontal = 0; horizontal < map[0].length; horizontal++) {

                    if (map[vertical][horizontal] == 'C') {
                        int direction = random.nextInt(4);

                        switch (direction) {
                            case 0: // Sağa hareket
                                if (horizontal < map[0].length - 1 && map[vertical][horizontal + 1] == ' ') {
                                    map[vertical][horizontal] = ' ';
                                    map[vertical][horizontal + 1] = 'C';
                                }
                                break;

                            case 1: // Sola hareket
                                if (horizontal > 0 && map[vertical][horizontal - 1] == ' ') {
                                    map[vertical][horizontal] = ' ';
                                    map[vertical][horizontal - 1] = 'C';
                                }
                                break;

                            case 2: // Yukarı hareket
                                if (vertical > 0 && map[vertical - 1][horizontal] == ' ') {
                                    map[vertical][horizontal] = ' ';
                                    map[vertical - 1][horizontal] = 'C';
                                }
                                break;

                            case 3: // Aşağı hareket
                                if (vertical < map.length - 1 && map[vertical + 1][horizontal] == ' ') {
                                    map[vertical][horizontal] = ' ';
                                    map[vertical + 1][horizontal] = 'C';
                                }
                                break;
                        }
                    }
                }
            }
            printMap(map);
        }
    }

    public static void printMap(char[][] map) {

        cn.getTextWindow().setCursorPosition(0, 0);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static void clearScreen() {
        // Ekranı temizleme
        cn.getTextWindow().setCursorPosition(0, 0);
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            new Game();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
