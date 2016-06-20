package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
	JPanel pan = new JPanel();
	JMenuBar menubar = new JMenuBar();
	JMenu fichier = new JMenu("Fichier");
	JMenuItem nouveau = new JMenuItem("Nouveau");
	JMenuItem score = new JMenuItem("Score");
	JMenuItem quitter = new JMenuItem("Quitter");
	JMenu apropos = new JMenu("A propos");
	JMenuItem regle = new JMenuItem("Règle");
	JMenuItem question = new JMenuItem("?");
	JMenuItem test = new JMenuItem ("test");
	private JPanel conteneur = new JPanel();
	
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
		menubar.add(fichier);
		fichier.setMnemonic('f');
		fichier.add(nouveau);
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		fichier.add(score);
		score.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
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
	        JOptionPane jop = new JOptionPane();      
	        String mess = "Pendu d'Horleas\n";
	        mess += "Réaliser via le tuto OpenClassRoom\n";     
	        jop.showMessageDialog(null, mess, "À propos", JOptionPane.INFORMATION_MESSAGE);    
    
			conteneur.removeAll();
			conteneur.add(new AccueilPanel().getPanel(), BorderLayout.CENTER);
			conteneur.revalidate();
	      }            
	    });
	    
	    //---------------ZONE DE TEST ---------------------------------------------//
	    
	    apropos.add(test);
	    test.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				conteneur.removeAll();
				conteneur.add(new GamePanel().getPanel(), BorderLayout.CENTER);
				conteneur.revalidate();
				i++;
				
			}
	     });
	    
	    //---------------FIN DE LA ZONE DE TEST ---------------------------------------------//
		
		this.setJMenuBar(menubar);
	}
	 /*
	public Dimension getSize(){
		return size;
	}*/

}
