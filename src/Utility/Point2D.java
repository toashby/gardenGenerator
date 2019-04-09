package Utility;

public class Point2D {
    public int x;
    public int y;
    public float fx;
    public float fy;

    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point2D(float v, float v1) {
        this.fx = x;
        this.fy = y;
    }

    public Point2D(Point2D p){
        this.x = p.x;
        this.y = p.y;
    }

}
