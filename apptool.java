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
JButton b1,b2,b3,b4;
ShellHelper sh;

apptool()
{
sh = new ShellHelper(0);
head = new JLabel("APPLICATION TOOL");
l1 = new JLabel("Place your apk in current working directory.");
l2 = new JLabel("Apk File Name : ");
l3 = new JLabel("Package Name : ");
l4 = new JLabel("Activity Name : ");
b1 = new JButton("Install");
b2 = new JButton("Launch");
b3 = new JButton("Stop");
b4 = new JButton("Uninstall");
t1 = new JTextField();
t2 = new JTextField();
t3 = new JTextField();
head.setFont(new Font("Georgia",Font.BOLD,35));
l1.setFont(new Font("Georgia",Font.BOLD,16));
l2.setFont(new Font("Georgia",Font.BOLD,16));
l3.setFont(new Font("Georgia",Font.BOLD,16));
l4.setFont(new Font("Georgia",Font.BOLD,16));
b1.setFont(new Font("Georgia",Font.BOLD,16));
b2.setFont(new Font("Georgia",Font.BOLD,16));
b3.setFont(new Font("Georgia",Font.BOLD,16));
b4.setFont(new Font("Georgia",Font.BOLD,16));
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
b4.addActionListener(this);
f1.setTitle("Application Tool");
b1.setForeground(Color.WHITE);
b2.setForeground(Color.WHITE);
b3.setForeground(Color.WHITE);
b4.setForeground(Color.WHITE);
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
b1.setBounds(100,310,120,40);
b2.setBounds(280,310,120,40);
b3.setBounds(280,375,120,40);
b4.setBounds(100,375,120,40);
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
p1.add(b4);
}

public void actionPerformed(ActionEvent e)
{
if(e.getSource() == b1)
{
installapk();
}
else if(e.getSource() == b2)
{
launchact();
}
else if(e.getSource() == b3)
{
stopact();
}
else if(e.getSource() == b4)
{
uninstallapp();
}
}

public void installapk()
{
String pack=t1.getText();
String cmd="adb install "+pack;
sh.executor("cd adbtool");
String check = sh.executor(cmd);
if(check.contains("Success"))
JOptionPane.showMessageDialog(null, "Done !");
else
JOptionPane.showMessageDialog(null, "Failed, "+check);
}

public void launchact()
{
String pack=t2.getText();
String act=t3.getText();
String cmd="adb shell am start -n "+pack+"/"+pack+"."+act;
sh.executor(cmd);
}

public void stopact()
{
String pack=t2.getText();
String cmd="adb shell am force-stop "+pack;
sh.executor(cmd);
}

public void uninstallapp()
{
String pack=t2.getText();
String cmd="adb shell pm uninstall "+pack;
String chk = sh.executor(cmd);
if(chk.equals("Success"))
JOptionPane.showMessageDialog(null, "Done !");
else
JOptionPane.showMessageDialog(null, "Failed, "+chk);
}

public void callapptool()
{
setposn();
addcomp();
}

}
