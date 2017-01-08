import javax.swing.*;
import java.awt.*;

public class StudentInfoFrame extends JFrame
{
    JTextField jtfRegNo=new JTextField(10);
    JTextField jtfName=new JTextField(20);
    JTextField jtfCode=new JTextField(10);
    JButton jbt1=new JButton("Submit");
    public StudentInfoFrame()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
        
        add(new JLabel("Registration number"));
        add(jtfRegNo);
        add(new JLabel("Name"));
        add(jtfName);
        add(new JLabel("Programme code"));
        add(jtfCode);
        add(jbt1);
        
        setTitle("Student Information");
        pack();
        setSize(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    public static void main(String[]args)
    {
        new StudentInfoFrame();
    }
}