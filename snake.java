/*  import javax.swing.*;
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

class JPDemo extends JPanel implements ActionListener,KeyListener
{ 
 ImageIcon img1,img2,img3;
 Image dot,head,food;
 int r1,r2; 
 int x[] = new int[100];
 int y[] = new int[100];
 int dots=5;
 JLabel l1;
 boolean start_game=false;
 boolean left=false,right=true,up=false,down=false;
 JPDemo()
 {
   x[0]=120;
   y[0]=100;
   x[1]=100;
   y[1]=100;
   x[2]=80;
   y[2]=100;
   x[3]=60;
   y[3]=100;
   x[4]=40;
   y[4]=100;
   setBackground(Color.black);
   img1 = new ImageIcon("dot.png");
   dot = img1.getImage();
   
   img2 = new ImageIcon("head.png");
   head = img2.getImage();
   
   img3 = new ImageIcon("food.png");
   food=img3.getImage();
   
   Timer t = new Timer(200,this);
   t.start();
    addKeyListener(this);
	setFocusable(true);
	
	randomDemo();
 }
  void randomDemo()
   {
	   r1 = (int)Math.round(Math.random()*25+1);
	   r1 = r1*20;
	   
	   r2 = (int)Math.round(Math.random()*25+1);
	   r2 = r2*20;
   }
 public void paintComponent(Graphics g)
 { 
	super.paintComponent(g);
	g.drawImage(food,r1,r2,this);
	for(int i=0;i<dots;i++)
	{
	 if(i==0)
	 {
    g.drawImage(head,x[i],y[i],this);
     }	
	 else
	 {
     g.drawImage(dot,x[i],y[i],this);		 
	 }
   
  
    if(x[0]==0||x[0]==1000||y[0]==0||y[0]==1000)
   {
	   Font f = new Font("",Font.BOLD,30);
	   l1=new JLabel("Game Over");
	   l1.setBounds(400,300,200,200);
	   l1.setForeground(Color.red);
	   l1.setFont(f);
	   add(l1);
     break ;
   }} 
 } 
public void actionPerformed(ActionEvent e)
{   

    if(x[0]==r1 && y[0]==r2)
	{
		
		dots++;
		randomDemo();
    }		
    if(start_game)
    {		
    for(int i = dots;i>0;i--)
	{
       x[i]=x[i-1];
	   y[i]=y[i-1];
    }	 
    if(right)
	{
     x[0]=x[0]+20;
	}
    if(left)
    {
    x[0]=x[0]-20;
    }

    if(up)
    {
    y[0]=y[0]-20;
	}
    if(down)
    {
    y[0]=y[0]+20;
	}
    	
	}
	repaint();
}	
 
 public void keyReleased(KeyEvent e){}
 public void keyPressed(KeyEvent e)
 {
	 start_game=true;
	 if(e.getKeyCode()==KeyEvent.VK_LEFT)
	 {
      left = true;
      right=false;
      up=false;
      down=false;
     }	  
	 if(e.getKeyCode()==KeyEvent.VK_RIGHT)
	 {
      left = false;
      right=true;
      up=false;
      down=false;
     }	  
	 if(e.getKeyCode()==KeyEvent.VK_UP)
	 {
      left = false;
      right=false;
      up=true;
      down=false;
     }	  
	 if(e.getKeyCode()==KeyEvent.VK_DOWN)
	 {
      left = false;
      right=false;
      up=false;
      down=true;
     }	  
	 
	 //if(x[0]==1000 || y[0]==1000 || x[0]==0 || y[0]==0){
	 // start_game=false;
	 //System.out.println("ram");
	 
	 
 }
public void keyTyped(KeyEvent e){}
} 

class snake
{
  public static void main(String args[])
  {
   FDemo f = new FDemo();
   f.setVisible(true);
   f.setSize(1000,1000);
   f.setLocation(200,50);
   f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
  }
}  
	
 */
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class FDemo extends JFrame {
    JPDemo jp;
    FDemo() {
        jp = new JPDemo();
        add(jp);
    }
}

class JPDemo extends JPanel implements ActionListener, KeyListener { 
    ImageIcon img1, img2, img3;
    Image dot, head, food;
    int r1, r2; 
    int[] x = new int[100];
    int[] y = new int[100];
    int dots = 5;
    boolean start_game = false;
    boolean left = false, right = true, up = false, down = false;
    boolean gameOver = false;
    Timer t;
    
    // Boundary settings
    final int BORDER_THICKNESS = 5;
    final Color BORDER_COLOR = Color.RED;
    final int GAME_WIDTH = 1000;
    final int GAME_HEIGHT = 1000;

    JPDemo() {
        // Initial snake position
        x[0] = 120; y[0] = 100;
        x[1] = 100; y[1] = 100;
        x[2] = 80; y[2] = 100;
        x[3] = 60; y[3] = 100;
        x[4] = 40; y[4] = 100;
        
        setBackground(Color.BLACK);
        
        // Load images (replace with your image paths)
        img1 = new ImageIcon("dot.png");
        dot = img1.getImage();
        img2 = new ImageIcon("head.png");
        head = img2.getImage();
        img3 = new ImageIcon("food.png");
        food = img3.getImage();
        
        t = new Timer(200, this);
        t.start();
        addKeyListener(this);
        setFocusable(true);
        randomDemo();
    }

    void randomDemo() {
        r1 = (int)(Math.random() * (GAME_WIDTH/20 - 2)) * 20 + 20;
        r2 = (int)(Math.random() * (GAME_HEIGHT/20 - 2)) * 20 + 20;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        // Draw red boundary
        g2d.setColor(BORDER_COLOR);
        g2d.setStroke(new BasicStroke(BORDER_THICKNESS));
        g2d.drawRect(BORDER_THICKNESS, BORDER_THICKNESS, 
                    GAME_WIDTH - 2*BORDER_THICKNESS, 
                    GAME_HEIGHT - 2*BORDER_THICKNESS);

        // Draw food
        g.drawImage(food, r1, r2, this);
        
        // Draw snake
        for(int i = 0; i < dots; i++) {
            if(i == 0) {
                g.drawImage(head, x[i], y[i], this);
            } else {
                g.drawImage(dot, x[i], y[i], this);
            }
        }
        
        // Game over display
        if(gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", 400, 500);
        }
    }

    public void actionPerformed(ActionEvent e) {   
        if(gameOver) {
            t.stop();
            return;
        }
        
        // Food collision
        if(x[0] == r1 && y[0] == r2) {
            dots++;
            randomDemo();
        }        
        
        // Move body
        for(int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        
        // Move head
        if(right) x[0] += 20;
        if(left) x[0] -= 20;
        if(up) y[0] -= 20;
        if(down) y[0] += 20;
        
        // Boundary collision
        if(x[0] <= BORDER_THICKNESS || x[0] >= GAME_WIDTH - BORDER_THICKNESS - 20 ||
           y[0] <= BORDER_THICKNESS || y[0] >= GAME_HEIGHT - BORDER_THICKNESS - 20) {
            gameOver = true;
        }
        
        // Self collision
        for(int i = 4; i < dots; i++) {
            if(x[0] == x[i] && y[0] == y[i]) {
                gameOver = true;
                break;
            }
        }
        
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if(gameOver) return;
        
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT && !right) {
            left = true;
            right = up = down = false;
        }
        else if(key == KeyEvent.VK_RIGHT && !left) {
            right = true;
            left = up = down = false;
        }
        else if(key == KeyEvent.VK_UP && !down) {
            up = true;
            left = right = down = false;
        }
        else if(key == KeyEvent.VK_DOWN && !up) {
            down = true;
            left = right = up = false;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}

class snake {
    public static void main(String[] args) {
        FDemo f = new FDemo();
        f.setVisible(true);
        f.setSize(1000, 3000);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}