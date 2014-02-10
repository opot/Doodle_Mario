package com.youtolife.mario;

import java.util.Random;

import org.newdawn.slick.SlickException;

public class MovingTrap extends Trap{

	float vel = 1;
	
	public MovingTrap(ImageContainer container, float x, float y)
			throws SlickException {
		super(container, x, y);
		img = container.getImage("trap/MovingTrap");
		vel = (new Random().nextInt(2)*2-1)*(new Random().nextFloat());
	}
	
	public void update(float delta){
		x+=delta/4*vel;
		if(x<-100||x>800)
			vel*=-1;
		super.update(delta);
	}
	
}
