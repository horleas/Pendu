package vue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.swing.JOptionPane;

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
			//LineNumberReader fnr = new LineNumberReader(new FileReader(this.getClass().getResourceAsStream("/dictionnaire.txt").toString()));
			//LineNumberReader fnr = new LineNumberReader(new FileReader(new File("dico/dictionnaire.txt")));
			String langnow = Fenetre.getLang();
			if(langnow=="French"){
				reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/dictionnaire.txt")));		
				fnr =new LineNumberReader(reader);
				
				//fnr = new LineNumberReader(new FileReader(this.getClass().getResourceAsStream("/dictionnaire.txt").toString()));
				//fnr = new LineNumberReader(new FileReader(new File(this.getClass().getResource("/dictionnaire.txt").toURI())));
				//fnr = new LineNumberReader(new FileReader(new File("dico/dictionnaire.txt")));
			}else if(langnow=="English"){
				
				reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/dictionnary.txt")));		
				fnr =new LineNumberReader(reader);
				//fnr = new LineNumberReader(new FileReader(this.getClass().getResourceAsStream("/dictionnary.txt").toString()));
				//fnr = new LineNumberReader(new FileReader(new File(this.getClass().getResource("/dictionnary.txt").toURI())));
				//fnr = new LineNumberReader(new FileReader(new File("dico/dictionnary.txt")));
				
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
