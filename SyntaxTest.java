package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Autonomous(name = "test (Blocks to Java)")
public class test extends LinearOpMode {

  private DcMotor backLeftMotor;
  private DcMotor backRightMotor;

  double COUNTS_PER_INCH;
  ElapsedTime runtime;

  /**
   * This OpMode illustrates the concept of driving a path based on encoder counts.
   * This OpMode requires that you have encoders on the wheels, otherwise you would use RobotAutoDriveByTime.
   * This OpMode also requires that the drive Motors have been configured such that a
   * positive power command moves them forward, and causes the encoders to count up.
   *
   * The desired path in this example is:
   *   - Drive forward for 48 inches
   *   - Spin right for 12 Inches
   *   - Drive backward for 24 inches
   *
   * This OpMode has a function called encoderDrive that performs the actual movement.
   * This function assumes that each movement is relative to the last stopping place.
   * There are other ways to perform encoder based moves, but this method is probably the simplest.
   * This OpMode uses the RUN_TO_POSITION mode.
   */
  @Override
  public void runOpMode() {
    int COUNTS_PER_MOTOR_REV;
    int DRIVE_GEAR_REDUCTION;
    int WHEEL_DIAMETER_INCHES;
    double DRIVE_SPEED;
    double TURN_SPEED;

    backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
    backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");

    // Calculate the COUNTS_PER_INCH for your specific drive train.
    // Go to your motor vendor website to determine your motor's COUNTS_PER_MOTOR_REV.
    // For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
    // For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
    // This is gearing down for less speed and more torque.
    // For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation.
    COUNTS_PER_MOTOR_REV = 1440;
    DRIVE_GEAR_REDUCTION = 1;
    WHEEL_DIAMETER_INCHES = 4;
    COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);
    DRIVE_SPEED = 0.6;
    TURN_SPEED = 0.5;
    runtime = new ElapsedTime();
    // To drive forward, most robots need the motor on one side to be reversed,
    // because the axles point in opposite directions.
    // When run, this OpMode should start both motors driving forward.
    // So adjust the motor directions based on your first test drive.
    // Note: The settings here assume direct drive on left and right wheels.
    // Gear Reduction or 90 Deg drives may require direction flips.
    backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    backRightMotor.setDirection(DcMotor.Direction.FORWARD);
    backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    // Send telemetry message to indicate successful Encoder reset.
    telemetry.addData("Starting at", JavaUtil.formatNumber(backLeftMotor.getCurrentPosition(), 7, 0) + " : " + JavaUtil.formatNumber(backRightMotor.getCurrentPosition(), 7, 0));
    telemetry.update();
    // Wait for the game to start (driver presses START).
    waitForStart();
    // Step through each leg of the path.
    // Note: Reverse movement is obtained by setting a negative distance (not speed).
    // S1: Forward 48 inches with 5 second timeout.
    encoderDrive(DRIVE_SPEED, 48, 48, 5);
    // S2: Turn right 12 inches with 4 second timeout.
    encoderDrive(TURN_SPEED, 12, -12, 4);
    // S3: Reverse 24 inches with 4 second timeout.
    encoderDrive(DRIVE_SPEED, -24, -24, 4);
    telemetry.addData("Path", "Complete");
    telemetry.update();
    // Pause to display final telemetry message.
    sleep(1000);
  }

  /**
   * Function to perform a relative move, based on encoder counts.
   * Encoders are not reset as the move is based on the current position.
   * Move will stop if any of three conditions occur:
   * 1) Move gets to the desired position
   * 2) Move runs out of time
   * 3) Driver stops the OpMode running.
   */
  private void encoderDrive(double speed, int leftInches, int rightInches, int timeoutS) {
    double newLeftTarget;
    double newRightTarget;

    // Ensure that the OpMode is still active.
    if (opModeIsActive()) {
      // Determine new target position, and pass to motor controller.
      newLeftTarget = backLeftMotor.getCurrentPosition() + Math.floor(leftInches * COUNTS_PER_INCH);
      newRightTarget = backRightMotor.getCurrentPosition() + Math.floor(rightInches * COUNTS_PER_INCH);
      backLeftMotor.setTargetPosition((int) newLeftTarget);
      backRightMotor.setTargetPosition((int) newRightTarget);
      // Turn On RUN_TO_POSITION.
      backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      // Reset the timeout time and start motion.
      runtime.reset();
      backLeftMotor.setPower(Math.abs(speed));
      backRightMotor.setPower(Math.abs(speed));
      // Keep looping while we are still active, and there is time left, and both motors are running.
      // Note: We use (isBusy() and isBusy()) in the loop test, which means that when EITHER motor hits
      // its target position, the motion will stop.  This is "safer" in the event that the robot will
      // always end the motion as soon as possible.
      // However, if you require that BOTH motors have finished their moves before the robot continues
      // onto the next step, use (isBusy() or isBusy()) in the loop test.
      while (opModeIsActive() && runtime.seconds() < timeoutS && backLeftMotor.isBusy() && backRightMotor.isBusy()) {
        // Display it for the driver.
        telemetry.addData("Running to", JavaUtil.formatNumber(newLeftTarget, 7, 0) + " :" + JavaUtil.formatNumber(newRightTarget, 7, 0));
        telemetry.addData("Currently at", JavaUtil.formatNumber(backLeftMotor.getCurrentPosition(), 7, 0) + " :" + JavaUtil.formatNumber(backRightMotor.getCurrentPosition(), 7, 0));
        telemetry.update();
      }
      // Stop all motion.
      backLeftMotor.setPower(0);
      backRightMotor.setPower(0);
      // Turn off RUN_TO_POSITION.
      backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      // Optional pause after each move.
      sleep(250);
    }
  }
}
