import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class SearchAlgorithm {
	Comparator<Node> comparator = new NodeComparator();
	
	/**
	 * My implementation of the A* search algorithm.
	 * Used this video as a reference: https://www.youtube.com/watch?v=-L-WgKMFuhE
	 */
	public List<Node> search(Node start, Node end) {
		Queue<Node> open = new PriorityQueue<>(comparator);
		Set<Node> closed = new HashSet<>();
		open.add(start);
		
		while (!open.isEmpty()) {
			Node current = open.poll();
			closed.add(current);
			
			if (current == end) {
				break;
			}
			
			for (Segment edge : current.segments) {
				Node neighbour = edge.start == current ? edge.end : edge.start;
				
				if (!closed.contains(neighbour)) {
					neighbour.cost = current.cost + edge.length;
					neighbour.totalCost = neighbour.cost + estimate(neighbour, end);
					neighbour.parent = current;
					if (!open.contains(neighbour)) {
						open.add(neighbour);
					}
				}
			}
		}
		
		try {
			return getPath(start, end);
		} catch (Exception e) {
			return new ArrayList<>();
		}
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
	
	class NodeComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			if (n1.totalCost < n2.totalCost) {
				return -1;
			} else if (n1.totalCost > n2.totalCost) {
				return 1;
			}
			return 0;
		}
	}
	
	public double estimate(Node node, Node goal) {
		return node.location.distance(goal.location);
	}
}
