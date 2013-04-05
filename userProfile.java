
public class userProfile {
	
	DBConnect data = new DBConnect( );

	userProfile( ){
		;
	}
	
	public String getName(String user){
		String name = data.getName(user);
		return name;
	}
	
	public String getOcc(String user){
		String role = data.getRole(user);
		return role;
	}
	
	public String getAge(String user){
		String age = data.getAge(user);
		return age;
	}
}
