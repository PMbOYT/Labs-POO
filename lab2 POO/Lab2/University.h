#ifndef UNIVERSITY_H
#define UNIVERSITY_H

#include <vector>
#include <string>
#include "Faculty.h"
using namespace std;

class University {
private:
    vector<Faculty> faculties;

public:
    void addFaculty(const Faculty& faculty);
    Faculty* findFacultyByName(const string& name);
    vector<Faculty> getFaculties() const;
};

#endif // UNIVERSITY_H

