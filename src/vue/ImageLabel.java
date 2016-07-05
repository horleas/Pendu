package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageLabel extends JPanel {
	
	private JPanel panelimg = new JPanel();
	private Dimension dim = new Dimension (500,600);

	  
	public ImageLabel (String num){
		
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.panelimg.setPreferredSize(dim);
		
		
		URL urlimage = this.getClass().getResource("/pendu"+num+".jpg");
		JLabel img = new JLabel(new ImageIcon(urlimage));
		
		//JLabel img = new JLabel(new ImageIcon("images/pendu"+num+".jpg"));
		this.panelimg.add(img, BorderLayout.CENTER);
		
		panelimg.setBackground(Color.white);
		this.add(panelimg, BorderLayout.CENTER);

	}
	
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return this.panelimg;
	}

}

