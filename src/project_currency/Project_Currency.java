/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_currency;
import javax.swing.*;
import java.awt.*;
import static java.awt.Font.BOLD;
import javax.swing.JFrame;
import javax.swing.border.Border;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;




public class Project_Currency  extends Thread {

   
   String countryS = "Country";
   String currencyS = "Currency";
   double a;
   double c;
   
    Connection con;
  
   
   JMenuBar bar ;
   JMenu file,store;
   JMenuItem nw,strData,tbl,clear,ext ;
   JPopupMenu popupmenu;
   Font f;
   
   
private JFrame frame;
private Container contain;
private JLabel main_lab,input_lab,amount_lab,output_lab,convert_lab;
private JTextField tf1,tf2;
private JComboBox box1,box2;
private JButton convert_btn,clear_btn,tb_btn,exit_btn;
private String[] country={countryS,"USA ","Eurep ","INDIA ","BRITISH ", };
private String[] currency={currencyS,"US dollars ","Euro ","INDIAN Rupees ","BRITISH Pounds" };

private UIManager.LookAndFeelInfo looks[];






public  Connection getConnection()
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

public void db_Filling()
{
    con = getConnection();
    ResultSet rs;
    Statement st;
    try
    {
    st = con.createStatement();
    rs = st.executeQuery("SELECT * FROM tblCurrency");
    RandomAccessFile file = new RandomAccessFile("Currency.csv","rw");
    file.writeBytes("ID,slcCurrency,Amount,srcCurrency,result/n");
    while(rs.next())
    {
        file.writeBytes(rs.getString(1)+",");
        file.writeBytes(rs.getString(2)+",");
        file.writeBytes(rs.getString(3)+",");
        file.writeBytes(rs.getString(4)+",");
        file.writeBytes(rs.getString(5)+"/n");
    }
    file.close();
    con.close();
    }catch(Exception e){
        System.out.println("Filling Error"+e);
    }
    
}


public void run(){
   

      
     // creating a j frame
     
     
        frame=new JFrame();
        frame.setBounds(300,100,700,700);
        frame.setTitle("rabbia");
        
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        contain=frame.getContentPane(); 
        contain.setLayout(null);
        contain.setBackground(Color.GRAY);
   
                
       
                
 

              looks = UIManager.getInstalledLookAndFeels();
	try {
	    UIManager.setLookAndFeel( looks[ 1 ].getClassName() );
	    SwingUtilities.updateComponentTreeUI( frame );
	} catch( Exception exception ) {
	   // exception.printStackTrace();
            JOptionPane.showMessageDialog(null,"Index out of bond");
	} //end of try catch block*/

   
        
        initComponent();
}

public void initComponent()
{
      // creating labels/////////////////////////////////////////////////////
               
               
                main_lab=new JLabel("CURRENCY CONVERTOR");
                input_lab=new JLabel("Select the Input Currency");
                amount_lab=new JLabel("Enter the Amount");
                output_lab=new JLabel("Select the output Currency");
                convert_lab=new JLabel("Converted Amount");
                
                
      // creating textfields///////////////////////////////////////////////
                
                tf1=new JTextField();
                
                tf1.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!(Character.isDigit(c) ||
              (c == KeyEvent.VK_BACK_SPACE) ||
              (c == KeyEvent.VK_DELETE))) {
                e.consume();
              }
         }
       });
                tf2=new JTextField();
                
                
       //creating buttons////////////////////////////////////////////////         
                
                
                convert_btn=new JButton("Convert");
                clear_btn=new JButton("Clear");
                tb_btn = new JButton("Show Table");
                exit_btn = new JButton("Exit");
                
        //creating combo box/////////////////////////////////////////////
                
                 box1=new JComboBox(country);
                 box2=new JComboBox(currency);
                
       //popup menu/////////////////////////////////////////////////////////////         
                
         f = new Font("sans-serif", Font.PLAIN, 18);
                
                popupmenu = new JPopupMenu("Edit"); 
                  clear = new JMenuItem("New");
                   tbl = new JMenuItem("Tabel");
                   ext = new JMenuItem("Exit"); 
                   
                   ///adding  components////
                     popupmenu.add(clear);
                     popupmenu.add(tbl);
                     popupmenu.addSeparator();
                     popupmenu.add(ext);   
                 
    /////////menubar////////////////////////////////////////////////////////////             
                   
                bar = new JMenuBar();
                file = new JMenu("File");
                store = new JMenu("Store");
                nw = new JMenuItem("New");
                strData = new JMenuItem("Filling");
                
               ////adding components////
                 file.add(nw);
                 store.add(strData);
                 bar.add(file);
                 bar.add(store);
                
                           
         setFonts();       
}


public void setFonts()
{
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
         //set colours,fontsize and boundry of labels//////////////////////////////////
                             
                             
                main_lab.setBounds(210,40,290,70);
                main_lab.setForeground(Color.BLACK);
                main_lab.setFont(new Font("Verdanna",Font.BOLD,22));
              
                                
                                
                input_lab.setBounds(100,100,230,50);
                input_lab.setForeground(Color.BLACK);
                input_lab.setFont(new Font("Verdanna",Font.BOLD,16));
               
                
                
                amount_lab.setBounds(100,160,230,50);
                amount_lab.setForeground(Color.BLACK);
                amount_lab.setFont(new Font("Verdanna",Font.BOLD,16));
                
                
                
                output_lab.setBounds(100,220,230,50);
                output_lab.setForeground(Color.BLACK);
                output_lab.setFont(new Font("Verdanna",Font.BOLD,16));
               
                
                
                convert_lab.setBounds(100,320,230,50);
                convert_lab.setForeground(Color.BLACK);
                convert_lab.setFont(new Font("Verdanna",Font.BOLD,16));
               
                 
    
        //set boundry,color,border and fontsize of textfields//////////////////////////////
                
                
                tf1.setBounds(300,170,190,30);
               //  a=Double.valueOf(tf1.getText());
                tf1.setFont(new Font("Verdanna",Font.BOLD,16));
                tf1.setBorder(border);
                 
               
                
                tf2.setBounds(300,330,190,30);
                //  tf2.setText(String.valueOf(c));  
                tf2.setFont(new Font("Verdanna",Font.BOLD,16));
                tf2.setBorder(border);
                 
    
        //set colour ,font and border of buttons///////////////////////////////////
                
                //convert button
                
                convert_btn.setBounds(250,275,120,35);
                convert_btn.setFont(new Font("Verdanna",Font.BOLD,14));
                convert_btn.setForeground(Color.BLACK);
                convert_btn.setBorder(border);
                
                
                //clear button
                
                
                clear_btn.setBounds(250,380,120,35);
                clear_btn.setFont(new Font("Verdanna",Font.BOLD,14));
                clear_btn.setForeground(Color.BLACK);
                clear_btn.setBorder(border);
              
                
                
                
                tb_btn.setBounds(250, 420, 120, 35);
                tb_btn.setFont(new Font("Verdanna",Font.BOLD,14));
                tb_btn.setForeground(Color.BLACK);
                tb_btn.setBorder(border);
                 
                
                exit_btn.setBounds(250, 460, 120, 35);
                exit_btn.setFont(new Font("Verdanna",Font.BOLD,14));
                exit_btn.setForeground(Color.BLACK);
                exit_btn.setBorder(border);
                
                
                
      /////////set colour ,font and border of COMBOBOX//////////////////////////////
                
 
               // box1.setSelectedIndex(0);
                box1.setBounds(450,100,140,35);
                box1.setFont(new Font("Verdanna",Font.BOLD,14));
                box1.setBorder(border);
                box1.setForeground(Color.BLACK);
                
               
                
                 //box2.setSelectedIndex(1);
                box2.setBounds(450,220,140,35);
                box2.setForeground(Color.BLACK);                                                               
                box2.setFont(new Font("Verdanna",Font.BOLD,14));                               
                box2.setBorder(border);
                
                

       /////////set colour ,font and border of POPUPMENU////////////////////////////////////////////////////////////          
                
                   popupmenu.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
                   clear.setFont(f);
                   tbl.setFont(f);
                   ext.setFont(f);
                   
                
      ////////set colour ,font and border of MENUBAR//////////////////////////////////////////////////////////
              
                    bar.setBounds(0, 0, 700, 50);
                 
                 file.setFont(f);
                 file.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                 file.setPreferredSize(new Dimension(50, file.getPreferredSize().height));
                
                 
                 
                 nw.setFont(f);
                 nw.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                 nw.setPreferredSize(new Dimension(70, nw.getPreferredSize().height));
         
                 store.setFont(f);
                 store.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                 store.setPreferredSize(new Dimension(70, store.getPreferredSize().height));
                 
                 strData.setFont(f);
                 strData.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                 strData.setPreferredSize(new Dimension(70, strData.getPreferredSize().height ));
                               
                   
                   
     addListeners();              
}


public void addListeners()
{
    ////////////////////////adding action to MENUBAR ITEMS////////////////////////
    
                   nw.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        box1.setSelectedIndex(0);
                        box2.setSelectedIndex(0);
                        tf1.setText("");
                        tf2.setText("");
                    }
                });
                          
 
                 strData.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                       db_Filling();
                    }
                });
                 
                 
   
    ////////////////////////adding action to POPUP ITEMS////////////////////////
                 
           clear.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        box1.setSelectedIndex(0);
                        box2.setSelectedIndex(0);
                        tf1.setText("");
                        tf2.setText("");
                    }
                });
           tbl.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        new table();
                    }
                });
           ext.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        System.exit(0);
                    }
                });
  
         
        frame.addMouseListener(new MouseAdapter() {  
            public void mousePressed(MouseEvent e) {         
                if(SwingUtilities.isRightMouseButton(e))
                popupmenu.show(frame , e.getX(), e.getY());  
            }                 
         }); 
                 
                 
     ////////////////////////adding action to BUTTONS//////////////////////////////   
            
                exit_btn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        System.exit(0);
                    }
                });
                
                 tb_btn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        table t = new table();
                     //   t.setVisible(true);
                      
                    }
                });
                
                
                
                clear_btn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        box1.setSelectedIndex(0);
                        box2.setSelectedIndex(0);
                        tf1.setText("");
                        tf2.setText("");
                    }
                });
         
             convert_btn.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae)
                {
                    
                   // table();
                     String slc_country = (String) box1.getSelectedItem();
                     String slc_currency = (String) box2.getSelectedItem();
                     double a=0,b;
                     
                     String c1 =null;
                     
    try{       
    a=Double.valueOf(tf1.getText());
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null,"Input Amount field is Empty");
    }
    try{
        if(box1.getSelectedIndex()==0 & box2.getSelectedIndex()==1){
            c= a*60.335456;}
        if(box1.getSelectedIndex()==0 & box2.getSelectedIndex()==2){
            c= a*0.595194;}
        if(box1.getSelectedIndex()==0 & box2.getSelectedIndex()==3){
            c= a*0.723333;}
        if(box1.getSelectedIndex()==0 & box2.getSelectedIndex()==4){
            c= a*1.099742;}
        if(box1.getSelectedIndex()==0 & box2.getSelectedIndex()==5){
            c= a*3.672939;}
        if(box1.getSelectedIndex()==0 & box2.getSelectedIndex()==6){
            c= a*6.221082;}
        if(box1.getSelectedIndex()==1 & box2.getSelectedIndex()==0){
            c= a*0.016574;}
        if(box1.getSelectedIndex()==1 & box2.getSelectedIndex()==2){
            c= a*0.009868;}
        if(box1.getSelectedIndex()==1 & box2.getSelectedIndex()==3){
            c= a*0.011992;}
        if(box1.getSelectedIndex()==1 & box2.getSelectedIndex()==4){
            c= a*0.018234;}
        if(box1.getSelectedIndex()==1 & box2.getSelectedIndex()==5){
            c= a*0.060880;}
        if(box1.getSelectedIndex()==1 & box2.getSelectedIndex()==6){
            c= a*0.103114;}
        if(box1.getSelectedIndex()==2 & box2.getSelectedIndex()==0){
            c= a*1.679949;}
        if(box1.getSelectedIndex()==2 & box2.getSelectedIndex()==1){
            c= a*101.251087;}
        if(box1.getSelectedIndex()==2 & box2.getSelectedIndex()==3){
            c= a*1.215237;}
        if(box1.getSelectedIndex()==2 & box2.getSelectedIndex()==4){
            c= a*1.848254;}
        if(box1.getSelectedIndex()==2 & box2.getSelectedIndex()==5){
            c= a*6.170453;}
        if(box1.getSelectedIndex()==2 & box2.getSelectedIndex()==6){
            c= a*10.449975;}
        if(box1.getSelectedIndex()==3 & box2.getSelectedIndex()==0){
            c= a*1.382656;}
        if(box1.getSelectedIndex()==3 & box2.getSelectedIndex()==1){
            c= a*83.332669;}
        if(box1.getSelectedIndex()==3 & box2.getSelectedIndex()==2){
            c= a*0.822930;}
        if(box1.getSelectedIndex()==3 & box2.getSelectedIndex()==4){
            c= a*1.52083;}
        if(box1.getSelectedIndex()==3 & box2.getSelectedIndex()==5){
            c= a*5.078644;}
        if(box1.getSelectedIndex()==3 & box2.getSelectedIndex()==6){
            c= a*8.600954;}
        if(box1.getSelectedIndex()==4 & box2.getSelectedIndex()==0){
            c= a*0.909156;}
        if(box1.getSelectedIndex()==4 & box2.getSelectedIndex()==1){
            c= a*54.794847;}
        if(box1.getSelectedIndex()==4 & box2.getSelectedIndex()==2){
            c= a*0.541034;}
        if(box1.getSelectedIndex()==4 & box2.getSelectedIndex()==3){
            c= a*0.657569;}
        if(box1.getSelectedIndex()==4 & box2.getSelectedIndex()==5){
            c= a*3.339467;}
        if(box1.getSelectedIndex()==4 & box2.getSelectedIndex()==6){
            c= a*5.655489;}
        if(box1.getSelectedIndex()==5 & box2.getSelectedIndex()==0){
            c= a*0.272260;}
        if(box1.getSelectedIndex()==5 & box2.getSelectedIndex()==1){
            c= a*16.409082;}
        if(box1.getSelectedIndex()==5 & box2.getSelectedIndex()==2){
            c= a*0.162022;}
        if(box1.getSelectedIndex()==5 & box2.getSelectedIndex()==3){
            c= a*0.196942;}
        if(box1.getSelectedIndex()==5 & box2.getSelectedIndex()==4){
            c= a*0.299497;}
        if(box1.getSelectedIndex()==5 & box2.getSelectedIndex()==6){
            c= a*1.693525;}
        if(box1.getSelectedIndex()==6 & box2.getSelectedIndex()==0){
            c= a*0.160762;}
        if(box1.getSelectedIndex()==6 & box2.getSelectedIndex()==1){
            c= a*9.689100;}
        if(box1.getSelectedIndex()==6 & box2.getSelectedIndex()==2){
            c= a*0.095673;}
        if(box1.getSelectedIndex()==6 & box2.getSelectedIndex()==3){
            c= a*0.116292;}
        if(box1.getSelectedIndex()==6 & box2.getSelectedIndex()==4){
            c= a*0.176855;}
        if(box1.getSelectedIndex()==6 & box2.getSelectedIndex()==5){
            c= a*0.590495;}
        tf2.setText(String.valueOf(c));
        }catch(Exception x){System.out.println("Error");}
     
    
            ///// Data BASE CONNECTION AND DATA      
    

    
                  con = getConnection();
                    try{
                   PreparedStatement pst = con.prepareStatement("INSERT INTO tblCurrency(slc_country,input_amount,slc_currency,cnvrt_amount) VALUES(?,?,?,?)");
                    
                   pst.setString(1, slc_country );
                   
                   pst.setString(2, tf1.getText());
                   
                   pst.setString(3, slc_currency);
                   
                   pst.setDouble(4, c);
                   
                   pst.executeUpdate();
                   con.close();
              
                    
                    }catch(Exception ex){
                        System.out.println("error"+ex);
                    }
                     
             
                }});
                 
   
    addComponent();              
                 
}

public void addComponent()
{
    
    contain.add(bar);

    contain.add(popupmenu);  
      
    contain.add(convert_lab);
    contain.add(output_lab);
    contain.add(amount_lab);
    contain.add(input_lab); 
    contain.add(main_lab);
    contain.add(tf1);
    contain.add(tf2);
    contain.add(convert_btn);
    contain.add(clear_btn);
    contain.add(tb_btn);
    contain.add(exit_btn);
    contain.add(box1);
    contain.add(box2);
    
    
     frame.setVisible(true);
    
}


    
    public static void main(String[] args) {
        
        Project_Currency cc=new Project_Currency();
        cc.start();
        
    }
  
  

}
