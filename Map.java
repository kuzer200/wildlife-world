//basically a 2D array of ints.

import java.util.*;

public class Map
{
	private int row_size;
	private int column_size;
	private int[][] tile_data;

	public Map(int rows, int columns)
	{
		row_size = rows;
		column_size = columns;

		tile_data = new int[rows][columns];

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				tile_data[i][j] = 0;  //initialize everything as dirt
	}

//generates plants in the 2D array
//@pre: none
//@post: plants are inserted into the world

	public void makePlants()
	{
		int grass_count = (getWidth() * getHeight())/2;  //half of the map is covered in grass
		int tree_count = (getWidth() * getHeight())/20;  //5% of the map is covered in trees
		int shrub_count = (getWidth() * getHeight())/10;  //10% of the map is covered in shrubs

		System.out.println(grass_count + " " + tree_count + " " + shrub_count);

		int x_pos, y_pos;

		for (int i = 0; i < grass_count; i++)
		{
			x_pos = (int)(Math.random() * (getWidth()));
			y_pos = (int)(Math.random() * (getHeight()));
			setTileData(y_pos, x_pos, 1);
		}

		for (int i = 0; i < tree_count; i++)
		{
			x_pos = (int)(Math.random() * (getWidth()));
			y_pos = (int)(Math.random() * (getHeight()));
			setTileData(y_pos, x_pos, 2);
		}	

		for (int i = 0; i < shrub_count; i++)
		{
			x_pos = (int)(Math.random() * (getWidth()));
			y_pos = (int)(Math.random() * (getHeight()));
			setTileData(y_pos, x_pos, 3);
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
		return tile_data[y][x];
	}

	public void setTileData(int y, int x, int n)
	{
		tile_data[y][x] = n;
	}

//provides a tile to display to the console
//@pre: none
//@post: none

	public char getDisplayTile(int y, int x)
	{
		char disp = ' ';

		switch (tile_data[y][x])
		{
			case 0: disp = ' '; break; //ground
			case 1: disp = ':'; break; //grass
			case 2: disp = '^'; break; //tree
			case 3: disp = '#'; break; //shrub
		}
		
		return disp;
	}
}
