package com.youtolife.mario;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {

	private int id = -1;
	Image back, play, exit,goodline;
	float PlayScale = 1;
	float ExitScale = 1;

	public MainMenuState(int id) {
		this.id = id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		back = ((Main) game).container.getImage("background");
		play = ((Main) game).container.getImage("play");
		exit = ((Main) game).container.getImage("exit");
		goodline = ((Main) game).container.getImage("goodline");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		back.draw(0, 0);

		play.draw(gc.getWidth() / 2 - play.getWidth() * PlayScale / 2, 50,
				PlayScale);
		exit.draw(gc.getWidth() / 2 - exit.getWidth() * ExitScale / 2, 350,
				ExitScale);
		goodline.draw(0,450,150,150);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();

		if (PlayScale > 1.2)
			PlayScale = 1.2f;
		if (ExitScale > 1.2)
			ExitScale = 1.2f;
		
		if (PlayScale < 1)
			PlayScale = 1f;
		if (ExitScale < 1)
			ExitScale = 1f;

		if (input.getMouseX() > gc.getWidth() / 2 - play.getWidth() / 2
				&& input.getMouseX() < gc.getWidth() / 2 + play.getWidth() / 2
				&& input.getMouseY() > 50
				&& input.getMouseY() < 50 + play.getHeight())
			PlayScale += 0.003;
		else
			PlayScale -= 0.003;
		if (input.getMouseX() > gc.getWidth() / 2 - exit.getWidth() / 2
				&& input.getMouseX() < gc.getWidth() / 2 + exit.getWidth() / 2
				&&input.getMouseY() > 350
				&& input.getMouseY() < 350 + exit.getHeight())
				ExitScale += 0.003;
		else
			ExitScale -= 0.003;
		
		if (input.getMouseX() > gc.getWidth() / 2 - play.getWidth() / 2
				&& input.getMouseX() < gc.getWidth() / 2 + play.getWidth() / 2
				&& input.getMouseY() > 50
				&& input.getMouseY() < 50 + play.getHeight()
				&&input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			game.enterState(Main.GAMEPLAYSTATE);
		
		if (input.getMouseX() > gc.getWidth() / 2 - exit.getWidth() / 2
				&& input.getMouseX() < gc.getWidth() / 2 + exit.getWidth() / 2
				&&input.getMouseY() > 350
				&& input.getMouseY() < 350 + exit.getHeight()
				&&input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				System.exit(0);

	}

	@Override
	public int getID() {
		return id;
	}

}
