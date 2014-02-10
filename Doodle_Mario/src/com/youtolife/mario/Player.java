package com.youtolife.mario;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class Player {

	Sprite anim;
	float XVel = 0,YVel= -50,YAc = 4;
	Polygon rect = new Polygon();
	Image arrow;
	
	public Player(ImageContainer container) throws SlickException{
		Image buf = container.getImage("player");
		anim = new Sprite(100,590-buf.getHeight(),75,69,false,buf,0);
		anim.setPreferedDelta(70);
		arrow = container.getImage("arrow");
		rect.addPoint(0, 0);
	}
	
	public void update(float delta, Input input){
		anim.update(delta);
		
		XVel = 0;
		if(input.isKeyDown(Input.KEY_LEFT))
			XVel = -delta;
		if(input.isKeyDown(Input.KEY_RIGHT))
			XVel = delta;
			
		if(anim.getX()<-anim.getWidth())
			anim.setX(800);
		if(anim.getX()>800)
			anim.setX(0);
		
		anim.setX(anim.getX()+XVel);
		YVel+=YAc*(delta/50);
		anim.setY(anim.getY()+YVel*(delta/50));
		
		rect = new Polygon();
		rect.addPoint(anim.getX()+anim.getWidth()/4,anim.getY()+anim.getWidth()/2);
		rect.addPoint(anim.getX()+anim.getWidth()/4*3,anim.getY()+anim.getWidth()/2);
		rect.addPoint(anim.getX()+anim.getWidth()/4*3,anim.getY()+anim.getHeigth());
		rect.addPoint(anim.getX()+anim.getWidth()/4,anim.getY()+anim.getHeigth());
		
	}
	
	public void draw(Graphics g){
		if(anim.getY()>-anim.getHeigth())
			anim.draw(g);
		else
			arrow.draw(anim.getX(), 0);
	}
	
}
