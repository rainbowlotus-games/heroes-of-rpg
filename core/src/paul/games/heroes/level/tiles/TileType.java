package paul.games.heroes.level.tiles;

public enum TileType {
    WATER(0),
    GRASS(1),
    SAND(2),
    STONE(3),
    LAVA(4);

    private final int id;

    TileType(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
