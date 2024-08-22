package org.firstinspires.ftc.teamcode.Test;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name= "sixWheelOmniOp", group="Linear Opmode")
public class sixWheelOmniOp extends LinearOpMode {

    // Declarations
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor side;
    private Servo launcher;
    private double servoPos;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "driveleft");
        rightDrive = hardwareMap.get(DcMotor.class, "driveright");
        side = hardwareMap.get(DcMotor.class, "side");
        launcher = hardwareMap.get(Servo.class, "launcher");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        launcher.setPosition(0);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double leftPower;
            double rightPower;

            double drive = gamepad1.left_stick_y;  // Forward/Backward movement
            double turn  =  -gamepad1.right_stick_x; // Left/Right rotation (turn)
            double strafe = -gamepad1.left_stick_x;  // Left/Right strafe

            leftPower  = Range.clip(drive - turn, -1.0, 1.0); //Calculate the power for the left motor
            rightPower = Range.clip(drive + turn, -1.0, 1.0); //Calculate the power for the right motor
            double strafePower = Range.clip(strafe, -1,1); //Calculate the power for the strafing motor

            if(gamepad1.a)
                servoPos = 1;

            if(gamepad1.b)
                servoPos = 0;



            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            side.setPower(strafePower);
            launcher.setPosition(servoPos);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f), side (%.2f)", leftPower, rightPower, strafePower);
            telemetry.update();
        }
    }
}
