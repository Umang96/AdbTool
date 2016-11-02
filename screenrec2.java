package adbtool;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class screenrec2 implements Runnable
{
Thread thread;
long tim;
screenrec2(long x)
{
tim=x;
}
public void start () {
        thread = new Thread (this);
        thread.start ();
    }
public void run ()
{
Process pr;
try{
String st = Long.toString(tim);
String cmd="adb shell screenrecord /sdcard/screenrec_"+st+".mp4";
System.out.println(cmd);
Runtime run = Runtime.getRuntime();
	      pr = run.exec(cmd);
	      pr.waitFor();
}
catch(Exception e)
{
JOptionPane.showMessageDialog(null, "Failed, is your phone connected in adb mode ?");
}
}
}
