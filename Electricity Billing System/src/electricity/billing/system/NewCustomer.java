package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
public class NewCustomer extends JFrame implements ActionListener{
    JLabel heading,lblcustomer,lblmeterno,lblmeter,lbladdress,lblcity,lblstate,lblemail,lblphone;
    JTextField txtcustomer,txtaddress,txtcity,txtstate,txtemail,txtphone;
    JButton btnnext,btncancel;
    NewCustomer()
    {
        setSize(730,500);
        setLocation(430,180);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173,216,230));
        add(panel);
        
        heading=new JLabel("New Customer");
        heading.setBounds(180, 17, 200, 25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,27));
        panel.add(heading);
        
        lblcustomer=new JLabel("Customer Name");
        lblcustomer.setBounds(100, 80, 100, 20);
        panel.add(lblcustomer);
        
        txtcustomer=new JTextField("");
        txtcustomer.setBounds(240, 80, 180, 20);
        panel.add(txtcustomer);
        
        lblmeterno=new JLabel("Meter No");
        lblmeterno.setBounds(100, 120, 100, 20);
        panel.add(lblmeterno);
        
        lblmeter=new JLabel("");
        lblmeter.setBounds(240, 120, 180, 20);
        panel.add(lblmeter);
        
        Random random=new Random();
        long number=random.nextLong()%1000000;
        lblmeter.setText(""+Math.abs(number));
        
        lbladdress=new JLabel("Address");
        lbladdress.setBounds(100, 160, 100, 20);
        panel.add(lbladdress);
        
        txtaddress=new JTextField("");
        txtaddress.setBounds(240, 160, 180, 20);
        panel.add(txtaddress);
        
        lblcity=new JLabel("City");
        lblcity.setBounds(100, 200, 100, 20);
        panel.add(lblcity);
        
        txtcity=new JTextField("");
        txtcity.setBounds(240, 200, 180, 20);
        panel.add(txtcity);
        
        lblstate=new JLabel("State");
        lblstate.setBounds(100, 240, 100, 20);
        panel.add(lblstate);
        
        txtstate=new JTextField("");
        txtstate.setBounds(240, 240, 180, 20);
        panel.add(txtstate);
        
        lblemail=new JLabel("Email");
        lblemail.setBounds(100, 280, 100, 20);
        panel.add(lblemail);
        
        txtemail=new JTextField("");
        txtemail.setBounds(240, 280, 180, 20);
        panel.add(txtemail);
        
        lblphone=new JLabel("Phone No");
        lblphone.setBounds(100, 320, 100, 20);
        panel.add(lblphone);
        
        txtphone=new JTextField("");
        txtphone.setBounds(240, 320, 180, 20);
        panel.add(txtphone);
        
        btnnext=new JButton("Next");
        btnnext.setBounds(140, 380, 100, 25);
        btnnext.setBackground(Color.BLACK);
        btnnext.setForeground(Color.white);
        btnnext.addActionListener(this);
        panel.add(btnnext);
        
        btncancel=new JButton("Cancel");
        btncancel.setBounds(300, 380, 100, 25);
        btncancel.setBackground(Color.BLACK);
        btncancel.setForeground(Color.white);
        btncancel.addActionListener(this);
        panel.add(btncancel);
        
        setLayout(new BorderLayout());
        add(panel,"Center");
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/newcustomer.png"));
        Image i2=i1.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image,"West");
        
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btnnext)
        {
            String name=txtcustomer.getText();
            String meterno=lblmeter.getText();
            String address=txtaddress.getText();
            String city=txtcity.getText();
            String state=txtstate.getText();
            String email=txtemail.getText();
            String phoneno=txtphone.getText();
            
            String query1="insert into customer values('"+name+"','"+meterno+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phoneno+"')";
            String query2="insert into login values('','"+meterno+"','','"+name+"','')";
            
            try
            {
                Conn c=new Conn();
                c.st.executeUpdate(query1);
                c.st.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Details added SuccessFully..");
                setVisible(false);
                
                new meterinfo(meterno);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            setVisible(false);
        }
    }
    public static void main(String args[])
    {
        new NewCustomer();
    }
}
