---
layout: page
title: Glenda Chong Rui Ting's Project Portfolio Page
---

# Project: LinkMeIn

LinkMeIn is a desktop address book application for computer science students to track their internship applications.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about
~10 kLoC.


The features in LinkMeIn can be categorised into 3 main categories:
1. **General features:** Navigating the application. Includes, help, exit and clear commands.
2. **Company management features:** Manage the entry of a company. Includes, add, delete, edit, view, remark and unremark commands.
3. **Company list features:** Manage the list of companies added. Includes, list, find, sort and filter commands.

## Summary of Contributions

Given below are my contributions to the project.

### Code Contributed
The code that I have contributed can be found in this [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=GlendaChong&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22).

### Enhancements Implemented

#### 1. Implement `Filter` Command (New feature)
* **What:** `Filter` command filters the company list by the application status.
* **Implementation**: Create a new command class `FilterCommand` to handle the filter command. A new parser class `FilterCommandParser` was created to handle the parsing of the filter command. A new `ApplicationStatusPredicate` class was created to update the filtered company list. The GUI will display only the filtered company list, and the `CompanyDetailPanel` will remove the existing company detail card, if any. 

#### 2. Enhance error messages for fields in company model
* **What:** Previously when checking for invalid fields, the error message is the same regardless of whether the field is empty or the field is invalid. The fields that are edited are company name, role, recruiter name, phone number, application status and priority.
* **Implementation**: I have enhanced the error messages to be more specific to the error and more user-friendly. For each field, there are 2 error messages. 1 for when the field is empty and 1 for when the field does not follow the valid regex. 

#### 3. Update GUI for company list panel and company detail panel
* Edit the colours of the company list and detail panels to be of similar theme. 
* Update `CompanyDetailPanel` and `CompanyListPanel` to display the fields without truncation. 

#### 4. Add Role field to company model
* **What:** Add a new company role field to the company model. 
* **Implementation:** Role only accepts alphanumeric characters and spaces. Update `CompanyDetailCard`, `CompanyListPanel` to display the role field in the GUI.  

#### 5. Add RecruiterName field to company model
* **What:** Add a new recruiter name field to the company model.
* **Implementation:** Recruiter name only accepts alphanumeric characters and spaces. Update `CompanyDetailCard`, `CompanyListPanel` to display the recruiter name field in the GUI.

#### 6. Enhance existing `Add` Command
* **What:** Update the existing AB3 `add` command for LinkMeIn. 
* **Implementation:** Update `AddCommand` and `AddCommandParser` to include all the new fields added to the company model. Improve error messages to check for empty fields and invalid fields.




### Contributions to the UG
The following sections from the UG were contributed by me: 
1. **Navigating the User Guide:** Add the annotations and icons using bootstrap alert icons.  
2. **Quick Start:** Improve on this section with a step-by-step instruction, including adding more details and screenshots.  
3. **Features:** Add a company (`add`),  List all companies (`list`), Filter companies by application status (`filter`)
   * Include description of command, format, notes, tips, examples and possible errors.
4. **Glossary:** Add definitions of key terms, parameters description, application status parameter description and email format description section. 
5. **Command Summary:** Add the command summary table. 

### Contributions to the DG

  _to be added soon_

### Contributions to Team-based Tasks
1. Set up CodeCov for the project. 
2. Update landing page. 
3. Update UG for entire Glossary section.
4. Managed milestones, changed their due dates, and closed them to wrap-up.
5. Review PRs for the team regularly. 
   * Non-trivial PRs reviewed: [#226](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/226), [#156](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/156), [#169](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/169), [#142](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/142), [#135](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/135), [#109](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/109), [#104](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/104).


### Contributions beyond the Project Team**
1. Extensive number of bugs reported for the other team's product during PE-D (28 bug reports)
