import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author david joyce
 * the purpose of this is class is to trace a path that will solve a maze txt.file input
 * the following methods accomplish this
 */

public class Maze {
	
	private char[][] theMaze;
	private int N,M;
	private int Startrow,Startcol;
	private int Endrow,Endcol;
	
	
	
	public Maze(String filename)throws IOException {
		
		try{
			
			Scanner scan = new Scanner(new File(filename));
			
			//M is the width (number of columns) in the maze
			this.M = scan.nextInt();
			//N is the height (number of rows) in the maze
			this.N = scan.nextInt();
			
			this.Startcol = scan.nextInt();
			this.Startrow = scan.nextInt();
			this.Endcol = scan.nextInt();
			this.Endrow = scan.nextInt();
			
			//Create a 2-dimensional char array that is the size of the maze input
			this.theMaze = new char[this.N][this.M];
			
			//Populate the 2-Dimenional char array with the characters
			//to represent the wall '#' and position for a possible path ' ' 
			for (int i = 0; i <this.N; i++ ){
				for (int j = 0; j<this.M; j++){
					
					char input_char = scan.next().charAt(0);
					
					//Check for the wall
					if (input_char == '1'){
						input_char = '#';
					}
					
					//Check for free space to move
					if (input_char == '0'){
						input_char = ' ';
					}

					//Add the character to the corresponding position
					//in the character array
					this.theMaze[i][j] = input_char;
					
				}
			}
			
			scan.close();
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		

	}
		
	public void print_stdout(){
		
		System.out.println("The Solution for the Maze is marked by the Path 'X' below: ");
		
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.M; j++) {
				System.out.print(this.theMaze[i][j]);
			}
			System.out.println();
			
		}
		
		
		
	}
	
	private boolean isSafe(int row, int col){
		
		//check if the row and column are within the maze
		if (row>=0 && row < this.N && col >=0 
				&& col < this.M){
			
			return true;
		}
		
		return false;
	}
	
	public void solve_the_maze(){
		
		//Set the start and end positions in the character array
		this.theMaze[this.Startrow][this.Startcol] = 'S';
		this.theMaze[this.Endrow][this.Endcol] = 'E';

		//Set the start and end positions in the character array
		this.theMaze[this.Startrow][this.Startcol] = 'S';
		this.theMaze[this.Endrow][this.Endcol] = 'E';
		
		//Start the recursion backtracking algorithm from the start position
		//If no solution exists then output a message to stdout
		if(solve(this.Startrow,this.Startcol) == false){
			System.out.println("Solution does not exist for this maze");	
		}
		else print_stdout();
		
		
	}
	
	private boolean solve(int row, int col) {
		
		char east = this.theMaze[row][col+1];
		char west = this.theMaze[row][col-1];
		char north = this.theMaze[row-1][col];
		char south = this.theMaze[row+1][col];
		
		//If any of the movements East, West, North or South are the End point
		//set the theMaze[row][col] position to the character 'X' and return true
		if (east == 'E' || west == 'E' || north == 'E' || south == 'E'){
			this.theMaze[row][col] = 'X';
			return true;		
		}
		
		boolean solved = false; 
		
		//Check if theMaze[row][col] is a valid position in the maze
		if (isSafe(row,col)){
			
			//make sure that we do not change the Start 'S' character
			if (this.theMaze[row][col] != 'S'){
				this.theMaze[row][col] = 'X';	
			}

			//Check if each possible movement from the current position theMaze[row][col]
			if (east == ' ' && !solved){
				solved = solve(row, col+1);
			}
			if (south == ' ' && !solved){
				solved = solve(row+1, col);
			}
			if (west == ' ' && !solved){
				solved = solve(row, col-1);
			}
			if (north == ' ' && !solved){
				solved = solve(row-1,col);
			}
			
			//If none of the above movements work and then
			//BACKTRACK: and unmark the position row,col
			if (!solved){
				this.theMaze[row][col] = ' ';
			}
		}
			
		//will return false if no possible solution f
		return solved;
		
		
	}	
}
