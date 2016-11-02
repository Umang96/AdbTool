package adbtool;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class cpumain implements ActionListener
{
JPanel p1;
JFrame f1;
JLabel l1,l2,l3,l4,l5,head;
JLabel l6,l7,l8,l9,l10;
JButton b1,b2;

cpumain()
{

f1 = new JFrame();
p1 = new JPanel();
head = new JLabel("CPU INFORMATION");
head.setFont(new Font("Times New Roman",Font.BOLD,35));
l1 = new JLabel("Architecture: ");
l2 = new JLabel("Chipset:      ");
l3 = new JLabel("No. of cores: ");
l4 = new JLabel("Max cpu freq: ");
l5 = new JLabel("Min cpu freq: ");
l6 = new JLabel();
l7 = new JLabel();
l8 = new JLabel();
l9 = new JLabel();
l10 = new JLabel();
b1 = new JButton("Pull/Refresh");
b2 = new JButton("Freq. Stats");
l1.setFont(new Font("Times New Roman",Font.BOLD,16));
l2.setFont(new Font("Times New Roman",Font.BOLD,16));
l3.setFont(new Font("Times New Roman",Font.BOLD,16));
l4.setFont(new Font("Times New Roman",Font.BOLD,16));
l5.setFont(new Font("Times New Roman",Font.BOLD,16));
l6.setFont(new Font("Times New Roman",Font.BOLD,16));
l7.setFont(new Font("Times New Roman",Font.BOLD,16));
l8.setFont(new Font("Times New Roman",Font.BOLD,16));
l9.setFont(new Font("Times New Roman",Font.BOLD,16));
l10.setFont(new Font("Times New Roman",Font.BOLD,16));
b1.setFont(new Font("Times New Roman",Font.BOLD,16));
b2.setFont(new Font("Times New Roman",Font.BOLD,16));
f1.add(p1);
f1.setVisible(true);
f1.setSize(540,580);
p1.setLayout(null);
b1.addActionListener(this);
b2.addActionListener(this);
f1.setTitle("Cpu Information");

}

public void setposn()
{

head.setBounds(80,30,380,75);
l1.setBounds(40,150,150,40);
l2.setBounds(40,200,150,40);
l3.setBounds(40,250,150,40);
l4.setBounds(40,300,150,40);
l5.setBounds(40,350,150,40);
l6.setBounds(200,150,150,40);
l7.setBounds(200,200,150,40);
l8.setBounds(200,250,150,40);
l9.setBounds(200,300,150,40);
l10.setBounds(200,350,150,40);
b1.setBounds(100,440,145,40);
b2.setBounds(295,440,145,40);
}

public void addcomp()
{

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
p1.add(head);
p1.add(b1);
p1.add(b2);

}

public void actionPerformed(ActionEvent e)
{
if(e.getSource() == b2)
{
cpusec ob1 = new cpusec();
ob1.callfreq();
}
if(e.getSource() == b1)
{
	String[] line  = new String[5];
	try
	{
		String[] cmd = new String[5];
		cmd[0] = "adb shell grep ro.product.cpu.abi= /system/build.prop";
		cmd[1] = "adb shell grep ro.product.board /system/build.prop";
		cmd[2] = "adb shell grep -c processor /proc/cpuinfo";
		cmd[3] = "adb shell cat sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
		cmd[4] = "adb shell cat sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";
		for(int i=0 ; i<5 ; i++)
		{
		StringBuilder out = new StringBuilder();
		Runtime run = Runtime.getRuntime();
	  	Process pr = run.exec(cmd[i]);
	  	pr.waitFor();
	  	BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		line[i] = buf.readLine();
		System.out.println(cmd[i]);		
		System.out.println(line[i]);
		if(i<2)
		{
		String[] parts = line[i].split("=");
		line[i] = parts[1];
		}
		if(i==2)
		{
		line[i] = line[i] + " Cores"; 		
		}
		if(i>2)
		{
		double temp;
		temp = Double.parseDouble(line[i]);
		temp = temp / 1000;
		line[i] = Double.toString(temp);
		line[i] = line[i] + " Mhz";
		}
		}
}
catch(Exception f)
{
System.out.println("Internal Error");
JOptionPane.showMessageDialog(null, "Internal error occured, is your phone connected in adb mode ?");
}
l6.setText(line[0]);
l7.setText(line[1]);
l8.setText(line[2]);
l9.setText(line[3]);
l10.setText(line[4]);
}
}

public void callcpumain()
{
setposn();
addcomp();
}

}
