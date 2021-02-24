package paul.games.heroes.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import paul.games.heroes.components.PlayerComponent;
import paul.games.heroes.components.TransformComponent;
import paul.games.heroes.controllers.InputController;

public class PlayerControlSystem extends IteratingSystem {

    ComponentMapper<PlayerComponent> pm;
    ComponentMapper<TransformComponent> tm;
    //InputController inputCon;

    public PlayerControlSystem(InputController input) {
        super(Family.all(PlayerComponent.class).get());
        //inputCon = input;
        pm = ComponentMapper.getFor(PlayerComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent tc = tm.get(entity);
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            tc.position.x -= 1f; // TODO: collision detection tomorrow :)
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            tc.position.x += 1f;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            tc.position.y += 1f;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            tc.position.y -= 1f;
        }
    }
}
