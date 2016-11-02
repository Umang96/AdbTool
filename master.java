package adbtool;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.io.*;

class master extends JFrame implements ActionListener 
{

JFrame f1;
JPanel p1;
JLabel head,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14;
JTextField t1;
JButton b1,b2,b3,b4,b5,b6,b7,b8;
String chk;

public master()
{
super();
f1 = new JFrame();
p1 = new JPanel(){ 
    public void paintComponent(Graphics g){
	Color grey = new Color(153, 161, 155);
	g.setColor(grey);
	g.drawLine(332,120,332,580);
    }
};
head = new JLabel("Android Debugging Tools");
l1 = new JLabel("Model      :");
l2 = new JLabel("Board      :");
l3 = new JLabel("Vendor    :");
l4 = new JLabel("Android   :");
l5 = new JLabel("Display    :");
l6 = new JLabel("Kernel     :");
l7 = new JLabel("Memory   :");
l8 = new JLabel();
l9 = new JLabel();
l10 = new JLabel();
l11 = new JLabel();
l12 = new JLabel();
l13 = new JLabel();
l14 = new JLabel();
t1 = new JTextField();
b1 = new JButton("Connect");
b2 = new JButton("Clear");
b3 = new JButton("CPU-INFO TOOL");
b4 = new JButton("BACKUP TOOL");
b5 = new JButton("LOGCAT TOOL");
b6 = new JButton("APPLICATION TOOL");
b7 = new JButton("SCREEN RECORDER");
b8 = new JButton("STORAGE TOOL");
f1.add(p1);
f1.setVisible(true);
f1.setSize(700,640);
p1.setLayout(null);
b2.addActionListener(this);
b1.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);
b7.addActionListener(this);
b8.addActionListener(this);
f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f1.setTitle("Android Debugging Tools");
head.setFont(new Font("Times New Roman",Font.BOLD,40));
l1.setFont(new Font("Times New Roman",Font.BOLD,16));
l2.setFont(new Font("Times New Roman",Font.BOLD,16));
l3.setFont(new Font("Times New Roman",Font.BOLD,16));
l4.setFont(new Font("Times New Roman",Font.BOLD,16));
l5.setFont(new Font("Times New Roman",Font.BOLD,16));
l6.setFont(new Font("Times New Roman",Font.BOLD,16));
l7.setFont(new Font("Times New Roman",Font.BOLD,16));
b1.setFont(new Font("Times New Roman",Font.BOLD,14));
b2.setFont(new Font("Times New Roman",Font.BOLD,14));
b3.setFont(new Font("Times New Roman",Font.BOLD,14));
b4.setFont(new Font("Times New Roman",Font.BOLD,14));
b5.setFont(new Font("Times New Roman",Font.BOLD,14));
b6.setFont(new Font("Times New Roman",Font.BOLD,14));
b7.setFont(new Font("Times New Roman",Font.BOLD,14));
b8.setFont(new Font("Times New Roman",Font.BOLD,14));
l8.setOpaque(true);
l9.setOpaque(true);
l10.setOpaque(true);
l11.setOpaque(true);
l12.setOpaque(true);
l13.setOpaque(true);
l14.setOpaque(true);
}

public void setposn()
{

head.setBounds(54,10,600,100);
l1.setBounds(360,130,102,30);
l2.setBounds(360,182,102,30);
l3.setBounds(360,234,102,30);
l4.setBounds(360,286,102,30);
l5.setBounds(360,338,102,30);
l6.setBounds(360,390,102,30);
l7.setBounds(360,442,102,30);
l8.setBounds(480,130,102,30);
l9.setBounds(480,182,102,30);
l10.setBounds(480,234,102,30);
l11.setBounds(480,286,102,30);
l12.setBounds(480,338,102,30);
l13.setBounds(480,390,165,30);
l14.setBounds(480,442,102,30);
b1.setBounds(396,530,100,40);
b2.setBounds(536,530,100,40);
b3.setBounds(76,140,190,40);
b4.setBounds(76,212,190,40);
b5.setBounds(76,284,190,40);
b6.setBounds(76,356,190,40);
b7.setBounds(76,428,190,40);
b8.setBounds(76,500,190,40);
}

public void addcomp()
{

p1.add(head);
p1.add(l1);
p1.add(l2);
p1.add(l3);
p1.add(l4);
p1.add(l5);
p1.add(l6);
p1.add(l7);
p1.add(l8);
p1.add(l9);
p1.add(l10);
p1.add(l11);
p1.add(l12);
p1.add(l13);
p1.add(l14);
p1.add(b1);
p1.add(b2);
p1.add(b3);
p1.add(b4);
p1.add(b5);
p1.add(b6);
p1.add(b7);
p1.add(b8);

}

public static void main(String a[])
{

master ob = new master();
ob.setposn();
ob.addcomp();

}

public void actionPerformed(ActionEvent e)
{

if(e.getSource()==b1)
{
getbasicinfo();
}

if(e.getSource()==b2)
{

l8.setText(null);
l9.setText(null);
l10.setText(null);
l11.setText(null);
l12.setText(null);
l13.setText(null);
l14.setText(null);
}

if(e.getSource()==b3)
{
cpumain ob2 = new cpumain();
ob2.callcpumain();
}

if(e.getSource()==b4)
{
backtool ob5 = new backtool();
ob5.callbackup();
}

if(e.getSource()==b5)
{
logcat ob = new logcat();
ob.logct();
}

if(e.getSource()==b6)
{
apptool ob3 = new apptool();
ob3.callapptool();
}

if(e.getSource()==b7)
{
screenrec ob4 = new screenrec();
ob4.callscreenrec();
}

if(e.getSource()==b8)
{
storage ob1 = new storage();
ob1.callstorage();
}

}

public void getbasicinfo()
{
String[] line = new String[7];

		try{
		Runtime rn = Runtime.getRuntime();
		Process p = rn.exec("adb devices");
	  	p.waitFor();
		String[] cmd = new String[7];
		cmd[0] = "adb shell grep ro.product.model /system/build.prop";
		cmd[1] = "adb shell grep ro.product.board /system/build.prop";
		cmd[2] = "adb shell grep ro.product.brand /system/build.prop";
		cmd[3] = "adb shell grep ro.build.version.release /system/build.prop";
		cmd[4] = "adb shell wm size";
		cmd[5] = "adb shell cat proc/version";
		cmd[6] = "adb shell grep MemTotal /proc/meminfo";
		for(int i=0 ; i<7 ; i++)
		{
		StringBuilder out = new StringBuilder();
		Runtime run = Runtime.getRuntime();
	  	Process pr = run.exec(cmd[i]);
	  	pr.waitFor();
	  	BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		line[i] = buf.readLine();
		System.out.println(cmd[i]);		
		System.out.println(line[i]);
		if(i<4)
		{
		String[] parts = line[i].split("=");
		line[i] = parts[1];
		}
		if(i==4)
		{
		String[] parts1 = line[i].split(": ");
		line[i] = parts1[1];
		}
		if(i==5)
		{
 		line[i] = line[i].substring(0,20);
		}
		if(i==6)
		{
		int ram = 0;
		Scanner in = new Scanner(line[i]).useDelimiter("[^0-9]+");
		ram = in.nextInt();
		System.out.println(line[i]);
		ram = ram / 1024;
		line[i] = Integer.toString(ram);
		line[i] = line[i]+" Mb";
		System.out.println(ram);
		}
		}
	}
		catch(Exception ae)
		{
		System.out.println("Internal Error");
		JOptionPane.showMessageDialog(null, "Internal error occured, is your phone connected in adb mode ?");
		}

l8.setText(line[0]);
l9.setText(line[1]);
l10.setText(line[2]);
l11.setText(line[3]);
l12.setText(line[4]);
l13.setText(line[5]);
l14.setText(line[6]);
}

}
