import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MentalHealth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Java Array to hold mood tracking scores for 7 days
        int[] weeklyMoodHistory = new int[7];
        int dayCounter = 0; // Tracks which day (index 0 to 6) we are logging

        boolean running = true;

        System.out.println("=========================================");
        System.out.println("   Welcome to Your AI Health Companion   ");
        System.out.println("=========================================");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Talk to your AI Companion");
            System.out.println("2. View Weekly Mood History Array");
            System.out.println("3. Get Immediate Crisis Helplines");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the scanner buffer

            if (choice == 1) {
                System.out.print("\nExpress your feelings or physical state: ");
                String userMessage = scanner.nextLine();

                try {
                    // Launch python script via ProcessBuilder
                    // Note: Use "python3" instead of "python" if you are on Mac/Linux
                   ProcessBuilder pb = new ProcessBuilder("C:\\Users\\STUDENT\\AppData\\Local\\Programs\\Python\\Python313\\python.exe", "companion.py",userMessage);
                    Process process = pb.start();

                    // Read what Python prints back to us
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String pythonOutput = reader.readLine();

                    if (pythonOutput != null && pythonOutput.contains("||")) {
                        // Split Python's response into [Mood Score, Text Message]
                        String[] parts = pythonOutput.split("\\|\\|");
                        int rawMoodScore = Integer.parseInt(parts[0]);
                        String companionReply = parts[1];
                        String polarityInfo = parts.length > 2 ? parts[2] : "";
                        String subjectivityInfo = parts.length > 3 ? parts[3] : "";

                        System.out.println("\n[Companion]: " + companionReply);
                        System.out.println("[AI Sentiment Analysis] " + polarityInfo + " | " + subjectivityInfo);
                        // Print the AI companion's reply
                       

                        // If the user is in severe distress (Score 5), print local emergency info
                        if (rawMoodScore == 5) {
                            System.out.println("\n🚨 EMERGENCY RESOURCE: Please call Tele-MANAS at 14416 (Free 24/7 National Mental Health Helpline in India).");
                        }

                        // Store the mood score inside our Java Array
                        if (dayCounter < 7) {
                            weeklyMoodHistory[dayCounter] = rawMoodScore;
                            System.out.println("*(Logged mood level " + rawMoodScore + " into array index [" + dayCounter + "])*");
                            dayCounter++;
                        } else {
                            // If array is full, shift everything left to make room for day 7 (FIFO structure)
                            for (int i = 1; i < 7; i++) {
                                weeklyMoodHistory[i - 1] = weeklyMoodHistory[i];
                            }
                            weeklyMoodHistory[6] = rawMoodScore;
                            System.out.println("*(History updated in array)*");
                        }
                    } else {
                        System.out.println("\n[Error]: Could not parse the Python AI response properly.");
                    }

                } catch (Exception e) {
                    System.out.println("\n[Error]: Failed to communicate with Python. Make sure Python is installed and companion.py is in the same folder.");
                }

            } else if (choice == 2) {
                System.out.println("\n--- Your Weekly Mood Array History ---");
                System.out.print("[ ");
                for (int i = 0; i < weeklyMoodHistory.length; i++) {
                    System.out.print(weeklyMoodHistory[i] + " ");
                }
                System.out.println("]");
                System.out.println("(Scale: 1 = Great, 2 = Neutral, 3 = Anxious, 4 = Physical Pain/Sad, 5 = Distress)");

            } else if (choice == 3) {
                System.out.println("\n=========================================");
                System.out.println("         EMERGENCY SUPPORT INFO          ");
                System.out.println("=========================================");
                System.out.println("• Kiran Mental Health Helpline: 1800-599-0019");
                System.out.println("• Tele-MANAS Helpline: 14416 or 1800-891-4416");
                System.out.println("• Tele-MANAS is free, confidential, and open 24/7.");
                System.out.println("=========================================");

            } else if (choice == 4) {
                running = false;
                System.out.println("\nGoodbye! Take care of yourself.");
            } else {
                System.out.println("\nInvalid choice. Please pick from options 1-4.");
            }
        }
        scanner.close();
    }
}
