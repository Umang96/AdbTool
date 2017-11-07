package adbtool;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class logcat2 implements Runnable
{
Thread thread;
long tim;
String filter,tag;
logcat2(long x,String ftr,String tg)
{
tim=x;
filter=ftr;
tag=tg;
if(tag.length()<1)
tag="*";
tag+=":";
}
public void start () {
        thread = new Thread (this);
        thread.start ();
    }    
public void run ()
{
Process pr;
try{
String time = Long.toString(tim);
time="log_"+time;
File file =new File(time);
file.createNewFile();
FileWriter fileWritter = new FileWriter(file.getName(),true);
BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
String cmd="adb logcat "+tag+filter;
if(!tag.equals("*:"))
cmd+=" *:S";
System.out.println(cmd);
Runtime run = Runtime.getRuntime();
	      pr = run.exec(cmd);
 BufferedReader reader =  
              new BufferedReader(new InputStreamReader(pr.getInputStream()));
 String line = "";
        while((line = reader.readLine()) != null) {
            //System.out.print(line + "\n");
	    bufferWritter.write(line+"\n");
        }
bufferWritter.close();
pr.waitFor();
}
catch(Exception e)
{
JOptionPane.showMessageDialog(null, "Failed, is your phone connected in adb mode ?");
}
}
}
