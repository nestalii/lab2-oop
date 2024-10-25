public class ImmutableMatrix extends Matrix {
    public ImmutableMatrix() {
        this.rows = 0;
        this.columns = 0;
        this.matrix = new double[0][0];
        System.out.println("Створено пусту матрицю.");
    }

    public ImmutableMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
    }

    public ImmutableMatrix(Matrix matrix) {
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

    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            System.out.println("Розмірність матриць різна.");
        }
        Matrix result = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
            }
        }
        System.out.println("\nРезультат додавання матриць: ");
        result.printMatrix();
        return result;
    }

    public Matrix multiplyByScalar(double scalar) {
        Matrix result = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.matrix[i][j] = this.matrix[i][j] * scalar;
            }
        }
        System.out.println("\nРезультат множення матриці на скаляр: ");
        result.printMatrix();
        return result;
    }

    // Task 9
    public Matrix multiply(Matrix other) {
        if (this.columns != other.rows) {
            System.out.println("Кількість стовпців першої матриці повинна дорівнювати кількості рядків другої матриці.");
        }
        Matrix result = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    result.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        Matrix.formatMatrix(result);
        System.out.println("\nРезультат множення матриць: ");
        result.printMatrix();
        return result;
    }

    // Task 15
    public Matrix toLowerTriangular() {
        Matrix result = new Matrix(this.rows, this.columns);
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < columns; j++) {
                result.matrix[i][j] = 0;
            }
        }
        System.out.println("\nНижня трикутна матриця: ");
        result.printMatrix();
        return result;
    }

    public Matrix toUpperTriangular() {
        Matrix result = new Matrix(this.rows, this.columns);
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < rows; j++) {
                if (j > i) result.matrix[j][i] = 0;
            }
        }
        System.out.println("\nВерхня трикутна матриця: ");
        result.printMatrix();
        return result;
    }
}
