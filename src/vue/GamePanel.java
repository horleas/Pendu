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
	private JButton[] tabButton = new JButton[tabchar.length];
	private int nbrmot = 0 ; 
	private int pts = 0 ;
	private int nbrfaute = 0 ;
	
	
	public GamePanel(){
		this.setBackground(Color.white);
		
		
		//Left CONTENT
		
		this.leftcontent.setLayout(new BorderLayout());
		Font fontpetit = new Font("Arial", Font.BOLD, 14);
		
		this.leftcontent.setPreferredSize(dimleft);
		
		
		//nb mot trouver
		nbremottrouver.setPreferredSize(dimtexte);
		nbremottrouver.setText("Nombre de mots trouvés : "+ Integer.toString(nbrmot) );
		nbremottrouver.setFont(fontpetit);
		nbremottrouver.setHorizontalAlignment(JLabel.CENTER);

		this.pantexte.setPreferredSize(new Dimension(400,75));
		this.pantexte.add(nbremottrouver,BorderLayout.NORTH);
		
		
		//Score actuel
		scoreactuel.setPreferredSize(dimtexte);
		scoreactuel.setText("Votre score actuel est de "+ Integer.toString(pts) + " point \n" );
		scoreactuel.setFont(fontpetit);
		scoreactuel.setHorizontalAlignment(JLabel.CENTER);

		this.pantexte.add(scoreactuel,BorderLayout.CENTER);
		
		this.pantexte.setBackground(Color.white);
		
		
		//mot a trouver

		this.motatrouve.setForeground(Color.blue);
		motatrouve.setText("**************");
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
			nbrfaute++;
			System.out.println("votre nbr de faute est : " + nbrfaute);
			System.out.println(word.getWord());
			rightcontent.removeAll();
			rightcontent.add(new ImageLabel(Integer.toString(nbrfaute)).getPanel(), BorderLayout.CENTER);
			rightcontent.revalidate();
			
			/*if(((JButton) e.getSource()).getText() == tabButton[25].getText() ){
				restartbouton();
			}*/
			
			if (nbrfaute > 6){
				echec();
			}
			
		}}
	
	public void restartbouton (){
		for( JButton refreshbouton : tabButton ){
			refreshbouton.setEnabled(true);
		}
		  nbrfaute = 0;	
		  word.initWord();
	}
	
	public void echec(){
		 JOptionPane jop = new JOptionPane();      
	        String mess = "Vous avez perdu\n";
	        mess += "le mot était " + word.getWord() +"\n";     
	        jop.showMessageDialog(null, mess, "Perdu", JOptionPane.INFORMATION_MESSAGE);   
	        
	        restartbouton();

	}
	
	public String setSecretWord(){
		String secret = "";
		
		return secret; 
	}
	
	
	
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return this.pangame;
	}
	
	
	
	
}
