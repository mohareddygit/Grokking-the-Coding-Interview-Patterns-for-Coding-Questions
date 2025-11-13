package A4Merge_Intervals;

// Problem Statement: Conflicting Appointments (medium)
// LeetCode Question: 1353. Maximum Number of Events That Can Be Attended

/**
 * To find all conflicting appointments in
 * Java, the most efficient approach for a large set of appointments is to sort the appointments by their start times and then iterate through the sorted list, comparing each appointment only with the relevant subsequent ones.
 * The brute-force method of comparing every pair of appointments works but has a time complexity of O(n²), which is inefficient for large datasets. The sorting approach reduces the complexity to O(n log n).
 *
 */

import java.util.*;

public class Problem_4_ConflictingAppointments {
    static class Appointment {
        int start;
        int end;
        String title; // Optional title for clarity

        Appointment(int start, int end, String title) {
            this.start = start;
            this.end = end;
            this.title = title;
        }

        @Override
        public String toString() {
            return title + " [" + start + ", " + end + "]";
        }
    }
    public class AppointmentScheduler {

        public static List<String> findAllConflicts(List<Appointment> appointments) {
            if (appointments == null || appointments.isEmpty()) {
                return Collections.emptyList();
            }

            // 1. Sort appointments by start time
            appointments.sort(Comparator.comparingInt(a -> a.start));

            List<String> conflicts = new ArrayList<>();

            // 2. Iterate and check for overlaps
            // For each appointment, compare it with all subsequent appointments that start before it ends
            for (int i = 0; i < appointments.size(); i++) {
                Appointment current = appointments.get(i);

                // Compare the current appointment with all subsequent appointments
                for (int j = i + 1; j < appointments.size(); j++) {
                    Appointment next = appointments.get(j);

                    // Check if they conflict: A conflict happens if the next appointment
                    // starts before the current one ends
                    if (next.start < current.end) {
                        conflicts.add("Conflict: \"" + current.title + "\" and \"" + next.title + "\" overlap.");

                        // Note: This continues the inner loop to find *all* conflicts for the 'current' appointment.
                        // If you only need to know *if* a conflict exists, you could break here.
                    }
                    // Since the list is sorted, if the next appointment starts after the current one ends,
                    // no subsequent appointments will conflict with the *current* one either, so we can stop this inner loop.
                    else {
                        break;
                    }
                }
            }

            return conflicts;
        }

        public static void main(String[] args) {
            List<Appointment> schedule = Arrays.asList(
                    new Appointment(1, 5, "Meeting A"),
                    new Appointment(2, 3, "Meeting B"),
                    new Appointment(4, 6, "Meeting C"),
                    new Appointment(7, 8, "Meeting D"),
                    new Appointment(5, 9, "Meeting E")
            );

            List<String> conflictList = findAllConflicts(schedule);

            System.out.println("Conflicts Found:");
            for (String conflict : conflictList) {
                System.out.println(conflict);
            }
            // Expected Output (order may vary based on comparison logic):
            // Conflict: "Meeting A" and "Meeting B" overlap.
            // Conflict: "Meeting A" and "Meeting C" overlap.
            // Conflict: "Meeting C" and "Meeting E" overlap.
            // Conflict: "Meeting E" and "Meeting C" overlap. (might be listed once depending on exact implementation)
        }
    }


}
