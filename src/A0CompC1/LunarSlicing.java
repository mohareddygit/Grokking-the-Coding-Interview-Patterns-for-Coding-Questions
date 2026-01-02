package A0CompC1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LunarSlicing {
    public static void main(String[] args) {
        // Formatted input strings: MM/DD/YYYY (Indices: M=0,1; D=3,4; Y=6-9)
        String[] dateStrings = {
            "01/29/2025", // Reference New Moon
            "03/14/2025", // Target: Eclipse
            "09/07/2025"  // Target: Eclipse
        };

        // Extract reference date from the first element
        int refM = Integer.parseInt(dateStrings[0].substring(0, 2));
        int refD = Integer.parseInt(dateStrings[0].substring(3, 5));
        int refY = Integer.parseInt(dateStrings[0].substring(6, 10));
        LocalDate refDate = LocalDate.of(refY, refM, refD);

        double synodicMonth = 29.53059;

        // Loop through the array to process each string
        for (String dateStr : dateStrings) {
            // Slicing to extract substrings and converting to numbers
            int m = Integer.parseInt(dateStr.substring(0, 2));
            int d = Integer.parseInt(dateStr.substring(3, 5));
            int y = Integer.parseInt(dateStr.substring(6, 10));

            LocalDate targetDate = LocalDate.of(y, m, d);
            long deltaDays = ChronoUnit.DAYS.between(refDate, targetDate);

            // Calculate Month (whole cycles) and Day (remainder)
            int lunarMonth = (int) (deltaDays / synodicMonth) + 1;
            
            int lunarDay = (int) (deltaDays % synodicMonth);
            System.out.println("Date: " + dateStr + " -> Lunar Day: " + lunarDay);

            /*Array Tip
            If you are using this lunarMonth result to pull a name from an array
            (e.g., String[] names = {"Wolf Moon", "Snow Moon"}),
            you actually don't want the +1 because array indices start at 0.
             */
            String[] monthNames = {"Wolf Moon", "Snow Moon", "Worm Moon"};
            int monthIndex = (int) (deltaDays / synodicMonth); // March 14 gives index 1
            System.out.println("Current Moon: " + monthNames[monthIndex]); // Prints "Snow Moon"
        }
    }
}