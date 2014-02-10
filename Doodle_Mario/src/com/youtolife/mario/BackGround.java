package com.youtolife.mario;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BackGround {
	
	Image img;
	float y1 = -800;
	public BackGround(ImageContainer container) throws SlickException{
		img = container.getImage("background");
	}
	
	public void move(float y){
		y1+=y;
		
			if(y1>=0)
				y1-=800;
	}
	
	public void draw(Graphics g){
		img.draw(0,0);
	}
	
}
