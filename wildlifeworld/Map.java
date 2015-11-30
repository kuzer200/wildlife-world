//basically a 2D array of tiles.

package wildlifeworld;
import java.util.*;

public class Map
{
	private int row_size;
	private int column_size;
	private Tile[][] tile;

	public Map(int rows, int columns)
	{
		row_size = rows;
		column_size = columns;

		tile = new Tile[rows][columns];

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
			{
				tile[i][j] = new Tile();
			}


	}

//generates plants in the 2D array
//@pre: none
//@post: plants are inserted into the world

	public void makePlants()
	{
		int grass_count = (getWidth() * getHeight())/2;  //half of the map is covered in grass
		int tree_count = (getWidth() * getHeight())/20;  //5% of the map is covered in trees
		int shrub_count = (getWidth() * getHeight())/10;  //10% of the map is covered in shrubs

		int x_pos, y_pos;

		for (int i = 0; i < grass_count; i++)
		{
			x_pos = (int)(Math.random() * (getWidth()));
			y_pos = (int)(Math.random() * (getHeight()));
			tile[y_pos][x_pos].setTileData(1);
		}

		for (int i = 0; i < tree_count; i++)
		{
			x_pos = (int)(Math.random() * (getWidth()));
			y_pos = (int)(Math.random() * (getHeight()));
			tile[y_pos][x_pos].setTileData(2);
		}	

		for (int i = 0; i < shrub_count; i++)
		{
			x_pos = (int)(Math.random() * (getWidth()));
			y_pos = (int)(Math.random() * (getHeight()));
			tile[y_pos][x_pos].setTileData(3);
		}	
	
	}

	public int getHeight() //can't set the height after it's been declared, so there's no setter.
	{
		return row_size;
	}

	public int getWidth() //likewise for the width.
	{
		return column_size;
	}

	public int getTileData(int y, int x)
	{
		return tile[y][x].getTileData();
	}

	public void setTileData(int y, int x, int n)
	{
		tile[y][x].setTileData(n);
	}

	public void addEntity(Entity e, int y, int x)
	{
		tile[y][x].setEntity(e);
	}

	public void removeEntity(int y, int x)
	{
		tile[y][x].setEntity(null);
	}

	public Entity getEntity(int y, int x)
	{
		return tile[y][x].getEntity();
	}



//provides a tile to display to the console
//@pre: none
//@post: none

	public char getDisplayTile(int y, int x)
	{
		char disp = ' ';
		int temp = tile[y][x].getTileData();

		switch (temp)
		{
			case 0: disp = ' '; break; //ground
			case 1: disp = ':'; break; //grass
			case 2: disp = '^'; break; //tree
			case 3: disp = '#'; break; //shrub
		}
		
		return disp;
	}
}
