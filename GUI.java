import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener{
	//Note: If you click something once, and type stuff in, say, the username area
	//then if you leave that panel and return later, the stuff you typed is still there
	//obviously this is not what I want to be happening
	
	JButton log, register, go, post, back, back2, viewProf;
	//Later on, we want to make the user variable equal to whatever the stored username is
	//this will be information from another class
	String user = null;
	String tweetSet = "Public";
	JTextArea typeTweet, tweetSpace, searchBox, usernameSpace, passwordSpace, usernameSpace2,
		passwordSpace2, passwordSpace3;
	JScrollPane pane1, pane2;
	JPanel defaultPanel = new JPanel(null);
	JPanel loginPanel = new JPanel(null);
	JPanel registerPanel = new JPanel(null);
	JPanel userProfilePanel = new JPanel(null);
	JPanel currentPanel;
	JLabel error, error2, statusBox;
	
	userValidation userVal = new userValidation( );
	registration reg = new registration( );
	honkReader hr = new honkReader( );
	userProfile up = new userProfile( );

	public GUI( ){
		super("Operation Red Nose");
		pack( );
		setVisible(true);
		initDefaultGUI( );
		setSize(650,450);
	}

	public void initDefaultGUI( ){
		//The default interface, i.e., the home screen - where the tweets will appear, etc.
		log = new JButton(" ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton welcomeUser = new JButton(" ");
		welcomeUser.setBounds(100, 10, 75, 25);
		if(user == null){
			log.setBounds(445, 10, 75, 25);
			log.setText("Login");
		}else{
			//it's going into the else statement when I make the user something, but it's not
			//changing the login button to read "logout"
			//it's also not changing the welcome user
			log.setBounds(445, 10, 75, 25);
			log.setText("Logout");
			welcomeUser.setText("Welcome to ORN, " + user + "!");
		}
		register = new JButton("Register");
		register.setBounds(525, 10, 95, 25);
		
		tweetSpace = new JTextArea(" ");
		tweetSpace.setBounds(25, 50, 440, 245);
		tweetSpace.setEditable(false);
		pane2 = new JScrollPane(tweetSpace);
		pane2.setBounds(25, 50, 440, 245);
		
		searchBox = new JTextArea("Search");
		searchBox.setBounds(470, 50, 150, 20);
		searchBox.setBorder(BorderFactory.createLineBorder(Color.gray));
		go = new JButton("Go");
		go.setBounds(565, 75, 55, 25);
		
		typeTweet = new JTextArea(" ");
		typeTweet.setBounds(25, 300, 440, 80);
		pane1 = new JScrollPane(typeTweet);
		pane1.setBounds(25, 300, 440, 80);
		String[] options = {"Public","Private"};
		JComboBox pubPrivList = new JComboBox(options);
		pubPrivList.setSelectedIndex(0);
		pubPrivList.setBounds(325, 380, 65, 25);
		post = new JButton("Post");
		post.setBounds(400, 380, 65, 25);
		viewProf = new JButton("View Profile");
		viewProf.setBounds(480,380,130,25);
		statusBox = new JLabel(" ");
		statusBox.setBounds(25,380,300,25);
		
		defaultPanel.add(log);
		log.addActionListener(this);
		defaultPanel.add(register);
		register.addActionListener(this);
		defaultPanel.add(go);
		go.addActionListener(this);
		defaultPanel.add(statusBox);
		defaultPanel.add(viewProf);
		viewProf.addActionListener(this);
		defaultPanel.add(pane1);
		defaultPanel.add(pane2);
		defaultPanel.add(post);
		defaultPanel.add(pubPrivList);
		pubPrivList.addActionListener(this);
		post.addActionListener(this);
		defaultPanel.add(searchBox);
		add(defaultPanel);
		currentPanel = defaultPanel;
	}
	
	public void initLoginGUI( ){
		//The interface for the login screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(100, 175, 450, 25);
		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(100, 225, 450, 25);
		JLabel welcomeBack = new JLabel("Welcome back to Operation Red Nose!");
		welcomeBack.setBounds(225, 100, 250, 75);
		
		usernameSpace = new JTextArea(" ");
		usernameSpace.setBounds(100, 200, 450, 25);
		usernameSpace.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		passwordSpace = new JTextArea(" ");
		passwordSpace.setBounds(100, 250, 450, 25);
		passwordSpace.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		JButton goLog = new JButton("Complete Login");
		goLog.setBounds(400, 278, 150, 25);
		back = new JButton("Back");
		back.setBounds(525, 10, 95, 25);
		
		error = new JLabel(" ");
		error.setBounds(100, 315, 400, 25);
		
		loginPanel.add(usernameSpace);
		loginPanel.add(passwordSpace);
		loginPanel.add(userLabel);
		loginPanel.add(passLabel);
		loginPanel.add(goLog);
		loginPanel.add(error);
		loginPanel.add(welcomeBack);
		goLog.addActionListener(this);
		loginPanel.add(back);
		back.addActionListener(this);
		add(loginPanel);
		currentPanel = loginPanel;
	}

	public void initRegisterGUI( ){
		//the interface for the registration screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel userLabel2 = new JLabel("Please Enter a Username:");
		userLabel2.setBounds(100, 175, 450, 25);
		JLabel passLabel2 = new JLabel("Please Enter a Password:");
		passLabel2.setBounds(100, 225, 450, 25);
		JLabel passLabel3 = new JLabel("Re-enter Your Password for Confirmation:");
		passLabel3.setBounds(100, 275, 450, 25);
		JLabel welcomeNewUser = new JLabel("Welcome to Operation Red Nose!");
		welcomeNewUser.setBounds(225, 100, 250, 75);
		
		usernameSpace2 = new JTextArea(" ");
		usernameSpace2.setBounds(100, 200, 450, 25);
		usernameSpace2.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		passwordSpace2 = new JTextArea(" ");
		passwordSpace2.setBounds(100, 250, 450, 25);
		passwordSpace2.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		passwordSpace3 = new JTextArea(" ");
		passwordSpace3.setBounds(100, 300, 450, 25);
		passwordSpace3.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		JButton goRegister = new JButton("Complete Registration");
		goRegister.setBounds(400, 330, 200, 25);
		back2 = new JButton("Back");
		back2.setBounds(525, 10, 95, 25);
		
		error2 = new JLabel(" ");
		error2.setBounds(100, 352, 400, 25);
		
		registerPanel.add(usernameSpace2);
		registerPanel.add(passwordSpace2);
		registerPanel.add(passwordSpace3);
		registerPanel.add(userLabel2);
		registerPanel.add(passLabel2);
		registerPanel.add(passLabel3);
		registerPanel.add(goRegister);
		registerPanel.add(error2);
		registerPanel.add(welcomeNewUser);
		goRegister.addActionListener(this);
		registerPanel.add(back2);
		back2.addActionListener(this);
		add(registerPanel);
		currentPanel = registerPanel;
	}
	
	public void initUserProfileGUI(String profileUser){
		//the interface for the user profile screen
		//profile user is the name of the user at whose profile we want to look
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel nameLabel = new JLabel(up.getName(profileUser));
		nameLabel.setBounds(25, 25, 440, 25);
		
		JLabel occLabel = new JLabel(up.getOcc(profileUser));
		occLabel.setBounds(25,75,440,25);
		
		JLabel ageLabel = new JLabel(up.getAge(profileUser));
		ageLabel.setBounds(25,100,440,25);
		
		userProfilePanel.add(nameLabel);
		userProfilePanel.add(occLabel);
		userProfilePanel.add(ageLabel);
		add(userProfilePanel);
		currentPanel = userProfilePanel;
	}
	
	public void actionPerformed(ActionEvent ev){
		if(ev.getActionCommand( ).equals("Login")){
			//if we want to login, go to the login screen
			remove(currentPanel);
			initLoginGUI( );
			validate( );
			repaint( );			
		}else if(ev.getActionCommand( ).equals("Register")){
			//if we want to register, go to the register screen
			remove(currentPanel);
			initRegisterGUI( );
			validate( );
			repaint( );
		}else if(ev.getActionCommand( ).equals("Go")){
			//the search
		}else if(ev.getActionCommand( ).equals("Post")){
			if(user == null){
				statusBox.setText("You must be logged in.");
			}else{
				String honk = typeTweet.getText( );
				if(!hr.post(user, honk, tweetSet)){
					statusBox.setText("The post has failed.");
				}else{
					tweetSpace.setText(honk + "-" + user);
				}
			}
			
		}else if(ev.getActionCommand( ).equals("Complete Login")){
			//if we are trying to complete a login, get the user name and password
			String username = usernameSpace.getText( );
			String password = passwordSpace.getText( );
			//getting the text with .getText( ) puts a space in front of whatever was typed by the user
			if(userVal.login(username, password)){
				user = username;
				remove(currentPanel);
				initDefaultGUI( );
				validate( );
				repaint( );
			}else{
				error.setText("The username and password combination is not valid. Have you registered?");
			}

		}else if(ev.getActionCommand( ).equals("Back")){
			//return to the home screen
			remove(currentPanel);
			initDefaultGUI( );
			validate( );
			repaint( );
			
		}else if(ev.getActionCommand( ).equals("Complete Registration")){
			error2.setText(" ");
			//If we are trying to complete the registration, get the user name and both password entries
			String username2 = usernameSpace2.getText( );
			String password2 = passwordSpace2.getText( );
			String password3 = passwordSpace3.getText( );
			if(!password2.equals(password3)){
				error2.setText("The passwords do not match.");
			}else if(reg.isUser(username2)){
				//if that user name is already in the database
				error2.setText("That user name is already in use.");
			}else{
				reg.register(username2, password2);
				user = username2;
				initDefaultGUI( );
				validate( );
				repaint( );
			}
			
		}else if(ev.getActionCommand( ).equals("View Profile")){
			remove(currentPanel);
			initUserProfileGUI(user);
			validate( );
			repaint( );	
			
		}else{
			JComboBox box = (JComboBox)ev.getSource( );
			tweetSet = (String) box.getSelectedItem( );
		}
	}
	
	public static void main(String[] args){
		new GUI( );
	}
}
