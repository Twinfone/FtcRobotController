package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class BlueOmniDrive extends LinearOpMode {


    private DcMotor FL;
    private DcMotor FR;
    private DcMotor BL;
    private DcMotor BR;
    private Servo launcher;
    private double servoPos;

    @Override
    public void runOpMode() {


        FL = hardwareMap.get(DcMotor.class, "frontL");
        FR = hardwareMap.get(DcMotor.class, "frontR");
        BL = hardwareMap.get(DcMotor.class, "backL");
        BR = hardwareMap.get(DcMotor.class, "backR");
        launcher = hardwareMap.get(Servo.class, "launcher");


        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        launcher.setPosition(0);

        waitForStart();

        while (opModeIsActive()) {
            float gamepad1LeftY = -gamepad1.left_stick_y;
            float gamepad1LeftX = gamepad1.left_stick_x;
            float gamepad1RightX = gamepad1.right_stick_x;

            // holonomic formulas

            float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
            float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

            // clip the right/left values so that the values never exceed +/- 1
            FrontRight = Range.clip(FrontRight, -1, 1);
            FrontLeft = Range.clip(FrontLeft, -1, 1);
            BackLeft = Range.clip(BackLeft, -1, 1);
            BackRight = Range.clip(BackRight, -1, 1);

            if(gamepad1.a)
                servoPos = 0.9;

            if(gamepad1.b)
                servoPos = 0;

            Range.clip(servoPos, 0,0.9);


            FL.setPower(FrontLeft);
            FR.setPower(FrontRight);
            BL.setPower(BackLeft);
            BR.setPower(BackRight);
            launcher.setPosition(servoPos);


        }
    }
}