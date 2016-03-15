package comp261.assignment1.helper;

import comp261.assignment1.graph.Graph;

/**
 * LocationHelper contains a number of static methods for converting latitudes
 * and longitudes to X and Y locations.
 */

public class LocationHelper {
	/**
	 * Returns the X location for the specififed latitude relative to the centre
	 * latitude.
	 * 
	 * @param lat - Latitude to convert
	 * @return X location
	 */
	public static double getLocX(double lat) {
		return (lat - Graph.CENTRE_LAT) * Graph.SCALE_LAT * Graph.zoom;
	}

	/**
	 * Returns the Y location for the specified longitude relative to the centre
	 * longitude.
	 * 
	 * @param lon - Longitude to convert
	 * @return Y location
	 */
	public static double getLocY(double lon) {
		return (lon - Graph.CENTRE_LON) * Graph.SCALE_LON * Graph.zoom;
	}
}
