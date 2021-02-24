package paul.games.heroes.level.tiles;

public class Tile {
    private TileType type;
    private int x;
    private int y;

    Tile(TileType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}
