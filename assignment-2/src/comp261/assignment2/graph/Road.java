package comp261.assignment2.graph;

public class Road {
	private int id;
	private String label, city;
	
	public Road(int id, String label, String city) {
		this.id = id;
		this.label = label;
		this.city = city;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getCity() {
		return city;
	}
}
