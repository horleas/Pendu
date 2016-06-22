package vue;

import java.io.Serializable;

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
