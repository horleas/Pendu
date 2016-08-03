package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Game Panel is the hearth of the game is composed with 2 big JPanel :
 * the right one use the ImageLabel and is called every time it need to show another image
 * the left one is composed of 3 parts : 
 * 			pantexte : host nbremottrouver , scoreactuel and motatrouve
 * 			panlettre : which host all the keybord button
 * 			panbonus : host 3 button
 * 
 * Inside this panel:
 * all the action of searching throught the word to place letters
 * call for a new word 
 * and update the score is done. 
 * Translation is done here too. 		
 * 
 * important method is are :
 * verify word() 
 * isCharInWord()
 * isButtonCharUp()
 * updatepoint()
 */
public class GamePanel extends JPanel {

	private JLabel nbremottrouver = new JLabel("Nombre de mots trouvés : A \n");
	private JLabel scoreactuel = new JLabel("Votre score actuel est de A point \n");
	private JLabel motatrouve = new JLabel();
	private Word word = new Word();
	boolean modif = false ;

	private JPanel pangame = new JPanel();
	private JPanel panlettre = new JPanel();
	private JPanel pantexte = new JPanel();
	private JPanel panbonus = new JPanel();
	private JPanel leftcontent = new JPanel();
	private JPanel rightcontent = new JPanel();

	/*
	 * my button for the merchant panel with the price of each element
	 */
	private JButton disable3 = new JButton();
	private int priceToDisable3 = 20 ;
	private JButton buy1letter = new JButton();
	private int priceToBuy1Letter = 40 ;
	private JButton upgradenbrfault = new JButton();
	private int priceToUpgradeNbrFault = 100 ;
	
	/*
	 * all the dimension used through this class
	 */
	private Dimension dimleft = new Dimension (400,600);
	private Dimension dimright = new Dimension (300,600);
	private Dimension dimbouton = new Dimension (50,30);
	private Dimension dimtexte = new Dimension (400,20);
	private Dimension dimlettre = new Dimension (400,100);
	private Dimension dimbonus = new Dimension (400,200);
	private Dimension dimboutonbonus = new Dimension (300,30);
	
	/*
	 * first tab is all the letters of Romanji alphabet but in French, we have some particularities such as letters with accent and C with cedilla
	 * in order to prevent bug like picking the letter E in a french word and looking for E instead of É, we hade this tab which, when the player
	 * choose to click of one of the following letters : E,A,C,O,U and I,for each letters of this tab, the word will be  completed 
	 */
	private  String tabchar[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	private char accenttabE[] = {'Ê','Ë','É','È','E'};
	private char accenttabA[] = {'Á','Â','À','Ã','Ä','A'};
	private char accenttabC[] = {'Ç','C'};
	private char accenttabO[] = {'Ö','Ô','O'};
	private char accenttabU[] = {'Ú','Û','Ù','Ü','U'};
	private char accenttabI[] = {'Ï','Î','I'};
	
	/*
	 * some variable used to calculate the score or to count the mistake made in the game
	 */
	private JButton[] tabButton = new JButton[tabchar.length];
	private static int nbrmot = 0 ; 
	private static int pts = 0 ;
	private int nbrfaute = 0 ;
	private int nbrcoup = 0 ;
	private int nbrfautemax = 6 ;
	private int maxDisable3 = 3 ;
	private int varDisable3 = 0 ;
	private int valupgradenbrfault = 0;
	private int maxupgradenbrfault = 3 ;
	
	private Font normalblack =new Font("Courrier",Font.CENTER_BASELINE,12);
	private Font errorred =new Font("Courrier",Font.BOLD,12);

	
	
	public GamePanel(){
		this.setBackground(Color.white);
		
		nbrmot = 0 ; 
		pts = 0 ;
		nbrfaute = 0 ;
		nbrcoup = 0 ;
		
		/*
		 * Left Content initialization
		 */
		
		this.leftcontent.setLayout(new BorderLayout());
		Font fontpetit = new Font("Arial", Font.BOLD, 14);		
		this.leftcontent.setPreferredSize(dimleft);
		
		
		//nb word found
		nbremottrouver.setPreferredSize(dimtexte);	
		String langnow = Fenetre.getLang();
		if(langnow=="French"){
			nbremottrouver.setText("Nombre de mots trouvés : "+ Integer.toString(nbrmot) );
			scoreactuel.setText("Votre score actuel est de "+ Integer.toString(pts) + " point \n" );
			}
		else if(langnow=="English"){ 
	        nbremottrouver.setText("Number of words founds : "+ Integer.toString(nbrmot) );	        
	        scoreactuel.setText("Your total Score is : "+ Integer.toString(pts) + " points \n" );
		}
		
		
		nbremottrouver.setFont(fontpetit);
		nbremottrouver.setHorizontalAlignment(JLabel.CENTER);
		this.pantexte.setPreferredSize(new Dimension(400,75));
		this.pantexte.add(nbremottrouver,BorderLayout.NORTH);
				
		//Score actuel
		scoreactuel.setPreferredSize(dimtexte);
		scoreactuel.setFont(fontpetit);
		scoreactuel.setHorizontalAlignment(JLabel.CENTER);
		this.pantexte.add(scoreactuel,BorderLayout.CENTER);	
		this.pantexte.setBackground(Color.white);
		
		
		//mot a trouver
		this.motatrouve.setForeground(Color.blue);
		motatrouve.setText(this.setSecretWordetoile());
		
		//set the - to appear in the word ( all optional caractère outside the alphabet must be here for the first word )
		verifyword('-');
		
		/*
		 * get the first and last letter to help the player in easy mode
		 * if the easy mode is set to false or the word is < 4 letters, the string return by easymode could be empty
		 * so we concaten "  " to it. when the function using lettreeasyrecup will check, it will get space and not void and so prevent the bug
		 */
		String lettreeasyrecup = easymode();
		lettreeasyrecup = lettreeasyrecup.concat("  ");
		

		motatrouve.setHorizontalAlignment(JLabel.CENTER);
		this.pantexte.add(motatrouve,BorderLayout.CENTER);
		
		this.leftcontent.add(pantexte, BorderLayout.NORTH);
		
		
		/*
		 * take the alphabet and create a button for each letters in it 
		 */
		panlettre.setPreferredSize(dimlettre);
		for(int j = 0 ;j< tabchar.length; j++ ){
			tabButton[j] = new JButton( tabchar[j].toUpperCase());
			tabButton[j].setPreferredSize(dimbouton);
			tabButton[j].addActionListener(new BoutonListener());
			this.panlettre.add(tabButton[j]);
			
		}
		
		/*
		 * in easy mode, some letters are already place.
		 * In order to avoid the player clicking on those button with set them disabled
		 * by comparing the letters get by easymod in the string lettreeasyrecup with the char inside the button
		 * if the lettreeasyrecup is "  " which is 2 spaces , any button will be disabled
		 *
		 */
		for( JButton refreshbouton : tabButton ){
			if(refreshbouton.getText().charAt(0)== lettreeasyrecup.charAt(0)||refreshbouton.getText().charAt(0)== lettreeasyrecup.charAt(1)){
				refreshbouton.setEnabled(false);
			}
			
		}
		this.panlettre.setBackground(Color.white);
		this.leftcontent.add(panlettre,BorderLayout.CENTER);
		
		
		/*
		 * The bonus panel is created to offer to the player the opportunity to exchange some of his points with different bonus such as :
		 * 		disabling 3 useless letters
		 * 		placing 1 random letter with each occurences in the current word
		 * 		have an additional try for the game( up to 10 try)
		 */
		
		this.panbonus.setPreferredSize(dimbonus);
		
		// disable 3 letter which are not in the word for 20 pts
		this.panbonus.add(disable3);
		disable3.setPreferredSize(dimboutonbonus);
		disable3.setEnabled(false);
		disable3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("remove 3 useless letter");

				varDisable3 = varDisable3+1;
				updatePoint(20,0);
				
				
				int randomletter = 0 ;
				char charToTest = 'a';
				boolean booIsCharInWord = false ;
				

				for(int i=0;i<3;i++){
					do{
						randomletter = (int) (Math.random()*tabchar.length);
						charToTest = tabchar[randomletter].toUpperCase().charAt(0);
						System.out.println(word.getWord()+" "+randomletter +charToTest);
						 
						/*
						 * must check for every special letters in french language to avoid disabling a button where the letters is inside the word with accent
						 */						
						if (charToTest == 'E'){
							booIsCharInWord = !isCharInWord(word.getWord(),accenttabE);
						}else if (charToTest == 'A'){
							booIsCharInWord = !isCharInWord(word.getWord(),accenttabA);
						}else if (charToTest == 'O'){
							booIsCharInWord = !isCharInWord(word.getWord(),accenttabO);
						}else if (charToTest == 'U'){
							booIsCharInWord = !isCharInWord(word.getWord(),accenttabU);
						}else if (charToTest == 'C'){
							booIsCharInWord = !isCharInWord(word.getWord(),accenttabC);
						}else if (charToTest == 'I'){
							booIsCharInWord = !isCharInWord(word.getWord(),accenttabI);
						}else{			
							booIsCharInWord = !isCharInWord(word.getWord(),charToTest);
						}
						
						
					/*
					 * the button will be disable if the letters isn't in the word and the button isn't already disabled	
					 */
					}while( (!booIsCharInWord) || (!isButtonCharUp(charToTest)));
					
					for( JButton refreshbouton : tabButton ){
						if(refreshbouton.getText().charAt(0)== charToTest){
							refreshbouton.setEnabled(false);
						}
					}
					System.out.println(charToTest +" is not in the word : "+word.getWord()+"\n varDisable3 is : "+varDisable3);
				}
			}
		});
		
		
		// buy a random letter for 40 pts
		this.panbonus.add(buy1letter);
		buy1letter.setEnabled(false);
		buy1letter.setPreferredSize(dimboutonbonus);
		buy1letter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				updatePoint(40,0);

				
				System.out.println("place 1 letter");
				int randomletter = 0 ;
				char charToTest = 'a';
				boolean booIsCharInWord = false ;
				
				/*
				 * take a random letters to put inside the word ( take care of special french letters)
				 * if the button isn't already pressed
				 */
				do{
					randomletter = (int) (Math.random()*tabchar.length);
					charToTest = tabchar[randomletter].toUpperCase().charAt(0);
					System.out.println(word.getWord()+" "+randomletter +charToTest);
					 
					/*
					 * check the presence of the letters in the word
					 */
					if (charToTest == 'E'){
						booIsCharInWord = !isCharInWord(word.getWord(),accenttabE);
					}else if (charToTest == 'A'){
						booIsCharInWord = !isCharInWord(word.getWord(),accenttabA);
					}else if (charToTest == 'O'){
						booIsCharInWord = !isCharInWord(word.getWord(),accenttabO);
					}else if (charToTest == 'U'){
						booIsCharInWord = !isCharInWord(word.getWord(),accenttabU);
					}else if (charToTest == 'C'){
						booIsCharInWord = !isCharInWord(word.getWord(),accenttabC);
					}else if (charToTest == 'I'){
						booIsCharInWord = !isCharInWord(word.getWord(),accenttabI);
					}else{			
						booIsCharInWord = !isCharInWord(word.getWord(),charToTest);
					}
					
					
					/*
					 * check for the button still up
					 */
				}while( (booIsCharInWord) || (!isButtonCharUp(charToTest)));
				
				/*
				 * disable the concern button
				 */
				for( JButton refreshbouton : tabButton ){
					if(refreshbouton.getText().charAt(0)== charToTest){
						refreshbouton.setEnabled(false);
					}
					
				}
				
				/*
				 * add the letters to the word
				 */
				if (charToTest == 'E'){
					verifyword(accenttabE);
				}else if (charToTest == 'A'){
					verifyword(accenttabA);
				}else if (charToTest == 'O'){
					verifyword(accenttabO);
				}else if (charToTest == 'U'){
					verifyword(accenttabU);
				}else if (charToTest == 'C'){
					verifyword(accenttabC);
				}else if (charToTest == 'I'){
					verifyword(accenttabI);
				}else{			
					verifyword(charToTest);
				}
				
				if(pts<40){
					buy1letter.setEnabled(false);
				}else{
					buy1letter.setEnabled(true);
				}

			}

		});
		
		

		/*
		 *  augment nbrfault before lose by 1 for 100pts ( max 3)
		 *  the math to load the correct image is :
		 *  
		 *  nbrfaute-valupgradenbrfault+3
		 *  
		 *  when the player begin the game, he is at 0 mistake and wll be hanged at 7 mistake 
		 *  but with this new mechanic, he is at 3 fault about 10 and after each upgrade the valupgradenbrfault increase so :
		 *  nbrfaute : 0~7
		 *  valupgradenbrfault : 0~3
		 *  
		 *  valupgradenbrfault totally upgraded and beginning of the game : 0-3+3 = 0
		 *  valupgradenbrfault totally upgraded and player loose : 10 - 3 + 3 10
		 */
		this.panbonus.add(upgradenbrfault);
		upgradenbrfault.setEnabled(false);
		upgradenbrfault.setPreferredSize(dimboutonbonus);
		upgradenbrfault.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				valupgradenbrfault++;
				updatePoint(100,0);
				
				rightcontent.removeAll();
				rightcontent.add(new ImageLabel(Integer.toString(nbrfaute-valupgradenbrfault+3)).getPanel(), BorderLayout.CENTER);
				rightcontent.revalidate();

			}
			
			
		});

		this.panbonus.setBackground(Color.white);
		this.leftcontent.add(panbonus, BorderLayout.SOUTH);
		leftcontent.setBackground(Color.white);
		
		

		
		
		/*
		 * Right content : imageloarder according to the number of mistake
		 */
		
		 ImageLabel image = new ImageLabel(Integer.toString(nbrfaute-valupgradenbrfault+3));
		 this.rightcontent.add(image, BorderLayout.CENTER);
		 
		 
		this.pangame.add(leftcontent, BorderLayout.CENTER);
		this.pangame.add(rightcontent, BorderLayout.WEST);

	 updatePoint(0,0);

	}
	
	/*
	 * this button listener is shared with all the button/letters of the alphabets.
	 * when pressed, he will set himself disabled, then he will get is own text 
	 * and will verify if this letters is in the word. ( special french letters again)
	 * and it increase the amount of try. if the letters is in verifyword,
	 * then modif will be true and the player can continue to play
	 * if modif is false then , the playe rmake a mistake so the mistake counter is increased
	 * and the image label must be refresh with the correct pictures.
	 * 
	 * if the player exceed the max mistake ( 7 + valupgradenbrfault ), he have failed and the echec method is called.
	 */
	class BoutonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((JButton) e.getSource()).setEnabled(false);
			//System.out.println("you pressed the button  : " + ((JButton) e.getSource()).getText());
			char lettretape =((JButton) e.getSource()).getText().charAt(0);
			
			if (lettretape == 'E'){
				verifyword(accenttabE);
			}else if (lettretape == 'A'){
				verifyword(accenttabA);
			}else if (lettretape == 'O'){
				verifyword(accenttabO);
			}else if (lettretape == 'U'){
				verifyword(accenttabU);
			}else if (lettretape == 'C'){
				verifyword(accenttabC);
			}else if (lettretape == 'I'){
				verifyword(accenttabI);
			}			else{			
				verifyword(lettretape);
			}
			nbrcoup++;
			if(modif){
			}
			else{
			nbrfaute++;
			rightcontent.removeAll();
			rightcontent.add(new ImageLabel(Integer.toString(nbrfaute-valupgradenbrfault+3)).getPanel(), BorderLayout.CENTER);
			rightcontent.revalidate();
			}
			
			System.out.println("mistake counter : " + nbrfaute + ".you are at your "+ nbrcoup+" try");
			System.out.println(word.getWord());
			
			
			if (nbrfaute > nbrfautemax+valupgradenbrfault){
				echec();
			}
			
		}}
	
	/*
	 * function called when a word is complete or the player failed to be in the top 10 and a new game is lauched
	 * it reset all the button to enable and get a new word to display
	 * verify if there is not the linker of french composed word '-',
	 * reset the variable and refresh the text and pictures
	 */
	public void restartbouton (){
		for( JButton refreshbouton : tabButton ){
			refreshbouton.setEnabled(true);
		}
  
			do{
				word.initWord();
				}while(word.getWord().length()<4);
			
			
			motatrouve.setText(setSecretWordetoile());
			verifyword('-');
			String lettreeasyrecup = easymode();
			lettreeasyrecup = lettreeasyrecup.concat("  ");
			  nbrfaute = 0;	
			  nbrcoup = 0 ;
			  modif = true ;
			  varDisable3 = 0;
			for( JButton refreshbouton : tabButton ){
				if(refreshbouton.getText().charAt(0)== lettreeasyrecup.charAt(0)||refreshbouton.getText().charAt(0)== lettreeasyrecup.charAt(1)){
					refreshbouton.setEnabled(false);
				}
				
			}

			updatePoint(0,0);
			
			rightcontent.removeAll();
			rightcontent.add(new ImageLabel(Integer.toString(nbrfaute-valupgradenbrfault+3)).getPanel(), BorderLayout.CENTER);
			rightcontent.revalidate();
		  
	}
	
	
	/*
	 * called when the player failed to find a word.
	 * Show in a JOptionPane the current word and resume the score and number of word found
	 * then verify if the player is in the top 10 to propose him to write is name on the leaderboard
	 * if the player don't write anything in the inputDialogue, we attribut him the anonymous name
	 * and display the new score panel with him on it
	 * 
	 * If he can't be in the leaderboard, a new game is launch (restartbouton)
	 */
	public void echec(){
		

		String langnow = Fenetre.getLang();
		if(langnow=="French"){
			 
			 JOptionPane jop = new JOptionPane();      
		        String mess = "Vous avez perdu\n";
		        mess += "le mot était " + word.getWord() +"\n";
		        mess += "en " + nbrcoup +" coups \n";
		        mess += "Vous avez accumulé " + pts +" points en "+nbrmot + " mot \n";
		        jop.showMessageDialog(null, mess, "Perdu", JOptionPane.INFORMATION_MESSAGE);  
		        

		        
		        if( pts >= ScorePanel.getworstscore()){
		        	System.out.println("vous êtes dans le top 10");
		        	 
		        	JOptionPane joppseudo = new JOptionPane();
		        	String pseudo = joppseudo.showInputDialog(null, "Veuillez écrire votre pseudo !", "Top 10 !", JOptionPane.QUESTION_MESSAGE);
		            if(pseudo==null){
		            	pseudo = "Anonyme";
		            }
		            
		            ScorePanel.newScore(new Score(pseudo,pts,nbrmot));
		            
		            
		            
					Fenetre.conteneur.removeAll();
					Fenetre.conteneur.add(ScorePanel.getPanel(), BorderLayout.CENTER);
					Fenetre.conteneur.revalidate();
		            
		        }
		        			
		}else if(langnow=="English"){
			
			 JOptionPane jop = new JOptionPane();      
		        String mess = "Vous lost\n";
		        mess += "The word was " + word.getWord() +"\n";
		        mess += "You have earned " + pts +" points with "+nbrmot + " words \n";
		        jop.showMessageDialog(null, mess, "Game Over", JOptionPane.INFORMATION_MESSAGE);    
		        

		        
		        if( pts >= ScorePanel.getworstscore()){
		        	System.out.println("You are in the top 10");
		        	 
		        	JOptionPane joppseudo = new JOptionPane();
		        	String pseudo = joppseudo.showInputDialog(null, "Write your pseudo !", "Top 10 !", JOptionPane.QUESTION_MESSAGE);
		            if(pseudo==null){
		            	pseudo = "Anonymous";
		            }
		            ScorePanel.newScore(new Score(pseudo,pts,nbrmot));
		            
		            
		            
					Fenetre.conteneur.removeAll();
					Fenetre.conteneur.add(ScorePanel.getPanel(), BorderLayout.CENTER);
					Fenetre.conteneur.revalidate();
		            
		        }
		}
		

	        
	        restartbouton();

	}
	
	/*
	 * when a player complete a word , update nbrmot and update the point of the player depending of his number of mistake
	 * then show him on an OptionPane the resume of his current game before continue the game by provinding a new word (restartbutton) 
	 */
	public void victory(){
        
		nbrmot++;
		
		updatePoint(0,getScorebyfault(nbrfaute));
		
		String langnow = Fenetre.getLang();
		if(langnow=="French"){
			 
	        nbremottrouver.setText("Nombre de mots trouvés : "+ Integer.toString(nbrmot) );
	        
	        
	        scoreactuel.setText("Votre score actuel est de "+ Integer.toString(pts) + " point \n" );
			
			 JOptionPane jop = new JOptionPane();      
		        String mess = "Vous avez gagné\n";
		        mess += "en " + nbrcoup +" coups avec " + nbrfaute + " fautes " ;
		        if(Fenetre.isBooeasymode()){mess +="avec le mode facile. \n"; }
		        mess += "vous gagner " +  getScorebyfault(nbrfaute) +" pts. Votre total de point est de  " +pts ;
		        jop.showMessageDialog(null, mess, "Gagné", JOptionPane.INFORMATION_MESSAGE);   
			
		}else if(langnow=="English"){
			
	        nbremottrouver.setText("Number of words founds : "+ Integer.toString(nbrmot) );
	        
	        
	        scoreactuel.setText("Your total Score is : "+ Integer.toString(pts) + " points \n" );
			
			 JOptionPane jop = new JOptionPane();      
		        String mess = "You win\n";
		        mess += "with " + nbrcoup +" try and with " + nbrfaute + " faults " ;
		        if(Fenetre.isBooeasymode()){mess +="with the easy mode selected. \n"; }
		        mess += "You earned " +  getScorebyfault(nbrfaute) +" points. Your new score is  " +pts ;
		        jop.showMessageDialog(null, mess, "Win", JOptionPane.INFORMATION_MESSAGE);   
			
		}

	        restartbouton();

	}
	
	/*
	 * set the secret word to be displayed on the panel to have the same length than the word looked about
	 */
	public String setSecretWordetoile(){
		String secret = "";
		
		for (int k=0 ;k<word.getWord().length() ; k++ ){
			
			secret +="*";
		}
		
		return secret; 
	}
	
	/*
	 * verify if the word is completed by looking if there is stars '*' left inside
	 */
	public boolean wordcomplete(){
		boolean wordfound = true ;
		for (int k=0 ;k<word.getWord().length() ; k++ ){
			if (motatrouve.getText().charAt(k) == '*'){
				wordfound = false;
			}	
		}
		return wordfound ;
	}
	
	/*
	 * verifyword(char c) take a char to look if it is inside the word.
	 * check at each place if the char correspond to the word char.
	 * if yes, modif = true to indicate the success of the player guessing the letter
	 * then call wordcomplete to check if victory must be called
	 */
	public void verifyword(char c){
		String wordupdate= motatrouve.getText();
		String wordall= word.getWord();
		modif = false ;
	
		for(int k=0;k <wordall.length();k++){
			if (wordall.charAt(k) == c){			
				wordupdate = wordupdate.substring(0, k)+c+wordupdate.substring(k+1);
			
				modif = true;
			}
		}
			
		if(modif){
			motatrouve.setText(wordupdate);
			System.out.println("lettre trouver " + wordupdate);
			
			if(wordcomplete()){
				victory();	
			}		
		}
	}
	
	/*
	 * same than the previous version but with tab for special french letters
	 */
	public void verifyword(char[] c){
		String wordupdate= motatrouve.getText();
		String wordall= word.getWord();
		modif = false ;
		
		for(int j = 0; j<c.length;j++){
			for(int k=0;k <wordall.length();k++){
				if (wordall.charAt(k) == c[j]){			
					wordupdate = wordupdate.substring(0, k)+c[j]+wordupdate.substring(k+1);
				
					modif = true;
				}
			}
		}	
		if(modif){
			motatrouve.setText(wordupdate);
			System.out.println("lettre trouver " + wordupdate);
			
			if(wordcomplete()){
				victory();	
			}		
		}
	}
	
	
	/*
	 * method to get point according to the number of faults
	 * if easy mode is actived, then the amount of points are divided by 2
	 */
	public int getScorebyfault(int nbrfautes){
		int newpts = 0 ;
		switch(nbrfautes){
		case 0 : newpts = 100;
				break;
		case 1 : newpts = 75;
				break;
		case 2 : newpts = 50;
				break;
		case 3 : newpts = 30;
				break;
		case 4 : newpts = 15;
				break;
		case 5 : newpts = 10;
				break;
		case 6 : newpts = 5;
				break;

		default: newpts = 5 ;
				break;
		}
		if(Fenetre.isBooeasymode()){newpts = newpts/2;}
		
		return newpts;
	}
	
	/*
	 * easymode is done if the player choose to check the box on the menu.
	 * it provide help to the player if the word has more than 4 letters
	 * by giving the first letter and the last if the word has more than 7 letters.
	 */
	public String easymode(){
		String lettretrouver ="";
		
		if(Fenetre.isBooeasymode()&& word.getWord().length()> 4 )
		{
			char firstchar =(char)word.getWord().charAt(0);
			boolean foundfirst = false;
			
			//breaking go to next 
			while(true){
			
				//must take care of french word which begins with an accent/Special letter.
				for(char testchar :accenttabE){
					if (firstchar == testchar){
							verifyword(accenttabE);
							foundfirst = true;
							break;
						}
				}
				if (foundfirst){
					firstchar='E';	// to be sure to return the name text of the button associate because É != E
					break;
					}
				
				
				for(char testchar :accenttabA){
					if (firstchar == testchar){
							verifyword(accenttabA);
							foundfirst = true;
							break;
						}
				}
				if (foundfirst){
					firstchar='A';
					break;
					}
				
				
				
				for(char testchar :accenttabO){
					if (firstchar == testchar){
							verifyword(accenttabO);
							foundfirst = true;
							break;
						}
				}
				if (foundfirst){
					firstchar='O';
					break;
					}
				
				for(char testchar :accenttabU){
					if (firstchar == testchar){
							verifyword(accenttabU);
							foundfirst = true;
							break;
						}
				}
				if (foundfirst){
					firstchar='U';
					break;
					}
				
				
				for(char testchar :accenttabI){
					if (firstchar == testchar){
							verifyword(accenttabI);
							foundfirst = true;
							break;
						}
				}
				if (foundfirst){
					firstchar='I';
					break;
					}
				
				for(char testchar :accenttabC){
					if (firstchar == testchar){
							verifyword(accenttabC);
							foundfirst = true;
							break;
						}
				}
				if (foundfirst){
					firstchar='C';
					break;
					}
				
				
				verifyword(firstchar);
				break;
			}
			lettretrouver = lettretrouver.concat(Character.toString(firstchar));
			System.out.println("first letter : "+firstchar);
			if(word.getWord().length()> 7 )
			{
				char lastchar =(char)word.getWord().charAt(word.getWord().length()-1);
				boolean foundlast = false;
				
				//breaking go to next 
				while(true){
				
					//must take care of french word which end with an accent/Special letter.
					for(char testchar :accenttabE){
						if (lastchar == testchar){
								verifyword(accenttabE);
								foundlast = true;
								break;
							}
					}
					if (foundlast){
						lastchar='E';
						break;
						}
					
					
					for(char testchar :accenttabA){
						if (lastchar == testchar){
								verifyword(accenttabA);
								foundlast = true;
								break;
							}
					}
					if (foundlast){
						lastchar='A';
						break;
						}
					
					
					for(char testchar :accenttabO){
						if (lastchar == testchar){
								verifyword(accenttabO);
								foundlast = true;
								break;
							}
					}
					if (foundlast){
						lastchar='O';
						break;
						}
					
					
					for(char testchar :accenttabU){
						if (lastchar == testchar){
								verifyword(accenttabU);
								foundlast = true;
								break;
							}
					}
					if (foundlast){
						lastchar='U';
						break;
						}
					
					
					for(char testchar :accenttabI){
						if (lastchar == testchar){
								verifyword(accenttabI);
								foundlast = true;
								break;
							}
					}
					if (foundlast){
						lastchar='I';
						break;
						}
					
					
					for(char testchar :accenttabC){
						if (lastchar == testchar){
								verifyword(accenttabC);
								foundlast = true;
								break;
							}
					}
					if (foundlast){
						lastchar='C';
						break;
						}
					
					verifyword(lastchar);
					break;
				}
				lettretrouver = lettretrouver.concat(Character.toString(lastchar));
				System.out.println("last letter : "+lastchar);
			}
		}
		System.out.println(" letters in first and last position : "+lettretrouver);
		return lettretrouver;
	}
	
	/*
	 * verify if the letter is in the word
	 */
	public boolean isCharInWord(String word, char charToTest) {
		
		for(int i=0;i< word.length();i++)
			if(word.charAt(i)==charToTest){
				return true;
			}

		return false;
	}
	
	/*
	 * verify if one of letter of the tab is in the word
	 */
	public boolean isCharInWord(String word, char charToTest[]) {
		
		for(int j=0;j<charToTest.length ;j++){
			for(int i=0;i< word.length();i++)
				if(word.charAt(i)==charToTest[j]){
					return true;
				}
		}
		return false;
	}
	
	/*
	 * verify if the button with the corresponding char is enable or disabled
	 */
	private boolean isButtonCharUp(char charToTest) {
		for( JButton refreshbouton : tabButton ){
			if(refreshbouton.getText().charAt(0)== charToTest){
				if(refreshbouton.isEnabled()==true){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
	
	
	/*
	 * update point is used to add or remove point by completing a word or buying a bonus
	 * then set all the text according to the lang chosen
	 */
	private void updatePoint(int cost,int gain){
		
		pts = pts - cost;
		pts = pts + gain;
		
		
		
		
		String langnow = Fenetre.getLang();
		if(langnow=="French"){
			disable3.setText("Enleve 3 lettres inutiles (20pts)");
			buy1letter.setText("Place toutes les itérations d'une lettre (40pts)");
			upgradenbrfault.setText("Nombre d'erreur max : "+(nbrfautemax+valupgradenbrfault)+" => "+(nbrfautemax+valupgradenbrfault+1)+" (100pts)");
			}
		else if(langnow=="English"){ 
			disable3.setText("Remove 3 useless char (20pts)");
			buy1letter.setText("Place every iteration of  a char (40pts)");
			upgradenbrfault.setText("Improve max error "+nbrfautemax+" to "+(nbrfautemax+1)+" (100pts)");
		}
		
		if(langnow=="French"){
			scoreactuel.setText("Votre score actuel est de "+ Integer.toString(pts) + " point \n" );
			}
		else if(langnow=="English"){        
	        scoreactuel.setText("Your total Score is : "+ Integer.toString(pts) + " points \n" );
		}
		
		
		//Disable3
		if(pts>=priceToDisable3  && varDisable3 < maxDisable3) {
			disable3.setFont(normalblack);
			disable3.setEnabled(true);
			}
		else{
			if(pts<priceToDisable3){
				if(langnow=="French"){
					disable3.setText("Pas assez de points (20pts)");
				}
				else if(langnow=="English"){ 
					disable3.setText("Not enough points  (20pts)");
				}
			}else{
				if(langnow=="French"){
					disable3.setText("Maximum 3 utilisations par mot (20pts)");
				}
				else if(langnow=="English"){ 
					disable3.setText("Only 3 use by word (20pts)");

				}
			}

			disable3.setFont(errorred);
			disable3.setEnabled(false);
			}
		
		//BuyLetter
		if(pts>=priceToBuy1Letter){
			buy1letter.setFont(normalblack);
			buy1letter.setEnabled(true);
			}
		else{
			if(langnow=="French"){
				buy1letter.setText("Pas assez de points (40pts)");
			}
			else if(langnow=="English"){ 
				buy1letter.setText("Not enough points (40pts)");
			}
			buy1letter.setFont(errorred);
			buy1letter.setEnabled(false);
			}
		
		//UPgrade NBRFAULT
		if(pts>=priceToUpgradeNbrFault && valupgradenbrfault<maxupgradenbrfault){
			upgradenbrfault.setFont(normalblack);
			upgradenbrfault.setEnabled(true);
			}
		else{
			if(pts < priceToUpgradeNbrFault){
				if(langnow=="French"){
					upgradenbrfault.setText("Pas assez de points (100pts)");
				}
				else if(langnow=="English"){ 
					upgradenbrfault.setText("Not enough points  (100pts)");
				}
			}else{
				if(langnow=="French"){
					upgradenbrfault.setText("Seulement augmentable 3 fois");
				}
				else if(langnow=="English"){ 
					upgradenbrfault.setText("Only upgradable 3 times");

				}
			}
			
			upgradenbrfault.setFont(errorred);
			upgradenbrfault.setEnabled(false);
		
		}
		
	}

	
	
	public JPanel getPanel() {

		return this.pangame;
	}




	
	
	
	
}
