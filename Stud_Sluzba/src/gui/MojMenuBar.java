package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import controller.AddListener;
import controller.DeleteListener;
import controller.SaveToDatabaseListener;

public class MojMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1107467685534769718L;

	private static MojMenuBar instance = null;
	
	public static MojMenuBar getInstance() {
		if(instance==null)
			instance = new MojMenuBar();
		return instance;
	}
	
	private MojMenuBar() {

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem new_mi = new JMenuItem("New", new ImageIcon("images/add-22.png"));
		new_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));		//CTRL+N
		new_mi.addActionListener(new AddListener(AddDialog.ADD_MODE));
		
		JMenuItem save_mi = new JMenuItem("Save to DB", new ImageIcon("images/save-22.png"));
		save_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));		//CTRL+S
		save_mi.addActionListener(new SaveToDatabaseListener());
		
		JMenuItem close_mi = new JMenuItem("Close", new ImageIcon("images/close-22.png"));
		close_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));		//ALT+F4
		close_mi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int code = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Are you sure you want to close the program",
						"Close Window", JOptionPane.YES_NO_OPTION);
				if (code != JOptionPane.YES_OPTION) {
					MainFrame.getInstance().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				} else {
					// TODO sacuvaj bazu objekata, da li ovde?...
					MainFrame.getInstance().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				}
			}
		});
		
		//TODO: MenuBar "Edit" Izmena postojeceg entiteta
		JMenuItem edit_mi = new JMenuItem("Edit", new ImageIcon("images/edit-22.png"));
		edit_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));		//CTRL+E
		edit_mi.addActionListener(new AddListener(AddDialog.EDIT_MODE));
		
		//TODO: MenuBar "Delete" Brisanje postejećeg entiteta
		JMenuItem delete_mi = new JMenuItem("Delete", new ImageIcon("images/trash-22.png"));
		delete_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));		//CTRL+D
		delete_mi.addActionListener(new DeleteListener());
		
		//TODO: MenuBar "Help" Ova sekcija treba da sadrži detaljan opis o načinu korišćenja aplikacije.
		//Potrebno je objasniti kako se svaka od dolenavedenih funkcionalnosti može sprovesti u
		//delo i to u vidu niza korisničkih akcija. Takođe, potrebno je navesti prečice
		//(akceleratore) koje naprednim korisnicima mogu olakšati rad.
		JMenuItem help_mi = new JMenuItem("Help", new ImageIcon("images/help-22.png"));
		help_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));		//CTRL+H
		
		//TODO MenuBar "About" Prikaz verzije aplikacije, kao i kratak opis iste.
		// Nakon toga treba da sledi sažeta biografija svakog autora.
		JMenuItem about_mi = new JMenuItem("About", new ImageIcon("images/about-22.png"));
		about_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));		//CTRL+A
		about_mi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AboutDialog ad = new AboutDialog(MainFrame.getInstance());
				ad.setVisible(true);
			}
			
		});
		
		file.add(new_mi);
		file.add(save_mi);
		file.add(close_mi);
		
		edit.add(edit_mi);
		edit.add(delete_mi);
		
		help.add(help_mi);
		help.add(about_mi);
		
		add(file);
		add(edit);
		add(help);
		
	}
}
