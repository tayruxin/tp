---
layout: page
title: Developer Guide
---

* Table of Contents
  {:toc}

---

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

* Libraries used:
    * [JavaFX](https://openjfx.io/)
    * [Jackson](https://github.com/FasterXML/jackson)
    * [JUnit](https://junit.org/junit5/)

---

## **Setting Up, Getting Started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.

-   At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
-   At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

-   [**`UI`**](#ui-component): The UI of the App.
-   [**`Logic`**](#logic-component): The command executor.
-   [**`Model`**](#model-component): Holds the data of the App in memory.
-   [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="584" />

Each of the four main components (also shown in the diagram above),
-   defines its _API_ in an `interface` with the same name as the Component.
-   implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI Component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `CompanyListPanel`, `CompanyDetailPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

-   executes user commands using the `Logic` component.
-   listens for changes to `Model` data so that the UI can be updated with the modified data.
-   keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
-   depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic Component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to delete a company).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

-   When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
-   All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model Component

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="500" />

The `Model` component,

-   stores the address book data i.e., all `Company` objects (which are contained in a `UniqueCompanyList` object).
-   stores the currently 'selected' `Company` objects (e.g., results of a search query) as a separate _filtered_ list
    which is exposed to outsiders as an unmodifiable `ObservableList<Company>` that can be 'observed' e.g. the UI can be
    bound to this list so that the UI automatically updates when the data in the list change.
-   stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
-   does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage Component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="627" />

The `Storage` component,

-   can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
-   inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
-   depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common Classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

---

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Company Detail Panel (UI component)
The `CompanyDetailPanel` allows the user to view the company details of the selected company in the company list.
Recruiter's information, company's information and remarks will be shown in the company detail panel.

#### Implementation
`CompanyDetailCard` and `CompanyDetailPanel` both inheriting `UiPart` are used to display the company details. More details
of the class implementation can be seen in the class diagram below.

<img src="images/DetailPanelClassDiagram.png" />

`CompanyDetailCard` calls the static method `createPriorityFlowPane` from `CompanyCardUtils` which creates a `FlowPane` to display the priority of the company.
The color of the `FlowPane` is determined by the priority of the company. Red is used to indicate high priority,
orange is used to indicate medium priority and green is used to indicate low priority.

As for the other information, FXML labels are used to display the information. Within the constructor of `CompanyDetailCard`,
the respective FXML labels are set with the information of the company.

To display the details in 3 different boxes, `CompanyDetailCard.fxml` is divided into 3 sections with each section
being a `VBox`. The 3 `VBox` are then added into a `HBox` to display the details in 3 different boxes.

As for `CompanyDetailPanel`, there is an inner class `CompanyDetailViewCell` which extends `ListCell<Company>`. This class
sets the graphics to the `CompanyDetailCard` by constructing a new `CompanyDetailCard` with the company details of the company.

#### Design Considerations

**Aspect: How details of the company is displayed**

-   **Alternative 1 (current choice):** Display the details of the company in a separate panel.

    -   Pros: The information is well compartmentalized. This improves the user viewing experience.
    -   Cons: More commands are needed to view the details of the company.

-   **Alternative 2:** Display the details of the company in the same panel as the company list.
    -   Pros: User does not need to key in additional commands to view the details of the company.
    -   Cons: The company list panel will be too cluttered with too much information displayed in a company card.

### View Feature
The `CompanyDetailPanel` allows the user to view the company details of the selected company in the company list.
The user can use the `view` command to select the company to view.

#### Implementation
A new `UniqueCompanyList` is created in `AddressBook` to store the selected company which the user wishes to view.
Additionally, the following operations are implemented in `AddressBook` to support the `view` and other commands:

-   `setCurrentViewedCompany(Company company)` - Sets the selected company to be viewed.
-   `clearDetailPanel()` - Clears the `UniqueCompanyList` to remove the selected company from the company detail panel.

These operations are exposed in the `Model` interface as `Model#setCurrentViewedCompany(Company company)` and
`Model#checkDelete()` respectively.

The `view` function is implemented in the `ViewCommand` class which calls `Model#setCurrentViewedCopany(Company company)`
to insert the selected company into the `UniqueCompanyList`.
The follow sequence diagram depicts how the `view` command is executed.

<img src="images/ViewSequenceDiagram.png" />

<div markdown="block" class="alert alert-info">
 **:information_source: Note:**
 The lifeline for `ViewCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
 </div>

Since only the detail of one company will be displayed anytime, `Model#setCurrentViewedCopany(Company company)` will
clear the `UniqueCompanyList` before inserting the selected company.
Since `UniqueCompanyList` is an observable list, the `CompanyDetailPanel` will be updated automatically
when there is any changes made to the `UniqueCompanyList`.

When the `edit`, `add`, `view` or `delete` command is executed, the `CompanyDetailPanel` will be updated respectively as
shown in the activity diagram below.

<img src="images/CompanyDetailPanelActivityDiagram.png" width="400"/>

#### Design Considerations

**Aspect: How the company to be viewed is stored in the `AddressBook`**

-   **Alternative 1 (current choice):** Create a new `UniqueCompanyList` in `AddressBook` to store the selected company which the user wishes to view.

    -   Pros: Since the `UniqueCompanyList` is an observable list, the `CompanyDetailPanel` will be updated automatically when there is any changes made to the `UniqueCompanyList`.
    -   Cons: There is a need to clear the list before adding the selected company to the `UniqueCompanyList` to ensure that only one company is displayed in the `CompanyDetailPanel` at any time.

-   **Alternative 2:** Create a new `Company` object in `AddressBook` to store the selected company which the user wishes to view.
    -   Pros: Since there are no lists involved, there is no need to clear the list.
    -   Cons: The `CompanyDetailPanel` will not be updated automatically when there are any changes made to the `Company`
        object. There is a need to create additional methods to update the `CompanyDetailPanel` when there is any changes
        made to the `Company` object.

### Find Feature

#### Implementation

The `find` command allows users to search for companies using one or more keywords. Companies matching any of the keywords in their names will be returned. This search is case-insensitive, and partial matches are valid. The critical change in the implementation centers around the modification of the `NameContainsKeywordsPredicate` class.

**How `NameContainsKeywordsPredicate` Works**

Previously, `NameContainsKeywordsPredicate` was designed to match a company name against a whole keyword. However, the modified implementation allows it to test a company's name against substrings and return true if the comapany's name contains the substring .

When `find` is executed, it uses the `Model` interface's `updateFilteredCompanyList(Predicate<Company> predicate)` method, passing in the modified `NameContainsKeywordsPredicate` to filter the list of companies.

The sequence diagram below illustrates the processing of a `find` command, such as `find Micr`:

<img src="images/FindCompanySequenceDiagram.png"/>

> :information_source: **Note:** The above sequence diagram simplifies the interaction by focusing on the primary components involved in processing the `find` command.

#### Design Considerations

**Aspect: Approach to matching keywords**

-   **Alternative 1 (current choice):** Match company names that contain the keyword **anywhere** within them.

    -   Pros: Flexible search, allows partial keyword matching.
    -   Cons: Might produce more results than expected.

-   **Alternative 2:** Match company names that **start** with the given keyword.

    -   Pros: Precise results.
    -   Cons: Might omit some relevant results if user does not remember the exact start of the company's name.

**Aspect: Case-sensitivity**

-   **Alternative 1 (current choice):** Case-insensitive matching.

    -   Pros: User-friendly; users don’t need to remember exact case.
    -   Cons: Might produce a broader range of results.

-   **Alternative 2:** Case-sensitive matching.

    -   Pros: More exact matches.
    -   Cons: Less user-friendly, especially if users do not recall the exact case of company names.

With the design considerations, we've chosen the alternatives that provide a balance between user-friendliness and precision.

### Filter Feature

The `filter` command allows users to filter the company list by the application status.

#### Implementation

The `filter` command is implemented in the `FilterCommand` class, which uses the `ApplicationStatusPredicate` class. The `ApplicationStatusPredicate` class implements the `Predicate` interface, which allows it to be used in the `Model` interface's `updateFilteredCompanyList(Predicate<Company> predicate)` method.

Given below is an example usage scenario and how the `filter` mechanism behaves at each step.

1. The user enters the input `filter s/PA`.
2. The `LogicManager` calls `AddressBookParser#parseCommand()` with the user input.
3. The `AddressBookParser` creates a parser that matches the `filter` command, a `FilterCommandParser` object and uses it to parse the command.
4. This results in a `FilterCommand` object, which is executed by the `LogicManager`.
5. The `FilterCommand` object can communicate with the `Model` when it is executed. It calls `Model#filterCompaniesByStatus(Predicate<Company> predicate)` to filter the list of companies by their application status.
6. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The following sequence diagram will illustrate the process of performing the `filter` command.

<img src="images/FilterSequenceDiagram.png"/>

<div markdown="block" class="alert alert-info">
**:information_source: Note:**
The lifeline for `FilterCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

#### Design Considerations

**Aspect: UI of the Filter Command**

* **Alternative 1:** The `CompanyDetailPanel` will still display the details of the company that was selected before the `filter` command is executed.
    * Pros: Users can still view the details of the company in the `CompanyDetailPanel` alongside the filtered list of companies.
    * Cons: Users may be confused as the currently viewed company in the `CompanyDetailPanel` may not be in the filtered list of companies.

* **Alternative 2 (current choice):** The `CompanyDetailPanel` will be cleared whenever the `filter` command is executed.
    * Pros: Users can focus on viewing details of company(s) belonging to the filtered list only, reducing distractions and confusions.
    * Cons: Users might have to execute the `view` command again to access details of the company that is selected before filtering even if that company is still in the filtered list, potentially leading to additional steps taken.

### Edit Feature

#### Implementation
The edit mechanism is facilitated by `EditCompanyDescriptor`. It is a nested class of `EditCommand` that stores the edited fields of a company and unedited fields to be `null`.
Additionally, `EditCommand` implements the following operations:

* `EditCommand#execute(Model model)` — Edits all the attributes indicated in user input.

These operations are exposed in the `Model` interface as `Model#setCompany(Company target, Company editedCompany)`.

Given below is the sequence diagram shows how the edit operation works.
<img src="images/EditSequenceDiagram.png"/>

After the `EditCommandParser` initializes an `EditCompanyDescriptor` object, it sets the attributes of `EditCompanyDescriptor` that needs to be edited to the values input by the user.
When `EditCommand#execute()` is called, a `Company` object, `c`, with edited attributes is initialized since `Company` is immutable.
When `Model#setCompany(Company company)` is called, the original `Company` object in the `AddressBook` is replaced with the edited Company `c`.

#### Design Considerations

**Aspect: How to edit different attributes of a company**

* **Alternative 1 (current choice):** Edits all attributes using one command.
    * Pros: Easy to implement.
    * Cons: More prone to errors and bugs/ require more test cases for code coverage.

* **Alternative 2:** Have a command to edit each attribute.
    * Pros: Command line is shorter which reduces users' error such as duplicates or invalid command. This improves user experience.
    * Cons: We must ensure that the implementation of each individual command are correct. This may also require more memory usage, a Company object is initialized for every modified attribute.

### Duplicate detection

<img src="images/duplicate-detection/edit-command/DuplicateSequenceDiagram.png"/>

#### Implementation
The term _duplicate_ hereafter refers to companies with the same company name, role and deadline

The _duplicate_ detection mechanism is facilitated by `Company#isSameCompany(Company otherCompany)`.
This method checks if two `Company` entities are the same by checking if their `Name`, `Role` and 
`Deadline` fields are equal. This method is used by `AddCommand` and `EditCommand` to check if 
the company to be added or edited already exists in the company list.

The above sequence diagram shows the events when a user attempts to **edit** the details of an existing company,
namely the company name, role and deadline fields to match that of another company in the company list.
The purpose of the diagram is a **simplified** view of the message passing when a _duplicate_ company is detected.

Therefore, the diagram omits the following
1. The `if` statement in the `EditCommand` class that checks if the edited company is the same as the company to be
   edited before the call to `getDuplicateCompany(c)`. This is removed as the purpose of the diagram is to show the message
   passing **after** a duplicate company is detected.
1. The `if` statements in the `isSameCompany` method checking for strict equality with `this` and company d with`null`.
   This is removed to simplify the diagram and not show the inner-workings of the method in detail.
1. The `equals` method propagated after the `getName()`, `getRole()` and `getDeadline()` methods. Again, this would
   involve the details of the equality checks of the `Name`, `Role` and `Deadline` classes which is not the focus of the
   diagram.
1. The instantiation of the `CommandException` class through the `super` call from `DuplicateException` class.
   This is removed to simplify the diagram.

Below is an activity diagram showing the events when a user attempts to **add** a duplicate company to the company list.

<img src="images/duplicate-detection/add-command/DuplicateActivityDiagram.png"/>

The purpose of the diagram is to show the difference in the message passing when a duplicate company is detected
between the `AddCommand` and `EditCommand` classes. Therefore, the diagram omits the propagation of the
getDuplicateCompany(toAdd) method, which has already been shown in the sequence diagram prior.

#### Design Considerations
**Aspect: Change the location of the duplicate detection**
- **Alternative:** Implement the duplicate detection logic within the `AddCommandParser` or `EditCommandParser` classes.
    - Pros: The `execute` method's sole responsibility will be to execute the add or edit command without
      needing to handle duplicate detection logic, adhering to the Single Responsibility Principle.
    - Cons: The current architecture design dictates that `Model` be separate from `Logic`. The interaction
      between `Model` and `Logic` is through the `execute` method. Implementing the duplicate detection in the
      `Parser` classes will require the `Parser` classes to have access to the `Model` class, which violates the
      current architecture design.

**Aspect: Change the definition of a _duplicate_**
- **Alternative:** Define _duplicates_ as equivalence of all fields other than just `Name`, `Role` and `Deadline`.
    - Pros: Allows users to add companies with the same name, role and deadline but different contact details.
    - Cons: This approach does not align with real-world scenarios where if the Name, Role, and Deadline fields
      are identical, it likely indicates the same job application. The purpose of the duplicate detection is to prevent
      interns from inadvertently applying multiple times to the same position at a company with the same role and
      application deadline.

### Add Feature
The `add` command allows users to add companies into LinkMeIn. The compulsory parameters are the company's name, the application's role, status and deadline, and the recruiter's name, phone and email address. The optional field is the priority field. Parameters can be added in any order.

#### Implementation
Given below is an example usage scenario and how the `add` mechanism behaves at each step.

1. The user enters the input `add c/Google r/Software Engineer s/PA d/10-10-2023 n/Francis Tan p/98765432 e/francist@gmail.com`.

2. The `LogicManager` calls `AddressBookParser#parseCommand()` with the user input.

3. The `AddressBookParser` creates a parser that matches the `add` command, an `AddCommandParser` object and uses it to parse the command.

4. This results in an `AddCommand` object, which is executed by the `LogicManager`.

5. The `AddCommand` object can communicate with the `Model` when it is executed. It first checks if there's a duplicate input, which has the same company name, application role and application deadline.

6. If the `Model` does not have a duplicate, the `AddCommand` object calls `Model#addCompany` to add the new `Company` into LinkMeIn.

7. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The following sequence diagram illustrates how the `add` command works:

<img src="images/AddSequenceDiagram.png" alt="Add Sequence Diagram"/>

<div markdown="block" class="alert alert-info">
**:information_source: Note:**
The lifeline for `AddCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

The following activity diagram shows how the `add` command works:

<img src="images/AddActivityDiagram.png" alt="Add Activity Diagram"/>

#### Design Considerations
**Aspect: Parameters to be Added into Company**

* **Alternative 1:** A `Company` object only requires the company's name, application's role and deadline as parameters for `add` command.
    * Pros: Short and concise `add` command for users to type in. Easy for developers to implement with less code.
    * Cons: Users may not be able to store necessary information in LinkMeIn, such as recruiter's information. Users may also be unable to keep track of which stage of the application they are at.
* **Alternative 2 (Current Choice):** A `Company` object also includes application status, recruiter's name, phone and email address. The priority field, which is the user's opinion of the application priority, is kept optional.
    * Pros: Users can add in all the information at once, minimising the need to use other commands to do so afterward, like using `edit` command.
    * Cons: Longer `add` command for users. Users may also not have recruiter's information at hand when they are adding in the company into LinkMeIn.

---

## **Documentation, Logging, Testing, Configuration, DevOps**

-   [Documentation guide](Documentation.md)
-   [Testing guide](Testing.md)
-   [Logging guide](Logging.md)
-   [Configuration guide](Configuration.md)
-   [DevOps guide](DevOps.md)

---

## **Appendix A: Requirements**

### Product Scope

**Target user profile**:

-   Computer Science students preparing for an internship or job application
-   prefer desktop apps over other types
-   can type quickly
-   prefers typing to mouse interactions
-   is reasonably comfortable using CLI apps

**Value proposition**: CS students often struggle to manage a multitude of internship contacts and track their application progress.
A CLI address book not only efficiently stores these connections but also offers a valuable tool for monitoring and organizing the entire
application process, simplifying the pursuit of career opportunities.

### User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                         | I want to …​                                                 | So that I can…​                                                                            |
|----------|---------------------------------|--------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| `* * *`  | user                            | add a new company to LinkMeIn                                | manage my internship applications for that company                                         |
| `* * *`  | thorough user                   | view the recruiter's information of a company                | follow up on my internship application                                                     |
| `* * *`  | tidy user                       | delete a company                                             | ensure my list of companies remains organised by removing those I no longer wish to manage |
| `* * *`  | user                            | list out all my companies                                    | have an overview of my internship applications                                             |
| `* *`    | careless user                   | edit the details of a company                                | rectify any typographical errors in the company details                                    |
| `* *`    | user managing many applications | find companies by their name                                 | locate details of companies without having to go through the entire list                   |
| `* *`    | user managing many applications | sort companies by application deadline                       | view applications with nearer or further deadlines easily to plan my schedule              |
| `* *`    | meticulous user                 | add remarks for a company                                    | keep track of specific notes, thoughts or important details related to that application    |
| `* *`    | meticulous user                 | remove remarks for a company                                 | remove irrelevant information and keep my remarks up to date                               |
| `* *`    | seasoned user                   | clear all data                                               | start afresh on a internship application cycle                                             |
| `* *`    | user                            | exit the app quickly                                         | conclude my session and ensure the application is not running in the background            |
| `* *`    | confused user                   | view a list of available CLI commands and their descriptions | learn more about the application's features                                                |
| `* *`    | new user                        | access a list of sample data                                 | test the application out                                                                   |
| `* *`    | seasoned user                   | filter companies by application status                       | focus on the most pertinent and relevant applications of interest                          |
| `* *`    | careless user                   | check for duplicate entries before adding an entry           | avoid redundancy and maintain an accurate representation of my internship applications     |
| `* `     | creative user                   | be able to change the theme of LinkMeIn                      | personalise the visual appearance of the interface based on my preferences                 |
| `*`      | new user                        | import data from excel file                                  | easily switch from excel to LinkMeIn and continue tracking my internship applications      |
| `*`      | new user                        | export data to excel file                                    | easily switch from LinkMeIn to excel and continue tracking my internship applications      |


### Use Cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a company**

**MSS**

1.  User requests to list companies
2.  AddressBook shows a list of companies
3.  User requests to delete a specific company in the list
4.  AddressBook deletes the company

    Use case ends.

**Extensions**

-   2a. The list is empty.

    Use case ends.

-   3a. The given index is invalid.

    -   3a1. AddressBook shows an error message.

        Use case resumes at step 2.

-   3b. User is viewing the details of the company to be deleted.

    -   3b1. AddressBook clears the company details panel.

        Use case resumes at step 4.

**Use case: Find a Company**

**MSS**

1. User requests to find a company by name.
2. AddressBook shows a list of companies whose names contain the given keywords.

   Use case ends.

**Extensions**

-   2a. The list is empty.

    Use case ends.

-   2b. The given keywords do not match any company name.

    -   2b1. AddressBook shows an empty list.

        Use case ends.

-   2c. The given keywords match multiple company names.

    -   2c1. AddressBook shows a list of companies whose names contain the given keywords.

        Use case ends.

**Use case: Add a company**

**MSS**

1. User requests to add a company.
2. User key in required field and information.
3. AddressBook adds the company.
4. AddressBook shows the company detail of the added company in the company detail panel.

   Use case ends.

**Extensions**

-   2a. User key in invalid information.

    -   2a1. AddressBook shows an error message.

        Use case resumes at step 2.

**Use case: List company**

**MSS**

1. User requests to list companies.
2. AddressBook shows a list of companies.

   Use case ends.

**Extensions**

-   2a. The list is empty.

    Use case ends.

**Use case: View full company information**

**MSS**

1. User requests to list companies.
2. AddressBook shows a list of companies.
3. User requests to view a specific company in the list.
4. AddressBook shows the full information of the company in the company detail panel.

   Use case ends.

**Extensions**

-   2a. The list is empty.

    Use case ends.

-   3a. The given index is invalid.

    -   3a1. AddressBook shows an error message.

        Use case resumes at step 2.


**Use case: Sort companies by deadline**

**MSS**

1.  User requests to list companies.

2.  AddressBook shows a list of companies.

3.  User requests to sort the companies by deadline in a specific order (ascending or descending).

4.  AddressBook sorts and displays the companies based on the deadline in the specified order.

    Use case ends.


**Extensions**

-   2a. The list is empty.

    Use case ends.

-   3a. The given order (ascending or descending) is invalid or not specified.

    -   3a1. AddressBook shows an error message.

        Use case resumes at step 2.

### Non-Functional Requirements

1. The system should be available for download on our GitHub release page in the form of a JAR file.
2. The system should work on any _mainstream OS_ as long as it has Java `11` or above installed.
3. The system should be able to hold up to 200 applications to companies without a noticeable sluggishness in performance for typical usage.
4. The response to any user input should become visible within 2 seconds.
5. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
6. Most of the commands should be easy to remember so that a new user can learn to use the system quickly.
7. Data should be stored locally in the device (i.e. user can access the file through the system or directly from the device).
8. The code should meet the coding standard of CS2103T for maintainability.

### Glossary

-   **Company**: A company that is offering an internship position
-   **Internship application**: An application made by the user to a company offering an internship position
-   **Mainstream OS**: Windows, Linux, Unix, OS-X

---

## **Appendix B: Planned Enhancements**

### **1. More Specific Success Message for Company**

### **2. Make Recruiter Name, Phone and Email Parameters Optional in Add Command**

### **3. Omit Alphanumeric Checks for Company Name, Recruiter Name and Role Parameters**

### **4. Enhanced Flexibility in Phone Number Parameter Input**

### **5. Find Feature Enhancement 1**

### **6. Find Feature Enhancement 2**

### **7. Improve Error Message for Deadline Parameter**

### **8. Enhance Flexibility in Deadline Parameter Input**

### **9. Allow Multiple Indexes Input for Delete Command**

### **10. Enhance Remark Feature**

---

## **Appendix C: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and Shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. _{ more test cases …​ }_

### Deleting a Company

1. Deleting a company while all companies are being shown

    1. Prerequisites: List all companies using the `list` command. Multiple companies in the list.

    2. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

    3. Test case: `delete 0`<br>
       Expected: No company is deleted. Error details shown in the status message. Status bar remains the same.

    4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

2. _{ more test cases …​ }_

### Saving Data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

2. _{ more test cases …​ }_

## **Appendix D: Effort**
