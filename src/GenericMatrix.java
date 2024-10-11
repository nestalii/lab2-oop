import java.math.BigDecimal;
import java.util.Scanner;

public class GenericMatrix<T> {
    private final int rows;
    private int columns;
    private T[][] matrix;

    private final Scanner scanner = new Scanner(System.in);

    @SuppressWarnings("unchecked")
    public GenericMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = (T[][]) new Object[rows][columns];
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void fillFromConsole(Class<T> type) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть елементи матриці розміром " + rows + "x" + columns + ":");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Елемент [" + i + "][" + j + "]: ");
                if (type == Integer.class) {
                    matrix[i][j] = type.cast(scanner.nextInt());
                } else if (type == Double.class) {
                    matrix[i][j] = type.cast(scanner.nextDouble());
                } else if (type == String.class) {
                    matrix[i][j] = type.cast(scanner.next());
                } else if (type == BigDecimal.class) {
                    matrix[i][j] = type.cast(scanner.nextBigDecimal());
                } else {
                    throw new IllegalArgumentException("Тип не підтримується");
                }
            }
        }
    }

    public T getElement(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            return matrix[row][col];
        } else {
            throw new IndexOutOfBoundsException("Неправильний індекс");
        }
    }

    public void printElement(int row, int col) {
        System.out.println("Елемент [" + row + "][" + col + "]: " + getElement(row, col));
    }

    public T[] getRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < rows) {
            return matrix[rowIndex];
        } else {
            throw new IndexOutOfBoundsException("Неправильний індекс рядка");
        }
    }

    public void printRow(int rowIndex) {
        T[] row = getRow(rowIndex);
        System.out.print("Рядок " + rowIndex + ": ");
        for (T element : row) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public T[] getColumn(int colIndex) {
        if (colIndex >= 0 && colIndex < columns) {
            T[] column = (T[]) new Object[rows];
            for (int i = 0; i < rows; i++) {
                column[i] = matrix[i][colIndex];
            }
            return column;
        } else {
            throw new IndexOutOfBoundsException("Неправильний індекс стовпця");
        }
    }

    public void printColumn(int colIndex) {
        T[] column = getColumn(colIndex);
        System.out.println("Стовпець " + colIndex + ": ");
        for (T element : column) {
            System.out.println(element);
        }
    }
}
