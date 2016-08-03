package vue;

import java.io.Serializable;
/*
 * Personal Class which connects the name of player, the score with the number of word completed
 */
public class Score implements Serializable{

	private String nom ="Default" ;
	private int score = 0;
	private int nbrmot=0;
	
	public Score(){}
	
	public Score(String pnom, int pscore, int pnbrmot){
		this.nom = pnom;
		this.score = pscore;
		this.nbrmot =pnbrmot;
	}
	
	public String toString(){
		
		String langnow = Fenetre.getLang();
		if(langnow=="French"){
			return nom +" : "+ Integer.toString(score) + " points en "+ nbrmot +" mots";
		}else if(langnow=="English"){
			return nom +" : "+ Integer.toString(score) + " points in "+ nbrmot +" words";
		}
		return nom +" : "+ Integer.toString(score) + " points en "+ nbrmot +" mots";
	}
	
	public int getScore(){
		return score;
	}
	
	public String getName(){
		return nom;
	}

	public int getNbrmot() {
		return nbrmot;
	}
}
