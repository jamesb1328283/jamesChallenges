// Copyright 2021-2024 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.oi.DriverControls;
import frc.robot.oi.DriverControlsXbox;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.FlywheelIONeo;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
@SuppressWarnings("unused")
public class RobotContainer {
  // For instructions on how to implement this class, refer to the README.md file

  private DriverControls m_driverControls;
  private Flywheel m_Flywheel;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureSubsystems();
    configureControllers();
    configureButtonBindings();
  }

  private void configureSubsystems() {
    m_Flywheel =
        new Flywheel(new FlywheelIONeo(FlywheelConstants.kMotorPort),new PIDController(FlywheelConstants.kP, FlywheelConstants.kI, FlywheelConstants.kD));
  }

  private void configureControllers() {
    m_driverControls = new DriverControlsXbox(1);
  }
  /*
  This method should set up the button bindings for the Flywheel subsystem.
  DriverControls has already been implemented and instantiated in RobotContainer.
   It has one method: runFlywheel(), which returns a Trigger.

   You must bind the Trigger to the Flywheel subsystem's setDesiredVelocityCommand() method
           WHEN the button is initially pressed, {
                the Command object returned by setDesiredVelocityCommand() is scheduled with the desired velocity set to
                Constants.FlywheelConstants.kVelocitySetpoint.
          }
          WHEN the button is released, {
                the setDesiredVelocityCommand() is scheduled with the desired velocity set to 0.0.
          }

    Each of these commands should be run a single time when the button is pressed or released.

    holy yap fest bruh i aint readin allat

   */
  private void configureButtonBindings() {
    m_driverControls
        .runFlywheel()
        .onTrue(Commands.runOnce(() -> m_Flywheel.setDesiredVelocity(Constants.FlywheelConstants.kVelocitySetpoint)))
        .onFalse(Commands.runOnce(() -> m_Flywheel.setDesiredVelocity(0)));
  }
}