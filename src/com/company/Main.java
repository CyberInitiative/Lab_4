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

        TriangleDB generalTrinagle = new TriangleDB();
        EquliteralDB equilateral_triangles = new EquliteralDB();

        System.out.println("Hello! Please, enter 'Y' if you want to work with current DB or enter 'N' if you want to choose another one;");

        Scanner input = new Scanner(System.in);
        String chosenVariant1 = input.nextLine().toUpperCase();

        switch (chosenVariant1) {
            case "Y":
                generalTrinagle.add(0, 2, 4, 0, 0, -3);
                generalTrinagle.add(1, 1, 4, 4, 0, 0); //does not exist
                generalTrinagle.add(-2, 3, 2, -3, -5.19615, -3.4641); // equilateral_triangle
                generalTrinagle.add(0, 5, 0, 0, -2, 0);
                generalTrinagle.add(-6, 0, 0, 0, -3, -5.19615); // equilateral_triangle
                generalTrinagle.add(0, 2, 4, 0, 0, -3);
                generalTrinagle.add(0, 2, 4, 0, 0, -3);
                generalTrinagle.add(1, 1, 3, 3, 0, 0); //does not exist
                generalTrinagle.add(0, 6, 4, 0, -5, 0);
                generalTrinagle.add(1, 1, 4, 4, 0, 0); //does not exist
                generalTrinagle.add(1, 1, 2, 2, 0, 0); //does not exist

                if (!generalTrinagle.trianglesList.isEmpty()) {
                    generalTrinagle.save("General_Triangles_Reserve" + formatter.format(date) + ".txt");
                    System.out.println("Reserve copy for general triangles has been created;");
                }
                if (!equilateral_triangles.equilTrianglesList.isEmpty()) {
                    equilateral_triangles.save("Eq_Triangles_Reserve" + formatter.format(date) + ".txt");
                    System.out.println("Reserve copy for equilateral triangles has been created;");
                }

                generalTrinagle.trianglesList.removeIf(triangle -> !triangle.check_existence());

                System.out.println("The list of triangles: ");
                System.out.println(generalTrinagle);

                for (int i = 0; i < generalTrinagle.trianglesList.size(); i++) {
                    Triangle currentTriangle = generalTrinagle.trianglesList.get(i);
                    for (int j = i + 1; j < generalTrinagle.trianglesList.size(); j++) {
                        if (currentTriangle.equals(generalTrinagle.trianglesList.get(j))) {
                            System.out.println("Triangle " + (i + 1) + " equals to triangle " + (j + 1));
                        } //Отсчет идет на человеческий, то есть треугольник [0] для нас первый.
                    }
                }
                System.out.println("");
                for (Triangle triangle : generalTrinagle.trianglesList) {
                    try {
                        equilateral_triangles.add(triangle.getPoint_one(), triangle.getPoint_two(), triangle.getPoint_three());
                    } catch (RuntimeException ex) {
                        System.out.println(generalTrinagle.trianglesList.indexOf(triangle) + ex.getMessage());
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

                equilateral_triangles.save("Eq_Triangles.txt");
                generalTrinagle.save("General_Triangles.txt");
                break;
            case "N":
                System.out.println("Press 'Y' if you want to load reserve copy, press 'N' if you want to choose another file;");
                Scanner scanner4 = new Scanner(System.in);
                String chosenVariant3 = scanner4.nextLine().toUpperCase();
                switch (chosenVariant3) {
                    case "Y":
                        String filePath = "E:\\Users\\Мирослав Левдиков\\IdeaProjects\\Lab_3";
                        File directory = new File(filePath);
                        String[] reserveFiles = directory.list(new FilenameFilter() {
                            @Override
                            public boolean accept(File dir, String name) {
                                return name.contains("Reserve");
                            }
                        });
                        if(reserveFiles != null && reserveFiles.length > 0)
                        {
                            System.out.println("The reserve copy list:");
                            for (int i = 0; i < reserveFiles.length; i++) {
                                System.out.println((i) + " " + reserveFiles[i]);
                            }
                            System.out.println("Enter the number of copy you want to load;");
                            Scanner scanner5 = new Scanner(System.in);
                            String chosenCopy = scanner5.nextLine();
                            if (Integer.parseInt(chosenCopy) < reserveFiles.length && Integer.parseInt(chosenCopy) >= 0 ) {
                                generalTrinagle.load(reserveFiles[Integer.parseInt(chosenCopy)]);
                                System.out.println(generalTrinagle);
                            }
                            else
                                System.out.println("Error.");
                        }
                        else
                            System.out.println("There are no backups in the folder.");
                        break;
                    case "N":
                        System.out.println("Press 'Y' if you want to work with general triangles list, press 'N' if you want to skip;");
                        Scanner scanner = new Scanner(System.in);
                        String chosenVariant2 = scanner.nextLine().toUpperCase();
                        switch (chosenVariant2) {
                            case "Y":
                                System.out.println("Enter the name of file;");
                                Scanner scanner1 = new Scanner(System.in);
                                String filename1 = scanner1.nextLine();
                                generalTrinagle.load(filename1);
                                System.out.println(generalTrinagle);
                                generalTrinagle.save("Save_g_1");
                            case "N":
                                System.out.println("Press 'Y' if you want to work with equilateral triangles list, press 'N' if you want to skip;");
                                Scanner scanner2 = new Scanner(System.in);
                                String chosenVariant4 = scanner2.nextLine().toUpperCase();
                                switch (chosenVariant4) {
                                    case "Y":
                                        System.out.println("Enter the name of file");
                                        Scanner scanner3 = new Scanner(System.in);
                                        String filename2 = scanner3.nextLine();
                                        equilateral_triangles.load(filename2);
                                        System.out.println(equilateral_triangles);
                                        equilateral_triangles.save("Save_eq_1");
                                    case "N":
                                        System.out.println("Shut down.");
                                        break;
                                    default:
                                        System.out.println("Error, wrong symbol. Shut down.");
                                        break;
                                }
                                break;
                        }
                        break;
                    default:
                        System.out.println("Error, wrong symbol. Shut down.");
                        break;
                }
                break;
            default:
                System.out.println("Error, wrong symbol. Shut down.");
                break;
        }
    }
}

