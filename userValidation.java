
public class userValidation {
	
	DBConnect connect = new DBConnect( );
	
	userValidation( ){
		;
	}
	
	public boolean login(String user, String pass){
		if(connect.getUser(user) && connect.checkPassword(user,pass)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isUser(String user){
		if(connect.getUser(user)){
			return true;
		}else{
			return false;
		}
	}
}
