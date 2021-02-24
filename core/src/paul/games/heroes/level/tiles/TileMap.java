package paul.games.heroes.level.tiles;

import paul.games.heroes.level.generator.noise.NoiseMap;
import paul.games.heroes.math.MoreMath;

import java.util.Random;

public class TileMap {
    private final int width;
    private final int height;
    private final float tileSize = 16.0f;

    Tile[][] tileGrid;
    double[][] elevationBuffer;
    double[][] temperatureBuffer;
    double[][] precipitationBuffer;

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;

        NoiseMap elevationMap = new NoiseMap(16.0f, this.width, this.height);
        this.elevationBuffer = elevationMap.generate(100);

        NoiseMap temperatureMap = new NoiseMap(64.0f, this.width, this.height);
        this.temperatureBuffer = temperatureMap.generate(101);

        NoiseMap precipitationMap = new NoiseMap(16.0f, this.width, this.height);
        this.precipitationBuffer = precipitationMap.generate(102);

        this.tileGrid = new Tile[height][width];
        populate();
    }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    public float getTileSize() { return this.tileSize; }

    public void setTile(Tile tile, int yPos, int xPos) {
        tileGrid[yPos][xPos] = tile;
    }
    public Tile getTile(int yPos, int xPos) {
        return tileGrid[yPos][xPos];
    }

    private void populate() {
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                double elevation = this.elevationBuffer[y][x];
                double temperature = this.temperatureBuffer[y][x];
                double precipitation = this.precipitationBuffer[y][x];

                // Clamp initial noise to -1 <-> 1
                elevation = MoreMath.clamp(elevation, -1, 1);
                temperature = MoreMath.clamp(temperature, -1, 1);
                precipitation = MoreMath.clamp(precipitation, -1, 1);
                // Convert noise to easier to understand units
                elevation = MoreMath.clamp(elevation*=8000, -400, 8000); // -400m <-> 8000m
                temperature = MoreMath.clamp(temperature*=90, -90, 40); // -90°C <-> 40°C
                precipitation = MoreMath.clamp(precipitation*=400, 0, 400); // 0cm <-> 400cm

                Tile tile = new Tile(null, y, x); // To be filled

                if(temperature <= 0 && temperature >= -90) {
                    Random r = new Random();
                    TileType type = r.nextInt(2) < 1 ? TileType.ICE : TileType.SNOW;
                    tile.setType(type);
                } else {
                    tile.setType(TileType.GRASS);
                }

                setTile(tile, y, x);
            }
        }
    }
}