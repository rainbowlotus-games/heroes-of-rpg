package paul.games.heroes.level.tiles;

import paul.games.heroes.level.generator.noise.OpenSimplex2F;
import paul.games.heroes.level.generator.noise.OpenSimplex2S;

public class TileMap {
    private final int width;
    private final int height;
    private final float tileSize = 16.0f;

    Tile[][] tileGrid;
    double[][] noiseBuffer;

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;

        // noise stuff
        final double PERIOD = 16.0;
        final int OFF_X = width*2;
        final int OFF_Y = height*2;
        final double FREQ = 1.0 / PERIOD;

        OpenSimplex2S noise = new OpenSimplex2S(1234);
        OpenSimplex2S.GenerateContext2D noiseBulk = new OpenSimplex2S.GenerateContext2D(OpenSimplex2S.LatticeOrientation2D.Standard, FREQ, FREQ, 1.0);

        this.noiseBuffer = new double[height][width];
        noise.generate2(noiseBulk, noiseBuffer, OFF_X, OFF_Y);

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
                double value = this.noiseBuffer[y][x];

                // Clamp value to -1 <-> 1
                if (value < -1) value = -1;
                else if (value > 1) value = 1;

                // General landscape population
                if (value >= 0.8 && value <= 1.0)
                {
                    setTile(new Tile(TileType.STONE, y, x), y, x);
                }
                else if (value >= 0.0 && value <= 0.8)
                {
                    setTile(new Tile(TileType.GRASS, y, x), y, x);
                }
                else if (value >= -0.5 && value <= 0.0)
                {
                    setTile(new Tile(TileType.SAND, y, x), y, x);
                }
                else {
                    setTile(new Tile(TileType.WATER, y, x), y, x);
                }

                // Now put special tiles down
                if (value > 0.99 && value < 0.999 )
                {
                    setTile(new Tile(TileType.LAVA, y, x), y, x);
                }
            }
        }
    }
}