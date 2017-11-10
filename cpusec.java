package adbtool;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class cpusec implements ActionListener
{
JFrame f1;
JPanel p1;
JButton b1;
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,head;
int[] freq = new int[10];
int[] tis = new int[10];
double[] per = new double[10];
ShellHelper sh;

cpusec()
{
sh = new ShellHelper(0);
l1 = new JLabel();
l2 = new JLabel();
l3 = new JLabel();
l4 = new JLabel();
l5 = new JLabel();
l6 = new JLabel();
l7 = new JLabel();
l8 = new JLabel();
l9 = new JLabel();
l10 = new JLabel();
b1 = new JButton("Pull/Refresh");
head = new JLabel("Frequency                Time                Percent");
f1 = new JFrame();
p1 = new JPanel();
f1.setSize(500,680);
f1.setVisible(true);
f1.add(p1);
p1.setLayout(null);
b1.addActionListener(this);
head.setFont(new Font("Georgia",Font.BOLD,16));
l1.setFont(new Font("Georgia",Font.BOLD,16));
l2.setFont(new Font("Georgia",Font.BOLD,16));
l3.setFont(new Font("Georgia",Font.BOLD,16));
l4.setFont(new Font("Georgia",Font.BOLD,16));
l5.setFont(new Font("Georgia",Font.BOLD,16));
l6.setFont(new Font("Georgia",Font.BOLD,16));
l7.setFont(new Font("Georgia",Font.BOLD,16));
l8.setFont(new Font("Georgia",Font.BOLD,16));
l9.setFont(new Font("Georgia",Font.BOLD,16));
l10.setFont(new Font("Georgia",Font.BOLD,16));
f1.setTitle("Cpu Frequency Stats");
b1.setForeground(Color.WHITE);
}

public void setposn()
{
head.setBounds(30,5,410,40);
l1.setBounds(30,55,410,40);
l2.setBounds(30,105,410,40);
l3.setBounds(30,155,410,40);
l4.setBounds(30,205,410,40);
l5.setBounds(30,255,410,40);
l6.setBounds(30,305,410,40);
l7.setBounds(30,355,410,40);
l8.setBounds(30,405,410,40);
l9.setBounds(30,455,410,40);
l10.setBounds(30,505,410,40);
b1.setBounds(175,565,150,40);
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
p1.add(b1);
p1.add(head);
}

public void actionPerformed(ActionEvent e)
{
int i=1;
String line;
String cmd;
Scanner in,inn;
if(e.getSource() == b1)
{
	try{
	cmd = "adb shell cat sys/devices/system/cpu/cpu0/cpufreq/scaling_available_frequencies";
	line = sh.executor(cmd);
		in = new Scanner(line).useDelimiter("[^0-9]+");
		for(i=0 ; i<10 ; i++)
		{
		freq[i] = in.nextInt();
		System.out.println(freq[i]);
		}
	}	
	catch(Exception ex)
	{
	System.out.println("Failed");
	}
	try{
	for(i=0 ; i<10 ; i++)
	{
	cmd = "adb shell cat sys/devices/system/cpu/cpu0/cpufreq/stats/time_in_state | grep "+freq[i];
	System.out.println(cmd);
	line = sh.executor(cmd);
	inn = new Scanner(line).useDelimiter("[^0-9]+");
	tis[i] = inn.nextInt();
	tis[i] = inn.nextInt();
	if(freq[i]>0)
	System.out.println(tis[i]);
	}
	}	
	catch(Exception ex)
	{
	System.out.println("Failed");
	}
	printfreq();
}
}

public void printfreq()
{
double[] freq1 = new double[10];
double[] tis1 = new double[10];
String[] labl = new String[10];
double sum=0;
for(int i=0 ; i<10 ; i++)
{
freq1[i] = freq[i]/1000;
tis1[i] = tis[i]/1000;

for(int n=0; n<10; n++)
{
if(freq[n]>0)
sum = sum + tis[n];
}
per[i] = tis[i]/sum;
per[i]=per[i]*100;
if(freq1[i]<1000.0)
{
if(tis1[i]<100)
labl[i] = " "+Double.toString(freq1[i])+" Mhz"+"           "+Double.toString(tis1[i])+" sec"+"            "+String.format("%.2f", per[i])+" %";
if(tis1[i]>=100)
labl[i] = " "+Double.toString(freq1[i])+" Mhz"+"           "+Double.toString(tis1[i])+" sec"+"           "+String.format("%.2f", per[i])+" %";
if(tis1[i]>=1000)
labl[i] = " "+Double.toString(freq1[i])+" Mhz"+"           "+Double.toString(tis1[i])+" sec"+"          "+String.format("%.2f", per[i])+" %";
}
if(freq1[i]>=1000.0)
{
if(tis1[i]<100)
labl[i] = Double.toString(freq1[i])+" Mhz"+"           "+Double.toString(tis1[i])+" sec"+"            "+String.format("%.2f", per[i])+" %";
if(tis1[i]>=100)
labl[i] = Double.toString(freq1[i])+" Mhz"+"           "+Double.toString(tis1[i])+" sec"+"           "+String.format("%.2f", per[i])+" %";
if(tis1[i]>=1000)
labl[i] = Double.toString(freq1[i])+" Mhz"+"           "+Double.toString(tis1[i])+" sec"+"          "+String.format("%.2f", per[i])+" %";
}
System.out.println(labl[i]);
}
l1.setText(labl[0]);
l2.setText(labl[1]);
l3.setText(labl[2]);
l4.setText(labl[3]);
l5.setText(labl[4]);
l6.setText(labl[5]);
if(freq[6]>0)
l7.setText(labl[6]);
if(freq[7]>0)
l8.setText(labl[7]);
if(freq[8]>0)
l9.setText(labl[8]);
if(freq[9]>0)
l10.setText(labl[9]);
}

public void callfreq()
{
setposn();
addcomp();
}

}
