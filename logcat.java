package adbtool;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class logcat implements ActionListener
{
JFrame f1;
JPanel p1;
JButton b1,b2,b3;
JTextField t1;
JLabel l1,l2,l3,head;
Process pr,pr1;
int ti;
long start,end;
String tim2;

logcat()
{
f1 = new JFrame();
p1 = new JPanel();
b1 = new JButton("Start");
b2 = new JButton("Stop");
b3 = new JButton("Show");
l1 = new JLabel("Duration (sec) :");
l2 = new JLabel("Leave blank for manual mode.");
l3 = new JLabel("");
t1 = new JTextField();
head = new JLabel("LOGCAT TOOL");
head.setFont(new Font("Times New Roman",Font.BOLD,40));
l1.setFont(new Font("Times New Roman",Font.BOLD,16));
l2.setFont(new Font("Times New Roman",Font.BOLD,16));
l3.setFont(new Font("Times New Roman",Font.BOLD,16));
b1.setFont(new Font("Times New Roman",Font.BOLD,16));
b2.setFont(new Font("Times New Roman",Font.BOLD,16));
b3.setFont(new Font("Times New Roman",Font.BOLD,16));
f1.add(p1);
f1.setVisible(true);
f1.setSize(400,420);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
p1.setLayout(null);
f1.setTitle("Logcat Tool");
}

public void setposn()
{
head.setBounds(40,20,360,50);
l1.setBounds(35,105,150,40);
l2.setBounds(35,150,450,40);
l3.setBounds(35,195,450,40);
t1.setBounds(182,115,150,25);
b1.setBounds(75,255,100,30);
b2.setBounds(225,255,100,30);
b3.setBounds(150,315,100,30);
}

public void addcomp()
{
p1.add(head);
p1.add(l1);
p1.add(l2);
p1.add(l3);
p1.add(t1);
p1.add(b1);
p1.add(b2);
p1.add(b3);
}

public void actionPerformed(ActionEvent e)
{
if(e.getSource() == b1)
{
String time = t1.getText();
if(time.isEmpty())
System.out.println("ultd");
l3.setOpaque(true);
l3.setText("Taking logs, please wait...");
logunlimited();
if(!time.isEmpty())
{
try{
ti = Integer.parseInt(time);
System.out.println(ti);
loglimited(ti);
}
catch(Exception f)
{
JOptionPane.showMessageDialog(null, "Please enter integer only.");
}
}
}
if(e.getSource() == b2)
{
killadb(start);
}

if(e.getSource() == b3)
{
try{
String str1 = "log_"+tim2;
File file1 =new File(str1);
Desktop.getDesktop().open(file1);
}
catch(Exception E)
{
System.out.println("Failed");
}
}

}

public void logunlimited()
{
start = System.currentTimeMillis();
logcat2 ob = new logcat2(start);
ob.start();
}

public void loglimited(int x)
{
start = System.currentTimeMillis();
end = start + x*1000;
System.out.println("Running loglimited");
logcat2 ob = new logcat2(start);
ob.start();
while(System.currentTimeMillis() < end)
{
}
killadb(start);
}

public void killadb(long time)
{
try{
String cmd1="adb kill-server";
Runtime rn = Runtime.getRuntime();
	      pr1 = rn.exec(cmd1);
pr1.waitFor();
l3.setText("Done, saved log_"+time);
tim2=Long.toString(time);
}
catch(Exception ef)
{
System.out.println("Failed");
}
}

public void logct()
{
setposn();
addcomp();
}

}
