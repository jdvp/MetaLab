package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import model.TagAdder;
import model.Tags;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Tagger extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2321192101874405576L;
	
	private Tags myTags = new Tags();
	private String songAddress = "";
	private JPanel contentPane;

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
		
		ArrayList<String> defaultTags = myTags.getDefaultTags();
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		
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
		
		JButton btnSave = new JButton("      Save      ");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TagAdder a = new TagAdder(songAddress);
				a.commitComment(myTags.outputTag());
			}
		});
		
		JButton btnNewTag = new JButton("Add new tag");
		btnNewTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myTags.addTag(tagField.getText());
				tagField.setText("");
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewTag, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
					.addContainerGap())
				.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNewTag)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSave))
		);
		
		JPanel panel_5 = new JPanel();
		scrollPane_1.setViewportView(panel_5);
		
		JCheckBox tag8 = new JCheckBox(defaultTags.get(7));
		
		JCheckBox tag1 = new JCheckBox(defaultTags.get(2));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(tag8)
						.addComponent(tag1))
					.addGap(459))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(5)
					.addComponent(tag8)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tag1)
					.addContainerGap(163, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		tag1.addItemListener(checkBoxListener);
		tag8.addItemListener(checkBoxListener);
		panel.setLayout(gl_panel);
		
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
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				songAddress = txtCusersjdPorterfielddocumentssummerkerr.getText();
				myTags.checkOriginalTags(songAddress);
				myTags = new Tags();
				tag1.setSelected(false);
				tag2.setSelected(false);
				tag3.setSelected(false);
				tag4.setSelected(false);
				tag5.setSelected(false);
				tag6.setSelected(false);
				tag7.setSelected(false);
				tag8.setSelected(false);
			}
		});
		panel_4.add(btnChoose);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
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
