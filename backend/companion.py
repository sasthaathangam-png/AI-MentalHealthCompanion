import sys
from textblob import TextBlob

def analyze_mental_health(user_text):
    blob = TextBlob(user_text)
    polarity = blob.sentiment.polarity        # -1 (negative) to +1 (positive)
    subjectivity = blob.sentiment.subjectivity  # 0 (objective) to 1 (subjective)

    text = user_text.lower()

    replies = {
        5: "Please know that you are not alone and there is support available right now. Please reach out to a professional or a helpline immediately.",
        4: "I'm so sorry you're dealing with physical or emotional pain right now. Please be incredibly gentle with yourself and take things one breath at a time.",
        3: "It sounds like you're feeling anxious or stressed. Take a slow, deep breath. Inhale for 4 seconds, hold for 4, and exhale smoothly.",
        2: "Thanks for checking in. Even a neutral day is a valid day. Remember to drink some water and take short breaks.",
        1: "I'm really glad to hear that you're feeling good! Let's keep this positive momentum going today."
    }

    # Crisis keywords checked directly (safety-critical, cannot rely on sentiment alone)
    crisis_words = ["crisis", "harm", "suicide", "end it", "kill", "hopeless", "worthless"]
    if any(word in text for word in crisis_words):
        mood_score = 5
        reply = replies[5]
    elif polarity <= -0.5:
        mood_score = 4
        reply = replies[4]
    elif polarity < 0:
        mood_score = 3
        reply = replies[3]
    elif polarity == 0:
        mood_score = 2
        reply = replies[2]
    else:
        mood_score = 1
        reply = replies[1]

    print(f"{mood_score}||{reply}||Polarity:{round(polarity,2)}||Subjectivity:{round(subjectivity,2)}")

if __name__ == "__main__":
    if len(sys.argv) > 1:
        user_message = sys.argv[1]
        analyze_mental_health(user_message)
    else:
        print("2||No text provided, keeping things steady.||Polarity:0||Subjectivity:0")
