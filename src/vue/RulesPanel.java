package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RulesPanel extends JPanel{
	private JLabel bienvenueRegle = new JLabel();
	private JTextArea messageRegle = new JTextArea();

	private JPanel panregle = new JPanel();
	private Dimension dim = new Dimension (900,600);
	
	public RulesPanel(){
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		
		InitTextLang();
		
		Font fontgros = new Font("Tahoma", Font.BOLD, 40);
		bienvenueRegle.setFont(fontgros);
		bienvenueRegle.setHorizontalAlignment(JLabel.CENTER);		
		this.panregle.add(bienvenueRegle, BorderLayout.NORTH);
		this.panregle.setPreferredSize(dim);
		
		
		Font fontpetit = new Font("Arial", Font.BOLD, 14);
		messageRegle.setFont(fontpetit);
		messageRegle.setEditable(false);
	
		this.panregle.add(messageRegle, BorderLayout.SOUTH);
		panregle.setBackground(Color.white);
		this.add(panregle, BorderLayout.CENTER);
	}
	
	
public void InitTextLang(){
		
		String langnow = Fenetre.getLang();
		if(langnow=="French"){
			bienvenueRegle.setText("Le jeu du PENDU\n");
			messageRegle.setText(	"\n\n\nVous avez sept coups pour trouver le mot caché. Si vous réussissez, on recommence !\n" +
					"Plus vous trouvez de mots, plus votre score augmente. Alors, à vous de jouer !\n" +
					"\n\nCOMPTE DES POINTS :\n\n\tMot trouvé sans erreur\t\t100 pts\n\tMot trouvé avec une erreur\t75 pts\n" +
					"\tMot trouvé avec deux erreurs\t50 pts\n\tMot trouvé avec trois erreurs\t30 pts\n\tMot trouvé avec quatre erreurs\t15 pts\n" +
					"\tMot trouvé avec cinq erreurs\t10 pts\n\tMot trouvé avec six erreurs\t5 pts\n\n\n" +
					"Je vous souhaite bien du plaisir !\nSi vous pensez pouvoir trouver un mot en un seul essai, c'est que vous croyez que le dictionnaire est petit.\n" +
					"Or, pour votre information, il contient plus de 336 000 mots… Bonne chance ! ;)");
			
			
			
			
			
		}else if(langnow=="English"){
			
			bienvenueRegle.setText("Hangman Game\n");
			messageRegle.setText(	"\n\n\nYou have seven chances to get the hidden word.\n"+"If you succesfully find it, you gain points depending of the nomber of fault you made !\n" +
					"Higher is the number of word found and lowest is your number of fault, higher will be your score!\n" +
					"\n\n POINTS Rating :\n\n\t Word found flawlessly \t\t100 pts\n\t Word found with only 1 fault \t75 pts\n" +
					"\t Word found with only 2 faults\t50 pts\n\t Word found with only 3 faults\t30 pts\n\t Word found with 4 faults\t\t15 pts\n" +
					"\t Word found with 5 faults\t\t10 pts\n\t Word found with 6 faults\t\t5 pts\n\n\n" +
					"Have Fun !\n If you think you can found a word in only 1 try, be careful because the English dictionnary used have more than 300 000 words\n");
			
		}
		
		
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return this.panregle;
	}
	
	
	
}
