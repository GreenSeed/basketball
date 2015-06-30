package com.basket;

import java.awt.*;

public class Ball {
    final Vector pos = new Vector(0, 0);
    final Vector v = new Vector(0, 0);
    int size;
    int m;
    Screen scr;
//    Color ballColor = Color.YELLOW;

    public Ball(double x, double y, int size) {
        this.pos.x = x;
        this.pos.y = y;
        this.size = size;
        this.m=1;
    }

    public void paint(Graphics g) {
//        g.drawOval((int) pos.x, (int) pos.y, size, size);
        g.setColor(Color.CYAN);
        g.fillOval((int) pos.x, (int) pos.y, size, size);
    }
    public void reset(){
        if(this.pos.x<scr.getWidth()/2-10){
            this.pos.x = scr.p1.pos.x;
            this.pos.y = scr.getHeight()-scr.p1.size-this.size;
        }
        if(this.pos.x<scr.getWidth()/2+10){
            this.pos.x = scr.p2.pos.x;
            this.pos.y = scr.getHeight()-scr.p2.size-this.size;
        }
        this.v.y = 0;
        this.v.x = 0;
    }
}
