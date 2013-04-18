import java.util.*;

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
	
	public boolean newSubscribe(String user, String subscribe){
		if(isUser(subscribe) && user != null){
			LinkedList<String> subbed = connect.getSubscribe(user);
			Iterator<String> subbedIt = subbed.iterator( );
			while(subbedIt.hasNext( )){
				if(subbedIt.next( ) == subscribe){
					return false;
				}
			}
			connect.insertSubscribe(user, subscribe);
			return true;
		}else{
			return false;
		}
	}
}