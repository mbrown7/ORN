import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GUI extends JFrame implements ActionListener{
	
	JButton log, register, go, post, back, back2, viewProf, subButton;
	String user = null;
	String honkSet = "Public";
	JTextArea typeHonk, tweetSpace, searchBox, usernameSpace, passwordSpace, usernameSpace2,
		passwordSpace2, subBox, userProfBox, aboutSpace, nameSpace, birthSpace, aboutmeSpace;
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
	DBConnect connect = new DBConnect( );

	public GUI( ){
		super("Operation Red Nose");
		pack( );
		setVisible(true);
		initDefaultGUI( );
		setSize(650,450);
	}

	public void initDefaultGUI( ){
		//what I'm thinking of doing is making a logged in panel and a not logged in panel
		//because otherwise I just can't get the buttons and labels to update
		
		//The default interface, i.e., the home screen - where the tweets will appear, etc.
		log = new JButton(" ");
		log.setBounds(445, 10, 75, 25);
		JLabel welcomeUser = new JLabel(" ");
		welcomeUser.setBounds(25, 10, 175, 25);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		if(user == null){
			log.setText("Login");
			welcomeUser.setText("Operation Red Nose");
		}else{
			log.setText("Logout");
			welcomeUser.setText("Welcome to ORN, " + user + "!");
		}
		register = new JButton("Register");
		register.setBounds(525, 10, 95, 25);
		
		tweetSpace = new JTextArea(" ");
		tweetSpace.setBounds(25, 50, 440, 245);
		tweetSpace.setEditable(false);
		LinkedList<String> honks = hr.getAllPosts(user);
		Iterator<String> honkIt = honks.iterator( );
		while(honkIt.hasNext( )){
			tweetSpace.append(honkIt.next( )+"\n");
		}
		pane2 = new JScrollPane(tweetSpace);
		pane2.setBounds(25, 50, 440, 245);
		
		searchBox = new JTextArea("Search");
		searchBox.setBounds(470, 50, 150, 20);
		searchBox.setBorder(BorderFactory.createLineBorder(Color.gray));
		go = new JButton("Go");
		go.setBounds(565, 75, 55, 25);
		
		subBox = new JTextArea("Subscribe to User");
		subBox.setBounds(470, 100, 150, 20);
		subBox.setBorder(BorderFactory.createLineBorder(Color.gray));
		subButton = new JButton("Subscribe");
		subButton.setBounds(515, 125, 105, 25);
		
		JTextArea instructions = new JTextArea("Please use the correct\nbox for the desired function. " +
				"\nUsing the incorrect box will \nlead to errors. To search \nfor a term, use Search." +
				"\nTo subscribe to a user, use \nSubscribe. To view a user \nprofile, use View Profile.");
		instructions.setBounds(470, 175, 150, 150);
		instructions.setEditable(false);
		
		userProfBox = new JTextArea("View User Profile");
		userProfBox.setBounds(470, 355, 150, 20);
		userProfBox.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		typeHonk = new JTextArea( );
		typeHonk.setBounds(25, 300, 440, 80);
		pane1 = new JScrollPane(typeHonk);
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
		statusBox.setText(" ");
		statusBox.setOpaque(true);
		statusBox.setBounds(25,380,300,25);
		
		defaultPanel.add(log);
		log.addActionListener(this);
		defaultPanel.add(register);
		register.addActionListener(this);
		defaultPanel.add(go);
		go.addActionListener(this);
		defaultPanel.add(statusBox);
		defaultPanel.add(subBox);
		defaultPanel.add(subButton);
		subButton.addActionListener(this);
		defaultPanel.add(viewProf);
		viewProf.addActionListener(this);
		defaultPanel.add(pane1);
		defaultPanel.add(instructions);
		defaultPanel.add(pane2);
		defaultPanel.add(post);
		defaultPanel.add(pubPrivList);
		defaultPanel.add(userProfBox);
		pubPrivList.addActionListener(this);
		post.addActionListener(this);
		defaultPanel.add(searchBox);
		defaultPanel.add(welcomeUser);
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
		
		usernameSpace = new JTextArea( );
		usernameSpace.setBounds(100, 200, 450, 25);
		usernameSpace.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		passwordSpace = new JTextArea( );
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
		userLabel2.setBounds(100, 60, 450, 25);
		JLabel passLabel2 = new JLabel("Please Enter a Password:");
		passLabel2.setBounds(100, 110, 450, 25);
		JLabel welcomeNewUser = new JLabel("Welcome to Operation Red Nose!");
		welcomeNewUser.setBounds(225, 10, 250, 75);
		
		usernameSpace2 = new JTextArea( );
		usernameSpace2.setBounds(100, 85, 450, 25);
		usernameSpace2.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		passwordSpace2 = new JTextArea( );
		passwordSpace2.setBounds(100, 135, 450, 25);
		passwordSpace2.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		JLabel about = new JLabel("Would you like to create a profile? Enter 'yes' or 'no' only.");
		JLabel about2 = new JLabel("If you choose no, do not answer the following.");
		about.setBounds(100,160,450,25);
		about2.setBounds(100,210,450,25);
		aboutSpace = new JTextArea( );
		aboutSpace.setBounds(100,185,450,25);
		aboutSpace.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		JLabel name = new JLabel("What is your name?");
		name.setBounds(100,225,450,25);
		nameSpace = new JTextArea( );
		nameSpace.setBounds(100,250,450,25);
		nameSpace.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		JLabel birthday = new JLabel("When is your birthday?");
		birthday.setBounds(100,275,450,25);
		birthSpace = new JTextArea( );
		birthSpace.setBounds(100,300,450,25);
		birthSpace.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		JLabel aboutme = new JLabel("Tell us about yourself!");
		aboutme.setBounds(100,325,450,25);
		aboutmeSpace = new JTextArea( );
		aboutmeSpace.setBounds(100,350,450,25);
		aboutmeSpace.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		JButton goRegister = new JButton("Complete Registration");
		goRegister.setBounds(400, 378, 200, 25);
		back2 = new JButton("Back");
		back2.setBounds(525, 10, 95, 25);
		
		error2 = new JLabel("");
		error2.setBounds(100, 375, 400, 25);
		
		registerPanel.add(usernameSpace2);
		registerPanel.add(passwordSpace2);
		registerPanel.add(about2);
		registerPanel.add(userLabel2);
		registerPanel.add(passLabel2);
		registerPanel.add(about);
		registerPanel.add(aboutme);
		registerPanel.add(aboutmeSpace);
		registerPanel.add(birthday);
		registerPanel.add(birthSpace);
		registerPanel.add(aboutSpace);
		registerPanel.add(name);
		registerPanel.add(nameSpace);
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
		JLabel nameLabel = new JLabel( );
		String nameForLabel = up.getName(profileUser);
		if(nameForLabel.equals("DB ERROR")){
			nameLabel.setText("There was an error retrieving the name.");
		}else{
			nameLabel.setText(nameForLabel);
		}
		nameLabel.setBounds(25, 25, 440, 25);
		
		JLabel birthdayLabel = new JLabel( );
		String birthdayForLabel = up.getBirthday(profileUser);
		if(birthdayForLabel.equals("DB ERROR")){
		    birthdayLabel.setText("There was an error retrieving the birthday.");
		}else{
		    birthdayLabel.setText(birthdayForLabel);
		}
		birthdayLabel.setBounds(25,50,440,25);
		
		JLabel aboutMeLabel = new JLabel( );
		String aboutMeForLabel = up.aboutMe(profileUser);
		if(aboutMeForLabel.equals("DB ERROR")){
		    aboutMeLabel.setText("There was an error retrieving the birthday.");
		}else{
		    aboutMeLabel.setText(aboutMeForLabel);
		}
		aboutMeLabel.setBounds(25,75,440,25);
		
		back = new JButton("Back");
		back.setBounds(525, 10, 95, 25);

		userProfilePanel.add(nameLabel);
		userProfilePanel.add(birthdayLabel);
		userProfilePanel.add(aboutMeLabel);
		userProfilePanel.add(back);
		back.addActionListener(this);
		add(userProfilePanel);
		currentPanel = userProfilePanel;
	}
	
	public void actionPerformed(ActionEvent ev){
		if(ev.getActionCommand( ).equals("Login")){
			//if we want to login, go to the login screen
			currentPanel.removeAll( );
			remove(currentPanel);
			initLoginGUI( );
			validate( );
			repaint( );			
		}else if(ev.getActionCommand( ).equals("Register")){
			//if we want to register, go to the register screen
			if(user == null){
				currentPanel.removeAll( );
				remove(currentPanel);
				initRegisterGUI( );
				validate( );
				repaint( );
			}else{
				statusBox.setText("You cannot register while already logged in!");
			}
		}else if(ev.getActionCommand( ).equals("Go")){
			//this is the search function
			tweetSpace.setText("");
			String keyword = searchBox.getText( );
			LinkedList<String> results = hr.search(user, keyword);
			Iterator<String> iterator = results.iterator( );
			while(iterator.hasNext( )){
				String newHonk = iterator.next( );
				tweetSpace.append(newHonk);
			}
		}else if(ev.getActionCommand( ).equals("Post")){
			if(user == null){
				statusBox.setText("You must be logged in.");
			}else{
				String honk = typeHonk.getText( );
				honk = honk + " -" + user;
				if(!hr.post(user, honk, honkSet)){
					statusBox.setText("The post has failed.");
				}else{
					tweetSpace.append("\n"+honk);
				}
			}
			
		}else if(ev.getActionCommand( ).equals("Complete Login")){
			//if we are trying to complete a login, get the user name and password
			String username = usernameSpace.getText( );
			String password = passwordSpace.getText( );
			//getting the text with .getText( ) puts a space in front of whatever was typed by the user
			if(userVal.login(username, password)){
				user = username;
				currentPanel.removeAll( );
				remove(currentPanel);
				initDefaultGUI( );
				validate( );
				repaint( );
			}else{
				error.setText("The username and password combination is not valid. Have you registered?");
			}

		}else if(ev.getActionCommand( ).equals("Back")){
			//return to the home screen
			currentPanel.removeAll( );
			remove(currentPanel);
			initDefaultGUI( );
			validate( );
			repaint( );
			
		}else if(ev.getActionCommand( ).equals("Complete Registration")){
			error2.setText(" ");
			//If we are trying to complete the registration, get the user name and both password entries
			String username2 = usernameSpace2.getText( );
			String password2 = passwordSpace2.getText( );
			String about = aboutSpace.getText( );
			reg.register(username2, password2);
			user = username2;
			if(about.equals("no")){
				
			}else{
				String name = nameSpace.getText( );
				String birthday = birthSpace.getText( );
				String aboutme = aboutmeSpace.getText( );
				//put all of these things into the database
			}
			currentPanel.removeAll( );
			remove(currentPanel);
			initDefaultGUI( );
			validate( );
			repaint( );
			
		}else if(ev.getActionCommand( ).equals("View Profile")){
			String whoseProf = userProfBox.getText( );
			if(userVal.isUser(whoseProf)){
				//should we also make it so people can only view profiles of to whom they are
				//subscribed? I forget if we have a method already that's checking for if someone
				//is subscribed to someone else or not
				currentPanel.removeAll( );
				remove(currentPanel);
				initUserProfileGUI(whoseProf);
				validate( );
				repaint( );
			}else{
				statusBox.setText("That username is not valid.");
			}
			
		}else if(ev.getActionCommand( ).equals("Logout")){
			user = null;
			currentPanel.removeAll( );
			remove(currentPanel);
			initDefaultGUI( );
			validate( );
			repaint( );
		}else{
			JComboBox box = (JComboBox)ev.getSource( );
			honkSet = (String) box.getSelectedItem( );
		}
	}
	
	public static void main(String[] args){
		new GUI( );
	}
}
