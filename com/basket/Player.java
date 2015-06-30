package com.basket;

import java.awt.*;

public class Player {
    final Vector pos = new Vector(0, 0);
    final Vector v = new Vector(0, 0);
    int size;
    int m;
    boolean jump=false;
    private Screen scr;
    public Player(double x, double y, int size) {
        this.pos.x = x;
        this.pos.y = y;
        this.size = size;
        this.m=2;
    }

    public void paint(Graphics g) {
//        g.drawOval((int) pos.x, (int) pos.y, size, size);
        g.setColor(Color.MAGENTA);
        g.fillOval((int) pos.x, (int) pos.y, size, size);
    }
    public void reset(){
        this.pos.x=400;
        this.pos.y=400;
        this.v.y=0;
        this.v.x=0;
    }
    public  void start(String dir) {
        if (dir.equals("l")) {
            this.v.x =-4*80;
        }
        if (dir.equals("u")&&this.jump) {
            this.v.y=-5*80;
            this.jump=false;
        }
        if (dir.equals("r")) {
            this.v.x=4*80;//m to p
        }
    }
    public void move(int height, int width,double time, Vector g){
        this.v.addVector(this.v.multiply(-0.02/this.m*time));
        this.v.addVector(g.multiply(time));
        this.pos.addVector(this.v.multiply(time));
        this.pos.addVector(g.multiply(time * time/2));
        if (this.pos.y + this.size >= height) {
            this.v.y = 0;//*0.95;
            this.jump=true;
            this.pos.y = height - this.pos.y + height - this.size - this.size;
        }
        if(this.pos.x+this.size>=width){
            this.v.x = 0;//*0.95;
            this.pos.x = width - this.pos.x + width - this.size - this.size;
        }
        if(this.pos.x<=0){
            this.pos.x=-this.pos.x;
            this.v.x=0;
        }
        if(this.pos.x>=width/2 && this.pos.x<width/2+10 && this.pos.y+50/*b.size*/>= height / 2 + 35){
            this.v.x = 0;//*0.95;
            this.pos.x = width/2+10 + (this.pos.x- (width/2+10));
        }
        if(this.pos.y<=0){
            this.v.y = -this.v.y;//*0.95;
            this.pos.y =  - this.pos.y ;
        }
    }
    public void stop(){
        this.v.x=0;
    }

    public void setScr(Screen scr) {
        this.scr = scr;
    }
}
