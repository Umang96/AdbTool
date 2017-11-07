package adbtool;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class backtool implements ActionListener
{
JFrame f1;
JPanel p1;
JLabel l1,head,l2;
JTextField t1;
JRadioButton r1,r2,r3,r4,r5;
ButtonGroup bg;
JButton b1;

backtool()
{

head = new JLabel("Backup Tool");
r1 = new JRadioButton(".apk  (application packages)");
r2 = new JRadioButton(".mp3  (all mp3 music files)");
r3 = new JRadioButton(".mp4  (all mp4 video files)");
r4 = new JRadioButton(".pdf  (all pdf documents)");
r5 = new JRadioButton(".jpg  (all jpg pictures)");
bg = new ButtonGroup();
bg.add(r1);
bg.add(r2);
bg.add(r3);
bg.add(r4);
bg.add(r5);
l1 = new JLabel("Enter custom extention: ");
l2 = new JLabel("Make your choice and start backup.");
t1 = new JTextField();
b1 = new JButton("Start Backup");
f1 = new JFrame();
p1 = new JPanel();
f1.setSize(400,550);
f1.setVisible(true);
f1.add(p1);
p1.setLayout(null);
head.setFont(new Font("Times New Roman",Font.BOLD,40));
r1.setFont(new Font("Times New Roman",Font.BOLD,16));
r2.setFont(new Font("Times New Roman",Font.BOLD,16));
r3.setFont(new Font("Times New Roman",Font.BOLD,16));
r4.setFont(new Font("Times New Roman",Font.BOLD,16));
r5.setFont(new Font("Times New Roman",Font.BOLD,16));
l2.setFont(new Font("Times New Roman",Font.BOLD,16));
b1.setFont(new Font("Times New Roman",Font.BOLD,16));
b1.addActionListener(this);
f1.setTitle("Backup Tool");
l2.setOpaque(true);
}

public void setposn()
{
head.setBounds(60,10,300,80);
r1.setBounds(40,110,360,40);
r2.setBounds(40,160,360,40);
r3.setBounds(40,210,360,40);
r4.setBounds(40,260,360,40);
r5.setBounds(40,310,360,40);
l2.setBounds(40,370,390,40);
b1.setBounds(100,430,200,40);
}

public void addcomp()
{
p1.add(head);
p1.add(r1);
p1.add(r2);
p1.add(r3);
p1.add(r4);
p1.add(r5);
p1.add(b1);
p1.add(l2);
}

public void actionPerformed(ActionEvent e)
{

if(e.getSource() == b1)
{
runscript();
}

}

public void runscript()
{
String stc = checkradio();
if(stc=="failed")
JOptionPane.showMessageDialog(null, "Please select one option first.");
else
{
ShellHelper sh1 = new ShellHelper(0);
sh1.executor("cd adbtool");
sh1.executor("chmod +x script.sh");
sh1.executor("bash script.sh "+stc);
}
}

public String checkradio()
{
if(r1.isSelected())
return "apk";
if(r2.isSelected())
return "mp3";
if(r3.isSelected())
return "mp4";
if(r4.isSelected())
return "pdf";
if(r5.isSelected())
return "jpg";
return "failed";
}

public void callbackup()
{
setposn();
addcomp();
}
} 
