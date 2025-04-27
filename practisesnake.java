import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


class FDemo extends JFrame
{
	JPDemo jp;
	FDemo()
	{
		jp = new JPDemo();
		add(jp);
	}
}





class JPDemo extends JPanel implements ActionListener, KeyListener {
    ImageIcon img1, img2, img3;
    Image dot, head, food;
    int r1, r2;
    int x[] = new int[100];
    int y[] = new int[100];
    int dots = 5;
    JLabel l1;
    boolean start_game = false;
    boolean left = false, right = true, up = false, down = false;
    boolean game_over = false;  // Added a flag to check game-over state

    JPDemo() {
        x[0] = 120;
        y[0] = 100;
        x[1] = 100;
        y[1] = 100;
        x[2] = 80;
        y[2] = 100;
        x[3] = 60;
        y[3] = 100;
        x[4] = 40;
        y[4] = 100;
        setBackground(Color.black);
        img1 = new ImageIcon("dot.png");
        dot = img1.getImage();

        img2 = new ImageIcon("head.png");
        head = img2.getImage();

        img3 = new ImageIcon("food.png");
        food = img3.getImage();

        Timer t = new Timer(200, this);
        t.start();
        addKeyListener(this);
        setFocusable(true);

        randomDemo();
    }

    void randomDemo() {
        r1 = (int) Math.round(Math.random() * 25 + 1);
        r1 = r1 * 20;

        r2 = (int) Math.round(Math.random() * 25 + 1);
        r2 = r2 * 20;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (!game_over) {  // If the game is not over, draw the snake and food
            g.drawImage(food, r1, r2, this);
            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                } else {
                    g.drawImage(dot, x[i], y[i], this);
                }
            }
        } else {  // Display game over message
            Font f = new Font("", Font.BOLD, 30);
            g.setColor(Color.red);
            g.setFont(f);
            g.drawString("Game Over", 400, 300);  // Display Game Over at center
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (game_over) return;  // Stop action when game is over

        if (x[0] == r1 && y[0] == r2) {
            dots++;
            randomDemo();
        }

        if (start_game) {
            for (int i = dots; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
            if (right) {
                x[0] = x[0] + 20;
            }
            if (left) {
                x[0] = x[0] - 20;
            }
            if (up) {
                y[0] = y[0] - 20;
            }
            if (down) {
                y[0] = y[0] + 20;
            }

            checkCollision();  // Check for boundary collision
        }

        repaint();
    }

    void checkCollision() {
        // Check if the snake's head touches the boundary (left, right, top, bottom)
        if (x[0] < 0 || x[0] >= 1000 || y[0] < 0 || y[0] >= 1000) {
            game_over = true;  // Set game-over flag to true
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (game_over) return;  // Ignore key presses when the game is over

        start_game = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !right) {
            left = true;
            right = false;
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !left) {
            left = false;
            right = true;
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && !down) {
            left = false;
            right = false;
            up = true;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && !up) {
            left = false;
            right = false;
            up = false;
            down = true;
        }
    }

    public void keyTyped(KeyEvent e) {}
}

class snake {
    public static void main(String args[]) {
        FDemo f = new FDemo();
        f.setVisible(true);
        f.setSize(1000, 1000);
        f.setLocation(200, 50);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
}
