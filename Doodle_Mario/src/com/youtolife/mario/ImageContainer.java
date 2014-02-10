package com.youtolife.mario;

import java.util.Vector;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class ImageContainer {

	Vector<Image> a = new Vector<Image>();
	Music muse;
	
	public ImageContainer() throws SlickException{
		Image buf;
		buf = new Image("res/fly.png");
		buf.setName("fly");
		a.add(buf);
		for(int i = 0;i<=4;i++){
			buf = new Image("res/monster/mob"+String.valueOf(i)+".png");
			buf.setName("mob"+String.valueOf(i));
			a.add(buf);
		}
		muse = new Music("res/sound.wav");
	}
	
	public Music getMusic(){
		return muse;
	}
	
	public Image getImage(String s) throws SlickException {
		if (a.size() > 0)
			for (int i = 0; i < a.size(); i++)
				if (a.get(i).getName().equals(s))
					return a.get(i).copy();
		Image b = new Image("res/" + s+".png");
		b.setName(s);
		a.add(b);
		return b.copy();
	}

}
