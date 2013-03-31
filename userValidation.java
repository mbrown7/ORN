
public class userValidation {
	
	userValidation( ){
		;
	}
	
	public boolean login(String user, String pass){
		if(isUser(user) && passMatch(user,pass)){
			return true;
		}else{
			return false;
		}
	}

	private boolean isUser(String user){
		//if(the user name is present in the database){
			return true;
		//}else{
			//return false;
		//}
	}
	
	private boolean passMatch(String user, String pass){
		//if(the password matches that of the given user name in the database){
			return true;
		//}else{
			//return false;
		//}
	}
}
