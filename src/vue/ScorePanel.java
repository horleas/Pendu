package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{

	private static JPanel panscore = new JPanel();
	private Dimension dim = new Dimension (900,600);
	private static Dimension dimlabelscore = new Dimension (800,50);
	private static List listscore = new LinkedList();
	
	public ScorePanel(){
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.panscore.setPreferredSize(dim);
		
		initListScore();

		panscore.setBackground(Color.white);
		this.add(panscore, BorderLayout.CENTER);
	}
	
	
	@SuppressWarnings("unchecked")
	public void initListScore(){
		
		listscore.add(new Score("Robert", 200,3));
		listscore.add(new Score("Bertrant", 150,2));
		listscore.add(new Score("Anthony", 100,3));
		listscore.add(new Score("Nicolas", 75,1));
		listscore.add(new Score("Olarick", 50,2));
		listscore.add(new Score("Rick-Martin", 35,2));
		listscore.add(new Score("Tintamarre", 15,3));
		listscore.add(new Score("Maracasse", 10,1));
		listscore.add(new Score("Cassiopé", 5,1));
		listscore.add(new Score("Pédé", 2,0));
		
		//ListIterator li = listscore.listIterator();
		//while(li.hasNext()){

		afficherScore();
	}
	
	
	public static void newScore(Score newscore){
		
		int index = 0;
		for(int i = 0; i < listscore.size() ; i++){
			if( ((Score)listscore.get(i)).getScore() <= newscore.getScore()){
				index = i;
				break;
			}
			System.out.println( ((Score)listscore.get(i)).getScore() );
		}
		System.out.println("le nouveau score est a l'index "+ index);
		
		listscore.add(index, newscore);
		listscore.remove(listscore.size()-1);
		
		afficherScore();

	}
	
	public static int getworstscore(){
		return ((Score)listscore.get(listscore.size()-1)).getScore();
	}
	
	
	public static void afficherScore(){
		
		panscore.removeAll();
		
		for(int i = 0; i < listscore.size() ; i++){
			JLabel lab = new JLabel(listscore.get(i).toString());
			if(i==0){lab.setForeground(Color.yellow);}
			if(i==1){lab.setForeground(Color.GRAY);}
			if(i==2){lab.setForeground(Color.orange);}
			lab.setFont(new Font("Arial", Font.BOLD, 50- i*4));
			lab.setPreferredSize(dimlabelscore);
			panscore.add(lab);
		}
		

		panscore.revalidate();;
		
	}

	public static JPanel getPanel() {
		// TODO Auto-generated method stub
		return panscore;
	}
	
	
	
}
