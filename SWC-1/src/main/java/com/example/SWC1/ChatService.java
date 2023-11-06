package com.example.SWC1;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private List<WebSocketSession> sessions = new ArrayList<>();

    public void addUser(WebSocketSession session) {
        // Add a user's WebSocket session to the list of active sessions
        sessions.add(session);
    }

    public void removeUser(WebSocketSession session) {
        // Remove a user's WebSocket session when they disconnect
        sessions.remove(session);
    }

    public void broadcastMessage(String message) {
        // Broadcast a message to all connected users
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                // Handle the exception, e.g., by logging the error
            }
        }
    }

    public void sendMessageToUser(WebSocketSession session, String message) {
        // Send a message to a specific user's WebSocket session
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            // Handle the exception, e.g., by logging the error
        }
    }
}

