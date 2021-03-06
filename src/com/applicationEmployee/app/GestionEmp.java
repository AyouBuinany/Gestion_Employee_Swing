package com.applicationEmployee.app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class GestionEmp {

	private JFrame frame;
	private JTextField textNom;
	private JTextField textDate;
	private JTextField textPrenom;
	private JTextField textAge;
	private JTextField textPrix;
	private JTable table;

	Connection conn;
	PreparedStatement stmt;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEmp window = new GestionEmp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionEmp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionBorder);
		panel.setBounds(761, 176, 509, 663);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(57, 115, 120, 40);
		panel.add(lblNewLabel);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrnom.setBounds(57, 178, 120, 40);
		panel.add(lblPrnom);
		
		JLabel lblAge = new JLabel("Age  :");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAge.setBounds(57, 241, 120, 40);
		panel.add(lblAge);
		
		JLabel lblService = new JLabel("Service  :");
		lblService.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblService.setBounds(57, 306, 120, 40);
		panel.add(lblService);
		
		JLabel lblDateEntreeService = new JLabel("Date Entree Service  :");
		lblDateEntreeService.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDateEntreeService.setBounds(57, 374, 153, 40);
		panel.add(lblDateEntreeService);
		
		JLabel lblPrixUnitaire = new JLabel("Prix(chiffre d'affaire/nbrUnite/nbrHeure) :");
		lblPrixUnitaire.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrixUnitaire.setBounds(57, 431, 261, 50);
		panel.add(lblPrixUnitaire);
		
		textNom = new JTextField();
		textNom.setFont(new Font("Tahoma", Font.BOLD, 13));
		textNom.setBounds(211, 115, 251, 40);
		panel.add(textNom);
		textNom.setColumns(10);
		
		textDate = new JTextField();
		textDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		textDate.setColumns(10);
		textDate.setBounds(211, 372, 251, 40);
		panel.add(textDate);
		
		textPrenom = new JTextField();
		textPrenom.setFont(new Font("Tahoma", Font.BOLD, 13));
		textPrenom.setColumns(10);
		textPrenom.setBounds(211, 176, 251, 40);
		panel.add(textPrenom);
		
		textAge = new JTextField();
		textAge.setFont(new Font("Tahoma", Font.BOLD, 13));
		textAge.setColumns(10);
		textAge.setBounds(211, 239, 251, 40);
		panel.add(textAge);
		
		JComboBox<?> cmbxService = new JComboBox();
		cmbxService.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbxService.setModel(new DefaultComboBoxModel(new String[] {"Vendeur", "Representant", "Producteur", "ProducteurARisque", "Manutentionnaire", "ManutentionnaireARisque"}));
		cmbxService.setBounds(211, 306, 251, 40);
		panel.add(cmbxService);
		
		textPrix = new JTextField();
		textPrix.setFont(new Font("Tahoma", Font.BOLD, 13));
		textPrix.setColumns(10);
		textPrix.setBounds(328, 435, 134, 40);
		panel.add(textPrix);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(57, 498, 405, 107);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.addActionListener(new ActionListener() {
			

	
         public void actionPerformed(ActionEvent e) {
				
				String nom = textNom.getText();
				String prenom = textPrenom.getText();
				String age = textAge.getText();
				String service = cmbxService.getSelectedItem().toString();
				String date = textDate.getText();
				String prix = textPrix.getText();
				

				
				try {
					
					 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionEmp", "root", "");
					 
					
					 
					 stmt = conn.prepareStatement("insert into employe(nom,prenom,age,service,date_entree_service,prix_unitaire,salaire)values(?,?,?,?,?,?,?)");
						
					 stmt.setString(1,nom);
					 stmt.setString(2,prenom);
					 stmt.setString(3,age);
					 stmt.setString(4,service);
					 stmt.setString(5,date);
					 stmt.setString(6,prix);
					 
					 
					 
					 
				switch (service) {
					 
					case "Vendeur":
						
						double salaireVendeur =( 0.2 * Integer.parseInt(prix))+1500;
						
						 stmt.setDouble(7,salaireVendeur);
						
						 stmt.execute();
						
						break;
                    case "Representant":
                    	
                    	double salaireRepresentant =( 0.2 * Integer.parseInt(prix))+2500;
						
						 stmt.setDouble(7,salaireRepresentant);
						
						 stmt.execute();
						
						break;
                    case "Producteur":
                    	
                    	double salaireProducteur =( 5 * Integer.parseInt(prix));
						
						 stmt.setDouble(7,salaireProducteur);
						
						 stmt.execute();
	
	                    break;
                    case "ProducteurARisque":
                    	
                    	double salaireProducteurARisque =( 5 * Integer.parseInt(prix))+200;
						
						 stmt.setDouble(7,salaireProducteurARisque);
						
						 stmt.execute();
	
	                    break;
                    case "Manutentionnaire":
                    	
                    	double salaireManutentionnaire =( 50 * Integer.parseInt(prix));
					
						 stmt.setDouble(7,salaireManutentionnaire);
						
						 stmt.execute();
 	
	                    break;
                    case "ManutentionnaireARisque":
                    	
                    	double salaireManutentionnaireARisque =( 50 * Integer.parseInt(prix))+200;
						
						 stmt.setDouble(7,salaireManutentionnaireARisque);
						
						 stmt.execute();
	
  	                    break;

					default:
						break;
					}
					  
					 JOptionPane.showMessageDialog(btnAdd, "Employe Added Successfully");

					 textNom.setText("");
					 textPrenom.setText("");
					 textAge.setText("");
					 cmbxService.setSelectedIndex(0);
					 textDate.setText("");
					 textPrix.setText("");
					 
					 textNom.requestFocus();
					 
					 	 
				} catch (Exception  e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
	
		}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(10, 11, 103, 38);
		panel_1.add(btnAdd);
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int msg = JOptionPane.showConfirmDialog(null, "Are you sure to delete", "Delete", JOptionPane.YES_NO_OPTION);
				if(msg==0) {
					
			
				
				 DefaultTableModel df = (DefaultTableModel)table.getModel();	
			     int selectedIndex = table.getSelectedRow();	
				
			
				
			     try {
						
						
						int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
						
						
						
						 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionEmp", "root", "");
						   stmt = conn.prepareStatement("DELETE from employe  where id = '" + id + "'");
							 stmt.executeUpdate();
							  
							 JOptionPane.showMessageDialog(btnAdd, "Employe Deleted Successfully");
							 
							 textNom.setText("");
							 textPrenom.setText("");
							 textAge.setText("");
							 cmbxService.setSelectedIndex(0);
							 textDate.setText("");
							 textPrix.setText("");
							 
							 textNom.requestFocus();
							
						} catch (Exception  e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
					}
				}
					
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(287, 11, 108, 38);
		panel_1.add(btnDelete);
		
		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				 DefaultTableModel df = (DefaultTableModel)table.getModel();	
			     int selectedIndex = table.getSelectedRow();
				
			     

					try {
						
						
						int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					
						String nom = textNom.getText();
						String prenom = textPrenom.getText();
						String age = textAge.getText();
						String service = cmbxService.getSelectedItem().toString();
						String date = textDate.getText();
						String prix = textPrix.getText();
						
						double salaire = 0; 
						
						
					
						
						 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionEmp", "root", "");
						 
						
						 
							
					 
						 
						 
					switch (service) {
						 
						case "Vendeur":
							
							salaire =( 0.2 * Integer.parseInt(prix))+1500;
							
							
							
							break;
	                    case "Representant":
	                    	
	                    	salaire =( 0.2 * Integer.parseInt(prix))+2500;
							
							
							
							break;
	                    case "Producteur":
	                    	
	                    	salaire =( 5 * Integer.parseInt(prix));
							
							
		
		                    break;
	                    case "ProducteurARisque":
	                    	
	                    	salaire =( 5 * Integer.parseInt(prix))+200;
							
							
		
		                    break;
	                    case "Manutentionnaire":
	                    	
	                    	salaire =( 50 * Integer.parseInt(prix));
						
							
							
	 	
		                    break;
	                    case "ManutentionnaireARisque":
	                    	
	                    	salaire =( 50 * Integer.parseInt(prix))+200;
							
							
		
	  	                    break;

						default:
							break;
						}
					//update data -----------------
					
					 stmt = conn.prepareStatement("UPDATE employe SET " + "nom = '" + nom + "', prenom = '" + prenom + "', age = '" + age + "', date_entree_service = '" + date + "', service = '" + service + "', prix_unitaire = '" + prix+ "', salaire = '" + salaire + "' where id = '" + id + "'");
							 stmt.executeUpdate();
						  
						 JOptionPane.showMessageDialog(btnAdd, "Employe edited Successfully");

						 textNom.setText("");
						 textPrenom.setText("");
						 textAge.setText("");
						 cmbxService.setSelectedIndex(0);
						 textDate.setText("");
						 textPrix.setText("");
						 
						 textNom.requestFocus();
						 
						 	 
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
		
			}});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(152, 11, 103, 38);
		panel_1.add(btnUpdate);
		
		JButton btnCancel = new JButton("Quitter");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(217, 60, 126, 38);
		panel_1.add(btnCancel);
		
		JButton btnOrder = new JButton("Afficher");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					
				
					
					 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionEmp", "root", "");
					 
					
					 
					 stmt = conn.prepareStatement("SELECT * FROM employe");
						
					 ResultSet result = stmt.executeQuery();
					 

					 DefaultTableModel df = (DefaultTableModel)table.getModel();
					 
					 df.setRowCount(0);
					 
					 while(result.next()) {
						int id = result.getInt("id");
						String nom = result.getString("nom");
						String prenom = result.getString("prenom");
						int age = result.getInt("age");
						String service = result.getString("service");
						String date_entree_service = result.getString("date_entree_service");
						int prix_unitaire = result.getInt("prix_unitaire");
						Double salaire = result.getDouble("salaire");
						
                        df.addRow(new Object[] {id,nom,prenom,age,service,date_entree_service,prix_unitaire,salaire});
                        
                     }
					
	 
				} catch (Exception  e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				
				
				
				
				
			}
});
		btnOrder.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOrder.setBounds(58, 60, 126, 38);
		panel_1.add(btnOrder);
		
		JLabel lblNewLabel_1 = new JLabel("Registration  Employ\u00E9");
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setBackground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(160, 26, 212, 40);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gestion des Employees");
		lblNewLabel_2.setForeground(SystemColor.desktop);
		lblNewLabel_2.setBackground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_2.setBounds(436, 11, 484, 59);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaptionBorder);
		panel_2.setForeground(new Color(200, 24, 240));
		panel_2.setBounds(0, 516, 761, 323);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("List des Employe\u00E9s");
		lblNewLabel_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBackground(SystemColor.windowText);
		lblNewLabel_1_1.setBounds(296, 0, 179, 40);
		panel_2.add(lblNewLabel_1_1);
		
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setBounds(10, 51, 741, 229);
		panel_2.add(scrollTable);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				

				 DefaultTableModel df = (DefaultTableModel)table.getModel();	
			     int selectedIndex = table.getSelectedRow();
				 textNom.setText(df.getValueAt(selectedIndex, 1).toString());
				 textPrenom.setText(df.getValueAt(selectedIndex, 2).toString());
				 textAge.setText(df.getValueAt(selectedIndex, 3).toString());
				 textDate.setText(df.getValueAt(selectedIndex, 5).toString());
				 textPrix.setText(df.getValueAt(selectedIndex, 6).toString());
				
				
				
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
				
			new Object[][] {
			},
			new String[] {
				"ID", "Nom", "Prenom", "Age", "Service", "Date Entree Service", "Prix", "Salaire"
			}
		) {

			public boolean isCellEditable(int rowIndex, int columnIndex) {
			    return false;
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(81);
		table.getColumnModel().getColumn(5).setPreferredWidth(107);
		table.getColumnModel().getColumn(6).setPreferredWidth(49);
		scrollTable.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaptionBorder);
		panel_3.setBounds(0, 176, 761, 341);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Employ\u00E9s lister par service");
		lblNewLabel_1_1_1.setBounds(246, 0, 259, 40);
		lblNewLabel_1_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBackground(SystemColor.textHighlight);
		panel_3.add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 742, 156);
		panel_3.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nom", "Prenom", "Age", "Service"
			}
		));
		scrollPane.setViewportView(table_1);
		
		JComboBox serviceList = new JComboBox();
		serviceList.setFont(new Font("Tahoma", Font.BOLD, 15));
		serviceList.setModel(new DefaultComboBoxModel(new String[] {"Vendeur", "Representant", "Producteur", "ProducteurARisque", "Manutentionnaire", "ManutentionnaireARisque"}));
		serviceList.setBounds(158, 229, 249, 43);
		panel_3.add(serviceList);
		
		JButton btnList = new JButton("Lister");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//			
				String serviceListe= serviceList.getSelectedItem().toString();
              try {
					
					
					
					 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionEmp", "root", "");
					 
					
					 
					 stmt = conn.prepareStatement("	SELECT * FROM `employe` WHERE service = '" + serviceListe + "'");
						
					 ResultSet result = stmt.executeQuery();
					 

					 DefaultTableModel df = (DefaultTableModel)table_1.getModel();
					 
					 df.setRowCount(0);
					 
					 while(result.next()) {
						int id = result.getInt("id");
						String nom = result.getString("nom");
						String prenom = result.getString("prenom");
						int age = result.getInt("age");
						String service = result.getString("service");
						
						
                        df.addRow(new Object[] {id,nom,prenom,age,service});
					 }
					
	 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnList.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnList.setBounds(457, 229, 128, 43);
		panel_3.add(btnList);
		
	
	}

}
