---
layout: page
title: Alagappan Ramanathan's Project Portfolio Page
---

### Project: LinkMeIn

LinkMeIn - Is a desktop address book application
used to track student's job applications to companies.
The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java, and has about
~10 kLoC.

The features in LinkMeIn can be categorised into 3 main categories:
1. **General features:** Navigating the application. Includes, help, exit and clear commands.
2. **Company management features:** Manage an entry of a company. Includes, add, delete, edit, view, remark and
   unremark commands.
3. **Company list features:** Manage the list of companies added. Includes, list, find, sort and filter commands.

Given below are my contributions to the project.

## **Code contributed**:
The code that I have contributed can be found in this [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=AlagappanRa&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

## Enhancements implemented:

### Implemented the duplicate checking algorithm for the add and edit command.

#### What it does:

The duplicate checking algorithm checks if the company that the user is trying to add or edit is already present in the
company list. If the company is already present in the company list, a specific error message will be raised to the 
user, informing them of the corrective action they need to take. 

#### Justification:

This feature was implemented to inform the user of the duplicate company that they are trying to add or edit. This
not only prevents duplicates from being added to the application list, but also the user from accidentally editing 
the wrong company. The associated error message also guides the user towards the correct usage of the command, 
by telling them which fields differ form the original company.

#### Highlights:

-   **Robust Error Handling**: Informs users of incorrect input and guides them towards correct usage. Various cases
considered include, filtered list view, original list view, partial match and complete match of fields.
-   **Thorough Testing**: Ensured reliability and performance across diverse scenarios through unit testing.


### Input validation method for all fields.

#### What it does: 
- Company name, recruiter name, role, phone number, email, application status and priority fields are validated to 
ensure that they are not empty and that they follow the correct regex.
- Implemented specific REGEX to ensure that the phone number and email fields are in the correct format.

### Justification:
Input validation allows us to validate duplicates, while allowing the user to see whatever they typed. This allows
for flexibility in the user interface, while ensuring that the user does not enter invalid inputs.

### Delete the address field from the AddressBook.
Changed the AddressBook class to not have the address field. This was done as the address field was not used in any 
of the commands. Address field was also removed in all the various test cases.


### Contributions to the UG:

The following sections from the UG were contributed by me:
1. Features:
   - Duplicate detection algorithm: Added description, examples for the various errors that can arise 
2. Added FAQ:
   - Added questions 1 and 2 to the FAQ section.
3. Introduction section
   - Added a section on the motivation behind LinkMeIn
   - Quick summary of features
4. Code block text size re-adjustment
   - Adjusted the code block text size to be smaller for better readability.

### Contributions to the DG:

The following sections from the DG were contributed by me:
- Added diagrams for the duplicate checking algorithm
- Planned enhancements
  - Remove the alphanumeric checks for the company name, recruiter name and role fields
  - Make index field accept different types of numerical formats
  - Expanding acceptance for phone number field
- Effort section

* **Bug Fixes**:
[#232](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/232):
* Fixed a bug where the duplicate detection algorithm gave a wrong output 
* Fixed a bug where `Software Engineer 2` and `Software Engineer` were considered equals

* **Community**:
  * **Forum Enthusiast**
    * Resolved other classmate's code related issues (examples:
    [1](https://github.com/nus-cs2103-AY2324S1/forum/issues/104#issuecomment-1706219015),
    [2](https://github.com/nus-cs2103-AY2324S1/forum/issues/154#issuecomment-1726817514),
    [3](https://github.com/nus-cs2103-AY2324S1/forum/issues/170#issuecomment-1730530807)
    )
    * Resolved a bug in the [JavaFX tutorial](https://se-education.org/guides/tutorials/javaFxPart1.html)
    regarding a JavaFX MAC-OS dependency issue. Recommended a modification to build gradle to solve the issue.
    [\#159](https://github.com/nus-cs2103-AY2324S1/forum/issues/159#issuecomment-1728708305)

