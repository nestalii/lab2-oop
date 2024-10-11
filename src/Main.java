public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(3, 3);

        // Matrix class
        matrix.fill();
        Matrix matrix1 = new Matrix(matrix);
        Matrix matrix2 = new Matrix(matrix);
        Matrix matrix3 = new Matrix(matrix);
        Matrix matrix4 = new Matrix(matrix);
        matrix.getPart();
        matrix.getSize();
        matrix1.add(matrix2);
        matrix2.multiplyByScalar(2);
        matrix1.multiply(matrix2);
        matrix1.transpose();
        Matrix.fromVector(new double[] {3, 1, 7});
        Matrix.identity(3);
        Matrix.createRowMatrix(3);
        Matrix.createColumnMatrix(3);
        matrix3.toLowerTriangular();
        matrix4.toUpperTriangular();
        matrix.invert();

//        // Immutable class
//
//        Immutable immutable = new Immutable(3, 3);
//        immutable.fill();
//        Immutable immutable1 = new Immutable(immutable);
//        immutable.add(immutable1);
//        immutable.multiplyByScalar(2);
//        immutable.multiply(immutable1);
//        immutable.toLowerTriangular();
//        immutable.toUpperTriangular();
//
//        // Generic Matrix class
//
//        GenericMatrix<String> genericMatrix = new GenericMatrix<>(3, 3);
//        genericMatrix.fillFromConsole(String.class);
//        genericMatrix.printMatrix();
//        genericMatrix.printElement(1, 2);
    }
}
