public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(4, 4);
        matrix.fill();
//        Matrix matrix1 = new Matrix(matrix);
//        matrix.toLowerTriangular();
//        matrix1.toUpperTriangular();
        matrix.invert();
    }
}
