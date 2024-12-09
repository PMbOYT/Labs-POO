#include <iostream>
#include "University.h"
#include "Faculty.h"
#include "Student.h"
using namespace std;

int main() {
    University tum; // Create University

    // Create faculties
    Faculty ft("Food Technology");
    Faculty cs("Computer Science");

    // Add faculties to university
    tum.addFaculty(ft);
    tum.addFaculty(cs);

    // Create students
    Student s1("123", "Alice", "alice@tum.md");
    Student s2("124", "Bob", "bob@tum.md");

    // Enroll students
    ft.addStudent(s1);
    cs.addStudent(s2);

    // Display faculties
    cout << "Faculties in the university: " << endl;
    for (const auto& faculty : tum.getFaculties()) {
        cout << faculty.getName() << endl;
    }

    // Display enrolled students in Food Technology faculty
    cout << "\nEnrolled students in Food Technology: " << endl;
    for (const auto& student : ft.getEnrolledStudents()) {
        cout << student.getName() << " (" << student.getEmail() << ")" << endl;
    }

    // Graduate student Alice
    ft.graduateStudent("123");

    // Display graduates in Food Technology
    cout << "\nGraduates in Food Technology: " << endl;
    for (const auto& student : ft.getEnrolledStudents()) {
        if (student.hasGraduated()) {
            cout << student.getName() << endl;
        }
    }

    return 0;
}

