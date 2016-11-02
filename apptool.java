package adbtool;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class apptool implements ActionListener
{
JFrame f1;
JPanel p1;
JLabel head,l1,l2,l3,l4;
JTextField t1,t2,t3;
JButton b1,b2,b3;

apptool()
{

head = new JLabel("APPLICATION TOOL");
l1 = new JLabel("Place your apk in current working directory.");
l2 = new JLabel("Apk File Name : ");
l3 = new JLabel("Package Name : ");
l4 = new JLabel("Activity Name : ");
b1 = new JButton("Install");
b2 = new JButton("Launch");
b3 = new JButton("Stop");
t1 = new JTextField();
t2 = new JTextField();
t3 = new JTextField();
head.setFont(new Font("Times New Roman",Font.BOLD,35));
l1.setFont(new Font("Times New Roman",Font.BOLD,16));
l2.setFont(new Font("Times New Roman",Font.BOLD,16));
l3.setFont(new Font("Times New Roman",Font.BOLD,16));
l4.setFont(new Font("Times New Roman",Font.BOLD,16));
b1.setFont(new Font("Times New Roman",Font.BOLD,16));
b2.setFont(new Font("Times New Roman",Font.BOLD,16));
b3.setFont(new Font("Times New Roman",Font.BOLD,16));
f1 = new JFrame();
p1 = new JPanel();
f1.setSize(500,485);
p1.setSize(500,485);
f1.add(p1);
f1.setVisible(true);
p1.setLayout(null);
f1.setLayout(null);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
f1.setTitle("Application Tool");
}

public void setposn()
{
head.setBounds(50,10,390,90);
l1.setBounds(30,105,400,40);
l2.setBounds(30,150,150,40);
l3.setBounds(30,200,150,40);
l4.setBounds(30,250,150,40);
t1.setBounds(180,155,200,30);
t2.setBounds(180,205,200,30);
t3.setBounds(180,256,200,30);
b1.setBounds(130,310,100,40);
b2.setBounds(270,310,100,40);
b3.setBounds(200,375,100,40);
}

public void addcomp()
{
p1.add(head);
p1.add(l1);
p1.add(l2);
p1.add(l3);
p1.add(l4);
p1.add(t1);
p1.add(t2);
p1.add(t3);
p1.add(b1);
p1.add(b2);
p1.add(b3);
}

public void actionPerformed(ActionEvent e)
{
if(e.getSource() == b1)
{
installapk();
}
if(e.getSource() == b2)
{
launchact();
}
if(e.getSource() == b3)
{
stopact();
}
}

public void installapk()
{
try{
String pack=t1.getText();
String cmd="adb install "+pack;
Runtime run = Runtime.getRuntime();
Process pr = run.exec(cmd);
	  	pr.waitFor();
JOptionPane.showMessageDialog(null, "Done !");
  }
catch(Exception ex)
{
System.out.println("Error");
}
}

public void launchact()
{
try
{
String pack=t2.getText();
String act=t3.getText();
String cmd="adb shell am start -n "+pack+"/"+pack+"."+act;
Runtime run = Runtime.getRuntime();
Process pr = run.exec(cmd);
	  	pr.waitFor();
}
catch(Exception ez)
{
System.out.println("Error");
}
}

public void stopact()
{
try
{
String pack=t2.getText();
String cmd="adb shell am force-stop "+pack;
Runtime run = Runtime.getRuntime();
Process pr = run.exec(cmd);
	  	pr.waitFor();
}
catch(Exception ez)
{
System.out.println("Error");
}
}

public void callapptool()
{
setposn();
addcomp();
}

}
