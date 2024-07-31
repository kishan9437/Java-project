package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class meterinfo extends JFrame implements ActionListener{
    JLabel heading,lblmeterno,lblMeternumber,lblmeterlocation,lblmetertype,lblphase,lblbilltype,lblday,lblnote;
    JTextField txtcustomer,txtaddress,txtcity,txtstate,txtemail,txtphone;
    JButton btnsubmit;
    Choice meterlocation,metertype,phasecode,billtype;
    String meternumber;
    meterinfo(String meternumber)
    {
        this.meternumber=meternumber;
        setSize(600,500);
        setLocation(430,180);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173,216,230));
        add(panel);
        
        heading=new JLabel("Meter Information");
        heading.setBounds(180, 17, 220, 25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,27));
        panel.add(heading);
        
        lblmeterno=new JLabel("Meter Number");
        lblmeterno.setBounds(100, 80, 100, 20);
        panel.add(lblmeterno);
        
        lblMeternumber=new JLabel(meternumber);
        lblMeternumber.setBounds(240, 80, 180, 20);
        panel.add(lblMeternumber);
        
        lblmeterlocation=new JLabel("Meter Location");
        lblmeterlocation.setBounds(100, 120, 100, 20);
        panel.add(lblmeterlocation);
        
        meterlocation=new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240, 120, 250, 20);
        panel.add(meterlocation);
               
        lblmetertype=new JLabel("Meter Type");
        lblmetertype.setBounds(100, 160, 100, 20);
        panel.add(lblmetertype);
        
        metertype=new Choice();
        metertype.add("Electric meter");
        metertype.add("Solar meter");
        metertype.add("Smart meter");
        metertype.setBounds(240, 160, 250, 20);
        panel.add(metertype);
        
        lblphase=new JLabel("Phase Code");
        lblphase.setBounds(100, 200, 100, 20);
        panel.add(lblphase);
        
        phasecode=new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240, 200, 250, 20);
        panel.add(phasecode);
        
        lblbilltype=new JLabel("Bill Type");
        lblbilltype.setBounds(100, 240, 100, 20);
        panel.add(lblbilltype);
        
        billtype=new Choice();
        billtype.add("Normal");
        billtype.add("Industial");
        billtype.setBounds(240, 240, 250, 20);
        panel.add(billtype);
        
        lblday=new JLabel("Days");
        lblday.setBounds(100, 280, 100, 20);
        panel.add(lblday);
        
        JLabel days=new JLabel("30 Days");
        days.setBounds(240, 280, 180, 20);
        panel.add(days);
        
        lblnote=new JLabel("Note");
        lblnote.setBounds(100, 320, 100, 20);
        panel.add(lblnote);
        
        JLabel note=new JLabel("By Default Bill is calculated for 30 days only");
        note.setBounds(240, 320, 250, 20);
        panel.add(note);
        
        btnsubmit=new JButton("Submit");
        btnsubmit.setBounds(230, 380, 100, 25);
        btnsubmit.setBackground(Color.BLACK);
        btnsubmit.setForeground(Color.white);
        btnsubmit.addActionListener(this);
        panel.add(btnsubmit);
        
        setLayout(new BorderLayout());
        add(panel,"Center");
        
        
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btnsubmit)
        {
            String meterno=meternumber;
            String m_location=meterlocation.getSelectedItem();
            String m_type=metertype.getSelectedItem();
            String p_code=phasecode.getSelectedItem();
            String b_type=billtype.getSelectedItem();
            String days="30";
                       
            String query="insert into meter_info values('"+meterno+"','"+m_location+"','"+m_type+"','"+p_code+"','"+b_type+"','"+days+"')";
            
            try
            {
                Conn c=new Conn();
                c.st.executeUpdate(query);
                                
                JOptionPane.showMessageDialog(null, "Meter Information Added SuccessFully..");
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
        new meterinfo("");
    }
}