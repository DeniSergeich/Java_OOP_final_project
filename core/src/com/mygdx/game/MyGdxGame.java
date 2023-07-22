package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.consolGame.hero.Bandit;
import com.mygdx.game.consolGame.hero.BaseHero;
import com.mygdx.game.consolGame.hero.Crossbowman;
import com.mygdx.game.consolGame.hero.Magician;
import com.mygdx.game.consolGame.hero.Monk;
import com.mygdx.game.consolGame.hero.Peasant;
import com.mygdx.game.consolGame.hero.Sniper;
import com.mygdx.game.consolGame.hero.Spearman;
import com.mygdx.game.consolGame.main.Main;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background, crossBowMan, mage, monk, peasant, rouge, sniper, spearMan;

	Music music;
	Main game;
	boolean play, click;

	
	@Override
	public void create () {
		click = true;
		play = true;
		game = new Main();
		game.main();
		batch = new SpriteBatch();
		background = new Texture("backgrounds/" + Background.values()[new Random().nextInt(Background.values().length)]+".png");
		music = Gdx.audio.newMusic(Gdx.files.internal("music/paul-romero-rob-king-combat-theme-0"+ new Random().nextInt(0,5)+".mp3"));
		music.setLooping(true);
		music.setVolume(0.125f);
		music.play();

		crossBowMan = new Texture("characters/CrossBowMan.png");
		mage = new Texture("characters/Mage.png");
		monk = new Texture("characters/Monk.png");
		peasant = new Texture("characters/Peasant.png");
		rouge = new Texture("characters/Rouge.png");
		sniper = new Texture("characters/Sniper.png");
		spearMan = new Texture("characters/SpearMan.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		int kx = Gdx.graphics.getWidth()/12;
		int ky = Gdx.graphics.getHeight()/15;
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (BaseHero character: game.allTeam) {
			if (character.isDead()) continue;
			if (character instanceof Sniper) batch.draw(sniper, character.position.toArray()[0]*kx,character.position.toArray()[1]*ky);
			if (character instanceof Crossbowman) batch.draw(crossBowMan, character.position.toArray()[0]*kx,character.position.toArray()[1]*ky);
			if (character instanceof Monk) batch.draw(monk, character.position.toArray()[0]*kx,character.position.toArray()[1]*ky);
			if (character instanceof Magician) batch.draw(mage, character.position.toArray()[0]*kx,character.position.toArray()[1]*ky);
			if (character instanceof Bandit) batch.draw(rouge, character.position.toArray()[0]*kx,character.position.toArray()[1]*ky);
			if (character instanceof Spearman) batch.draw(spearMan, character.position.toArray()[0]*kx,character.position.toArray()[1]*ky);
			if (character instanceof Peasant) batch.draw(peasant, character.position.toArray()[0]*kx,character.position.toArray()[1]*ky);
		}
		batch.end();

		if (Gdx.input.isTouched() & play & click){
			if (!game.run()) {
				play = false;
				Gdx.graphics.setTitle("GAME OVER!");
				music.stop();
			}
			click = false;
		}
		if(!Gdx.input.isTouched()) click = true;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		music.dispose();
		crossBowMan.dispose();
		mage.dispose();
		monk.dispose();
		peasant.dispose();
		rouge.dispose();
		sniper.dispose();
		spearMan.dispose();
	}
}
