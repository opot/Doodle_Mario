package com.youtolife.mario;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class Mob {
	float x,y;
	public Image img;
	float vel = 1;
	Polygon rect = new Polygon();
	
	public Mob(ImageContainer container,float x,float y) throws SlickException{
		this.x = x;
		this.y = y;
		int num = new Random().nextInt(5);
		img = container.getImage("mob"+String.valueOf(num));
		vel = (new Random().nextInt(2)*2-1)*(new Random().nextFloat());
		rect.addPoint(0, 0);
	}
	
	public void draw(Graphics g){
		img.draw(x,y);
	}
	
	public void update(float delta){
		x+=delta/4*vel;
		y+=delta/4;
		if(x<-100||x>800)
			vel*=-1;
		rect = new Polygon();
		rect.addPoint(x, y);
		rect.addPoint(x+img.getWidth(), y);
		rect.addPoint(x+img.getWidth(), y+img.getHeight());
		rect.addPoint(x, y+img.getHeight());
	}
}
