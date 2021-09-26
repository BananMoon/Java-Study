package comuStudy_days10;

public class day13 {
    public static void main(String[] args) {

        class Student {
            String name;
            int score1;
            int score2;
            int score3;

            Student(String name, int score1, int score2, int score3) {
                this.name = name;
                this.score1 = score1;
                this.score2 = score2;
                this.score3 = score3;
            }

            public float getAverage() {
                return ((score1 + score2 + score3) / (float) 3);
            }
        }

        Student student1 = new Student("코뮤", 100, 80, 75);
        Student student2 = new Student("김변수", 96, 58, 90);
        System.out.printf("코뮤의 평균 점수: %f\n", student1.getAverage());
        System.out.printf("김변수의 평균 점수: %f", student2.getAverage());
    }
}
