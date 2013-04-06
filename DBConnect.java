
import java.sql.*;
import java.util.*;


public class DBConnect {
    Scanner in = new Scanner(System.in);
    
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    
    LinkedList<String> publicposts = new LinkedList<String>( );
    LinkedList<String> privateposts = new LinkedList<String>( );
    LinkedList<String> subscribe = new LinkedList<String>( );
    LinkedList<String> users = new LinkedList<String>( );
    
    public DBConnect( ){
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ORN","root","");
    		stmt = con.createStatement();          
    	}catch(Exception exp){
    		;
    	}
    }
    
    public String getName(String user){
    	return "Morgan Brown";
    }
    
    public String getAge(String user){
    	return "June 30th";
    }
    
    public String getAboutMe(String user){
    	return "I am super awesome and blah blah this is the about me";
    }
    
    public boolean getUser(String user){
    	return true;
    }
    
    public void insertUser(String user, String pass){
    	users.add(user);
    }
    
	public void insertPublicPost(String user, String honk){
		publicposts.add(honk);
	}
	
	public void insertPrivatePost(String user, String honk){
		privateposts.add(honk);
	}
    
	public LinkedList<String> getPublicPost( ){
		return publicposts;
	}
	
	public LinkedList<String> getPrivatePost(String user){
		return privateposts;
	}
	
	public LinkedList<String> getSubscribe(String user){
		return subscribe;
	}
	
	public boolean checkPassword(String user, String password){
		return true;
	}
    
}   
