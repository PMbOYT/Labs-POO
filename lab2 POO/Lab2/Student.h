#ifndef STUDENT_H
#define STUDENT_H

#include <string>
using namespace std;

class Student {
private:
    string studentID;
    string name;
    string email;
    int credits = 0;
    bool isGraduated = false;

public:
    Student(string id, string name, string email);

    string getID() const;
    string getName() const;
    string getEmail() const;
    bool hasGraduated() const;

    void graduate();
    void addCredits(int value);

    // Overloaded operators for bonus exercise
    Student& operator++(); // Prefix increment
    Student& operator--(); // Prefix decrement
};

#endif // STUDENT_H
