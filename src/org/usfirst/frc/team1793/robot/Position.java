package org.usfirst.frc.team1793.robot;

import static org.usfirst.frc.team1793.robot.Constants.BREACH;
import static org.usfirst.frc.team1793.robot.Constants.FARENOUGH;
import static org.usfirst.frc.team1793.robot.Constants.STRAIGHTENOUGH;
import static org.usfirst.frc.team1793.robot.Sensors.back;
import static org.usfirst.frc.team1793.robot.Sensors.front;
import static org.usfirst.frc.team1793.robot.Sensors.leftBack;
import static org.usfirst.frc.team1793.robot.Sensors.leftFront;
import static org.usfirst.frc.team1793.robot.Sensors.rightBack;
import static org.usfirst.frc.team1793.robot.Sensors.rightFront;

import org.usfirst.frc.team1793.robot.Constants.Progress;

public class Position {

	public static boolean straight;
	public static boolean frontObstructed,backObstructed;
	public static Progress breaching;
	
	public static void sense() {
		double frontPairSum = Sensors.getDistanceSum(leftFront, rightFront);
		double backPairSum = Sensors.getDistanceSum(leftBack, rightBack);
		double frontPairDelta = Sensors.getDelta(leftFront, rightFront);
		double backPairDelta = Sensors.getDelta(leftBack, rightBack);
		double frontDistance = Sensors.getDistance(front);
		double backDistance = Sensors.getDistance(back);
		straight = Math.abs(frontPairDelta - backPairDelta) <= STRAIGHTENOUGH; 
		frontObstructed = frontDistance < FARENOUGH;
		backObstructed = backDistance < FARENOUGH;
		if(frontPairSum <= BREACH && backPairSum > BREACH) {
			breaching = Progress.JUSTFRONT;
		} else if(frontPairSum > BREACH && backPairSum <= BREACH){
			breaching = Progress.JUSTBACK;
		} else if(frontPairSum <= BREACH && backPairSum <= BREACH) {
			breaching = Progress.ALL;
		} else {
			breaching = Progress.NONE;
		}
	}	


}
