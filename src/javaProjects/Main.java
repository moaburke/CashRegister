package javaProjects;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
  //Pseudocode
  /**
  * The program works s a simple cash register system which manage articles, sales and order history. The program offers the following key features:
  * 1. Insert articles - Add new articles to the inventory with randomized quantities and prices.
  * 2. Delete articles - Delete articles from the inventory based on user input.
  * 3. Display article list - View a table of articles, sorted in ascending order by article number.
  * 4. Register sale - Allow the user to register a sale by entering the article number and quantity sold.
  * 5. Display order history - View a list of sales, sorted in descending order by date and time.
  * 6. Sort and display order history table - Print a sorted table of sales, sorted in ascending order by article number.
  * If q is entered exit program
  *
  *
    *
    *@author Moa Burke
    * 19 Sept 2024
    */

  //Declaration of scanner object
  private static Scanner userInput = new Scanner(System.in);

  public static void main(String[] args) {
	  //Initialize a 2D array for articles with columns representing article number, quantity and price
	  int[][] articles = new int[10][3];

	  //Initialize a 2D array for sales with columns representing article number, quantity and total price
	  int[][] sales = new int[1000][3];

	  ///Initialize an array to store dales dates corresponding to sales entries
	  Date[] salesDate = new Date[1000];

	  //Initialize the starting article number
	  int articleNumber = 1000;

	  //Continue the loop until user chooses to exit program
	  while (true) {
		  // Call method to get the menu selection
		  int getChoice = menu();
		  System.out.println(""); // Empty line for aesthetics

		  //Switch statement to handle different menu choices
		  switch (getChoice) {
		  case 1:
			  // Prompt the user to enter the number of articles to add
			  System.out.print("How many articles do you want to add? "); 
  
			  boolean isValid = false; //Flag to indicate if the input is valid
			  int numOfArticles = 0; //variable to store the number of articles to be added

			  //Do-while loop to repeatedly get input until a valid number is entered
			  do {
				  //Call the input method to get the user input for the number of articles to be added
				  numOfArticles = input();
				  //Check if the entered number is greater than 0
				  if (numOfArticles > 0) {
					  isValid = true; //Set the flag to true if the input greater than 0
				  } else {
					  //Display an error message is the input is invalid
					  System.out.println("Invalid input. Please enter a valid number.");
					  isValid = false; //Set the flag to false to loop again
				  }
			  } while(!isValid); //Continue the loop until a valid input is entered
      
			  //Check if there are enough slots in the articles array, expand if necessary
			  articles = checkFull(articles, numOfArticles);
			  //Insert a specified number of articles into the articles array
			  insertArticles(articles, articleNumber, numOfArticles);
			  //Update the article number for the next set of articles
			  articleNumber = articleNumber + numOfArticles;
			  System.out.println(""); // Empty line for aesthetics
			  break;
		  case 2:
	          //Call method to remove article
	          removeArticle(articles);
	          System.out.println(""); // Empty line for aesthetics
	          break;
		  case 3:
	          //Print a header for the article table
	          System.out.println("----------------------------------");
	          System.out.println(" # Article Table");
	          System.out.println("----------------------------------");
	          //Call method to print the table of articles
	          printArticles(articles);
	          System.out.println(""); // Empty line for aesthetics
	          break;
		  case 4:
	          //Call method to sell an article and update sales data
	          sellArticle(sales, salesDate, articles);
	          System.out.println(""); // Empty line for aesthetics
	          break;
		  case 5:
	          //Print header for the sales data
	          System.out.println("----------------------------------");
	          System.out.println(" # Sales Data");
	          System.out.println("----------------------------------");
	          //Call method to print the sales data
	          printSales(sales, salesDate);
	          System.out.println(""); // Empty line for aesthetics
	          break;
		  case 6:
	          //Print header for the sorted order history data
	          System.out.println("----------------------------------");
	          System.out.println(" # Sorted order history");
	          System.out.println("----------------------------------");
	          //Call method to print the sorted sales data
	          sortedTable(sales, salesDate);
	          System.out.println(""); // Empty line for aesthetics
	          break;
		  case -1:
			  System.out.println("Goodbye!");
			  System.exit(0);
		  default:
			  //Do nothing if an invalid choice is entered
			  break;
		  }
	  }
  } //end of main method

  /**
  * Displays menu options for the user and returns a number representing their choice.
  *
  * @return A number that represents the user's choice. Returns 7 if the user wants to exit the program.
  */
  public static int menu() {
	  int menuSelection = 0; // Variable to store the menu selection
	  boolean validInput = false; //Flag to determine if the input is valid
	  final int EXIT_SELECTED = -1; //Constant to represent the exit option

	  // Show the menu options to user
	  System.out.println("1. Insert articles");
	  System.out.println("2. Remove an article");
	  System.out.println("3. Display a list of articles");
	  System.out.println("4. Register a sale");
	  System.out.println("5. Display order history");
	  System.out.println("6. Sort and display order history table");
	  System.out.println("q. Quit");
	  System.out.print("Your choice: ");

	  do {
		  //Calling the input method to get the user input for menu selection
		  menuSelection = input();

		  if (menuSelection == EXIT_SELECTED){ //User has selected to exit the program
			  validInput = true; //If input is -1, set vaildInput to true and exit the loop
			  menuSelection = EXIT_SELECTED;
		  } else if (menuSelection > 6 || menuSelection < 1){ //User has entered an invalid input
			  validInput = false; //if input is between 1 and 6, set validInput to false to loop again
			  System.out.println("Invalid input. Please enter a number between 1 and 6."); //Error message for invalid input
		  } else { //user has entered a valid input
			  validInput = true; //If input is between 1 and 6, set validInput to true and exit the loop
		  }
	  } while (!validInput); //Continue the loop until valid input is received

    //Return the menu selection
    return menuSelection;
    
  } //end of menu method

  /**
  * Takes user input and ensures it is a valid integer or 'q'. If 'q' is entered, returns -1.
  *
  * @return An integer representing user input, or -1 if the user chooses to exit the program.
  */
  public static int input() {
	  boolean isValid = false; //Flag to determine if the input is valid
	  int input = 0; //Variable to store user input
 
	  do {
		  if (userInput.hasNextInt()) { //If the user input is an integer
			  //Read the integer input
			  input = userInput.nextInt();
			  if (input < 0) { //Check if the input is less than 0 and therefore invalid input
				  System.out.println("Invalid input. Please enter a valid number."); //Error message for invalid input
				  isValid = false; //If less than 0, set isValid to false
			  } else {
				  isValid = true; //If input is valid, set isValid to true
			  }
		  } else if (userInput.hasNext()) { //Check if the next input is a string
			  //Read the thing input
			  String readString = userInput.next();
			  // Check if input is 'Q' or 'q'
			  if (readString.equalsIgnoreCase("q")) {
				  input = -1; //If 'q', set input to -1
				  isValid = true; //Set isValid to true
			  } else {
				  isValid = false; // Set isValid to false indicating the input is invalid
				  System.out.println("Invalid input. Please enter a valid number."); //Error message for invalid input
			  }
		  }
	  } while (!isValid); //Continue the loop until a valid input is received
	  
	  //Return the valid input
	  return input;
    
  } //end of input method

  /**
  * Inserts a specified number of articles with random values into an array.
  *
  * @param articles       The existing 2D array of articles.
  * @param articleNumber  The starting number for the articles to insert.
  * @param noOfArticles   The number of articles to insert.
  * @return               The modified 2D array with the added articles.
  */
  public static int[][] insertArticles(int[][] articles, int articleNumber, int noOfArticles) {
	  // Add the specified amount of articles with random values
	  for (int i = 0; i < noOfArticles; i++) {
		  for (int row = 0; row < articles.length; row++) {
			  //Check if the first column of the row is empty (0)
			  if (articles[row][0] == 0) {
		          //Article number
		          articles[row][0] = articleNumber + i;
		          
		          //Number of units
		          //Generate a number between 1 and 10 
		          articles[row][1] = (int) (Math.random() * 9) + 1;
		          
		          //Article price
		          //Generate a number between 0 and 901 '(Math.random() * 901)' 
		          //Shifts the range between 100 and 1000 '+100' 
		          articles[row][2] = (int) (Math.random() * 901) + 100;
		          
		          // Breaks the inner for loop to increment i and move to the next article
		          break;
			  }
		  }
	  }
	  //Message indicating successful addition 
	  System.out.println(noOfArticles + " articles added successfully.");

	  //Return the array with added articles
	  return articles;
    
  } //end of insertArticles method

  /**
  * Removes an article from the array based on user input.
  *
  * @param articles The 2D array containing articles.
  */
  public static void removeArticle(int[][] articles) {
	  boolean found = false; //Flag to indicate if the article is found
    
	  //Prompt user for the article to remove
	  System.out.print("What articles do you wish to remove? ");
	  //Call input method to get user input
	  int articleToRemove = input();

	  //Iterate through the array to find and remove the specified article
	  for (int i = 0; i < articles.length; i++) {
		  //Check if the first column of the row (the article number) is equal to the article to remove
		  if (articles[i][0] == articleToRemove) {
			  //If article found, set valued to 0 and mark it as removed
			  articles[i][0] = 0;
			  articles[i][1] = 0;
			  articles[i][2] = 0;
			  found = true; //Set flag to true
		  }
	  }
  
	  if (found == false) {
		  //Display a message if the article is not found
		  System.out.println("Article not found.");
	  } else {
		  //Display a message of successful removal
		  System.out.println("Article " + articleToRemove + " successfully removed.");
	  }
  } //end of removeArticle method

  /**
  * Print a table of articles including article number, quantity and price in ascending order based on the article number.
  *
  * @param articles     The 2D array containing unsorted articles information
  */
  private static void printArticles(int[][] articles) {
	  //Print the header for the articles table
	  System.out.printf("%-10s %-7s %15s\n", "Article", "Quantity", "Price");

	  //Perform bubble sort on the articles array in ascending order based on article numbers
	  for (int i = 0; i < articles.length; i++) {
		  for (int j = 0; j < articles.length - i - 1; j++) {
			  //Swap elements if the left elements article number is larger than right element
			  if (articles[j][0] > articles[j + 1][0]) {
	          int[] temp = articles[j]; //Store the value of articles[j] in the temporary array
	          articles[j] = articles[j + 1]; //Store the value of articles[j + 1] in articles[j]
	          articles[j + 1] = temp; //Store the value of the temporary array in articles[j + 1]
			  }
		  }
	  }

	  // Print the sorted array with the articles
	  for (int i = 0; i < articles.length; i++) {
	      //Check if the article number of index 'i' in the array is not 0
	      if (articles[i][0] != 0) {
	    	  //Print the articles information for each article
	    	  System.out.printf("%-10d %-7d %15d\n", articles[i][0], articles[i][1], articles[i][2]);
	      }
	  }
  } //end of printArticles method

  /**
  * Sell an article by updating the sales and articles array.
  *
  * @param sales       The 2D array to store sales data (article number, quantity sold, total price).
  * @param salesDate   The array to store the data corresponding to each  sale.
  * @param articles    The 2D array representing available articles (article number, quantity, price).
  */
  public static void sellArticle(int[][] sales, Date[] salesDate, int[][] articles) {
	  int buyArticle = 0; //Variable to store the article number to buy
	  int articleIndex = 0; //Variable to store the index of the article in the articles array
	  boolean articleExist = false; //Flag to indicate if the selected article exists

	  //Prompt user for input
	  System.out.println("What article do you want to buy?");
    
	  //Call input method to get user input for the article to buy
	  buyArticle = input();
    
	  //Check if the selected article exists in the articles array
	  for (int i = 0; i < articles.length; i++) {
		  //Check if the first column of the row (the article number) is equal to the selected article
		  if (articles[i][0] == buyArticle) {
			  articleIndex = i; // Get the index of the article
			  articleExist = true; //Set the flag to true indicating the article exists

			  //Check if the selected article in the articles array has a quantity of 0
			  if (articles[i][1] == 0){
				  articleExist = false; //Set the flag to false 
			  }
		  }
	  }

	  //Check if flag is set to false
	  if (articleExist == false) { //If article with given index does not exist
		  if (articles[articleIndex][1] == 0){ //The quantity of the article is 0
			  System.out.println("No items in stock of article "  + articles[articleIndex][0] + ".");
		  } else { ///An article with the specified article number does not exist
			  System.out.println("Article not found. Please select another article.");
		  }
	  } else { //If article with given index exist
		  int amountBuy = 0; //Variable to store the quantity of items to buy
		  boolean amountExist = false; //Flag to indicate if the entered amount is valid

		  //Prompt user to input how many articles to buy
		  System.out.println("How many items of article " + articles[articleIndex][0] + " do you want to buy?");
    
		  do {
			  //Call input method to the user input for the quantity to buy
			  amountBuy = input();

			  //Validate the entered amount
			  if (amountBuy <= 0){ //The entered amount is less than or equal to 0
				  System.out.println("Invalid input. Please enter an amount larger than 0.");
			  } else if (amountBuy <= articles[articleIndex][1]) { //The entered amount is less than or equal to the quantity of the selected article
				  amountExist = true; //Set flag to true
			  } else { //The entered amount is more than the quantity of the selected article
				  //Display a message informing how may items can be bought
				  System.out.println("Not enough items. Please enter another amount.");
				  System.out.println((articles[articleIndex][1]) + " items of article " + articles[articleIndex][0] + " are avalible for purchase.");
			  }

		  } while (!amountExist); //Repeat until a valid amount is entered

		  //Update the quantity of the selected article in the articles array subtracting the amount bought
		  articles[articleIndex][1] -= amountBuy;

		  //Add the sales information to the sales array
		  for (int i = 0; i < sales.length; i++) {
			  //Find an empty spot in the sales array
			  if (sales[i][0] == 0) {
				  // Add article number
				  sales[i][0] = articles[articleIndex][0];
				  // Add the quantity bought
				  sales[i][1] = amountBuy;
				  // Add the total price for the bought items
				  sales[i][2] = amountBuy * (articles[articleIndex][2]);
				  // Add a date to the salesDate array with the same index as the corresponding sale
				  salesDate[i] = new Date();
				  //Exit the loop once the sales information is added
				  break;
			  }
		  }
	  }  
  } //end of sellArticle method

  /**
  * Print sales information including date, article number, quantity, and total price.
  *
  * @param sales       The 2D array containing sales information.
  * @param salesDate   The array containing corresponding date for each sale
  */
  private static void printSales(int[][] sales, Date[] salesDate) {
	  //Create a date formatter for the sales information
	  SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm yyyy-MM-dd");
   
	  //Print the header for the sales information
	  System.out.printf("%-20s %-7s %10s %10s\n", "Date", "Article", "Quantity", "Total");

	  //Iterate through the sales array and print the sale's information
	  for (int i = 0; i < sales.length && sales[i][0] != 0; i++) {
		  //Format the date using the SimpleDateFormat
		  String formattedDate = dateFormat.format(salesDate[i]);
		  //Print the sales information for each sale
		  System.out.printf("%-20s %-7d %10d %10d\n", formattedDate, sales[i][0], sales[i][1], sales[i][2]);
	  }
  } // end of printSales method

  /**
  * Prints a sorted table of sales information including date, article number, quantity and total price.
  *
  * @param sales       The 2D array containing unsorted sales information
  * @param salesDate   The array containing corresponding date for each sale
  */
  public static void sortedTable(int[][] sales, Date[] salesDate) {
	  //Create a date formatter with the specified format
	  SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm yyyy-MM-dd");

	  //Create a temporary copy of the sales array to avoid modifying the original array
	  int[][] tempSales = new int[sales.length][3];
	  System.arraycopy(sales, 0, tempSales, 0, sales.length);
	  //Create a temporary copy of the salesDate array to avoid modifying the original array
	  Date[] tempSalesDate = new Date[salesDate.length];
	  System.arraycopy(salesDate, 0, tempSalesDate, 0, salesDate.length);

	  //Print the header for the sorted table
	  System.out.printf("%-20s %-7s %10s %10s\n", "Date", "Article", "Quantity", "Total");

	  //Perform bubble sort on the temporary array in ascending order based on article numbers
	  for (int i = 0; i < tempSales.length; i++) {
		  for (int j = 0; j < tempSales.length - i - 1; j++) {
			  //Swap elements if the left elements article number is larger than right element
			  if (tempSales[j][0] > tempSales[j + 1][0]) {
				  //Swap values in the tempSales array
				  int tempSale[] = tempSales[j]; //Store the value of tempSales[j] in the temporary array
				  tempSales[j] = tempSales[j + 1]; //Store the value of tempSales[j + 1] in tempSales[j]
				  tempSales[j + 1] = tempSale; //Store the value of the temporary array in tempSales[j + 1]

				  //Swap values in the tempSalesDate array
				  Date tempDate = tempSalesDate[j]; //Store the value of tempDate[j] in the temporary array
				  tempSalesDate[j] = tempSalesDate[j + 1]; //Store the value of tempSalesDate[j + 1] in tempSalesDate[j]
				  tempSalesDate[j + 1] = tempDate; //Store the value of the temporary array in tempSalesDate[j + 1]
			  }
		  }
	  }

	  //Print the sorted sales table
	  for (int i = 0; i < tempSales.length; i++) {
		  //Check if the article number of index 'i' in the array is not 0
		  if(tempSales[i][0] != 0){
			  //Format the date using SimpleDateFormat
			  String formattedDate = dateFormat.format(tempSalesDate[i]);
			  //Print the sales information for each sale
			  System.out.printf("%-20s %-7d %10d %10d\n", formattedDate, tempSales[i][0], tempSales[i][1], tempSales[i][2]);
		  }	
	  }
  } //end of sortedTable method

  /**
  * Checks if there are enough free slots in the articles array.
  * If not, expands the array to accommodate the required number of articles.
  *
  * @param articles      The 2D array representing articles (article number, quantity, price).
  * @param noOfArticles  The number of articles to check for available slots.
  * @return              The updated articles array with sufficient free slots
  */
  public static int[][] checkFull(int[][] articles, int noOfArticles) {
	  int freeSlots = 0; //Variable to track the number of free slots in the articles array

	  //Iterate through the article array to count the number of free slots
	  for (int i = 0; i < articles.length; i++) {
		  //Check if the current slot is empty (all values are 0)
		  if (articles[i][0] == 0 && articles[i][1] == 0 && articles[i][2] == 0) {
			  freeSlots++; //Increment to count the number of available slots
		  }
	  }

	  //Check if there are not enough free slots for the required number of articles
	  if (freeSlots < noOfArticles) {
		  //If insufficient free lots, expand the articles array
		  return expandArray(articles, noOfArticles - freeSlots);
	  }

	  //If there are enough free slots, return the original articles array
	  return articles;
  } //end of checkFull method

  /**
  * Expands the size of the articles array by adding a specified number or rows.
  *
  * @param articles      The 2D array representing articles (article number, quantity, price).
  * @param size          The number of rows to add to the array
  * @return              The expanded articles array with the specified number of additional rows
  */
  public static int[][] expandArray(int[][] articles, int size) {
	  //Create a new 2D array with increased row size
	  int[][] tempArticles = new int[articles.length + size][3];

	  //Copy the contents of the original articles array to the new array
	  System.arraycopy(articles, 0, tempArticles, 0, articles.length);

	  //Return the expanded articles array
	  return tempArticles;
    
  } //end of expandArray method
}
