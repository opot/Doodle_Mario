package com.youtolife.mario;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Cloud {

	Image img;
	float x,y;
	
	public Cloud(ImageContainer container,float x,float y) throws SlickException{
		img = container.getImage("cloud");
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g){
		img.draw(x, y);
	}
	
}
