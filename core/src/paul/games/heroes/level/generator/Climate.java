package paul.games.heroes.level.generator;

import paul.games.heroes.math.MoreMath;

public class Climate {
    private final int MAX_ELEVATION = 8000;
    private final int MIN_ELEVATION = -400;
    private final int MAX_TEMPERATURE = 40;
    private final int MIN_TEMPERATURE = -90;
    private final int MAX_PRECIPITATION = 400;
    private final int MIN_PRECIPITATION = 0;

    public ClimateType determineClimateType(double elevation, double temperature, double precipitation) {
        ClimateType type = null;
        double localElevation;
        double localTemperature;
        double localPrecipitation;

        // Clamp initial noise to -1 <-> 1
        localElevation = MoreMath.clamp(elevation, -1, 1);
        localTemperature = MoreMath.clamp(temperature, -1, 1);
        localPrecipitation = MoreMath.clamp(precipitation, -1, 1);

        // Convert noise to easier to understand units
        localElevation = MoreMath.multiClamp(localElevation, MIN_ELEVATION, MAX_ELEVATION); // -400m <-> 8000m
        localTemperature = MoreMath.multiClamp(localTemperature, MIN_TEMPERATURE, MAX_TEMPERATURE); // -90°C <-> 40°C
        localPrecipitation = MoreMath.multiClamp(localPrecipitation, MIN_PRECIPITATION, MAX_PRECIPITATION); // 0cm <-> 400cm

        for(ClimateType climateType : ClimateType.values())
        {
            if(MoreMath.isValueBetween(localElevation, climateType.getElevationRange().d1, climateType.getElevationRange().d2))
            {
                if(MoreMath.isValueBetween(localTemperature, climateType.getTemperatureRange().d1, climateType.getTemperatureRange().d2))
                {
                    if(MoreMath.isValueBetween(localPrecipitation, climateType.getPrecipitationRange().d1, climateType.getPrecipitationRange().d2))
                    {
                        type = climateType;
                    }
                }
            }
        }

        return type;
    }
}
