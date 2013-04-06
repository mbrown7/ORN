
public class registration {

	DBConnect connect = new DBConnect( );
	
	registration( ){
		;
	}
	
	public boolean register(String user, String pass){
		connect.insertUser(user,pass);
		return true;
	}
	
	
}
