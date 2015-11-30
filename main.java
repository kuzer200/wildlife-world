//Eric Kuz
//Nov. 30/15
//ENSE 374-093

//This program allows users to simulate a living, breathing world.
//The size of the grid is chosen by the user, as is the number of entities that spawn.
//Currently, foilage spawning is hard-coded.

import java.util.*;
import wildlifeworld.*;

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

		System.out.println("Map legend:");
		System.out.println("\' \' - Dirt");
		System.out.println("\':\' - Grass");
		System.out.println("\'^\' - Tree");
		System.out.println("\'#\' - Shrub");
		System.out.println("\'B\' - Bluejay");
		System.out.println("\'C\' - Caterpillar");
		System.out.println("\'D\' - Deer");
		System.out.println("\'F\' - Fox");
		System.out.println("\'G\' - Grasshopper");
		System.out.println("\'H\' - Hawk");
		System.out.println("\'M\' - Mouse");
		System.out.println("\'R\' - Rabbit");
		System.out.println("\'S\' - Squirrel");
		System.out.println("\'W\' - Wolf");
		System.out.println("");

		System.out.println("How large would you like to make the world in square km?  Integer values are accepted.");
		System.out.print("Height: ");
		height = input.nextInt();
		System.out.print("Width: ");
		width = input.nextInt();

		World world = new World(height, width);
		
		while (getAnimalCount(animal_count) > world.getMapSize())
			System.out.println("Error: Too many animals.  Specify less animals or restart the program and make the world larger.");
	
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
			input.nextLine();

		while (world.simulate())
		{
			world.printInfo();
			world.printMap();
			System.out.print("Press enter to continue...");
			input.nextLine();
		}

		world.printInfo();
		world.printMap();

		System.out.println("Everything has died.  Simulation terminated.");

	}

//gets the number of animals to spawn in the world
//@prereq: animal_count is not null, is an array of ten elements
//@post: the array now stores the number of animals to spawn for each index ([0] = bluejays, [1] = caterpillars, etc.)

	static public int getAnimalCount(int[] animal_count)
	{
		int sum = 0;

		System.out.println("How many animals of each kind do you want to spawn?");
		System.out.print("Bluejay: ");		
		animal_count[0] = input.nextInt();
		System.out.print("Caterpillar: ");	
		animal_count[1] = input.nextInt();
		System.out.print("Deer: ");		
		animal_count[2] = input.nextInt();
		System.out.print("Fox: ");		
		animal_count[3] = input.nextInt();
		System.out.print("Grasshopper: ");	
		animal_count[4] = input.nextInt();
		System.out.print("Hawk: ");		
		animal_count[5] = input.nextInt();
		System.out.print("Mouse: ");		
		animal_count[6] = input.nextInt();
		System.out.print("Rabbit: ");		
		animal_count[7] = input.nextInt();
		System.out.print("Squirrel: ");	
		animal_count[8] = input.nextInt();
		System.out.print("Wolf: ");		
		animal_count[9] = input.nextInt();

		for (int i = 0; i < 10; i++)
		{
			sum += animal_count[i];
		}

		return sum;
	}
};
