package calc.rmi.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SlaveEspecial  extends Remote {
	
	public boolean isOnline() throws RemoteException;
	
	public Double getPorcentagem(Double a) throws RemoteException;
	
	public Double getRaiz(Double a) throws RemoteException;

	public Double getPotenciacao(int a, int b) throws RemoteException;

}
