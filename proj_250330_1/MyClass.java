package proj_250330_1;

public class MyClass {
    private static int numberOfStudents = 0;

    MyClass () {
        numberOfStudents++;
    }

    public static void details() {
        System.out.println(
                "=========================\n" +
                "     My CLassDetails     \n" +
                "=========================\n" +
                "Number of students : " + numberOfStudents);
    }
}