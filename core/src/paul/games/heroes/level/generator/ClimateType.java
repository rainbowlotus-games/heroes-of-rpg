package paul.games.heroes.level.generator;

//TODO: Replace "66" with actual values.

public enum ClimateType {
    // Elevation(min, max) || Temperature(min, max) || PrecipitationRange(min, max)
    TUNDRA(new DoublePair(92, 3376), new DoublePair(-10, 0), new DoublePair(0, 100)),
    COLD_DESERT(new DoublePair(66, 66), new DoublePair(66, 66), new DoublePair(66, 66)),
    HOT_DESERT(new DoublePair(66, 66), new DoublePair(66, 66), new DoublePair(66, 66));

    private final DoublePair elevationRange;
    private final DoublePair temperatureRange;
    private final DoublePair precipitationRange;

    ClimateType(DoublePair elevationRange, DoublePair temperatureRange, DoublePair precipitationRange) {
        this.elevationRange = elevationRange;
        this.temperatureRange = temperatureRange;
        this.precipitationRange = precipitationRange;
    }

    DoublePair getElevationRange() {
        return this.elevationRange;
    }

    DoublePair getTemperatureRange() {
        return this.temperatureRange;
    }

    DoublePair getPrecipitationRange() {
        return this.precipitationRange;
    }

    static class DoublePair {
        double d1;
        double d2;

        DoublePair(double d1, double d2) {
            this.d1 = d1;
            this.d2 = d2;
        }
    }
}
