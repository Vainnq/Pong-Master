package pongMaster;

import java.util.Random;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that reflects a ball.
 */

public class Ball {
	private double ballX;
	private double ballY;
	private double xSpeed;
	private double ySpeed;
	private double speed;
	private final double radius = 7.5;
	
	protected Ball(){
		ballX = PongMaster.gameXandYsize / 2;
		ballY = PongMaster.gameXandYsize * 0.6;
		speed = 0.25;
		xSpeed = speed;
		ySpeed = speed;
		xSpeed = new Random().nextInt(2) == 0 ? 1: -1;
		ySpeed = new Random().nextInt(2) == 0 ? 1: -1;
	}
	protected void moveX(double x){
		ballX = x;
	}
	protected void moveY(double y){
		ballY = y;
	}
	protected void increaseSpeed(){
		if(xSpeed < 5 && xSpeed > -5) {
			xSpeed = (double) (Math.signum(xSpeed) * (Math.abs(xSpeed) + speed));
			ySpeed = (double) (Math.signum(ySpeed) * (Math.abs(ySpeed) + speed));
		}
	}
	protected void changeXdirection(){
		xSpeed *= -1;
	}
	protected void changeYdirection(){
		ySpeed *= -1;
	}
	protected double getX() {
		return ballX;
	}
	protected double getY() {
		return ballY;
	}
	protected double getXspeed() {
		return xSpeed;
	}
	protected double getYspeed() {
		return ySpeed;
	}
	protected double getRadius() {
		return radius;
	}
}
