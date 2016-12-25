package ui; //Author:Teh Yi Ting
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit;  
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JPanel;  
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.swingx.JXDatePicker;
import domain.Bus;
import control.BusControl;
import control.RouteControl;
import control.TripControl;
import domain.Route;
import domain.TripClass;
import java.util.Date;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;
import control.TripSeatControlForSchedule;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.*;
public class CreateNewTrip extends JFrame
{
     public static String now(String dateFormat) {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    return sdf.format(cal.getTime());
  }
    BusControl busControl;
    RouteControl routeControl=new RouteControl();
    TripControl tripControl;
    TripSeatControlForSchedule tripSeatControl;
    
    private ArrayList<Bus> busList = new ArrayList<Bus>();
    private ArrayList<Route> routeList = new ArrayList<Route>();
     ArrayList<String> busIDOnSameDate = new ArrayList<String>();
     
    private Pattern pattern;  
    private Matcher matcher;  
  
    private static final String TIME24HOURS_PATTERN ="([01]?[0-9]|2[0-3]):[0-5][0-9]";      
     
    public static int countPress=0;
     JPanel jpanel=new JPanel() { 
  
   public void paintComponent(Graphics g) {  
     Image img = Toolkit.getDefaultToolkit().getImage(  
     homePage.class.getResource("../images/background1.jpg"));  
     g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
     }  
   }; 
     
     ImageIcon iconHome=new ImageIcon(getClass().getResource("../images/homepageicon.png"));
     
     Font buttonFont=new Font("French Script MT",Font.ITALIC,40);
     Font buttonFont2=new Font("Century",Font.ITALIC,18);
     Font buttonFont3=new Font("Century",Font.PLAIN,15);
     Font buttonFont4=new Font("Century",Font.PLAIN,17);
     
     JLabel jtrip=new JLabel("Trip Management - Create New Trip"); 
     JLabel jlbTrip=new JLabel("New Trip Details");
     JLabel jlbBlank=new JLabel("");
     
     JLabel jlbRouteID=new JLabel("Route ID");
     JLabel jlbDepDate=new JLabel("Departure Date");
     JLabel jlbDepTime=new JLabel("Departure Time (24hrs)");
     JLabel jlbPrice=new JLabel("Trip Price (RM)");
     JLabel jlbBusType=new JLabel("Bus Type");
     JLabel jlbBusID=new JLabel("Bus ID");
     JLabel jlbTotSeat=new JLabel("Total Seat");
     
     JTextField jtfRouteID=new JTextField(15);
     JTextField jtfDepTime=new JTextField("0000",15);
     JTextField jtfPrice=new JTextField(15);
     
    private DefaultComboBoxModel dcbomBusList= new DefaultComboBoxModel();
    private JComboBox jcboBusList = new JComboBox(dcbomBusList);
    
    private DefaultComboBoxModel dcbomRouteList= new DefaultComboBoxModel();
    private JComboBox jcboRouteList = new JComboBox(dcbomRouteList);
    
     JTextField jtfTotSeat=new JTextField(15);
     
     JPanel jp2=new JPanel(new GridLayout(1,6));
     JPanel jp3=new JPanel(new GridLayout(9,2,5,5));
     JPanel jp4=new JPanel(new GridLayout(1,2));
     JPanel jp5=new JPanel(new FlowLayout(FlowLayout.CENTER));
     JPanel jp6=new JPanel(new FlowLayout(FlowLayout.CENTER));
    
     JPanel menuPanel_r1=new JPanel(new FlowLayout(FlowLayout.CENTER));
     JPanel menuPanel_r2=new JPanel(new FlowLayout(FlowLayout.CENTER));
     JPanel menuPanel_r3=new JPanel(new FlowLayout(FlowLayout.CENTER));
     JPanel menuPanel_r4=new JPanel(new FlowLayout(FlowLayout.CENTER));
     JPanel menuPanel_r5=new JPanel(new FlowLayout(FlowLayout.CENTER));
     JPanel menuPanel_r6=new JPanel(new FlowLayout(FlowLayout.CENTER));
     JPanel menuPanel_r7=new JPanel(new FlowLayout(FlowLayout.CENTER));
     
     JPanel subPanel_r1=new JPanel(new FlowLayout(FlowLayout.LEFT));
     JPanel subPanel_r2=new JPanel(new FlowLayout(FlowLayout.CENTER));
     
    JButton jbtCreateTrip=new JButton("Create New Trip");
    JButton jbtViewTrip=new JButton("View Trip Table");
    JButton jbtRetrieveTrip=new JButton("Retrieve Trip");
    JButton jbtUpdateTrip=new JButton("Update Trip");
    JButton jbtMenu=new JButton("Back");
    JButton jbtHome=new JButton("Main Menu",iconHome);
    
    JButton jbtClear=new JButton("Clear");
    JButton jbtCreate=new JButton("Create");
    
     JRadioButton singleD = new JRadioButton("Single Deck");
     JRadioButton doubleD = new JRadioButton("Double Deck");
     ButtonGroup group = new ButtonGroup();
    
    LineBorder buttonBorder=new LineBorder(new Color(60,179,113),3);
    LineBorder buttonBorder2=new LineBorder(new Color(128,128,128),3);
     
    private JXDatePicker datePick=new JXDatePicker();
    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    private char accType;
     public CreateNewTrip(final char accType)
    {
     this.accType=accType;   
     if(accType=='U')
     {
     jbtCreateTrip.setEnabled(false);
     jbtUpdateTrip.setEnabled(false);
     }
     else
     {
     jbtCreateTrip.setEnabled(true);
     jbtUpdateTrip.setEnabled(true);
     }   
     pattern = Pattern.compile(TIME24HOURS_PATTERN);     
     busControl = new BusControl();
     //routeControl=new RouteControl();
     tripControl=new TripControl();
     tripSeatControl=new TripSeatControlForSchedule();
     initRouteComboBox();
     //initComboBox();
     
     setLayout(new BorderLayout());
     
     jtrip.setHorizontalAlignment(SwingConstants.CENTER);
     jpanel.add(jtrip);
     jtrip.setFont(buttonFont);
     jtrip.setForeground(new Color(32, 178, 170));
     jlbTrip.setFont(buttonFont);
     jlbTrip.setForeground(new Color(32, 178, 170));
     
     jbtCreateTrip.setPreferredSize(new Dimension(150, 40));
     jbtViewTrip.setPreferredSize(new Dimension(150, 40));
     jbtRetrieveTrip.setPreferredSize(new Dimension(150, 40));
     jbtUpdateTrip.setPreferredSize(new Dimension(150, 40));
     jbtMenu.setPreferredSize(new Dimension(150, 40));
     jbtHome.setPreferredSize(new Dimension(150, 40));
    
      jbtCreateTrip.setFont(buttonFont2);
      jbtViewTrip.setFont(buttonFont2);
      jbtRetrieveTrip.setFont(buttonFont2);
      jbtUpdateTrip.setFont(buttonFont2);
      jbtMenu.setFont(buttonFont2);
      jbtHome.setFont(buttonFont2);
      
      jbtCreateTrip.setBackground(Color.WHITE);
      jbtViewTrip.setBackground(Color.WHITE);
      jbtRetrieveTrip.setBackground(Color.WHITE);
      jbtUpdateTrip.setBackground(Color.WHITE);
      jbtMenu.setBackground(Color.WHITE);
      jbtHome.setBackground(Color.WHITE);
      jbtCreate.setBackground(Color.WHITE);
      jbtClear.setBackground(Color.WHITE);
   
       jbtCreateTrip.setBorder(buttonBorder);
       jbtViewTrip.setBorder(buttonBorder);
       jbtRetrieveTrip.setBorder(buttonBorder);
       jbtUpdateTrip.setBorder(buttonBorder);
       jbtMenu.setBorder(buttonBorder);
       jbtHome.setBorder(buttonBorder);
       
       jbtCreate.setBorder(buttonBorder2);
       jbtClear.setBorder(buttonBorder2);
       
       jbtCreate.setPreferredSize(new Dimension(100, 28));
       jbtClear.setPreferredSize(new Dimension(100, 28));
       
       jbtClear.setFont(buttonFont2);
       jbtCreate.setFont(buttonFont2);
     
      jbtCreateTrip.setMnemonic('C');
      jbtViewTrip.setMnemonic('V');
      jbtRetrieveTrip.setMnemonic('R');
      jbtUpdateTrip.setMnemonic('U');
      jbtMenu.setMnemonic('B');
      jbtHome.setMnemonic('M');
      
       jbtCreateTrip.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        jbtCreateTrip.setBackground(new Color(179, 255, 226));
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        jbtCreateTrip.setBackground(Color.WHITE);
        
    }
});
       jbtViewTrip.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        jbtViewTrip.setBackground(new Color(179, 255, 226));
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        jbtViewTrip.setBackground(Color.WHITE);
        
    }
});
       jbtRetrieveTrip.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        jbtRetrieveTrip.setBackground(new Color(179, 255, 226));
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        jbtRetrieveTrip.setBackground(Color.WHITE);
        
    }
});
       jbtUpdateTrip.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        jbtUpdateTrip.setBackground(new Color(179, 255, 226));
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        jbtUpdateTrip.setBackground(Color.WHITE);
        
    }
});
        jbtMenu.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        jbtMenu.setBackground(new Color(179, 255, 226));
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        jbtMenu.setBackground(Color.WHITE);
        
    }
});
         jbtHome.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        jbtHome.setBackground(new Color(179, 255, 226));
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        jbtHome.setBackground(Color.WHITE);
        
    }
});
         jbtClear.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        jbtClear.setBackground(new Color(230,230,230));
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        jbtClear.setBackground(Color.WHITE);
        
    }
});
         jbtCreate.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        jbtCreate.setBackground(new Color(230,230,230));
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        jbtCreate.setBackground(Color.WHITE);
        
    }
});
         
          jbtMenu.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               closeFrame();
               new ScheduleMenu(accType);
           }
       });
          
          jbtHome.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               closeFrame();
               new homePage(accType);
           }
       });
          jbtViewTrip.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               closeFrame();
               new TripMgmt(accType);
           }
       });
          jbtCreateTrip.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               closeFrame();
               new CreateNewTrip(accType);
           }
       });
     jbtRetrieveTrip.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               closeFrame();
               new RetrieveTrip(accType);
           }
       });
     jbtUpdateTrip.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               closeFrame();
               //new UpdateTrip(accType);
           }
       });
     
     jbtClear.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               jcboRouteList.setSelectedIndex(0);
               datePick.setDate(null);
               jtfDepTime.setText("");
               jtfPrice.setText("");
               singleD.setSelected(true);
               jtfTotSeat.setText("");
               jcboBusList.removeAllItems();
               
           }
       });
     
     jbtCreate.addActionListener(new ActionListener()
     {
        public void actionPerformed(ActionEvent e)
        {
            try {
                String subStrTime1="";
                String subStrTime2="";
                int countNegative=0;
                boolean value=true;
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date convertedDate = dateFormat.parse(CreateNewTrip.now("yyyy-MM-dd"));
                
                if(!jtfDepTime.getText().isEmpty() &&jtfDepTime.getText().length()==4 ){
                    subStrTime1=jtfDepTime.getText().substring(0,2);
                    subStrTime2=jtfDepTime.getText().substring(2,jtfDepTime.getText().length());
                    //System.out.println(subStrTime1+":"+subStrTime2);
                    value=validate(subStrTime1+":"+subStrTime2);
                    //System.out.println(value);
                }
                
                int countAlpha=0;
                for (int i = 0; i < jtfPrice.getText().length(); i++) {
                    char ch = jtfPrice.getText().charAt(i);
                    if (Character.isAlphabetic(ch)) {
                        countAlpha++;
                    }
                    
                }
                for (int j = 0; j < jtfPrice.getText().length(); j++) {
                    char ch = jtfPrice.getText().charAt(j);
                    if (ch=='-') {
                        countNegative++;
                    }
                    
                }
                
                if(datePick.getDate()==null)
                {
                    JOptionPane.showMessageDialog(null, "Please select the departure date.","WARNING",JOptionPane.WARNING_MESSAGE);
                }
                else if(datePick.getDate().compareTo(convertedDate)<0)
                {
                JOptionPane.showMessageDialog(null, "Date selected is lesser than current date.","WARNING",JOptionPane.WARNING_MESSAGE);
                }
                else if(jtfDepTime.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please enter the departure time.","WARNING",JOptionPane.WARNING_MESSAGE);
                }
                else if(!value)
                {
                    JOptionPane.showMessageDialog(null, "Please enter the departure time in 24hrs format.","WARNING",JOptionPane.WARNING_MESSAGE);
                    jtfDepTime.setText("");
                }
                else if(countNegative>0)
                {
                    JOptionPane.showMessageDialog(null, "Invalid input. Trip price could not be negative value.","WARNING",JOptionPane.WARNING_MESSAGE);
                    jtfPrice.setText("");
                }
                else if(countAlpha>0)
                {
                    JOptionPane.showMessageDialog(null, "Alphabetic input is invalid.\nPlease reenter.","WARNING",JOptionPane.WARNING_MESSAGE);
                    jtfPrice.setText("");
                }
                else if(jtfTotSeat.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please select the bus type.","WARNING",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    String routeid=jcboRouteList.getSelectedItem().toString();
                    char type;
                    if (singleD.isSelected()) {
                        type='S';
                    }
                    else
                    {
                        type='D';
                    }
                    //System.out.println(type);
                    SimpleDateFormat dateformat = new SimpleDateFormat("ddMMyy");
                    String date_to_string = dateformat.format(datePick.getDate());
                    //System.out.println(date_to_string);
                    String time=jtfDepTime.getText();
                    String tripno=routeid+type+date_to_string+time;
                    System.out.println(tripno);
                    TripClass trip=tripControl.selectRecord(tripno);
                    if(trip!=null)
                    {
                        JOptionPane.showMessageDialog(null, "The trip number "+ tripno+ " already exist in the database.","WARNING",JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        java.sql.Date sqlDate = new java.sql.Date(datePick.getDate().getTime());
                        System.out.println(sqlDate);
                        
                        if(jtfPrice.getText().isEmpty() || Double.parseDouble(jtfPrice.getText())==0 )
                        {
                            tripControl.createRecord(tripno,sqlDate,time,Integer.parseInt(jtfTotSeat.getText()),
                                    Integer.parseInt(jtfTotSeat.getText()),0.0,'N',
                                    jcboRouteList.getSelectedItem().toString(),jcboBusList.getSelectedItem().toString());
                            closeFrame();
                            new TripMgmt(accType);
                        }
                        else
                        {
                            tripControl.createRecord(tripno,sqlDate,time,Integer.parseInt(jtfTotSeat.getText()),
                                    Integer.parseInt(jtfTotSeat.getText()),Double.parseDouble(jtfPrice.getText()),'Y',
                                    jcboRouteList.getSelectedItem().toString(),jcboBusList.getSelectedItem().toString());
                            
                            String seatPrefix = "SA";
                            String seatno="";
                            int countSuccess=0;
                            for(int i=1;i<=Integer.parseInt(jtfTotSeat.getText());i++)
                            {
                                seatno=seatPrefix+String.format("%02d",i );
                                //System.out.println(seatno);
                                countSuccess+= tripSeatControl.createRecord(tripno,seatno ,'Y');
                                
                            }
                            if(countSuccess==Integer.parseInt(jtfTotSeat.getText()))
                            {
                                JOptionPane.showMessageDialog(null, countSuccess+" trip seats are successfully created.");
                                closeFrame();
                                new TripMgmt(accType);
                            }
                        }
                        
                    }
                    
                }} catch (ParseException ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
          
        }
     });
     
      menuPanel_r1.setOpaque(false);
      menuPanel_r2.setOpaque(false);
      menuPanel_r3.setOpaque(false);
      menuPanel_r4.setOpaque(false);
      menuPanel_r5.setOpaque(false);
      menuPanel_r6.setOpaque(false);
    
      menuPanel_r1.add(jbtCreateTrip);
      menuPanel_r2.add(jbtViewTrip);
      menuPanel_r3.add(jbtRetrieveTrip);
      menuPanel_r4.add(jbtUpdateTrip);
      menuPanel_r5.add(jbtMenu);
      menuPanel_r6.add(jbtHome);
 
      jp2.setOpaque(false);
      jp2.add( menuPanel_r1);
      jp2.add( menuPanel_r2);
      jp2.add( menuPanel_r3);
      jp2.add( menuPanel_r4);
      jp2.add( menuPanel_r5);
      jp2.add( menuPanel_r6);
      
      jp3.setOpaque(false);
      jp3.add(jlbTrip);
      jp3.add(jlbBlank);
      jp3.add(jlbRouteID);
      jp3.add(jcboRouteList);
      jp3.add(jlbDepDate);
      jp3.add(datePick);
      datePick.setFormats(dateFormat);
     
      jp3.add(jlbDepTime);
      jp3.add(jtfDepTime);
      jp3.add(jlbPrice);
      jp3.add(jtfPrice);
      jp3.add(jlbBusType);
     
      menuPanel_r7.setOpaque(false);
      menuPanel_r7.add(singleD);
      menuPanel_r7.add(doubleD);
      
      singleD.setOpaque(false);
      doubleD.setOpaque(false);
      group.add(singleD);
      singleD.setSelected(true);
      group.add(doubleD);
      singleD.setFont(buttonFont3);
      doubleD.setFont(buttonFont3);
      singleD.addActionListener(new ButtonListener());
      doubleD.addActionListener(new ButtonListener());

      jp3.add(menuPanel_r7);
      jp3.add(jlbBusID);
      jp3.add(jcboBusList);
      jp3.add(jlbTotSeat);
      jp3.add(jtfTotSeat);
      
    jcboBusList.addActionListener(new ActionListener () {
    public void actionPerformed(ActionEvent e) {
   
        if(jcboBusList.getSelectedIndex()!=-1){
        //int index=jcboBusList.getSelectedIndex();
        String selectedBusID=jcboBusList.getSelectedItem().toString();
        
         for(int i=0; i<busList.size(); i++){
           
              if(selectedBusID.compareTo(busList.get(i).getBusid())==0)
              {
              if(busList.get(i).getBustype().compareTo('S')==0)
                {
                    //System.out.println(busList.get(i).getBustype());
                    jtfTotSeat.setText("24");
                   
                }
              else
                {
                    //System.out.println(busList.get(i).getBustype());
                    jtfTotSeat.setText("54");
                    
                }
              }
          
          }
     
        }
    }});
         
    jtfDepTime.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent e){
                ValidateTime();
            }
            
            public void removeUpdate(DocumentEvent e){
                //ValidateTime();
            }
            
            public void insertUpdate(DocumentEvent e){
                ValidateTime();
            }        
        });  
     jtfPrice.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent e){
                ValidatePrice();
            }
            
            public void removeUpdate(DocumentEvent e){
                //ValidatePrice();
            }
            
            public void insertUpdate(DocumentEvent e){
                ValidatePrice();
            }        
        });  
    
      jp5.setOpaque(false);
      jp5.add(jbtClear);
      jp5.add(jbtCreate);
      
      jp3.add(jp5);
      
      jlbRouteID.setFont(buttonFont2);
      //jtfRouteID.setFont(buttonFont2);
      jcboRouteList.setFont(buttonFont4);
      jlbDepDate.setFont(buttonFont2);
      datePick.setFont(buttonFont2);
      jlbDepTime.setFont(buttonFont2);
      jtfDepTime.setFont(buttonFont2);
      jlbPrice.setFont(buttonFont2);
      jtfPrice.setFont(buttonFont2);
      jlbBusID.setFont(buttonFont2);
      jcboBusList.setFont(buttonFont4);
      jlbTotSeat.setFont(buttonFont2);
      jtfTotSeat.setFont(buttonFont2);
      jlbBusType.setFont(buttonFont2);
      
      jtfTotSeat.setEditable(false);
   
      subPanel_r1.add(jp3);
      jp4.add(subPanel_r1);
      jp4.add(subPanel_r2);
     
      subPanel_r1.setOpaque(false);
      subPanel_r2.setOpaque(false);
      jp4.setOpaque(false);
      
     jpanel.add(jp2,BorderLayout.NORTH);
     jpanel.add(jp4,BorderLayout.WEST);
     add(jpanel,BorderLayout.CENTER);

     setIcon();
     addWindowListener(new WindowListener());
     setTitle("Create New Trip");
     setSize(1050,640);
     setVisible(true);
     setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
     setLocationRelativeTo(null);
      
    }
     private class WindowListener extends WindowAdapter{
        public void windowClosing(WindowEvent e) {
         int confirm = JOptionPane.showConfirmDialog(null, "Confirm return to the Home Page?", "Confirm?", JOptionPane.WARNING_MESSAGE);
           if(confirm == JOptionPane.YES_OPTION){
           dispose();
           new homePage(accType);
           }
        
        }
       
       }
     private void initRouteComboBox(){
     
     routeList=routeControl.getRouteList();
        for(int j=0;j<routeList.size();j++)
        {
            dcbomRouteList.addElement(routeList.get(j).getRouteid());
        }
     }
        
     private void initComboBox(char type){
        Date sameDepDate=datePick.getDate();
        //System.out.println(sameDepDate);
        java.sql.Date sqlDate = new java.sql.Date(sameDepDate.getTime());
        //System.out.println(sqlDate);
        busIDOnSameDate=tripControl.selectBusIDbyDate(type,sqlDate);
        System.out.println(busIDOnSameDate);
        busList = busControl.getBusList();
        for(int i=0; i<busList.size(); i++){
        if(busList.get(i).getBustype().compareTo(type)==0)
        {
            dcbomBusList.addElement(busList.get(i).getBusid());
            for(int j=0;j<busIDOnSameDate.size();j++)
            {
               if(busIDOnSameDate.get(j).compareTo(busList.get(i).getBusid())==0)
               {
                   String str = busIDOnSameDate.get(j);
                   Object obj = str;
                   

                int index=dcbomBusList.getIndexOf(obj);
                System.out.println(index);
                if(index!=-1)
                {
                    dcbomBusList.removeElementAt(index);
                }
               }
               
            }
        }
        }
       
    }
     private class ButtonListener implements ActionListener
     {
         public void actionPerformed(ActionEvent e)
         { 
             char ch;
             if(datePick.getDate()==null)
             {
             JOptionPane.showMessageDialog(null, "Please select the departure date.","WARNING",JOptionPane.WARNING_MESSAGE); 
             }
             else{
            
            if(e.getSource()==singleD)
            {
                 if(countPress>=1){
               dcbomBusList.removeAllElements();
               jcboBusList.removeAllItems();
               /*for(int i=0;i<busList.size();i++){
               busList.remove(i);
               }*/
      
               }
               //jcboBusList.removeAllItems();
                ch='S';  //System.out.print(ch);
               initComboBox(ch);
               countPress++;
               
            }
            else
            {
               if(countPress>=1){
               dcbomBusList.removeAllElements();
               jcboBusList.removeAllItems();
              /* for(int i=0;i<busList.size();i++){
               busList.remove(i);
               }*/
      
               }
               
                ch='D';//System.out.print(ch);
                 initComboBox(ch);
                 countPress++;
                 
              }}
             //System.out.print(countPress);
        
        }
         
     }
    
      public void closeFrame() {
                this.dispose();
        }
      private void setIcon() {
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/ezWayLogo.jpg")));
    }
     private void ValidateTime()
     {
     try{
         Integer time=Integer.parseInt(jtfDepTime.getText());
         
          if(jtfDepTime.getText().length()>4)
          {
          jtfDepTime.setBackground(new Color(255, 230, 230));
          JOptionPane.showMessageDialog(null, "Maximum 4 character for departure time.\nPlease reenter in 24hour format.","WARNING",JOptionPane.WARNING_MESSAGE);
          }
          else
          {
              if(jtfDepTime.getText().length()==4)
              {
                  String subStrTime1="";
                  String subStrTime2="";
                  subStrTime1=jtfDepTime.getText().substring(0,2);
                  subStrTime2=jtfDepTime.getText().substring(2,jtfDepTime.getText().length());
                  //System.out.print(subStrTime1);
                  //System.out.print(subStrTime2);
                  boolean value=validate(subStrTime1+":"+subStrTime2);
                  //System.out.print(subStrTime1+":"+subStrTime2);
                  if(value)
                  {
                      System.out.print("true");
                      jtfDepTime.setBackground(new Color(240, 255, 240));
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Invalid input.\nPlease reenter departure time in 24hour format.","WARNING",JOptionPane.WARNING_MESSAGE);
                      jtfDepTime.setBackground(new Color(255, 230, 230));
                      System.out.print("false");
                  }
                 
              }
           
          }
         }catch(NumberFormatException ex)
         {
             jtfDepTime.setBackground(new Color(255, 230, 230));
             JOptionPane.showMessageDialog(null, "Alphabetic input is invalid.\nPlease reenter in 24hour format.","WARNING",JOptionPane.WARNING_MESSAGE);
             
         }
     
     }
       private void ValidatePrice()
     {
         
         try{
         double price=Double.parseDouble(jtfPrice.getText());
          jtfPrice.setBackground(new Color(240, 255, 240));
         }catch(NumberFormatException ex)
         {
             jtfPrice.setBackground(new Color(255, 230, 230));
             JOptionPane.showMessageDialog(null, "Alphabetic input is invalid.\nPlease reenter.","WARNING",JOptionPane.WARNING_MESSAGE);
             //jtfPrice2.setText("");
         }
     
     }
   public boolean validate(String time){  
  
    matcher = pattern.matcher(time);  
    return matcher.matches();  
  
   }  
      
      public static void main(String[]args)
    {
        new CreateNewTrip('U');
    }
      
}