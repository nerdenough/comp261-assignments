package comp261.assignment1.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import comp261.assignment1.Program;
import comp261.assignment1.graph.Node;
import comp261.assignment1.graph.Point;
import comp261.assignment1.graph.Road;
import comp261.assignment1.graph.Segment;

/**
 * FileHelper contains a number of static methods used for loading data from the
 * resource files.
 */

public class FileHelper {
	/**
	 * Returns a HashMap of nodes using data from the nodes resource file, using
	 * the node ID as the key and the node object as the value.
	 * 
	 * @return nodes
	 */
	public static HashMap<Integer, Node> getNodes(String directory) throws Exception {
		HashMap<Integer, Node> nodes = new HashMap<>();

		try {
			String line;
			String filepath = String.format("%s/nodes.tab", directory);

			BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));

			while ((line = br.readLine()) != null) {
				String[] fields = line.split("\t");

				int id = Integer.parseInt(fields[0]);
				double lat = Double.parseDouble(fields[1]);
				double lon = Double.parseDouble(fields[2]);

				Node node = new Node(id, lat, lon);

				nodes.put(id, node);
			}
			
			br.close();
		} catch (Exception e) {
			throw new Exception();
		}

		return nodes;
	}

	/**
	 * Returns a Set of segments using data from the segments resource file.
	 * 
	 * @return segments
	 */
	public static Set<Segment> getSegments(String directory, HashMap<Integer, Node> nodes) throws Exception {
		Set<Segment> segments = new HashSet<>();

		try {
			String line;
			String filepath = String.format("%s/segments.tab", directory);

			BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));

			// Skip top line
			br.readLine();

			while ((line = br.readLine()) != null) {
				String[] fields = line.split("\t");
				
				List<Point> points = new ArrayList<>();
				int id = Integer.parseInt(fields[0]);
				Node n1 = nodes.get(Integer.parseInt(fields[2]));
				Node n2 = nodes.get(Integer.parseInt(fields[3]));

				Segment segment = new Segment(id, n1, n2, points);
				
				points.add(new Point(n1.getLat(), n1.getLon()));
				
				for (int i = 4; i < fields.length; i += 2) {
					double x = Double.parseDouble(fields[i]);
					double y = Double.parseDouble(fields[i+1]);
					Point point = new Point(x, y);
					points.add(point);
				}
				
				points.add(new Point(n2.getLat(), n2.getLon()));

				segments.add(segment);
			}
			
			br.close();
		} catch (Exception e) {
			throw new Exception();
		}

		return segments;
	}

	/**
	 * Returns a HashMap of roads using data from the roads resource file, using
	 * the road ID as the key and the road object as the value.
	 * 
	 * @return roads
	 */
	public static HashMap<Integer, Road> getRoads(String directory) throws Exception {
		HashMap<Integer, Road> roads = new HashMap<>();

		try {
			String line;
			String filepath = String.format("%s/roads.tab", directory);

			BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));

			// Skip top line
			br.readLine();

			while ((line = br.readLine()) != null) {
				String[] fields = line.split("\t");

				int id = Integer.parseInt(fields[0]);
				String label = fields[2];
				String city = fields[3];

				Road road = new Road(id, label, city);

				roads.put(id, road);
			}
			
			br.close();
		} catch (Exception e) {
			throw new Exception();
		}

		return roads;
	}
}
