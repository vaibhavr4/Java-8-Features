package java.io.IteratorExample_Players;

// A Player object represents a member of a team.
// Player objects are mutable because their status can change without
// changing the identity of the Player.

public class Players implements Player
{
	private String name;							//represents the name of the player
	private boolean contract=true;			//to represent whether the player is in contract
	private boolean injured=false;			//to represent whether the player is injured
	private boolean suspend=false;			//to represent whether the player is suspended
	
	// Constructor template for Player
	
	Players(String name)
	{
		this.name=name;
	} 
	
	// Players.make(String name) is a static factory method
	// GIVEN: a name
    // RETURNS: a player with the given name who is (initially) available.
	public static Player make(String name)
	{
		return new Players(name);
	}
	
	// RETURNS: the name of the Player
	public String name ()
	{
		return this.name;
	}


///////////////////////////getters and setters///////////////////////////////////////////////////////////
	
	
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
	
	// Returns true iff this player is
    //     under contract, and
    //     not injured, and
    //     not suspended
    // Example:
    //     Player gw = Players.make ("Gordon Wayhard");
    //     System.out.println (gw.available());  // prints true
    //     gw.changeInjuryStatus (true);
    //     System.out.println (gw.available());  // prints false
	public boolean available ()
	{
		if(underContract() && (!isInjured()) && (!isSuspended()) )
		{
			return true;
		}
		else
			return false;
	}
	
	// Returns true iff this player is under contract (employed).
    // Example:
    //     Player ih = Players.make ("Isaac Homas");
    //     System.out.println (ih.underContract());  // prints true
    //     ih.changeContractStatus (false);
    //     System.out.println (ih.underContract());  // prints false
    //     ih.changeContractStatus (true);
    //     System.out.println (ih.underContract());  // prints true
	public boolean underContract ()
	{
		return this.getContract();
	}

    // Returns true iff this player is injured.
	// Example:
    //     Player ih = Players.make ("Isaac Homas");
    //     System.out.println (ih.isInjured());  // prints false
    //     ih.changeInjuryStatus (true);
    //     System.out.println (ih.isInjured());  // prints true
    //     ih.changeInjuryStatus (false);
    //     System.out.println (ih.isInjured());  // prints false

    public boolean isInjured ()
    {
    	return this.getInjured();
    }

    // Returns true iff this player is suspended.
    // Example:
    //     Player ih = Players.make ("Isaac Homas");
    //     System.out.println (ih.isSuspended());  // prints false
    //     ih.changeSuspendedStatus (true);
    //     System.out.println (ih.isSuspended());  // prints true
    //     ih.changeSuspendedStatus (false);
    //     System.out.println (ih.isSuspended());  // prints false

    public boolean isSuspended ()
    {
    	return this.getSuspend();
    }

    // GIVEN: the new status of the Player's contract
    // Changes the underContract() status of this player
    // to the specified boolean.

    public void changeContractStatus (boolean newStatus)
    {
    	this.setContract(newStatus);
    }

    // GIVEN: the new status of the Player's injury status
    // Changes the isInjured() status of this player
    // to the specified boolean.

    public void changeInjuryStatus (boolean newStatus)
    {
    	this.setInjured(newStatus);
    }

    // GIVEN: the new status of the Player's suspended status
    // Changes the isSuspended() status of this player
    // to the specified boolean.

    public void changeSuspendedStatus (boolean newStatus)
    {
    	this.setSuspend(newStatus);
    }

    
////////////////////////////////////////////////TESTS////////////////////////////////////////////////////////
    
    public static void main (String[] args)
    {
    	Player one = new Players ("BATMAN");
   	 Player two= new Players("FLASH");
   	 Player three= new Players("JOKER");
   	 Player four= make("GOKU");
   	 Player eight= Players.make("XYZ");
   	 
   	 Player five=new Players("ABC");
   	 Player six= new Players("ABC");
   	 Player seven=(Players)five;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
   	 assert one.available()== true : "Player is available";
   	 one.changeContractStatus(false);
   	 assert one.available()== false : "Player is not available";
   	 one.changeContractStatus(true);
   	 assert one.available()== true : "Player is available";
   	 
   	 assert two.available()==true : "Player is available";
   	 two.changeInjuryStatus(true);
   	 assert two.available() == false : "Player is not available";
   	 two.changeInjuryStatus(false);
   	 assert two.available() == true : "Player is available";
   	 
   	 assert three.available()==true : "Player is available";
   	 three.changeSuspendedStatus(true);
   	 assert three.available() == false : "Player is not available";
   	 three.changeSuspendedStatus(false);
   	 assert three.available() == true : "Player is available";
   	 
   	 assert four.name() .equals("GOKU") : "Should return the name of the player";
   	 
   	 assert five.equals(seven) == true : "Return true since both players are equal";
   	 assert five.equals(six) ==false : "Return false since both players are unequal";
     assert eight.name().equals("XYZ") : "Returns the name of the Player";
   	 
	 System.out.println("All tests passed");
 
    }
}
