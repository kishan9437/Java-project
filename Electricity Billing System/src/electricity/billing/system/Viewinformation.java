package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Viewinformation extends JFrame implements ActionListener {

    JButton btncancel;
    Viewinformation(String meter)
    {
        setBounds(350,150,900,650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heding=new JLabel("VIEW CUSTOMER INFORMATION");
        heding.setBounds(250, 0, 500, 40);
        heding.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heding);
        
        JLabel lblname=new JLabel("Name");
        lblname.setBounds(70, 80, 100, 20);
        add(lblname);
        
        JLabel name=new JLabel("");
        name.setBounds(250, 80, 100, 20);
        add(name);
        
        JLabel lblmeterno=new JLabel("Meter Number");
        lblmeterno.setBounds(70, 140, 100, 20);
        add(lblmeterno);
        
        JLabel meterno=new JLabel("");
        meterno.setBounds(250, 140, 100, 20);
        add(meterno);
        
        JLabel lblAddress=new JLabel("Address");
        lblAddress.setBounds(70, 200, 100, 20);
        add(lblAddress);
        
        JLabel Address=new JLabel("");
        Address.setBounds(250, 200, 100, 20);
        add(Address);
        
        JLabel lblcity=new JLabel("City");
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);
        
        JLabel city=new JLabel("");
        city.setBounds(250, 260, 100, 20);
        add(city);
        
        JLabel lblstate=new JLabel("State");
        lblstate.setBounds(500, 80, 100, 20);
        add(lblstate);
        
        JLabel state=new JLabel("");
        state.setBounds(650, 80, 100, 20);
        add(state);
        
        JLabel lblemail=new JLabel("Email");
        lblemail.setBounds(500, 140, 100, 20);
        add(lblemail);
        
        JLabel email=new JLabel("");
        email.setBounds(650, 140, 120, 20);
        add(email);
        
        JLabel lblphone=new JLabel("Phone");
        lblphone.setBounds(500, 200, 100, 20);
        add(lblphone);
        
        JLabel phone=new JLabel("");
        phone.setBounds(650, 200, 100, 20);
        add(phone);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.st.executeQuery("select * from customer where meter_no='"+meter+"'");
            
            while(rs.next())
            {
                name.setText(rs.getString("cust_name"));
                Address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone_no"));
                meterno.setText(rs.getString("meter_no"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        btncancel=new JButton("Cancel");
        btncancel.setBackground(Color.BLACK);
        btncancel.setForeground(Color.WHITE);
        btncancel.setBounds(350, 340, 100, 25);
        add(btncancel);
        btncancel.addActionListener(this);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2=i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(20, 350, 600, 300);
        add(image);
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
    }
    public static void main(String args[])
    {
        new Viewinformation("");
    }
}
