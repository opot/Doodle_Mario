package com.youtolife.mario;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GUI {

	Image full, low;
	GamePlayState game;

	public GUI(GamePlayState game, ImageContainer container)
			throws SlickException {
		full = container.getImage("fullfire");
		low = container.getImage("lowfire");
		this.game = game;
	}

	public void draw(Graphics g) {
		float percent = game.boost>0?(20000-game.boost)/20000:1;
		g.drawImage(low, 0, 0);
		g.drawImage(full.getSubImage(0, 0,full.getWidth(),
				(int)(percent * full.getHeight())), 0, 0);
		g.drawString("Score = "+String.valueOf((int)game.Score), 600, 5);
	}
}
