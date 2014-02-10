package com.youtolife.mario;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class Trap {

	float x,y;
	public Image img;
	Polygon rect = new Polygon();
	
	public Trap(ImageContainer container,float x,float y) throws SlickException{
		this.x = x;
		this.y = y;
		img = container.getImage("trap/NormalTrap");
		rect.addPoint(0, 0);
	}
	
	public void draw(Graphics g){
		img.draw(x,y);
	}
	
	public void update(float delta){
		rect = new Polygon();
		rect.addPoint(x, y);
		rect.addPoint(x+img.getWidth(), y);
		rect.addPoint(x+img.getWidth(), y+img.getHeight());
		rect.addPoint(x, y+img.getHeight());
	}
	
}
