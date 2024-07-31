package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class signup extends JFrame implements ActionListener 
{
    JLabel lblheding,lblmeter_no,lblusername,lblname,lblpass;
    Choice acc_type;
    JTextField txtmeter_no,txtusername,txtname;
    JPasswordField txtpass;
    JButton btncreate,btnback;
    public signup()
    {
        setBounds(430,180,700,400);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JPanel panel=new JPanel();
        panel.setBounds(5,5,670,350);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(100,100,100),2),"Create-Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(250,140,120)));
        panel.setBackground(Color.white);
        panel.setLayout(null);
        add(panel);
        
        lblheding=new JLabel("Create Account As");
        lblheding.setBounds(100, 50, 140, 25);
        lblheding.setFont(new Font("Tahoma",Font.BOLD,15));
        panel.add(lblheding);
        
        acc_type=new Choice();
        acc_type.add("Admin");
        acc_type.add("Customer");
        acc_type.setBounds(250, 50, 160, 25);
        panel.add(acc_type);
        
        lblmeter_no=new JLabel("Meter Number ");
        lblmeter_no.setBounds(120, 90, 160, 25);
        lblmeter_no.setFont(new Font("Tahoma",Font.BOLD,13));
        lblmeter_no.setVisible(false);
        panel.add(lblmeter_no);
        
        txtmeter_no=new JTextField();
        txtmeter_no.setBounds(250, 90, 160, 25);
        txtmeter_no.setVisible(false);
        panel.add(txtmeter_no);
        
        lblusername=new JLabel("UserName ");
        lblusername.setBounds(120, 130, 140, 25);
        lblusername.setFont(new Font("Tahoma",Font.BOLD,13));
        panel.add(lblusername);
        
        txtusername=new JTextField();
        txtusername.setBounds(250, 130, 160, 25);
        panel.add(txtusername);

        lblname=new JLabel("Name ");
        lblname.setBounds(120, 170, 140, 25);
        lblname.setFont(new Font("Tahoma",Font.BOLD,13));
        panel.add(lblname);
        
        txtname=new JTextField();
        txtname.setBounds(250, 170, 160, 25);
        panel.add(txtname);
        
        txtmeter_no.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent fe){}
            
            public void focusLost(FocusEvent fe){
                try{
                    Conn c=new Conn();
                    ResultSet rs=c.st.executeQuery("select * from login where meter_no='"+txtmeter_no.getText()+"'");
                    while(rs.next())
                    {
                        txtname.setText(rs.getString("name"));
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        
        lblpass=new JLabel("Password ");
        lblpass.setBounds(120, 210, 140, 25);
        lblpass.setFont(new Font("Tahoma",Font.BOLD,13));
        panel.add(lblpass);
        
        txtpass=new JPasswordField();
        txtpass.setBounds(250, 210, 160, 25);
        panel.add(txtpass);
        
        acc_type.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ae)
            {
                String user=acc_type.getSelectedItem();
                if(user.equals("Customer"))
                {
                    lblmeter_no.setVisible(true);
                    txtmeter_no.setVisible(true);
                    txtname.setEditable(false);
                }
                else
                {
                    lblmeter_no.setVisible(false);
                    txtmeter_no.setVisible(false);
                    txtname.setEditable(true);
                }
            }
        });
        
        btncreate=new JButton("Create");
        btncreate.setBounds(140, 270, 100, 25);
        btncreate.setBackground(Color.black);
        btncreate.setForeground(Color.white);
        btncreate.addActionListener(this);
        panel.add(btncreate);
        
        btnback=new JButton("Back");
        btnback.setBounds(280, 270, 100, 25);
        btnback.setBackground(Color.black);
        btnback.setForeground(Color.white);
        btnback.addActionListener(this);
        panel.add(btnback);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/sing.png"));
        Image i2=i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(450, 50, 200, 200);
        panel.add(image);
        
        
       // setSize(700,400);
        //setLocation(430,180);
        
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btncreate)
        {
            String type=acc_type.getSelectedItem();
            String username=txtusername.getText();
            String name=txtname.getText();
            String pass=txtpass.getText();
            String meterno=txtmeter_no.getText();
            
            try
            {
                Conn c=new Conn();
                String query=null;
                
                if(type.equals("Admin"))
                {
                    query="insert into login values('"+type+"','"+meterno+"','"+username+"','"+name+"','"+pass+"')";
                }
                else
                {
                    query="update login set user_name='"+username+"',password='"+pass+"',acc_type='"+type+"' where meter_no='"+meterno+"'";
                }
                
                c.st.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Account Create SuccessFully...");
                setVisible(false);
                
                new Login();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==btnback)
        {
            setVisible(false);
            new Login();
        }
    }
    public static void main(String []args)
    {
        new signup();
    }
}
