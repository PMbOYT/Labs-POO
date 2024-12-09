#include "Faculty.h"
#include <algorithm>
#include <stdexcept>

Faculty::Faculty(string name) : facultyName(name) {}

string Faculty::getName() const {
    return facultyName;
}

void Faculty::addStudent(const Student& student) {
    students.push_back(student);
}

void Faculty::graduateStudent(const string& studentID) {
    auto it = find_if(students.begin(), students.end(),
                       [&](const Student& s) { return s.getID() == studentID; });
    if (it != students.end()) {
        it->graduate();
    } else {
        throw runtime_error("Student not found.");
    }
}

vector<Student> Faculty::getEnrolledStudents() const {
    vector<Student> enrolled;
    for (const auto& s : students) {
        if (!s.hasGraduated()) enrolled.push_back(s);
    }
    return enrolled;
}

