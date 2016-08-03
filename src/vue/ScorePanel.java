package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * ScorePanel is a Panel which show the top 10 of score.
 * The first time this game is launch, it create a score.txt
 * which will be initialized with the initListScore :
 * static method savescore will rewrite the score.txt file
 * static method afficherScore will take the list of score and show it on the ScorePanel
 * When a player have finished a game, the GamePanel will checked
 *  if the current score is over or equal to the last score of the list.
 * If Yes, the player is ask to write his name and then the method newScore will iterating 
 * over the list to find the place depending on the score of the player.
 * when the place is found, and to keep the top 10, we remove the last score of the list 
 * dimlabelscore is dimensioned to take the screen dimension of the panel in length
 * but have only 50 in height so 10 label of score = 10*50 = 500 <600
 * When the score is displayed, the 3 better score has special color to simulate the podium (gold, silver, bronze)
 * and the label have different size for theirs placement in the top 10
 * 
 */
public class ScorePanel extends JPanel{

	private static JPanel panscore = new JPanel();
	private Dimension dim = new Dimension (900,600);
	private static Dimension dimlabelscore = new Dimension (800,50);
	private static List listscore = new LinkedList();
	public ScorePanel(){
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.panscore.setPreferredSize(dim);
		
		
		File fichierscore = new File("score.txt");
		
		if(!fichierscore.exists()){
			initListScore();
		}
		
		afficherScore();

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
		listscore.add(new Score("Pierre", 2,0));
		
		savescore();
		
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
		System.out.println("the new record is at "+ index);
		
		listscore.add(index, newscore);
		listscore.remove(listscore.size()-1);
		
		savescore();
		
		afficherScore();

	}
	
	public static int getworstscore(){
		return ((Score)listscore.get(listscore.size()-1)).getScore();
	}
	

	public static void afficherScore(){
		
		panscore.removeAll();
		ObjectInputStream ois;

		 
		 try {
			 
			 ois = new ObjectInputStream(
		              new BufferedInputStream(
		                new FileInputStream(
		                  new File("score.txt"))));
			 
			 try {
				listscore = (List) ois.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("can't find the file score.txt");
				e.printStackTrace();
			}
			 
				for(int i = 0; i < listscore.size() ; i++){
					JLabel lab = new JLabel(listscore.get(i).toString());
					if(i==0){lab.setForeground(Color.yellow);}
					if(i==1){lab.setForeground(Color.GRAY);}
					if(i==2){lab.setForeground(Color.orange);}
					lab.setFont(new Font("Arial", Font.BOLD, 50- i*4));
					lab.setPreferredSize(dimlabelscore);
					panscore.add(lab);
				}
			 
			 
				ois.close();
		      }catch (FileNotFoundException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }		 
		 
		panscore.revalidate();
		
	}
	
	public static void savescore(){
		
		 ObjectOutputStream oos;
		    try {
		      oos = new ObjectOutputStream(
		              new BufferedOutputStream(
		                new FileOutputStream(
		                  new File("score.txt"))));

		      oos.writeObject(listscore);

		      oos.close();
		    }catch(IOException e)
		    {
		    	e.printStackTrace();
		    }
	}

	public static JPanel getPanel() {
		// TODO Auto-generated method stub
		return panscore;
	}
	
	
	
}
