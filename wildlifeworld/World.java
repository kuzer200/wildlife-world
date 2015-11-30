//Interprets the values in the 2D array of ints from Map.java.

package wildlifeworld;
import java.util.*;

public class World
{
	private Map map;
	private int mapSize;
	private int day;
	private boolean[][] takenSpawnLocations;
	private ArrayList<Entity> entityList;

//makes a world
//@pre: rows and columns are not zero or negative
//@post: a world is created wih the given dimensions

	public World(int rows, int columns)
	{
		map = new Map(rows, columns);
		mapSize = rows*columns;
		day = 0;
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
		map.addEntity(ent, ent.getY(), ent.getX());
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
			{
				toPrint[i][j] = map.getDisplayTile(i, j);

				if (map.getEntity(i, j) != null)
				{
				 	Entity ent = map.getEntity(i, j);
					
					if (ent.isAlive())
						toPrint[i][j] =	ent.getDisplayChar();
	   	
				}
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

	public void printInfo()
	{
		int animal_count = 0;
		int alive = 0;
	  	for(Entity ent: entityList)
		{
			animal_count++;
			if (ent.isAlive())
			{
				System.out.print("The " + ent.getName() + " at position [" + ent.getY() + "][" + ent.getX() +"] ");
				System.out.print("says " + ent.speak() + "\n");
				alive++;
			}
	   	}
	
		System.out.println("Day: " + day);
		System.out.println("Animals alive: " + alive + "/" + animal_count);

	}

//simulates a day in the world
//@pre: N/A
//@post:N/A

	public boolean simulate()
	{
		boolean somethingIsAlive = false;

	  	for(Entity ent: entityList)
		{
			if (ent.isAlive())
			{
				ent.loseHunger();

				if (ent.getHunger() > 0)
				{
					somethingIsAlive = true;
					moveEntity(ent);
				}
				else	
				{
					System.out.print("The " + ent.getName() + " at position [" + ent.getY() + "][" + ent.getX() + "] ");
					System.out.println("died of starvation."); 
				}
			}
	   	}
		day++;

		return somethingIsAlive;		
	}

//moves an entity
//@pre: entity is not null
//@post: the entity is moved to a valid space

	public void moveEntity(Entity ent)
	{
		int direction = 0;
		int oldX = 0;
		int oldY = 0;
		int newX = 0;
		int newY = 0;

		int attempts = 0;

		boolean successMove = false;
		for (int i = 0; i < ent.getWalkSpeed(); i++)
		{	
			do
			{
				successMove = false;

				direction = (int)(Math.random() * 4);
				oldX = ent.getX();
				oldY = ent.getY();
				newX = oldX;
				newY = oldY;

				if (direction == 0 && oldY > 0) //up
				{
					newY = oldY-1;
					successMove = true;
				}
				else if (direction == 1 && oldX < map.getWidth() - 1) //right
				{
					newX = oldX+1;
					successMove = true;
				}
				else if (direction == 2 && oldY < map.getHeight() - 1) //down
				{
					newY = oldY+1;
					successMove = true;
				}
				else if (direction == 3 && oldX > 0) //left
				{
					newX = oldX-1;
					successMove = true;
				}
	
				if (successMove == true)
				{
					if (checkNewTile(ent, newY, newX))
						{
							ent.setY(newY);
							ent.setX(newX);
							map.removeEntity(oldY, oldX);
							map.addEntity (ent, newY, newX);
						
						}
					else
						successMove = false;
				}

				attempts++;

			} while (!successMove && attempts < 20);
			attempts = 0;

		}
	}

//checks if a potential tile is suitable for moving
//@pre: entity is not null, y and x are in range of map size
//@post: returns true if the tile is valid, flse otherwise

	public boolean checkNewTile(Entity e, int y, int x)
	{
		Entity entityAtNewLoc = map.getEntity(y, x);

		if (entityAtNewLoc == null || entityAtNewLoc.isAlive() == false)
		{
			if (canEatTerrain (e, y, x))
			{
				System.out.println("The " + e.getName() + " ate the plant at [" + e.getY() + "][" + e.getX() +"].");
				map.setTileData(y, x, 0);
				e.restoreHunger();
			}

			return true;
		}
		else
		{
			if (canEatEntity (e, entityAtNewLoc))
			{
				entityAtNewLoc.die();
				map.removeEntity(y,x);
				System.out.println("The " + e.getName() + " ate the " + entityAtNewLoc.getName() + " at [" + e.getY() + "][" + e.getX() +"].");
				e.restoreHunger();
				return true;
			}
			else
			{
				return false;

			}
		}
	}

//a table for predator-prey relationships
//@pre: entities are not null
//@post: returns if the first entity can eat the second

	public boolean canEatEntity (Entity predator, Entity prey)
	{
		
		String predname = predator.getName();
		String preyname = prey.getName();

		switch (predname)
		{
			case "bluejay": if (preyname == "caterpillar" || preyname == "grasshopper") return true; break;
			case "fox": if (preyname == "bluejay" || preyname == "mouse" || preyname == "squirrel" || preyname == "rabbit") return true; break;
			case "hawk":  if (preyname == "mouse" || preyname == "squirrel") return true; break;
			case "wolf":   if (preyname == "deer" || preyname == "rabbit") return true; break;
			default: return false;
		}
		return false;
	}

//a table for prey-plant elationships
//@pre: entity is not null
//@post: returns if the first entity can eat the plant on the tile or not

	public boolean canEatTerrain (Entity predator, int y, int x)
	{
		String predname = predator.getName();
		int td = map.getTileData(y,x);

		switch (predname)
		{
			case "bluejay": if (td > 1) return true; break;
			case "caterpillar": if (td > 1) return true; break;
			case "deer": if (td != 0) return true; break;
			case "grasshopper": if (td == 1) return true; break;
			case "mouse": if (td != 0) return true; break;
			case "rabbit": if (td == 1) return true; break;
			case "squirrel": if (td > 1) return true; break;
			default: return false;
		}
		return false;
	}

}
