import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.*;

public class GUI extends JFrame implements ActionListener {
	
	/**
	 * This class includes everything to do with the GUI creation and implementation.
	 * @author Morgan Brown, Daniela Cardona, Sonia Obeng
	 */

	//Global variables needed for GUI screens as well as Action Listener actions
	JButton back;
	String user = null;
	JTextArea typeHonk, tweetSpace, searchBox, usernameSpace, passwordSpace,
			usernameSpace2, passwordSpace2, subBox, userProfBox, aboutSpace,
			nameSpace, birthSpace, aboutmeSpace, colorSpace;
	JPanel defaultPanel = new JPanel(null);
	JPanel loginPanel = new JPanel(null);
	JPanel registerPanel = new JPanel(null);
	JPanel userProfilePanel = new JPanel(null);
	JPanel currentPanel;
	JLabel error, error2, statusBox;
	String honkSet = "Public";

	//instantiating the relevant classes
	userValidation userVal = new userValidation();
	registration reg = new registration();
	honkReader hr = new honkReader();
	userProfile up = new userProfile();
	DBConnect connect = new DBConnect();

	public GUI() {
		/**
		 * This class creates the GUI window.
		 */
		super("Operation Red Nose");
		pack();
		setVisible(true);
		initDefaultGUI();
		setSize(650, 450);
	}

	public void initDefaultGUI() {
		/**
		 * This class creates the GUI panel for the homescreen.
		 */
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Login button, deciding whether it should say "Login" or "Logout"
		//Welcome label, deciding whether it should be generic or specific
		//both of these are based on if user is "null" or not
		JButton log = new JButton(" ");
		log.setBounds(445, 10, 75, 25);
		JLabel welcomeUser = new JLabel(" ");
		welcomeUser.setBounds(25, 10, 400, 25);
		if(user == null){
			log.setText("Login");
			welcomeUser.setText("Operation Red Nose");
			welcomeUser.setFont(new Font("Impact", Font.ITALIC, 22));

		}else{
			log.setText("Logout");
			welcomeUser.setText("Welcome to ORN, " + user + "!");
			welcomeUser.setFont(new Font("Impact", Font.ITALIC, 19));
		}
		
		//Registration button
		JButton register = new JButton("Register");
		register.setBounds(525, 10, 95, 25);

		//text area for displaying the tweets
		//display all of the posts based on who is logged in, if anyone
		tweetSpace = new JTextArea(" ");
		tweetSpace.setBounds(25, 50, 440, 245);
		tweetSpace.setEditable(false);
		LinkedList<String> honks = hr.getAllPosts(user);
		Iterator<String> honkIt = honks.iterator();
		while(honkIt.hasNext( )){
			tweetSpace.append(honkIt.next( ) + "\n");
		}
		//allowing the tweet space to scroll
		JScrollPane pane2 = new JScrollPane(tweetSpace);
		pane2.setBounds(25, 45, 440, 245);
		
		//the box and button for searching for terms
		searchBox = new JTextArea("Search");
		searchBox.setBounds(470, 53, 150, 20);
		searchBox.setBorder(BorderFactory.createLineBorder(Color.gray));
		JButton go = new JButton("Go");
		go.setBounds(565, 75, 55, 25);

		//the box and button for subscribing to users
		subBox = new JTextArea("Subscribe to User");
		subBox.setBounds(470, 102, 150, 20);
		subBox.setBorder(BorderFactory.createLineBorder(Color.gray));
		JButton subButton = new JButton("Subscribe");
		subButton.setBounds(515, 125, 105, 25);

		//setting the instructions text area
		JTextArea instructions = new JTextArea(
				"Please use the correct\nbox for the desired function. "
						+ "\nUsing the incorrect box will \nlead to errors. To search \nfor a term, use Search."
						+ "\nTo subscribe to a user, use \nSubscribe. To view a user \nprofile, use View Profile.");
		instructions.setBounds(470, 175, 150, 150);
		instructions.setEditable(false);
		instructions.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		//viewing user profiles
		userProfBox = new JTextArea("View User Profile");
		userProfBox.setBounds(470, 355, 150, 20);
		userProfBox.setBorder(BorderFactory.createLineBorder(Color.gray));
		JButton viewProf = new JButton("View Profile");
		viewProf.setBounds(480, 380, 130, 25);

		//typing the tweet and letting it scroll
		typeHonk = new JTextArea();
		typeHonk.setBounds(25, 300, 440, 80);
		JScrollPane pane1 = new JScrollPane(typeHonk);
		pane1.setBounds(25, 300, 440, 80);
		
		//tweet privacy settings
		String[] options = { "Public", "Private" };
		JComboBox pubPrivList = new JComboBox(options);
		pubPrivList.setSelectedIndex(0);
		pubPrivList.setBounds(325, 380, 65, 25);
		
		//posting the tweet
		JButton post = new JButton("Post");
		post.setBounds(400, 380, 65, 25);
		
		//an error box
		statusBox = new JLabel(" ");
		statusBox.setText(" ");
		statusBox.setOpaque(true);
		statusBox.setBounds(25, 380, 300, 25);
		statusBox.setBackground(Color.yellow);
		
		//the refresh button
		JButton refresh = new JButton("Refresh");
		refresh.setBounds(340, 10, 100, 25);

		//adding everything to the panel
		defaultPanel.setBackground(Color.yellow);
		defaultPanel.add(log);
		log.addActionListener(this);
		defaultPanel.add(refresh);
		refresh.addActionListener(this);
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

	public void initLoginGUI() {
		/**
		 * Creating the interface for the login screen.
		 */
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//welcoming the user and asking for their username and password
		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(100, 175, 450, 25);
		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(100, 225, 450, 25);
		JLabel welcomeBack = new JLabel("Welcome back to Operation Red Nose!");
		welcomeBack.setBounds(150, 100, 360, 75);
		welcomeBack.setFont(new Font("Impact", Font.ITALIC, 22));

		//space to type username
		usernameSpace = new JTextArea();
		usernameSpace.setBounds(100, 200, 450, 25);
		usernameSpace.setBorder(BorderFactory.createLineBorder(Color.gray));

		//space to type password
		passwordSpace = new JTextArea();
		passwordSpace.setBounds(100, 250, 450, 25);
		passwordSpace.setBorder(BorderFactory.createLineBorder(Color.gray));

		//buttons to complete the login and to go back
		JButton goLog = new JButton("Complete Login");
		goLog.setBounds(400, 278, 150, 25);
		back = new JButton("Back");
		back.setBounds(525, 10, 95, 25);

		//an error label
		error = new JLabel(" ");
		error.setBounds(100, 315, 400, 25);

		//adding everything to the panel
		loginPanel.setBackground(Color.yellow);
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

	public void initRegisterGUI() {
		/**
		 * Creating the registeration GUI panel.
		 */
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//prompting the user for a username and password
		//welcoming the new user
		JLabel userLabel2 = new JLabel("Create a Username:");
		userLabel2.setBounds(100, 45, 450, 25);
		JLabel passLabel2 = new JLabel("Create a Password:");
		passLabel2.setBounds(100, 90, 450, 25);
		JLabel welcomeNewUser = new JLabel("Welcome to Operation Red Nose!");
		welcomeNewUser.setBounds(180, 0, 350, 75);
		welcomeNewUser.setFont(new Font("Impact", Font.ITALIC, 22));

		//place to type username
		usernameSpace2 = new JTextArea();
		usernameSpace2.setBounds(100, 65, 450, 25);
		usernameSpace2.setBorder(BorderFactory.createLineBorder(Color.gray));

		//place to type password
		passwordSpace2 = new JTextArea();
		passwordSpace2.setBounds(100, 110, 450, 25);
		passwordSpace2.setBorder(BorderFactory.createLineBorder(Color.gray));

		//asking for a profile
		JLabel about = new JLabel(
				"Would you like to create a profile? Enter 'yes' or 'no' only.");
		JLabel about2 = new JLabel(
				"If you choose no, do not answer the following.");
		about.setBounds(100, 135, 450, 25);
		about2.setBounds(100, 145, 450, 25);
		aboutSpace = new JTextArea();
		aboutSpace.setBounds(100, 165, 450, 25);
		aboutSpace.setBorder(BorderFactory.createLineBorder(Color.gray));

		//asking for the name
		JLabel name = new JLabel("What is your name?");
		name.setBounds(100, 190, 450, 25);
		nameSpace = new JTextArea();
		nameSpace.setBounds(100, 210, 450, 25);
		nameSpace.setBorder(BorderFactory.createLineBorder(Color.gray));

		//asking for the birthday
		JLabel birthday = new JLabel("When is your birthday?");
		birthday.setBounds(100, 235, 450, 25);
		birthSpace = new JTextArea();
		birthSpace.setBounds(100, 255, 450, 25);
		birthSpace.setBorder(BorderFactory.createLineBorder(Color.gray));

		//asking for about me
		JLabel aboutme = new JLabel("Tell us about yourself!");
		aboutme.setBounds(100, 280, 450, 25);
		aboutmeSpace = new JTextArea();
		aboutmeSpace.setBounds(100, 300, 450, 25);
		aboutmeSpace.setBorder(BorderFactory.createLineBorder(Color.gray));

		//asking for profile color
		JLabel color = new JLabel("Choose Profile Background Color :)");
		color.setBounds(100, 325, 450, 25);
		colorSpace = new JTextArea();
		colorSpace.setBounds(100, 345, 450, 25);
		colorSpace.setBorder(BorderFactory.createLineBorder(Color.gray));

		//completing registration and back buttons
		JButton goRegister = new JButton("Complete Registration");
		goRegister.setBounds(400, 378, 200, 25);
		JButton back2 = new JButton("Back");
		back2.setBounds(525, 10, 95, 25);

		//an error label
		error2 = new JLabel("");
		error2.setBounds(100, 375, 400, 25);

		//adding everything to the panel
		registerPanel.setBackground(Color.yellow);
		registerPanel.add(usernameSpace2);
		registerPanel.add(passwordSpace2);
		registerPanel.add(about2);
		registerPanel.add(userLabel2);
		registerPanel.add(passLabel2);
		registerPanel.add(about);
		registerPanel.add(aboutme);
		registerPanel.add(aboutmeSpace);
		registerPanel.add(color);
		registerPanel.add(colorSpace);
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

	public void initUserProfileGUI(String profileUser) {
		/**
		 * Creating the profile GUI panel.
		 */
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//displaying the name
		JLabel nameLabel = new JLabel();
		String nameForLabel = up.getName(profileUser);
		if(nameForLabel.equals("DB ERROR")){
			nameLabel.setText("This User Never Created A Profile");
			nameLabel.setFont(new Font("Impact", Font.PLAIN, 30));

		}else{
			nameLabel.setText("Name: " + nameForLabel);
			nameLabel.setFont(new Font("Impact", Font.PLAIN, 30));

		}
		nameLabel.setBounds(150, 75, 440, 25);
		nameLabel.setForeground(Color.LIGHT_GRAY);

		//displaying the birthday
		JLabel birthdayLabel = new JLabel();
		String birthdayForLabel = up.getBirthday(profileUser);
		if(birthdayForLabel.equals("DB ERROR")){
			birthdayLabel.setText("");
		}else{
			birthdayLabel.setText("Birthday: " + birthdayForLabel);
			birthdayLabel.setFont(new Font("Impact", Font.PLAIN, 25));
			birthdayLabel.setForeground(Color.LIGHT_GRAY);
		}
		birthdayLabel.setBounds(150, 100, 440, 25);

		//setting up to display the color
		JLabel colorLabel = new JLabel();
		String colorMeLabel = up.color(profileUser);
		if(colorMeLabel.equals("DB ERROR")){
			colorLabel.setText("");
		}else{
			colorLabel.setText(colorMeLabel);
			colorLabel.setFont(new Font("Impact", Font.PLAIN, 25));
			colorLabel.setForeground(Color.LIGHT_GRAY);
		}
		colorLabel.setBounds(0, 0, 0, 0);

		//displaying the "about me"
		JTextArea aboutMeLabel = new JTextArea();
		aboutMeLabel.setLineWrap(true);
		aboutMeLabel.setEditable(false);
		String aboutMeForLabel = up.aboutMe(profileUser);
		if(aboutMeForLabel.equals("DB ERROR")){
			aboutMeLabel.setText("");
		}else{
			aboutMeLabel.setText("About Me: " + aboutMeForLabel);
			aboutMeLabel.setFont(new Font("Impact", Font.PLAIN, 25));
			aboutMeLabel.setForeground(Color.LIGHT_GRAY);
		}
		aboutMeLabel.setBounds(150, 120, 325, 200);

		//back button
		back = new JButton("Back");
		back.setBounds(525, 10, 95, 25);
		//displaying the color
		switch (colorMeLabel) {
		case "green":
			userProfilePanel.setBackground(Color.green);
			aboutMeLabel.setBackground(Color.green);
			break;
		case "cyan":
			userProfilePanel.setBackground(Color.cyan);
			aboutMeLabel.setBackground(Color.cyan);
			break;
		case "red":
			userProfilePanel.setBackground(Color.red);
			aboutMeLabel.setBackground(Color.red);
			break;
		case "blue":
			userProfilePanel.setBackground(Color.blue);
			aboutMeLabel.setBackground(Color.blue);
			break;
		case "pink":
			userProfilePanel.setBackground(Color.pink);
			aboutMeLabel.setBackground(Color.pink);
			break;
		case "black":
			userProfilePanel.setBackground(Color.black);
			aboutMeLabel.setBackground(Color.green);
			break;
		case "orange":
			userProfilePanel.setBackground(Color.ORANGE);
			aboutMeLabel.setBackground(Color.orange);
			break;
		case "magenta":
			userProfilePanel.setBackground(Color.MAGENTA);
			aboutMeLabel.setBackground(Color.magenta);
			break;
		default:
			userProfilePanel.setBackground(Color.yellow);
			aboutMeLabel.setBackground(Color.yellow);
			break;
		}

		//adding everything to the panel
		userProfilePanel.add(nameLabel);
		userProfilePanel.add(birthdayLabel);
		userProfilePanel.add(colorLabel);
		userProfilePanel.add(aboutMeLabel);
		userProfilePanel.add(back);
		back.addActionListener(this);
		add(userProfilePanel);
		currentPanel = userProfilePanel;
	}

	public void actionPerformed(ActionEvent ev) {
		/**
		 * The class which handles the action listeners.
		 * @param ev The name of the action event, like a button press.
		 */
		//if we want to login
		if(ev.getActionCommand( ).equals("Login")){
			//remove everything from the current panel, remove the panel, and replace it with
			//the login panel
			currentPanel.removeAll();
			remove(currentPanel);
			initLoginGUI();
			validate();
			repaint();
			
		}else if(ev.getActionCommand( ).equals("Register")){
			//remove everything from the current panel, remove the panel, and replace it with
			//the registration panel
			//unless someone is already logged in
			if(user == null){
				currentPanel.removeAll();
				remove(currentPanel);
				initRegisterGUI();
				validate();
				repaint();
			}else{
				statusBox.setText("You cannot register while already logged in!");
			}
			
		}else if(ev.getActionCommand( ).equals("Go")){
			// this is the search function
			//make the tweet space empty
			tweetSpace.setText("");
			//search through all of the tweets for the word
			String keyword = searchBox.getText( );
			LinkedList<String> results = hr.search(user, keyword);
			Iterator<String> iterator = results.iterator( );
			while(iterator.hasNext( )){
				//add everything in the list to be displayed
				String newHonk = iterator.next( );
				tweetSpace.append(newHonk);
			}
			
		}else if(ev.getActionCommand( ).equals("Post")){
			//Posting a tweet
			if(user == null){
				statusBox.setText("You must be logged in.");
			}else{
				//get the post
				String honk = typeHonk.getText();
				//get the date of the post
				Calendar rightNow = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("[MMM,d]: ");
				String time = sdf.format(rightNow.getTime());
				//put everything together
				honk = time + " " + honk + " -" + user;
				//find any user references
				int index = honk.indexOf("@");
				boolean fail = false;
				while(index != -1){
					int index2 = honk.indexOf(" ", index);
					if(index2 == -1){
						index2 = honk.length( );
					}
					String refdUser = honk.substring(index + 1, index2);
					//add this post to their profile as well
					if (!hr.post(refdUser, honk, honkSet)) {
						fail = true;
					}
					index = honk.indexOf("@", index2);
				}
				if (!hr.post(user, honk, honkSet)) {
					fail = true;
				}
				//if the post is too long
				if (honk.length() >= 140 || fail) {
					statusBox.setText("The post has failed.");
				//otherwise add it to the space
				} else {
					JLabel honkLabel = new JLabel(honk);
					honkLabel.setForeground(Color.green);
					tweetSpace.add(honkLabel);
				}
			}

		} else if (ev.getActionCommand().equals("Complete Login")) {
			// if we are trying to complete a login, get the user name and
			// password
			String username = usernameSpace.getText();
			String password = passwordSpace.getText();
			//if it's a valid user
			if (userVal.login(username, password)) {
				//log them in
				user = username;
				currentPanel.removeAll();
				remove(currentPanel);
				initDefaultGUI();
				validate();
				repaint();
			} else {
				error.setText("The username and password combination is not valid. Have you registered?");
			}

		} else if (ev.getActionCommand().equals("Back")) {
			// return to the home screen
			currentPanel.removeAll();
			remove(currentPanel);
			initDefaultGUI();
			validate();
			repaint();

		} else if (ev.getActionCommand().equals("Complete Registration")) {
			error2.setText(" ");
			// If we are trying to complete the registration, get the user name
			// and both password entries
			String username2 = usernameSpace2.getText();
			String password2 = passwordSpace2.getText();
			String about = aboutSpace.getText();
			reg.register(username2, password2);
			//log them in
			user = username2;
			if (about.equals("no")) {

			} else {
				//getting all of the profile information
				String name = nameSpace.getText();
				String birthday = birthSpace.getText();
				String aboutme = aboutmeSpace.getText();
				String color = colorSpace.getText();
				String[] tokens = name.split(" ");
				String firstname = "";
				String lastname = "";
				if (tokens.length > 0) {
					firstname = tokens[0];
					lastname = tokens[tokens.length - 1];
				}
				//add the profile
				up.profile(user, firstname, lastname, birthday, aboutme, color);
			}
			//return to the home screen
			currentPanel.removeAll();
			remove(currentPanel);
			initDefaultGUI();
			validate();
			repaint();

		} else if (ev.getActionCommand().equals("View Profile")) {
			//viewing profile panels based on the profile box entry
			String whoseProf = userProfBox.getText();
			if (userVal.isUser(whoseProf)) {
				currentPanel.removeAll();
				remove(currentPanel);
				initUserProfileGUI(whoseProf);
				validate();
				repaint();
			} else {
				statusBox.setText("That username is not valid.");
			}

		} else if (ev.getActionCommand().equals("Logout")) {
			//logout the user
			user = null;
			currentPanel.removeAll();
			remove(currentPanel);
			initDefaultGUI();
			validate();
			repaint();

		} else if (ev.getActionCommand().equals("Subscribe")) {
			//add whatever is typed to the user's subscribed to section
			String subscribeTo = subBox.getText();
			boolean work = userVal.newSubscribe(user, subscribeTo);
			if (work) {
				statusBox.setText("You have subscribed to " + subscribeTo);
			} else {
				statusBox.setText("There was a subscription error.");
			}
			
		}else if (ev.getActionCommand( ).equals("Refresh")){
			//refresh the tweet space to see the new tweets
			tweetSpace.setText("");
			LinkedList<String> honks = hr.getAllPosts(user);
			Iterator<String> honkIt = honks.iterator();
			while (honkIt.hasNext()) {
				tweetSpace.append(honkIt.next() + "\n");
			}

		} else {
			//otherwise it's a command from the combo box and we set the privacy setting to it
			JComboBox box = (JComboBox) ev.getSource();
			honkSet = (String) box.getSelectedItem();
		}
	}

	public static void main(String[] args) {
		new GUI();
	}
}