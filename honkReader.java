
public class honkReader {

	honkReader( ){
		;
	}
	
	public boolean post(String user, String honk, String setting){
		if(isUser(user)==-1){
			//if there is no user in the database
			return false;
		}else{
			//put the honk into the post section of the database under the given user with the given
			//public or private setting
			return true;
		}
	}
	
	private int isUser(String user){
		//if(user is in the database){
			//return the index of the user
		//}else{
			return -1;
	}
}
