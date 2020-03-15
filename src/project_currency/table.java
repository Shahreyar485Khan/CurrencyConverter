/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_currency;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE; 

/**
 *
 * @author Shahryar
 */
public class table extends JFrame {
    
    Connection con;
     
    Object[][] data;
    JTable table;
    String[] col;
    JScrollPane scroll;
    JButton btn;
    
    Box b1;
    
    JPanel left,right,center;
    JLabel top,btm;
    
  //  table t = new table();
 //   Project_Currency p = new Project_Currency();
    
     public Connection getConnection()
    {
          String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
                String url = "jdbc:ucanaccess://C:\\Users\\Shahryar\\Documents\\MS ACCESS\\CurrencyDB.accdb";
                    
                   try{
                       Class.forName(driver);
                       con = DriverManager.getConnection(url);
                       
                   }catch(Exception ex){
                       System.out.println(ex);
                   } 
                   return con;
    }
     
   
    
     
     public table()
     {
         
         this.setTitle("DATABASE RECORD");
       this.setLayout(new BorderLayout(10,10));
       this.setSize(600,650);
       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      //this.getContentPane().setBackground(Color.red);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
      
        btn= new JButton("BACK");
       top = new JLabel("DATABASE RECORDS",JLabel.CENTER);
       top.setForeground(Color.BLACK);
       top.setFont(new Font("Verdanna",Font.BOLD,22));
       btm = new JLabel("Press back Button to GO CURRENCY CONVERTER",JLabel.CENTER);
       right = new JPanel();
       left = new JPanel();
       center = new JPanel();
        data = new Object[0][5];
       col = new String[]{"ID","Currency1","Amount","Currency2","Converted Amount"};
      
      
       
         this.add(top,BorderLayout.NORTH);
        this.add(btm,BorderLayout.SOUTH);
        this.add(left,BorderLayout.WEST);
       this.add(right,BorderLayout.EAST);
        
       
       
       
       ResultSet rs;
       Statement st;
       con = getConnection();
       if(con != null)
       {try{
       
           st=con.createStatement();
           rs = st.executeQuery("SELECT * FROM tblCurrency");
           ArrayList ids = new ArrayList();
           ArrayList cont1 = new ArrayList();
           ArrayList amount = new ArrayList();
           ArrayList cont2 = new ArrayList();
           ArrayList rus = new ArrayList();
           
           while(rs.next())
           {
               ids.add(rs.getInt(1));
               cont1.add(rs.getString(2));
               amount.add(rs.getString(3));
               cont2.add(rs.getString(4));
               rus.add(rs.getString(5));
           }
           data = new Object[cont1.size()][5];
           for(int i=0;i<cont1.size();i++)
           {
               data[i][0]=ids.get(i);
               data[i][1]=cont1.get(i);
               data[i][2]=amount.get(i);
               data[i][3]=cont2.get(i);
               data[i][4]=rus.get(i);
           }
           
            table = new JTable(data,col);
            table.setSize(300, 200);
            scroll = new JScrollPane(table);
    
                
       
      
       b1 = Box.createVerticalBox();
       
       b1.add(scroll);
       b1.add(btn);
       
     
        center.add(b1);
      
        
        this.add(center,BorderLayout.CENTER);
        this.setVisible(true);
      
          }catch(Exception ex){
           System.out.println("error"+ex);
       }
           
       }
         btn.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e)
             {
                 dispose();
                 
                
             }
         });
     
         btn.addMouseListener(new MouseAdapter(){

            
             @Override
             public void mouseEntered(MouseEvent e) {
                 
                setCursor( Cursor.getPredefinedCursor( HAND_CURSOR) );
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 setCursor( Cursor.getPredefinedCursor( DEFAULT_CURSOR ) );
             }
             
         });
     
     }
     
       public static void main(String[] args) {
          table tb=new table();
        
          
       }
    
}
