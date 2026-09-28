package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class BlueAutoBorder1 extends LinearOpMode 
{
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

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        

        
        waitForStart(); // Begins Repeat loop

        if (isStopRequested()) return; // Stops the robot

        while (opModeIsActive()) {
            distance(1,0,0);
            
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
        int frontLeftTarget;
        int frontRightTarget;
        int backLeftTarget;
        int backRightTarget;
        
        
        if (forward > 0)
        {
            leftInches = Math.floor(forward * COUNTS_PER_INCH);
            rightInches = Math.floor(-forward * COUNTS_PER_INCH);
            
            frontLeftTarget = (int) (frontLeftMotor.getCurrentPosition() + leftInches);
            frontRightTarget = (int) (frontRightMotor.getCurrentPosition() + rightInches);
            backLeftTarget = (int) (backLeftMotor.getCurrentPosition() + leftInches);
            backRightTarget = (int) (backRightMotor.getCurrentPosition() + rightInches);
            
            backLeftMotor.setTargetPosition(backLeftTarget);
            backRightMotor.setTargetPosition(backRightTarget);
            frontLeftMotor.setTargetPosition(frontLeftTarget);
            frontRightMotor.setTargetPosition(frontRightTarget);
        }
        
        if (strafe > 0)
        {
            leftInches = Math.floor(forward * COUNTS_PER_INCH);
            rightInches = Math.floor(-forward * COUNTS_PER_INCH);
            
            frontLeftTarget = (int) (frontLeftMotor.getCurrentPosition() - leftInches);
            frontRightTarget = (int) (frontRightMotor.getCurrentPosition() + rightInches);
            backLeftTarget = (int) (backLeftMotor.getCurrentPosition() + leftInches);
            backRightTarget = (int) (backRightMotor.getCurrentPosition() - rightInches);
            
            backLeftMotor.setTargetPosition(backLeftTarget);
            backRightMotor.setTargetPosition(backRightTarget);
            frontLeftMotor.setTargetPosition(frontLeftTarget);
            frontRightMotor.setTargetPosition(frontRightTarget);
        }
        if (rotate > 0)
        {
            leftInches = Math.floor(forward * COUNTS_PER_INCH);
            rightInches = Math.floor(-forward * COUNTS_PER_INCH);
            
            frontLeftTarget = (int) (frontLeftMotor.getCurrentPosition() - leftInches);
            frontRightTarget = (int) (frontRightMotor.getCurrentPosition() + rightInches);
            backLeftTarget = (int) (backLeftMotor.getCurrentPosition() - leftInches);
            backRightTarget = (int) (backRightMotor.getCurrentPosition() + rightInches);
            
            backLeftMotor.setTargetPosition(backLeftTarget);
            backRightMotor.setTargetPosition(backRightTarget);
            frontLeftMotor.setTargetPosition(frontLeftTarget);
            frontRightMotor.setTargetPosition(frontRightTarget);
        }
        
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        frontLeftMotor.setPower(0.7);
        backLeftMotor.setPower(0.7);
        frontRightMotor.setPower(0.7);
        backRightMotor.setPower(0.7);

    }
}
    
