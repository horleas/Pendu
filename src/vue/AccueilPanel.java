package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AccueilPanel extends JPanel {
	
	private JLabel bienvenueAccueil = new JLabel();
	private JTextArea messageAccueil = new JTextArea();
	private JPanel panaccueil = new JPanel();
	private Dimension dim = new Dimension (900,600);
	
	public AccueilPanel(){
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		
		this.panaccueil.setPreferredSize(dim);
		
		InitTextLang();
		
		Font fontgros = new Font("Tahoma", Font.BOLD, 40);
		bienvenueAccueil.setFont(fontgros);
		bienvenueAccueil.setHorizontalAlignment(JLabel.CENTER);

		this.panaccueil.add(bienvenueAccueil, BorderLayout.NORTH);
		
		
		JLabel img = new JLabel(new ImageIcon("images/accueil.jpg"));
		this.panaccueil.add(img, BorderLayout.CENTER);
		  
		
		Font fontpetit = new Font("Arial", Font.BOLD, 14);
		messageAccueil.setFont(fontpetit);
		messageAccueil.setEditable(false);
		
		this.panaccueil.add(messageAccueil, BorderLayout.SOUTH);
		panaccueil.setBackground(Color.white);
		this.add(panaccueil, BorderLayout.CENTER);

	}
	
	public void InitTextLang(){
		
		String langnow = Fenetre.getLang();
		if(langnow=="French"){
			bienvenueAccueil.setText("Bienvenue dans le jeu du PENDU\n");
			messageAccueil.setText("Vous avez sept coups pour trouver le mot caché. Si vous réussissez, on recommence !\n" +
					"Plus vous trouvez de mots, plus votre score augmente. Alors, à vous de jouer !\n" +
					"Proverbe :\t« Pas vu, pas pris !\n" +
						"\tPris ! PENDU ! »");
			
			
			
			
			
		}else if(langnow=="English"){
			
			bienvenueAccueil.setText("Welcome in the Hangman Game\n");
			messageAccueil.setText(" You have 7 try to find the hidden word. If you achieve to find the word, You will gain points. \n" +
			"While you win, you have another word until you loose. Let's Play !\n");
			
		}
		
		
	}
	
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return this.panaccueil;
	}

}
