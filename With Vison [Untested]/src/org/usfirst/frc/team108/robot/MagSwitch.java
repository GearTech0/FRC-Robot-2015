package org.usfirst.frc.team108.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;


public class MagSwitch{
	ArrayList <DigitalInput> Maggie = new ArrayList<>();
	VictorSP a;
	Joystick j;
	
	//Lifter position
	int position = 0;
	int button1 = 0, button2 = 1, button3 = 2, button4 = 3;
	
	MagSwitch(){
		Maggie.add(new DigitalInput(4));
		Maggie.add(new DigitalInput(5));
		Maggie.add(new DigitalInput(6));
		Maggie.add(new DigitalInput(7));
	}
	
	/*
	 * When switch is true and called upon stop there
	 * 
	 * He wants buttons to be labeld in increasing numbers
	 * 
	 * when number is pressed go to position
	 * -----------------------------------------------------------------
	 * 
	 * Take buttons
	 * Check buttons, if number is pressed:
	 * check if motor is in that position
	 * if not, go to that position
	 * else, do nothing
	 * 
	 * While going to position:
	 * continue going until hits correct button
	 * 
	 * --------------------------------------------------------
	 * 
	 * */
	public void getPosition(VictorSP t, Joystick j){
		
		//if button is pressed and pick up position is less than 1
		
		if(j.getRawButton(button1)){ //
			if(position < 1){
				while(Maggie.get(0).get() == false ){	//while the switch is not tru
					t.set(0.2);
					position = 1;
				}
			}
			else if(position > 1){
				while(Maggie.get(0).get() == false ){
					t.set(-0.2);
					position = 1;
				}
			}
		}
		else if(j.getRawButton(button2)){
			if(position <2){
				while(Maggie.get(1).get() == false){
					t.set(0.2);
					position = 2;
				}
			}
			else if(position > 2){
				while(Maggie.get(1).get() == false){
					t.set(-2.0);
					position = 2;
				}
			}
		}
		else if(j.getRawButton(button3)){
			if(position < 3){
				while(Maggie.get(2).get() == false){
					t.set(0.2);
					position = 3;
				}
			}
			else if(position > 3){
				while(Maggie.get(2).get() == false){
					t.set(-0.2);
					position = 3;
				}
			}
		}
		else if(j.getRawButton(button4)){
			if(position < 4){
				while(Maggie.get(3).get() == false){
					t.set(0.2);
					position = 4;
			}
		}
			else if(position >= 4){
				while(Maggie.get(3).get() == false){
					t.set(-0.2);
					position = 4;
				}
			}
		}
		else{
			t.set(0.0);
		}
	}
//	public int checkPosition(int initial, int destination){
//		if(initial > destination){
//			return -0.5;
//		}
//		else(initial < destination){
//			return 0.5;
//		}
//	}
	
//	public int getJoyCode(Joystick j){
//		return j.;
//	}
}
//ALEXXXXXXXXXXX ARE WE DONE!
