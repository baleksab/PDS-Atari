package baleksab.pdsatari.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        try {
            IDeleteChatHistory deleteChatHistory = new DeleteChatHistory();
            Registry registry = LocateRegistry.createRegistry(8081);
            registry.rebind("DeleteChatHistory", deleteChatHistory);
            System.out.println("Started rmi server");
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

}
