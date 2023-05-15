/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pak;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 *
 * @author Niki1oo1 //https://github.com/Niki1oo1/Koch-snowflake
 */
public class Functions {
    public static void drawKoch(Graphics g, Point2D[] p, int n) {
        n--;
        int l = p.length;
        
        if(n<=0) {
            Color c = new Color(30, 60, 90);
            g.setColor(c);
            Functions.drawPoly(g, p);
            return;
        }
        
        Point2D q[] = new Point2D.Double[4*l];
        
        for(int i = 0; i < 4*l; i++) {
                q[i] = new Point2D.Double();
            }
        
        
        for(int i = 0; i < l; i++) {
                if(i != l-1) {
                    q[4*i] = Functions.retina(p[i], p[i+1], 0);
                    q[4*i + 1] = Functions.retina(p[i], p[i+1], 1);
                    q[4*i + 2] = Functions.retina(p[i], p[i+1], 2);
                    q[4*i + 3] = Functions.retina(p[i], p[i+1], 3);
                } else {
                    q[4*i] = Functions.retina(p[i], p[0], 0);
                    q[4*i + 1] = Functions.retina(p[i], p[0], 1);
                    q[4*i + 2] = Functions.retina(p[i], p[0], 2);
                    q[4*i + 3] = Functions.retina(p[i], p[0], 3);
                }
        }
        
        
        if(n%2==0) {
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.RED);
        }
        
        Functions.drawPoly(g, q);
        
        g.setColor(Color.BLACK);        
        Functions.drawKoch(g, q, n);
        
    }
    
        public static Point2D retina(Point2D p0, Point2D p1, int h){
            int w1 = (int)Math.round(p0.getX());
            int w2 = (int)Math.round(p0.getY());
            int r1 = (int)Math.round(p1.getX());
            int r2 = (int)Math.round(p1.getY());
            
            Point2D a1 = new Point2D.Double();
            Point2D a2 = new Point2D.Double();
            Point2D a3 = new Point2D.Double();
            
            if(w1 > r1) {
                    if (w2 > r2) {
                        a1.setLocation(Math.round(r1 + ((w1-r1)*2)/3), Math.round(r2 + ((w2-r2)*2)/3));
                        
                        a3.setLocation(Math.round(r1 + (w1-r1)/3), Math.round(r2 + (w2-r2)/3));
                    }
                    if (w2 < r2) {
                        a1.setLocation(Math.round(r1 + ((w1-r1)*2)/3), Math.round(w2 + (r2-w2)/3));
                        
                        a3.setLocation(Math.round(r1 + (w1-r1)/3), Math.round(w2 + ((r2-w2)*2)/3));
                    }
                    if (w2 == r2) {
                        a1.setLocation(Math.round(r1 + ((w1-r1)*2)/3), Math.round(w2));
                        
                        a3.setLocation(Math.round(r1 + (w1-r1)/3), Math.round(w2));
                    }
                }
            if(w1 <= r1) {
                    if (w2 > r2) {
                        a1.setLocation(Math.round(w1 + (r1-w1)/3), Math.round(r2 + ((w2 - r2)*2)/3));
                        
                        a3.setLocation(Math.round(w1 + ((r1-w1)*2)/3), Math.round(r2 + (w2-r2)/3));
                    }
                    if (w2 < r2) {
                        a1.setLocation(Math.round(w1 + (r1-w1)/3), Math.round(w2 + (r2-w2)/3));
                        
                        a3.setLocation(Math.round(w1 + ((r1-w1)*2)/3), Math.round(w2 + ((r2-w2)*2)/3));
                    }
                    if (w2==r2) {
                        
                        a1.setLocation(Math.round(w1 + (r1-w1)/3), Math.round(w2));
                        
                        a3.setLocation(Math.round(w1 + ((r1-w1)*2)/3), Math.round(w2));
                    }
                }
                
                a2 = Functions.trr((int)Math.round(a1.getX()), (int)Math.round(a1.getY()), (int)Math.round(a3.getX()), (int)Math.round(a3.getY()), true);
            
            if(h==0) {
                return p0;
            }
            if (h==1) {
                return a1;
            } 
            if (h==2) {
                return a2;
            } else {
                return a3;
            }
        }
    
    public static void drawPoly(Graphics g, Point2D[] p) {
        int arrx[] = new int[p.length];
        for(int pk = 0; pk < p.length; pk++) {
            arrx[pk] = (int)p[pk].getX();
        }
        
        int arry[] = new int[p.length];
        for(int pk = 0; pk < p.length; pk++) {
            arry[pk] = (int)p[pk].getY();
        }
        
        g.drawPolygon(arrx, arry, p.length);
    }
    
    public static Point2D trr(int x0, int y0, int x1, int y1, boolean d) {
        //Partial code from https://stackoverflow.com/questions/23239703/how-to-draw-an-equilateral-triangle-when-two-points-are-already-given
        if(x0==x1 && y0==y1) {
            return new Point2D.Double(x0, y0);
        }
        int dx = x1 - x0;
        int dy = y1 - y0;
        
        double l = Math.sqrt(dx*dx + dy*dy);
        double dirX = dx / l;
        double dirY = dy / l;
        double h = (Math.sqrt(3)/2)*l;
        double midX = x0 + dx/2;
        double midY = y0 + dy/2;
        double pDirX = -dirY;
        double pDirY = dirX;
        int rx;
        int ry;
        
        if (d)
        {
            rx = (int)Math.round((midX + h * pDirX));
            ry = (int)Math.round((midY + h * pDirY));
        }
        else
        {
            rx = (int)Math.round((midX - h * pDirX));
            ry = (int)Math.round((midY - h * pDirY));
        }
        return new Point2D.Double(rx, ry);
    }
}
