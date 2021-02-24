package paul.games.heroes.level.tiles;

public class Tile {
    private TileType type;
    private int y;
    private int x;

    Tile(TileType type, int y, int x) {
        this.type = type;
        this.y = y;
        this.x = x;
    }

    TileType getType() {
        return this.type;
    }

    void setType(TileType type) {
        this.type = type;
    }

    int getY() { return this.y; }
    int getX() { return this.x; }
}
