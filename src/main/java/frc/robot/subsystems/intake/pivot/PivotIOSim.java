package frc.robot.subsystems.intake.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.Constants.IntakeConstants;

public class PivotIOSim implements PivotIO {

  private SingleJointedArmSim m_sim;
  private double m_voltage;

  public PivotIOSim() {
    m_sim =
        new SingleJointedArmSim(
            DCMotor.getNEO(1),
            IntakeConstants.kPivotGearing,
            IntakeConstants.kPivotJKgMetersSquared,
            IntakeConstants.kPivotLength,
            IntakeConstants.kPivotMinAngle,
            IntakeConstants.kPivotMaxAngle,
            false,
            Units.degreesToRadians(120));
  }

  @Override
  public void updateInputs(PivotInputs inputs) {
    m_sim.update(0.02);

    inputs.voltage = getVoltage();
    inputs.velocityRadPerSec = getVelocityRadPerSec();
    inputs.angleRad = getAngle().getRadians();
  }

  @Override
  public void setVoltage(double voltage) {
    m_sim.setInputVoltage(voltage);
    m_voltage = voltage;
  }

  @Override
  public double getVoltage() {
    return m_voltage;
  }

  @Override
  public double getVelocityRadPerSec() {
    return m_sim.getVelocityRadPerSec();
  }

  @Override
  public Rotation2d getAngle() {
    return new Rotation2d(m_sim.getAngleRads());
  }
}
