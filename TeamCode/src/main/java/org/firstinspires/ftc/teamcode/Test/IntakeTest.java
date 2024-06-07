package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "IntakeTest", group = "Linear OpMode")
public class IntakeTest extends LinearOpMode {

    // Define class members
    public Servo leftPincer;
    public Servo rightPincer;

    public CRServo spinnerIntake;

    public TouchSensor rightIntake;
    public TouchSensor leftIntake;

    public ElapsedTime intakeTimer = new ElapsedTime();




    @Override
    public void runOpMode() {

       // rightPincer = hardwareMap.get(Servo.class, "leftPincer");
      //  leftPincer = hardwareMap.get(Servo.class, "rightPincer");
        spinnerIntake = hardwareMap.get(CRServo.class, "spinnny");
        leftIntake = hardwareMap.get(TouchSensor.class, "leftIntake");
        rightIntake = hardwareMap.get(TouchSensor.class, "rightIntake");



        waitForStart();


        while (opModeIsActive()) {


            //if (gamepad1.right_bumper) {
            //wrist drops

            //open pincers which dont have pixels in them

            //turn on spinny thingy

            //close pincer when pixel gets in

            //stop intake when pincers close

            //when button stops being pressed bring the wirst



            if (!leftIntake.isPressed()  || !rightIntake.isPressed()) {

                intakeTimer.reset();



                spinnerIntake.setPower(0);
                sleep(2000);

            } else {
                spinnerIntake.setPower(-1);
            }


        }


        //do the intake!!!
        telemetry.addData(" left Servo Position", "%5.2f", leftPincer.getPosition());
        telemetry.addData("right Servo Position", "%5.2f", rightPincer.getPosition());
        telemetry.addData(">", "Press Stop to end test.");
        telemetry.update();

    }

}
