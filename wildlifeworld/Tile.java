//encapsulates a tile in a 2D world.  Each tile has terrain (tile_data) and a potential entity.

package wildlifeworld;

public class Tile
{
	private Entity entity;
	private int tile_data;

	public Tile()
	{
		tile_data = 0;
		entity = null;
	}

	public void setEntity(Entity e1)
	{
		entity = e1;
	}

	public Entity getEntity()
	{
		return entity;
	}

	public void setTileData(int d1)
	{
		tile_data = d1;
	}

	public int getTileData()
	{
		return tile_data;
	}
}
