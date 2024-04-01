---
layout: page
title: User Guide
---

**TrAcker** is a handy contact management app built for CS Head Teaching Assistants (TAs) in NUS.
Optimized for use via a command line interface, you can manage student assignments, attendance,
tutor availability and much more with just a few keystrokes!


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TrAcker.jar` from [here](https://github.com/AY2324S2-CS2103T-T11-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your **TrAcker** app.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar TrAcker.jar` 
   command to 
   run the application.<br>
   The GUI with some sample data should appear in a few seconds: <br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it.<br>
   Some example commands you can try:
   * `list` : Lists all contacts.
   
   * `add stu /n John Doe /i A0123456Y /p 91234567 /e johndoe@example.com` : Adds the Student `John Doe` to your contact list.
   
   * `add ta /n Jane Smith /i A0654321Y /p 97654321 /e janesmith@example.com` : Adds the TA `Jane Smith` to your contact list.
   
   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.


6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add stu /n NAME`, `NAME` is a parameter which can be used as `add stu /n John Doe`.

* Items in square brackets are optional.<br>
  e.g `/n NAME [/p PHONE]` can be used as `/n John Doe /p 91234567` or as `/n John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `/n NAME /i ID`, `/i ID /n NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a Student or TA: `add stu`, `add ta`

Adds a Student (TA) to the address book.

Format:
* To add a Student,<br>
  `add stu /n NAME /i ID /p PHONE /e EMAIL`

* To add a TA,<br>
  `add ta /n NAME /i ID /p PHONE /e EMAIL]`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
All persons are saved as either Students or TAs
</div>

Examples:
* `add stu /n Alex Yeoh /i A0777777L /p 87438807 /e alexyeoh@example.com`
* `add ta /n Charlotte Oliveiro A2222222P /p 93210283 /e charlotte@example.com`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [/n NAME] [/p PHONE] [/e EMAIL]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* A person's `ID` cannot be edited.

Examples:
*  `edit 1 /p 91234567 /e johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 /n Betsy Crower` Edits the name of the 2nd person to be `Betsy Crower`.

### Locating persons by name: `find`

Filters all persons whose contact details contain each of the specified keywords 
under the specified flag and displays them as a list with index numbers.

Format: `find [stu/ta] [/n NAME] [/i ID] [/p PHONE] [/e EMAIL]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords under each flag does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Prefixes will be matched e.g. `Han` will match `Hans`
* The search filters for persons meeting ALL criteria, (i.e. `AND` search).
    e.g. `find stu /n John` will find all Students whose names contain `John`.

Examples:
* `find John` returns `john` and `John Doe`
* `find ta` returns all TAs
  ![result for 'find ta'](images/findTaResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TrAcker data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TrAcker data is saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
