/*
* This is a program that calculates mean, median and mode
* after reading in a text file into an array.
*
* @author  Haokai
* @version 1.0
* @since   2020-11-25
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* This is the statistics program.
*/
final class Statistics {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Statistics() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The mean() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the mean of the integers
    */
    public static double mean(final Integer[] arrayOfIntegers) {
        double meanNumber = 0;
        double answer = 0;
        final int numberLong = arrayOfIntegers.length;
        for (int i = 0; i < numberLong; i++) {
            meanNumber += arrayOfIntegers[i];
        }

        answer = meanNumber / numberLong;

        return answer;
    }

    /**
    * The median() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the median of the integers
    */
    public static double median(final Integer[] arrayOfIntegers) {
        double answer = 0;
        final int numberLongInt = arrayOfIntegers.length;
        final int numberLongIntHalf = numberLongInt / 2;
        final double numberLongDouble = arrayOfIntegers.length;
        final double numberLongDoubleHalf = numberLongDouble / 2;
        if (numberLongDoubleHalf == numberLongIntHalf) {
            answer = (arrayOfIntegers[numberLongIntHalf]
                + arrayOfIntegers[numberLongIntHalf - 1]) / 2;
        } else if (numberLongDoubleHalf != numberLongIntHalf) {
            answer = arrayOfIntegers[numberLongIntHalf];
        }

        return answer;
    }

    /**
    * The mode() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the mode of the integers
    */
    public static List<Integer> mode(Integer[] arrayOfIntegers) {
        final ArrayList<Integer> listSecond = new ArrayList<Integer>();
        final ArrayList<Integer> listanswer = new ArrayList<Integer>();
        final int numberLong = arrayOfIntegers.length;
        int max = 0;
        int rLoop = 0;
        int iLoop = 0;
        int eLoop = 0;
        int bLoop = 0;

        for (rLoop = 0; rLoop < numberLong; rLoop++) {
            listSecond.add(0);
        }

        for (iLoop = 0; iLoop < numberLong; iLoop++) {
            int timeNumber = 0;
            if (arrayOfIntegers[iLoop] == -1) {
                listSecond.add(0);
            } else {
                for (eLoop = iLoop + 1; eLoop < numberLong; eLoop++) {
                    if (arrayOfIntegers[iLoop] == arrayOfIntegers[eLoop]) {
                        timeNumber += 1;
                        arrayOfIntegers[eLoop] = -1;
                    }
                }
                listSecond.set(iLoop, timeNumber);
            }
        }

        for (bLoop = 0; bLoop < numberLong; bLoop++) {
            if (listSecond.get(bLoop) >= max) {
                max = listSecond.get(bLoop);
            }
        }

        for (int hLoop = 0; hLoop < numberLong; hLoop++) {
            if (listSecond.get(hLoop) == max) {
                listanswer.add(arrayOfIntegers[hLoop]);
            }
        }

        return listanswer;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        Integer tempNumber;
        final ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        final Path filePath = Paths.get("./", args[0]);
        final Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(
                                     filePath, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tempNumber = Integer.parseInt(line);
                listOfNumbers.add(tempNumber);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        final Integer[] arrayOfNumbers = listOfNumbers.toArray(
            new Integer[listOfNumbers.size()]);
        System.out.println(Arrays.toString(arrayOfNumbers));

        System.out.println("\nCalculating stats...");
        final double mean = mean(arrayOfNumbers);
        final double median = median(arrayOfNumbers);
        final List<Integer> mode = mode(arrayOfNumbers);

        System.out.println("The mean is: " + mean);
        System.out.println("The median is: " + median);
        System.out.println("The mode(s) is/are: "
                            + Arrays.toString(mode.toArray()));

        System.out.println("\nDone.");
    }
}
