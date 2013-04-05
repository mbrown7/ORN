
import java.sql.*;
import java.util.Scanner;


public class DBConnect {
    Scanner in = new Scanner(System.in);
    
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    
    public DBConnect(){
    try{
    
        Class.forName("com.mysql.jdbc.Driver");
        
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ORN","root","");
        stmt = con.createStatement();


                
    }catch(Exception exp){
    	System.out.println("YOU SUCK"+exp);
    }

}
    
      public void insertDataToTable()
    {
        try
        {
        System.out.println("ENTER UNAME");
        String username = in.nextLine();
        
        System.out.println("ENTER PWORD");
        String password = in.nextLine();
        
        System.out.println("ENTER ROLE");
        String role = in.nextLine();
        
        System.out.println("ENTER ABOUT ME");
        String aboutme = in.nextLine();
        
        System.out.println("ENTER POSTS");
        String posts = in.nextLine();
        
        
            String insert = "INSERT INTO profile VALUES ('"+username+"','"+password+ "','"+role+"','"+aboutme+"','"+posts+"')";
            System.out.println(insert);
            stmt.executeUpdate(insert);
            

        }

        catch(Exception e)
        {
            System.out.println("YOU SUCKBALLS");
        }
    }

       public void getUser(){
        try{
            
            String query = "SELECT username FROM profile";
            rs = stmt.executeQuery(query);
            
            System.out.println("Getting USERNAME");
    
            while(rs.next()){
                String username =rs.getString("username");
           
                
                System.out.println(""+username+" ");
                
         
            }          
        }catch(Exception ex){
            System.out.println("YOU SMELL"+ex);
        }
        
    }
    
        public void getRole(){
        try{
            
            String query = "SELECT role FROM profile";
            rs = stmt.executeQuery(query);
            
            System.out.println("Getting ROLES");
    
            while(rs.next()){
                String role =rs.getString("role");
           
                
                System.out.println(""+role+" ");
                
         
            }          
        }catch(Exception ex){
            System.out.println("YOU SMELL"+ex);
        }
        
    }
        
     public void getPosts(){
        try{
            
            String query = "SELECT posts FROM profile";
            rs = stmt.executeQuery(query);
            
            System.out.println("Getting POSTS");
    
            while(rs.next()){
                String posts =rs.getString("posts");
           
                
                System.out.println(""+posts+" ");
                
         
            }          
        }catch(Exception ex){
            System.out.println("YOU SMELL"+ex);
        }
        
    }

    
         public void getProfile(){
        try{
            
            String query = "SELECT aboutme FROM profile";
            rs = stmt.executeQuery(query);
            
            System.out.println("Getting PROFILE");
    
            while(rs.next()){
                String aboutme =rs.getString("aboutme");
           
                
                System.out.println(""+aboutme+" ");
                
         
            }          
        }catch(Exception ex){
            System.out.println("YOU SMELL"+ex);
        }
        
    }
    
    
}   
