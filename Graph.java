import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;


// A user define class to represent a graph. 
// A graph is an array of adjacency lists. 
// Size of array will be V (number of vertices 
// in graph) 

//Adapted from https://www.geeksforgeeks.org/graph-and-its-representations/
class Graph { 

	
	ArrayList<Node> vertices;
	Node start;
	Node end;
	Graph() {
		vertices = new ArrayList<>();
		start = null;
		end = null;
	}
	Graph(ArrayList<Node> vertices) { 

		this.vertices = vertices;
				
		 
	}
//	public void loadGraph
	public boolean[] getConnections(Node vertex) {
		
		//top, right, bottom, left
		boolean[] connections = {false, false, false, false};

		for (Node n : vertex.neighbors) {
			
			if (n.data.x == vertex.data.x && n.data.y < vertex.data.y) {
				connections[0] = true;
			}
			if (n.data.x == vertex.data.x + 1 && n.data.y == vertex.data.y)
				connections[1] = true;
			if (n.data.x == vertex.data.x && n.data.y > vertex.data.y)
				connections[2] = true;
			if (n.data.x == vertex.data.x - 1 && n.data.y == vertex.data.y)
				connections[3] = true;
		}
		return connections;
	}
	
	public void loadGraph(String fileName) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String currentLine = br.readLine();
			while (currentLine != null) {
				String[] coords = currentLine.split(" ");
				
				int tempX = Integer.parseInt(coords[0].split(",")[0]);
				int tempY = Integer.parseInt(coords[0].split(",")[1]);
				
				Node temp = new Node(new Position(tempX, tempY));
				
				for (int i = 1; i < coords.length; i++) {
					tempX = Integer.parseInt(coords[i].split(",")[0]);
					tempY = Integer.parseInt(coords[i].split(",")[1]);
					temp.neighbors.add(new Node(new Position(tempX, tempY)));
				}
				
				vertices.add(temp);
				//System.out.println(temp);
				currentLine = br.readLine();
			}
			br.close();
			
		}catch (Exception ee) {
			ee.printStackTrace();
		}
		
		
		this.end = vertices.get(16);
		this.start = vertices.get(18);

		
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < vertices.size(); i++) {
			sb.append(vertices.get(i));
			if (i < vertices.size()-1)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	class Node {
		Position data;
		LinkedList<Node> neighbors;
		
		public boolean visited;

		
		public Node(Position node) {
			this.data = node;
			this.neighbors = new LinkedList<>();
			this.visited = false;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Node: " + data.toString());
			
			/*
			sb.append(" | Neighbors: ");
			for (int i = 0; i < neighbors.size(); i++) {
				sb.append(neighbors.get(i));
				if (i < neighbors.size()-1)
					sb.append(", ");
			}
			*/
			
			return sb.toString();
		}
		public boolean equals(Node n) {
			return n.data.equals(this.data);
			
		}
	}
	class Position {
		public int x;
		public int y;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
		
	}
}