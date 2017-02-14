package org.usfirst.frc.team4911.robot.subsystems;

import java.util.logging.Logger;

import org.junit.Before;

import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.HLUsageReporting.Interface;

public class SubsystemTestBase {
	Logger log = Logger.getLogger(SubsystemTestBase.class.getName());

	private Interface implementation = new Interface() {

		@Override
		public void reportSmartDashboard() {
		}

		@Override
		public void reportScheduler() {
		}

		@Override
		public void reportPIDController(int num) {
		}
	};

	@Before
	public void initSubsystemBase() {
		// need to call this before the subsystem or it fails due to no implementation
		HLUsageReporting.SetImplementation(implementation);
	}
}
