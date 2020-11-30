import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Stack;

public class Main {

    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintStream out = System.out;

    public static void main(String[] args) {
        int[][] matrix = generate_random_matrix(ask_matrix_size());

        print_matrix(matrix);

        int x_coordinate = ask_x_coordinate(matrix);
        int y_coordinate = ask_y_coordinate(x_coordinate, matrix);

        out.printf("Value in (%d, %d) = %d\n", x_coordinate, y_coordinate, matrix[x_coordinate][y_coordinate]);

        int zeros_in_matrix = count_zeros_from_coord(x_coordinate, y_coordinate, matrix, 0);

        out.printf("Number of Zeros From Limit (%d, %d) = %d\n", x_coordinate, y_coordinate, zeros_in_matrix);

    }

    private static void print_matrix(int[][] matrix)
    {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                out.printf("%d\t", anInt);
            }
            out.print("\n");
        }
    }
    private static int get_random_int(){
        return (int) (Math.floor(Math.random() * 10));
    }

    private static int ask_x_coordinate(int[][] matrix)
    {
        int x_coordinate = -1;
        boolean repeat_flow = true;
        while(repeat_flow) {
            out.print("X Coordinate: ");
            try {
                x_coordinate = Integer.parseInt(in.readLine());

                if (x_coordinate < 0) throw new IllegalArgumentException();

                if (matrix.length < x_coordinate) throw new ArithmeticException();
                repeat_flow = false;
            } catch (IOException ioEx) {
                out.println("The value must be a number");
            } catch (IllegalArgumentException iaE) {
                out.println("The value must be a positive number");
            } catch(ArithmeticException aE){
                out.println("X Coordinate doesn't exist in the matrix");
            }
            catch (Exception e) {
                out.println("Something happen in the system... try again.");
                e.printStackTrace();
            }
        }

        return x_coordinate;
    }

    private static int ask_y_coordinate(int x_coordinate, int[][] matrix)
    {
        int y_coordinate = -1;
        boolean repeat_flow = true;
        while(repeat_flow) {
            out.print("Y Coordinate: ");
            try {
                y_coordinate = Integer.parseInt(in.readLine());

                if (y_coordinate < 0) throw new IllegalArgumentException();

                if (matrix[x_coordinate].length < y_coordinate) throw new ArithmeticException();
                repeat_flow = false;
            } catch (IOException ioEx) {
                out.println("The value must be a number");
            } catch (IllegalArgumentException iaE) {
                out.println("The value must be a positive number");
            } catch(ArithmeticException aE){
                out.printf("Y Coordinate doesn't exist in the matrix in the X position = %d\n", x_coordinate);
            }
            catch (Exception e) {
                out.println("Something happen in the system... try again.");
                e.printStackTrace();
            }
        }

        return y_coordinate;
    }

    private static int ask_matrix_size(){
        out.print("Matrix Size\n>>> ");
        int size = 0;
        try{
            size = Integer.parseInt(in.readLine());

            if(size < 0) throw new IllegalArgumentException();
        }catch(IOException ioEx){
            out.println("The value must be a number");
        }catch(IllegalArgumentException iaE){
            out.println("The value must be a positive number");
        }catch(Exception e){
            out.println("Something happen in the system... try again.");
            e.printStackTrace();
        }
        return size;
    }

    private static int[][] generate_random_matrix(int matrixSize){
        int[][] matrix = new int[matrixSize][matrixSize];
        //Coordinate X
        int counter = 0;
        for(int x = 0; x < matrix.length; x++){
            //Coordinate Y
            for(int y = 0; y < matrix[x].length; y++){
                matrix[x][y] = get_random_int();
                counter++;
            }
        }

        return matrix;
    }

    private static int count_zeros_from_coord(int xPos, int yPos, int[][] matrix, int ...counter)
    {
        //Stop.
        if(xPos == 0 && yPos == 0) {
            return counter[0];
        }else{
            if(yPos > 0){
                if(matrix[xPos][yPos] == 0){
                    counter[0]++;
                }
                return count_zeros_from_coord(xPos, yPos - 1, matrix, counter[0]);
            }else {
                return count_zeros_from_coord(xPos -1, matrix[xPos].length -1, matrix, counter[0]);
            }
        }
    }

}
