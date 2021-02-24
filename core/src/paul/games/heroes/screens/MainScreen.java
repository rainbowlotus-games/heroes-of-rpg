package paul.games.heroes.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import paul.games.heroes.HeroesGame;
import paul.games.heroes.components.PlayerComponent;
import paul.games.heroes.components.TextureComponent;
import paul.games.heroes.components.TransformComponent;
import paul.games.heroes.controllers.InputController;
import paul.games.heroes.viewport.PixelPerfectViewport;
import paul.games.heroes.systems.RenderingSystem;

public class MainScreen implements Screen {

    private HeroesGame game;
    private InputController input;
    private Engine engine;
    private SpriteBatch batch;
    private Viewport viewport;
    private OrthographicCamera cam;
    private RenderingSystem renderingSys;

    public MainScreen(HeroesGame game) {
        this.game = game;
        this.input = new InputController();
        this.batch = new SpriteBatch();
        // For now, viewport dimensions will be the same as the windows
        this.viewport = new PixelPerfectViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Setup engine and its systems
        setupEngine();
        // Create player and add it to the engine
        createPlayer();
    }

    private void setupEngine() {
        // Instantiate engine
        this.engine = new Engine();
        // Instantiate systems
        this.renderingSys = new RenderingSystem(this.batch, this.viewport);
        // Add systems
        this.engine.addSystem(renderingSys);
    }

    private void createPlayer() {
        Entity entity = engine.createEntity();
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        transformComponent.position.x = 16;
        transformComponent.position.y = 16;
        transformComponent.position.z = 0;
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.region = new TextureRegion(new Texture(Gdx.files.internal("player.png")));

        entity.add(new PlayerComponent());
        entity.add(transformComponent);
        entity.add(textureComponent);

        engine.addEntity(entity);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        renderingSys.getCamera().update();
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
