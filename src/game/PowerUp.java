package game;

import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Texture;

public class PowerUp extends Gear
{
	
	public PowerUp (float x, float y, float z)
	{
		super(x, y, z);
	}
	
	public void PowerUp1()
	{
		Material m = new Material();
		m.addTexture("Testing", new Texture("brown.png"));
		
		Mesh n = new Mesh("");

	}
	
	public String toString()
	{
		return "Power-Up";
	}
}