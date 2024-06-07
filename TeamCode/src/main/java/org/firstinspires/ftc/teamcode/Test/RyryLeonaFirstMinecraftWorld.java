package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="RyryLeonaFirstMinecraftWorld", group = "Linear OpMode")

public class RyryLeonaFirstMinecraftWorld extends LinearOpMode {


    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    @Override

    public void runOpMode() {

        leftFrontDrive  = hardwareMap.get(DcMotor.class, "frontL");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "backL");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "frontR");
        rightBackDrive = hardwareMap.get(DcMotor.class, "backR");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        //while (opModeIsActive()) {

            leftBackDrive.setPower(0.3);
            rightFrontDrive.setPower(0.3);
            sleep(5000);

            leftBackDrive.setPower(0);
            rightFrontDrive.setPower(0);

        //}

    }

}
