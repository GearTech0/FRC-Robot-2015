package org.usfirst.frc.team108.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;

public class PressureControl {
	DoubleSolenoid kirbyDouble, kirbyDouble2;
	Joystick controller;
	
	PressureControl(Joystick controller){
		kirbyDouble = new DoubleSolenoid(1, 0);
		kirbyDouble2 = new DoubleSolenoid(2, 3);
		
		this.controller = controller;
	}
	
	public void Operate(){
		if(controller.getRawButton(9))
			Open();
		if(controller.getRawButton(10))
			Close();
	}
	
	public void Open(){
		kirbyDouble.set(Value.kForward);
		kirbyDouble2.set(Value.kForward);
	}
	public void Close(){
		kirbyDouble.set(Value.kReverse);
		kirbyDouble2.set(Value.kReverse);
	}
}
