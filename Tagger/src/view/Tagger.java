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

public class Tagger extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2321192101874405576L;
	
	private Tags myTags = new Tags();
	private String songAddress = "";
	private JPanel contentPane;
	private JTextField tagField;

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
		
		
		JCheckBox tag3 = new JCheckBox(defaultTags.get(0));
		tag3.addItemListener(checkBoxListener);
		
		JCheckBox tag2 = new JCheckBox(defaultTags.get(1));
		tag2.addItemListener(checkBoxListener);
		
		JCheckBox tag1 = new JCheckBox(defaultTags.get(2));
		tag1.addItemListener(checkBoxListener);
		
		JCheckBox tag4 = new JCheckBox(defaultTags.get(3));
		tag4.addItemListener(checkBoxListener);
		
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
		
		JCheckBox tag5 = new JCheckBox(defaultTags.get(4));
		tag5.addItemListener(checkBoxListener);
		
		JCheckBox tag6 = new JCheckBox(defaultTags.get(5));
		tag6.addItemListener(checkBoxListener);
		
		JCheckBox tag7 = new JCheckBox(defaultTags.get(6));
		tag7.addItemListener(checkBoxListener);
		
		JCheckBox tag8 = new JCheckBox(defaultTags.get(7));
		tag8.addItemListener(checkBoxListener);
		
		tagField = new JTextField();
		tagField.setColumns(10);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(tag1)
						.addComponent(tag2)
						.addComponent(tag3)
						.addComponent(tag4)
						.addComponent(tag5)
						.addComponent(tag6)
						.addComponent(tag7)
						.addComponent(tag8)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewTag))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(tagField, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(tag1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tag2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tag3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tag4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tag5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tag6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tag7)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tag8)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addComponent(tagField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewTag)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSave))
		);
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
		
		JTextField textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				songAddress = textField.getText();
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
