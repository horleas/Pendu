package vue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.swing.JOptionPane;

/*
 * Word class must search in the 2 dictionnaries :
 * dictionnaire.txt contains 300 000+ word in French
 * dictionnary.txt contains 300 000+ word in English
 * 
 * I used a math.random with the length of dictionnary to get
 * a random number which be given to the LineNumberReader who return the word
 * then the word is trim and put to uppercase before setting the secred word by replacing all the letters by stars '*'
 * except the '-' which is used in the french langage and is named : composed word
 */
public class Word {
	private String word ="";
	private String secretword="";

	
	public Word(){
		do{
		initWord();
		}while(word.length()<3);
	}
	
	public void initWord(){
		
		int carac;
		int i = (int) (Math.random()*336529);
		
		word ="";
		secretword="";
		try{
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/dictionnaire.txt")));		
			LineNumberReader fnr =new LineNumberReader(reader);
			String langnow = Fenetre.getLang();
			if(langnow=="French"){
				reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/dictionnaire.txt")));		
				fnr =new LineNumberReader(reader);

			}else if(langnow=="English"){
				
				reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/dictionnary.txt")));		
				fnr =new LineNumberReader(reader);
				
			}
			
			while((carac = fnr.read()) != -1){
				if(fnr.getLineNumber() == (i+1) ){
					break;
					
				}	
				else {
					if(fnr.getLineNumber() == i){
						this.word += (char) carac ;
					}
				}
			}
			
			this.word=this.word.trim().toUpperCase();
			
			
			for (int j=0; j<word.length();j++){
				this.secretword += (this.word.charAt(j) != '\'' && this.word.charAt(j) != '-') ? "*" : this.word.charAt(j);
			}

			fnr.close();
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier de mots !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier de mots !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public String getWord(){
		return word;
	}
	
	public String getSecretWord(){
		return word;
	}
	

}
