import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Chat_Application {
    private static final int PORT = 12345;
    private static Map<String, PrintWriter> clientWriters = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("ChatServer started on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Error in the server: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;
        private String username;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);

                writer.println("Enter your username:");
                username = reader.readLine();
                System.out.println("New client connected: " + username);

                clientWriters.put(username, writer);

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received message from " + username + ": " + message);
                    if (message.startsWith("/private")) {
                        String[] parts = message.split("\\s+", 3);
                        if (parts.length >= 3) {
                            String recipient = parts[1];
                            String privateMessage = parts[2];
                            sendPrivateMessage(username, recipient, privateMessage);
                        }
                    } else {
                        broadcastMessage(username + ": " + message);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error handling client: " + e.getMessage());
            } finally {
                clientWriters.remove(username);
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Error closing client socket: " + e.getMessage());
                }
            }
        }

        private void sendPrivateMessage(String sender, String recipient, String message) {
            PrintWriter recipientWriter = clientWriters.get(recipient);
            if (recipientWriter != null) {
                recipientWriter.println("[Private from " + sender + "]: " + message);
            } else{
                writer.println(recipient + " is not connected");
            }
        }

        private static void broadcastMessage(String message) {
            for (PrintWriter writer : clientWriters.values()) {
                writer.println(message);
            }
        }
    }
}
