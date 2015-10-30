package com.vz.usage;

import java.util.List;

import com.vz.usage.util.CustAlert;

public class AlertProcessed extends Thread{
	List<CustAlert> alertUsers;
	public AlertProcessed(List<CustAlert> ppalertUsers) {
		alertUsers=ppalertUsers;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("starting.... ");
		//AlertProcessed().st
	}

	@Override
	public void run() {
		ProcessedAlerts.updateAlerts(alertUsers);
		System.out.println("Completed the process ***********");
	}
}
