package google.com.ortona.hashcode.final_2014.logic;

public class JunctionDirectionManager {

    enum LocationDirection {
        UNKNOWN,
        NORTH,
        NORTH_EAST,
        EAST,
        SOUTH_EAST,
        SOUTH,
        SOUTH_WEST,
        WEST,
        NORTH_WEST
    }

    public static LocationDirection direction(LatLng latlng1, LatLng latlng2) {
        double delta = 22.5;
        LocationDirection direction = LocationDirection.UNKNOWN;
        double heading = SphericalUtil.computeHeading(latlng1, latlng2);

        if ((heading >= 0 && heading < delta) || (heading < 0 && heading >= -delta)) {
            direction = LocationDirection.NORTH;
        } else if (heading >= delta && heading < 90 - delta) {
            direction = LocationDirection.NORTH_EAST;
        } else if (heading >= 90 - delta && heading < 90 + delta) {
            direction = LocationDirection.EAST;
        } else if (heading >= 90 + delta && heading < 180 - delta) {
            direction = LocationDirection.SOUTH_EAST;
        } else if (heading >= 180 - delta || heading <= -180 + delta) {
            direction = LocationDirection.SOUTH;
        } else if (heading >= -180 + delta && heading < -90 - delta) {
            direction = LocationDirection.SOUTH_WEST;
        } else if (heading >= -90 - delta && heading < -90 + delta) {
            direction = LocationDirection.WEST;
        } else if (heading >= -90 + delta && heading < -delta) {
            direction = LocationDirection.NORTH_WEST;
        }

        return direction;
    }
}
