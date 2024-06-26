package baleksab.pdsatari.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDeleteChatHistory extends Remote  {

    void deleteChatHistory() throws RemoteException;

}
