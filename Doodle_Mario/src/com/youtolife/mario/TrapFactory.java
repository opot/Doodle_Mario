package com.youtolife.mario;

import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.SlickException;

public class TrapFactory implements Runnable {

	public boolean alive = true;
	Vector<Cloud> clouds;
	Vector<Trap> traps;
	Vector<Bonus> bonuses;
	Vector<Mob> mobs;
	ImageContainer container;
	Random rand = new Random();

	public TrapFactory(Vector<Trap> traps, Vector<Cloud> clouds, Vector<Bonus> bonuses,
			Vector<Mob> mobs,ImageContainer container) {
		this.traps = traps;
		this.clouds = clouds;
		this.container = container;
		this.bonuses = bonuses;
		this.mobs = mobs;
	}

	public void run() {
		while (alive) {
			try {
				Thread.sleep(rand.nextInt(700));
				synchronized (traps) {
					if (traps.size() < 10) {
						int random = rand.nextInt(10);
						if (random < 6)
							traps.add(new Trap(container, rand.nextInt(700),
									rand.nextInt(250) - 250));
						if(random >5&&random< 8)
							traps.add(new DestroyableTrap(container, rand.nextInt(700),
									rand.nextInt(250) - 250));
						if(random >=8)
							traps.add(new MovingTrap(container, rand.nextInt(700),
									rand.nextInt(250) - 250));
						if(random < 8){
							random = rand.nextInt(10);
							if(random>8){
								bonuses.add(new Bonus(container,traps.get(traps.size()-1).x,traps.get(traps.size()-1).y-26));
							}
						}
					}
				}
				synchronized (clouds) {
					if (rand.nextInt(10) > 7)
						clouds.add(new Cloud(container, rand.nextInt(700), rand
								.nextInt(250) - 250));
				}
				synchronized (mobs) {
					if (rand.nextInt(10) == 9)
						mobs.add(new Mob(container, rand.nextInt(700), rand
								.nextInt(250) - 250));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (SlickException e) {
				e.printStackTrace();
			}

		}
	}

}
