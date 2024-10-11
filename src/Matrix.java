import interfaces.MatrixInterface;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// Task 1
public class Matrix implements MatrixInterface {
    private final int rows;
    private int columns;
    private double[][] matrix;

    private final Scanner scanner = new Scanner(System.in);

    // Task 2
    public Matrix() {
        this.rows = 0;
        this.columns = 0;
        this.matrix = new double[0][0];
        System.out.println("Створено пусту матрицю.");
    }

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
    }

    public Matrix(Matrix matrix) {
        this.rows = matrix.rows;
        this.columns = matrix.columns;
        this.matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.matrix[i][j] = matrix.matrix[i][j];
            }
        }
        System.out.println("\nСтворено копію матриці:");
        printMatrix();
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // Task 3
    private void fillConsole() {
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Елемент[" + i + "][" + j + "] = ");
                matrix[i][j] = scanner.nextDouble();
            }
        }
        System.out.println("\nМатрицю створено: ");
        printMatrix();
    }

    private void fillRandom() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = -20 + random.nextDouble() * 40;
            }
        }
        System.out.println("\nМатрицю заповнено випадковими значеннями:");
        printMatrix();
    }

    public void fill() {
        System.out.println("\nОберіть варіант заповнення матриці: \n1. Заповнити матрицю з консолі; \n2. Заповнити матрицю випадковими значеннями.");
        System.out.print("\nВаріант: ");
        int variant = scanner.nextInt();
        if (variant == 1) fillConsole();
        else fillRandom();
    }

    // Task 4
    private double getElement() {
        System.out.print("Введіть індекс рядка: ");
        int row = scanner.nextInt();
        System.out.print("Введіть індекс стовпчика: ");
        int column = scanner.nextInt();
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            System.out.print("Не існує елемента з цими індексами.");
            return Double.NaN;
        }
        double element = matrix[row][column];
        System.out.printf("Елемент за індексом [%d][%d]: %.2f\n", row, column, element);
        return element;
    }

    private double[] getRow() {
        System.out.print("Введіть індекс рядка: ");
        int row = scanner.nextInt();
        if (row < 0 || row >= rows) {
            System.out.print("Не існує рядка з цим індексом.");
            return null;
        }
        System.out.printf("Рядок за індексом [%d]:\n", row);
        printArray(matrix[row], false);
        return matrix[row];
    }

    private double[] getColumn() {
        System.out.print("Введіть індекс стовпчика: ");
        int column = scanner.nextInt();
        if (column < 0 || column >= columns) {
            System.out.print("Не існує стовпчика з цим індексом.");
            return null;
        }
        double[] col = new double[rows];
        for (int i = 0; i < rows; i++) {
            col[i] = matrix[i][column];
        }
        System.out.printf("Стовпчик за індексом [%d]:\n", column);
        printArray(col, true);
        return col;
    }

    private void printArray(double[] array, boolean isColumn) {
        String separator = isColumn ? "\n" : " ";
        for (double value : array) {
            System.out.printf("%.2f%s", value, separator);
        }
        System.out.println();
    }

    public void getPart() {
        System.out.println("\nОберіть варінт для повернення частини матриці: \n1. Елемент матриці; \n2. Рядок матриці; \n3. Стовпчик матриці.");
        System.out.print("\nВаріант: ");
        int variant = scanner.nextInt();
        if (variant == 1) getElement();
        else if (variant == 2) getRow();
        else if (variant == 3) getColumn();
    }

    // Task 5
    public int[] getSize() {
        System.out.printf("Розмірність матриці: %dx%d", rows, columns);
        return new int[] { rows, columns };
    }

    // Task 6
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matrix other = (Matrix) obj;
        if (this.rows != other.rows || this.columns != other.columns) return false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (this.matrix[i][j] != other.matrix[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }

    // Task 8
    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            System.out.println("Розмірність матриць різна.");
        }
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
            }
        }
        System.out.println("\nРезультат додавання матриць: ");
        printMatrix();
        return this;
    }

    public Matrix multiplyByScalar(double scalar) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.matrix[i][j] = this.matrix[i][j] * scalar;
            }
        }
        System.out.println("\nРезультат множення матриці на скаляр: ");
        printMatrix();
        return this;
    }

    // Task 9
    public Matrix multiply(Matrix other) {
        if (this.columns != other.rows) {
            System.out.println("Кількість стовпців першої матриці повинна дорівнювати кількості рядків другої матриці.");
        }
        double[][] result = new double[this.rows][other.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    result[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        this.matrix = result;
        this.columns = other.columns;
        System.out.println("\nРезультат множення матриць: ");
        printMatrix();
        return this;
    }

    // Task 10
    public Matrix transpose() {
        Matrix transposed = new Matrix(this.columns, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                transposed.matrix[j][i] = this.matrix[i][j];
            }
        }
        System.out.println("\nТранспонована матриця: ");
        transposed.printMatrix();
        return transposed;
    }

    // Task 11
    public static Matrix fromVector(double[] vector) {
        int size = vector.length;
        Matrix diagonalMatrix = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            diagonalMatrix.matrix[i][i] = vector[i];
        }
        System.out.println("\nДіагональна матриця: ");
        diagonalMatrix.printMatrix();
        return diagonalMatrix;
    }

    // Task 12
    public static Matrix identity(int size) {
        Matrix identityMatrix = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            identityMatrix.matrix[i][i] = 1;
        }
        System.out.println("\nОдинична матриця: ");
        identityMatrix.printMatrix();
        return identityMatrix;
    }

    // Task 13
    public static Matrix createRowMatrix(int columns) {
        Matrix rowMatrix = new Matrix(1, columns);
        Random random = new Random();
        for (int j = 0; j < columns; j++) {
            rowMatrix.matrix[0][j] = random.nextDouble() * 40 - 20;
        }
        System.out.println("\nМатриця-строка, заповнена випадковими значеннями: ");
        rowMatrix.printArray(rowMatrix.matrix[0], false);
        return rowMatrix;
    }

    // Task 14
    public static Matrix createColumnMatrix(int rows) {
        Matrix columnMatrix = new Matrix(rows, 1);
        Random random = new Random();
        double[] toPrint = new double[rows];
        for (int i = 0; i < rows; i++) {
            columnMatrix.matrix[i][0] = random.nextDouble() * 40 - 20;
            toPrint[i] = columnMatrix.matrix[i][0];
        }
        System.out.println("\nМатриця-стовпчик, заповнена випадковими значеннями: ");
        columnMatrix.printArray(toPrint, true);
        return columnMatrix;
    }

    // Task 15
    public Matrix toLowerTriangular() {
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < columns; j++) {
                matrix[i][j] = 0;
            }
        }
        System.out.println("\nНижня трикутна матриця: ");
        printMatrix();
        return this;
    }

    public Matrix toUpperTriangular() {
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < rows; j++) {
                if (j > i) matrix[j][i] = 0;
            }
        }
        System.out.println("\nВерхня трикутна матриця: ");
        printMatrix();
        return this;
    }

    // Task 16
    public Matrix invert() {
        if (rows != columns) {
            System.out.println("Матриця повинна бути квадратною.");
        }
        Matrix augmented = new Matrix(rows, columns * 2);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                augmented.matrix[i][j] = this.matrix[i][j];
                augmented.matrix[i][j + columns] = (i == j) ? 1 : 0;
            }
        }

        for (int i = 0; i < rows; i++) {
            double pivot = augmented.matrix[i][i];
            if (pivot == 0) {
                System.out.println("Матриця не має оберненої (її детермінант дорівнює 0).");
            }
            for (int j = 0; j < 2 * columns; j++) {
                augmented.matrix[i][j] /= pivot;
            }
            for (int k = 0; k < rows; k++) {
                if (k != i) {
                    double factor = augmented.matrix[k][i];
                    for (int j = 0; j < 2 * columns; j++) {
                        augmented.matrix[k][j] -= factor * augmented.matrix[i][j];
                    }
                }
            }
        }
        Matrix invertedMatrix = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                invertedMatrix.matrix[i][j] = augmented.matrix[i][j + columns];
            }
        }
        System.out.println("\nОбернена матриця: ");
        invertedMatrix.printMatrix();
        return invertedMatrix;
    }
}
