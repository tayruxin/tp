---
layout: page
title: User Guide
---

Never miss an internships interview with LinkMeIn! Keep track of all your deadlines in an organised fashion.

* Table of Contents for the User Guide
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start ##

1. Product only runs on Java 11

2. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Current Features ##

### Adding a company : `add` ###

You scroll through your LinkedIn feed and see a company that you are interested in. 
You want to start on the application later, but you are afraid that you will forget about it. 
Add the company to LinkMeIn to keep track of the application process using the `add` command!

**Format**:
`add c/COMPANY_NAME r/ROLE s/APPLICATION_STATUS d/DEADLINE n/RECRUITER_NAME e/EMAIL p/PHONE_NUMBER [pr/priority]`

**Examples**:
* `add c/Tiktok r/Software Engineer s/PA n/John Tan d/10-10-2023 e/johntan@example.com p/987654321` adds a Company with 
the information provided into LinkMeIn. 
* `add c/Google n/Mary r/Data Analyst s/R d/11-11-2023 e/johntan@example.com p/987654321 pr/high`adds a Company with
  the information provided into LinkMeIn.

**Note**: 
* Compulsory fields include COMPANY_NAME, RECRUITER_NAME, ROLE, APPLICATION_STATUS, EMAIL and PHONE_NUMBER.
* PRIORITY is optional. If not specified, the default priority is `none`.
* Order of input **does not** matter.
* DEADLINE should be in DD-MM-YYYY format. 
* PRIORITY should be one of the following: `high`, `medium`, `low`, `none`. PRIORITY is case-insensitive. 
* APPLICATION_STATUS should be one of the following: `PA`, `PI`, `PO`, `A`, `R`. APPLICATION_STATUS is case-insensitive. 
The table below shows the meaning of each status.

| Prefix | Application Status     |
|--------|------------------------|
| PA     | PENDING APPLICATION    |
| PI     | PENDING INTERVIEW      |
| PO     | PENDING OUTCOME        |
| A      | ACCEPTED               |
| R      | REJECTED               |

**What you will see when `add` command succeeds:**
| Before                                                     | After                                              |
|------------------------------------------------------------|----------------------------------------------------|
| ![AddCommand.png](images/add-command/BeforeAddCommand.png) | ![img.png](images/add-command/AfterAddCommand.png) |

### Listing all contacts : `list`

Want to see all of your applications in one list? You can do so with the `list` command!

**Format:** `list`

**Examples:** 
- `filter s/PI` followed by `list` changes from showing only applications with status "Pending Interview".
to all companies added in LinkMeIn.

**Note:**
- Whenever LinkMeIn is started, the list of companies on the right is what you will see after the `list` command is executed.
- Typical use case for `list` command is to change the current list of companies that is being viewed back to the 
full list of companies added in LinkMeIn.

**What you will see when `list` command succeeds:**

| Before                                                   | After                                           |
|----------------------------------------------------------|-------------------------------------------------|
| ![ListCommand.png](images/filter-command/FilterByPI.png) | ![img.png](images/list-command/ListCommand.png) |


### Find a company: `find` ###

**Format:** `find KEYWORD [MORE_KEYWORDS]...`

You wish to find a specific company in the list of companies. You can use the find command to find the
company that you are looking for. The find command allows you to find the company whose name contain any of the
given keywords.

- The search is case-insensitive. e.g `tiktok` will match `TikTok`.
- The order of the keywords does not matter. e.g. `tiktok google` will match `Google TikTok`.
- Only the company name is searched.
- Only full words will be matched e.g. `tik` will not match `tiktok`.
- Only companies with names that contain all the keywords will be returned e.g. `tiktok google` will match `Google TikTok` but not `TikTok`.

**Examples:**
* `find TikTok` returns `TikTok`
* `find TikTok Google` returns `TikTok Google`

**What you will see when command succeeds:**

![img.png](FindCommand.png)


### View full company information: `view` ###

You look at the list of companies and you see a company that has not been getting back to you. You can use the view 
command to view the company's recruiter's contact details to follow up with them. The view command allows you to view
the company detail of any company from the list of companies that you have added.

**Format:** `view INDEX`

* The index refers to the index number shown in the displayed company list.
* The index must be a positive integer.
* The index must not be out of bounds.
* You can only view one company at a time.

**Example:**
* `view 1` displays application details of the first company in the full list

**What you will see when command succeeds:**

| Before                            | After                            |
|-----------------------------------|----------------------------------|
| ![img.png](BeforeViewCommand.png) | ![img.png](AfterViewCommand.png) |


The company’s information should be listed on the left panel and you will see the following fields:
1. COMPANY_NAME 
2. PRIORITY
3. ROLE
4. DEADLINE
5. APPLICATION_STATUS
6. RECRUITER_NAME
7. EMAIL 
8. PHONE


### Deleting a company : `delete` ###

Whether you accidentally added a company or no longer want to track it, don't worry LinkMeIn got you covered.
You can use the delete command to delete the company from the list of companies that you have added.

**Format:`delete INDEX`**

* The index refers to the index number shown in the displayed company list.
* The index must be a positive integer.
* The index must not be out of bounds.
* You can only delete one company at a time.

**Examples:**
* `list` followed by `delete 1` deletes the first contact in the full list of companies. 
* `find` TikTok followed by `delete 1` deletes the first contact in the results of find TikTok

**What you will see when command succeeds:**

Company at specified index removed and list of companies updated

| Before | After                              |
|--------|------------------------------------|
| ![img.png](BeforeDeleteCommand.png) | ![img.png](AfterDeleteCommand.png) |

>
> Note: If you are viewing the details of the company that you are deleting, the details panel will be cleared too.


### Edit a company's information: `edit` ###
If you made a typo or need to update the progress of application,
use the `edit` command to input the accurate details.

**Format:** `edit INDEX [c/COMPANY_NAME] [n/RECRUITER_NAME] [r/ROLE] [s/APPLICATION_STATUS] [d/DEADLINE] [e/EMAIL] [p/PHONE_NUMBER] [pr/PRIORITY] [nt/NOTE]`

* `INDEX`: Edits the company at INDEX number shown in the displayed company list.
* `[c/COMPANY_NAME]` etc. : type prefix `c/` followed by new company name to edit company name.
* At least one of the optional fields must be provided.

:warning: When editing the note, the existing note of the company will be removed i.e adding of note is not cumulative.

**Examples:** </br>
1.Type `edit 2 s/PI r/Frontend Developer` 
![EditDemo1.png](images/EditDemo1.png)
2.The 2nd company's the status and role is edited to be Pending Interview and Frontend Developer respectively.
![EditDemo2.png](images/EditDemo2.png)



### Clear all entries: `clear` ###
Clears all entries of internship application.

**Format:** `clear`

:warning: Entries cannot be recovered after clearing.

### Exit LinkMeIn: `exit` ###
Exits the program and closes the window.

**Format:** `exit`


### Filter companies by application status: `filter` ###
Can't remember which applications you haven't submitted or want to prepare for the companies that have offered you an interview? 
Filter the list of companies by their application status using the `filter` command!

**Format:** `filter s/APPLICATION_STATUS`

**Examples:**
* `list` followed by`filter s/PI` filters the list of companies to show only companies with status "Pending Interview".

**Note:**
- APPLICATION_STATUS should be one of the following: `PA`, `PI`, `PO`, `A`, `R`. APPLICATION_STATUS is case-insensitive.

**What you will see when `filter` command succeeds:**

| Before                                                  | After                                            |
|---------------------------------------------------------|--------------------------------------------------|
| ![ListCommand.png](images/list-command/ListCommand.png) | ![img.png](images/filter-command/FilterByPI.png) |


## Upcoming Features ##

TBD

--------------------------------------------------------------------------------------------------------------------

## FAQ ##

Q: How do I load data from another computer?

A: Delete the `addressbook.json` file (stored at `[JAR file location]/data/addressbook.json` )
from the computer that you wish to use LinkMeIn on. Then, copy over the `addressbook.json` file
from the computer which you no longer wish to use LinkMeIn on. After which, boot up LinkMeIn
to check whether your doctor information is properly loaded into the new computer.

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
| **Filter** | `filter s/APPLICATION_STATUS` <br/><br/> e.g., `filter s/PA`                                                                                                                                                                  |
| **List**   | `list`                                                                                                                                                                                                                        |

