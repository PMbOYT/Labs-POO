#include "Student.h"

Student::Student(string id, string name, string email)
    : studentID(id), name(name), email(email) {}

string Student::getID() const {
    return studentID;
}

string Student::getName() const {
    return name;
}

string Student::getEmail() const {
    return email;
}

bool Student::hasGraduated() const {
    return isGraduated;
}

void Student::graduate() {
    isGraduated = true;
}

void Student::addCredits(int value) {
    credits += value;
}

Student& Student::operator++() {
    credits++; // Increment credits
    return *this;
}

Student& Student::operator--() {
    credits--; // Decrement credits
    return *this;
}

