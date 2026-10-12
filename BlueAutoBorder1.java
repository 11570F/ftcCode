package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class BlueAutoBorder1 extends LinearOpMode 
{
    public DcMotorEx frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        frontLeftMotor  = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        backLeftMotor   = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        backRightMotor  = hardwareMap.get(DcMotorEx.class, "backRightMotor");
        
        frontRightMotor.setDirection(DcMotorEx.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorEx.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        
        // Make sure your ID's match your configuration
        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        
                frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        
        //backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        
        waitForStart(); // Begins Repeat loop

        if (isStopRequested()) return; // Stops the robot
        
        // Step 1
        distance(1,0,0);
        
    }
    public void distance(float forward, float strafe, float rotate) // all measurements in inches
    {
        int COUNTS_PER_INCH = 7200;
        double leftInches;
        //     double rightInches;
        // frontLeftMotor  = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        //  backLeftMotor   = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        //  frontRightMotor = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        //  backRightMotor  = hardwareMap.get(DcMotorEx.class, "backRightMotor");

        int frontLeftTarget;
        // int frontRightTarget;
        // int backLeftTarget;
        // int backRightTarget;        

        
        if (forward > 0)
        {
            leftInches  = Math.floor(forward * COUNTS_PER_INCH);
            // rightInches = Math.floor(-forward * COUNTS_PER_INCH);
            
            frontLeftTarget  = (int) (frontLeftMotor.getCurrentPosition()  + leftInches);
            // frontRightTarget = (int) (frontRightMotor.getCurrentPosition() + leftInches);
            // backLeftTarget   = (int) (backLeftMotor.getCurrentPosition()   + leftInches);
            // backRightTarget  = (int) (backRightMotor.getCurrentPosition()  + leftInches);
            
            backLeftMotor.setTargetPosition(frontLeftTarget);
            backRightMotor.setTargetPosition(frontLeftTarget);
            frontLeftMotor.setTargetPosition(frontLeftTarget);
            frontRightMotor.setTargetPosition(frontLeftTarget);
            
            frontRightMotor.setPower(0.3);
            backRightMotor.setPower(0.3);
            frontLeftMotor.setPower(0.3);
            backLeftMotor.setPower(0.3);
        }
        
        // if (strafe > 0)
        // {
        //     leftInches  = Math.floor(forward * COUNTS_PER_INCH);
        //     rightInches = Math.floor(forward * COUNTS_PER_INCH);
            
        //     frontLeftTarget  = (int) (frontLeftMotor.getCurrentPosition()  - leftInches);
        //     frontRightTarget = (int) (frontRightMotor.getCurrentPosition() + rightInches);
        //     backLeftTarget   = (int) (backLeftMotor.getCurrentPosition()   + leftInches);
        //     backRightTarget  = (int) (backRightMotor.getCurrentPosition()  - rightInches);
            
        //     backLeftMotor.setTargetPosition(backLeftTarget);
        //     backRightMotor.setTargetPosition(backRightTarget);
        //     frontLeftMotor.setTargetPosition(frontLeftTarget);
        //     frontRightMotor.setTargetPosition(frontRightTarget);
            
            
            
        //     telemetry.addLine("strafe is running");
        // }
        // if (rotate > 0)
        // {
        //     leftInches  = Math.floor(forward * COUNTS_PER_INCH);
        //     rightInches = Math.floor(-forward * COUNTS_PER_INCH);
            
        //     frontLeftTarget  = (int) (frontLeftMotor.getCurrentPosition()  - leftInches);
        //     frontRightTarget = (int) (frontRightMotor.getCurrentPosition() + rightInches);
        //     backLeftTarget   = (int) (backLeftMotor.getCurrentPosition()   - leftInches);
        //     backRightTarget  = (int) (backRightMotor.getCurrentPosition()  + rightInches);
            
        //     backLeftMotor.setTargetPosition(backLeftTarget);
        //     backRightMotor.setTargetPosition(backRightTarget);
        //     frontLeftMotor.setTargetPosition(frontLeftTarget);
        //     frontRightMotor.setTargetPosition(frontRightTarget);
            
        //     telemetry.addLine("rotate is running");
        // }


        
        while (opModeIsActive() && (backRightMotor.isBusy() || frontRightMotor.isBusy()) && (backLeftMotor.isBusy() || frontLeftMotor.isBusy())) {
            telemetry.addData("frontLeftPos", frontLeftMotor.getCurrentPosition());
            // telemetry.addData("frontRightPos", frontRightMotor.getCurrentPosition());
            // telemetry.addData("backLeftPos", backLeftMotor.getCurrentPosition());
            telemetry.addData("backRightPos", backRightMotor.getCurrentPosition());
            telemetry.addData("backRightTarget", backRightMotor.getTargetPosition());
            telemetry.addData("frontLeftTarget", frontLeftMotor.getTargetPosition());
            // telemetry.addData("frontRightTarget", frontRightMotor.getTargetPosition());
            telemetry.update();
        }
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        
    }
}
    