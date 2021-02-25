package paul.games.heroes.level.tiles;

import paul.games.heroes.level.generator.Climate;
import paul.games.heroes.level.generator.ClimateType;
import paul.games.heroes.level.generator.noise.NoiseMap;

public class TileMap {
    private final int width;
    private final int height;
    private final float tileSize = 16.0f;

    Tile[][] tileGrid;
    double[][] elevationBuffer;
    double[][] temperatureBuffer;
    double[][] precipitationBuffer;
    Climate climate;

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;

        NoiseMap elevationMap = new NoiseMap(16.0f, this.width, this.height);
        this.elevationBuffer = elevationMap.generate(100);

        NoiseMap temperatureMap = new NoiseMap(64.0f, this.width, this.height);
        this.temperatureBuffer = temperatureMap.generate(101);

        NoiseMap precipitationMap = new NoiseMap(16.0f, this.width, this.height);
        this.precipitationBuffer = precipitationMap.generate(102);

        this.climate = new Climate();

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

    private void populate()
    {
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                double elevation = this.elevationBuffer[y][x];
                double temperature = this.temperatureBuffer[y][x];
                double precipitation = this.precipitationBuffer[y][x];

                // ClimateType climateType = this.climate.determineClimateType(elevation, temperature, precipitation);
                // System.out.println(climateType);

                setTile(new Tile(null, y, x), y, x);
            }
        }
    }
}