#include "University.h"
#include <algorithm>

void University::addFaculty(const Faculty& faculty) {
    faculties.push_back(faculty);
}

Faculty* University::findFacultyByName(const string& name) {
    auto it = find_if(faculties.begin(), faculties.end(),
                       [&](const Faculty& f) { return f.getName() == name; });
    return it != faculties.end() ? &(*it) : nullptr;
}

vector<Faculty> University::getFaculties() const {
    return faculties;
}

