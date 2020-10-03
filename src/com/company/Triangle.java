package com.company;

import java.text.DecimalFormat;

public class Triangle implements ITriangle {
    protected Point point_one;
    protected Point point_two;
    protected Point point_three;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        point_one = new Point(x1, y1);
        point_two = new Point(x2, y2);
        point_three = new Point(x3, y3);
    }

    public Triangle(Point pointOne, Point pointTwo, Point pointThree) {
        point_one = pointOne;
        point_two = pointTwo;
        point_three = pointThree;
    }

    public Point getPoint_one() {
        return point_one;
    }

    public Point getPoint_two() {
        return point_two;
    }

    public Point getPoint_three() {
        return point_three;
    }

    public double find_first_side() {
        return Math.round(Math.sqrt((Math.pow((point_two.getX() - point_one.getX()), 2) + (Math.pow((point_two.getY() - point_one.getY()), 2)))) * 100.0) / 100.0;
    }

    public double find_second_side() {
        return Math.round(Math.sqrt((Math.pow((point_three.getX() - point_one.getX()), 2) + (Math.pow((point_three.getY() - point_one.getY()), 2)))) * 100.0) / 100.0;
    }

    public double find_third_side() {
        return Math.round(Math.sqrt((Math.pow((point_three.getX() - point_two.getX()), 2) + (Math.pow((point_three.getY() - point_two.getY()), 2)))) * 100.0) / 100.0;
    }

    public boolean check_existence() {
        return (((find_first_side() + find_second_side()) > find_third_side())
                && ((find_second_side() + find_third_side()) > find_first_side())
                && ((find_first_side() + find_third_side()) > find_second_side()));
    }

    public double find_perimeter() {
        return (find_first_side() + find_second_side() + find_third_side());
    }

    public double find_area() {
        return (Math.abs((point_two.getX() - point_one.getX()) * (point_three.getY() - point_one.getY()) - (point_three.getX() - point_one.getX()) * (point_two.getY() - point_one.getY())) / 2);
    }

    public double find_median() {
        return 0.5 * Math.round((Math.sqrt((2 * (Math.pow(find_second_side(), 2))) + (2 * (Math.pow(find_third_side(), 2))) - (Math.pow(find_first_side(), 2)))) * 10.0) / 10;
    }

    public double find_first_angle() {
        return (((Math.pow(find_first_side(), 2)) + (Math.pow(find_third_side(), 2)) - (Math.pow(find_second_side(), 2))) / (2 * (find_first_side()) * (find_third_side())));
    }

    public double find_second_angle() {
        return (((Math.pow(find_first_side(), 2)) + (Math.pow(find_second_side(), 2)) - (Math.pow(find_third_side(), 2))) / (2 * (find_first_side()) * (find_second_side())));
    }

    public double find_third_angle() {
        return (((Math.pow(find_second_side(), 2)) + (Math.pow(find_third_side(), 2)) - (Math.pow(find_first_side(), 2))) / (2 * (find_third_side()) * (find_second_side())));
    }

    DecimalFormat df = new DecimalFormat("##.##");
    DecimalFormat dff = new DecimalFormat("##.###");

    @Override
    public String toString() {
        return ("The fist side of triangle: " + df.format(find_first_side()) + "; the second side: " + df.format(find_second_side()) +
                "; the third side: " + df.format(find_third_side()) + "; the median: " + df.format(find_median()) + "; the perimeter: " +
                df.format(find_perimeter()) + "; the area: " + df.format(find_area()) + "; the first angle: " + dff.format(find_first_angle()) +
                "; the second angle: " + dff.format(find_second_angle()) + "; the third angle: " + dff.format(find_third_angle()) + "\n");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass())
            return false;

        return this.point_one.equals(((Triangle) obj).getPoint_one())
                && this.point_two.equals(((Triangle) obj).getPoint_two())
                && this.point_three.equals(((Triangle) obj).getPoint_three());
    }
}
