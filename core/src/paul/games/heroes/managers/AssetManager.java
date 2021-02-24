package paul.games.heroes.managers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class AssetManager {

    ArrayMap<String, Sprite> spritesMap;
    String[] spriteNames;
    TextureAtlas textureAtlas;

    public AssetManager(String atlasFile) {
        spritesMap = new ArrayMap<>();
        create(atlasFile);
    }

    private void create(String atlasFile) {
        textureAtlas = new TextureAtlas(atlasFile);

        addSprites();
    }

    private void addSprites() {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

        for(TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);
            spritesMap.put(region.name, sprite);
        }
    }

    public String[] getSpriteNames() {
        spriteNames = new String[textureAtlas.getRegions().size];

        for(int i = 0; i < spriteNames.length; i++) {
            spriteNames[i] = textureAtlas.getRegions().get(i).name;
        }

        return spriteNames;
    }

    public Sprite getSprite(String name) {
        return spritesMap.get(name);
    }
}
