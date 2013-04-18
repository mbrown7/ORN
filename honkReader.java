import java.util.Iterator;
import java.util.LinkedList;

public class honkReader {

	DBConnect connect = new DBConnect( );
	
	honkReader( ){
		;
	}
	
	public boolean post(String user, String honk, String setting){
		if(!connect.getUser(user)){
			return false;
		}else{
			if(setting == "Public"){
				connect.insertPublicPost(user,honk);
			}else if(setting == "Private"){
				connect.insertPrivatePost(user,honk);
			}else{
				return false;
			}
			return true;
		}
	}
	
	public LinkedList<String> getAllPosts(String username){
		LinkedList<String> honkList = connect.getPublicPost( );
		if(username == "null"){
			
		}else{
			LinkedList<String> subList = connect.getSubscribe(username);
			Iterator<String> subListIt = subList.iterator( );
			while(subListIt.hasNext( )){
				String subscribe = subListIt.next( );
				LinkedList<String> subPrivPosts = connect.getPrivatePost(subscribe);
				Iterator<String> subPrivPostsIt = subPrivPosts.iterator( );
				while(subPrivPostsIt.hasNext( )){
					honkList.add(subPrivPostsIt.next( ));
				}
			}
		}
		return honkList;
	}
	
	public LinkedList<String> search(String username, String keyword){
		LinkedList<String> error = new LinkedList<String>( );
		error.add("The search term was not found.");
		LinkedList<String> honkList = getAllPosts(username);
		String honk;
		LinkedList<String> finalReturn = new LinkedList<String>( );
		boolean found = false;
		keyword = "#"+keyword;
		Iterator<String> iterator = honkList.iterator( );
		while(iterator.hasNext( )){
			honk = iterator.next( );
			iterator.remove( );
			if(honk.indexOf(keyword)==-1){
				
			}else{
				finalReturn.add(honk);
				found = true;
			}
		}
		if(found == false){
			return error;
		}else{
			return finalReturn;
		}
	}
}
