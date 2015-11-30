//encapsulates an entity in the world.

package wildlifeworld;
import java.util.*;

public abstract class Entity
{
	protected int x; //x coordinate
	protected int y; //y coordinate

	protected String name; //name of animal
	protected String speech; //what it says
	protected int hunger;
	protected int walk_speed;
	protected char display_char;

	protected boolean alive = true;
	

	public int getX()
	{
		return x;
	}

	public void setX(int x1)
	{
		x = x1;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y1)
	{
		y = y1;
	}

	public int getHunger()
	{
		return hunger;
	}

	public void loseHunger()
	{
		hunger--;

		if (hunger == 0)
			die();
	}

	public void restoreHunger()
	{
		 hunger = 3;
	}

	public String getName()
	{
		return name;
	}

	public String speak()
	{
		return speech;
	}

	public char getDisplayChar()
	{
		return display_char;
	}

	public int getWalkSpeed()
	{
		return walk_speed;
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void die()
	{
		alive = false;
	}

};

class Bluejay extends Entity
{
	Bluejay(int y1, int x1)
	{
		x = x1;
		y = y1;

		name = "bluejay";
		speech = "Tweet tweet!";
		 hunger = 4;
		walk_speed = 5;
		display_char = 'B';
	}
};

class Caterpillar extends Entity
{
	Caterpillar(int y1, int x1)
	{
		x = x1;
		y = y1;

		name = "caterpillar";
		speech = "Squirm, squirm.";
		 hunger = 4;
		walk_speed = 1;
		display_char = 'C';
	}
};

class Deer extends Entity
{
	Deer(int y1, int x1)
	{
		x = x1;
		y = y1;

		name = "deer";
		speech = "Snort.";
		 hunger = 4;
		walk_speed = 3;
		display_char = 'D';
	}
};

class Fox extends Entity
{
	Fox(int y1, int x1)
	{
		x = x1;
		y = y1;

		name = "fox";
		speech = "Yip yip yip!";
		 hunger = 4;
		walk_speed = 3;
		display_char = 'F';
	}
};

class Grasshopper extends Entity
{
	Grasshopper(int y1, int x1)
	{
		x = x1;
		y = y1;

		name = "grasshopper";
		speech = "Chirp chirp chirp chirp.";
		 hunger = 4;
		walk_speed = 1;
		display_char = 'G';
	}
};

class Hawk extends Entity
{
	Hawk(int y1, int x1)
	{
		x = x1;
		y = y1;

		name = "hawk";
		speech = "Caw caw!";
		 hunger = 4;
		walk_speed = 5;
		display_char = 'H';
	}
};

class Mouse extends Entity
{
	Mouse(int y1, int x1)
	{
		x = x1;
		y = y1;

		name = "mouse";
		speech = "Squeak!";
		 hunger = 4;
		walk_speed = 3;
		display_char = 'M';
	}
};

class Rabbit extends Entity
{
	Rabbit(int y1, int x1)
	{
		x = x1;
		y = y1;

		name = "rabbit";
		speech = "Nibble nibble...";
		 hunger = 4;
		walk_speed = 3;
		display_char = 'R';
	}
};

class Squirrel extends Entity
{
	Squirrel(int y1, int x1)
	{
		x = x1;
		y = y1;

		name = "squirrel";
		speech = "Nuts!";
		 hunger = 4;
		walk_speed = 3;
		display_char = 'S';
	}
};

class Wolf extends Entity
{
	Wolf(int y1, int x1)
	{
		x = x1;
		y = y1;

		name = "wolf";
		speech = "Woof!";
		 hunger = 4;
		walk_speed = 3;
		display_char = 'W';
	}
};

