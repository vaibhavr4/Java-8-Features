package java.io.StreamExample_Players;

// A Player object represents a member of a team.
// Player objects are mutable because their status can change without
// changing the identity of the Player.

public class Player1 implements Player
{
	private String name;							//represents the name of the player
	Players p=new Players();
	
	// Constructor template for Player
	
	Player1(String name)
	{
		this.name=name;
	} 
	
	
	// RETURNS: the name of the Player
	public String name ()
	{
		return this.name;
	}
	
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
		return (underContract() && (!isInjured()) && (!isSuspended()) );		
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
		return p.getContract();
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
    	return p.getInjured();
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
    	return p.getSuspend();
    }

    // GIVEN: the new status of the Player's contract
    // Changes the underContract() status of this player
    // to the specified boolean.

    public void changeContractStatus (boolean newStatus)
    {
     	p.setContract(newStatus);    	
    }

    // GIVEN: the new status of the Player's injury status
    // Changes the isInjured() status of this player
    // to the specified boolean.

    public void changeInjuryStatus (boolean newStatus)
    {    	
    	p.setInjured(newStatus);
    }

    // GIVEN: the new status of the Player's suspended status
    // Changes the isSuspended() status of this player
    // to the specified boolean.

    public void changeSuspendedStatus (boolean newStatus)
    {    	
    	p.setSuspend(newStatus);
    }

    
////////////////////////////////////////////////TESTS////////////////////////////////////////////////////////
    
    public static void main (String[] args)
    {
    	Player one = new Player1 ("BATMAN");
   	    Player two= new Player1("FLASH");
   	    Player three= new Player1("JOKER");
   	    Player four= Players.make("GOKU");
   	    Player eight= Players.make("XYZ");
   	 
   	    Player five=new Player1("ABC");
   	    Player six= new Player1("ABC");
   	    Player seven=(Player1)five;
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
