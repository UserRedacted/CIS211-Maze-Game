
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MazeDriver extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		Scene scene = new Scene(mainWindow(), 1600, 900);
		
		File style = new File("style.css");
		scene.getStylesheets().add(style.toURI().toString());
		stage.setMaximized(true);

		stage.setTitle("A m a z i n g   M a z e");
		stage.setScene(scene);
		FileInputStream input;
		try {
			input = new FileInputStream(new File("cobblestone.png"));
			Image image = new Image(input);
			stage.getIcons().add(image);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		stage.show();
	}

	
	
	// Main method that loads printers and launches JavaFX window
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public VBox mainWindow() {
		

		Graph graph = new Graph();
		graph.loadGraph("adjList.txt");
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		Text header = new Text("A m a z i n g   M a z e");
		header.setId("text");
		
		GridPane maze = new GridPane();
		maze.setAlignment(Pos.CENTER);

		
		for(Graph.Node node: graph.vertices) {

    		int x = node.data.x;
    		int y = node.data.y;
    		    		
    		 boolean[] connections = graph.getConnections(node);
    		// N E S W

    		Pane temp = new Pane();
    		temp.setId("cell");
    	    temp.setMinSize(64, 64);
    	    
    	    
    	    String style = "-fx-border-width: ";
    	    String borderWidth = "3";
    	    
    	    
    	    
    	    for(int index = 0; index < 4; index++) {
	    	    if(connections[index]) {
	    	    	style += "0 ";
	    	    } else {
	    	    	style += borderWidth + " ";
	    	    }
    	    }
    	    style = style.substring(0, style.length()-1);
    	    style += ";";
    	    
    	    temp.setStyle(style);
    	    
    		maze.add(temp, x, y);
    	
	    }
	    
	    
	    
	    Button solve = new Button("Solve!");
	    solve.setMinWidth(200);
	    solve.setId("text");
	    
	    solve.setOnMouseClicked(e -> {
	    	solveMaze(maze, graph);
	    });
	    
	    
	    
	    root.getChildren().add(header);

	    root.getChildren().add(maze);

	    root.getChildren().add(solve);
	    
		return root;
	
	}



	private void solveMaze(GridPane maze, Graph graph) {
		
		PathFinder solver = new PathFinder(graph);
		
		// get linked list
		// List of nodes representing solution
		LinkedList<Graph.Node> path = solver.breadthFirstSearch(graph.start, graph.end);
		
		System.out.println("Path:");
		System.out.println(path);
		
		
		int count = 1500;
		
		Timer timer = new Timer();
		

		for(Graph.Node n: path) {
			Graph.Node newPos = n;
			timer.schedule(new UpdatePlayer(maze, newPos), count);
			count += count;
		}
		
		
	}
	
	public class UpdatePlayer extends TimerTask {

		GridPane maze;
		Graph.Node newPos;
		
		
		public UpdatePlayer(GridPane maze, Graph.Node newPos) {
			this.maze = maze;
			this.newPos = newPos;
		}
		
		
		@Override
		
		public void run() {
			Platform.runLater(new Runnable() {
				public void run() {
					
					int nx = newPos.data.x;
					int ny = newPos.data.y;
					Pane newP = (Pane) getPane(maze, nx, ny);

					File image = new File("player.gif");
					Image player = new Image(image.toURI().toString());
					ImageView holder = new ImageView();
					holder.setImage(player);

					newP.getChildren().add(holder);
					
				}
			});
		}
		
		private Node getPane(GridPane gridPane, int col, int row) {
		    for (Node node : gridPane.getChildren()) {
		        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
		            return node;
		        }
		    }
		    return null;
		}
		
	}
	
	
}
