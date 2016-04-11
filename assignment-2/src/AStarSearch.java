import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AStarSearch {	
	public List<Node> search(Node start, Node end) {
		// https://www.youtube.com/watch?v=-L-WgKMFuhE
		Set<Node> open = new HashSet<>();
		Set<Node> closed = new HashSet<>();
		open.add(start);
		
		boolean foundPath = false;
		
		while (!foundPath) {
			Node current = getLowestCost(open, end);
			open.remove(current);
			closed.add(current);
			
			if (current == end) {
				foundPath = true;
				break;
			}
			
			for (Node neighbour : current.neighbours) {
				if (!closed.contains(neighbour)) {
					neighbour.fCost = start.location.distance(neighbour.location) + end.location.distance(neighbour.location);
					neighbour.parent = current;
					if (!open.contains(neighbour)) {
						open.add(neighbour);
					}
				}
			}
		}
		
		return getPath(start, end);
	}
	
	/**
	 * Returns the path from end to start.
	 */
	public List<Node> getPath(Node start, Node end) {
		List<Node> path = new ArrayList<>();
		Node node = end;
		
		while (node != start) {
			path.add(node);
			node = node.parent;
		}
		
		path.add(start);
		return path;
	}
	
	public Node getLowestCost(Set<Node> open, Node end) {
		Node lowest = null;
		for (Node node : open) {
			if (lowest == null || node.fCost < lowest.fCost) {
				lowest = node;
			}
		}
		return lowest;
	}
}
