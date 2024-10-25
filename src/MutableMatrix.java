public class MutableMatrix extends Matrix {
    public MutableMatrix() {
        this.rows = 0;
        this.columns = 0;
        this.matrix = new double[0][0];
        System.out.println("Створено пусту матрицю.");
    }

    public MutableMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
    }

    public MutableMatrix(Matrix matrix) {
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
        Matrix.formatMatrix(this);
        System.out.println("\nРезультат множення матриць: ");
        printMatrix();
        return this;
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
}
