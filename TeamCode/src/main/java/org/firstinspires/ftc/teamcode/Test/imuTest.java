package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous(name= "imuTest", group="Linear Opmode")

public class imuTest extends LinearOpMode {

    private DcMotor FL;
    private DcMotor FR;
    private DcMotor BL;
    private DcMotor BR;
    private IMU imu;

    public static final double IMU_TURN_GAIN =  0.040  ;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)
    public static final double IMU_MAX_AUTO_TURN = 0.4;   //  Clip the turn speed to this max value (adjust for your robot)



    @Override
    public void runOpMode(){

        FL = hardwareMap.get(DcMotor.class, "frontL");
        FR = hardwareMap.get(DcMotor.class, "frontR");
        BL = hardwareMap.get(DcMotor.class, "backL");
        BR = hardwareMap.get(DcMotor.class, "backR");

        imu = hardwareMap.get(IMU.class, "imu");

        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        configureImu();
        imu.resetYaw();

        waitForStart();
        while (opModeIsActive()){
            imuTurn(90);
        }


    }
    private void imuTurn(double heading){
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        double turn;
        double headingError;

        headingError = heading - orientation.getYaw(AngleUnit.DEGREES);

        while (Math.abs(headingError) > 5 && opModeIsActive()){
            orientation = imu.getRobotYawPitchRollAngles();
            headingError = heading - orientation.getYaw(AngleUnit.DEGREES);

            turn   = Range.clip(headingError * IMU_TURN_GAIN, -1, 1);
            moveRobot(0,0,turn);

        }
        moveRobot(0,0,0);





    }
    public void moveRobot(double x, double y, double yaw) {
        // Calculate wheel powers.
        //pos y is left

        double leftFrontPower = x - y - yaw;
        double rightFrontPower = x + y + yaw;
        double leftBackPower = x + y - yaw;
        double rightBackPower = x - y + yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        // Send powers to the wheels.
        FL.setPower(leftFrontPower);
        FR.setPower(rightFrontPower);
        BL.setPower(leftBackPower);
        BR.setPower(rightBackPower);

    }
    private void configureImu(){

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);


        imu.initialize(new IMU.Parameters(orientationOnRobot));


    }


}
