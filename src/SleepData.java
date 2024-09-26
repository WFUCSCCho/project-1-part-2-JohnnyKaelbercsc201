public class SleepData implements Comparable<SleepData> {
    //User ID, Age, Gender, Sleep Quality, Bedtime, Wake-up Time, Daily Steps, Calories Burned, Physical Activity, Dietary Habits, Sleep Disorders, Medication Usage
    private Integer userID;
    private Integer age;
    private String gender;
    private Integer sleepQuality;
    private String bedTime;
    private String wakeUpTime;
    private Integer dailySteps;
    private Integer caloriesBurned;
    private String physicalActivity;
    private String dietaryHabits;
    private String sleepDisorders;
    private String medicationUsage;

    //default constructor
    public SleepData() {
        this.userID = null;
        this.age = null;
        this.gender = null;
        this.sleepQuality = null;
        this.bedTime = null;
        this.wakeUpTime = null;
        this.dailySteps = null;
        this.caloriesBurned = null;
        this.physicalActivity = null;
        this.dietaryHabits = null;
        this.sleepDisorders = null;
        this.medicationUsage = null;
    }

    //Constructor for Sleep Data
    public SleepData(Integer userID, Integer age, String gender, Integer sleepQuality, String bedTime, String wakeUpTime, Integer dailySteps, Integer caloriesBurned, String physicalActivity, String dietaryHabits, String sleepDisorders, String medicationUsage) {
        this.userID = userID;
        this.age = age;
        this.gender = gender;
        this.sleepQuality = sleepQuality;
        this.bedTime = bedTime;
        this.wakeUpTime = wakeUpTime;
        this.dailySteps = dailySteps;
        this.caloriesBurned = caloriesBurned;
        this.physicalActivity = physicalActivity;
        this.dietaryHabits = dietaryHabits;
        this.sleepDisorders = sleepDisorders;
        this.medicationUsage = medicationUsage;
    }
    @Override
    public int compareTo(SleepData o) {
        return this.age.compareTo(o.age); //compareTo method for comparing Sleepers by Age
    }
    @Override
    public String toString() {
        return "User Id: " +userID + " Age: " +age + " Gender: " + gender + " Sleep Quality: " + sleepQuality + " Bed Time: " + bedTime + " Wake-Up Time: " + wakeUpTime + " Daily Steps: " + dailySteps + " Calories Burned: " + caloriesBurned + " Physical Activity: " + physicalActivity + " Dietary Habits: " + dietaryHabits + " Sleep Disorders: " + sleepDisorders + " Medication Usage: " + medicationUsage;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SleepData sleepData = (SleepData) o;
        return userID.equals(sleepData.userID);
    }
}
