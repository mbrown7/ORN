
public class userProfile {
	
	DBConnect connect = new DBConnect( );

	userProfile( ){
		;
	}
	
	public String getName(String user){
		String name = connect.getName(user);
		return name;
	}
	
	public String getBirthday(String user){
		String birthday = connect.getAge(user);
		return birthday;
	}
	
	public String aboutMe(String user){
		String aboutme = connect.getAboutMe(user);
		return aboutme;
	}
	
	public String color(String user){
		String color = connect.getColor(user);
		return color;
	}
	
	public boolean profile(String user, String first, String last, String bday, String me, String color){
		connect.insertProfile(user, first, last, bday, me, color);
		return true;
	}
}