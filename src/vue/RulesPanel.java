package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RulesPanel extends JPanel{
	private JLabel bienvenueRegle = new JLabel("Le jeu du PENDU\n");
	private JTextArea messageRegle = new JTextArea();

	private JPanel panregle = new JPanel();
	private Dimension dim = new Dimension (900,600);
	
	public RulesPanel(){
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		
		Font fontgros = new Font("Tahoma", Font.BOLD, 40);
		bienvenueRegle.setFont(fontgros);
		bienvenueRegle.setHorizontalAlignment(JLabel.CENTER);		
		this.panregle.add(bienvenueRegle, BorderLayout.NORTH);
		this.panregle.setPreferredSize(dim);
		
		

		
		Font fontpetit = new Font("Arial", Font.BOLD, 14);
		messageRegle.setText(	"\n\n\nVous avez sept coups pour trouver le mot caché. Si vous réussissez, on recommence !\n" +
				"Plus vous trouvez de mots, plus votre score augmente. Alors, à vous de jouer !\n" +
				"\n\nCOMPTE DES POINTS :\n\n\tMot trouvé sans erreur\t\t100 pts\n\tMot trouvé avec une erreur\t50 pts\n" +
				"\tMot trouvé avec deux erreurs\t35 pts\n\tMot trouvé avec trois erreurs\t25 pts\n\tMot trouvé avec quatre erreurs\t15 pts\n" +
				"\tMot trouvé avec cinq erreurs\t10 pts\n\tMot trouvé avec six erreurs\t5 pts\n\n\n" +
				"Je vous souhaite bien du plaisir !\nSi vous pensez pouvoir trouver un mot en un seul essai, c'est que vous croyez que le dictionnaire est petit.\n" +
				"Or, pour votre information, il contient plus de 336 000 mots… Bonne chance ! ;)");
		messageRegle.setFont(fontpetit);
		messageRegle.setEditable(false);
		
		
		
		
		
		
		this.panregle.add(messageRegle, BorderLayout.SOUTH);
		panregle.setBackground(Color.white);
		this.add(panregle, BorderLayout.CENTER);
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return this.panregle;
	}
	
	
	
}
