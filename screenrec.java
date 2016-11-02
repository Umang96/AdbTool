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
JLabel l1,l2,l3,head;
JButton b1,b2;

screenrec()
{
head = new JLabel("Screen Recorder");
l1 = new JLabel("Duration (sec) :");
l2 = new JLabel("Enter duration and press button.");
l3 = new JLabel("");
b1 = new JButton("Screenrecord");
b2 = new JButton("Screenshot");
t1 = new JTextField();
f1 = new JFrame();
p1 = new JPanel();
f1.setSize(450,430);
f1.add(p1);
f1.setVisible(true);
p1.setLayout(null);
head.setFont(new Font("Times New Roman",Font.BOLD,35));
l1.setFont(new Font("Times New Roman",Font.BOLD,16));
l2.setFont(new Font("Times New Roman",Font.BOLD,16));
l3.setFont(new Font("Times New Roman",Font.BOLD,15));
b1.setFont(new Font("Times New Roman",Font.BOLD,14));
b2.setFont(new Font("Times New Roman",Font.BOLD,14));
l3.setOpaque(true);
b1.addActionListener(this);
b2.addActionListener(this);
f1.setTitle("Screen Recorder");
}

public void setposn()
{
head.setBounds(50,10,360,80);
l1.setBounds(50,110,150,40);
l2.setBounds(50,160,300,40);
t1.setBounds(200,115,150,30);
b1.setBounds(46,220,156,40);
b2.setBounds(226,220,156,40);
l3.setBounds(50,280,370,40);
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
}

public void callscreenrec()
{
setposn();
addcomp();
}

public void actionPerformed(ActionEvent e)
{
if(e.getSource() == b1)
screenrecord();
if(e.getSource() == b2)
screenshot();
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
Runtime run = Runtime.getRuntime();
Process pr;
		pr = run.exec(cmd1);
	  	pr.waitFor();
		pr = run.exec(cmd2);
	  	pr.waitFor();
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
screenrec2 ob = new screenrec2(start);
ob.start();
String cmd2="adb pull /sdcard/screenrec_"+st+".mp4";
Runtime run = Runtime.getRuntime();
Process pr;
		while(System.currentTimeMillis() < end)
		{}
		killadb();
		pr = run.exec("adb devices");
	  	pr.waitFor();
		pr = run.exec(cmd2);
	  	pr.waitFor();
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
try{
String cmd1="adb kill-server";
Runtime rn = Runtime.getRuntime();
Process pr1;
	      pr1 = rn.exec(cmd1);
pr1.waitFor();
}
catch(Exception ef)
{
System.out.println("Failed to kill adb");
}
}

}
