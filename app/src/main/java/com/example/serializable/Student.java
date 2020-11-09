package com.example.serializable;

import java.io.Serializable;

public class Student implements Serializable {
    //transient 关键字可将该属性不保留
    private String name;
    private int age;
    private Score score;

    public Student(String name, int age, Score score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    static class Score implements Serializable{
        private int math;
        private int english;
        private String grade;

        public Score(int math, int english) {
            this.math = math;
            this.english = english;
            if (math>=90&&english>=90){
                grade = "学霸";
            }else {
                grade = "学渣";
            }
        }

        public int getMath() {
            return math;
        }

        public void setMath(int math) {
            this.math = math;
        }

        public int getEnglish() {
            return english;
        }

        public void setEnglish(int english) {
            this.english = english;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }
}
