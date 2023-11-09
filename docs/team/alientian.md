---
layout: page
title: Lim En Tian's Project Portfolio Page
---

# Project: LinkMeIn

## **Overview**

LinkMeIn is a desktop address book application used to track computer science student's job applications to companies.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about
~10 kLoC.

## **Summary of Contributions**

Given below are my contributions to the project.

### **Code contributed**

You can find all the code I have contributed in [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=alientian&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

### **Enhancements implemented**

#### 1. Implemented display of application details in the GUI
Created `CompanyDetailCard.fxml` file to restructure our UI to have two panels and its Java class to render the UI.

This enhancement shows application details of all the fields input by user on the left panel.
Users change the company viewed by clicking on the list of companies on the right panel.
This is later on enhanced by my team member, Ru Xin, to be controlled by the `view` command to adhere to our CLI interface app.

#### 2. Implemented Remark field and its commands
Created the `Remark` class to add a remark to a `Company`.
`RemarkCommandParser` and `UnremarkCommandParser` classes are created to add and remove remarks respectively.
`RemarkCommand` class is also created so that both parser extends from `Parser<RemarkCommand>`.

Test cases were added for all the classes created. 

#### 3. Modified the edit command
Edited the code for `edit` command so that the command can edit all the fields that we have added to the `Company` class. 
Update error message to be more specific for invalid inputs.

### **Contributions to the UG**

- **CLI Description** : 
Enhanced the explanation of CLI for users to comprehend easily.

- **Features** :
Added description, examples and images for the following features. 
Tips, notes and warnings are also added if applicable.
    - **Edit** 
    - **Remark**
    - **Unremark**
    - **Clear**
    - **Exit**

### **Contributions to the DG**

- Added NFRs 
- Added planned enhancements 
- Added how Edit Command and Remark Command works under Implementation
- Created a UML sequence diagram to explain Edit Command
- Created a UML activity diagram to explain Remark Command

### **Contributions to team-based tasks**

- Created new issues under the issue tracker

### **Review/Mentoring contributions**

- PRs reviewed: 
[\#144](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/144),
[\#145](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/145),
[\#168](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/168),
[\#231](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/231)


### **Contributions beyond the project team**

- [Bugs reported during PE-D](https://github.com/alientian/ped/issues)
