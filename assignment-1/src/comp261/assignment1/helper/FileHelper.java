package comp261.assignment1.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import comp261.assignment1.Program;
import comp261.assignment1.graph.Graph.GraphType;
import comp261.assignment1.graph.Node;

/**
 * FileHelper contains a number of static methods used for loading data from the
 * resource files.
 */

public class FileHelper {
	public static HashMap<Integer, Node> getNodes(GraphType type) {
		HashMap<Integer, Node> nodes = new HashMap<>();

		try {
			String line;
			String folder = type == GraphType.SMALL ? "small" : "large";
			String filepath = String.format("/data/%s/nodes.tab", folder);

			InputStream is = Program.class.getResourceAsStream(filepath);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			while ((line = br.readLine()) != null) {
				String[] fields = line.split("\t");

				int id = Integer.parseInt(fields[0]);
				double lat = Double.parseDouble(fields[1]);
				double lon = Double.parseDouble(fields[2]);

				Node node = new Node(id, lat, lon);

				nodes.put(id, node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nodes;
	}
}
