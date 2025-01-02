package frc.robot.subsystems.arm;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class ArmIOKraken implements ArmIO {
  // For instructions on how to implement this class, refer to the README.md file
  private TalonFX m_motor;

  public ArmIOKraken(int port) {
    m_motor = new TalonFX(port);
  }

  @Override
  public void setVoltage(double voltage) {
    m_motor.setVoltage(voltage);
  }

  @Override
  public double getVoltage() {
    return m_motor.getSupplyVoltage().getValueAsDouble();
  }

  @Override
  public double getVelocityRadiansPerSecond() {
    double radiansPerSecond = m_motor.getVelocity().getValueAsDouble();
    return Units.rotationsToRadians(radiansPerSecond);
  }

  @Override
  public Rotation2d getPosition() {
    double position = m_motor.getRotorPosition().getValueAsDouble();
    return Rotation2d.fromRotations(position);
  }

  @Override
  public Object getMotor() {
    // DO NOT MODIFY THIS METHOD
    // shut up idiot dummy
    return m_motor;
  }
}
