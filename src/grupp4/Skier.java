package grupp4;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class Player {

	   String firstName ;
	   	   

	   public Player(String firstName) {
	       this.firstName = firstName;
	       
	       
	   }

	   public String toString(){
	       return firstName ;
	   }
	}

class Number {
	   String Number ;

	   ArrayList<Player> players = new ArrayList<Player>() ; ;

	   public Number(String Number){
	       this.Number = Number;
	   }

	   public void addPlayer(Player player) {
	       players.add(player);
	   }

	   public String getNumber(){
	       return Number;
	   }

	   public ArrayList<Player> getPlayers(){
	       return  players ;
	   }
	}

public class Skier {

	   HashMap<String ,Number> numbers = new LinkedHashMap<String ,Number>();


	   public void addNumber(String Number) {
	      Number number =  new Number(Number) ;
	      numbers.put(Number ,number);
	   }

	   public void addPlayer(String Number,String firstName) {
	       Player player = new Player(firstName);
	       Number number = numbers.get(Number) ;
	       number.addPlayer(player);
	   }

	   public void print(){
	       for(Map.Entry<String , Number> entry : numbers.entrySet() ) {
	           Number number = entry.getValue();
	           System.out.println(number.getNumber()  + number.getPlayers().toString());	           
	       }
	   }	      

	   public static void main (String args[]) throws Exception{

	       Skier game = new Skier();       
	       
	       game.addNumber("1");
	       game.addNumber("2");
	       game.addNumber("3");
	       game.addNumber("4");
	       game.addNumber("5");
	       
	       game.addPlayer("1","Robert");
	       game.addPlayer("2","Frank");
	       game.addPlayer("3","Åsa");
	       game.addPlayer("4","Bert");
	       game.addPlayer("5","Emma");
	       
	       game.print();

	   }
	}