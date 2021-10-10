package bricksgame;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play=false;
    private int rr=4;
            private int cc=4;
    private int score=0;
    private int totalbrivks;
    private Timer time;
    private int delay=8;
    private int playX=320;
    private int ballx=300;
    private int bally=400;
    private int balldirx=-2;
    private int balldiry=-2;
    private map  ma;
    public Gameplay() {
        ma=new map(rr,cc);
        totalbrivks=ma.ma.length*ma.ma[0].length;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        time=new Timer(delay,this);
        time.start();
    }
    public void paint(Graphics g)
    {   //background\

         g.setColor(Color.BLACK);
         g.fillRect(1,1,500,500);
         ma.draw((Graphics2D)g);
        //borders
         g.setColor(Color.yellow);
         g.fillRect(0,0,3,500);
         g.fillRect(0,0,500,3);
         g.fillRect(500,0,3,500);


           //scores

        g.setFont(new Font("sherif",Font.BOLD,25) );
        g.drawString("SCORE "+score,300,30);


          // all bcicks broken
        if(totalbrivks==0)
        {
            play=false;
            score=0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("sherif",Font.BOLD,30) );
            g.drawString("WIN ",100,300);
            g.setColor(Color.BLUE);
            g.setFont(new Font("sherif",Font.BOLD,20) );
            g.drawString("Press ENTER to play again ",100,340);


        }
         //paddle
         g.setColor(Color.green);
         g.fillRect(playX,450,80,8);

         //game over condition
        if(bally>470)
        {
            play=false;
            g.setColor(Color.RED);
            g.setFont(new Font("sherif",Font.BOLD,20) );
            g.drawString("GAME OVER",100,300);
            g.setColor(Color.BLUE);
            g.setFont(new Font("sherif",Font.BOLD,15) );
            g.drawString("Play again?(press ENTER)",100,340);

        }
        //ball
        g.setColor(Color.blue);
        g.fillOval(ballx,bally,20,20);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if(play)
        {


            for(int i=0;i<ma.ma.length;i++)
            {
               for(int j=0;j<ma.ma[0].length;j++)
               {
                   if(ma.ma[i][j]>0)
                   {
                       int xd=j*ma.w +10;
                       int yd=i*ma.h +10;
                       int w=ma.w;
                       int h=ma.h;
                       Rectangle rect=new Rectangle(xd,yd,w,h);
                       Rectangle brect=new Rectangle(ballx,bally,20,20);
                       Rectangle temp=rect;
                       if(brect.intersects(temp))
                       {
                           ma.setval(0,i,j);
                           totalbrivks--;
                           score++;
                           if(ballx+19<=temp.x || ballx+1>=temp.x+temp.width)
                               balldirx*=-1;
                           else
                               balldiry*=-1;


                       }
                   }
               }
            }

            if(new Rectangle(ballx,bally,20,20).intersects(new Rectangle(playX,450,90,10)))
                balldiry*=-1;
            ballx+=balldirx;
            bally+=balldiry;}

        if(ballx<0 || ballx>480)balldirx*=-1;
        if(bally<0 || bally>480)balldiry*=-1;
         repaint();
    }

    @Override  public void keyTyped(KeyEvent e) {}
    @Override  public void keyReleased(KeyEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {
          if(e.getKeyCode()== KeyEvent.VK_RIGHT)
         {
           if(playX>=400) playX=400;
           else moveright();
         }
        if(e.getKeyCode()== KeyEvent.VK_LEFT)
        {
            if(playX<=0) playX=0;
            else moveleft();
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(!play)
            {

                 playX=320;
                 ballx=300;
                bally=400;
                balldirx=-2;
                balldiry=-2;
                score=0;
                ma=new map(rr,cc);
                totalbrivks=ma.ma.length*ma.ma[0].length;
                repaint();
            }
        }
    }

    public void moveleft() {
        play=true;
        playX-=10;
    }

    public void moveright() {
        play=true;
        playX+=10;
    }


}
