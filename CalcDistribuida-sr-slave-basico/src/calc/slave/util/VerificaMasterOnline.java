package calc.slave.util;

import java.rmi.RemoteException;

import calc.rmi.stub.Master;

public class VerificaMasterOnline implements Runnable {

	
	public Master master;

	public VerificaMasterOnline(Master master) {
		this.master = master;
	}
	
	@Override
	public void run() {
		try {

			while (master.isOnline()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (RemoteException e) {
			System.out.println("Conexão perdida com o Servidor Master");

		}
		
	}

}
