package kiloboltgame;

import java.util.Random;

//this class serves to render tiles to the screeen and the levels of the games
public class Tilemap {

	public static void main(String args[]) {
		//create a tilemap object called tp
		Tilemap tp = new Tilemap();

	}
	
	//constructor
	public Tilemap() {
		//create a 2D (30 rows 50 columns) array for our tilemap
		//sort of like arrays within arrays
		int[][] tilemap = new int[30][50];
		System.out.println("tilemap created");
		//create random number to be inserted into the tile !!
		Random r = new Random(); 
		int rows = tilemap.length; //this will print out the rows!!
		int columns = tilemap[1].length; //length of the first row is the column length!!
		
		printTiles(rows,columns,tilemap, r); 
	}

	//this is the method to print our tiles !!
	private void printTiles(int rows, int columns, int[][] tilemap, Random r) {
		//for loop to print out every row
		for (int i = 0; i<rows; i++){
			//for loop to print out every column
			for (int j=0; j<columns; j++){
				tilemap[i][j] = r.nextInt(5);
				System.out.print(" " + tilemap[i][j] );
			}
			System.out.println("");
		}
	}

}
