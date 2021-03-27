package bricksgame;

import java.awt.*;

public class map {
    public int ma[][];
    public int w,h;
    public map(int row,int col)
    {
        ma=new int[row][col];
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
            {
                ma[i][j]=1;
            }
        w=450/col;
            h=250/row;
    }
    public void draw(Graphics2D g)
    {
        for(int i=0;i<ma.length;i++)
            for(int j=0;j<ma[0].length;j++)
            {
                if(ma[i][j]==1)
                {
                    g.setColor(Color.MAGENTA);
                    g.fillRect((j*w)+10, (i*h)+10,w,h);

                    g.setStroke(new BasicStroke(15));
                    g.setColor(Color.black);
                    g.drawRect((j*w)+10, (i*h)+10,w,h);
                }
            }
    }
    public void setval(int val,int row,int col)
    {
        ma[row][col]=val;
    }


}
