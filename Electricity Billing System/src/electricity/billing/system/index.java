package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class index extends JFrame implements ActionListener 
{
    JButton new_cust,cust_detail,deposit_detail,calc_bill,update_info,view_info,
            pay_bill,bill_detail,gen_bill,note,calcu,exit;
    String a_type,meter;
    index(String a_type,String meter)
    {
        this.a_type=a_type;
        this.meter=meter;
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        
        JLabel heding=new JLabel("Electricity  Billing  System");
        heding.setBounds(250, 50, 1000, 70);
        heding.setForeground(Color.PINK);
        heding.setFont(new Font("Lucida Fax",Font.BOLD,60));
        add(heding);
        
        new_cust=new JButton("New Customer");
        new_cust.setFont(new Font("Tahoma",Font.BOLD,22));
        new_cust.setBackground(Color.black);
        new_cust.setForeground(Color.pink);
        ImageIcon icon1=new ImageIcon(ClassLoader.getSystemResource("icon/newcust.png"));
        Image image1=icon1.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        new_cust.setIcon(new ImageIcon(image1));
        new_cust.setBounds(120, 150, 260, 50);
        new_cust.addActionListener(this);
        
        
        cust_detail=new JButton("Customer Details");
        cust_detail.setFont(new Font("Tahoma",Font.BOLD,22));
        cust_detail.setBackground(Color.black);
        cust_detail.setForeground(Color.pink);
        ImageIcon icon2=new ImageIcon(ClassLoader.getSystemResource("icon/cust_detail.png"));
        Image image2=icon2.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        cust_detail.setIcon(new ImageIcon(image2));
        cust_detail.setBounds(120, 250, 260, 50);
        cust_detail.addActionListener(this);
        
        
        deposit_detail=new JButton("Deposit Details");
        deposit_detail.setFont(new Font("Tahoma",Font.BOLD,22));
        deposit_detail.setBackground(Color.black);
        deposit_detail.setForeground(Color.pink);
        ImageIcon icon3=new ImageIcon(ClassLoader.getSystemResource("icon/Deposit.jpg"));
        Image image3=icon3.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        deposit_detail.setIcon(new ImageIcon(image3));
        deposit_detail.setBounds(120, 350, 260, 50);
        deposit_detail.addActionListener(this);
        
        calc_bill=new JButton("Calculate Bill");
        calc_bill.setFont(new Font("Tahoma",Font.BOLD,22));
        calc_bill.setBackground(Color.black);
        calc_bill.setForeground(Color.pink);
        ImageIcon icon4=new ImageIcon(ClassLoader.getSystemResource("icon/calcbill.png"));
        Image image4=icon4.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        calc_bill.setIcon(new ImageIcon(image4));
        calc_bill.setBounds(120, 450, 260, 50);
        calc_bill.addActionListener(this);
        
        update_info=new JButton("Update Information");
        update_info.setFont(new Font("Tahoma",Font.BOLD,22));
        update_info.setBackground(Color.black);
        update_info.setForeground(Color.pink);
        ImageIcon icon5=new ImageIcon(ClassLoader.getSystemResource("icon/updateinfo.png"));
        Image image5=icon5.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        update_info.setIcon(new ImageIcon(image5));
        update_info.setBounds(550, 150, 260, 50);
        update_info.addActionListener(this);

        
        view_info=new JButton("View Information");
        view_info.setFont(new Font("Tahoma",Font.BOLD,22));
        view_info.setBackground(Color.black);
        view_info.setForeground(Color.pink);
        ImageIcon icon6=new ImageIcon(ClassLoader.getSystemResource("icon/viewinfo.png"));
        Image image6=icon6.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        view_info.setIcon(new ImageIcon(image6));
        view_info.setBounds(550, 250, 260, 50);
        view_info.addActionListener(this);
        
        pay_bill=new JButton("Pay Bill");
        pay_bill.setFont(new Font("Tahoma",Font.BOLD,22));
        pay_bill.setBackground(Color.black);
        pay_bill.setForeground(Color.pink);
        ImageIcon icon7=new ImageIcon(ClassLoader.getSystemResource("icon/paybill.jpg"));
        Image image7=icon7.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        pay_bill.setIcon(new ImageIcon(image7));
        pay_bill.setBounds(550, 350, 260, 50);
        pay_bill.addActionListener(this);
        
        bill_detail=new JButton("Bill Details");
        bill_detail.setFont(new Font("Tahoma",Font.BOLD,22));
        bill_detail.setBackground(Color.black);
        bill_detail.setForeground(Color.pink);
        ImageIcon icon8=new ImageIcon(ClassLoader.getSystemResource("icon/billde.png"));
        Image image8=icon8.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        bill_detail.setIcon(new ImageIcon(image8));
        bill_detail.setBounds(550, 450, 260, 50);
        bill_detail.addActionListener(this);

        gen_bill=new JButton("Generate Bill");
        gen_bill.setFont(new Font("Tahoma",Font.BOLD,22));
        gen_bill.setBackground(Color.black);
        ImageIcon icon9=new ImageIcon(ClassLoader.getSystemResource("icon/genebill.png"));
        Image image9=icon9.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        gen_bill.setIcon(new ImageIcon(image9));
        gen_bill.setForeground(Color.pink);
        gen_bill.setBounds(550, 550, 260, 50);
        gen_bill.addActionListener(this);

        note=new JButton("Notepad");
        note.setFont(new Font("Tahoma",Font.BOLD,22));
        note.setBackground(Color.black);
        ImageIcon icon10=new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png"));
        Image image10=icon10.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        note.setIcon(new ImageIcon(image10));
        note.setForeground(Color.pink);
        note.setBounds(1000, 150, 260, 50);
        note.addActionListener(this);
        
        calcu=new JButton("Calculator");
        calcu.setFont(new Font("Tahoma",Font.BOLD,22));
        calcu.setBackground(Color.black);
        ImageIcon icon11=new ImageIcon(ClassLoader.getSystemResource("icon/cal.png"));
        Image image11=icon11.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        calcu.setIcon(new ImageIcon(image11));
        calcu.setForeground(Color.pink);
        calcu.setBounds(1000, 250, 260, 50);
        calcu.addActionListener(this);

        exit=new JButton("Exit");
        exit.setFont(new Font("Tahoma",Font.BOLD,22));
        exit.setBackground(Color.black);
        ImageIcon icon12=new ImageIcon(ClassLoader.getSystemResource("icon/exit.png"));
        Image image12=icon12.getImage().getScaledInstance(20, 22, Image.SCALE_DEFAULT);
        exit.setIcon(new ImageIcon(image12));
        exit.setForeground(Color.pink);
        exit.setBounds(1000, 350, 260, 50);
        exit.addActionListener(this);

        if(a_type.equals(("Admin")))
        {   
            setTitle("Welcome Admin");
            add(new_cust);
            add(cust_detail);
            add(deposit_detail);        
            add(calc_bill);
        }
        else
        {
            setTitle("Welcome Customer");
            add(update_info);
            add(view_info);
            add(pay_bill);
            add(bill_detail);
            add(gen_bill);
        }
        add(note);
        add(calcu);
        add(exit);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/home5.png"));
        Image i2=i1.getImage().getScaledInstance(1550, 800, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel i4=new JLabel(i3);
        i4.setBounds(1,1,1550,800);
        add(i4);
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==new_cust)
        {
            new NewCustomer();
        }
        else if(ae.getSource()==cust_detail)
        {
            new Customerdetails();
        }
        else if(ae.getSource()==deposit_detail)
        {
            new DepositDetails();
        }
        else if(ae.getSource()==calc_bill)
        {
            new calculateBill();
        }
        else if(ae.getSource()==view_info)
        {
            new Viewinformation(meter);
        }
        else if(ae.getSource()==update_info)
        {
            new Updateinformation(meter);
        }
        else if(ae.getSource()==bill_detail)
        {
            new Billdetail(meter);
        }
        else if(ae.getSource()==pay_bill)
        {
            new Paybill(meter);
        }
        else if(ae.getSource()==gen_bill)
        {
            new Generatebill(meter);
        }
        else if(ae.getSource()==note)
        {
            try
            {
                Runtime.getRuntime().exec("notepad.exe");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==calcu)
        {
            try
            {
                Runtime.getRuntime().exec("calc.exe");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==exit)
        {
            setVisible(false);
            new Login();
        }
    }
    public static void main(String []args)
    {
        new index("","");
    }
}
