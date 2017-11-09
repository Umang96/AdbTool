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
JLabel[] cln;
JTextField t1;
JButton b1,b2,b3,b4,b5,b6,b7,b8;
String chk;
boolean connected;

public master()
{
super();
f1 = new JFrame();
p1 = new JPanel(){ 
    public void paintComponent(Graphics g){
	Color grey = new Color(150, 150, 150);
	g.setColor(grey);
	g.drawLine(360,130,360,570);
	g.drawLine(40,100,680,100);
    }
};
cln = new JLabel[7];
head = new JLabel("ANDROID DEBUGGING TOOLS");
l1 = new JLabel("Model");
l2 = new JLabel("Board");
l3 = new JLabel("Vendor");
l4 = new JLabel("Android");
l5 = new JLabel("Display");
l6 = new JLabel("Kernel");
l7 = new JLabel("Memory");
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
f1.setSize(720,630);
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
head.setFont(new Font("Georgia",Font.BOLD,34));
l1.setFont(new Font("Georgia",Font.BOLD,16));
l2.setFont(new Font("Georgia",Font.BOLD,16));
l3.setFont(new Font("Georgia",Font.BOLD,16));
l4.setFont(new Font("Georgia",Font.BOLD,16));
l5.setFont(new Font("Georgia",Font.BOLD,16));
l6.setFont(new Font("Georgia",Font.BOLD,16));
l7.setFont(new Font("Georgia",Font.BOLD,16));
b1.setFont(new Font("Georgia",Font.BOLD,13));
b2.setFont(new Font("Georgia",Font.BOLD,13));
b3.setFont(new Font("Georgia",Font.BOLD,13));
b4.setFont(new Font("Georgia",Font.BOLD,13));
b5.setFont(new Font("Georgia",Font.BOLD,13));
b6.setFont(new Font("Georgia",Font.BOLD,13));
b7.setFont(new Font("Georgia",Font.BOLD,13));
b8.setFont(new Font("Georgia",Font.BOLD,13));

b1.setForeground(Color.WHITE);
b2.setForeground(Color.WHITE);
b3.setForeground(Color.WHITE);
b4.setForeground(Color.WHITE);
b5.setForeground(Color.WHITE);
b6.setForeground(Color.WHITE);
b7.setForeground(Color.WHITE);
b8.setForeground(Color.WHITE);

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

head.setBounds(75,10,600,100);
l1.setBounds(390,148,90,30);
l2.setBounds(390,200,90,30);
l3.setBounds(390,252,90,30);
l4.setBounds(390,304,90,30);
l5.setBounds(390,356,90,30);
l6.setBounds(390,408,90,30);
l7.setBounds(390,460,90,30);
l8.setBounds(540,148,160,30);
l9.setBounds(540,200,160,30);
l10.setBounds(540,252,160,30);
l11.setBounds(540,304,160,30);
l12.setBounds(540,356,160,30);
l13.setBounds(540,408,165,30);
l14.setBounds(540,460,160,30);
b1.setBounds(410,520,100,40);
b2.setBounds(570,520,100,40);
b3.setBounds(80,140,200,40);
b4.setBounds(80,212,200,40);
b5.setBounds(80,284,200,40);
b6.setBounds(80,356,200,40);
b7.setBounds(80,428,200,40);
b8.setBounds(80,500,200,40);
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

for(int i = 0 ; i<7 ; i++)
{
cln[i] = new JLabel(":");
cln[i].setFont(new Font("Georgia",Font.BOLD,16));
cln[i].setBounds(495,148+(i*52),20,30);
p1.add(cln[i]);
}

}


public static void main(String a[])
{

try{
	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	
	UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    defaults.put("Button.background",  Color.BLACK);
}
catch (Exception e)
{
    e.printStackTrace();
}

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
connected = false;
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
		connected = false;
		String[] line = new String[7];
		ShellHelper sh = new ShellHelper(0);

		sh.executor("adb devices");

		String st = sh.executor("adb devices");

		try
		{
			st = st.substring(26,st.length());
			if(st.contains("device"))
				connected = true;
			else if(st.contains("no permissions"))
				JOptionPane.showMessageDialog(null, "Permission error, please allow this computer to connect to your device.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cannot find device, is your phone connected in adb mode ?");
		}

		System.out.print(st);
		if(connected)
		{
		//Prepare array of commands
		String[] cmd = new String[7];
		cmd[0] = "adb shell getprop ro.product.model";
		cmd[1] = "adb shell getprop ro.product.board";
		cmd[2] = "adb shell getprop ro.product.brand";
		cmd[3] = "adb shell getprop ro.build.version.release";
		cmd[4] = "adb shell wm size";
		cmd[5] = "adb shell cat proc/version";
		cmd[6] = "adb shell grep MemTotal /proc/meminfo";
		
		for(int i=0 ; i<7 ; i++)
		{
		//Store output of each command[i] in line[i]
		line[i] = sh.executor(cmd[i]);
		System.out.println(cmd[i]);	
		System.out.println(line[i]);
		//Do some string operations if required
		if(i==4)
		{
		String[] parts1 = line[i].split(": ");
		line[i] = parts1[1];
		}
		else if(i==5)
		{
 		line[i] = line[i].substring(0,20);
		}
		else if(i==6)
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

		//Update all labels with line[i]
		l8.setText(line[0]);
		l9.setText(line[1]);
		l10.setText(line[2]);
		l11.setText(line[3]);
		l12.setText(line[4]);
		l13.setText(line[5]);
		l14.setText(line[6]);

}
}

}
