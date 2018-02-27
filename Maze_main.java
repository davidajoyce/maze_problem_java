import java.io.IOException;

/**
 * @author david joyce
 * the purpose of this is class is to check if a maze input has a solution
 * and print the solution path to stdout
 */

public class Maze_main {

	public static void main(String[] args) throws IOException {
		
		
		Maze maze = new Maze(args[0]);
		
		maze.solve_the_maze();		

	}

}
