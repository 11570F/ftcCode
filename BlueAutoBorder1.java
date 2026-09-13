package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class BlueAutoBorder1 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        

        
        waitForStart(); // Begins Repeat loop

        if (isStopRequested()) return; // Stops the robot

        while (opModeIsActive()) {
            
            
        }
        
    }
    private void distance(float forward, float strafe, float rotate) // all measurements in inches
    {
        int tps = 5000;
        int COUNTS_PER_INCH = 1440;
        double leftInches;
        double rightInches;
        boolean forwardCheck;
        boolean strafeCheck;
        boolean rotateCheck;
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        double frontLeftTarget;
        double frontRightTarget;
        double backLeftTarget;
        double backRightTarget;
        
        
        if (forward > 0)
        {
            leftInches = Math.floor(forward * COUNTS_PER_INCH);
            rightInches = Math.floor(-forward * COUNTS_PER_INCH);
            
            frontLeftTarget = Math.floor(frontLeftMotor.getCurrentPosition()) + Math.floor(leftInches * COUNTS_PER_INCH);
            frontRightTarget = Math.floor(frontRightMotor.getCurrentPosition()) + Math.floor(rightInches * COUNTS_PER_INCH);
            backLeftTarget = Math.floor(backLeftMotor.getCurrentPosition()) + Math.floor(leftInches * COUNTS_PER_INCH);
            backRightTarget = Math.floor(backRightMotor.getCurrentPosition()) + Math.floor(rightInches * COUNTS_PER_INCH);
            
            backLeftMotor.setTargetPosition((double) backLeftTarget);
            backRightMotor.setTargetPosition((double) backRightTarget);
            frontLeftMotor.setTargetPosition((double) frontLeftTarget);
            frontRightMotor.setTargetPosition((double) frontRightTarget);
        }
        
        if (strafe > 0)
        {
            leftInches = Math.floor(forward * COUNTS_PER_INCH);
            rightInches = Math.floor(-forward * COUNTS_PER_INCH);
            
            frontLeftTarget = frontLeftMotor.getCurrentPosition() + Math.floor(leftInches * COUNTS_PER_INCH);
            frontRightTarget = frontRightMotor.getCurrentPosition() + Math.floor(-rightInches * COUNTS_PER_INCH);
            backLeftTarget = backLeftMotor.getCurrentPosition() + Math.floor(-leftInches * COUNTS_PER_INCH);
            backRightTarget = backRightMotor.getCurrentPosition() + Math.floor(rightInches * COUNTS_PER_INCH);
            
            backLeftMotor.setTargetPosition((int) backLeftTarget);
            backRightMotor.setTargetPosition((int) backRightTarget);
            frontLeftMotor.setTargetPosition((int) frontLeftTarget);
            frontRightMotor.setTargetPosition((int) frontRightTarget);
        }
        
        if (rotate > 0)
        {
            leftInches = Math.floor(forward * COUNTS_PER_INCH);
            rightInches = Math.floor(-forward * COUNTS_PER_INCH);
            
            frontLeftTarget = frontLeftMotor.getCurrentPosition() + Math.floor(leftInches * COUNTS_PER_INCH);
            frontRightTarget = frontRightMotor.getCurrentPosition() + Math.floor(-rightInches * COUNTS_PER_INCH);
            backLeftTarget = backLeftMotor.getCurrentPosition() + Math.floor(leftInches * COUNTS_PER_INCH);
            backRightTarget = backRightMotor.getCurrentPosition() + Math.floor(-rightInches * COUNTS_PER_INCH);
            
            backLeftMotor.setTargetPosition((int) backLeftTarget);
            backRightMotor.setTargetPosition((int) backRightTarget);
            frontLeftMotor.setTargetPosition((int) frontLeftTarget);
            frontRightMotor.setTargetPosition((int) frontRightTarget);
        }
    }
}
    
