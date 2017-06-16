package calc.slave.server;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import calc.rmi.stub.Master;
import calc.rmi.stub.SlaveBasico;

public class SlaveBasicoImple extends UnicastRemoteObject implements SlaveBasico{

	private static final long serialVersionUID = 1291277331116139235L;
	
	private ExecutorService executorService;
	
	public SlaveBasicoImple() throws RemoteException {
		super();
		executorService = Executors.newFixedThreadPool(1);
	}

	public void VerificaMasterOnline(Master master) {
		executorService.execute(new calc.slave.util.VerificaMasterOnline(master));
	}	

	@Override
	public boolean isOnline() throws RemoteException {
		return true;
	}

	@Override
	public double getSoma(double a, double b) throws RemoteException {
		Double resposta = new BigDecimal(a).add(new BigDecimal(b)).doubleValue();
		System.out.println("SOMA: " + a + " + " + b + " = " + resposta);
		return resposta;
	}

	@Override
	public double getSubtracao(double a, double b) throws RemoteException {
		Double resposta = new BigDecimal(a).subtract(new BigDecimal(b)).doubleValue();
		System.out.println("SUBTRAÇÃO: " + a + " - " + b + " = " + resposta);
		return resposta;
	}

	@Override
	public double getMultiplicacao(double a, double b) throws RemoteException {
		Double resposta = new BigDecimal(a).multiply(new BigDecimal(b)).doubleValue();
		System.out.println("MULTIPLICAÇÃO: " + a + " x " + b + " = " + resposta);
		return resposta;
	}

	@Override
	public double getDivisao(double a, double b) throws RemoteException {
		Double resposta = new BigDecimal(a).divide(new BigDecimal(b), 3, RoundingMode.HALF_EVEN).doubleValue();
		System.out.println("DIVISÃO: " + a + " / " + b + " = " + resposta);
		return resposta;
	}


}
