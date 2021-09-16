package com.customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class Customer {

	private JFrame MainFrame;
	private JTextField Customer_Name;
	private JTextField Customer_id;
	private JTextField Customer_address;
	private JTextField textField_3;
	private JTextField Customer_Number;
	private JTable table;
	//private JTable table_1;
	private JTextField searchBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer window = new Customer();
					window.MainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Customer() {
		initialize();
		Connect();
		table_load();
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("Jdbc:mysql://localhost:3306/customermanagementsystem", "root", "Shashi@20");
		}
		
		 catch (ClassNotFoundException ex) 
        {
          ex.printStackTrace();
        }
        catch (SQLException ex) 
        {
        	   ex.printStackTrace();
        }
	}
	
	public void table_load()
    {
    	try 
    	{
	    pst = con.prepareStatement("select * from customer");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	} 
    	catch (SQLException e) 
    	 {
    		e.printStackTrace();
	  } 
    }
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainFrame = new JFrame();
		MainFrame.getContentPane().setBackground(new Color(255, 192, 203));
		MainFrame.setBackground(new Color(250, 235, 215));
		MainFrame.setBounds(100, 100, 869, 502);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.getContentPane().setLayout(null);
		
		JLabel Title = new JLabel(" Customer Details");
		Title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		Title.setBounds(286, 11, 309, 33);
		MainFrame.getContentPane().add(Title);
		
		JPanel RegistrationPanel = new JPanel();
		RegistrationPanel.setBackground(new Color(173, 216, 230));
		RegistrationPanel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		RegistrationPanel.setBounds(20, 55, 331, 218);
		MainFrame.getContentPane().add(RegistrationPanel);
		RegistrationPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Customer Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 36, 107, 14);
		RegistrationPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Customer Id");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(10, 78, 107, 14);
		RegistrationPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Customer Address");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(10, 115, 134, 14);
		RegistrationPanel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Contact Number");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(10, 159, 107, 14);
		RegistrationPanel.add(lblNewLabel_1_2);
		
		Customer_Name = new JTextField();
		Customer_Name.setBounds(137, 34, 161, 20);
		RegistrationPanel.add(Customer_Name);
		Customer_Name.setColumns(10);
		
		Customer_id = new JTextField();
		Customer_id.setBounds(137, 76, 161, 20);
		RegistrationPanel.add(Customer_id);
		Customer_id.setColumns(10);
		
		Customer_address = new JTextField();
		Customer_address.setBounds(137, 113, 161, 20);
		RegistrationPanel.add(Customer_address);
		Customer_address.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(137, 157, 161, 0);
		RegistrationPanel.add(textField_3);
		textField_3.setColumns(10);
		
		Customer_Number = new JTextField();
		Customer_Number.setColumns(10);
		Customer_Number.setBounds(137, 157, 161, 20);
		RegistrationPanel.add(Customer_Number);
		
		JButton saveButton = new JButton("save");
		saveButton.setBackground(new Color(224, 255, 255));
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cname,id,address,number;
				cname=Customer_Name.getText();
				id=Customer_id.getText();
				address=Customer_address.getText();
				number=Customer_Number.getText();
				
				
				try {
					pst=con.prepareStatement("insert into customer (customer_name,customer_id,customer_address,contact_number)values(?,?,?,?)");
					pst.setString(1, cname);
					pst.setString(2,id );
					pst.setString(3, address);
					pst.setString(4,number);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Customer Data Successfully Added!");
					
					
					table_load();
					//After Adding Form should be Empty
					
					Customer_Name.setText("");
					Customer_id.setText("");
					Customer_address.setText("");
					Customer_Number.setText("");
					Customer_Name.requestFocus();					
						
				}
				catch (SQLException e1) 
		        {
								
			e1.printStackTrace();
			}
			}
		});
		saveButton.setBounds(144, 284, 98, 38);
		MainFrame.getContentPane().add(saveButton);
		
		table = new JTable();
		table.setBounds(415, 93, 1, 1);
		MainFrame.getContentPane().add(table);
		
		JPanel searchBarPanel = new JPanel();
		searchBarPanel.setBackground(new Color(230, 230, 250));
		searchBarPanel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchBarPanel.setBounds(20, 333, 331, 61);
		MainFrame.getContentPane().add(searchBarPanel);
		searchBarPanel.setLayout(null);
		
		JLabel searchbarTitle = new JLabel("Customer SSN");
		searchbarTitle.setBackground(new Color(224, 255, 255));
		searchbarTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		searchbarTitle.setBounds(10, 24, 107, 14);
		searchBarPanel.add(searchbarTitle);
		
		searchBar = new JTextField();
		searchBar.setBackground(new Color(224, 255, 255));
		searchBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          
			            String ssn = searchBar.getText();

			                pst = con.prepareStatement("select customer_name,customer_id,customer_address,contact_number from customer where ssn = ?");
			                pst.setString(1, ssn);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String customer_name = rs.getString(1);
			                String customer_id = rs.getString(2);
			                String customer_address = rs.getString(3);
			                String contact_number = rs.getString(4);
			                
			            	Customer_Name.setText(customer_name);
							Customer_id.setText(customer_id);
							Customer_address.setText(customer_address);
							Customer_Number.setText(contact_number);
			                
			            }   
			            else
			            {
			            	Customer_Name.setText("");
							Customer_id.setText("");
							Customer_address.setText("");
							Customer_Number.setText("");
			                
			                 
			            }
			            


			        } 
				
				 catch (SQLException ex) {
			           
			        }
				
				
				
			
				
			}
		});
		searchBar.setColumns(10);
		searchBar.setBounds(131, 22, 161, 20);
		searchBarPanel.add(searchBar);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String cname,id,address,number,ssn;
				cname=Customer_Name.getText();
				id=Customer_id.getText();
				address=Customer_address.getText();
				number=Customer_Number.getText();
				ssn=searchBar.getText();
				
				
				try {
					pst=con.prepareStatement("update customer SET  customer_name,customer_id,customer_address,contact_number where ssn =?");
					pst.setString(1, cname);
					pst.setString(2,id );
					pst.setString(3, address);
					pst.setString(4,number);
					pst.setString(5,ssn);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Customer Data Successfully Updated!");
					
					
					table_load();
					//After Adding Form should be Empty
					
					Customer_Name.setText("");
					Customer_id.setText("");
					Customer_address.setText("");
					Customer_Number.setText("");
					Customer_Name.requestFocus();					
						
				}
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			
			}
		});
		

		btnUpdate.setBackground(new Color(224, 255, 255));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
		btnUpdate.setBounds(519, 343, 98, 38);
		MainFrame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(224, 255, 255));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	              String cssn;
	  			cssn  = searchBar.getText();
	  			
	  			 try {
	  					pst = con.prepareStatement("delete from customer where ssn =?");
	  			
	  		            pst.setString(1, cssn);
	  		            pst.executeUpdate();
	  		            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
	  		            table_load();
	  		           
	  		            Customer_Name.setText("");
						Customer_id.setText("");
						Customer_address.setText("");
						Customer_Number.setText("");
		                
	  				}
	   
	  	            catch (SQLException e1) {
	  					
	  					e1.printStackTrace();
	  				}
				
				
			}
		});
		btnDelete.setBounds(661, 343, 92, 38);
		MainFrame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 55, 453, 253);
		MainFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setRowHeaderView(panel_2);
		panel_2.setLayout(null);
	}
}
