package java.io.IteratorExample_Players;

// A Roster is an object of any class that implements the Roster interface.
// Rosters.empty() is a static factory method that returns an
// empty roster.


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

final public class Rosters implements Roster 
{
	 private HashSet<Player> players=new HashSet<Player>();    // represents a set of Player in this Roster
	 
	 // Constructor for Rosters
     private Rosters()
     {
     	// do nothing
     }
     
     // Primary Constructor for Rosters
     private Rosters(HashSet<Player> p)
 	{
 		this.players=p;
 	}
    
    // Static factory method
    // RETURNS: An empty Roster
    // EXAMPLE: Roster.empty() -> Returns an empty Roster
	public static Roster empty()
    {
    	return new Rosters();
    }

	// RETURNS: the hashcode of this Roster
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		return result;
	}

	// GIVEN: an object
	// RETURNS: true if and only if
    // every player on given Roster is also on this Roster, and
    // every player on this Roster is also on given Roster.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Roster))
			return false;
		Rosters other = (Rosters) obj;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		return true;
	}

	// GIVEN: A Player 
	// RETURNS: a roster consisting of the given player together
    // with all players on this roster.
    // Example:
    //     r.with(p).with(p)  =>  r.with(p)
	public Roster with(Player p) 
	{	
		HashSet<Player> players_temp=new HashSet<Player>();
					
		players_temp.addAll(this.players);
		players_temp.add(p);
		//System.out.println("INSIDE PLAYERS LIST TEMP:"+players_temp.size());
		
		Rosters r=new Rosters(players_temp);
		return r;
		
	}
	
	// GIVEN: A Player
	// RETURNS: a roster consisting of all players on this roster
    // except for the given player.
    // Examples:
    //     Rosters.empty().without(p)  =>  Rosters.empty()
    //     r.without(p).without(p)     =>  r.without(p)
	public Roster without(Player p) 
	{
		HashSet<Player> players_temp=new HashSet<Player>();
		players_temp.addAll(this.players);
		players_temp.remove(p);
		Rosters r=new Rosters(players_temp);
		return r;
	}
	
	// RETURNS: the to_String() value of the Roster with Players in the Roster
	@Override
	public String toString() {
		return "Rosters [players=" + players + "]";
	}

	// GIVEN: A Player 
	// RETURNS: true iff the given player is on this roster.
    // Examples:
    //
    //     Rosters.empty().has(p)  =>  false
    //
    // If r is any roster, then
    //
    //     r.with(p).has(p)     =>  true
    //     r.without(p).has(p)  =>  false	
	public boolean has(Player p) 
	{
		HashSet<Player> players_temp=new HashSet<Player>();
		players_temp.addAll(this.players);
		Rosters r=new Rosters(players_temp);
		return r.players.contains(p);
		//return false;
	}
	
	
	// RETURNS: the number of players on this roster.
    // Examples:
    //
    //     Rosters.empty().size()  =>  0
    //
    // If r is a roster with r.size() == n, and r.has(p) is false, then
    //
    //     r.without(p).size()          =>  n
    //     r.with(p).size               =>  n+1
    //     r.with(p).with(p).size       =>  n+1
    //     r.with(p).without(p).size()  =>  n

	public int size() 
	{
    	HashSet<Player> players_temp=new HashSet<Player>();
		players_temp.addAll(this.players);
		Rosters r=new Rosters(players_temp);
		return r.players.size();
		
		//return 0;
	}
	
	
	// RETURNS: the number of players on this roster whose current
    // status indicates they are available.
	public int readyCount() 
	{
		HashSet<Player> players_temp=new HashSet<Player>();
		players_temp.addAll(this.players);
		Rosters r=new Rosters(players_temp);
		return r.readyRoster().size();

	}

	// RETURNS: a roster consisting of all players on this roster
    // whose current status indicates they are available.
	public Roster readyRoster() {
		HashSet<Player> players_temp=new HashSet<Player>();
		players_temp.addAll(this.players);
		Rosters r=new Rosters(players_temp);
		for (Iterator<Player> iterator = r.players.iterator(); iterator.hasNext(); ) {
		    Player value = iterator.next();
		    if (!value.available()) {
		        iterator.remove();
		    }
		}
		/*
		for(Player p:players)
		{
			System.out.println(p.name());
		}*/
		return r;
		
	}
	
	// RETURNS: an iterator that generates each player on this
    // roster exactly once, in alphabetical order by name.
	public Iterator<Player> iterator() 
	{
		HashSet<Player> players_temp=new HashSet<Player>();
		players_temp.addAll(this.players);
		Rosters r=new Rosters(players_temp);
		List<Player> p=new ArrayList<Player>();
		List<Player> psort=new ArrayList<Player>();
		p.addAll(r.players);
		psort=sortPlayers(p);
		
		Iterator<Player> itr=psort.iterator();
		
		/*for (Iterator<Player> iterator = psort.iterator(); iterator.hasNext(); ) 
		{
		    System.out.println(iterator.next().name());
		} */
		return itr;
	}
	
	// GIVEN: A list of Player
	// RETURNS: the list of Player in sorted order of their name
	private List<Player> sortPlayers(List<Player> players)
	{
		
		Collections.sort(players, new Comparator<Player>()
		{
   		 public int compare(Player p1, Player p2) 
   		 {
   			 return p1.name().compareTo(p2.name());
   		 }
		});
		return players;
	}
	
	
	public static void main (String[] args)
	{
		Roster r=new Rosters();
		Roster r2=new Rosters();
		Roster r3=new Rosters();
		ArrayList<Player> p=new ArrayList<Player>();
		p.add(null);
		//r.with(p.get(0));
		
		Player pl1=new Players("ABC");
		Player pl2=new Players("BCD");
		Player pl3=new Players("CDE");
		Player pl4=new Players("ABC");
		Player pl5=new Players("bcd");
		
///////////////////////////////////////TESTS/////////////////////////////////////////////////////////////////		
		
		assert Rosters.empty().size()==0 : "The size of empty Roster is 0";
		assert r.with(pl1).with(pl1).equals(r.with(pl1)) : "The Player should be added only once if equal";
		assert r.with(pl1).with(pl2).size()==2 : "The size of Roster is 2";
		Roster a=new Rosters();
		a= r.with(pl1).with(pl2);
		assert a.size()==2 : "The size of Roster is 2";
		assert Rosters.empty().without(pl2).equals(Rosters.empty()) : "Returns an empty Roster";
		assert a.without(pl2).size()==1 : "The size of Roster is 1 without a Player";
		assert a.has(pl1)==true : "Returns true since the Player exists";
		assert a.without(pl2).has(pl2)==false : "Returns false since the Player does not exist";
		assert a.readyCount()==2 : "The number of players available in this Roster are 2";
		assert a.size()==2 : "The number of players iin this Roster are 2";
		pl2.changeSuspendedStatus(true);
		assert a.readyCount()==1 : "The number of players available in this Roster is 1";
		assert a.size()==2 : "The number of players in this Roster is 2";
		assert a.readyRoster().size()==1 : "The number of players available in this Roster is 1";
		
		Roster b=new Rosters();
		Roster c=new Rosters();
		
		b= r2.with(pl3).with(pl4);
		c= r3.with(pl4).with(pl3);
		assert b.equals(c)==true : "The two Rosters are equal";
		pl3.changeInjuryStatus(true);
		assert b.equals(c)==true : "The two Rosters are equal";
		assert b.hashCode()==(c.hashCode())==true : "The hashcode of two Rosters should be equal"
				+ "since the two Rosters are equal";
		assert b.toString().equals(c.toString())==true : "The toString() of both Rosters should be same";
		c=r3.with(pl3).with(pl5);
		assert b.equals(c)==false : "The two Rosters are not equal";
		
		Roster d=new Rosters();
		Roster e= new Rosters();
		e= r2.with(pl1).with(pl2).with(pl3);
		d= r3.with(pl3).with(pl2).with(pl1);
		Iterator<Player> id= d.iterator();
		Iterator<Player> ie= e.iterator();
		ArrayList<String> ae=new ArrayList<String>();
		ArrayList<String> ad = new ArrayList<String>();
		while(id.hasNext())
			ad.add(((Player) id.next()).name());
		while(ie.hasNext())
			ae.add(((Player) ie.next()).name());
		
		assert ad.equals(ae) == true : "The two Rosters contain equal Players";
		
		///////////////////object equals/////////////////////
		Roster r4=(Rosters)r3;
		assert r4.equals(r3)== true: "The two Rosters are equal";
		Roster r5=new Rosters();
		assert r5.equals(null)==false : "The object is null";
		Roster f= new Rosters();
		f=r4.with(pl1);
		
		assert r5.equals(pl1)==false : "The object is not an instance of Roster";
		assert r5.equals(f)==false : "The Roster does not contain equal Player";
		System.out.println("All tests passed");
		
		
	
		
	}

}
