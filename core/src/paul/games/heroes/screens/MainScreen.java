package paul.games.heroes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import paul.games.heroes.HeroesGame;
import paul.games.heroes.controller.InputController;

public class MainScreen implements Screen {

    private HeroesGame game;
    private InputController input;

    public MainScreen(HeroesGame game) {
        this.game = game;
        this.input = new InputController();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
