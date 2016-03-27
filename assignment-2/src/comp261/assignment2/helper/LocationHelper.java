package comp261.assignment2.helper;

import comp261.assignment2.graph.Graph;

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
	public static float getLocX(double lat) {
		return (float) ((lat - Graph.CENTRE_LAT) * Graph.SCALE_LAT * Graph.zoom);
	}

	/**
	 * Returns the Y location for the specified longitude relative to the centre
	 * longitude.
	 * 
	 * @param lon - Longitude to convert
	 * @return Y location
	 */
	public static float getLocY(double lon) {
		return (float) ((lon - Graph.CENTRE_LON) * Graph.SCALE_LON * Graph.zoom);
	}
}
