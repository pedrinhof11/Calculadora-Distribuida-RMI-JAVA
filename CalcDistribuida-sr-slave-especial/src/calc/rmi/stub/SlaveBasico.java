package calc.rmi.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SlaveBasico extends Remote{

	public boolean isOnline() throws RemoteException;
	
	public double getSoma(double a, double b) throws RemoteException;
	
	public double getSubtracao(double a, double b) throws RemoteException;
	
	public double getMultiplicacao(double a, double b) throws RemoteException;
	
	public double getDivisao(double a, double b) throws RemoteException;
	
	
}
