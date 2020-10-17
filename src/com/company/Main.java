package com.company;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("_dd-MM-yy_HH-mm-ss");

        final String FilePath = System.getProperty("java.io.tmpdir") + "\\IdeaProjects\\Lab3";
        var tmpDir = new File(FilePath);
        if (!tmpDir.exists())
            tmpDir.mkdirs();

        TriangleDB generalTriangle = new TriangleDB();
        EquliteralDB equilateral_triangles = new EquliteralDB();

        System.out.println("Hello! Please, enter 'Y' if you want to work with current DB or enter 'N' if you want to choose another one;");

        Scanner input = new Scanner(System.in);
        String chosenVariant1 = input.nextLine().toUpperCase();

        switch (chosenVariant1) {
            case "Y":
                generalTriangle.add(0, 2, 4, 0, 0, -3);
                generalTriangle.add(1, 1, 4, 4, 0, 0);
                generalTriangle.add(-2, 3, 2, -3, -5.19615, -3.4641); // equilateral_triangle
                generalTriangle.add(0, 5, 0, 0, -2, 0);
                generalTriangle.add(-6, 0, 0, 0, -3, -5.19615); // equilateral_triangle
                generalTriangle.add(0, 2, 4, 0, 0, -3);
                generalTriangle.add(0, 2, 4, 0, 0, -3);
                generalTriangle.add(1, 1, 3, 3, 0, 0); //does not exist
                generalTriangle.add(0, 6, 4, 0, -5, 0);
                generalTriangle.add(1, 1, 4, 4, 0, 0);
                generalTriangle.add(1, 1, 2, 2, 0, 0); //does not exist

                if (!generalTriangle.trianglesList.isEmpty()) {
                    generalTriangle.serialize(FilePath + "\\Initial_Triangles_Reserve_Serialize" + formatter.format(date) + ".txt");
                    System.out.println("Initial reserve serialization for triangles has been created;");
                    generalTriangle.JKSerialize(FilePath + "\\Initial_Triangles_JSON_Reserve_Serialize" + formatter.format(date) + ".json");
                    System.out.println("Initial reserve JSON serialization for triangles has been created;");
                }

                generalTriangle.trianglesList.removeIf(triangle -> !triangle.check_existence());

                System.out.println("The list of triangles: ");
                System.out.println(generalTriangle);

                for (int i = 0; i < generalTriangle.trianglesList.size(); i++) {
                    Triangle currentTriangle = generalTriangle.trianglesList.get(i);
                    for (int j = i + 1; j < generalTriangle.trianglesList.size(); j++) {
                        if (currentTriangle.equals(generalTriangle.trianglesList.get(j))) {
                            System.out.println("Triangle " + (i + 1) + " equals to triangle " + (j + 1));
                        } //Отсчет идет на человеческий, то есть треугольник [0] для нас первый.
                    }
                }
                System.out.println("");
                for (Triangle triangle : generalTriangle.trianglesList) {
                    try {
                        equilateral_triangles.add(triangle.getPoint_one(), triangle.getPoint_two(), triangle.getPoint_three());
                    } catch (RuntimeException ex) {
                        System.out.println(generalTriangle.trianglesList.indexOf(triangle) + ex.getMessage());
                    }
                }
                System.out.println("");
                if (equilateral_triangles.equilTrianglesList.size() > 0) {
                    System.out.println("The list of equilateral triangles: ");

                    System.out.println(equilateral_triangles);
                    EquilateralTriangle equilateral_triangle = Collections.min(equilateral_triangles.equilTrianglesList, Comparator.comparing(s -> s.find_median()));
                    System.out.println("The lowest value among medians: " + equilateral_triangle.find_median());
                } else {
                    System.out.println("No equilateral triangles exist.");
                }

                for (EquilateralTriangle equilateralTriangle : equilateral_triangles.equilTrianglesList) {
                    try {
                        generalTriangle.add(equilateralTriangle.getPoint_one(), equilateralTriangle.getPoint_two(), equilateralTriangle.getPoint_three());
                    } catch (RuntimeException ex) {
                        System.out.println(equilateral_triangles.equilTrianglesList.indexOf(equilateralTriangle) + ex.getMessage());
                    }
                }
                System.out.println("Finite reserve serialization for triangles has been created;");
                generalTriangle.serialize(FilePath + "\\Finite_Triangles_Reserve_Serialize" + formatter.format(date) + ".txt");
                System.out.println("Finite JSON reserve serialization for triangles has been created;");
                generalTriangle.JKSerialize(FilePath + "\\Finite_Triangles_JSON_Reserve_Serialize" + formatter.format(date) + ".json");
                break;
            case "N":
                File directory = new File(FilePath);
                String[] reserveFiles = directory.list(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.contains("Reserve");
                    }
                });
                if (reserveFiles != null && reserveFiles.length > 0) {
                    System.out.println("The reserve copy list:");
                    for (int i = 0; i < reserveFiles.length; i++) {
                        System.out.println((i) + " " + reserveFiles[i]);
                    }
                    System.out.println("Enter the number of copy you want to load;");
                    Scanner scanner1 = new Scanner(System.in);
                    String chosenCopy = scanner1.nextLine();
                    if (Integer.parseInt(chosenCopy) < reserveFiles.length && Integer.parseInt(chosenCopy) >= 0) {
                        if(reserveFiles[Integer.parseInt(chosenCopy)].endsWith(".txt")) {
                            generalTriangle.deserialize(FilePath + "\\" + reserveFiles[Integer.parseInt(chosenCopy)]);
                        }
                        if(reserveFiles[Integer.parseInt(chosenCopy)].endsWith(".json")) {
                            generalTriangle.JKDeserialize(FilePath + "\\" + reserveFiles[Integer.parseInt(chosenCopy)]);
                        }
                        System.out.println(generalTriangle);
                    } else
                        System.out.println("Error.");
                } else
                    System.out.println("There are no backups in the folder.");
                break;
            default:
                System.out.println("Error, wrong symbol. Shut down.");
                break;
        }
    }
}