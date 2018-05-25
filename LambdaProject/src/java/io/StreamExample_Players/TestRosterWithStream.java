package java.io.StreamExample_Players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestRosterWithStream 
{
	public static void main (String[] args)
	{
		RosterWithStream r=new RosterWithStreams1();
		RosterWithStream r2=new RosterWithStreams1();
		RosterWithStream r3=new RosterWithStreams1();
		ArrayList<Player> p=new ArrayList<Player>(); 
		p.add(null);
		//r.with(p.get(0));
		
		Player pl1=new Player1("ABC");
		Player pl2=new Player1("BCD");
		Player pl3=new Player1("CDE");
		Player pl4=new Player1("ABC");
		Player pl5=Players.make("bcd");
		
///////////////////////////////////////TESTS/////////////////////////////////////////////////////////////////		
		
		assert r.with(pl1).with(pl1).equals(r.with(pl1)) : "The Player should be added only once if equal";
		assert r.with(pl1).with(pl2).size()==2 : "The size of Roster is 2";
		RosterWithStream a=new RosterWithStreams1();
		a= r.with(pl1).with(pl2);
		assert a.without(pl3).equals(a) : "The player pl1 already exists and need not be added";
		assert a.size()==2 : "The size of Roster is 2";
		//assert Rosters.empty().without(pl2).equals(Rosters.empty()) : "Returns an empty Roster";
		assert a.without(pl2).size()==1 : "The size of Roster is 1 without a Player";
		assert a.has(pl1)==true : "Returns true since the Player exists";
		assert a.without(pl2).has(pl2)==false : "Returns false since the Player does not exist";
		assert a.readyCount()==2 : "The number of players available in this Roster are 2";
		assert a.size()==2 : "The number of players iin this Roster are 2";
		pl2.changeSuspendedStatus(true);
		assert a.readyCount()==1 : "The number of players available in this Roster is 1";
		assert a.size()==2 : "The number of players in this Roster is 2";
		assert a.readyRoster().size()==1 : "The number of players available in this Roster is 1";
		
		RosterWithStream b=new RosterWithStreams1();
		RosterWithStream c=new RosterWithStreams1();
		
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
		
		RosterWithStream d=new RosterWithStreams1();
		RosterWithStream e= new RosterWithStreams1();
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
		RosterWithStream r4=(RosterWithStreams1)r3;
		assert r4.equals(r3)== true: "The two Rosters are equal";
		RosterWithStream r5=new RosterWithStreams1();
		assert r5.equals(null)==false : "The object is null";
		RosterWithStream f= new RosterWithStreams1();
		f=r4.with(pl1);
		
		assert r5.equals(pl1)==false : "The object is not an instance of Roster";
		assert r5.equals(f)==false : "The Roster does not contain equal Player";
		
		//System.out.println(a.stream().filter((Player pl10) -> pl1.available()).count());
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////Tests for Stream<Player>////////////////////////////////////////////////////////
		RosterWithStream x=new RosterWithStreams1();
		RosterWithStream y=new RosterWithStreams1();
		RosterWithStream z=new RosterWithStreams1();
		
		Player p1=new Player1("ABC");
		Player p2=new Player1("BCD");
		Player p3=new Player1("CDE");
		Player p4=new Player1("ABC");
		Player p5=new Player1("bcd");
		Player p6= (Player1) p2;
		
		RosterWithStream x1= RosterWithStreams.empty();
		x1= x.with(p1).with(p2).with(p3);
		
		p1.changeContractStatus(false);
		// Tests for anyMatch
		assert x1.stream().anyMatch(a1 ->!a1.available()) : "The Roster x1 has p1 unavailable";
		assert x1.stream().anyMatch(a1 ->a1.available()) : "The Roster x1 has p2 and p3 available";
		assert x.stream().anyMatch(a1->a1.available())==false : "Returns false since Roster is empty";
		//Tests for allMatch
		assert x1.stream().allMatch(a1 -> a1.available())==false : "The Roster x1 has p1 unavailable";
		assert x.stream().allMatch(a1->a1.available()) :"Returns true since Roster is empty";
		//Tests for count
		assert x1.stream().count() ==3: "The Roster x1 has 3 players";
		
		//Tests for distinct
		assert x1.with(p6).stream().distinct().count()==3 : "The number of distinct players are 3 in x1";
		
		//Tests for filter
		assert x1.stream().filter(a1-> a1.available()).count() == 2: "The number of available players are "
				+ "2 in x1";
		
		
		Set<Player> readyplay=new HashSet<Player>();
		for (Iterator<Player> itr=x1.readyRoster().iterator(); itr.hasNext(); ) 
		{
		    readyplay.add(itr.next());
		} 
		//Tests for filter
		assert x1.stream().filter(a1-> a1.available()).collect(Collectors.toCollection(HashSet::new))
		.equals(readyplay):"The players available are filtered";
		
		//Tests for findFirst()
		assert x1.stream().findFirst().get().equals(x1.iterator().next()) : "Returns the first player of "
				+ "Roster x1";
		//Tests for findAny()
		assert x1.has(x1.stream().findAny().get()) : "The Player returned by findAny should be available"
				+ "in Roster x1";
		//Tests for forEach()
		x1.stream().filter(a1-> a1.available())
		.forEach(player->player.changeInjuryStatus(true));
		
		assert x1.stream().filter(a1->a1.available()).count()==0 : "The status of all available players is changed to injured"
				+ "and no players are available in Roster";
		//limit
		assert x1.stream().collect(Collectors.toList()).size()==3:"There are 3 players in Roster";
		assert x1.stream().limit(2).collect(Collectors.toList()).size()==2:"The players are limited to 2";
		
		//noneMatch
		assert x1.stream().noneMatch(aa->aa.underContract())==false :"The player p1 is not under contract hence false";
		assert x1.stream().noneMatch(aa->aa.available()) :"Players p2 and p3 are available hence true";	
		
		
////////////////////////////////////////////////////////////////////////
		
		RosterWithStream y1= new RosterWithStreams1();
		y1= y.with(p4).with(p5);
		
		//Tests for anyMatch()
		assert y1.stream().anyMatch(a2 ->!a2.available())==false : "The Roster y1 has all players available";
		
		//Tests for allMatch()
		assert y1.stream().allMatch(a2 -> a2.available()) : "The players are available in Roster y1";
		
		//Tests for count()
		assert y1.stream().count() ==2 : "The Roster y1 has 2 players";
		p4.changeInjuryStatus(true);
		
		//Tests for filter()
		assert y1.stream().filter(a2-> a2.isInjured()).count()==1 : "The number of players injured in "
				+ "y1 is 1";
		
		//Tests for findFirst()
		assert y1.stream().findFirst().get().equals(y1.iterator().next()) : "Returns the first player of "
				+ "Roster y1";
		
		//Tests for findFirst()
		assert z.stream().findFirst().equals(RosterWithStreams.empty().stream().findFirst()): "Returns empty since"
				+ "Roster contains no players";
		
		//Tests for findAny()
		assert z.stream().findAny().equals(RosterWithStreams.empty().stream().findFirst()): "Returns empty since"
		+ "Roster contains no players";
		
		//Tests for findAny()
		assert y1.stream().findAny().get().equals(y1.stream().findFirst().get()) : "Returns the first player of "
		+ "Roster y1";
		
		//Tests for filter() //forEach()
		y1.stream().filter(a2-> a2.isInjured())
			.forEach(player->player.changeInjuryStatus(false));
		assert y1.stream().filter(a2-> a2.isInjured()).count()==0 : "The status of injured player is changed "
				+ "and no player is injured";
		
		//Tests for skip()
		assert y1.stream().skip(2).count()==0 : "There are only 2 players in Roster y1";
		assert y1.stream().skip(2).collect(Collectors.toList()).equals(Arrays.asList()) :"Returns empty since"
				+ "Roaster consists of only 2 players";
		assert y1.stream().skip(1).count()==1 : "There is 1 player after skipping the first";	
		assert y1.stream().skip(1).collect(Collectors.toList()).equals(Arrays.asList(p5)) : "The Roster y1"
				+ "consists of only Player p5";
		
		Player p7=new Player1("CDE");
		Player p8=new Player1("ABC");
		RosterWithStream z1= new RosterWithStreams1();
		z1= z.with(p7).with(p8);
		
		//Tests for toArray()
		assert z1.stream().toArray().length==z1.size() : " The Roster z1 contains 2 players";
		
		List<Object> checkarray=new ArrayList<Object>();
		for(Object play:z1.stream().toArray())
		{
			checkarray.add(play);
		}
		assert checkarray.equals(Arrays.asList(p8,p7)) : " The players of Roster are stored in an array";
		//Tests for map()
		assert z1.stream().map(az->az.name()).collect(Collectors.toList()).equals
		(Arrays.asList(p8.name(),p7.name())) : "The name of the Players in Roster is returned";
		
		//Tests for map()
		assert z1.stream().map(az->az.available()).collect(Collectors.toList()).equals
		(Arrays.asList(p8.available(),p7.available())) : "The individual status of Players is returned";
		
		//Tests for reduce()
		assert z1.stream().reduce((play1,play2)->Players.make(p7.name()+p8.name())).get().name().equals
				("CDEABC") :"The concatenated name of Players in Roster is CDEABC";
		
		
 		System.out.println("All tests passed");
		

}
	
}
