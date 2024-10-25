public class Main {
    public static void main(String[] args) {
        ImmutableMatrix matrix = new ImmutableMatrix(3, 3);
        matrix.fill();
        MutableMatrix matrix1 = new MutableMatrix(matrix);
        Matrix matrix2 =  matrix1.invert();
        matrix.multiply(matrix2);
    }
}
