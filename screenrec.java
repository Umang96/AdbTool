package adbtool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class screenrec implements ActionListener
{
    
JFrame f1;
JPanel p1;
JTextField t1;
JLabel l1,l2,l3,head,l5,l6;
JButton b1,b2;
JRadioButton bit1,bit2,bit3,bit4,res1,res2,res3,res4;
ButtonGroup bitrate,resolution;
ShellHelper sh;
String bitr,reso;

screenrec()
{

sh = new ShellHelper(0);
bitrate=new ButtonGroup();
resolution=new ButtonGroup();   
l5=new JLabel("Bit Rate");
l6=new JLabel("Resolution");
head = new JLabel("Screen Recorder");
l1 = new JLabel("Duration (sec) :");
l2 = new JLabel("Enter duration and press button.");
l3 = new JLabel("");
b1 = new JButton("Screenrecord");
b2 = new JButton("Screenshot");
bit1=new JRadioButton("50000000");
bit2=new JRadioButton("100000000",true);
bit3=new JRadioButton("150000000");
bit4=new JRadioButton("200000000");
res1=new JRadioButton("1280x720",true);
res2=new JRadioButton("1920x1080");
res3=new JRadioButton("960x640");
res4=new JRadioButton("640x480");

t1 = new JTextField();
f1 = new JFrame();
p1 = new JPanel();
f1.setSize(450,450);
f1.add(p1);
f1.setVisible(true);
p1.setLayout(null);
head.setFont(new Font("Georgia",Font.BOLD,35));
l1.setFont(new Font("Georgia",Font.BOLD,13));
l2.setFont(new Font("Georgia",Font.BOLD,13));
l3.setFont(new Font("Georgia",Font.BOLD,13));
b1.setFont(new Font("Georgia",Font.BOLD,13));
b2.setFont(new Font("Georgia",Font.BOLD,13));
l3.setOpaque(true);
b1.addActionListener(this);
b2.addActionListener(this);

bit1.setFont(new Font("Georgia",Font.BOLD,13));
bit2.setFont(new Font("Georgia",Font.BOLD,13));
bit3.setFont(new Font("Georgia",Font.BOLD,13));
bit4.setFont(new Font("Georgia",Font.BOLD,13));
res1.setFont(new Font("Georgia",Font.BOLD,13));
res2.setFont(new Font("Georgia",Font.BOLD,13));
res3.setFont(new Font("Georgia",Font.BOLD,13));
res4.setFont(new Font("Georgia",Font.BOLD,13));
l5.setFont(new Font("Georgia",Font.BOLD,13));
l6.setFont(new Font("Georgia",Font.BOLD,13));

bit1.addActionListener(this);
bit2.addActionListener(this);
bit3.addActionListener(this);
bit4.addActionListener(this);
res1.addActionListener(this);
res2.addActionListener(this);
res3.addActionListener(this);
res4.addActionListener(this);

b1.setForeground(Color.WHITE);
b2.setForeground(Color.WHITE);

f1.setTitle("Screen Recorder");
f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


}

public void setposn()
{
head.setBounds(80,5,360,80);
l5.setBounds(90, 70, 80, 30);
bit1.setBounds(80, 100, 140, 30);
bit2.setBounds(80, 125, 140, 30);
bit3.setBounds(80, 150, 140, 30);
bit4.setBounds(80, 175, 140, 30);
l6.setBounds(250, 70, 80, 30);
res1.setBounds(240, 100, 120, 30);
res2.setBounds(240, 125, 120, 30);
res3.setBounds(240, 150, 120, 30);
res4.setBounds(240, 175, 120, 30);
l1.setBounds(70,260,140,40);
l2.setBounds(70,220,300,40);
t1.setBounds(220,265,120,30);
b1.setBounds(46,345,156,40);
b2.setBounds(226,345,156,40);
l3.setBounds(50,310,370,30);
}

public void addcomp()
{
bitrate.add(bit1);
bitrate.add(bit2);
bitrate.add(bit3);
bitrate.add(bit4);
resolution.add(res1);
resolution.add(res2);
resolution.add(res3);
resolution.add(res4);


    
p1.add(head);
p1.add(l5);
p1.add(l6);
p1.add(bit1);
p1.add(bit2);
p1.add(bit3);
p1.add(bit4);
p1.add(res1);
p1.add(res2);
p1.add(res3);
p1.add(res4);

p1.add(l1);
p1.add(l2);
p1.add(l3);
p1.add(t1);
p1.add(b1);
p1.add(b2);
}

public void callscreenrec()
{
setposn();
addcomp();
}

public void actionPerformed(ActionEvent e)
{
    reso="1280X720";
        bitr="100000000";
    if(res1.isSelected())
    {
        reso=res1.getText();
    }
    if(res2.isSelected())
    {
        reso=res2.getText();
    }
    if(res3.isSelected())
    {
        reso=res3.getText();
    }
    if(res4.isSelected())
    {
        reso=res4.getText();
       
    }
    
      if(bit1.isSelected())
      {
          bitr=bit1.getText();
           
      }
      if(bit2.isSelected())
      {
          bitr=bit2.getText();
           
      }
      if(bit3.isSelected())
      {
          bitr=bit3.getText();
           
      }
      if(bit4.isSelected())
      {
          bitr=bit4.getText();
           
      }
      if(e.getSource() == b1)
	{screenrecord();}
      if(e.getSource() == b2)
	{screenshot();}

}

public void screenshot()
{
int x = checkadb();
if(x>1)
{
try{
long start = System.currentTimeMillis();
String st = Long.toString(start);
System.out.println("Running screenshot function");
String cmd1="adb shell screencap -p /sdcard/screencap_"+st+".png";
String cmd2="adb pull /sdcard/screencap_"+st+".png";
sh.executor(cmd1);
sh.executor("cd adbtool");
sh.executor(cmd2);
l3.setText("Done, saved screencap_"+st);
}
catch(Exception e1)
{
System.out.println("Error");
JOptionPane.showMessageDialog(null, "Failed, is your phone connected in adb mode ?");
}
}
}

public void screenrecord()
{
try{
String x1 = t1.getText();
long x = Long.parseLong(x1);
long start = System.currentTimeMillis();
long end = start + x*1000;
String st = Long.toString(start);
System.out.println("Running screenrecord function");
screenrec2 ob = new screenrec2(start,reso,bitr);
ob.start();
String cmd2="adb pull /sdcard/screenrec_"+st+".mp4";
		while(System.currentTimeMillis() < end)
		{}
		killadb();
		sh.executor("adb devices");
		sh.executor("cd adbtool");
		sh.executor(cmd2);
l3.setText("Done, saved screenrec_"+st);
}
catch(Exception e1)
{
System.out.println("Error");
JOptionPane.showMessageDialog(null, "Failed, is your phone connected in adb mode ?\nMake sure you entered an integer in duration.");
}
}

public int checkadb()
{
int z=-1;
	try{
		String cmd = "adb devices";
		Runtime run = Runtime.getRuntime();
	  	Process pr = run.exec(cmd);
	  	pr.waitFor();
	  	BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String[] line = new String[3];
		line[0] = buf.readLine();
		line[1] = buf.readLine();
		if(line[1].isEmpty())
		{
		JOptionPane.showMessageDialog(null, "Failed, is your phone connected in adb mode ?");
		z=(-1);
		}
		else if(!line[1].isEmpty())
		z=(2);
	}
	catch(Exception e)
	{
	JOptionPane.showMessageDialog(null, "Failed, is your phone connected in adb mode ?");
	z=(-1);
	}
return z;
}

public void killadb()
{
String cmd1="adb kill-server";
sh.executor(cmd1);
}
public static void main(String args[])
{
    screenrec obj=new screenrec();
    obj.setposn();
    obj.addcomp();
    
}

}

