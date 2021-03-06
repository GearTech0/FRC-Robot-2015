package org.usfirst.frc.team108.robot;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    RobotDrive myRobot;  // class that handles basic drive operations
    Joystick leftStick;  // set to ID 1 in DriverStation
    Joystick rightStick; // set to ID 2 in DriverStation
    Joystick controller;
    MagSwitch Maggie; // Magnetic Switch
    Thread silk;
    Compressor kirby;
    DoubleSolenoid s2;
    PressureControl p;
    
    Drive108 drive;
    
    public Robot() {
    	drive = new Drive108(5);
    	Maggie = new MagSwitch();
//        myRobot.setExpiration(0.1);
        leftStick = new Joystick(0);
        rightStick = new Joystick(1);
        controller = new Joystick(2);
        silk = new Thread();
        kirby = new Compressor();
        p = new PressureControl(controller);
        
        kirby.start();
        
        /*
         * 0 = Front Left
         * 1 = Rear Left
         * 2 = Front Right
         * 3 = Rear Right
         * 
         * */
        
        myRobot = new RobotDrive(drive.talonSet.get(0),
        		drive.talonSet.get(1), 
        		drive.talonSet.get(2), 
        		drive.talonSet.get(3));
    }
    
    //
    public void autonomous(){
    	while(isAutonomous() && isEnabled()){
    		try {
    			myRobot.tankDrive(1.0, 1.0);
				wait(2000);
				myRobot.tankDrive(0.0, 0.0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return;
    }
    
    //
    public void operatorControl() {
//        myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	drive.Drive(myRobot, leftStick, rightStick);
        	p.Operate();
            Timer.delay(0.005);		// wait for a motor update time
            Maggie.getPosition(drive.talonSet.get(4), controller);
            //Maggie.start();
        }
    }

}
