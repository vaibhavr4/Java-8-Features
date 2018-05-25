package java.io.StreamExample_Players;

public class Players
{
	private boolean contract=true;			//to represent whether the player is in contract
	private boolean injured=false;			//to represent whether the player is injured
	private boolean suspend=false;			//to represent whether the player is suspended
	
	
///////////////////////////getters and setters////////////////////////////////////////////////////////////////
	
	// Players.make(String name) is a static factory method
	// GIVEN: a name
	// RETURNS: a player with the given name who is (initially) available.
	public static Player make(String name)
	{
		return new Player1(name);
	}
		
		
	// GIVEN: no argument
	// RETURNS: the contract of this Player
	public boolean getContract()
	{
		return this.contract;
	}
	
	// GIVEN: no argument 
	// sets the value of contract
	public void setContract(boolean contract)
	{
		this.contract=contract;
	}
	
	// GIVEN: no argument
	// RETURNS: the injured of this Player
	public boolean getInjured()
	{
		return this.injured;
	}
	
	// GIVEN: no argument 
	// sets the value of injured
	public void setInjured(boolean injured)
	{
		this.injured=injured;
	}
	
	// GIVEN: no argument
	// RETURNS: the suspend of this Player
	public boolean getSuspend()
	{
		return this.suspend;
	}
	
	// GIVEN: no argument 
	// sets the value of suspend
	public void setSuspend(boolean suspend)
	{
		this.suspend=suspend;
	}

/////////////////////////////////////////getters and setters end////////////////////////////////////////////  

}
