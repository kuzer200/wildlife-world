//Eric Kuz
//Nov. 9/15
//ENSE 374-093

//This program allows users to simulate a living, breathing world.  Currently, you can spawn entities on a 2D grid.
//The size of the grid is chosen by the user, as is the number of entities that spawn.
//Currently, foilage spawning is hard-coded.

import java.util.*;

class Main
{	
	static Scanner input = new Scanner(System.in);
	static final int ENTITY_TYPES = 10;

	public static void main(String[] args)
	{
		int width, height;
		int[] animal_count = new int[ENTITY_TYPES];

		System.out.println("Welcome to Wildlife World.");
		System.out.println("This program allows you to simulate a small, isolated ecosystem and watch animals interact.");
		System.out.println("Animals can eat each other, and will starve if they do not eat anything within three days.");
		System.out.println("");

		System.out.println("How large would you like to make the world in square km?  Integer values are accepted.");
		System.out.print("Height: ");
		height = input.nextInt();
		System.out.print("Width: ");
		width = input.nextInt();

		World world = new World(height, width);

		while (getAnimalCount(animal_count) > world.getMapSize())
			System.out.println("Error: Too many animals.  Specify less animals or restart the program and make the world smaller.");
	
		world.spawnEntities("bluejay", animal_count[0]);
		world.spawnEntities("caterpillar", animal_count[1]);
		world.spawnEntities("deer", animal_count[2]);
		world.spawnEntities("fox", animal_count[3]);
		world.spawnEntities("grasshopper", animal_count[4]);
		world.spawnEntities("hawk", animal_count[5]);
		world.spawnEntities("mouse", animal_count[6]);
		world.spawnEntities("rabbit", animal_count[7]);
		world.spawnEntities("squirrel", animal_count[8]);
		world.spawnEntities("wolf", animal_count[9]);

		world.printMap();
		world.printEntityInfo(true);
	}

//gets the number of animals to spawn in the world
//@prereq: animal_count is not null, is an array of ten elements
//@post: the array now stores the number of animals to spawn for each index ([0] = bluejays, [1] = caterpillars, etc.)

	static public int getAnimalCount(int[] animal_count)
	{
		int sum = 0;

		System.out.println("How many animals of each kind do you want to spawn?");
		System.out.print("Bluejay:\t");		
		animal_count[0] = input.nextInt();
		System.out.print("Caterpillar:\t");	
		animal_count[1] = input.nextInt();
		System.out.print("Deer:\t");		
		animal_count[2] = input.nextInt();
		System.out.print("Fox:\t");		
		animal_count[3] = input.nextInt();
		System.out.print("Grasshopper:\t");	
		animal_count[4] = input.nextInt();
		System.out.print("Hawk:\t");		
		animal_count[5] = input.nextInt();
		System.out.print("Mouse:\t");		
		animal_count[6] = input.nextInt();
		System.out.print("Rabbit:\t");		
		animal_count[7] = input.nextInt();
		System.out.print("Squirrel:\t");	
		animal_count[8] = input.nextInt();
		System.out.print("Wolf:\t");		
		animal_count[9] = input.nextInt();

		for (int i = 0; i < 10; i++)
		{
			sum += animal_count[i];
		}

		return sum;
	}
};
