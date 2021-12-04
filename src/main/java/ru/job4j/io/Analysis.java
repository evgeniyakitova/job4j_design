package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileWriter(target))) {
            boolean isWork = true;
            StringBuilder period = new StringBuilder();
            String str = in.readLine();
            while (str != null) {
                String[] state = str.split(" ");
                if ((state[0].equals("400") || state[0].equals("500")) && isWork) {
                    isWork = false;
                    period.append(state[1]);
                    period.append(";");
                }
                if ((state[0].equals("200") || state[0].equals("300")) && !isWork) {
                    period.append(state[1]);
                    out.println(period);
                    isWork = true;
                    period.setLength(0);
                }
                str = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("server.log", "target.txt");
    }
}
