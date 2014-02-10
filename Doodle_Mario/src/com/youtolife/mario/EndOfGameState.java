package com.youtolife.mario;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndOfGameState extends BasicGameState {

	private int id = -1;
	Image back,restart;
	float scale = 1;
	int dir = 1;
	Music die;
	
	public EndOfGameState(int id){
		this.id = id;
	}
	
	public void enter(GameContainer gc, StateBasedGame game)
			throws SlickException {
		die.play();
	}
	@Override
	public void init(GameContainer arg0, StateBasedGame game)
			throws SlickException {
		back = ((Main)game).container.getImage("end");
		restart = ((Main)game).container.getImage("restart");
		die = new Music("res/die.wav");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		back.draw();
		restart.draw(310,430,scale);
		g.drawString("Score = "+String.valueOf((int)((Main)game).Score), 210, 490);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
			game.enterState(Main.MAINMENUSTATE);
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE))
			game.enterState(Main.GAMEPLAYSTATE);
		
		scale+=0.004*dir;
		if(scale>1.1||scale<1)
			dir*=-1;
		
	}

	@Override
	public int getID() {
		return id;
	}

}
