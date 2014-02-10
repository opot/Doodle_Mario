package com.youtolife.mario;

import java.util.Iterator;
import java.util.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState {

	private int id = -1;
	BackGround back;
	Player player;
	Vector<Trap> traps;
	Vector<Cloud> clouds;
	Vector<Bonus> bonuses;
	Vector<Mob> mobs;
	TrapFactory generator;
	GUI gui;
	float Score = 0;
	Sound jump;
	
	float boost = 0;
	
	public GamePlayState(int id){
		this.id = id;
	}

	public void enter(GameContainer gc, StateBasedGame game)
			throws SlickException {
		boost = 0;
		Score = 0;
		back = new BackGround(((Main) game).container);
		player = new Player(((Main) game).container);
		gui = new GUI(this,((Main) game).container);
		bonuses = new Vector<Bonus>();
		clouds = new Vector<Cloud>();
		mobs = new Vector<Mob>();
		clouds.add(new Cloud(((Main) game).container,100,50));
		traps = new Vector<Trap>();
		traps.add(new DestroyableTrap(((Main) game).container,0,590));
		traps.add(new DestroyableTrap(((Main) game).container,120,590));
		traps.add(new Trap(((Main) game).container,240,590));
		traps.add(new Trap(((Main) game).container,360,590));
		traps.add(new Trap(((Main) game).container,480,590));
		traps.add(new DestroyableTrap(((Main) game).container,600,590));
		traps.add(new DestroyableTrap(((Main) game).container,720,590));
		traps.add(new MovingTrap(((Main) game).container,280,390));
		traps.add(new MovingTrap(((Main) game).container,480,190));
		generator = new TrapFactory(traps,clouds,bonuses,mobs ,((Main) game).container);
		new Thread(generator).start();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		jump = new Sound("res/jump.wav");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		back.draw(g);
		synchronized (clouds) {
			Iterator<Cloud> a = clouds.iterator();
			while (a.hasNext()) {
				Cloud b = a.next();
				b.draw(g);
			}
		}
		
		player.draw(g);

		synchronized (traps) {
			Iterator<Trap> a = traps.iterator();
			while (a.hasNext()) {
				Trap b = a.next();
				b.draw(g);
			}
		}
		synchronized (bonuses) {
			Iterator<Bonus> a = bonuses.iterator();
			while (a.hasNext()) {
				Bonus b = a.next();
				b.draw(g);
			}
		}
		synchronized (mobs) {
			Iterator<Mob> a = mobs.iterator();
			while (a.hasNext()) {
				Mob b = a.next();
				b.draw(g);
			}
		}
		gui.draw(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();

		if(boost>0)
			boost-=delta;
		if(input.isKeyPressed(Input.KEY_SPACE)&&boost<=0){
			boost = 20000;
			player.YVel = -100;
		}
		
		player.update(delta, gc.getInput());
		
		synchronized (mobs) {
			Iterator<Mob> a = mobs.iterator();
			while (a.hasNext()) {
				Mob b = a.next();
				b.update(delta);
				if(b.rect.intersects(player.rect))
					Score-=Score/32;
				if(b.y>600)
					a.remove();
			}
		}
		
		synchronized (traps) {
			Iterator<Trap> a = traps.iterator();
			while (a.hasNext()) {
				Trap b = a.next();
				b.update(delta);
				if (player.rect.intersects(b.rect) && player.YVel > 0){
					player.YVel = -60;
					jump.play();
					synchronized (bonuses) {
						Iterator<Bonus> c = bonuses.iterator();
						while (c.hasNext()) {
							Bonus d = c.next();
							d.update(delta);
							if(d.y>600)
								c.remove();
							if(d.rect.intersects(player.rect)){
								d.use(player);
								c.remove();
							}
							
						}
					}
					if(b instanceof DestroyableTrap){
						a.remove();
						Score+=100;
					}
				}
				if(b.y>600)
					a.remove();
			}
			if(!((Main) game).container.getMusic().playing())
				((Main) game).container.getMusic().play();
		}

		if (player.anim.getY() < 50) {
			
			float move = -player.YVel>60?120*(float)(delta/100f):60*(float)(delta/100f);
			Score+=move;
			
			player.anim.setY(player.anim.getY()+move);
			synchronized (clouds) {
				Iterator<Cloud> a = clouds.iterator();
				while (a.hasNext()) {
					Cloud b = a.next();
					b.y+=move/2;
					if(b.y>600)
						a.remove();
				}
			}
			synchronized (bonuses) {
				Iterator<Bonus> a = bonuses.iterator();
				while (a.hasNext()) {
					Bonus b = a.next();
					b.y+=move;
				}
			}
			synchronized (mobs) {
				Iterator<Mob> a = mobs.iterator();
				while (a.hasNext()) {
					Mob b = a.next();
					b.y+=move;
				}
			}
			synchronized (traps) {
				Iterator<Trap> a = traps.iterator();
				while (a.hasNext()) {
					Trap b = a.next();
					b.y+=move;
				}
			}
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			generator.alive = false;
			game.enterState(Main.MAINMENUSTATE);
		}
		if(player.anim.getY() > gc.getHeight()){
			generator.alive = false;
			((Main)game).Score = (int)Score;
			game.enterState(Main.ENDOFGAMESTATE);
		}
			
	}

	@Override
	public int getID() {
		return id;
	}

}
