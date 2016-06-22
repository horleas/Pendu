package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Fenetre extends JFrame{
	
	private Dimension size ;
	AccueilPanel accueil = new AccueilPanel();
	RulesPanel rules = new RulesPanel();
	ScorePanel scorepanel = new ScorePanel();
	JPanel pan = new JPanel();
	JMenuBar menubar = new JMenuBar();
	JMenu fichier = new JMenu("Fichier");
	JMenuItem nouveau = new JMenuItem("Nouveau");
	JMenuItem score = new JMenuItem("Score");
	JMenuItem quitter = new JMenuItem("Quitter");
	JMenu apropos = new JMenu("A propos");
	JMenuItem regle = new JMenuItem("R�gle");
	JMenuItem question = new JMenuItem("?");
	JMenuItem test = new JMenuItem ("test");
	private JComboBox langchoice= new JComboBox();
	static JPanel conteneur = new JPanel();
	public static String language = "French";
	
	
	public static int i = 0;

	
	
	public Fenetre(){
		
		this.setTitle("Pendu by Horleas");
		this.setSize(900, 600);
		this.setAlwaysOnTop(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(Color.white);
		this.setContentPane(conteneur);

		initBarMenu();
		
		
		conteneur.add(accueil,BorderLayout.CENTER);
		
	}



	private void initBarMenu() {
		
		SetlangMenu();
		
		menubar.add(fichier);
		fichier.setMnemonic('f');
		fichier.add(nouveau);
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		nouveau.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				conteneur.removeAll();
				conteneur.add(new GamePanel().getPanel(), BorderLayout.CENTER);
				conteneur.revalidate();			
			}
	     });
		
		
		
		fichier.add(score);
		score.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
		score.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				conteneur.removeAll();
				conteneur.add(new ScorePanel().getPanel(), BorderLayout.CENTER);
				conteneur.revalidate();
				
				
			}
	     });
		
		
		fichier.addSeparator();
		fichier.add(quitter);
		quitter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK));
		
		menubar.add(apropos);
		apropos.setMnemonic('a');
		apropos.add(regle);
		regle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				conteneur.removeAll();
				conteneur.add(new RulesPanel().getPanel(), BorderLayout.CENTER);
				conteneur.revalidate();
				
			}
			
			
		});
		apropos.add(question);
		
	    //Ajout de ce que doit faire le "?"
	    question.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	    	  
	    	  String langnow = getLang();
	    	  if(langnow=="French"){
	    		  JOptionPane jop = new JOptionPane();      
	  	        String mess = "Pendu d'Horleas\n";
	  	        mess += "R�aliser via le tutorial d'apprentissage d' OpenClassRoom\n";     
	  	        jop.showMessageDialog(null, mess, "� propos", JOptionPane.INFORMATION_MESSAGE); 
	  			
	  		}else if(langnow=="English"){
	  			
	  			 JOptionPane jop = new JOptionPane();      
	 	        String mess = "Hangman by Horleas\n";
	 	        mess += "Idea from the tutorial of OpenClassRoom for Java learning\n";     
	 	        jop.showMessageDialog(null, mess, "About", JOptionPane.INFORMATION_MESSAGE); 
	  			
	  		}
	    	  
	          
    
			conteneur.removeAll();
			conteneur.add(new AccueilPanel().getPanel(), BorderLayout.CENTER);
			conteneur.revalidate();
	      }            
	    });
	    

	    // JComboboc choice of Language
	    langchoice.addItem("Fran�ais");
	    langchoice.addItem("English");
	    langchoice.setMaximumSize(new Dimension(100,20));
	    langchoice.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(langchoice.getSelectedItem().toString()=="Fran�ais"){
					setLang("French");
					SetlangMenu();
				}else if(langchoice.getSelectedItem().toString()=="English"){
					setLang("English");
					SetlangMenu();
				}
				
				conteneur.removeAll();
				conteneur.add(new AccueilPanel().getPanel(), BorderLayout.CENTER);
				conteneur.revalidate();

			}
			
	    	
	    });
	    
	    menubar.add(langchoice);
	    
	    //---------------ZONE DE TEST ---------------------------------------------//	    
	    /*
	    apropos.add(test);
	    test.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				conteneur.removeAll();
				conteneur.add(new ScorePanel().getPanel(), BorderLayout.CENTER);
				conteneur.revalidate();
				
				
			}
	     });*/
	    
	    //---------------FIN DE LA ZONE DE TEST ---------------------------------------------//
		
		this.setJMenuBar(menubar);
	}
	
	public static String getLang(){
		return language;
	}
	
	public static void setLang(String lang){
		language = lang;
	}
	
	
	
	public void SetlangMenu(){
		String langnow = getLang();
		if(langnow=="French"){
			this.setTitle("Pendu by Horleas");
			fichier.setText("Fichier");
			apropos.setText("A propos");
			nouveau.setText("Nouvelle partie");
			regle.setText("R�gles");
			quitter.setText("Quitter");
			
			
			
			
			
		}else if(langnow=="English"){
			this.setTitle("Hangman by Horleas");
			
			fichier.setText("Files");
			apropos.setText("About");
			nouveau.setText("New Game");
			regle.setText("Rules");
			quitter.setText("Quit");
			
		}
	
	}

}
