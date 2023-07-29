#include <iostream>
#include <string>
#include <fstream>
#include <conio.h>
using namespace std;
class AdminS{
protected:
	string name, course, gender, address, email_id, contact_No;
	int rollNo;
public:
	void manueS();
	void getData();
	void Display();
	void update();
	void search();
	void deleted();
};
class AdminT{
protected:
	string Name, Course, Gender, Address, Email_id, Contact_No, salary;
	int teacherID;
public:
	void manueT();
	void getData();
	void Display();
	void update();
	void search();
	void deleted();
};
class student : public AdminS{
public:
	void data();
};
class teacher : public AdminS, AdminT{
public:
	void teachermanue();
	void showAll();
	void srch();
	void Teacher_rec();
};
class login :public AdminS,AdminT{
public:
	void loginManue();
	void Login();
	void registr();
	void forget();
};
//*********************
//******ADMINS CLASS DEFINATION******
//*********************
void AdminS::manueS(){
Start:
	int  choice;
	char x;
	system("cls");
	cout << "\t\t\t------------------------------------" << endl;
	cout << "\t\t\t|    STUDENT MANAGEMENT SYSTEM     | " << endl;
	cout << "\t\t\t------------------------------------" << endl;
	cout << "\t\t\t 1.Enter New Record" << endl;
	cout << "\t\t\t 2.Display Record" << endl;
	cout << "\t\t\t 3.Update Record" << endl;
	cout << "\t\t\t 4.Search Record" << endl;
	cout << "\t\t\t 5.Delete Record" << endl;
	cout << "\t\t\t 6.Exit" << endl;

	cout << "\t\t\t--------------" << endl;
	cout << "\t\t\tCHOOSE OPTION" << endl;
	cout << "\t\t\t--------------" << endl;
	cout << "Enter Your Choice: ";
	cin >> choice;
	switch (choice)
	{
	case 1:
		do
		{
			getData();
			cout << "\n\t\t\t Add Another Student Record (Y,N): ";
			cin >> x;
		} while (x == 'y' || x == 'Y');
		break;
	case 2:
		Display();
		break;
	case 3:
		do{
			update();
			cout << "\n\t\t\tDo You Want To Update Another Student Record (Y,N): ";
			cin >> x;
		} while (x == 'y' || x == 'Y');
		break;
	case 4:
		search();
		break;
	case 5:
		deleted();
		break;
	case 6:
		exit(0);
		break;
	default:
		cout << "\n\t\t\t Invalid Choice...Try Again:";
	}
	getch();
	goto Start;
}
void AdminS::getData()
{	system("cls");
	ofstream write;
	cout << "\n---------------------------------------------------------------------------";
	cout << "\n-----------------------------Add Student Details---------------------------";
	cout << "\n---------------------------------------------------------------------------";
	cout << "\n\t\t\tEnter Name Of Student: ";
	cin >> name;
	cout << "\n\t\t\tEnter Roll Number Of Student: ";
	cin >> rollNo;
	cout << "\n\t\t\tEnter Class Of Student: ";
	cin >> course;
	cout << "\n\t\t\tEnter Gender Of Student: ";
	cin >> gender;
	cout << "\n\t\t\tEnter Email ID Of Student: ";
	cin >> email_id;
	cout << "\n\t\t\tEnter Contact Number Of Student: ";
	cin >> contact_No;
	cout << "\n\t\t\tEnter Address Of Student: ";
	cin >> address;

	write.open("Student.txt", ios::app);
	write << name << endl;
	write << rollNo << endl;
	write << course << endl;
	write << gender << endl;
	write << email_id << endl;
	write << contact_No << endl;
	write << address << endl;
	write.close();
}
void AdminS::Display(){
	system("cls");
	int total = 0;
	ifstream read;

	cout << "\n---------------------------------------------------------------------------";
	cout << "\n------------------------------Student Details------------------------------";
	cout << "\n---------------------------------------------------------------------------";

	read.open("Student.txt");
	if (!read){
		cout << "\n\t\t\tNo Data is Present to Show: ";
		read.close();
	}
	else{
		read >> name >> rollNo >> course >> gender >> email_id >> contact_No >> address;
		while (!read.eof()){
			cout << "\n\t\t\t Student No." << ++total << endl;
			cout << "\t\t\t Name of Student: " << name << endl;
			cout << "\t\t\t Roll No. of Student: " << rollNo << endl;
			cout << "\t\t\t Course of Student:" << course << endl;
			cout << "\t\t\t gender of Student: " << gender << endl;
			cout << "\t\t\t Email ID OfStudent: " << email_id << endl;
			cout << "\t\t\t Contact No. Of Student: " << contact_No << endl;
			cout << "\t\t\t Address Of Student: " << address << endl;
			read >> name >> rollNo >> course >> email_id >> contact_No >> address;
		}
		if (total == 0){
			cout << "\n\t\t\tNo Data is Present to Show: ";
		}
	}
	read.close();
}
void AdminS::search(){
	system("cls");
	int id;
	int found = 0;
	cout << "\n---------------------------------------------------------------------------";
	cout << "\n-----------------------------Search Student Details---------------------------";
	cout << "\n---------------------------------------------------------------------------";

	cout << "\n\t\t\tEnter the roll number :" << endl;
	cin >> id;;
	if (id != rollNo){
		cout << "\n\t\t\tStudent Not found:";
	}
	ifstream read;

	read.open("Student.txt");
	read >> name;
	read >> rollNo;
	read >> course;
	read >> gender;
	read >> email_id;
	read >> contact_No;
	read >> address;
	
	while (!read.eof()){
		if (id == rollNo) {
			++found;
			cout << "\t\t\t Name of Student: " << name << endl;
			cout << "\t\t\t Roll No. of Student: " << rollNo << endl;
			cout << "\t\t\t Course of Student:" << course << endl;
			cout << "\t\t\t Gender of Student:" << gender << endl;
			cout << "\t\t\t Email ID OfStudent: " << email_id << endl;
			cout << "\t\t\t Contact No. Of Student: " << contact_No << endl;
			cout << "\t\t\t Address Of Student: " << address << endl;
		}
		read >> name;
		read >> rollNo;
		read >> course;
		read >> gender;
		read >> email_id;
		read >> contact_No;
		read >> address;
	}
	if (found == 0){
		cout << "\n\t\t\tNo such student present in the file: ";
	}
	read.close();
}
void AdminS::update() {
	system("cls");
	int id;
	int found = 0;
	cout << "\n------------------------------------------------------------------------------";
	cout << "\n-----------------------------Modify Student Details---------------------------";
	cout << "\n------------------------------------------------------------------------------";

	cout << "\n\t\t\tEnter Roll number :";
	cin >> id;
	ofstream write;
	ifstream read;
	write.open("temp.txt", ios::app);

	read.open("Student.txt");
	read >> name;
	read >> rollNo;
	read >> course;
	read >> gender;
	read >> email_id;
	read >> contact_No;
	read >> address;
	while (!read.eof()){
		if (id == rollNo) {
			++found;
			cout << "\n\t\t\tName of student:" << name << endl;
			cout << " \t\t\tRoll number of student  :" << rollNo << endl;
			cout << " \t\t\tClass of student:" << course << endl;
			cout << " \t\t\tGender of student  :" << gender << endl;
			cout << "\t\t\tEmail ID of student :" << email_id << endl;
			cout << " \n\t\t\tContact number of student:" << contact_No << endl;
			cout << "\t\t\t Address of student:" << address << endl;
			cout << " \n\n\t\t\tRoll number  : " << rollNo;

			cout << "\n\t\t\tEnter the Name of the student: ";
			cin >> name;
			cout << "\n\t\t\tEnter the Class of student ";
			cin >> course;
			cout << "\n\t\t\tEnter the Gender of student ";
			cin >> gender;
			cout << "\n\t\t\tEnter Email Id of student: ";
			cin >> email_id;
			cout << "\n\t\t\tEnter the contact Number of student: ";
			cin >> contact_No;
			cout << "\n\t\t\tEnter the address of Student: ";
			cin >> address;

			write << name << endl;
			write << rollNo << endl;
			write << course << endl;
			write << gender << endl;
			write << email_id << endl;
			write << contact_No << endl;
			write << address << endl;
			break;
		}
		else{
			cout << "\n\t\t\tStudent of this roll No. doesn't exist";
			break;
		}
		write << name << endl;
		write << rollNo << endl;
		write << course << endl;
		write << gender << endl;
		write << email_id << endl;
		write << contact_No << endl;
		write << address << endl;
		
		read >> name;
		read >> rollNo;
		read >> course;
		read >> gender;
		read >> email_id;
		read >> contact_No;
		read >> address;
	}
	if (found == 0){
		cout << "\n\t\t\tNo Data is Present in the file: ";
	}
	write.close();
	read.close();
	remove("Student.txt");
	rename("temp.txt", "Student.txt");
}
void AdminS::deleted()
{	system("cls");
	fstream read, write;
	int rollno;
	int found = 0;
	cout << "\n------------------------------------------------------------------------------";
	cout << "\n-----------------------------Delete student Details---------------------------";
	cout << "\n------------------------------------------------------------------------------";
	read.open("Student.txt");
	if (!read){
		cout << "\n\t\t\tNo Data is Present..";
	}
	else{
		cout << "\n Enter RollNo. of student which you want to delete data:";
		cin >> rollno;
		write.open("temp.txt", ios::app | ios::out);
		read >> name >> rollNo >> course >> gender >> email_id >> contact_No >> address;
		while (!read.eof()){
			if (rollno != rollNo){
				write << "  " << name << "  " << rollNo << "  " << course << "  " << "" << gender << email_id << "  " << contact_No << "  " << address << "\n";
			}
			read >> name >> rollNo >> course >> gender >> email_id >> contact_No >> address;
			found++;
		}
		if (found == 0){
			cout << "\n\t\t\t Student Roll No. not found....";
		}
	}
	if (rollno == rollNo){
		cout << "\n\t\t\tSuccsessfuly deleted:";
	}
	write.close();
	read.close();
	remove("Student.txt");
	rename("temp.txt", "Student.txt");
}
//*********************
//*****ADMINT CLASS DEFINATION*******
//*********************
void AdminT::manueT()
{
Start:
	int  choice;
	char x;
	system("cls");
	cout << "\t\t\t------------------------------------" << endl;
	cout << "\t\t\t|    STUDENT MANAGEMENT SYSTEM     | " << endl;
	cout << "\t\t\t------------------------------------" << endl;
	cout << "\t\t\t 1.Enter New Record" << endl;
	cout << "\t\t\t 2.Display Record" << endl;
	cout << "\t\t\t 3.Update Record" << endl;
	cout << "\t\t\t 4.Search Record" << endl;
	cout << "\t\t\t 5.Delete Record" << endl;
	cout << "\t\t\t 6.Exit" << endl;

	cout << "\t\t\t--------------" << endl;
	cout << "\t\t\tCHOOSE OPTION" << endl;
	cout << "\t\t\t--------------" << endl;
	cout << "Enter Your Choice: ";
	cin >> choice;
	switch (choice)
	{
	case 1:
		do{
			getData();
			cout << "\n\t\t\t Add Another teacher's Record (Y,N): ";
			cin >> x;
		} while (x == 'y' || x == 'Y');
		break;
	case 2:
		Display();
		break;
	case 3:
		do{
			update();
			cout << "\n\t\t\tDo You Want To Update Another teacher Record (Y,N): ";
			cin >> x;
		} while (x == 'y' || x == 'Y');
		break;
	case 4:
		search();
		break;
	case 5:
		deleted();
		break;
	case 6:
		exit(0);
		break;
	default:
		cout << "\n\t\t\t Invalid Choice...Try Again:";
	}
	getch();
	goto Start;
}
void AdminT::getData()
{	system("cls");
	ofstream write;
	cout << "\n---------------------------------------------------------------------------";
	cout << "\n-----------------------------ADD TEACHER'S DETAILS---------------------------";
	cout << "\n---------------------------------------------------------------------------";
	cout << "\n\t\t\tEnter Name Of teacher: ";
	cin >> Name;
	cout << "\n\t\t\tEnter ID Of teacher: ";
	cin >> teacherID;
	cout << "\n\t\t\tEnter Class Of teacher: ";
	cin >> Course;
	cout << "\n\t\t\tEnter Gender Of teacher: ";
	cin >> Gender;
	cout << "\n\t\t\tEnter Email ID Of teacher: ";
	cin >> Email_id;
	cout << "\n\t\t\tEnter Contact Number Of techer: ";
	cin >> Contact_No;
	cout << "\n\t\t\tEnter Address Of teacher: ";
	cin >> Address;
	cout << "\n\t\t\tEnter salary Of teacher: ";
	cin >> salary;

	write.open("teacher.txt", ios::app);
	write << Name << endl;
	write << teacherID << endl;
	write << Course << endl;
	write << Gender << endl;
	write << Email_id << endl;
	write << Contact_No << endl;
	write << Address << endl;
	write << salary << endl;
	write.close();
}
void AdminT::Display()
{	system("cls");
	int total = 0;
	ifstream read;
	cout << "\n---------------------------------------------------------------------------";
	cout << "\n------------------------------All Teacher's Details------------------------------";
	cout << "\n---------------------------------------------------------------------------";
	read.open("teacher.txt");
	if (!read){
		cout << "\n\t\t\tNo Data is Present to Show: ";
		read.close();
	}
	else{
		read >> Name >> teacherID >> Course >> Gender >> Email_id >> Contact_No >> Address >> salary;
		while (!read.eof()){
			cout << "\n\t\t\t Student No." << ++total << endl;
			cout << "\t\t\t Name of teacher: " << Name << endl;
			cout << "\t\t\t ID No. of teacher: " << teacherID << endl;
			cout << "\t\t\t Course of teacher:" << Course << endl;
			cout << "\t\t\t gender of teacher: " << Gender << endl;
			cout << "\t\t\t Email ID Of teacher: " << Email_id << endl;
			cout << "\t\t\t Contact No. Of teacher: " << Contact_No << endl;
			cout << "\t\t\t Address Of teacher: " << Address << endl;
			cout << "\t\t\t Address Of teacher: " << salary << endl;
			read >> Name >> teacherID >> Course >> Email_id >> Contact_No >> Address >> salary;
		}
		if (total == 0){
			cout << "\n\t\t\tNo Data is Present to Show: ";
		}
	}
	read.close();
}
void AdminT::search(){
	system("cls");
	int id;
	int found = 0;
	cout << "\n---------------------------------------------------------------------------";
	cout << "\n-----------------------------Search Teacher's Details---------------------------";
	cout << "\n---------------------------------------------------------------------------";

	cout << "\n\t\t\tEnter the Teacher's ID :" << endl;
	cin >> id;;
	if (id != teacherID){
		cout << "\n\t\t\tteacher Not found:";
	}

	ifstream read;

	read.open("teacher.txt");
	read >> Name;
	read >> teacherID;
	read >> Course;
	read >> Gender;
	read >> Email_id;
	read >> Contact_No;
	read >> Address;
	read >> salary;
	while (!read.eof()){
		if (id == teacherID) {
			++found;
			cout << "\t\t\t Name of teacher: " << Name << endl;
			cout << "\t\t\t ID No. of teacher: " << teacherID << endl;
			cout << "\t\t\t Course of teacher:" << Course << endl;
			cout << "\t\t\t Gender of teacher:" << Gender << endl;
			cout << "\t\t\t Email ID Of teacher: " << Email_id << endl;
			cout << "\t\t\t Contact No. Of teacher: " << Contact_No << endl;
			cout << "\t\t\t Address Of teacher: " << Address << endl;
			cout << "\t\t\t Address Of teacher: " << salary << endl;
		}
		read >> Name;
		read >> teacherID;
		read >> Course;
		read >> Gender;
		read >> Email_id;
		read >> Contact_No;
		read >> Address;
		read >> salary;
	}
	if (found == 0){
		cout << "\n\t\t\tNo such teacher present in the file: ";
	}
	read.close();
}
void AdminT::update() {
	system("cls");
	int id;
	int found = 0;
	cout << "\n------------------------------------------------------------------------------";
	cout << "\n-----------------------------Modify teacher's Details---------------------------";
	cout << "\n------------------------------------------------------------------------------";

	cout << "\n\t\t\tEnter teacher ID :";
	cin >> id;
	ofstream write;
	ifstream read;
	write.open("temp.txt", ios::app);

	read.open("teacher.txt");
	read >> Name;
	read >> teacherID;
	read >> Course;
	read >> Gender;
	read >> Email_id;
	read >> Contact_No;
	read >> Address;
	read >> salary;
	while (!read.eof()){
		if (id == teacherID) {
			++found;
			cout << "\n\t\t\tName of teacher:" << Name << endl;
			cout << " \t\t\tID number of teacher  :" << teacherID << endl;
			cout << " \t\t\tClass of teacher:" << Course << endl;
			cout << " \t\t\tGender of teacher  :" << Gender << endl;
			cout << "\t\t\tEmail ID of teacher :" << Email_id << endl;
			cout << " \n\t\t\tContact number of teacher:" << Contact_No << endl;
			cout << "\t\t\t Address of teacher:" << Address << endl;
			cout << "\t\t\t salary of teacher:" << salary << endl;
			cout << " \n\n\t\t\tID number  : " << teacherID;

			cout << "\n\t\t\tEnter the Name of the teacher: ";
			cin >> Name;
			cout << "\n\t\t\tEnter the Class of teacher ";
			cin >> Course;
			cout << "\n\t\t\tEnter the Gender of teacher ";
			cin >> Gender;
			cout << "\n\t\t\tEnter Email Id of teacher: ";
			cin >> Email_id;
			cout << "\n\t\t\tEnter the contact Number of teacher: ";
			cin >> Contact_No;
			cout << "\n\t\t\tEnter the address of teacher: ";
			cin >> Address;
			cout << "\n\t\t\tEnter the salary of teacher: ";
			cin >> salary;
			write << Name << endl;
			write << teacherID << endl;
			write << Course << endl;
			write << Gender << endl;
			write << Email_id << endl;
			write << Contact_No << endl;
			write << Address << endl;
			write << salary << endl;
			break;
		}
		else{
			cout << "\n\t\t\tteacher of this ID No. doesn't exist";
			break;
		}
		write << Name << endl;
		write << teacherID << endl;
		write << Course << endl;
		write << Gender << endl;
		write << Email_id << endl;
		write << Contact_No << endl;
		write << Address << endl;
		write << salary << endl;

		read >> Name;
		read >> teacherID;
		read >> Course;
		read >> Gender;
		read >> Email_id;
		read >> Contact_No;
		read >> Address;
		read >> salary;
	}
	if (found == 0){
		cout << "\n\t\t\tNo Data is Present in the file: ";
	}
	write.close();
	read.close();
	remove("teacher.txt");
	rename("temp.txt", "teacher.txt");
}
void AdminT::deleted() {
    system("cls");
    fstream read, write;
    int id;
    int found = 0;
    cout << "\n------------------------------------------------------------------------------";
    cout << "\n-----------------------------Delete teacher's Details---------------------------";
    cout << "\n------------------------------------------------------------------------------";

    read.open("teacher.txt");
    if (!read) {
        cout << "\n\t\t\tNo Data is Present..";
    } else {
        cout << "\n Enter ID of teacher which you want to delete data:";
        cin >> id;
        write.open("temp.txt", ios::app | ios::out);
        read >> Name >> teacherID >> Course >> Gender >> Email_id >> Contact_No >> Address >> salary;
        while (!read.eof()) {
            if (id != teacherID) {
                write << "  " << Name << "  " << teacherID << "  " << Course << "  "
                      << Gender << "  " << Email_id << "  " << Contact_No << "  " << Address << " " << salary << "\n";
            }
            read >> Name >> teacherID >> Course >> Gender >> Email_id >> Contact_No >> Address >> salary;
            found++;
        }
        if (found == 0) {
            cout << "\n\t\t\t Teacher's ID No. not found....";
        }
    }
    if (found > 0) {
        cout << "\n\t\t\tSuccessfully deleted:";
    }
    write.close();
    read.close();
    remove("teacher.txt");
    rename("temp.txt", "teacher.txt");
}
//******************************************
//*******STUDENT CLASS DEFINATION***********
//******************************************
void student::data() {
{	system("cls");
	int id;
	int found = 0;
	cout << "\n---------------------------------------------------------------------------";
	cout << "\n-----------------------------Student Details---------------------------";
	cout << "\n---------------------------------------------------------------------------";

	cout << "\n\t\t\tEnter your roll number :" << endl;
	cin >> id;
	if (id != rollNo){
		cout << "\n\t\t\tStudent Not found:";
	}
	ifstream read;

	read.open("Student.txt");
	read >> name;
	read >> rollNo;
	read >> course;
	read >> gender;
	read >> email_id;
	read >> contact_No;
	read >> address;

	while (!read.eof()){
		if (id == rollNo) {
			++found;
			cout << "\t\t\t Name: " << name << endl;
			cout << "\t\t\t Roll No.: " << rollNo << endl;
			cout << "\t\t\t Course:" << course << endl;
			cout << "\t\t\t Gender:" << gender << endl;
			cout << "\t\t\t Email: " << email_id << endl;
			cout << "\t\t\t Contact No.: " << contact_No << endl;
			cout << "\t\t\t Address: " << address << endl;
		}
		read >> name;
		read >> rollNo;
		read >> course;
		read >> gender;
		read >> email_id;
		read >> contact_No;
		read >> address;
	}
	if (found == 0){
		cout << "\n\t\t\tNo such student present in the file: ";
	}
	read.close();
}
}
//**********************************************************
//***************TEACHER CLASS DEFINATION*******************
//**********************************************************
void teacher::teachermanue() {
start:
    int choice;
    system("cls");
    cout << "\n\t\t\t 1.search student: ";
    cout << "\n\t\t\t 2.Display All Students: ";
    cout << "\n\t\t\t 3.See Your Information: ";
    cout << "\n\t\t\t Enter your choice: ";
    cin >> choice;
    switch (choice) {
    case 1:
        srch();
        break;
    case 2:
        showAll();
        break;
    case 3:
        Teacher_rec();
        break;
    default:
        cout << "\n\t\t\t invalid choice: ";
    }
    getch();
    goto start;
}
void teacher::showAll() {
    system("cls");
    int total = 0;
    ifstream read;

    cout << "\n---------------------------------------------------------------------------";
    cout << "\n------------------------------Student Details------------------------------";
    cout << "\n---------------------------------------------------------------------------";
    read.open("Student.txt");
    if (!read) {
        cout << "\n\t\t\tNo Data is Present to Show: ";
        read.close();
    } else {
        read >> name >> rollNo >> course >> gender >> email_id >> contact_No >> address;
        while (!read.eof()) {
            cout << "\n\t\t\t Student No." << ++total << endl;
            cout << "\t\t\t Name of Student: " << name << endl;
            cout << "\t\t\t Roll No. of Student: " << rollNo << endl;
            cout << "\t\t\t Course of Student:" << course << endl;
            cout << "\t\t\t Gender of Student: " << gender << endl;
            cout << "\t\t\t Email ID Of Student: " << email_id << endl;
            cout << "\t\t\t Contact No. Of Student: " << contact_No << endl;
            cout << "\t\t\t Address Of Student: " << address << endl;
            read >> name >> rollNo >> course >> gender >> email_id >> contact_No >> address;
        }
        if (total == 0) {
            cout << "\n\t\t\tNo Data is Present to Show: ";
        }
    }
    read.close();
}
void teacher::srch()
{	system("cls");
	int id;
	int found = 0;
	cout << "\n---------------------------------------------------------------------------";
	cout << "\n-----------------------------Search Student Details---------------------------";
	cout << "\n---------------------------------------------------------------------------";

	cout << "\n\t\t\tEnter the roll number :" << endl;
	cin >> id;
	if (id != rollNo){
		cout << "\n\t\t\tStudent Not found:";
	}
	ifstream read;

	read.open("Student.txt");
	read >> name;
	read >> rollNo;
	read >> course;
	read >> gender;
	read >> email_id;
	read >> contact_No;
	read >> address;

	while (!read.eof()){
		if (id == rollNo) {
			++found;
			cout << "\t\t\t Name of Student: " << name << endl;
			cout << "\t\t\t Roll No. of Student: " << rollNo << endl;
			cout << "\t\t\t Course of Student:" << course << endl;
			cout << "\t\t\t Gender of Student:" << gender << endl;
			cout << "\t\t\t Email ID OfStudent: " << email_id << endl;
			cout << "\t\t\t Contact No. Of Student: " << contact_No << endl;
			cout << "\t\t\t Address Of Student: " << address << endl;
		}
		read >> name;
		read >> rollNo;
		read >> course;
		read >> gender;
		read >> email_id;
		read >> contact_No;
		read >> address;
	}
	if (found == 0){
		cout << "\n\t\t\tNo such student present in the file: ";
	}
	read.close();
}
void teacher::Teacher_rec() {
    system("cls");
    int id;
    int found = 0;
    cout << "\n---------------------------------------------------------------------------";
    cout << "\n--------------------------------Your Details-------------------------------";
    cout << "\n---------------------------------------------------------------------------";
    cout << "\n\t\t\tEnter the Teacher's ID :" << endl;
    cin >> id;
    if (id != teacherID) {
        cout << "\n\t\t\tTeacher Not found:";
    } else {
        ifstream read;
        read.open("teacher.txt");
        read >> Name >> teacherID >> Course >> Gender >> Email_id >> Contact_No >> Address >> salary;
        while (!read.eof()) {
            if (id == teacherID) {
                found++;
                cout << "\t\t\t Name of teacher: " << Name << endl;
                cout << "\t\t\t ID No. of teacher: " << teacherID << endl;
                cout << "\t\t\t Course of teacher: " << Course << endl;
                cout << "\t\t\t Gender of teacher: " << Gender << endl;
                cout << "\t\t\t Email ID Of teacher: " << Email_id << endl;
                cout << "\t\t\t Contact No. Of teacher: " << Contact_No << endl;
                cout << "\t\t\t Address Of teacher: " << Address << endl;
                cout << "\t\t\t Salary Of teacher: " << salary << endl;
            }
            read >> Name >> teacherID >> Course >> Gender >> Email_id >> Contact_No >> Address >> salary;
        }
        if (found == 0) {
            cout << "\n\t\t\tNo such teacher present in the file: ";
        }
        read.close();
    }
}
//*************************************************************
//******************Login Class Defination*********************
//*************************************************************
void login::loginManue(){
	manuelogin:
	system("cls");
	cout << "\n\n\t\t\t---------------------------------------------------------------------------";
	cout << "\n\n\t\t\t------------------WELCOME TO STUDENT MANAGEMENT SYSTEM---------------------";
	cout << "\n\n\t\t\t---------------------------------------------------------------------------";
	int choice;
	cout << "\n\t\t\t Login Manue: ";
	cout << "\n\t\t\t1.login: ";
	cout << "\n\t\t\t2.Registration: ";
	cout << "\n\t\t\t3.Forgot User Name or Pasword: ";
	cout << "\n\t\t\t4.Exit: ";
	cout << "Enter Your Choice: ";
	cin >> choice;
	switch (choice){
	case 1:
		Login();
		break;
	case 2:
		registr();
		break;
	case 3:
			forget();
		break;
	case 4:
		exit(0);
		break;
	default:
		system("cls");
		cout << "\n\n\t\t\tyou have made a mistake, please try again";
		break;
	}
	getch();
	goto manuelogin;
}
void login::Login() {
log:
    system("cls");
    int choice;
    cout << "\n\t\t\t Choose your option: ";
    cout << "\n\t\t\t 1. Login as student";
    cout << "\n\t\t\t 2. Login as teacher";
    cout << "\n\t\t\t 3. Login as Admin";
    cout << "\n\t\t\t 4. Back to main menu: ";
    cin >> choice;
    switch (choice) {
    case 1: {
        int count = 0;
        int p;
        string u;
        system("cls");
        cout << "\n\t\t\t Please enter the following details: ";
        cout << "\n\t\t\t Username: ";
        cin >> email_id;
        cout << "\n\t\t\t Password: ";
        cin >> rollNo;
        
        ifstream input;
        input.open("login.txt");
        while (input >> u >> p) {
            if (u == email_id && p == rollNo) {
                count = 1;
                system("cls");
            }
        }
        input.close();
        if (count == 1) {
            cout << "\n\t\t\tWelcome! Login Successful";
            student s;
            s.data();
        } else {
            cout << "\n\t\t\tLogin failed, please try again.";
        }
        getch();
        goto log;
    }
    break;
	case 2:{
		int count = 0;
		int   p;
		string u;
		system("cls");
		cout << "\n\t\t\t Please enter the following details: ";
		cout << "\n\t\t\t Username: ";
		cin >> Email_id;
		cout << "\n\t\t\t Password: ";
		cin >> teacherID;
		ifstream input;
		input.open("login.txt");
		while (input >> u >> p){
			if (u == Email_id && p == teacherID){
				count = 1;
				system("cls");
			}
		}
		input.close();
		if (count == 1)
		{
			cout << "\n\t\t\tWelcom Login Successful: ";
			teacher t;
			t.teachermanue();
		}
		else{
			cout << "\n\t\t\t Login failed please try agin:";
		}
		goto log;
	}
	break;
	case 3: {
		int count = 0;
		string  pass, u, p;
		system("cls");
		cout << "\n\t\t\t Please enter the following details: ";
		cout << "\n\t\t\t Username: ";
		cin >> email_id;
		cout << "\n\t\t\t Password: ";
		cin >> pass;
		ifstream input;
		input.open("login.txt");
		while (input >> u >> p){
			if (u == email_id && p == pass){
				count = 1;
				system("cls");
			}
		}
		input.close();
		if (count == 1){
			cout << "\n\t\t\tWelcom Login Successful: ";
			int choice;
			cout << "\n\t\t\t please select an Option: ";
			cout << "\n\t\t\t 1.Student Manue: ";
			cout << "\n\t\t\t 2.Teacher Manue: ";
			cout << "\n\t\t\t 3.Back: ";
			cout << "\n\t\t\t Enter your choice: ";					
			cin >> choice;
			switch(choice){
				case 1: {
						AdminS a;
		    			a.manueS();
		    		}
		    	break;
		    	case 2: {
						AdminT a;
		    			a.manueT();
		    		}
		    	break;
		    	case 3:
		    		Login();
		    		break;
		    	default:
		    		cout << "\n\n Invalid choice: ";
			}
		}
		else{
			cout << "\n\t\t\t Login failed please try agin:";
		}
	}
	case 4:
		loginManue();
		break;
	}
}
void login::registr() {
    system("cls");
    string dp, de;
    string defaultpass = "admin123";
    string defaultEmailId = "admin@example.com";
    string regpass;
    string ru, pu;

    cout << "\n\t\t\tEnter the default password: ";
    cin >> dp;
    cout << "\n\t\t\tEnter the default user name: ";
    cin >> de;

    if (dp == defaultpass && de == defaultEmailId) {
        cout << "\n\t\t\tEnter the user name: ";
        cin >> email_id;
        cout << "\n\t\t\tEnter the Password: ";
        cin >> regpass;

        ofstream write;
        write.open("login.txt", ios::app);
        write << email_id << " " << regpass << endl;
        write.close();

        system("cls");
        cout << "\n\t\t\tRegistration successful.";
    } else {
        cout << "\n\t\t\tSorry, only administrators can register please contact to the admin.";
    }
}

void login::forget(){
	int ch;
	cout << "\n\t\t\t Forgotten password?" << "Weare here to help.";
	cout << "\n\t\t Search your ID by username: ";
	cout << "\n\n\t\t\t 1.Search your Id by User Name: ";
	cout << "\n\t\t\t 2.Main Manue:";
	cin >> ch;
	switch(ch){
		case 1:
			int count = 0;
			string searchUser,su,sp;
			cout << "\n\t\t\t Enter your remember user name: ";
			cin >> searchUser ;
			ifstream read;
			read.open("login.txt");
			while(read >> su >> sp){
				if(su == searchUser){
					count = 1;
				}
			}
				read.close();
				if (count == 1){
					cout << "\n\t\t\t Acount Found: ";
					cout << "\n\t\t\t Your Password is: " << sp;;
					cin.get(); 
				 } 
				 else{
				 	cout << "\n\t\t\t Sorry user name is not found in Our Record: \n";
				 	cout << "\n\t\t\t Your Password is: " << sp;
				 	cin.get();	
				 }			
	}
}
//***********************************************************
//*********************INTRODUCTION**************************
//***********************************************************
void introduction(){
	cout << "\n\n\t\t\t --------------------------------------------------";
	cout << "\n\n\t\t\t -------------------THIS PROJECT ------------------";
	cout << "\n\n\t\t\t -------------STUDENT MANAGEMENT SYSTEM------------";
	cout << "\n\n\t\t\t -------------------PRESENTED BY ------------------";
	cout << "\n\n\t\t\t *******SAJJAD ALI AND HIS PARTNER M.FASEEH********";
	cout << "\n\n\t\t\t ----STUDENTS OF RIPHAH INTERNATIONAL UNIVERSITY---";
	cout << "\n\n\t\t\t *****************TO MR.HAROON*********************";
	cin.get();
}
int main(){
	introduction();
	login L;
	L.loginManue();
}
