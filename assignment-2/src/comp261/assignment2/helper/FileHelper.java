package comp261.assignment2.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import comp261.assignment2.graph.Node;
import comp261.assignment2.graph.Point;
import comp261.assignment2.graph.Polygon;
import comp261.assignment2.graph.Road;
import comp261.assignment2.graph.Segment;

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
	public static Set<Segment> getSegments(String directory, HashMap<Integer, Node> nodes, HashMap<Integer, Road> roads) throws Exception {
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
				Road road = roads.get(id);

				Segment segment = new Segment(id, n1, n2, road, points);
				
				points.add(new Point(n1.getLat(), n1.getLon()));
				
				for (int i = 4; i < fields.length; i += 2) {
					double x = Double.parseDouble(fields[i]);
					double y = Double.parseDouble(fields[i+1]);
					Point point = new Point(x, y);
					points.add(point);
				}
				
				points.add(new Point(n2.getLat(), n2.getLon()));

				n1.getSegments().add(segment);
				n2.getSegments().add(segment);
				segments.add(segment);
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public static Set<Polygon> getPolygons(String directory) {
		Set<Polygon> polygons = new HashSet<>();
		
		try {
			String line;
			String filepath = String.format("%s/polygons.mp", directory);

			BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));

			// Skip top line
			br.readLine();
			
			boolean inPoly = false;
			int type = 0;
			List<Point> points = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				if (line.contains("[POLYGON]")) {
					inPoly = true;
					type = 0;
					points = new ArrayList<>();
				} else if (line.contains("[END]")) {
					inPoly = false;
					Polygon polygon = new Polygon(type, points);
					polygons.add(polygon);
				}
				
				if (inPoly) {
					if (line.startsWith("Type=")) {
						line = line.substring(7);
						type = Integer.parseInt(line, 16);
					}
					
					if (line.startsWith("Data0=")) {
						line = line.substring(6);
						line = line.replace("(", "");
						line = line.replace(")", "");
						line = line.replace(",", " ");
						
						Scanner sc = new Scanner(line);
						while (sc.hasNextDouble()) {
							Point point = new Point(sc.nextDouble(), sc.nextDouble());
							points.add(point);
						}
						sc.close();
					}
				}
			}
			
			br.close();
		} catch (Exception e) {
			// File may not exist
		}
		
		return polygons;
	}
}
