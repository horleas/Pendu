package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private JLabel nbremottrouver = new JLabel("Nombre de mots trouvés : A \n");
	private JLabel scoreactuel = new JLabel("Votre score actuel est de A point \n");
	private JLabel motatrouve = new JLabel();
	private Word word = new Word();
	boolean modif = false ;

	private JPanel pangame = new JPanel();
	private JPanel panlettre = new JPanel();
	private JPanel pantexte = new JPanel();
	private JPanel leftcontent = new JPanel();
	private JPanel rightcontent = new JPanel();

	
	private Dimension dimleft = new Dimension (400,600);
	private Dimension dimright = new Dimension (300,600);
	private Dimension dimbouton = new Dimension (50,30);
	private Dimension dimtexte = new Dimension (400,20);
	private Dimension dimlettre = new Dimension (400, 100);
	
	private  String tabchar[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	private char accenttabE[] = {'Ê','Ë','É','È','E'};
	private char accenttabA[] = {'Á','Â','À','Ã','Ä','A'};
	private char accenttabC[] = {'Ç','C'};
	private char accenttabO[] = {'Ö','O'};
	private char accenttabU[] = {'Ú','Û','Ù','Ü','U'};
	private char accenttabI[] = {'Ï','I'};
	
	
	private JButton[] tabButton = new JButton[tabchar.length];
	private static int nbrmot = 0 ; 
	private static int pts = 0 ;
	private int nbrfaute = 0 ;
	private int nbrcoup = 0 ;
	
	
	public GamePanel(){
		this.setBackground(Color.white);
		
		nbrmot = 0 ; 
		pts = 0 ;
		nbrfaute = 0 ;
		nbrcoup = 0 ;
		
		//Left CONTENT
		
		this.leftcontent.setLayout(new BorderLayout());
		Font fontpetit = new Font("Arial", Font.BOLD, 14);
		
		this.leftcontent.setPreferredSize(dimleft);
		
		
		//nb mot trouver
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
		motatrouve.setHorizontalAlignment(JLabel.CENTER);
		this.pantexte.add(motatrouve,BorderLayout.CENTER);
		
		this.leftcontent.add(pantexte, BorderLayout.NORTH);
		
		
		
		
		
		// bouton lettres
		//panlettre.setLayout(new GridLayout(0,7,5,5));
		panlettre.setPreferredSize(dimlettre);
		for(int j = 0 ;j< tabchar.length; j++ ){
			tabButton[j] = new JButton( tabchar[j].toUpperCase());
			tabButton[j].setPreferredSize(dimbouton);
			tabButton[j].addActionListener(new BoutonListener());
			this.panlettre.add(tabButton[j]);
			
		}
		
		this.panlettre.setBackground(Color.white);
		
		
		this.leftcontent.add(panlettre,BorderLayout.CENTER);
		leftcontent.setBackground(Color.white);

		
		
		// RIGHT CONTENT
		
		 ImageLabel image = new ImageLabel(Integer.toString(nbrfaute));
		 this.rightcontent.add(image, BorderLayout.CENTER);
		 
		 
		this.pangame.add(leftcontent, BorderLayout.CENTER);
		this.pangame.add(rightcontent, BorderLayout.WEST);


	}
	
	class BoutonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((JButton) e.getSource()).setEnabled(false);
			System.out.println("vous avez appuyer sur le bouton : " + ((JButton) e.getSource()).getText());
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
			rightcontent.add(new ImageLabel(Integer.toString(nbrfaute)).getPanel(), BorderLayout.CENTER);
			rightcontent.revalidate();
			}
			
			System.out.println("votre nbr de faute est : " + nbrfaute + ". Vous en etes a votre "+ nbrcoup+" coups");
			System.out.println(word.getWord());
			
			
			if (nbrfaute > 6){
				echec();
			}
			
		}}
	
	public void restartbouton (){
		for( JButton refreshbouton : tabButton ){
			refreshbouton.setEnabled(true);
		}
		  nbrfaute = 0;	
		  nbrcoup = 0 ;
		  
			do{
				word.initWord();
				}while(word.getWord().length()<4);
			
			
			motatrouve.setText(setSecretWordetoile());
			verifyword('-');
			
			rightcontent.removeAll();
			rightcontent.add(new ImageLabel(Integer.toString(nbrfaute)).getPanel(), BorderLayout.CENTER);
			rightcontent.revalidate();
		  
	}
	
	
	
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
		        
		        
		        nbrmot=0;
		        nbremottrouver.setText("Nombre de mots trouvés : "+ Integer.toString(nbrmot) );
		        
		        pts = 0;
		        scoreactuel.setText("Votre score actuel est de "+ Integer.toString(pts) + " points \n" );
			
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
		        
		        
		        nbrmot=0;
		        nbremottrouver.setText("Number of words founds : "+ Integer.toString(nbrmot) );
		        
		        pts = 0;		        
		        scoreactuel.setText("Your total Score is : "+ Integer.toString(pts) + " points \n" );
			
		}
		

	        
	        restartbouton();

	}
	
	public void victory(){
        
		nbrmot++;
		
		pts = pts + getScorebyfault(nbrfaute);
		
		String langnow = Fenetre.getLang();
		if(langnow=="French"){
			 
	        nbremottrouver.setText("Nombre de mots trouvés : "+ Integer.toString(nbrmot) );
	        
	        
	        scoreactuel.setText("Votre score actuel est de "+ Integer.toString(pts) + " point \n" );
			
			 JOptionPane jop = new JOptionPane();      
		        String mess = "Vous avez gagné\n";
		        mess += "en " + nbrcoup +" coups avec " + nbrfaute + " fautes\n" ;
		        mess += "vous gagner " +  getScorebyfault(nbrfaute) +" pts. Votre total de point est de  " +pts ;
		        jop.showMessageDialog(null, mess, "Gagné", JOptionPane.INFORMATION_MESSAGE);   
			
		}else if(langnow=="English"){
			
	        nbremottrouver.setText("Number of words founds : "+ Integer.toString(nbrmot) );
	        
	        
	        scoreactuel.setText("Your total Score is : "+ Integer.toString(pts) + " points \n" );
			
			 JOptionPane jop = new JOptionPane();      
		        String mess = "You win\n";
		        mess += "with " + nbrcoup +" try and with " + nbrfaute + " faults\n" ;
		        mess += "You earned " +  getScorebyfault(nbrfaute) +" points. Your new score is  " +pts ;
		        jop.showMessageDialog(null, mess, "Win", JOptionPane.INFORMATION_MESSAGE);   
			
		}

	        restartbouton();

	}
	
	
	public String setSecretWordetoile(){
		String secret = "";
		
		for (int k=0 ;k<word.getWord().length() ; k++ ){
			
			secret +="*";
		}
		
		return secret; 
	}
	
	
	public boolean wordcomplete(){
		boolean wordfound = true ;
		for (int k=0 ;k<word.getWord().length() ; k++ ){
			if (motatrouve.getText().charAt(k) == '*'){
				wordfound = false;
			}	
		}
		return wordfound ;
	}
	
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

		default: newpts = 0 ;
				break;
		}
		return newpts;
	}
	
	
	
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return this.pangame;
	}
	
	
	
	
}
