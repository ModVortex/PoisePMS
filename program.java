
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * @author Jurie
 *
 */
public class program {
    
    static Scanner input = new Scanner(System.in);
    static LocalDate date;
    static int rowsAffected;
    static ResultSet results;
    static boolean isCompleted = false;
    static boolean isFound = false;
    static String projectDeadline;
    static String searchType;
    static String tableName;
    static String prjID;
    static boolean updated = false;
    /**
     * Validate if projectDeadline is in a valid date format
     * @param projectDeadline
     * @return
     */
    static boolean isValidDate(String projectDeadline) {
        if (projectDeadline.length() == 10) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);
            try {
                date = LocalDate.parse(projectDeadline, formatter);
                System.out.println(date);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format");
            }
        }
        if (date == null || projectDeadline.length() != 10){
            System.out.println("Invalid format");
            return false;
        }else {
            return true;
        }

    }
    
    /**
     * Adds a new project to the database
     * @param statement
     * @return
     * @throws SQLException
     */
    static String addProject(Statement statement) throws SQLException {
    	//Determine ID
    	String projectID = ""; 
    	results = statement.executeQuery("SELECT prjID FROM projects");
        while (results.next()) {
        	 projectID = results.getString("prjID");
        }
        projectID = String.valueOf(Integer.parseInt(projectID)+1);
    	System.out.println("ID: " + projectID);
        
    	//Assigning rest of project info
        System.out.print("Enter project name: ");
        String projectName = input.nextLine();
        projectName = input.nextLine();
        System.out.print("Enter building type ex. House/Hotel etc : ");
        String buildingType = input.nextLine();
        System.out.println("Enter the address of building ex. 21JumpStreet : ");
        String projectAddress = input.nextLine();
        System.out.println("Enter the ERF number:");
        String erfNum = input.next();
        System.out.println("Enter the project fee:");
        float projectFee = input.nextFloat();
        System.out.println("Enter the total amount paid to date:");
        float totalPaid = input.nextFloat();
        
        //Making project name if not given
        if (projectName.equals("")) {
            String surname = projectName.split(" ")[1];
            projectName = buildingType + " " + surname;
            System.out.println(projectName);
        }
        
        //Assigning date in right format
        while (true) {
        	System.out.println("Enter the deadline date in format: yyyy/mm/dd");
            projectDeadline = input.next();
        	if (isValidDate(projectDeadline)) {
                break;
            }
        }
        LocalDate currDate = LocalDate.now();
        if (date.isBefore(currDate)) {
//            pastDueDateProjects.add(newProject);//Adds to this array list if project is past due date
        }
    	
    	
    	//Executing SQL for creating project
        rowsAffected = statement.executeUpdate("INSERT INTO projects VALUES ("
                + "'"+projectID+"', '"+projectName+"', '"+buildingType+"', '"+projectAddress+"', '"+erfNum+"', '"+projectFee+"', "
                + " '"+totalPaid+"', '"+projectDeadline+"', '"+0+"')"
        );
        System.out.println("Query complete, " + rowsAffected + " rows added.");
        return projectID;
    }
    
    /**
     * Adds a customer to the database
     * @param statement
     * @param prjID
     * @throws SQLException
     */
    static void addCustomer(Statement statement, String prjID) throws SQLException{
    	//Assigning values for Customer
        System.out.print("Enter the name of the customer: ");
        String name = input.nextLine();
        name = input.nextLine();
        System.out.println("Enter customers telephone number");
        String telNum = input.next();
        System.out.println("Enter customers email");
        String email = input.next();
        System.out.print("Enter address ex. 21JumpStreet: ");
        String address = input.nextLine();
        address = input.nextLine();
        
        //Adding info to database
        rowsAffected = statement.executeUpdate(
                "INSERT INTO customer VALUES ("
                + "'"+ prjID +"', '"+name+"', '"+telNum+"', '"+email+"', '"+address+"' )"
        );
        System.out.println("Query complete, " + rowsAffected + " rows added.");
        System.out.println("Customers info saved");        
    }
   
    /**
     * Adds an architect to the database
     * @param statement
     * @param prjID
     * @throws SQLException
     */
    static void addArchitect(Statement statement, String prjID) throws SQLException {
    	//Getting values for Architect
        System.out.print("Enter the name of the architect: ");
        String name = input.nextLine();
        name = input.nextLine();
        System.out.println("Enter architects telephone number");
        String telNum = input.next();
        System.out.println("Enter architects email");
        String email = input.next();
        System.out.print("Enter address ex. 21 Jump Street: ");
        String address = input.nextLine();
        address = input.nextLine();

      //Adding info to database
        rowsAffected = statement.executeUpdate(
                "INSERT INTO architect VALUES ("
                + "'"+ prjID +"', '"+name+"', '"+telNum+"', '"+email+"', '"+address+"' )"
        );
        System.out.println("Query complete, " + rowsAffected + " rows added.");
        System.out.println("Architects info saved");
    }
    
    /**
     * Adds a project manager to the database 
     * @param statement
     * @param prjID
     * @throws SQLException
     */
    static void addProjectManager(Statement statement, String prjID) throws SQLException {
    	//Making a projectManager object
    	System.out.print("Enter the name of the project manager: ");
        String name = input.nextLine();
        name = input.nextLine();
        System.out.println("Enter project managers telephone number");
        String telNum = input.next();
        System.out.println("Enter project managers email");
        String email = input.next();
        System.out.print("Enter address ex. 21 Jump Street: ");
        String address = input.nextLine();
        address = input.nextLine();

        //Adding info to database
        rowsAffected = statement.executeUpdate(
                "INSERT INTO manager VALUES ("
                + "'"+ prjID +"', '"+name+"', '"+telNum+"', '"+email+"', '"+address+"' )"
        );
        System.out.println("Query complete, " + rowsAffected + " rows added.");
        System.out.println("project manager's info saved");
    }
    
    /**
     * Adds an engineer to the database
     * @param statement
     * @param prjID
     * @throws SQLException
     */
    static void addEngineer(Statement statement, String prjID) throws SQLException {
    	//Making a projectManager object
    	System.out.print("Enter the name of the engineer: ");
        String name = input.nextLine();
        name = input.nextLine();
        System.out.println("Enter engineer's telephone number");
        String telNum = input.next();
        System.out.println("Enter engineer's email");
        String email = input.next();
        System.out.print("Enter address ex. 21 Jump Street: ");
        String address = input.nextLine();
        address = input.nextLine();

      //Adding info to database
        rowsAffected = statement.executeUpdate(
                "INSERT INTO engineer VALUES ("
                + "'"+ prjID +"', '"+name+"', '"+telNum+"', '"+email+"', '"+address+"' )"
        );
        System.out.println("Query complete, " + rowsAffected + " rows added.");     
        System.out.println("project manager's info saved");
    }
    
    static boolean updateProject(Statement statement, String updateColumn) throws SQLException {
    	updated = false;
    	System.out.println("Enter the ID of the project u wish to edit.");
		prjID = input.next();
    	switch (updateColumn) {
		case "name": {
			System.out.println("Enter the value u wish to insert in " + updateColumn + " column"); 
    		String updateString = input.nextLine();
        	updateString = input.nextLine();
        	// Change a project:
            rowsAffected = statement.executeUpdate( 
                    "UPDATE projects SET name ='"+updateString+"' WHERE prjID='"+prjID+"'"
                    );	
            return true;
		}
		case "type":{
			System.out.println("Enter the value u wish to insert in " + updateColumn + " column"); 
    		String updateString = input.nextLine();
        	updateString = input.nextLine();
        	// Change a project:
            rowsAffected = statement.executeUpdate( 
                    "UPDATE projects SET type ='"+updateString+"' WHERE prjID='"+prjID+"'"
                    );
            return true;
		}
		case "address":{
			System.out.println("Enter the value u wish to insert in " + updateColumn + " column"); 
    		String updateString = input.nextLine();
        	updateString = input.nextLine();
        	// Change a project:
            rowsAffected = statement.executeUpdate( 
                    "UPDATE projects SET address ='"+updateString+"' WHERE prjID='"+prjID+"'"
                    );
            return true;
		}
		case "ERFnum":{
			System.out.println("Enter the value u wish to insert in " + updateColumn + " column"); 
    		String updateString = input.nextLine();
        	updateString = input.nextLine();
        	// Change a project:
            rowsAffected = statement.executeUpdate( 
                    "UPDATE projects SET ERFnum ='"+updateString+"' WHERE prjID='"+prjID+"'"
                    );
            return true;
		}
		case "paid":{
			System.out.println("Enter the value u wish to insert in " + updateColumn + " column");    		
    		int updateInteger = input.nextInt();
    		// Change a project:
            rowsAffected = statement.executeUpdate( 
                    "UPDATE projects SET paid ='"+updateInteger+"' WHERE prjID ='"+prjID+"'"
            );
            return true;
		}
		case "duedate":{
			while (true) {
	        	System.out.println("Enter the new deadline date in format: yyyy/mm/dd");
	            projectDeadline = input.next();
	        	if (isValidDate(projectDeadline)) {
	                break;
	            }
	        }
        	
        	// Change a project:
            rowsAffected = statement.executeUpdate( 
                    "UPDATE projects SET duedate ='"+projectDeadline+"' WHERE prjID='"+prjID+"'"
                    );
            return true;
		}
		default:
			System.out.println();
			return false;
		}
    }
    
    static boolean searchProject(Statement statement, String searchString) throws SQLException {
    	isFound = false;
    	results = statement.executeQuery("SELECT * FROM projects WHERE prjID = '"+searchString+"' OR name = '"+searchString+"'");    
    	System.out.println("|\tprjID\t|\tname\t|\ttype\t|\taddress\t\t|\tERFnum\t|\tcost\t|\tpaid\t|\tduedate\t\t|\tcompleted\t|");
    	System.out.println("|\t-----\t|\t----\t|\t----\t|\t-------\t\t|\t------\t|\t----\t|\t----\t|\t-------\t\t|\t---------\t|");
    	while (results.next()) {
    		System.out.println(	                    
    				"|\t  "+results.getInt("prjID") +                           
    						"\t|\t" + results.getString("name") +	                            
    						"\t|\t" + results.getString("type") +                           
    						"\t|\t" + results.getString("address") +	                            
    						"\t|\t" + results.getInt("ERFnum") +
    						"\t|\t" + results.getFloat("cost") +
    						"\t|\t" + results.getFloat("paid") +
    						"\t|\t" + results.getString("duedate") +
    						"\t|\t" + results.getBoolean("completed") + "\t\t|"    						    							                         
    				);	
    		System.out.println();
    		isFound = true;
		}	            
    	return isFound;	    	    	
    }
    
    /**
     * Finalize project and returns an invoice
     * @param statement
     * @return invoice
     * @throws SQLException 
     */
    static String finalizeProject(Statement statement, String searchString) throws SQLException {
    	float paid;
    	float totalCost;
    	String email;
    	String telNum;
    	String invoice = "";
    	results = null;
    	results = statement.executeQuery("SELECT prjID, cost, paid FROM projects WHERE prjID = '"+searchString+"'");    	
    	if (results.next()) {
    		totalCost = results.getFloat("cost");
        	paid = results.getFloat("paid");
        	prjID = results.getString("prjID");
        	float amountToPay = totalCost - paid;
        	if (amountToPay > 0) {
        		results = statement.executeQuery("SELECT email, telNum FROM customer WHERE prjID = '"+prjID+"'");
        		if (results.next()) {
        			email = results.getString("email");
            		telNum = results.getString("telNum");
            		invoice = "Telephone number : " + telNum + "\n" +
                            "Email: " + email + "\n" +
                            "Amount to pay: " + amountToPay + "\n";
            		rowsAffected = statement.executeUpdate( 
                            "UPDATE projects SET completed=True WHERE prjID='"+prjID+"'"
                    );
				}else {
					return invoice;
				}

        	} else {
        		rowsAffected = statement.executeUpdate( 
                        "UPDATE projects SET completed=True WHERE prjID='"+prjID+"'"
                );
        		return "0";
        	}
    	}
    	
    	return invoice;
    }
    
    public static void printAllFromTable(Statement statement) throws SQLException{	        
    	results = statement.executeQuery("SELECT * FROM projects");    
    	System.out.println("|\tprjID\t|\tname\t|\ttype\t|\taddress\t\t|\tERFnum\t|\tcost\t|\tpaid\t|\tduedate\t\t|\tcompleted\t|");
    	System.out.println("|\t-----\t|\t----\t|\t----\t|\t-------\t\t|\t------\t|\t----\t|\t----\t|\t-------\t\t|\t---------\t|");
    	while (results.next()) {	            
    		System.out.println(	                    
    				"|\t  "+results.getInt("prjID") +                           
    						"\t|\t" + results.getString("name") +	                            
    						"\t|\t" + results.getString("type") +                           
    						"\t|\t" + results.getString("address") +	                            
    						"\t|\t" + results.getInt("ERFnum") +
    						"\t|\t" + results.getFloat("cost") +
    						"\t|\t" + results.getFloat("paid") +
    						"\t|\t" + results.getString("duedate") +
    						"\t|\t" + results.getBoolean("completed") + "\t\t|"    						    							                         
    				);	        
    	}
    	System.out.println();
    } 
    
    public static void printIncompleteProjects(Statement statement) throws SQLException {
    	results = statement.executeQuery("SELECT * FROM projects WHERE completed = false");     
    	System.out.println("|\tprjID\t|\tname\t|\ttype\t|\taddress\t\t|\tERFnum\t|\tcost\t|\tpaid\t|\tduedate\t\t|\tcompleted\t|");
    	System.out.println("|\t-----\t|\t----\t|\t----\t|\t-------\t\t|\t------\t|\t----\t|\t----\t|\t-------\t\t|\t---------\t|");
    	while (results.next()) {	            
    		System.out.println(	                    
    				"|\t  "+results.getInt("prjID") +                           
    						"\t|\t" + results.getString("name") +	                            
    						"\t|\t" + results.getString("type") +                           
    						"\t|\t" + results.getString("address") +	                            
    						"\t|\t" + results.getInt("ERFnum") +
    						"\t|\t" + results.getFloat("cost") +
    						"\t|\t" + results.getFloat("paid") +
    						"\t|\t" + results.getString("duedate") +
    						"\t|\t" + results.getBoolean("completed") + "\t\t|"    						    							                         
    				);	        
    	}
    	System.out.println();
    }

    public static void printExpiredProjects(Statement statement) throws SQLException {
    	LocalDate currDate = LocalDate.now();    	
    	results = statement.executeQuery("SELECT * FROM projects WHERE dueDate < '"+currDate+"'");    
    	System.out.println("|\tprjID\t|\tname\t|\ttype\t|\taddress\t\t|\tERFnum\t|\tcost\t|\tpaid\t|\tduedate\t\t|\tcompleted\t|");
    	System.out.println("|\t-----\t|\t----\t|\t----\t|\t-------\t\t|\t------\t|\t----\t|\t----\t|\t-------\t\t|\t---------\t|");
    	while (results.next()) {	            
    		System.out.println(	                    
    				"|\t  "+results.getInt("prjID") +                           
    						"\t|\t" + results.getString("name") +	                            
    						"\t|\t" + results.getString("type") +                           
    						"\t|\t" + results.getString("address") +	                            
    						"\t|\t" + results.getInt("ERFnum") +
    						"\t|\t" + results.getFloat("cost") +
    						"\t|\t" + results.getFloat("paid") +
    						"\t|\t" + results.getString("duedate") +
    						"\t|\t" + results.getBoolean("completed") + "\t\t|"    						    							                         
    				);	        
    	}
    	System.out.println();
    }
        
    public static void main(String[] args){
    	//Assigning variables
        String searchString;       	
        String updateColumn;
        Connection connection = null;	
    	try {
            // Connect to the poisepms database, via the jdbc:mysql: channel on localhost (this PC)
            // Use username "otheruser", password "swordfish".
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "otheruser",
                    "swordfish");
            // Create a direct line to the database for running our queries
            Statement statement = connection.createStatement();// Set up finished                        
        
            label:
            while (true) {
            	System.out.println("""
                        Choose one of the options below:
                        a     - Add a new project
                        up    - Updates selected info of project
                        fp    - Finalize project
                        spinc - See all projects that still need to be completed.
                        spdd  - See all projects that are past the due date
                        sp    - Search for a specific project
                        e     - Exits program""" 
                );
            	
                searchType = "";
                String option = input.next();

                switch (option) {
                    case "a": {
                        //Add a new project
                    	String prjID = addProject(statement);
                    	addProjectManager(statement, prjID);
                    	addEngineer(statement, prjID);
                    	addArchitect(statement, prjID);                    	
                    	addCustomer(statement, prjID);
                    	break;
                    }
                    case "up":{
                        //Updates info of project
                    	updateColumn = "";
                    	while (true) {
                    		System.out.print("Choose one of the columns below:\n"
                    				+ "name\n"
                    				+ "type\n"
                    				+ "address\n"
                    				+ "ERFnum\n"
                    				+ "paid\n"
                    				+ "duedate\n");
                    		updateColumn = input.nextLine();
                    		updateColumn = input.nextLine();
                    		if (updateProject(statement, updateColumn)) {
                    			System.out.println("Project updated");
                    			System.out.println("Returning to main menu\n");
                    			break;                    		
							}else {
								System.out.println("Sorry something went wrong updating the table");
                    			System.out.println("Returning to main menu\n");
                    			break;
							}
						}                    	                    	                    	
                        break;
                    }    
                    case "fp":{
                        //Finalizes project                      
                    	searchString = "";
                    	System.out.println("Enter the ID of the project u wish to finalise.");
                    	searchString = input.next();                    	                    	
                        String invoice = finalizeProject(statement, searchString);  
                        if (invoice != "" && invoice != "paid") {
							System.out.println(invoice);
						}else if (invoice.equals("paid")) {
							System.out.println("User already paid");
						}else{
							System.out.println("Project not found");
						}
                        break;
                    }
                    case "spinc":{
                        //Displays all projects that is not complete                       
                        printIncompleteProjects(statement);
                        break;
                    }
                    case "spdd":{
                    	//Displays all projects names that is past due date
                    	printExpiredProjects(statement);
                        break;
                    }
                    
                    case "sp": {
                    	//Search for a specific project
                    	searchString = "";
                    	System.out.println("Enter the ID or Name of the project:");
                    	searchString = input.next();
                    	if (!searchProject(statement, searchString)) {
                    		System.out.println("Project not found");
						} else {
							System.out.println("Project Found");
						}
                    	break;
                    }
                        
                    case "e":{
                    	//Exiting program and saves all current tasks into text file Projects
                        System.out.println("Exiting program");               
                        break label;
                }
                    }
                        
            }
         // Close up our connections
            input.close();
            results.close();
            statement.close();
            connection.close();
           
        } catch (SQLException e) {
            // We only want to catch a SQLException - anything else is off-limits for now.
            e.printStackTrace();
        } 
                          
        }
    }