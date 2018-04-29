import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;




@ManagedBean(name="databaseConnectionBean")
@SessionScoped

public class DatabaseConnectionBean {

	
	private Connection connect = null;
	private Statement statement = null;
	private String username = "s16g20";
	private String password = "s16g20QzeR9";
	private String host ="131.193.209.54";
	private String rdbms ="mysql";
	private String dbSchema ="world";
	private String dbSchema2 ="s16g20";
	private ResultSet result = null;
	private String output;
	private boolean logged_in;
	

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getRdbms() {
		return rdbms;
	}

	public void setRdbms(String rDBMS) {
		rdbms = rDBMS;
	}

	public String getDbSchema() {
		return dbSchema;
	}

	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}

	public String getDbSchema2() {
		return dbSchema2;
	}

	public void setDbSchema2(String dbSchema2) {
		this.dbSchema2 = dbSchema2;
	}


	public boolean isLogged_in() {
		return logged_in;
	}

	
	public void setLogged_in(boolean logged_in) {
		this.logged_in = logged_in;
	}

	public Connection getConn() {
		return connect;
	}

	
	
	public void setConnect(Connection connect) {
		this.connect = connect;
	}

	
	

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}


	
	
	
	
	public String getConnection()
	{
		output = "";
		String URL = "jdbc:"+rdbms+"://"+host+":3306/"+dbSchema;		
		String driver ="com.mysql.jdbc.Driver";
		
		try 
		{
			Class.forName(driver);
			connect = DriverManager.getConnection(URL, username, password);
			
			logged_in = true;

			return "operationDatabase.xhtml";
		
		} 
		catch (Exception e) 
		{
			logged_in = false;
			return "credentials.xhtml";
		} 
		
	}
	

	
	
	 public ResultSet readFromDatabase(String query)
	 
     {
		 statement = null;
		 result = null;
		 output = "";
     try 
             {
             if(connect == null){
                             getConnection();
                     }
             		statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             	

         result = statement.executeQuery(query);
     } 
             catch(Exception e) 
             {
  
            	 //System.out.println(e.getMessage());
         
             }
     return result;
     }
     

	 public void executeQuery(String query)
     {
     try 
             {
             if(connect == null){
                             getConnection();
                     }
                statement = connect.createStatement();
                result = statement.executeQuery(query);
     } 
             catch(Exception e) 
             {
            
            
        
             }
   
     }
	 
	 public void execute(String query)
	 
     {
     try 
             {
             if(connect == null){
                             getConnection();
                     }
                statement = connect.createStatement();
                statement.execute(query);
     } 
             catch(Exception e) 
             {
                
                System.out.println(e.getMessage());
         
             }

     }

	public boolean dropTable(String table_name, Connection connect)
	{
		try 
		{
			statement=connect.createStatement();
			String query = "DROP TABLE "+table_name;
			statement.execute(query);
	        return true;
		} 
		catch (Exception e) 
		{
			
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	void closeConn(){
		
		try {
			connect.close();
			System.out.println("");
			logged_in = false;
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
	}
	
	public void setupDatabase(){
		
		
		
		try {
			statement=connect.createStatement();
		
		
		String query = "";
		
	

		query = "CREATE TABLE IF NOT EXISTS `country`"
				+ " (  `Code` char(3) NOT NULL DEFAULT '',"
				+ "  `Name` char(52) NOT NULL DEFAULT '',"
				+ "  `Continent` "
				+ "enum('Asia','Europe','North America','Africa',"
				+ "'Oceania','Antarctica','South America') NOT NULL "
				+ "DEFAULT 'Asia',  `Region` char(26) NOT NULL DEFAULT '',"
				+ "  `SurfaceArea` float(10,2) NOT NULL DEFAULT '0.00', "
				+ " `IndepYear` smallint(6) DEFAULT NULL, "
				+ " `Population` int(11) NOT NULL DEFAULT '0',"
				+ "  `LifeExpectancy` float(3,1) DEFAULT NULL,  "
				+ "`GNP` float(10,2) DEFAULT NULL, "
				+ " `GNPOld` float(10,2) DEFAULT NULL,"
				+ "  `LocalName` char(45) NOT NULL DEFAULT '',"
				+ "  `GovernmentForm` char(45) NOT NULL DEFAULT '', "
				+ " `HeadOfState` char(60) DEFAULT NULL,  `Capital` int(11)"
				+ " DEFAULT NULL,  `Code2` char(2) NOT NULL DEFAULT '',  "
				+ "PRIMARY KEY (`Code`)) ENGINE=InnoDB DEFAULT CHARSET=latin1";	
	    		statement.execute(query);

	    		
	    		query =  "CREATE TABLE IF NOT EXISTS `city` ("
	    				+ "  `ID` int(11) NOT NULL AUTO_INCREMENT, "
	    				+ " `Name` char(35) NOT NULL DEFAULT '',"
	    				+ "  `CountryCode` char(3) NOT NULL DEFAULT '', "
	    				+ " `District` char(20) NOT NULL DEFAULT '', "
	    				+ " `Population` int(11) NOT NULL DEFAULT '0', "
	    				+ " PRIMARY KEY (`ID`),  KEY `CountryCode` "
	    				+ "(`CountryCode`),  CONSTRAINT `city_ibfk_1`"
	    				+ " FOREIGN KEY (`CountryCode`) REFERENCES `country` "
	    				+ "(`Code`)) ENGINE=InnoDB AUTO_INCREMENT=4080 "
	    				+ "DEFAULT CHARSET=latin1;";
	    		statement.execute(query);
	    		
	    		query =  "CREATE TABLE IF NOT EXISTS `countrylanguage` ( "
	    				+ " `CountryCode` char(3) NOT NULL DEFAULT '',"
	    				+ "  `Language` char(30) NOT NULL DEFAULT '',"
	    				+ "  `IsOfficial` enum('T','F') NOT NULL DEFAULT 'F',"
	    				+ "  `Percentage` float(4,1) NOT NULL DEFAULT '0.0', "
	    				+ " PRIMARY KEY (`CountryCode`,`Language`),"
	    				+ "  KEY `CountryCode` (`CountryCode`),"
	    				+ "  CONSTRAINT `countryLanguage_ibfk_1` FOREIGN KEY"
	    				+ " (`CountryCode`) REFERENCES `country` (`Code`)) "
	    				+ "ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	    		statement.execute(query);
	    		

		} catch (SQLException e) {
			
		
		}


		}
	
	public ResultSet processSelect(String query)
	{
		try 
        {
        if(connect == null){
                        getConnection();
                }
           statement = connect.createStatement();
           result = statement.executeQuery(query);
} 
        catch(Exception e) 
        {
      
      
        }
		return result;
	}

	
}
