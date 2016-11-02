package adbtool;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class storage implements ActionListener 
{
JFrame f1;
JPanel p1;
JButton b1;
JLabel head,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
String[] parts;
String main;

storage()
{
f1 = new JFrame();
p1 = new JPanel();
f1.add(p1);
f1.setSize(500,550);
f1.setVisible(true);
p1.setLayout(null);
head = new JLabel("STORAGE INFO TOOL");
l1 = new JLabel("/data");
l2 = new JLabel("/system");
l3 = new JLabel("/cache");
l4 = new JLabel("/ext.sd");
l5 = new JLabel("/dev");
l6 = new JLabel();
l7 = new JLabel();
l8 = new JLabel();
l9 = new JLabel();
l10 = new JLabel();
l11 = new JLabel();
l11.setOpaque(true);
b1 = new JButton("Fetch/Refresh");
b1.addActionListener(this);
head.setFont(new Font("Times New Roman",Font.BOLD,32));
l1.setFont(new Font("Times New Roman",Font.BOLD,17));
l2.setFont(new Font("Times New Roman",Font.BOLD,17));
l3.setFont(new Font("Times New Roman",Font.BOLD,17));
l4.setFont(new Font("Times New Roman",Font.BOLD,17));
l5.setFont(new Font("Times New Roman",Font.BOLD,17));
l6.setFont(new Font("Times New Roman",Font.BOLD,17));
l7.setFont(new Font("Times New Roman",Font.BOLD,17));
l8.setFont(new Font("Times New Roman",Font.BOLD,17));
l9.setFont(new Font("Times New Roman",Font.BOLD,17));
l10.setFont(new Font("Times New Roman",Font.BOLD,17));
l11.setFont(new Font("Times New Roman",Font.BOLD,17));
b1.setFont(new Font("Times New Roman",Font.BOLD,16));
f1.setTitle("Storage Info Tool");
}

public void setposn()
{
head.setBounds(62,10,400,60);
l1.setBounds(30,120,90,40);
l2.setBounds(30,180,90,40);
l3.setBounds(30,240,90,40);
l4.setBounds(30,300,90,40);
l5.setBounds(30,360,90,40);
l6.setBounds(130,120,380,40);
l7.setBounds(130,180,380,40);
l8.setBounds(130,240,380,40);
l9.setBounds(130,300,380,40);
l10.setBounds(130,360,380,40);
l11.setBounds(130,80,380,40);
b1.setBounds(150,428,180,40);
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
p1.add(b1);
p1.add(l7);
p1.add(l8);
p1.add(l9);
p1.add(l10);
p1.add(l11);
}

public void actionPerformed(ActionEvent e)
{
if(e.getSource() == b1)
getstore(1);
}

public void getstore(int mode)
{
String[] line  = new String[5];
try
	{
		String[] cmd = new String[5];
		if(mode == 1){
		l11.setText("Total   Used   Free   Used%");
		cmd[0] = "adb shell df -h /data";
		cmd[1] = "adb shell df -h /system";
		cmd[2] = "adb shell df -h /cache";
		cmd[3] = "adb shell df -h /sdcard";
		cmd[4] = "adb shell df -h /dev";}
		if(mode == 2)
		{
		l11.setText("Total   Used   Free");
		cmd[0] = "adb shell df /data";
		cmd[1] = "adb shell df /system";
		cmd[2] = "adb shell df /cache";
		cmd[3] = "adb shell df /sdcard";
		cmd[4] = "adb shell df /dev";
		}
		for(int i=0 ; i<5 ; i++)
		{
		StringBuilder out = new StringBuilder();
		Runtime run = Runtime.getRuntime();
	  	Process pr = run.exec(cmd[i]);
	  	pr.waitFor();		
	BufferedReader reader =  
              new BufferedReader(new InputStreamReader(pr.getInputStream()));
 	line[i] = "";
	System.out.println(cmd[i]);
	    line[i] = reader.readLine();
	    line[i] = reader.readLine();
		System.out.println(line[i]);
		int dex = line[i].indexOf("G");
		if(dex>=0)
{
		int t = dex-4;
		line[i] = line[i].substring(t,line[i].length());
}	   
		if(dex<0)
{
		dex = line[i].indexOf("M");
		int t = dex-4;
		line[i] = line[i].substring(t,line[i].length());

}
	    if(mode==1)
	    {
            dex = line[i].indexOf("/");
	    line[i] = line[i].substring(0,dex);
            }
	    if(mode==2)
	    {
            dex = line[i].length();
	    dex = dex-5;
	    line[i] = line[i].substring(0,dex);
            }
            System.out.print(line[i] + "\n");
		}
l6.setText(line[0]);
l7.setText(line[1]);
l8.setText(line[2]);
l9.setText(line[3]);
l10.setText(line[4]);
}
catch(Exception f)
{
System.out.println("Internal Error");
getstore(2);
}
}

public void callstorage()
{
setposn();
addcomp();
}

}
