package java.io.StreamExample_Players;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RosterWithStreams1 implements RosterWithStream 
{
	private Set<Player> players=new HashSet<Player>();    // represents a set of Player in this Roster
	
	public RosterWithStreams1()
    {
    	// do nothing
    }
    
    // Primary Constructor for Rosters
    public RosterWithStreams1(Set<Player> p)
	{
		this.players=p;
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
 		if (!(obj instanceof RosterWithStream))
 			return false;
 		RosterWithStreams1 other = (RosterWithStreams1) obj;
 		if (players == null) {
 			if (other.players != null)
 				return false;
 		} else if (!players.equals(other.players))
 			return false;
 		return true;
 	}
    
 	// RETURNS: the to_String() value of the Roster with Players in the Roster
 	@Override
 	public String toString() {
 		return "RosterWithStreams [players=" + players + "]";
 	}
 	
 	
 	// GIVEN: A Player 
 	// RETURNS: a roster consisting of the given player together
    // with all players on this roster.
    // Example:
    //     r.with(p).with(p)  =>  r.with(p)
 	
 	public RosterWithStream with(Player p) 
	{	
		Set<Player> players_temp=new HashSet<Player>();					
		players_temp.addAll(this.players);
		if(players.contains(p))
		{
			return this;
		}
		else
		{
			players_temp.add(p);		
			RosterWithStreams1 r=new RosterWithStreams1(players_temp);
			return r;
		}
		
		
	}
 	
 	// GIVEN: A Player
 	// RETURNS: a roster consisting of all players on this roster
    // except for the given player.
    // Examples:
    //     Rosters.empty().without(p)  =>  Rosters.empty()
    //     r.without(p).without(p)     =>  r.without(p)
 	public RosterWithStream without(Player p) 
 	{
 		Set<Player> players_temp=new HashSet<Player>();
 		players_temp.addAll(this.players);
 		if(!players.contains(p))
		{
			return this;
		}
 		else
 		{
 			players_temp.remove(p);
 	 		RosterWithStreams1 r=new RosterWithStreams1(players_temp);
 	 		return r;
 		}
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
 		return players.contains(p);
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
		return players.size();		
	}
    
	// RETURNS: the number of players on this roster whose current
    // status indicates they are available.
	public int readyCount() 
	{
		return readyRoster().size();
	}
	
	
	// RETURNS: a roster consisting of all players on this roster
    // whose current status indicates they are available.
	public RosterWithStream readyRoster() 
	{		
		Set<Player> players_temp=
	     this.players.stream()
		.filter(players -> players.available())		
		.collect(Collectors.toCollection(HashSet::new));
		
		RosterWithStreams1 r=new RosterWithStreams1(players_temp);
		
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
		Set<Player> players_temp=new HashSet<Player>();
		players_temp.addAll(this.players);
		RosterWithStreams1 r=new RosterWithStreams1(players_temp);
		List<Player> p=new ArrayList<Player>();
		p.addAll(r.players);
		List<Player> psort=
			     p.stream()				
				.sorted((p1,p2) -> p1.name().compareTo(p2.name()))
				.collect(Collectors.toList());	
		
		Iterator<Player> itr=psort.iterator();
		
		/*for (Iterator<Player> iterator = psort.iterator(); iterator.hasNext(); ) 
		{
		    System.out.println(iterator.next().name());
		} */
		return itr;
	} 
	
	// RETURNS: a sequential Stream with this RosterWithStream
    // as its source.
    // The result of this method generates each player on this
    // roster exactly once, in alphabetical order by name.
    // Examples:
    //
    //     RosterWithStreams.empty().stream().count()  =>  0
    //
    //     RosterWithStreams.empty().stream().findFirst().isPresent()
    //         =>  false
    //
    //     RosterWithStreams.empty().with(p).stream().findFirst().get()
    //         =>  p
    //
    //     this.stream().distinct()  =>  this.stream()
    //
    //     this.stream().filter((Player p) -> p.available()).count()
    //         =>  this.readyCount()
	public Stream<Player> stream ()
	{
		return StreamSupport.stream(
				this.spliterator(),
				false);
			
	}
	
	
	
}
