package com.basket;

import javax.swing.*;
import java.awt.*;
public  class Screen extends JComponent{
    private Ball b;
    public Player p1;
    public Player p2;
    private TimerThread timer;

    public final double METER_TO_PIXEL = 800 / 10.0;
    public Vector g = new Vector(0, 9.8 * METER_TO_PIXEL);

    public Rectangle rect=new Rectangle(getWidth() / 2 - 10, getHeight() / 2 + 35, 20, getHeight() - 240);

    public double last_t;
    public double now_t;
    public double time;

    public int Keycode;


    public double leaks_v = 0.0005;

    public Screen() {
        b = new Ball(50, 80, 50);
        b.v.x = 8 * METER_TO_PIXEL;

        p1 = new Player(640 * 7 / 8, 480, 60);
        p2 = new Player(50,480,60);
//        b.v.y = 12 * METER_TO_PIXEL;
        timer = new TimerThread(this);
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        g.drawRect(0, 0, width - 1, height - 1);
        g.drawRect(rect.x,rect.y,rect.width,rect.height);
        g.drawString("Time: " + timer.now, 50, 200);
        g.drawString("y: " + b.pos.y, 50, 220);
        g.drawString("Keycode: " + Keycode, 50, 240);

//        g.drawString("v: " + v, 50, 240);
//        g.drawString("dy: " + (m*(gravityForce/m)*(height- b.pos.y) + m*v*v/2), 50, 260);

        b.paint(g);
        p1.paint(g);
        p2.paint(g);

//        g.drawOval(width/4,height/2,60,60);
//        g.drawLine(0,0,100,200);

    }


public synchronized void newTime() {
        int width = getWidth();
        int height = getHeight();
        if (width == 0) {

            last_t = timer.now;
            return;
        }


        now_t = timer.now;
        time = (now_t - last_t) / 1.0;
        last_t = now_t;

        for (int i = 0; i < 1; i++) {
           moveBall(height,width);
            p1.move(height, width, time, g);
            p2.move(height, width,time,g);
        }
        repaint();

}
    private void moveBall(int height,int width){
        b.v.addVector(b.v.multiply(-0.02/b.m*time));
        b.v.addVector(g.multiply(time));
        if (b.pos.y + b.size > height - 1 && b.v.length() * time < 0.3) {
            b.v.y = 0;
        }

        b.pos.addVector(b.v.multiply(time));
        b.pos.addVector(g.multiply(time*time/2));

        if (b.pos.y + b.size >= height) {
//            b.v.y = -b.v.y;//*0.95;
//            b.pos.y = height - b.pos.y + height - b.size - b.size;
            reset();

        }

        if(b.pos.y<=0){
            b.v.y = -b.v.y;//*0.95;
            b.pos.y =  - b.pos.y ;
        }
        if(b.pos.x+b.size>=width){
            b.v.x = -b.v.x;//*0.95;
            b.pos.x = width - b.pos.x + width - b.size - b.size;
        }
        if(b.pos.x<=0){
            b.v.x = -b.v.x;//*0.95;
            b.pos.x =  - b.pos.x ;

        }

        if(b.pos.x+b.size>=width/2-10 && b.pos.x + b.size<width/2 && b.pos.y+b.size >= height / 2 + 35){
            b.v.x = -b.v.x;//*0.95;
            b.pos.x = width/2-10 - b.pos.x + width/2-10 - b.size - b.size;
        }
        check_interball(width,height,p1);
        check_interball(width,height,p2);

    }
    public void check_interball(int width,int height,Player p){
        double S=Math.sqrt(Math.pow(b.pos.x-p.pos.x,2)+Math.pow(b.pos.y-p.pos.y,2));
        if(S<=(b.size+p.size)/2) {
            b.pos.x = ((p.size + b.size) / 2 - S) - Math.signum(b.pos.x)*b.pos.x;
            b.pos.y = ((p.size + b.size) / 2 - S) - Math.signum(b.pos.y)*b.pos.y;
            b.v.addVector(p.v.multiply(-0.4 * p.m/b.m));
//            p1.v.x=p1.v.y=0;
//            v1*m1+v2*m2=v3*m1
//            b.v.x=-b.v.x*0.8+p1.v.x;
//            b.v.y=-b.v.y*0.8+p1.v.x;
//            p1.v.x=0.8*p1.v.x;
//            p1.v.y=0.8*p1.v.y;
        }
//        i f(Math.pow(b.size/2,2)-Math.pow(width/2+10-b.pos.x,2)<0.01&&b.pos.y+b.size>height&&b.pos.y+b.size<=height / 2 + 35){
//                b.v.x = -b.v.x;//*0.95;
//                b.pos.x = width/2+10 + (b.pos.x- (width/2+10));
//            }
        if(b.pos.x>=width/2 && b.pos.x<width/2+10 && b.pos.y+b.size >= height / 2 + 35){
            b.v.x = -b.v.x;//*0.95;
            b.pos.x = width/2+10 + (b.pos.x- (width/2+10));
        }
    }
    public synchronized void reset() {
        p1.reset();
        p2.pos.x=50;
        p2.pos.y=500;
        p2.v.x=p2.v.y=0;
        b.v.y=b.v.x=0;

        if (b.pos.x<getWidth()/2-10){
            rect.x-=15;
            b.pos.x = p1.pos.x;
            b.pos.y = getHeight()-p1.size-b.size;
        }
        if (b.pos.x>getWidth()/2+10){
            rect.x-=15;
            b.pos.x = p2.pos.x;
            b.pos.y = getHeight()-p2.size-b.size;
        }
        last_t = 0;
        timer.reset();
    }
}
