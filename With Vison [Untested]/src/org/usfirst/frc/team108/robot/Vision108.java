package org.usfirst.frc.team108.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;

public class Vision108 {
	String name;
	Image frame;
	int session;
	NIVision.Rect rect;
	CameraServer ca, ca2;
	
	Vision108(String name){
		this.name = name;
	}
	
	public void createCamera(){
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		session = NIVision.IMAQdxOpenCamera(name,
	                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);
	}
	public void initiateCamera(){
//		NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
		NIVision.IMAQdxGrab(session, frame, 1);
		NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                 DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
		CameraServer.getInstance().setImage(frame);
		
	}
	public void start(){
		NIVision.IMAQdxStartAcquisition(session);
		rect = new NIVision.Rect(10, 10, 100, 100);
	}
	public void stop(){
		NIVision.IMAQdxStopAcquisition(session);
	}
}
