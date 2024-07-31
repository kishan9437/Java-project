package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Updateinformation extends JFrame implements ActionListener{
    
    JTextField txtaddress,txtcity,txtstate,txtemail,txtphone;
    JButton btnupdate,btncancel;
    String meter;
    JLabel name,meterno;
    Updateinformation(String meter)
    {
        this.meter=meter;
        setBounds(350,150,1050,450);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heding=new JLabel("UPDATE CUSTOMER INFORMATION");
        heding.setBounds(110, 0, 400, 40);
        heding.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heding);
        
        JLabel lblname=new JLabel("Name");
        lblname.setBounds(30, 70, 100, 20);
        add(lblname);
        
        name=new JLabel("");
        name.setBounds(230, 70, 100, 20);
        add(name);
        
        JLabel lblmeterno=new JLabel("Meter Number");
        lblmeterno.setBounds(30, 110, 100, 20);
        add(lblmeterno);
        
        meterno=new JLabel("");
        meterno.setBounds(230, 110, 100, 20);
        add(meterno);
        
        JLabel lblAddress=new JLabel("Address");
        lblAddress.setBounds(30, 150, 100, 20);
        add(lblAddress);
        
        txtaddress=new JTextField();
        txtaddress.setBounds(230, 150, 200, 20);
        add(txtaddress);
        
        JLabel lblcity=new JLabel("City");
        lblcity.setBounds(30, 190, 100, 20);
        add(lblcity);
        
        txtcity=new JTextField();
        txtcity.setBounds(230, 190, 200, 20);
        add(txtcity);
        
        JLabel lblstate=new JLabel("State");
        lblstate.setBounds(30, 230, 100, 20);
        add(lblstate);
        
        txtstate=new JTextField();
        txtstate.setBounds(230, 230, 200, 20);
        add(txtstate);
        
        JLabel lblemail=new JLabel("Email");
        lblemail.setBounds(30, 270, 100, 20);
        add(lblemail);
        
        txtemail=new JTextField();
        txtemail.setBounds(230, 270, 200, 20);
        add(txtemail);
        
        JLabel lblphone=new JLabel("Phone No");
        lblphone.setBounds(30, 310, 100, 20);
        add(lblphone);
        
        txtphone=new JTextField();
        txtphone.setBounds(230, 310, 200, 20);
        add(txtphone);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.st.executeQuery("select * from customer where meter_no='"+meter+"'");
            
            while(rs.next())
            {
                name.setText(rs.getString("cust_name"));
                txtaddress.setText(rs.getString("address"));
                txtcity.setText(rs.getString("city"));
                txtstate.setText(rs.getString("state"));
                txtemail.setText(rs.getString("email"));
                txtphone.setText(rs.getString("phone_no"));
                meterno.setText(rs.getString("meter_no"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        btnupdate=new JButton("Update");
        btnupdate.setBackground(Color.BLACK);
        btnupdate.setForeground(Color.WHITE);
        btnupdate.setBounds(70, 360, 100, 25);
        add(btnupdate);
        btnupdate.addActionListener(this);
                
        btncancel=new JButton("Cancel");
        btncancel.setBackground(Color.BLACK);
        btncancel.setForeground(Color.WHITE);
        btncancel.setBounds(230, 360, 100, 25);
        add(btncancel);
        btncancel.addActionListener(this);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2=i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(530, 80, 400, 300);
        add(image);
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btnupdate)
        {
            String address=txtaddress.getText();
            String city=txtcity.getText();
            String state=txtstate.getText();
            String email=txtemail.getText();
            String phone=txtphone.getText();
            
            try{
                Conn c=new Conn();
                c.st.executeUpdate("update customer set address='"+address+"',city='"+city+"',state='"+state+"',email='"+email+"',phone_no='"+phone+"' where meter_no='"+meter+"'");
                
                JOptionPane.showMessageDialog(null, "User Information Update Successfully... ");
                setVisible(false);
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
        new Updateinformation("");
    }
}
