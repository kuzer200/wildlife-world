//Interprets the values in the 2D array of ints from Map.java.
import java.util.*;

public class World
{
	private Map map;
	private int mapSize;
	private boolean[][] takenSpawnLocations ;
	private ArrayList<Entity> entityList;

//makes a world
//@pre: rows and columns are not zero or negative
//@post: a world is created wih the given dimensions

	public World(int rows, int columns)
	{
		map = new Map(rows, columns);
		mapSize = rows*columns;
		entityList = new ArrayList<Entity>();

		takenSpawnLocations = new boolean[map.getHeight()][map.getWidth()];
		for (int i = 0; i < map.getHeight(); i++)
			for (int j = 0; j < map.getWidth(); j++)
				takenSpawnLocations[i][j] = false;

		map.makePlants();
	}

//provides a tile to display to the console
//@pre: entity is not null
//@post: An entity is added to the arraylist

	public void addEntity(Entity ent)
	{
		entityList.add(ent);
	}

//spawns an entity in the world.  Will not spawn two entities in the same location.
//@pre: name is valid, num is greater than zero
//@post: num entities of type name are put into the world

	public void spawnEntities(String name, int num)
	{
		int x_pos, y_pos = 0;	

		for (int i = 0; i < num; i++)
		{
			x_pos = (int)(Math.random() * (map.getWidth()));
			y_pos = (int)(Math.random() * (map.getHeight()));

			while (takenSpawnLocations[y_pos][x_pos]) //make sure the space isn't already taken
			{
				x_pos = (int)(Math.random() * (map.getWidth()));
				y_pos = (int)(Math.random() * (map.getHeight()));
			}

			takenSpawnLocations[y_pos][x_pos] = true;

			switch (name)
			{
				case "bluejay": 	addEntity(new Bluejay(y_pos, x_pos)); break;
				case "caterpillar": 	addEntity(new Caterpillar(y_pos, x_pos)); break;
				case "deer": 		addEntity(new Deer(y_pos, x_pos)); break;
				case "grasshopper": 	addEntity(new Grasshopper(y_pos, x_pos)); break;
				case "fox": 		addEntity(new Fox(y_pos, x_pos)); break;
				case "hawk": 		addEntity(new Hawk(y_pos, x_pos)); break;
				case "mouse": 		addEntity(new Mouse(y_pos, x_pos)); break;
				case "rabbit": 		addEntity(new Rabbit(y_pos, x_pos)); break;
				case "squirrel": 	addEntity(new Squirrel(y_pos, x_pos)); break;
				case "wolf": 		addEntity(new Wolf(y_pos, x_pos)); break;
			}
		}
	}

	public int getMapSize() //can't set the size after it's been declared, so there's no setter.
	{
		return mapSize;
	}

	public int getMapHeight() //can't set the height after it's been declared, so there's no setter.
	{
		return map.getHeight();
	}

	public int getMapWidth() //likewise for the width.
	{
		return map.getWidth();
	}

	public int getMapTileData(int y, int x)
	{
		return map.getTileData(y, x);
	}

	public void setMapTileData(int x, int y, int n)
	{
		map.setTileData(x, y, n);
	}

//prints the map.  Entities take precedence over terrain.
//@pre: none
//@post: none

	public void printMap()
	{
		char[][] toPrint = new char[getMapHeight()][getMapWidth()];

		for (int i = 0; i < getMapHeight(); i++)
			for (int j = 0; j < getMapWidth(); j++)
				toPrint[i][j] = map.getDisplayTile(i, j);

	  	for(Entity ent: entityList)
		{
			if (ent.isAlive()) //do not print dead things
				toPrint[ent.getY()][ent.getX()] = ent.getDisplayChar();
	   	}

		System.out.print("+"); //top border
		for (int i = 0; i < getMapWidth(); i++)
			System.out.print("-");
		System.out.print("+\n");

		for (int i = 0; i < getMapHeight(); i++)
		{
			System.out.print("|"); //left border
			
			for (int j = 0; j < getMapWidth(); j++)
				System.out.print(toPrint[i][j]);

			System.out.print("|\n"); //right border
		}

		System.out.print("+"); //bottom border
		for (int i = 0; i < getMapWidth(); i++)
			System.out.print("-");
		System.out.print("+\n");
	}

//prints entity position and their speech.  An argument can be supplied to turn on or off verbose printing
//@pre: none
//@post: none

	public void printEntityInfo(boolean printDeadAnimals)
	{
	  	for(Entity ent: entityList)
		{
			if (ent.isAlive() || printDeadAnimals)
			{
				System.out.print("The " + ent.getName() + " at position [" + ent.getY() + "][" + ent.getX() +"] ");
		
				if (!ent.isAlive())
					System.out.print("is dead.\n");
				else
					System.out.print("says " + ent.speak() + "\n");
			}
	   	}

	}

}
