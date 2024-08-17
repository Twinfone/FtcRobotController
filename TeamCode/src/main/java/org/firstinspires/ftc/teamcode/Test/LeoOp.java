package org.firstinspires.ftc.teamcode.Test;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name= "LeoOp", group="Linear Opmode")
public class LeoOp extends LinearOpMode {


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
            double max;

            double axial = -gamepad1.left_stick_y;
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;

            double leftFrontPower = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower = axial - lateral + yaw;
            double rightBackPower = axial + lateral - yaw;

            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }

            if(gamepad1.a)
                servoPos = 0.9;

            if(gamepad1.b)
                servoPos = 0;

            Range.clip(servoPos, 0,0.9);


            FL.setPower(leftFrontPower);
            FR.setPower(rightFrontPower);
            BL.setPower(leftBackPower);
            BR.setPower(rightBackPower);
            launcher.setPosition(servoPos);


        }
    }
}