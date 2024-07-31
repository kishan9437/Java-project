package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener 
{    
    JLabel lblusername,lblpassword,logininas;
    JTextField txtname,txtpassword;
    Choice loginin;
    JButton login,cancel,signup;
    Login()
    {
        super("Login");
        getContentPane().setBackground(Color.white);
        
        setLayout(null);
        
        lblusername=new JLabel("UserName");
        lblusername.setBounds(320, 25, 300, 40);
        add(lblusername);
        
        txtname=new JTextField();
        txtname.setBounds(420, 30, 170, 25);
        add(txtname);
        
        lblpassword=new JLabel("Password");
        lblpassword.setBounds(320, 65, 300, 40);
        add(lblpassword);
        
        txtpassword=new JPasswordField();
        txtpassword.setBounds(420, 72, 170, 25);
        add(txtpassword);
        
        logininas=new JLabel("Login in as");
        logininas.setBounds(320, 105, 100, 40);
        add(logininas);
        
        loginin=new Choice();
        loginin.add("Admin");
        loginin.add("Customer");
        loginin.setBounds(420, 112, 170, 25);
        add(loginin);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2=i1.getImage().getScaledInstance(18, 16, Image.SCALE_DEFAULT);
        login=new JButton("Login", new ImageIcon(i2));
        login.addActionListener(this);
        login.setBounds(330, 170, 110, 25);
        add(login);
        
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("icon/cancel.png"));
        Image i4=i3.getImage().getScaledInstance(14, 14, Image.SCALE_DEFAULT);
        cancel=new JButton("Cancel",new ImageIcon(i4));
        cancel.addActionListener(this);
        cancel.setBounds(470, 170, 110, 25);
        add(cancel);
        
        ImageIcon i5=new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6=i5.getImage().getScaledInstance(18, 16, Image.SCALE_DEFAULT);
        signup=new JButton("Signup",new ImageIcon(i6));   
        signup.addActionListener(this);
        signup.setBounds(395, 215, 110, 25);
        add(signup);
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icon/loginp.png"));
        Image i8=i7.getImage().getScaledInstance(250, 150, WIDTH);
        ImageIcon i9=new ImageIcon(i8);
        JLabel image=new JLabel(i9);
        image.setBounds(20, 30, 250, 150);
        add(image);
       
        setSize(630,320);
        setLocation(430,180);
        setVisible(true);
         
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==login)
        {
            String a_username=txtname.getText();
            String a_password=txtpassword.getText();
            String a_type=loginin.getSelectedItem();
            
            try
            {
                Conn c=new Conn();
                String query="select * from login where user_name='"+a_username+"' and password='"+a_password+"' and acc_type='"+a_type+"'";
                
                ResultSet rs=c.st.executeQuery(query);
                
                if(rs.next())
                {
                    String meter=rs.getString("meter_no");
                    setVisible(false);
                    new index(a_type,meter);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    txtname.setText("");
                    txtpassword.setText("");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==cancel)
        {
            System.exit(0);
        }
        else if(ae.getSource()==signup)
        {
            new signup();
            setVisible(false);
        }
    }   
    public static void main(String []args)
    {
        new Login();
    }
}
