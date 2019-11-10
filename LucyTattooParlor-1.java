import java.util.Scanner;
public class LucyTattooParlor {
	
	//by Yvette Williamson
	public static void main(String[] args){
		//Initialization of variables
		String name = null;
		String tattoo = null;
		int minutes = 0;
		Scanner input = new Scanner(System.in);
		
		//Initialization of variables of 2D array
		//Variables set dimensions of 2D array
		int numSpecs = Integer.parseInt(args[0]);
		int numSpecs2 = Integer.parseInt(args[1]);
		int additionsList= numSpecs*numSpecs2+2;
		
		//Initializations for keeping track of artist number and adding of customer
		int numForArtist;
		boolean custAdded=false;

		//2D array is set
		TattooCustomer [][] a = new TattooCustomer [numSpecs][numSpecs2];
	
		//for loop for menu
			for( int i= 0; i<additionsList; i++){
				System.out.println("Please enter the customer's name:");
				name = input.nextLine();
				
				if(name.equals("Print Waitlist")){
					
					for(int s=0; s< a.length;s++){		
						System.out.print("Technician "+s+"\n");
						
						for(int t=0; t<a[s].length;t++){
							
							if(a[s][t]!=null){	
							System.out.println("\tCustomer number "+t+":\n\t\tName: "+a[s][t].getName()+"\n\t\tTattoo: "+a[s][t].getTattoo()
							+"\n\t\tMinutes: "+a[s][t].getMinutes());
							
							}else{
							
								System.out.println("\tNo Customer for slot "+t);
							}
					}
					}
					//Once list is printed, program ends
					System.exit(0);
				}
				
				//Prompts user for info on customers
				System.out.println("Please enter the customer's tattoo:");
				tattoo = input.nextLine();
				System.out.println("If they would like a specific artist, please enter yes or no:");
				String answer = input.nextLine();
				
				//If statement for answer to specific artist
				if(answer.equals("yes")){
					System.out.println("Please enter the number of artist (options 0 to "+numSpecs+") :");
					numForArtist = input.nextInt();
					input.nextLine();
					System.out.println("Please enter the minutes it will take to get tattoo done:");
					minutes = input.nextInt();
					input.nextLine();
					
					//catches minutes above 480 and prints error message
					while(minutes>480){
						System.out.println("ERROR: Please enter minutes less than 480 minutes. ");
						minutes = input.nextInt();
						input.nextLine();
					}
					
					//Customer object made
					TattooCustomer c = new TattooCustomer(name, tattoo, minutes );
					
					//calls addCustomer() with artist number
					custAdded=addCustomer(a,c,numForArtist);
					
					//If addCustomer() with artist number returns false, 
					//will print error message and let user 
					//know that the customer will be added in other artist line, the shortest one
					if(!custAdded){
						System.out.println("ERROR: The waitlist for artist "+numForArtist+" is full. Will add to shortest waitlist.");
						
						custAdded=addCustomer(a,c);
						
						//If addCustomer() returns false, then all waitlists are full
						if(!custAdded){
							System.out.println("ERROR: All waitlists are full.");
						}
						
					}
				//Other part of if statement for answer of specific artist	
				}else if(answer.equals("no")){
					System.out.println("Please enter the minutes it will take to get tattoo done:");
					minutes = input.nextInt();
					input.nextLine();
				
				//catches minutes above 480 and prints error message
				while(minutes>480){
					System.out.println("ERROR: Please enter minutes less than 480 minutes. ");
					minutes = input.nextInt();
					input.nextLine();
				}
				
				//Customer object made
				TattooCustomer c = new TattooCustomer(name, tattoo, minutes );
				
				//calls addCustomer() 
				custAdded=addCustomer(a,c);
				
				//If addCustomer() returns false, then all waitlists are full
				if(!custAdded){
					System.out.println("ERROR: All waitlists are full.");
				}
				}
				}
	
	}
	
		
		/** 
		 * Computes how many minutes of work the specified tattoo artist has.  
		 * @param The array of customers for one particular tattoo artist.
		 * @return The sum of minutes of customer.
		 */ 
		public static int computeMinutesOfWork(TattooCustomer [] a) { 
			
			//Initialization of variables 
			int sum = 0;
			
			//For loop to get minutes then add them together
				for(int r =0; r< a.length;r++){
					if(a[r]!=null)
					sum+=a[r].getMinutes();
			}
			return sum; 
			} 
	
	
			/** 
			* Adds customer to the waitlist for a specific artist. 
			* If the artist is at capacity (in terms of number of customers or minutes) 
			* Then the customer is not added and the method returns false 
			* If the customer is successfully added the method returns true 
			* @param The 2D array of waitlist, customer object, and artist number.
			* @return True if customer added to specfic artist waitlist.
			* @return False if waitlist for artist is full and customer not added.
			*/ 
	public static boolean addCustomer(TattooCustomer [][] a, TattooCustomer c, int artistNum) { 
		
		//Initialization of variables 
		int combineMin = 0;
		int minutes =0;
		int max =0;
		int combineWithCust=0;
		boolean answer =true;
		
		//Gets minutes total for artist
		combineMin= computeMinutesOfWork(a[artistNum]);
		combineWithCust = combineMin+c.getMinutes();
	
		//If statement makes sure addition of customer doesn't go over 8 hours
		if(combineWithCust>480 ){
				answer=false; 
		//If statement to catch whether artist line is full		
		}else if(a[artistNum][a[artistNum].length-1]!=null){
			answer=false;
			
		}else {
			
			//For loop and if statement to add customer to first null place
			for(int i =0; i<a[artistNum].length;i++){
				
				if(a[artistNum][i]==null){
					a[artistNum][i]=c;	
						break;	
				}
				
		
			}
		}
		
		return answer;
		
	}  
	
	
			/** 
			* Adds customer to the shortest waitlist in terms of minutes. 
			* If some artists have equal length waitlists 
			* then the customer is added to the lowest numbered artist. 
			* If no artist has space then the method does not 
			* add the customer, and returns false.  
			* @param 2D array and customer object.
			* @return false if the customer was not added to waitlist.
			* @return true if the customer was added to the waitlist, false otherwise (if all artists were full.)  
			*/ 
	public static boolean addCustomer(TattooCustomer [][] a, TattooCustomer c) {  
		
		//Initialization of variables
		int combineMin = 0;
		int lessWait = 0;
		int min =0;
		int temp = 0;
		int max =0;
		int combineWithCust=0;
		boolean answer =true;
		
		
		//For loop to find shortest waitlist
		for(int b = 0; b<a.length;b++){
			
			//Call to get sum of minutes for each artist
				combineMin= computeMinutesOfWork(a[b]);
				combineWithCust = combineMin+c.getMinutes();
				
				//Checks that addition does not go over 8 hours
				if(combineWithCust>480){
					answer=false;	
				}
				
				//Call to get sum of minutes for first artist
				min= computeMinutesOfWork(a[0]);
				
				//if statement to make sure the artist line is shortest and 
				//that there is a space available
				if(combineMin<min&& a[b][a[b].length-1]==null){
					lessWait=b;
					break;
				}
				
		}
		
//		//Once shortist time waitlist(array) is found, loops through array and adds customer
//		//to first null slot
		for(int q1=0;q1<a[lessWait].length;q1++){

			if(a[lessWait][q1]==null){
				a[lessWait][q1]=c;
				answer=true;
				break;
			}
		
		}
		return answer; 
	}
}

