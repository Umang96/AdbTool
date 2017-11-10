package adbtool;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class screenrec2 implements Runnable
{
Thread thread;
long tim;
String resolution;
String bitrate;
ShellHelper sh;

screenrec2(long x,String res,String bit)
{
tim=x;
resolution=res;
bitrate=bit;
sh = new ShellHelper(0);
}
public void start () {
        thread = new Thread (this);
        thread.start ();
    }
public void run ()
{
try{
String st = Long.toString(tim);
String cmd="adb shell screenrecord --bit-rate "+bitrate+" --size "+resolution+" /sdcard/screenrec_"+st+".mp4";
System.out.println(cmd);
sh.executor(cmd);
}
catch(Exception e)
{
e.printStackTrace();
JOptionPane.showMessageDialog(null, "Failed, is your phone connected in adb mode ?");
}
}
}
