package org.usfirst.frc.team108.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class Drive108 {
	ArrayList<Talon> talonSet = new ArrayList<>();
	
	Drive108(int numTalon){
		for(int i = 0; i < numTalon; i++)
			talonSet.add(new Talon(i));
	}
	
	public void Drive(RobotDrive robo, Joystick j1, Joystick j2){
		robo.tankDrive(j1, j2);
	}
	
}
