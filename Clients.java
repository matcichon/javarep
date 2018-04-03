package Windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdesktop.swingx.JXDatePicker;

import workshop.Workshop;

import javax.naming.NamingEnumeration;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;

public class Clients extends JFrame {
	private JTextField SNameTextField;
	private JTextField NameTextField;
	private JTextField CitizenNumTextField;
	private JTextField Address1TextField;
	private JTextField Address2TextField;
	private JTable table;
	private Date date;
	private JXDatePicker datePicker;
	private String birth_date,birth_date_tmp,id,Sname,name,citizen_num,phone,address1,address2;
	private JFormattedTextField  PhoneTextField;
	private JLabel NameErrLabel,SNameErrLabel,CitizenNumberErrLabel,PhoneErrLabel;
	private JButton btnCancelClient, btnSaveClient, btnEditClient;
	private Boolean isNew=false;
	
	public Clients() {
		Workshop wrk=new Workshop();
		setTitle("CLIENTS");
		getContentPane().setLayout(null);
		setBounds(0, 0, 1092, 549);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 329, 425);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SurName");
		lblNewLabel.setBounds(10, 40, 96, 14);
		panel.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 98, 96, 14);
		panel.add(lblName);
		
		JLabel lblCitizennum = new JLabel("CitizenNum");
		lblCitizennum.setBounds(10, 150, 96, 14);
		panel.add(lblCitizennum);
		
		JLabel lblBirthdate = new JLabel("BirthDate");
		lblBirthdate.setBounds(10, 202, 96, 14);
		panel.add(lblBirthdate);
		
		JLabel lblAdress = new JLabel("Address1");
		lblAdress.setBounds(10, 310, 96, 14);
		panel.add(lblAdress);
		
		JLabel lblAddress = new JLabel("Address2");
		lblAddress.setBounds(10, 362, 96, 14);
		panel.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 256, 96, 14);
		panel.add(lblPhone);
		
		SNameTextField = new JTextField();
		SNameTextField.setBounds(116, 37, 119, 20);
		panel.add(SNameTextField);
		SNameTextField.setColumns(10);
		
		NameTextField = new JTextField();
		NameTextField.setColumns(10);
		NameTextField.setBounds(116, 95, 119, 20);
		panel.add(NameTextField);
		
		CitizenNumTextField = new JTextField();
		CitizenNumTextField.setColumns(10);
		CitizenNumTextField.setBounds(116, 147, 119, 20);
		
		panel.add(CitizenNumTextField);
		
		
		datePicker=new JXDatePicker();
		datePicker.setBounds(116, 199, 119, 20);
		datePicker.setFormats("yyyy-MM-dd");
		datePicker.setDate(new Date());
		panel.add(datePicker);
		GetCitizenNumber();
		
		datePicker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GetCitizenNumber();
			}
		});
		
		datePicker.getComponent(0).addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				GetCitizenNumber();
				
			}
		});
		
	/*	JFormattedTextField BirthDateTextField = new JFormattedTextField();
		BirthDateTextField.setBounds(75, 199, 153, 20);
		panel.add(BirthDateTextField);*/
		
		PhoneTextField = new JFormattedTextField();
		PhoneTextField.setBounds(116, 253, 119, 20);
		panel.add(PhoneTextField);
		
		Address1TextField = new JTextField();
		Address1TextField.setColumns(10);
		Address1TextField.setBounds(116, 307, 119, 20);
		panel.add(Address1TextField);
		
		Address2TextField = new JTextField();
		Address2TextField.setColumns(10);
		Address2TextField.setBounds(116, 359, 119, 20);
		panel.add(Address2TextField);
		
		btnCancelClient = new JButton("Clear");
		btnCancelClient.setBounds(222, 391, 89, 23);
		panel.add(btnCancelClient);
		
		btnCancelClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClearFields();
			}
		});
		
		btnEditClient = new JButton("Edit");
		btnEditClient.setBounds(10, 391, 96, 23);
		panel.add(btnEditClient);
		
		btnEditClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BlockFields(false);
			}
		});
		
		btnSaveClient = new JButton("Save");
		btnSaveClient.setBounds(116, 390, 96, 23);
		panel.add(btnSaveClient);
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		
		
		
		SNameErrLabel = new JLabel("");
		SNameErrLabel.setForeground(Color.RED);
		SNameErrLabel.setBounds(115, 56, 227, 14);
		panel.add(SNameErrLabel);
		
		
		NameErrLabel = new JLabel("");
		NameErrLabel.setForeground(Color.RED);
		NameErrLabel.setBounds(115, 115, 227, 14);
		panel.add(NameErrLabel);
		
		CitizenNumberErrLabel = new JLabel("");
		CitizenNumberErrLabel.setForeground(Color.RED);
		CitizenNumberErrLabel.setBounds(116, 166, 227, 14);
		panel.add(CitizenNumberErrLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(245, 40, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(245, 98, 46, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(245, 150, 46, 14);
		panel.add(label_1);
		
		PhoneErrLabel = new JLabel("");
		PhoneErrLabel.setForeground(Color.RED);
		PhoneErrLabel.setBounds(116, 272, 227, 14);
		panel.add(PhoneErrLabel);
		
		// DODANIE NOWEGO KLIETNA
		btnSaveClient.addActionListener(new ActionListener() {
		
		
			@Override
		public void actionPerformed(ActionEvent e) {
				boolean update1,update2,update3,update4=false;
				// TODO Auto-generated method stub
				if(NameTextField.getText().length()==0)  	
					NameErrLabel.setText("Please enter Client Name");
						else if(!NameTextField.getText().matches("^[a-zA-Z]+")) 
								NameErrLabel.setText("Sorry, only letters allowed");
									else 
										{
											NameErrLabel.setText("");	
											update1=true;
										}
				
			
			 if(SNameTextField.getText().length()==0)  	
					SNameErrLabel.setText("Please enter Client Name");
						else if(!SNameTextField.getText().matches("^[a-zA-Z]+")) 
								SNameErrLabel.setText("Sorry, only letters allowed");
									else {
											SNameErrLabel.setText("");
											update2=true;
										}
				
				
				if(CitizenNumTextField.getText().length()==0) 
					CitizenNumberErrLabel.setText("Please enter Citizen Number");
				else if(CitizenNumTextField.getText().length()!=11)
					CitizenNumberErrLabel.setText("Citizen Number must contain 11 digits");
				else if(!CitizenNumTextField.getText().matches("^[0-9]+"))
					CitizenNumberErrLabel.setText("Only Digits Allowed...");
						else {
								CitizenNumberErrLabel.setText("");
								update3=true;
							}
				
				if(PhoneTextField.getText().length()>0) {
					if(!PhoneTextField.getText().matches("^[0-9]+")) 
						PhoneErrLabel.setText("Sorry, only digits allowed");
					else PhoneErrLabel.setText("");
				}
				else {
					PhoneErrLabel.setText("");	
					update4=true;
				}
				
				if(NameErrLabel.getText()==""&&SNameErrLabel.getText()==""&&CitizenNumberErrLabel.getText()==""&&PhoneErrLabel.getText()=="") {
					//*********************** DODAJEMY NOWEGO KLIENTA **********************
					if(isNew) {
							wrk.insertClient(SNameTextField.getText(), NameTextField.getText(), CitizenNumTextField.getText(),birth_date,PhoneTextField.getText(), 
							Address1TextField.getText(), Address2TextField.getText());
							ClearFields();
							isNew=false;
					} else {
						wrk.UpdateClient(id,SNameTextField.getText(),NameTextField.getText(), CitizenNumTextField.getText(),df.format(datePicker.getDate()).toString() , PhoneTextField.getText(), 
								Address1TextField.getText(), Address2TextField.getText());
					}
					
					// **************************** ODŒWIE¯ENIE TABLICY PO DODANIU KLIENTA *******************************
					DefaultTableModel tm=(DefaultTableModel) table.getModel();
					tm=new DefaultTableModel(wrk.getClients(), wrk.getClientsColumnNames());
					table.setModel(tm);
						
					//HideTableColumn(table, 0);
					tm.fireTableDataChanged(); 
					TableGraphics();
					
					BlockFields(true);
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(339, 0, 727, 425);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		// ***************************     WCZYTANIE TABLICY KLIENTÓW   **************************************
		table = new JTable(wrk.getClients(),wrk.getClientsColumnNames());
		panel_1.add(table);
		table.setDefaultEditor(Object.class, null);
		TableGraphics();
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel_1.add(scrollPane);
		
	
		
		//WCZYTANIE DANYCH DO PÓL FORMULARZA PO KLIKNIÊCIU W REKORD TABELI
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting()&&table.getSelectedRow()!=-1) {
					id=table.getValueAt(table.getSelectedRow(), 0).toString();     //POBRANIE ID KLIENTA (DO PRYSZ£EGO UPDATE'A)
					Sname=table.getValueAt(table.getSelectedRow(), 1).toString();  SNameTextField.setText(Sname);
					name=table.getValueAt(table.getSelectedRow(), 2).toString();  NameTextField.setText(name);
					citizen_num=table.getValueAt(table.getSelectedRow(), 3).toString(); CitizenNumTextField.setText(citizen_num);
					phone=table.getValueAt(table.getSelectedRow(), 5).toString();  PhoneTextField.setText(phone);
					address1= table.getValueAt(table.getSelectedRow(), 6).toString() ; Address1TextField.setText(address1);
					address2=table.getValueAt(table.getSelectedRow(), 7).toString() ; Address2TextField.setText(address2);
					try {
						date=df.parse(table.getValueAt(table.getSelectedRow(), 4).toString());
						datePicker.setDate(date);
					}catch (ParseException ex) {
						ex.printStackTrace();
					}
					BlockFields(true);
				}
			}
		});
		
		
		id=table.getValueAt(0, 0).toString();
		Sname=table.getValueAt(0, 1).toString();    SNameTextField.setText(Sname);
		name=table.getValueAt(0, 2).toString(); NameTextField.setText(name);
		citizen_num=table.getValueAt(0, 3).toString();  CitizenNumTextField.setText(citizen_num);
		phone=table.getValueAt(0, 5).toString();  PhoneTextField.setText(phone);
		address1=table.getValueAt(0, 6).toString();  Address1TextField.setText(address1);
		address2=table.getValueAt(0, 7).toString() ; Address2TextField.setText(address2);
		try {
			date=df.parse(table.getValueAt(0, 4).toString());
			datePicker.setDate(date);
		}catch(ParseException ex) {
			ex.printStackTrace();
		}
		
		
		
		btnSaveClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			/*	DefaultTableModel tm= (DefaultTableModel) table.getModel();
				System.out.println(id+Sname+name+citizen_num+phone+address1+address2);
				wrk.UpdateClient(id,SNameTextField.getText(),NameTextField.getText(), CitizenNumTextField.getText(),df.format(datePicker.getDate()).toString() , PhoneTextField.getText(), 
						Address1TextField.getText(), Address2TextField.getText());
				tm=new DefaultTableModel(wrk.getClients(), wrk.getClientsColumnNames());
				table.setModel(tm);
				TableGraphics();
				BlockFields(true); */
			}
		});

		JButton btnCancelClientB = new JButton("BACK TO MENU");
		btnCancelClientB.setBounds(144, 436, 153, 57);
		getContentPane().add(btnCancelClientB);
		
		btnCancelClientB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				wrk.CloseConnection();
				MenuFrame mf=new MenuFrame();
				mf.setVisible(true);
			
			}
		});
		
		JButton btnAddClientB = new JButton("NEW CLIENT");
		btnAddClientB.setBounds(496, 436, 153, 57);
		getContentPane().add(btnAddClientB);
		
		btnAddClientB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isNew=true;
				BlockFields(false);
				ClearFields();
			}
		});
	BlockFields(true);	
	
	ChangeColumnSize(table, 0,25); //ukrycie pola repairs_id
	ChangeColumnSize(table, 1,100);  //ustawienie szerokoœci kolumn
	ChangeColumnSize(table, 2,80);
	ChangeColumnSize(table, 3,100);
	ChangeColumnSize(table, 4,80);
	ChangeColumnSize(table, 5,80);
	ChangeColumnSize(table, 6,150);
	ChangeColumnSize(table, 7,150);
	
	}  // KONIEC KONSTRUKTORA 
	
	public void GetCitizenNumber() {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		birth_date_tmp=birth_date=formatter.format(datePicker.getDate());
		birth_date_tmp=birth_date_tmp.replace("-", "");
		birth_date_tmp=birth_date_tmp.substring(2, birth_date_tmp.length());
		CitizenNumTextField.setText(birth_date_tmp);
	}
	public void ClearFields() {
		NameTextField.setText("");
		SNameTextField.setText("");
		datePicker.setDate(new Date());
		GetCitizenNumber();
		PhoneTextField.setText("");
		Address1TextField.setText("");
		Address2TextField.setText("");	
		NameErrLabel.setText(""); SNameErrLabel.setText("");
		PhoneErrLabel.setText(""); CitizenNumberErrLabel.setText("");
	}
	public void BlockFields(boolean blk) {
		NameTextField.setEnabled(!blk);
		SNameTextField.setEnabled(!blk);
		CitizenNumTextField.setEnabled(!blk);
		datePicker.setEnabled(!blk);
		PhoneTextField.setEnabled(!blk);
		Address1TextField.setEnabled(!blk);
		Address2TextField.setEnabled(!blk);
		btnSaveClient.setEnabled(!blk); btnCancelClient.setEnabled(!blk);
		
		
	}
	
	public void TableGraphics() {

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
					
			@Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	        {
			final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	         //   c.setBackground(row % 2 == 0 ?   Color.LIGHT_GRAY : Color.WHITE);
	            
	           DefaultTableCellRenderer Renderer=new DefaultTableCellRenderer();
	           Renderer.setHorizontalAlignment(JLabel.CENTER);
	           if(!isSelected) c.setBackground(row % 2 == 0 ?   Color.LIGHT_GRAY : Color.WHITE);
	           table.getTableHeader().setFont(new Font("SansSerif",Font.BOLD, 12));
	           
	         //  System.out.println(row);
	           /*for(int i=0;i<table.getColumnCount();i++) {
	        	   table.getColumnModel().getColumn(i).setCellRenderer(Renderer);
	           }*/
	           this.setHorizontalAlignment(JLabel.CENTER);
	            return this;
	        }
		});
	
	}
	
	public void HideTableColumn(JTable table,int id) {
		table.getColumnModel().getColumn(id).setMinWidth(0); //ukyrwamy pole ID
		table.getColumnModel().getColumn(id).setMaxWidth(0);
		table.getColumnModel().getColumn(id).setWidth(0);
	}
	
	public void ChangeColumnSize(JTable table,int id,int size) {
		table.getColumnModel().getColumn(id).setMinWidth(size);
		table.getColumnModel().getColumn(id).setMaxWidth(size);
		table.getColumnModel().getColumn(id).setWidth(size);	
	}


}
