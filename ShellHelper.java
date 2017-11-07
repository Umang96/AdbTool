package adbtool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ShellHelper {

    String line;
    BufferedReader mReader;
    BufferedWriter mWriter;
    Process mProcess;
    boolean firstTry;

    void FinishShell()
    {
        try {
            mReader.close();
            mWriter.close();
            mProcess.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ShellHelper(int root)
    {
        try {
            if(root==1) {
                mProcess = Runtime.getRuntime().exec("sudo");
            }
            else
            {
                mProcess = Runtime.getRuntime().exec("sh");
            }
            mWriter = new BufferedWriter(new OutputStreamWriter(mProcess.getOutputStream()));
            mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    synchronized public String executor(String command) {
        try {
            StringBuilder sb = new StringBuilder();
            String callback = "/shellCallback/";
            mWriter.write(command + "\necho " + callback + "\n");
            mWriter.flush();

            int i;
            char[] buffer = new char[256];
            while (true) {
                sb.append(buffer, 0, mReader.read(buffer));
                if ((i = sb.indexOf(callback)) > -1) {
                    sb.delete(i, i + callback.length());
                    break;
                }
            }
            firstTry = false;
            return sb.toString().trim();
         }
         catch (Exception e) {
            e.printStackTrace();
        }

        return "FAIL";
    }
}
