package game;

import com.base.engine.core.math.Vector3f;

public class MainRoom extends Room 
{

	public MainRoom(Vector3f position, int index) 
	{
		super(position, index);
		// TODO Auto-generated constructor stub
		
		setConnection(1,1,1,1);
	}

}