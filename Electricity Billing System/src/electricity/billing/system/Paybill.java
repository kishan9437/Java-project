package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Paybill extends JFrame implements ActionListener{
    
    JLabel lblheading,lblmeterno,meterno,lblname,name,lblmonth,lblunit,unit,lbltotalbill,totalbill,lblstatus,status;
    Choice chcmonth;
    JButton pay,back;
    String meter;
    Paybill(String meter)
    {
        this.meter=meter;
        setLayout(null);
        setBounds(300,150,900,600);
        getContentPane().setBackground(Color.WHITE);
        
        lblheading = new JLabel("Pay Electricity Bill");
        lblheading.setBounds(120,10,300,30);
        lblheading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(lblheading);
        
        lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(35,80,200,20);
        add(lblmeterno);
        
        meterno = new JLabel("");
        meterno.setBounds(300,80,200,20);
        add(meterno);
        
        lblname = new JLabel("Name");
        lblname.setBounds(35,140,200,20);
        add(lblname);
        
        name = new JLabel("");
        name.setBounds(300,140,200,20);
        add(name);
        
        lblmonth = new JLabel("Month");
        lblmonth.setBounds(35,200,200,20);
        add(lblmonth);
        
        chcmonth=new Choice();
        chcmonth.add("January");
        chcmonth.add("February");
        chcmonth.add("March");
        chcmonth.add("April");
        chcmonth.add("May");
        chcmonth.add("June");
        chcmonth.add("Jully");
        chcmonth.add("August");
        chcmonth.add("September");
        chcmonth.add("Auctomer");
        chcmonth.add("November");
        chcmonth.add("December");
        chcmonth.setBounds(300, 200, 200, 20);
        add(chcmonth);
        
        lblunit = new JLabel("Units");
        lblunit.setBounds(35,260,200,20);
        add(lblunit);
        
        unit = new JLabel("");
        unit.setBounds(300,260,200,20);
        add(unit);
        
        lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35,320,200,20);
        add(lbltotalbill);
        
        totalbill = new JLabel("");
        totalbill.setBounds(300,320,200,20);
        add(totalbill);
        
        lblstatus = new JLabel("Status");
        lblstatus.setBounds(35,380,200,20);
        add(lblstatus);
        
        status = new JLabel("");
        status.setBounds(300,380,200,20);
        status.setForeground(Color.red);
        add(status);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.st.executeQuery("select * from customer where meter_no='"+meter+"'");
            
            while(rs.next())
            {
                meterno.setText(meter);
                name.setText(rs.getString("cust_name"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        chcmonth.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ae)
            {
                try{
                    Conn c=new Conn();
                    ResultSet rs=c.st.executeQuery("select * from bill where meter_no='"+meter+"' and month='"+chcmonth.getSelectedItem()+"'");
                    
                    while(rs.next())
                    {
                        unit.setText(rs.getString("units"));
                        totalbill.setText(rs.getString("total_bill"));
                        status.setText(rs.getString("status"));
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        
        pay=new JButton("Pay");
        pay.setBackground(Color.black);
        pay.setForeground(Color.white);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);
        
        back=new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/bill_pay.png"));
        Image i2=i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400, 120, 600, 300);
        add(image);
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==pay)
        {
            try
            {
                Conn c=new Conn();
                c.st.executeUpdate("update bill set status='Paid' where meter_no='"+meter+"' and month='"+chcmonth.getSelectedItem()+"'");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            setVisible(false);
            
            new Paytm(meter);
        }
        else
        {
            setVisible(false);
        }
    }
    public static void main(String []args)
    {
        new Paybill("");
    }
}
