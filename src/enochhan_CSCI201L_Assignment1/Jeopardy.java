package enochhan_CSCI201L_Assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jeopardy {
	
	public static void main(String[] args) {
		String[] categorynames = new String[5];
		String[] points = new String[5];
//-------------------------Reading the File---------------------------------------------
		String filename = args[0];
		List<String> words = new ArrayList<String>();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String str;
				while((str = reader.readLine()) != null)
				{
					words.add(str);
				}
				reader.close();
		} catch (IOException e){
			System.out.println("Invalid file name/path.");
			e.printStackTrace();
			System.exit(0);
		}
//-------------------------Now Check if the file is in the correct format---------------------------
		String[] allstrings = new String[words.size()];
		String[] questions = new String[words.size()];
		String[] answers = new String[words.size()];
		for(int i = 0; i<words.size();i++){
			allstrings[i] = words.get(i);
			String[] tempstrings = words.get(i).split("::");	//Parse lines with split, to rid of "::"
			//---------------General Check if there are extra or missing ':'------------------------
//			for(int k = 0;k<tempstrings.length;k++){
//				System.out.println(tempstrings[k]);
//			} // This loops through each of the questions and prints each individual ones without the colons.
			for(int j = 0; j<tempstrings.length;j++){
				int location = tempstrings[j].indexOf(':');
				if(location >= 0){
					System.out.println("File Error: Incorrect Format. (':')");
					System.exit(0);
				}
			}
			//--------------Checking line 0 on its own----------------------------------------------
			if(i == 0){
				if(tempstrings.length != 5){
					System.out.println("File Error: Not correct amount of Categories.");
					System.exit(0);
				}
				for(int j = 0; j< tempstrings.length;j++){
					if (!(tempstrings[j].matches(".*\\w.*"))){
						System.out.println("File Error: Missing category");
						System.exit(0);
					}
					categorynames[j] = tempstrings[j];
					for(int k = j+1;k< tempstrings.length;k++){
						if (tempstrings[j].toUpperCase().equals(tempstrings[k].toUpperCase())){
							System.out.println("File Error: Duplicate Categories");
							System.exit(0);
						}
					}
				}
			} 
			//-------------Checking line 1 on its own------------------------------------------------
			else if (i == 1){
				if(tempstrings.length != 5){
					System.out.println("File Error: Not correct amount of Categories.");
					System.exit(0);
				}
				for(int j = 0; j<tempstrings.length;j++){
					if (!(tempstrings[j].matches(".*\\w.*"))){
						System.out.println("File Error: Missing points");
						System.exit(0);
					}
					points[j] = tempstrings[j];
					for(int k = j+1;k< tempstrings.length;k++){
						if (tempstrings[j].equals(tempstrings[k])){
							System.out.println("File Error: Duplicate Points.");
							System.exit(0);
						}
					}
				}
			}
			//-------------Checking if there is a perfect amount of colons----------------------------
			
//			******** This is for something of lines after . 
//			int dualcolcounter = 0;
//			for(int l = 0;l < words.get(i).length();l++){
//				//find how many ':' in the lines. Actually, might have to do this after.
//			}
			
			//-----------Now to check all the other lines including FJ--------------------------------
			if( (i > 1) && (words.size()<29)){	
				if(tempstrings[1].equals("FJ")){	//if you find FJ
					if(tempstrings.length != 4){
						System.out.println("File Error: FJ question format incorrect in line " + (i+1));
						System.exit(0);
					} if (!(tempstrings[2].matches(".*\\w.*"))){
						System.out.println("File Error: Missing question, line " + (i+1));
						System.exit(0);
					} if (!(tempstrings[3].matches(".*\\w.*"))){
						System.out.println("File Error: Missing answer, line " + (i+1));
						System.exit(0);
					}
				} else {	//uses tempstrings to complete
					if(tempstrings.length != 5){
						System.out.println("File Error: Question format incorrect in line " + (i+1));
						System.exit(0);
					}
					boolean category = false;
					boolean pointss = false;
					for(int n = 0; n< categorynames.length;n++){
						if(tempstrings[1].toUpperCase().equals(categorynames[n].toUpperCase())){
							category = true;
						} if(tempstrings[2].equals(points[n])){
							pointss = true;
						}
					}
					if(!category){
						System.out.println(tempstrings[1]);
						System.out.println("File Error: Category Name does not match, line " + (i+1));
						System.exit(0);
					} else if(!pointss){
						System.out.println(tempstrings[2]);
						System.out.println("File Error: Points does not match, line " + (i+1));
						System.exit(0);
					}
					questions[i] = tempstrings[3];
					answers[i] = tempstrings[4];
					if (!(answers[i].matches(".*\\w.*"))){
						System.out.println("File Error: Missing answer, line " + (i+1));
						System.exit(0);
					} else if (!(questions[i].matches(".*\\w.*"))){
						System.out.println("File Error: Missing question, line " + (i+1));
						System.exit(0);
					}
				}
			} 
			
			else if ((i > 1) && (words.size() > 28)){ //Use allstrings to complete this part of the parse
				//Go through Lengthy Check including adding the continuation of a question on another line back onto the same line
				
			}
			
		}//end of out most for loop
		
		for(int r = 2; r< questions.length;r++){
			//arbitrary forloop for anything
		}
		
		//----------------------------- this is a loop of the information in a String array----------
//		for(int k = 0;k<allstrings.length;k++){
//			System.out.println(allstrings[k]);
//		}
//---------------------------------------------------------------------------------------------------
		
//------------------------------Prompting User Before Game-------------------------------------------
		System.out.println("Welcome to Jeopardy!");
		System.out.println("Please enter the number of teams that will be playing in this game (1-4): ");
		Scanner sc = new Scanner(System.in); 
		String x = sc.nextLine();
		int i = Integer.valueOf(x);
		String[] teamnames = new String[i];
		while(i>4 || i<1){
			System.out.println("Invalid entry; Please Try again.");
			i = sc.nextInt();
		}
		Scanner teamname = null;
		for(int k = 0;k<i;k++){
			System.out.println("Please choose a name for Team " + (k+1) + ":");
			teamname = new Scanner(System.in);
			teamnames[k] = teamname.nextLine();
		}
		teamname.close();
		System.out.println("Thank you! Setting up the game for you..");
		sc.close();
//----------------------------Beginning of Jeopardy Game----------------------------------------------
		Random firstteam = new Random();
		int firsttogo = firstteam.nextInt(i)+1;
		System.out.println("Ready! \n     Randomizing first team");
		System.out.println("First team is... " + teamnames[firsttogo-1]);
		int teamturn = firsttogo-1;
		int availablequestions = 25;
		int[] scores = new int[i];
		while(availablequestions > 0){
			availablequestions = PromptJeopardy.Prompt(availablequestions);
			
			
			PromptJeopardy.printScore(first, second, third, fourth);
			teamturn = PromptJeopardy.teamturn(teamturn, i);
		}
		
		
		//Final Jeopardy should be done after this line and after the While loop.
	} //end of pub static void main
}
