package com.pirates.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.ListIterator;

public class PyramidPyrates extends ApplicationAdapter implements InputProcessor{

	//Game state enum
	public enum GameState {
		MENU,
		GAME,
		GAMEOVER
	}
	public static GameState state;

	//drawing stuff
	private BitmapFont font;
	private SpriteBatch batch;
	public static float XSCALE, YSCALE;


	public static float SPEED = 10;
	public static float GRAVITY = -1;

	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;

	public static final int BLOCK_WIDTH_RAW = 108;
	public static int BLOCK_WIDTH;

	public static LinkedList<Sprite> allSprites;

	public static Pirate pirate;

	private static boolean touching;
	private static int touchx, touchy;

	private int backx = 0;

	private Menu menuAnimation;
	private GameOver gameOverAnimation;

	private static final String sampleEndText = "Score: 00000\nHigh Score: 00000";
	private static int sampleEndTextWidth;

	private static FileStorage fileStorage;

	public static Audio audio;

	//textures
	private Texture pyramid_background;

	private static int score = 0;
	private static int scoreOffset = 0;

	@Override
	public void create () {
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		BLOCK_WIDTH = SCREEN_HEIGHT / 10;

		XSCALE = SCREEN_WIDTH / 1920.0f;
		YSCALE = SCREEN_HEIGHT / 1080.0f;

		SPEED *= XSCALE;
		GRAVITY *= YSCALE;

		font = new BitmapFont();
		font.getData().setScale(5.0f * XSCALE, 5.0f * YSCALE);
		font.setColor(Color.BLACK);

		menuAnimation = new Menu();
		gameOverAnimation = new GameOver();
		state = GameState.MENU;
		allSprites = new LinkedList<Sprite>();
		batch = new SpriteBatch();
		pyramid_background = new Texture("images/background.png");
		pirate = new Pirate();
		allSprites.add(pirate);

		//calculations for centering end screen text
		GlyphLayout glyphLayout = new GlyphLayout(font, sampleEndText);
		sampleEndTextWidth = (int)glyphLayout.width;

		fileStorage = new FileStorage();

		audio = new Audio();

		Generation.initial();

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		//step through the game
		step();

		//draw everything
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if(state == GameState.MENU){
			batch.draw(menuAnimation.getTexture(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		}
		else if(state == GameState.GAME) {
			//draw background to fill the screen
			int drawx = backx;
			while (drawx < SCREEN_WIDTH) {
				batch.draw(pyramid_background, drawx, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				drawx += SCREEN_WIDTH;
			}

			for(Sprite s : allSprites) {
				batch.draw(s.getTexture(), s.getX(), s.getY(), s.getWidth(), s.getHeight()); //draw pirate with scaled sizes
			}

			font.setColor(Color.BLACK);
			font.draw(batch, Integer.toString(score), 10, SCREEN_HEIGHT - 10);
		}
		else if(state == GameState.GAMEOVER){
			batch.draw(gameOverAnimation.getTexture(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			String currScoreStr = "Score: " + score + "\nHigh Score: " + fileStorage.getHighScore();
			batch.draw(pirate.getTexture(), pirate.getX(), pirate.getY(), pirate.getWidth(), pirate.getHeight());

			font.setColor(Color.WHITE);
			font.draw(batch, currScoreStr, SCREEN_WIDTH/2-sampleEndTextWidth/2, SCREEN_HEIGHT*3/4);
		}

		batch.end();
	}

	@Override
	public void pause() {
		audio.stop();
	}

	private void step() {
		if(state == GameState.GAME) {
			if (pirate.getX() + pirate.getWidth() < 0 || pirate.toBeRemoved) {
				gameOver();
				return;
			}

			//jumping
			if(touching && touchx < SCREEN_WIDTH / 2){
				pirate.jump(touching);
			}else{
				pirate.jump(false);
			}

			Generation.step();

			//increment the score every 3 frames
			scoreOffset++;
			if(scoreOffset == 2){
				score++;
				scoreOffset = 0;
			}

			backx -= SPEED;
			if (backx <= -SCREEN_WIDTH) backx = 0;

			ListIterator<Sprite> listIterator = allSprites.listIterator();
			while (listIterator.hasNext()) {
				Sprite curr = listIterator.next();
				if (curr.getX() + curr.getWidth() < 0 || curr.toBeRemoved) {
					listIterator.remove();
					continue;
				}
				curr.move();
			}
		}
	}

	public void reset() {
		allSprites.clear();
		pirate = new Pirate();
		allSprites.add(pirate);

		Generation.initial();
		score = 0;
	}

	public static void gameOver(){
		state = GameState.GAMEOVER;
		pirate.die();

		if (score > fileStorage.getHighScore()) {
			fileStorage.setHighScore(score);
		}
	}

	public static void increaseScore(int n) {
		score += n;
	}

	///InputProcessor methods
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touching = true;
		touchx = screenX;
		touchy = screenY;

		if(state == GameState.MENU){
			state = GameState.GAME;
		}
		else if(state == GameState.GAME) {
			if (screenX > SCREEN_WIDTH / 2) {
				pirate.fire();
			}
		}
		else if(state == GameState.GAMEOVER) {
			if(screenY > 600 && pirate.animationStep >= 100) {
				if (screenX < SCREEN_WIDTH / 2) {
					state = GameState.GAME;
				} else if (screenX > SCREEN_WIDTH / 2) {
					state = GameState.MENU;
				}
				reset();
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touching = false;
		touchx = -1;
		touchy = -1;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		touching = true;
		touchx = screenX;
		touchy = screenY;
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
