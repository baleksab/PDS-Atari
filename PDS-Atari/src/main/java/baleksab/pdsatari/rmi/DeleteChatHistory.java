package baleksab.pdsatari.rmi;

import baleksab.pdsatari.service.ChatMessageService;
import jakarta.inject.Inject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DeleteChatHistory extends UnicastRemoteObject implements IDeleteChatHistory {

    protected DeleteChatHistory() throws RemoteException {

    }

    public void deleteChatHistory() throws RemoteException {
        ChatMessageService chatMessageService = new ChatMessageService();
        System.out.println("Attempting to delete chat history...");
        chatMessageService.deleteAllMessages();
        System.out.println("Deleted chat history!");
    }

}
