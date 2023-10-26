---
layout: page
title: User Guide
---
## Welcome to LinkMeIn!

_Master Your Internship Journey: Every Deadline, Every Detail._

LinkMeIn is a **desktop application** built for [NUS School Of Computing (SOC) students](https://www.comp.nus.edu.sg/) to
track their internship applications.

Here is a **quick** overview of your internship journey with LinkMeIn:
<br>
* An exciting internship listing has caught your attention. Don't let it slip through the cracks! **Add** your internship to your list of applications
* Did you get a response from the recruiter with an interview date? Congratulations! Navigate to LinkMeIn and **Edit** the application details to add this crucial information
* Remember that application you sent out months ago? Need to touch base with that recruiter? With LinkMeIn, you can **List** all your applications by their added dates.
  No more fumbling through emails or notes; reconnect with ease!
* Curiosity piqued about a particular application's details? **View** its comprehensive breakdown on the left panel, making sure you're always in the loop
* If at any point you're feeling a tad overwhelmed or unsure about how to proceed, worry not! **Help** is just a click away on LinkMeIn.
* You've got the offer! It's time to celebrate and de-clutter. You can **Delete** that application from your list. Here's to new beginnings!
* But wait, before you move forward, how about helping the ones following in your footsteps? **Find** that company you applied to within LinkMeIn and share the recruiter's
  details with your juniors, ensuring their path is a little smoother, thanks to you.
  </br>

LinkMeIn combines the rapid **efficiency** of CLI with the visual clarity of GUI,
offering a tailored experience for internship management that outpaces the generic
spreadsheets of Excel. Dive into precision with commands and visualize your progress seamlessly.


## Information on icons used in this document

|                                                   Icon                                                   |                                                  Meaning                                                  |
|:--------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------:|
| <div style="border:1px solid red;padding:10px;background-color:#ffe6e6;">⚠️ <strong>Warning</strong></div> | Alerts users to potential pitfalls or things to be cautious of when using your application.                |
| <div style="border:1px solid #f9eb9e;padding:10px;background-color:#ffffe0;">💡 <strong>Tip</strong></div>  | Provides users with additional insights or more efficient ways to use your application.                   |
| <div style="border:1px solid #c8e1ff;padding:10px;background-color:#e8f4ff;">ℹ️ <strong>Note</strong></div> | Highlights supplementary information that users should be aware of, but isn't necessarily mission-critical. |
| <div style="border:1px solid #d3d3d3;padding:10px;background-color:#f5f5f5;"> </> <strong>Code</strong></div> | Indicates commands or programming-related content that can be typed or referred to.                        |


## Table of Contents

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Make sure that you have **Java 11 or above** installed in your Computer.

  - [How to check if you have Java installed?](#faq-1)
  - [How to check if you have Java 11 or above?](#faq-2))

2. Download the latest jar file from [here]()

3. Copy the file to the folder you want to use as the home folder for your LinkMeIn.

  - Create a new folder (you can name it LinkMeIn) where you would like to house the application
    (e.g., Desktop, Documents, etc.)
  - Move the downloaded jar file as shown:

    - For Windows users
    - For Mac users

4. Launch LinkMeIn

  - For Windows users

    - Double-click on the `LinkMeIn.jar` file to launch the application

  - For Mac users

    - Open up your terminal (Command +Space > type Terminal > Enter)
    - Navigate to the folder where you have placed the jar file with the `cd` command
    - For example if you have placed the jar file in the Documents folder, type `cd Documents` and press Enter

<div style="border:1px solid red;padding:10px;background-color:#ffe6e6;">
<strong>Warning:</strong> **Do not** move or delete the `data` folder as it contains the data of your applications.
</div>

You should notice the GUI of the application pop up.
1. Learn more about navigating the GUI [here]().
2. For new users, learn to use LinkMeIn [here]().
3. For advanced users, view all feature details [here]().
--------------------------------------------------------------------------------------------------------------------

## Current Features ##

### Adding a company : `add` ###

Adds a company to the address book.
The company must have the required fields:
CompanyName, ApplyingRole, ApplicationStatus, Deadline (dd-MM-yyyy), RecruiterName, Email and PhoneNumber.
Order of input **does not** matter.

| Prefix | Application Status     |
|--------|------------------------|
| PA     | PENDING APPLICATION    |
| PI     | PENDING INTERVIEW      |
| PO     | PENDING OUTCOME        |
| A      | ACCEPTED               |
| R      | REJECTED               |

**Format**:</br>
`add c/COMPANY_NAME r/ROLE s/APPLICATION_STATUS d/DEADLINE n/RECRUITER_NAME e/EMAIL p/PHONE_NUMBER [t/tags]`

**Examples**:
* `add c/Tiktok r/Software Engineer s/PA n/John Tan d/10-10-2023 e/johntan@example.com p/987654321`
* `add c/Google n/Mary r/Data Analyst s/R d/11-11-2023 e/johntan@example.com p/987654321 t/high`
* `add c/Meta r/Data Scientist s/PI n/Mary d/12-12-2023 e/mary@example.com p/91234567`

**Acceptable values for each parameter:**<br>
No other string separators other than c/, n/, r/, s/, d/, e/, p/, t/.
Otherwise, the **entire** command will be considered invalid and all data inputted will be discarded.

Example of invalid input: `c/Google n/Mary r/Data Analyst a/R d/11-11-2023 f/`</br>
Explanation: Invalid f/ string separator.

**Expected output when command succeeds**: </br>
`New company added: {COMPANY_NAME}`

**GUI Changes:** </br>
The company should be added to the existing list of companies on the right.</br>
The company’s information should also be listed on the left panel, which displays all the added fields information.

**Expected output when command fails:** </br>
`Invalid command format!`</br>
`add: Adds a company to the address book. Parameters: c/COMPANY_NAME r/ROLE s/STATUS d/DEADLINE n/RECRUITER_NAME p/PHONE e/EMAIL [t/TAG]...`</br>
`Example: add c/Google r/Software Engineer s/PA d/10-10-2023 n/Francis Tan p/98765432 e/johnd@example.com t/high`

***To be further updated in V1.3***
* If the COMPANY_NAME field is missing:
  `Invalid command format! Missing COMPANY_NAME. Format is add c/COMPANY_NAME
  n/RECRUITER_NAME r/ROLE a/APPLICATION_STATUS e/EMAIL p/PHONE_NUMBER`
  </br>
  </br>
* If the RECRUITER_NAME field is missing:
  `Invalid command format! Missing RECRUITER_NAME. Format is add c/COMPANY_NAME n/RECRUITER_NAME
  r/ROLE a/APPLICATION_STATUS [e/EMAIL] [p/PHONE_NUMBER]`
  </br>
  </br>
* If the APPLICATION_STATUS field is missing:
  `Invalid command format! Missing RECRUITER_NAME. Format is add
  c/COMPANY_NAME n/RECRUITER_NAME r/ROLE a/APPLICATION_STATUS [e/EMAIL] [p/PHONE_NUMBER]`
  </br>
  </br>
* If the ROLE field is missing:
  `Invalid command format! Missing RECRUITER_NAME. Format is add c/COMPANY_NAME
  n/RECRUITER_NAME r/ROLE a/APPLICATION_STATUS [e/EMAIL] [p/PHONE_NUMBER]`

**Expected UI:**

![AddCommand.png](AddCommand.png)

### Listing all contacts : `list`

Lists all the contacts in the application at present.

**Format:** `list`

**Examples:** `list`

**Expected UI**:

![ListCommand.png](ListCommand.png)

The list of companies should be listed in the following format below:
```
{COMPANY_NAME 1} {ROLE} {APPLICATION_STATUS} {DEADLINE}

{COMPANY_NAME 2} {ROLE} {APPLICATION_STATUS} {DEADLINE}

{COMPANY_NAME 3} {ROLE} {APPLICATION_STATUS} {DEADLINE}
```
### Deleting a company : `delete` ###

Deletes a company from the address book.

**Format:`delete INDEX`**

* Deletes the contact at the specific INDEX
* The index refers to the index number shown in the displayed contact list
* The index must be a positive integer

**Examples:**
* `list` followed by `delete 1` deletes the first contact in the full list
* `find` TikTok followed by `delete 1` deletes the first contact in the results of find TikTok

**Acceptable values for each parameter:**
* INDEX must be a number. If not the entire command will be considered invalid input.
* INDEX must not be out of bounds. If not the entire command will be considered invalid input.
* INDEX must be more than zero. If not the entire command will be considered invalid input.

**Expected output when command succeeds:**
```
“{COMPANY_NAME} application record has been deleted!
You have __ contacts in the list.”
```

**GUI Changes:** </br>
Company at specified index removed and list of companies updated

**Expected output when command fails:**
</br>
If INDEX is out of bounds:
</br>
`The company index provided is invalid`

If INDEX is zero or negative:
</br>
`Invalid command format`
`delete: Deletes the company identified by the index number used in the displayed company list.`
`Parameters: INDEX (must be a positive integer)`

If INDEX is not a number: </br>
`Invalid command format`
`delete: Deletes the company identified by the index number used in the displayed company list.`
`Parameters: INDEX (must be a positive integer)`

**Expected UI**

![img_2.png](DeleteCommand.png)


### View full company information: `view` ###
View the full company information of a particular company on the left panel.

**Format:** `view INDEX`

**Examples:**
* `view 1` displays application details of the first company in the full list
* `view 2` displays application details of the second company in the full list

**Acceptable values for each parameter:**
* INDEX must be a number. If not the entire command will be considered invalid input.
* INDEX must not be out of bounds. If not the entire command will be considered invalid input.
* INDEX must be more than zero. If not the entire command will be considered invalid input.

**Expected output when command succeeds:**</br>
`Viewing Company: {COMPANY_NAME}`

**GUI Changes:** </br>
The company’s information should be listed on the left panel, which includes the following fields:

1. COMPANY_NAME
2. APPLICATION_STATUS
3. ROLE
4. DEADLINE
5. RECRUITER_NAME
6. EMAIL
7. PHONE
8. PRIORITY

**Expected output when command fails:** </br>

* If INDEX is out of bounds: </br>
  `The company index provided is invalid`
* If INDEX is zero or negative: </br>
  `Invalid command format`
  `view: Views the company identified by the index number used in the displayed company list.`
  `Parameters: INDEX (must be a positive integer)`
* If INDEX is not a number: </br>
  `Invalid command format`
  `view: Views the company identified by the index number used in the displayed company list.`
  `Parameters: INDEX (must be a positive integer)`

**Expected UI**

![ViewUI.png](ViewUI.png)

### Edit a company's information: `edit` ###
Edits the information of a particular company.

**Format:** `edit INDEX [c/COMPANY_NAME] [n/RECRUITER_NAME] [r/ROLE] [s/APPLICATION_STATUS] [d/DEADLINE] [e/EMAIL] [p/PHONE_NUMBER] [t/TAG]…`

* Edits the company at the specified INDEX. The index refers to the index number shown in the displayed company list. The index must be a positive integer 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the company will be removed i.e adding of tags is not cumulative.
* You can remove all the company’s tags by typing t/ without specifying any tags after it.

**Examples:**
* `edit 2 s/Pending Application r/frontend developer` edits the status and role of the 2nd person to be Pending Application and frontend developer respectively.
* `edit 3 e/example@abc.com t/` edits the email of the 3rd person to be example@abc.com and clears all existing tags.

**Acceptable values for each parameter:**
* INDEX must be a number. If not the entire command will be considered invalid input.
* INDEX must not be out of bounds. If not the entire command will be considered invalid input.
* INDEX must be more than zero. If not the entire command will be considered invalid input.
* No other string separators other than c/, n/, r/, s/, d/, e/, p/. Otherwise, the entire command will be considered invalid and all data inputted will be discarded.

**Expected output when command succeeds:**</br>
`{COMPANY_NAME} company edited.`

**Expected output when command fails:** </br>
* If INDEX is out of bounds: </br>
  `The company index provided is invalid`
* If invalid string separator: </br>
  `Invalid command format! edit: Edits the details of the company identified by the index number used in the displayed company list. Existing values will be overwritten by the input values.
  Parameters: INDEX (must be a positive integer) [c/COMPANY_NAME] [n/RECRUITER_NAME] [r/ROLE] [s/APPLICATION_STATUS] [d/DEADLINE] [e/EMAIL] [p/PHONE] [t/TAG]...
  Example: edit 1 p/91234567 e/johndoe@example.com`
* If empty input after string separator: </br>
  `Please enter a valid {field}`

**Expected UI** <br/>
![EditUI.png](EditUI.png)

## Upcoming Features ##

TBD

--------------------------------------------------------------------------------------------------------------------

## FAQ ##

### 1. How do I check if I have Java installed? ###

* Open up your terminal and type `java -version`. If you have Java installed, you should see something like this:
```
java version "
OpenJDK Runtime Environment (build
OpenJDK 64-Bit Server VM (build
```
![img_1.png](img_1.png)
* If you do not have Java installed, you can download it [here](https://www.oracle.com/sg/java/technologies/javase-downloads.html).
* If you are using Windows, you can refer to this [guide](https://www.java.com/en/download/help/windows_manual_download.html) to install Java.
* If you are using Mac, you can refer to this [guide](https://www.java.com/en/download/help/mac_install.html) to install Java.
* If you are using Linux, you can refer to this [guide](https://www.java.com/en/download/help/linux_x64_install.html) to install Java.
* If you are using Ubuntu, you can refer to this [guide](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-20-04) to install Java.

### 2. How do I check if I have Java 11 or above? ###
* Open up your terminal and type `java -version`. If you have Java 11 or above installed, you should see something like this:
``` 
openjdk version "
OpenJDK Runtime Environment (build
OpenJDK 64-Bit Server VM (build
```
--------------------------------------------------------------------------------------------------------------------

## Known issues ##

TBD.
--------------------------------------------------------------------------------------------------------------------

## Command summary ##

| Action     | Format, Examples                                                                                                                                                                                                              |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add c/COMPANY_NAME r/ROLE d/DEADLINE s/APPLICATION_STATUS n/RECRUITER_NAME e/EMAIL p/PHONE_NUMBER [t/tags]` <br><br> e.g., `add c/Tiktok r/Software Engineer s/PA d/11-11-2023 n/John Tan e/johntan@example.com p/987654321` |
| **Delete** | `delete INDEX`<br><br> e.g., `delete 3`                                                                                                                                                                                       |
| **View**   | `view INDEX`<br><br> e.g., `view 3`                                                                                                                                                                                           |                                                                                                                                                                                     |
| **Edit**   | `edit INDEX [c/COMPANY_NAME] [n/RECRUITER_NAME] [r/ROLE] [a/APPLICATION_STATUS] [d/DEADLINE] [e/EMAIL] [p/PHONE_NUMBER] [t/TAG]…` <br/><br/> e.g., `edit 2 s/Pending Application r/frontend developer`                        |
