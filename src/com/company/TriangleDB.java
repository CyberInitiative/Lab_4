package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class TriangleDB implements IArray, Serializable {
    public ArrayList<Triangle> trianglesList;

    public TriangleDB() {
        trianglesList = new ArrayList<>();
    }

    public void add(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.trianglesList.add(new Triangle(x1, y1, x2, y2, x3, y3));
    }

    public void add(Point point_one, Point point_two,Point point_three){
        this.trianglesList.add(new Triangle(point_one, point_two, point_three));
    }

    public Triangle get(int index) {
        return this.trianglesList.get(index);
    }

    public Triangle remove(int index) {
        return this.trianglesList.remove(index);
    }

    public void clear() {
        this.trianglesList.clear();
    }

    @Override
    public String toString() {
        return trianglesList.toString();
    }

    public void save(String filename) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(outStream);
        for (Triangle triangle : trianglesList) {
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

    public void load(String filename) throws IOException {
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
            this.trianglesList.add(new Triangle(x1, y1, x2, y2, x3, y3));
        }
        scanner.close();
    }

    public void serialize(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.trianglesList);
            out.close();
            fileOut.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void deserialize(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            this.trianglesList = (ArrayList<Triangle>) in.readObject();
            in.close();
            fileInputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Triangle class not found");
        }
    }

    public void JKSerialize(String filename) throws IOException {
        ObjectMapper objectMapperList = new ObjectMapper();
        objectMapperList.writeValue(new File(System.getProperty("java.io.tmpdir") + "\\IdeaProjects\\Lab3\\Triangles_Json_Reserve.json"), this);
    }

    public void JKDeserialize(String filename) throws IOException {
        TriangleDB trDB1 = new ObjectMapper().readValue(new File(filename), TriangleDB.class);
        this.trianglesList = trDB1.trianglesList;
    }
}