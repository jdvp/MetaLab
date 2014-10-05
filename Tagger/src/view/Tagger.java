package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import model.TagAdder;
import model.Tags;

public class Tagger extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2321192101874405576L;
	
	private Tags myTags = new Tags();
	private String songAddress = "";
	private JPanel contentPane;
	private JTextField tagField;
	private JPanel tagPanel;
	private JScrollPane scrollPane_1;
	
	ItemListener checkBoxListener = new ItemListener(){
		public void itemStateChanged(ItemEvent arg0)
		{
			if(arg0.getStateChange() == ItemEvent.SELECTED)
			{
				if(arg0.getItem() instanceof JCheckBox)
				{
					JCheckBox a = (JCheckBox) arg0.getItem();
					addTag(a.getText());
				}
			}
			else 
			{
				if(arg0.getItem() instanceof JCheckBox)
				{
					JCheckBox a = (JCheckBox) arg0.getItem();
					removeTag(a.getText());
				}
			}
		}
	};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tagger frame = new Tagger();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tagger() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 360);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		
		tagPanel = new JPanel();
		scrollPane_1.setViewportView(tagPanel);
		
		tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
		
		
		updateTagPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(scrollPane_1);
		
		JButton btnNewTag = new JButton("Add new tag");
		btnNewTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myTags.addTag(tagField.getText());
				tagField.setText("");
				updateTagPanel();
			}
		});
		
		tagField = new JTextField();
		tagField.setColumns(10);
		panel.add(tagField);
		panel.add(btnNewTag);
		
		JButton btnSave = new JButton("      Save      ");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TagAdder a = new TagAdder(songAddress);
				a.commitComment(myTags.outputTag());
			}
		});
		panel.add(btnSave);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.NORTH);
		
		JTextField txtCusersjdPorterfielddocumentssummerkerr = new JTextField();
		txtCusersjdPorterfielddocumentssummerkerr.setText("C:/Users/JD Porterfield/Documents/Summer 2014/Kerr PSP/PSP/Music/Rap/001 Ridin' Dirty.mp3");
		panel_4.add(txtCusersjdPorterfielddocumentssummerkerr);
		txtCusersjdPorterfielddocumentssummerkerr.setColumns(10);
		

		JLabel albumArt = new JLabel("");
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				songAddress = txtCusersjdPorterfielddocumentssummerkerr.getText();
				myTags = new Tags();
				myTags.checkOriginalTags(songAddress);
				myTags.addTag(tagField.getText());
				tagField.setText("");
				lblNewLabel.setText(myTags.getTitle(songAddress));
				Image resizedImage = myTags.getImage(songAddress).getScaledInstance(albumArt.getWidth(), albumArt.getHeight(), 0);
				ImageIcon icon = new ImageIcon(resizedImage);
				albumArt.setIcon(icon);
				updateTagPanel();
			}
		});
		
		panel_2.add(albumArt, BorderLayout.CENTER);
		panel_4.add(btnChoose);
		
	}
	
	public ArrayList<JCheckBox> getCheckBoxes()
	{
		ArrayList<JCheckBox> myTagFields = new ArrayList<JCheckBox>();
		
		
		for(String tag : myTags.getAllTags())
		{
			if(tag.matches("[a-zA-Z]+"))
			{
				JCheckBox box = new JCheckBox(tag);
				ArrayList<String> current = myTags.getCurrentTags();
				if(current.contains(tag))
					box.setSelected(true);
				else
					box.setSelected(false);
				myTagFields.add(box);
			}
		}
		
		return myTagFields;
	}
	
	public void updateTagPanel()
	{
		tagPanel.removeAll();
		scrollPane_1.setViewportView(tagPanel);
		tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
		
		for(JCheckBox box: getCheckBoxes())
		{
			box.addItemListener(checkBoxListener);
			tagPanel.add(box);
		}
	}
	
	public void addTag(String tag)
	{
		myTags.addTag(tag);
	}
	
	public void removeTag(String tag)
	{
		myTags.removeTag(tag);
	}
}
