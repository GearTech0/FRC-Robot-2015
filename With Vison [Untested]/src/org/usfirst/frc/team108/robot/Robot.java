package org.usfirst.frc.team108.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the NIVision class to do vision processing. 
 * The image is acquired from the USB Webcam, then a circle is overlayed on it. 
 * The NIVision class supplies dozens of methods for different types of processing. 
 * The resulting image can then be sent to the FRC PC Dashboard with setImage()
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
	
    int session;
    Image frame;
    
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
        
    public void robotInit() {

        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
    }

    public void operatorControl() {
        NIVision.IMAQdxStartAcquisition(session);

        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);

        while (isOperatorControl() && isEnabled()) {

            NIVision.IMAQdxGrab(session, frame, 1);
            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
            
            CameraServer.getInstance().setImage(frame);

            /** robot code here! **/
            drive.Drive(myRobot, leftStick, rightStick);
        	p.Operate();
            //Timer.delay(0.005);		// wait for a motor update time
            Maggie.getPosition(drive.talonSet.get(4), controller);
            //Maggie.start();
            
            Timer.delay(0.005);		// wait for a motor update time
        }
        NIVision.IMAQdxStopAcquisition(session);
    }

    public void test() {
    }
}
