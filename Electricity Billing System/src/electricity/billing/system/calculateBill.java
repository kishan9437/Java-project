package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class calculateBill extends JFrame implements ActionListener{
    JLabel heading,lblmeterno,lblname,c_Name,lbladdress,c_address,lblunit_con,lblmonth;
    JTextField txtunit_con;
    JButton btnsubmit,btncancel;
    Choice meterno,c_month;
    calculateBill()
    {
        setSize(760,450);
        setLocation(430,180);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173,216,230));
        add(panel);
        
        heading=new JLabel("Calculate Electricity Bill");
        heading.setBounds(120, 17, 280, 25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,27));
        panel.add(heading);
        
        lblmeterno=new JLabel("Meter No");
        lblmeterno.setBounds(100, 80, 100, 20);
        panel.add(lblmeterno);
        
        meterno=new Choice();
        
        try
        {
            Conn c=new Conn();
            ResultSet rs=c.st.executeQuery("select * from customer");
            while(rs.next())
            {
                meterno.add(rs.getString("meter_no"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        meterno.setBounds(240, 80, 180, 20);
        panel.add(meterno);
        
        lblname=new JLabel("Name");
        lblname.setBounds(100, 120, 100, 20);
        panel.add(lblname);
        
        c_Name=new JLabel("");
        c_Name.setBounds(240, 120, 180, 20);
        panel.add(c_Name);
        
        lbladdress=new JLabel("Address");
        lbladdress.setBounds(100, 160, 100, 20);
        panel.add(lbladdress);
        
        c_address=new JLabel("");
        c_address.setBounds(240, 160, 180, 20);
        panel.add(c_address);
        
        try
        {
            Conn c=new Conn();
            ResultSet rs=c.st.executeQuery("select * from customer where meter_no='"+meterno.getSelectedItem()+"'");
            while(rs.next())
            {
                c_Name.setText(rs.getString("cust_name"));
                c_address.setText(rs.getString("address"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        meterno.addItemListener(new ItemListener() 
        { 
            public void itemStateChanged(ItemEvent ie)
            {
                try
                {
                    Conn c=new Conn();
                    ResultSet rs=c.st.executeQuery("select * from customer where meter_no='"+meterno.getSelectedItem()+"'");
                    while(rs.next())
                    {
                        c_Name.setText(rs.getString("cust_name"));
                        c_address.setText(rs.getString("address"));
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        lblunit_con=new JLabel("Units Consumed");
        lblunit_con.setBounds(100, 200, 100, 20);
        panel.add(lblunit_con);
        
        txtunit_con=new JTextField("");
        txtunit_con.setBounds(240, 200, 180, 20);
        panel.add(txtunit_con);
        
        lblmonth=new JLabel("Month");
        lblmonth.setBounds(100, 240, 100, 20);
        panel.add(lblmonth);
        
        c_month=new Choice();
        c_month.add("January");
        c_month.add("February");
        c_month.add("March");
        c_month.add("April");
        c_month.add("May");
        c_month.add("June");
        c_month.add("Jully");
        c_month.add("August");
        c_month.add("September");
        c_month.add("Auctomer");
        c_month.add("November");
        c_month.add("December");
        c_month.setBounds(240, 240, 180, 20);
        panel.add(c_month);
        
        btnsubmit=new JButton("Submit");
        btnsubmit.setBounds(140, 320, 100, 25);
        btnsubmit.setBackground(Color.BLACK);
        btnsubmit.setForeground(Color.white);
        btnsubmit.addActionListener(this);
        panel.add(btnsubmit);
        
        btncancel=new JButton("Cancel");
        btncancel.setBounds(300, 320, 100, 25);
        btncancel.setBackground(Color.BLACK);
        btncancel.setForeground(Color.white);
        btncancel.addActionListener(this);
        panel.add(btncancel);
        
        setLayout(new BorderLayout());
        add(panel,"Center");
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/calcu_bill.png"));
        Image i2=i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image,"West");
        
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btnsubmit)
        {
            String meter=meterno.getSelectedItem();
            String units=txtunit_con.getText();
            String month=c_month.getSelectedItem();
           
            int totalbill=0;
            int unit_consumed=Integer.parseInt(units);
            
            String query="select * from tax";
            
            try
            {
                Conn c=new Conn();
                ResultSet rs=c.st.executeQuery(query);
                
                while(rs.next())
                {
                    totalbill+=unit_consumed*Integer.parseInt(rs.getString("cost_per_unit"));
                    totalbill+=Integer.parseInt(rs.getString("meter_rent"));
                    totalbill+=Integer.parseInt(rs.getString("service_charge"));
                    totalbill+=Integer.parseInt(rs.getString("service_tax"));
                    totalbill+=Integer.parseInt(rs.getString("swaccha_bharat"));
                    totalbill+=Integer.parseInt(rs.getString("fixed_tax"));
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            String query2="insert into bill values('"+meter+"','"+month+"','"+units+"','"+totalbill+"','Not Paid')";
            
            try
            {
                Conn c=new Conn();
                c.st.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Bill Updated SuccessFully..");
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
        new calculateBill();
    }
}
