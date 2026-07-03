A Java-Python integrated console application that provides mood tracking and empathetic, AI-driven responses using Natural Language Processing (NLP) sentiment analysis.

📌 Overview
This project analyzes a user's emotional state from natural language input using TextBlob's NLP sentiment analysis, combined with domain-specific keyword boosting for context-sensitive scenarios (like exam stress or physical illness). It logs mood history and provides mental health crisis support resources.

✨ Features
AI-Powered Sentiment Analysis — Uses TextBlob NLP library to calculate polarity and subjectivity scores from user input
Java-Python Integration — Java communicates with a Python NLP script via ProcessBuilder
Mood Tracking — Logs daily mood scores into a Java array with a 7-day rolling history (FIFO structure)
Crisis Detection — Identifies high-risk language and immediately surfaces emergency helpline information
Emergency Helplines — Displays verified Indian mental health helplines (Kiran, Tele-MANAS)
🛠️ Tech Stack
Java — Core application logic, user interaction, array-based data storage, exception handling
Python — NLP processing via TextBlob
TextBlob — Sentiment polarity/subjectivity analysis
🚀 How to Run
Prerequisites
Java JDK installed
Python 3.x installed
TextBlob library: pip install textblob
Steps
javac MentalHealth.java
java MentalHealth
📊 Mood Scale
Score	Meaning
1	Positive / Great
2	Neutral
3	Anxious / Stressed
4	Physical Pain / Sad
5	Crisis / Distress
🆘 Emergency Resources (India)
Kiran Mental Health Helpline: 1800-599-0019
Tele-MANAS Helpline: 14416 / 1800-891-4416 (Free, 24/7)
📝 Note
This tool is designed for awareness and educational purposes only. It is not a substitute for professional mental health care.

👤 Author
Thangam S K
