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
	JTextField t1,t2;
	JLabel l1,l2,l3,head,radioDesc;
	ButtonGroup logcatOptions;
	JRadioButton radio_none, radio_error, radio_info, radio_debug, radio_verbose, radio_warning, radio_fatal, radio_silent;
	Process pr,pr1;
	int ti;
	long start,end;
	String logcatFilter, logtag;
	String tim2;

	logcat()
	{
		f1 = new JFrame();
		p1 = new JPanel();
		b1 = new JButton("Start");
		b2 = new JButton("Stop");
		b3 = new JButton("Show");
		l1 = new JLabel("Duration (sec) :");
		l2 = new JLabel("Logtag:");
		l3 = new JLabel("");
		t1 = new JTextField();
		t2 = new JTextField();
		t1.setHorizontalAlignment(SwingConstants.RIGHT);
		t2.setHorizontalAlignment(SwingConstants.RIGHT);
		head = new JLabel("LOGCAT TOOL");
		radioDesc = new JLabel("Select a logcat filter:");
		radio_none = new JRadioButton("None");
		radio_error = new JRadioButton("Error");
		radio_info = new JRadioButton("Info");
		radio_debug = new JRadioButton("Debug");
		radio_verbose = new JRadioButton("Verbose");
		radio_warning = new JRadioButton("Warning");
		radio_fatal = new JRadioButton("Fatal");
		radio_silent = new JRadioButton("Silent");

		p1.setLayout(null);
		f1.add(p1);
		f1.setVisible(true);
		f1.setSize(425,510);
		f1.setTitle("Logcat Tool");
		head.setFont(new Font("Georgia",Font.BOLD,40));

		l1.setFont(new Font("Georgia",Font.BOLD,16));
		l2.setFont(new Font("Georgia",Font.BOLD,16));
		l3.setFont(new Font("Georgia",Font.BOLD,16));
		b1.setFont(new Font("Georgia",Font.BOLD,13));
		b2.setFont(new Font("Georgia",Font.BOLD,13));
		b3.setFont(new Font("Georgia",Font.BOLD,13));
		t1.setFont(new Font("Georgia",Font.BOLD,14));
		t2.setFont(new Font("Georgia",Font.BOLD,14));
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b1.setForeground(Color.WHITE);
		b2.setForeground(Color.WHITE);
		b3.setForeground(Color.WHITE);


		// Make Radio Buttons for Logcat Filter
		radioDesc.setFont(new Font("Georgia",Font.BOLD,16));
		radio_none.setFont(new Font("Georgia",Font.BOLD,13));
		radio_verbose.setFont(new Font("Georgia",Font.BOLD,13));
		radio_warning.setFont(new Font("Georgia",Font.BOLD,13));
		radio_info.setFont(new Font("Georgia",Font.BOLD,13));
		radio_debug.setFont(new Font("Georgia",Font.BOLD,13));
		radio_error.setFont(new Font("Georgia",Font.BOLD,13));
		radio_fatal.setFont(new Font("Georgia",Font.BOLD,13));
		radio_silent.setFont(new Font("Georgia",Font.BOLD,13));

		//Add radio buttons to ButtonGroup, and set None as default
		logcatOptions = new ButtonGroup();
		logcatOptions.add(radio_none);
		logcatOptions.add(radio_info);
		logcatOptions.add(radio_debug);
		logcatOptions.add(radio_warning);
		logcatOptions.add(radio_error);
		logcatOptions.add(radio_verbose);
		logcatOptions.add(radio_fatal);
		logcatOptions.add(radio_silent);
		radio_none.setSelected(true);
	}

	public void setposn()
	{
		head.setBounds(60,20,360,50);
		l1.setBounds(35,135,150,40);
		l2.setBounds(35,170,450,40);
		l3.setBounds(60,325,450,40);
		t1.setBounds(200,145,190,25);
		t2.setBounds(200, 180, 190, 25);
		b1.setBounds(60,380,100,30);
		b2.setBounds(260,380,100,30);
		b3.setBounds(160, 430,100,30);
		radioDesc.setBounds(35,230,200,20);
		radio_none.setBounds(35,260,65,20);
		radio_info.setBounds(135,260,60,20);
		radio_error.setBounds(235,260,65,20);
		radio_silent.setBounds(325,260,80,20);
		radio_warning.setBounds(35,290,95,20);
		radio_verbose.setBounds(135,290,100,20);
		radio_debug.setBounds(235,290,75,20);
		radio_fatal.setBounds(325,290,80,20);
	}

	public void addcomp()
	{
		p1.add(head);
		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(t1);
		p1.add(t2);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(radioDesc);
		p1.add(radio_none);
		p1.add(radio_error);
		p1.add(radio_debug);
		p1.add(radio_warning);
		p1.add(radio_verbose);
		p1.add(radio_info);
		p1.add(radio_fatal);
		p1.add(radio_silent);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == b1)
		{
			if (radio_info.isSelected())
				logcatFilter="I";
			else if (radio_silent.isSelected())
				logcatFilter="S";
			else if (radio_fatal.isSelected())
				logcatFilter="F";
			else if (radio_warning.isSelected())
				logcatFilter="W";
			else if (radio_verbose.isSelected())
				logcatFilter="V";
			else if (radio_debug.isSelected())
				logcatFilter="D";
			else if (radio_error.isSelected())
				logcatFilter="E";
			else logcatFilter = "V";

			String time = t1.getText();
			logtag = t2.getText();

			if(time.isEmpty())
			{
			System.out.println("ultd");
			l3.setOpaque(true);
			logunlimited();
			}
			else if(!time.isEmpty())
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

		else if(e.getSource() == b2)
		{
			killadb(start);
		}

		else if(e.getSource() == b3)
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
		l3.setText("Press Stop to stop logging...");
		start = System.currentTimeMillis();
		adbtool.logcat2 ob = new adbtool.logcat2(start,logcatFilter,logtag);
		ob.start();
	}

	public void loglimited(int x)
	{
		l3.setText("Taking logs, please wait...");
	    	start = System.currentTimeMillis();
		end = start + x*1000;
		System.out.println("Running loglimited");
		adbtool.logcat2 ob = new adbtool.logcat2(start,logcatFilter,logtag);
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
			l3.setText("Done! Saved log_"+time);
			tim2=Long.toString(time);
		}
		catch(Exception ef)
		{
			System.out.println("Failed");
		}
	}

	public void logct()
	{
		addcomp();
		setposn();
	}
}
