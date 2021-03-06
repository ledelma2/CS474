//
//Liam Edelman
//CS 474, Fall '18
//UIC
//

#include "pch.h"
#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <algorithm>
#include <locale>

using namespace std;
using namespace System;

int main()
{
	string response = "-1";
	vector<string> firstname = vector<string>();
	vector<string> lastname = vector<string>();
	string filename;
	ifstream file;
	while (1)
	{
		cout << "Enter action number [0, 1, 2, or 3]: ";
		cin >> response;
		cin.ignore();
		if (response == "1")
		{
			cout << "Enter name of .csv file: ";
			cin >> filename;
			if (filename.find(".csv") != filename.npos)
			{
				file.open(filename);
				if (file.is_open() == false)
				{
					cout << "File not found..." << endl;
				}
				else
				{
					firstname.clear();
					lastname.clear();
					char first[100];
					char last[100];
					while (file.getline(last, 100, ','))
					{
						file.getline(first, 100, '\n');
						firstname.push_back(first);
						lastname.push_back(last);
					}
					file.close();
				}
			}
			else
			{
				cout << "Incorrect filetype..." << endl;
			}
		}
		else if (response == "2")
		{
			cout << "Enter name of new employee (FirstName LastName): ";
			char full[100];
			cin.getline(full, 100);
			string name(full);
			size_t SpaceNo = name.find(' ');
			if (SpaceNo == name.npos)
				cout << "Invalid Name..." << endl;
			else
			{
				string first = name.substr(0, SpaceNo);
				string last = name.substr(SpaceNo + 1, (name.npos) - 1);
				firstname.push_back(first);
				lastname.push_back(last);
			}
		}
		else if (response == "3")
		{
			cout << "Enter Search Query: ";
			char query[100];
			cin.getline(query, 100);
			string search(query);
			size_t SpaceNo = search.find(' ');
			transform(search.begin(), search.end(), search.begin(), tolower);
			vector<int> matches = vector<int>();
			if (SpaceNo == search.npos)
			{
				for (int i = 0; i < firstname.size(); i++)
				{
					string first = firstname[i];
					transform(first.begin(), first.end(), first.begin(), tolower);
					string last = lastname[i];
					transform(last.begin(), last.end(), last.begin(), tolower);
					if (first.find(search) != first.npos || last.find(search) != last.npos)
						matches.push_back(i);
				}
			}
			else
			{
				string f = search.substr(0, SpaceNo);
				string l = search.substr(SpaceNo + 1, (search.npos) - 1);
				for (int i = 0; i < firstname.size(); i++)
				{
					string first = firstname[i];
					transform(first.begin(), first.end(), first.begin(), tolower);
					string last = lastname[i];
					transform(last.begin(), last.end(), last.begin(), tolower);
					if (first.find(f) != first.npos && last.find(l) != last.npos)
						matches.push_back(i);
				}
			}
			cout << "Found Users:" << endl;
			for (int i = 0; i < matches.size(); i++)
			{
				cout << firstname[matches[i]] << " " << lastname[matches[i]] << endl;
			}
		}
		else if (response == "0")
		{
			break;
		}
		else
		{
			cout << "Invalid action number..." << endl;
			response = -1;
		}
	}
	cout << "Goodbye" << endl;
	return 0;
}