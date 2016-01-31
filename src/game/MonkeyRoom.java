package game;

import com.base.engine.core.math.Vector3f;

public class MonkeyRoom extends Room 
{
	private static int roomIterator = 0;
	
	private enum MonkeyRoomTypes
	{
		PLACEBO, TIME, GREED, CHAMELEON
	}
	
	private final int ENUMSIZE = MonkeyRoomTypes.values().length;
	
	public MonkeyRoom(Vector3f position, int xPos, int yPos) 
	{	
		super(position,xPos,yPos);
		
		roomType = "m";
		
		handleConnectors();
		
		roomIterator += 1;
	}
	
	@Override  
	public void handleConnectors()
	{
		if(roomIterator == 0)
		{
			setConnection(1,0,1,0);
		}
		else if(roomIterator == 1)
		{
			setConnection(1,1,1,1);
		}
		else if(roomIterator == 2)
		{
			setConnection(1,0,1,0);
		}			
		else if(roomIterator == 3)
		{
			setConnection(1,1,1,1);
		}
		else if(roomIterator == 4)
		{
			roomIterator = 0;
		}
	}
	
	public String toString()
	{
		return "M" + super.toString();
	}
}
