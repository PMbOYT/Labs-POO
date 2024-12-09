#ifndef FACULTY_H
#define FACULTY_H

#include <vector>
#include <string>
#include "Student.h"
using namespace std;

class Faculty {
private:
    string facultyName;
    vector<Student> students;

public:
    Faculty(string name);

    string getName() const;

    void addStudent(const Student& student);
    void graduateStudent(const string& studentID);
    vector<Student> getEnrolledStudents() const;
};

#endif // FACULTY_H
