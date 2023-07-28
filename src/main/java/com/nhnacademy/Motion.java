package com.nhnacademy;

public class Motion {

    private int dX;
    private int dY;

    private Motion(int dX, int dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public static Motion createPosition(int dX, int dY) { // 좌표계 위치를 기준으로 생성
        return new Motion(dX, dY);
    }

    public static Motion createDisplacement(int magnitude, int theta) { // 크기와 각도로 생성
        return new Motion((int) (magnitude * Math.cos(Math.toRadians(theta))),
                (int) (magnitude * Math.sin(Math.toRadians(theta))));
    }

    protected int getDX() {
        return dX;
    }

    protected void setDX(int dX) {
        this.dX = dX;
    }

    protected int getDY() {
        return dY;
    }

    protected void setDY(int dY) {
        this.dY = dY;
    }

    protected int getMegnitude() {
        return (int) (Math.sqrt(Math.pow(getDX(), 2) + Math.pow(getDY(), 2)));
    }

    protected int getTheta() {
        return (int) Math.toDegrees(Math.atan2(getDY(), getDX()));
    }

    protected void add(Motion motion) {
        this.dX = dX + motion.getDX();
        this.dY = dY + motion.getDY();
    }
}
