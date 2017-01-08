import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class Question4 extends JFrame
{
    private ArrayList<String> studentList = new ArrayList<String>();
    JButton jbtCreate=new JButton("Create");
    JButton jbtRetrieve=new JButton("Retrieve");
    JButton jbtUpdate=new JButton("Update");
    JButton jbtDelete=new JButton("Delete");
    
    //private JTextField jtfTel= new JTextField(20);
    private JTextField jtfTel1=new JTextField(3);
    private JTextField jtfTel2=new JTextField(7);

    public Question4()
    {
        initializeStudents();
        setLayout(new FlowLayout());
        add(jbtCreate);
        add(jbtRetrieve);
        add(jbtUpdate);
        add(jbtDelete);
        
        ListStudentListener listStudentListener=new ListStudentListener();
        jbtCreate.addActionListener(listStudentListener);
        jbtUpdate.addActionListener(listStudentListener);
        jbtDelete.addActionListener(listStudentListener);
        
         jbtCreate.addActionListener(new CreateListener());
         jbtUpdate.addActionListener(new UpdateListener());
         jbtRetrieve.addActionListener(new RetrieveListener());
         jbtDelete.addActionListener(new DeleteListener());
         
        
        
        add(jtfTel1);
        add(new JLabel("-"));
        add(jtfTel2);
        String str1=jtfTel1.getText();
        String str2=jtfTel2.getText();
        String str3=str1+"-"+str2;
        //add(jtfTel);
        //jtfTel.setText(str3);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }
      public void initializeStudents() {
        studentList.add("Jack Ryan");
        studentList.add("Anne Hathaway");
        studentList.add("Marion Lee");
    }
      
      private class CreateListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
          {
              String studName=JOptionPane.showInputDialog("Enter new student's name:");
              studentList.add(studName);
              
          }
      }
       private class RetrieveListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
          {
              String retrieveOption=JOptionPane.showInputDialog("Please enter the index of student that you would like to retrieve:");
              int conversedRetrieveOption=Integer.parseInt(retrieveOption)-1;
              for(int i=0;i<studentList.size();i++)
              {
                  if(conversedRetrieveOption==i)
                  {
                       JOptionPane.showMessageDialog(null, studentList.get(i),"Updated Student List",JOptionPane.INFORMATION_MESSAGE); 
                  }
              }
              
          }
      }
       private class UpdateListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
          {
              String updateOption=JOptionPane.showInputDialog("Please enter the index of student that you would like to retrieve:");
               int conversedUpdateOption=Integer.parseInt(updateOption)-1;
              for(int i=0;i<studentList.size();i++)
              {
                  if(conversedUpdateOption==i)
                  {
                    String newName = JOptionPane.showInputDialog(null, "Current student name is " + 
                    studentList.get(i) + ".  Enter updated name: ", "Update Student", JOptionPane.INFORMATION_MESSAGE);
                    studentList.set(i,newName);
                  }
                      
              }
          }
      }
        private class DeleteListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
          {
              String updateOption=JOptionPane.showInputDialog("Please enter the index of student that you would like to retrieve:");
               int conversedUpdateOption=Integer.parseInt(updateOption)-1;
              for(int i=0;i<studentList.size();i++)
              {
                  if(conversedUpdateOption==i)
                  {
                   
                    studentList.remove(i);
                  }
                      
              }
          }
      }
      private class ListStudentListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
          {
              String outputStr="";
              for(int i=0;i<studentList.size();i++)
              {
                  outputStr+=(i+1)+". "+studentList.get(i)+"\n";
              }
              JOptionPane.showMessageDialog(null, outputStr,"Updated Student List",JOptionPane.INFORMATION_MESSAGE); 
              
          }
      }
      
    public static void main(String[]args)
    {
        new Question4();
    }
}