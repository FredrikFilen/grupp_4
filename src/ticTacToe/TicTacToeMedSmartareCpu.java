package ticTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeMedSmartareCpu {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();		//arrayList för att spara spelarens drag
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();			//arrayList för att spara cpuns drag

	public static void main(String[] args) {
		int playerPosition = 0;	//deklaration av variabel f�r att h�lla koll p� spelarens val.
	
		//spelbr�det, 2d array f�r rader och kolumner.
		char [] [] gameBoard = 
		{{' ', '|', ' ', '|', ' '},
		 {'-', '+', '-', '+', '-' },
		 {' ', '|', ' ', '|', ' '},
		 {'-', '+', '-', '+', '-' },
		 {' ', '|', ' ', '|', ' '}};
		
		System.out.println("V�lkommen till TicTacToe!"
			+ "\nX = du \nO = CPU");
		printBoard(gameBoard); 	//printar ut spelbrädet. 
		
		//loop som k�r sj�lva spelet, l�ter spelaren och cpun spela tills br�det �r fullt eller n�gon har vunnit.
		//spelarens tur
		while(true) {
			System.out.println("Ange din placering (1 - 9): ");
			//exceptionhantering f�r input. f�ngar om input inte �r ett nummer.
			while(true) {		
				try {
					Scanner scanner = new Scanner(System.in);
					playerPosition = scanner.nextInt();
					while(playerPosition <= 0 || playerPosition > 9) {
						System.out.println("Du kan bara v�lja position med siffrorna 1 - 9, pr�va igen.");
							try {
								playerPosition = scanner.nextInt();
								
								
							}catch (InputMismatchException e) {
								scanner.nextLine();
							}
						}
				break;
				}catch (InputMismatchException e) {
						System.out.println("Du kan bara v�lja position med siffrorna 1 - 9, pr�va igen.");
				}
			}
			
			
			//Loop som kontrollerar om positionen �r upptagen, om den �r det får man pr�va igen. med exceptionhantering f�r input.
			while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
				System.out.println("position upptagen, pr�va igen");
				while(true) {
					try {
						Scanner scanner = new Scanner(System.in);
						playerPosition = scanner.nextInt();
						break;
					}catch (InputMismatchException e) {
						System.out.println("Du kan bara v�lja position med siffrorna 1 - 9, pr�va igen.");
					}
				}
			}
			
			// här placeras spelarens val på brädet.
			placePiece(gameBoard, playerPosition, "player");
			
			//efter att spelaren spelat kontrollerar programmet om någon vunnit.
			String result = checkWinner();
			if(result.length() > 0) {
				printBoard(gameBoard);
				System.out.println(result);
				break;
			}
			
			//linje för snyggare output. separerar olika "turer".
			System.out.println("--------------------------------");
			
			//datorns tur
			//Datorn går igenom alla positioner på spelbrädet. Om positionen är upptagen så prövar den nästa. Om en placering i positionen resulterar i vinst så kommer
			//datorn att spela den.
			Random rand = new Random();
			for(int i = 1; i <= 9; i++) {
				if(playerPositions.contains(i) || cpuPositions.contains(i)) {	//om platsen är upptagen så går den vidare till nästa position.
					continue;
				}
				placePiece(gameBoard, i, "cpu");	//om positionen är ledig så prövar datorn att spela den
				result = checkWinner();
				if(result.length() > 0) {	//om positionen resulterar i vinst så spelas den
					break;
				}else {
					removePiece(gameBoard, i, "cpu");	//om det inte är en vinst så positionen bort.
				}
			}
			result = checkWinner();
			if(result.length() > 0) {
				printBoard(gameBoard);
				System.out.println(result);
				break;
			}
			
			//om ingen av de möjliga positionera resulterar i vinst så slumpar datorn sitt val.
			int cpuPosition = rand.nextInt(9) + 1;
			while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) { //om den framslumpade positionen är upptagen så slumpas en ny fram.
				cpuPosition = rand.nextInt(9) + 1;
			}
			
			//placerar den slumpade positionen
			placePiece(gameBoard, cpuPosition, "cpu");
			printBoard(gameBoard);	
			
			//kollar om datorn vann med den slumpade positionen
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
		
		}
		
	}
	
	
	// metod för att kontrollera om någon har vunnit. Skapar en lista för varje positioner som resulterar i en vinst. Placerar sedan alla dom listorna i en stor lista. Sedan loopas denna igenom och 
	// jämförs med spelarens lagda positioner. Om spelarens positioner innehåller allt från en av vinstlistorna så printas det ut vem som vann. Om brädet är fullt men ingen har vunnit så
	// printas ävenmdet ut. Om ingen har vunnit så returneras en tom sträng. 
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1, 2, 3);
		List middleRow = Arrays.asList(4, 5, 6);
		List bottomRow = Arrays.asList(7, 8, 9);
		List leftColumn = Arrays.asList(1, 4, 7);
		List middleColumn = Arrays.asList(2, 5, 8);
		List rightColumn = Arrays.asList(3, 6, 9);
		List diagonal1 = Arrays.asList(1, 5, 9);
		List diagonal2 = Arrays.asList(7, 5, 3);
		
		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(middleRow);
		winningConditions.add(bottomRow);
		winningConditions.add(leftColumn);
		winningConditions.add(middleColumn);
		winningConditions.add(rightColumn);
		winningConditions.add(diagonal1);
		winningConditions.add(diagonal2);
		
		for(List l: winningConditions) {
			if(playerPositions.containsAll(l)) {
				return "Grattis du vann!";
				
			} else if(cpuPositions.containsAll(l)) {
				return "Tyvärr, datorn vann :(";
				
			}
			
			}
		if(playerPositions.size() + cpuPositions.size() == 9) {
			return "Tie, försök igen.";
		}
	
		return "";
	}
	
	
	// metod som printar ut spelbrädet, loopar igenom varje rad och kolumn.
	public static void printBoard(char [][] gameBoard) {
		
		for (char[] row: gameBoard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	// metod för att placera spelarens val. Beroende på vems tur det är så placeras rätt symbol ut på den valda positionen på brädet.
	public static void placePiece(char[][] gameBoard, int position, String user) {
	
		char symbol = ' ';
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(position);
		}
		else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(position);
		}
	
		// för varje möjlig spelbar position
		switch(position) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
				
			default:
				System.out.println("Du kan bara placera mellan 1 - 9");
		}
	}
	
	
	// metod som tar bort en spelad position från brädet. används enbart av cpu.
	public static void removePiece(char[][] gameBoard, int position, String user) {
		char symbol = ' ';

		if(user.equals("cpu")) {
			int index = cpuPositions.size() - 1;
			cpuPositions.remove(index);
		}
	
		switch(position) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
		}
	}
}

	