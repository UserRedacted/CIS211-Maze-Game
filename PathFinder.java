import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PathFinder {
	 //private Map<Vertex, List<Vertex>> adjVertices;
	 private LinkedList<Graph.Node> path = new LinkedList<>();
	 private Graph graph;
	
	 PathFinder(Graph graph){
		 
		 this.graph = graph;
	 }
	 
	 public LinkedList<Graph.Node> breadthFirstSearch(Graph.Node start, Graph.Node end) {

	        if (end == null)
	            return null;
	        
	        
	        LinkedList<Graph.Node> queue = new LinkedList<>();
	        queue.add(start);

	        while (!queue.isEmpty()) {
	        	Graph.Node currentFirst = queue.removeFirst();

	            if (currentFirst.visited == true)
	                continue;

	            currentFirst.visited = true;
	            path.add(currentFirst);

	            // LinkedList<Vertex> allNeighbors = (LinkedList<Vertex>) adjVertices.get(currentFirst);

	            if (currentFirst.neighbors == null)
	                continue;

	            for (Graph.Node neighbor : currentFirst.neighbors) {
	                if (neighbor.visited == false) {
	                    queue.add(neighbor);
	                }
	            }
	        }
	        //queue.add(end);
	        return path;
	    }
	 
	 
/* 
	 class Vertex {
	    int x, y;
 	    boolean visited;
 	    Vertex(int x, int y) {
 	        this.x = x;
 	        this.y = y;
 	        visited = false;
 	    }

 	    void visit() {
 	        visited = true;
 	    }

 	    void unvisit() {
 	        visited = false;
 	    }
	        String label;
	        Vertex(String label) {
	            this.label = label;
	        }
	        
	        @Override
	        public int hashCode() {
	            final int prime = 31;
	            int result = 1;
	            result = prime * result + getOuterType().hashCode();
	            result = prime * result + ((label == null) ? 0 : label.hashCode());
	            return result;
	        }
	        
	        @Override
	        public boolean equals(Object obj) {
	            if (this == obj)
	                return true;
	            if (obj == null)
	                return false;
	            if (getClass() != obj.getClass())
	                return false;
	            Vertex other = (Vertex) obj;
	            if (!getOuterType().equals(other.getOuterType()))
	                return false;
	            if (label == null) {
	                if (other.label != null)
	                    return false;
	            } else if (!label.equals(other.label))
	                return false;
	            return true;
	        }

	        @Override
	        public String toString() {
	            return label;
	        }
	        
	        public int getX() {
	        	return this.x;
	        }
	        
	        public int getY() {
	        	return this.y;
	        }
	        
	        public boolean isVisited() {
	        	if(this.visited == true) {
	        		return true;
	        	}else {
	        		return false;
	        	}
	        }

	        private Graph getOuterType() {
	            return getGraph();
	            
	        }
	        
	
	    }

	public Map<Vertex, List<Vertex>> getAdjVertices() {
		return adjVertices;
	}

	public void setAdjVertices(Map<Vertex, List<Vertex>> adjVertices) {
		this.adjVertices = adjVertices;
	}

	public LinkedList<Vertex> getPath() {
		return path;
	}

	public void setPath(LinkedList<Vertex> path) {
		this.path = path;
	}

	public Vertex getStart() {
		return start;
	}

	public void setStart(Vertex start) {
		this.start = start;
	}

	public Vertex getEnd() {
		return end;
	}

	public void setEnd(Vertex end) {
		this.end = end;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
*/	 
	 
}
