import java.util.*;
import java.util.Scanner;

class Guess{
    static final int EASY=3;
    static final int INTERMEDIATE=2;
    static final int HARD=1;
    static Scanner in = new Scanner(System.in);
    static Random r=new Random();
    static Map<String,Integer> scores=new HashMap<String,Integer>();

    public static void printScores(){
     System.out.println("----------------");
     System.out.println("ScoreBoard");
     System.out.println("----------------");
     scores.forEach(
            (key, value)
                -> System.out.println(key + "<--->" + value));
    System.out.println("----------------");
    }

    /*calculates scores based on the no.of attempts*/
    public static void calculate_score(String name,int attempts)  {

	        if (!scores.containsKey(name)||scores.get(name)<(100-attempts))
                 scores.put(name,100-attempts);

            System.out.println("Score:"+(100-attempts));

    }


    /* takes number and validates and keeps count of no.of attempts*/
    public static boolean round(String name){ 
        int attempts=0;
        int number=r.nextInt(100);
        String guess_num="";
        int in_num=0;
	   
        do{
           
            System.out.print("Enter (your Guess/quit):");
            guess_num=in.next().trim();
            if(guess_num.equals("quit"))
                  {
                    System.out.println("The number was "+ number);
                    System.out.println( name +" has quit the game!");
		            return false;
                  }
            try{
                    in_num=Integer.parseInt(guess_num);
                    if(in_num>number)
                        System.out.println("Lower!");
                    else if(in_num<number)
                            System.out.println("Higher!");
                    else{
                        System.out.println("Thats correct! You have guessed the number in "+(attempts+1)+" attempt(s)");
                        break;
                    }
                    attempts+=1;
                       
            }
            catch(NumberFormatException e){
                    System.out.println("Only integer input allowed!");
            }
		 
        }while(true);

        Guess.calculate_score(name,attempts);
        return true;
    }
    
    public static void play(){

        
        int players,noOfPlayers,level;
        int round,noOfRounds;
        String name;
        
        System.out.print("Levels:\n1.EASY\n2.INTERMEDIATE\n3.HARD\nSelect your level:");
        try{
        level=in.nextInt();
        } 
        catch(Exception e){level=1;}
        switch(level){
            case 1:
                  noOfRounds=EASY;
                  break;
            case 2:
                 noOfRounds=INTERMEDIATE;
                 break;
            case 3:
                 noOfRounds=HARD;
                 break;
            default: 
                    noOfRounds=EASY;                   
        }
     
        System.out.print("Enter no.of players:");
        while(true){
        try{
        noOfPlayers=in.nextInt();
        break;
        }
        catch(Exception e){
            System.out.println("Invalid input!");
        }
        }

        players=noOfPlayers;
        while(players>0){
                round=0;
                System.out.print("Enter name:");
                name=in.next();
                while(round<noOfRounds){
                    System.out.println("Round "+(round+1)+" :");
                    if(!Guess.round(name)) break;
                    round++;
                }
                Guess.printScores();
                players--;
        }
    }
    public static void main(String[] args){

        int choice;
        while(true){
        System.out.println("Main Menu:\n1.Start Game\n2.ScoreBoard\n3.Exit");
        System.out.print("Enter your choice:");
        try{
                choice=in.nextInt();
                switch(choice){
                        case 1: Guess.play();
                                break;
                        case 2: Guess.printScores();
                                break;
                        case 3: return;
                                
                        default:
                                System.out.println("Invalid Choice!");
                        }
            }
        
        catch(Exception e)
         { 
            System.out.println("Only intergers are allowed!");
         }
        }
    }

}