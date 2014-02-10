package com.youtolife.mario;

import org.newdawn.slick.SlickException;

public class DestroyableTrap extends Trap {

	public DestroyableTrap(ImageContainer container, float x, float y)
			throws SlickException {
		super(container, x, y);
		img = container.getImage("trap/DestroyableTrap");
	}

}
