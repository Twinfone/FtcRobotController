package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name="LeoTest", group = "Linear OpMode")

public class LeoTest extends LinearOpMode {

    private DcMotor BR;
    private DcMotor BL;
    private DcMotor FR;
    private DcMotor FL;

    @Override
    public void runOpMode(){
        BR = hardwareMap.get(DcMotor.class, "backR");
        BL  = hardwareMap.get(DcMotor.class, "backL");
        FR = hardwareMap.get(DcMotor.class, "frontR");
        FL = hardwareMap.get(DcMotor.class, "frontL");

        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        moveRobot(0.5,0,0, 700);
        moveRobot(0,-0.5,0, 1300);
        moveRobot(-0.5,0,0, 700);
        moveRobot(0,0.5,0, 1300);
        moveRobot(0.4,-0.1,-0.3, 1000);



    }
    public void moveRobot(double x, double y, double yaw, int time) {
        // Calculate wheel powers.
        //pos y is left

        double leftFrontPower    =  x -y -yaw;
        double rightFrontPower   =  x +y +yaw;
        double leftBackPower     =  x +y -yaw;
        double rightBackPower    =  x -y +yaw;

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



        sleep(time);
        FL.setPower(0);
        FR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
        sleep(500);

    }
}
