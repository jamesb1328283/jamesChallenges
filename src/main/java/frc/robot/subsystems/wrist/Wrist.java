package frc.robot.subsystems.wrist;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {
  // For instructions on how to implement this class, refer to the README.md file
  // no idiot

  public WristIO m_io;
  public WristInputsAutoLogged m_inputs;
  private PIDController m_PIDController;

  public Wrist(WristIO io, PIDController controller) {
    m_io = io;
    m_PIDController = controller;
    m_inputs = new WristInputsAutoLogged();
  }

  @Override
  public void periodic() {
    m_io.updateInputs(m_inputs);
    double PID = m_PIDController.calculate(m_io.getAngle().getRadians());
    m_io.setVoltage(PID);
    ;
  }

  public void setDesiredAngle(Rotation2d angle) {
    m_PIDController.setSetpoint(angle.getRadians());
  }

  public Command setDesiredAngleCommand(Rotation2d angle) {
    return Commands.runOnce(
        () -> {
          setDesiredAngle(angle);
        });
  }

  public boolean withinTolerance() {
    return m_PIDController.atSetpoint();
  }

  public WristInputsAutoLogged getInputs() {
    return m_inputs;
  }
}
