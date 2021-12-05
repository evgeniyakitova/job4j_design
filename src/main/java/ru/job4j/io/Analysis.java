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
                if (("400".equals(state[0]) || "500".equals(state[0])) && isWork) {
                    isWork = false;
                    period.append(state[1]);
                    period.append(";");
                }
                if (("200".equals(state[0]) || "300".equals(state[0])) && !isWork) {
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
