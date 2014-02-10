package com.youtolife.mario;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame{

	public static final int GAMEPLAYSTATE = 1;
	public static final int MAINMENUSTATE = 0;
	public static final int ENDOFGAMESTATE = 2;
	public ImageContainer container;
	public int Score;
	
	public Main(String name) throws SlickException {
		super(name);
		this.addState(new GamePlayState(GAMEPLAYSTATE));
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new EndOfGameState(ENDOFGAMESTATE));
		this.enterState(MAINMENUSTATE);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main("Doodle Mario"));
		app.setDisplayMode(800, 600, false);
		app.setVSync(true);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		container = new ImageContainer();
	}

}
