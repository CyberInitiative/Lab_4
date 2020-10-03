package com.company;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EquliteralDB implements IArray{
    public ArrayList<EquilateralTriangle> equilTrianglesList = new ArrayList<>();
    public void add(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.equilTrianglesList.add(new EquilateralTriangle(x1, y1, x2, y2, x3, y3));
    }

    public void add(Point point_one, Point point_two,Point point_three){
        this.equilTrianglesList.add(new EquilateralTriangle(point_one, point_two, point_three));
    }

    public Triangle get(int index) {
        return this.equilTrianglesList.get(index);
    }

    public Triangle remove(int index) {
        return this.equilTrianglesList.remove(index);
    }

    public void clear(){
        this.equilTrianglesList.clear();
    }

    @Override
    public String toString() {
        return equilTrianglesList.toString();
    }

    public void save(String filename) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(outStream);
        for (Triangle triangle : equilTrianglesList) {
            try {
                bufferedWriter.write(String.valueOf(triangle.point_one.getX()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(triangle.point_one.getY()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(triangle.point_two.getX()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(triangle.point_two.getY()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(triangle.point_three.getX()));
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(String.valueOf(triangle.point_three.getY()));
                bufferedWriter.write(System.lineSeparator());
            } catch (IOException IOexception) {
                System.out.println("An I / O error has occurred");
            }
        }
        bufferedWriter.close();
        outStream.close();
    }

    public void load(String filename) throws IOException{
        this.clear();
        Scanner scanner = new Scanner(new FileReader(filename));
        double x1;
        double y1;
        double x2;
        double y2;
        double x3;
        double y3;
        while (scanner.hasNextLine()) {
            x1 = Double.parseDouble(scanner.nextLine());
            y1 = Double.parseDouble(scanner.nextLine());
            x2 = Double.parseDouble(scanner.nextLine());
            y2 = Double.parseDouble(scanner.nextLine());
            x3 = Double.parseDouble(scanner.nextLine());
            y3 = Double.parseDouble(scanner.nextLine());
            this.equilTrianglesList.add(new EquilateralTriangle(x1, y1, x2, y2, x3, y3));
        }
        scanner.close();
    }
}
