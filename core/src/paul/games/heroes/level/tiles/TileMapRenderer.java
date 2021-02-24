package paul.games.heroes.level.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import paul.games.heroes.managers.AssetManager;
import paul.games.heroes.math.MoreMath;

import java.util.Random;

public class TileMapRenderer {

    private final AssetManager assetManager;
    private SpriteBatch batch;

    public TileMapRenderer(AssetManager assetManager, SpriteBatch batch) {
        this.assetManager = assetManager;
        this.batch = batch;
    }

    public void render(TileMap tileMap) {
        int width = tileMap.getWidth();
        int height = tileMap.getHeight();
        float tileSize = tileMap.getTileSize();

        batch.begin();
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = tileMap.getTile(y, x);
                TileType tileType = tile.getType();

                if(tile.getType() != null) {
                    Sprite tileSprite = assetManager.getSprite(tileType.name());
                    batch.draw(
                            tileSprite,
                            x * tileSize,
                            y * tileSize,
                            width * tileSprite.getScaleX(),
                            width * tileSprite.getScaleY()
                    );
                }
            }
        }
        batch.end();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void dispose() {
        batch.dispose();
    }
}
