package calc.rmi.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Master extends Remote {
	
	public boolean isOnline() throws RemoteException;
	
	public boolean registraEscravo(SlaveBasico slaveBasico) throws RemoteException;
	
	public boolean registraEscravo(SlaveEspecial slaveEspecial) throws RemoteException;
	
}