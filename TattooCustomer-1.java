
public class TattooCustomer {
	//by Yvette Williamson
	//Initialization of private variables
	private String name;
	private String tattoo;
	private int minutes;

	TattooCustomer(String name, String tattoo, int minutes){
		//Constructor for objects
		this.name = name;
		this.tattoo=tattoo;
		this.minutes = minutes;
	}
	
	public String getName(){
		//gets name of customer
		
		return name;
	}
	
	public String getTattoo(){
		//gets tattoo of customer
		
		return tattoo;
	}
	
	public int getMinutes(){
		//gets minutes of customer
		
		return minutes;
	}
}
