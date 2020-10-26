package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        final Map<String, String> answers = Map.of(
                "Exit", "Goodbye, dear friend.",
                "Hello", "Hello, dear friend."
        );
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str, key;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("msg")) {
                            key = str.split("msg=")[1].split(" ")[0];
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write(answers.getOrDefault(key, key).getBytes());
                            if (key.equals("Exit")) {
                                server.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    LOG.error("Request execution error", e);
                }
            }
        } catch (Exception e) {
            LOG.error("Server error", e);
        }
    }
}
