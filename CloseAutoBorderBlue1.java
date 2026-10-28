package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.PwmControl;


@Autonomous
public class CloseAutoBorderBlue1 extends LinearOpMode 
{
    public DcMotorEx frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, launcher;
    public CRServoImplEx windmill;
    public int launcherSpeed = 1405;
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        frontLeftMotor  = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        backLeftMotor   = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        backRightMotor  = hardwareMap.get(DcMotorEx.class, "backRightMotor");
        launcher        = hardwareMap.get(DcMotorEx.class, "launcher");
        windmill        = hardwareMap.get(CRServoImplEx.class, "windmillServo");
        
        windmill.setPwmRange(new PwmControl.PwmRange(500, 2500));
        
        frontRightMotor.setDirection(DcMotorEx.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorEx.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        
        // Make sure your ID's match your configuration
        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        launcher.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        
        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        
        backLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        
        waitForStart(); // Begins Repeat loop

        if (isStopRequested()) return; // Stops the robot
        
        // Step 1
        distance(0,0,0,30);
        windmill.setPower(0);
        
    }
    public void distance(float forward, float strafe, float rotate, float shoot) // all measurements in inches
    {
        int COUNTS_PER_INCH = 720;
        double leftInches;
        double rightInches;

        int frontLeftTarget;
        int frontRightTarget;
        int backLeftTarget;
        int backRightTarget;
        int launcherTarget;

        
        if (forward > 0)
        {
            leftInches  = Math.floor(forward * COUNTS_PER_INCH);
            rightInches = Math.floor(-forward * COUNTS_PER_INCH);
            
            frontLeftTarget  = (int) (frontLeftMotor.getCurrentPosition()  + leftInches);
            frontRightTarget = (int) (frontRightMotor.getCurrentPosition() + rightInches);
            backLeftTarget   = (int) (backLeftMotor.getCurrentPosition()   + leftInches);
            backRightTarget  = (int) (backRightMotor.getCurrentPosition()  + rightInches);
            
            backLeftMotor.setTargetPosition(backLeftTarget);
            backRightMotor.setTargetPosition(backRightTarget);
            frontLeftMotor.setTargetPosition(frontLeftTarget);
            frontRightMotor.setTargetPosition(frontRightTarget);
            
            frontRightMotor.setPower(0.3);
            backRightMotor.setPower(0.3);
            frontLeftMotor.setPower(0.3);
            backLeftMotor.setPower(0.3);
            
            backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            
            telemetry.addLine("Forward is running!");
        }
        
        if (strafe > 0)
        {
            leftInches  = Math.floor(strafe * COUNTS_PER_INCH);
            rightInches = Math.floor(-strafe * COUNTS_PER_INCH);
            
            frontLeftTarget  = (int) (frontLeftMotor.getCurrentPosition()  + leftInches);
            frontRightTarget = (int) (frontRightMotor.getCurrentPosition() - rightInches);
            backLeftTarget   = (int) (backLeftMotor.getCurrentPosition()   - leftInches);
            backRightTarget  = (int) (backRightMotor.getCurrentPosition()  + rightInches);
            
            backLeftMotor.setTargetPosition(backLeftTarget);
            backRightMotor.setTargetPosition(backRightTarget);
            frontLeftMotor.setTargetPosition(frontLeftTarget);
            frontRightMotor.setTargetPosition(frontRightTarget);
            
            backLeftMotor.setTargetPositionTolerance(30);
            backRightMotor.setTargetPositionTolerance(30);
            frontLeftMotor.setTargetPositionTolerance(30);
            frontRightMotor.setTargetPositionTolerance(30);
            
            frontRightMotor.setPower(0.3);
            backRightMotor.setPower(0.3);
            frontLeftMotor.setPower(0.3);
            backLeftMotor.setPower(0.3);
            
            backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            
            telemetry.addLine("strafe is running");
        }
        if (rotate > 0)
        {
            leftInches  = Math.floor(rotate * COUNTS_PER_INCH);
            rightInches = Math.floor(-rotate * COUNTS_PER_INCH);
            
            frontLeftTarget  = (int) (frontLeftMotor.getCurrentPosition()  - leftInches);
            frontRightTarget = (int) (frontRightMotor.getCurrentPosition() + rightInches);
            backLeftTarget   = (int) (backLeftMotor.getCurrentPosition()   - leftInches);
            backRightTarget  = (int) (backRightMotor.getCurrentPosition()  + rightInches);
            
            backLeftMotor.setTargetPosition(backLeftTarget);
            backRightMotor.setTargetPosition(backRightTarget);
            frontLeftMotor.setTargetPosition(frontLeftTarget);
            frontRightMotor.setTargetPosition(frontRightTarget);
            
            backLeftMotor.setTargetPositionTolerance(30);
            backRightMotor.setTargetPositionTolerance(30);
            frontLeftMotor.setTargetPositionTolerance(30);
            frontRightMotor.setTargetPositionTolerance(30);

            frontRightMotor.setPower(0.3);
            backRightMotor.setPower(0.3);
            frontLeftMotor.setPower(0.3);
            backLeftMotor.setPower(0.3);
            
            backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            
            telemetry.addLine("rotate is running");
        }
        if (shoot > 0)
        {
            leftInches  = Math.floor(shoot * COUNTS_PER_INCH);

            launcherTarget = (int) (launcher.getCurrentPosition() + leftInches);

            launcher.setTargetPosition(launcherTarget);
            launcher.setTargetPositionTolerance(1000);
            launcher.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            launcher.setVelocity(1300);
        }
        if (launcher.isBusy()){
            sleep(3000);
            windmill.setPower(0.4);
            telemetry.update();
        }
        while (opModeIsActive() && (backRightMotor.isBusy() || frontRightMotor.isBusy()) || (backLeftMotor.isBusy() || frontLeftMotor.isBusy()) || launcher.isBusy()) {
            telemetry.addData("frontLeftPos", frontLeftMotor.getCurrentPosition());
            telemetry.addData("frontRightPos", frontRightMotor.getCurrentPosition());
            telemetry.addData("backLeftPos", backLeftMotor.getCurrentPosition());
            telemetry.addData("backRightPos", backRightMotor.getCurrentPosition());
            telemetry.addData("launcherPos", launcher.getCurrentPosition());
            telemetry.addData("backRightTarget", backRightMotor.getTargetPosition());
            telemetry.addData("frontLeftTarget", frontLeftMotor.getTargetPosition());
            telemetry.addData("frontRightTarget", frontRightMotor.getTargetPosition());
            telemetry.addData("backLeftTarget", backLeftMotor.getTargetPosition());
            telemetry.addData("launcherTarget", launcher.getTargetPosition());
            telemetry.update();
        }
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        launcher.setVelocity(0);
        windmill.setPower(0);
        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        launcher.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        
    }
}
    
