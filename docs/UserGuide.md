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
   
   * `add stu /n John Doe /i A0123456Y /p 91234567 /e johndoe@ex.com` : Adds the Student `John Doe` to your contact list.
   
   * `add ta /n Jane Smith /i A0654321Y /p 97654321 /e janesmith@ex.com` : Adds the TA `Jane Smith` to your contact list.
   
   * `delete 3` : Deletes the 3rd contact shown in the displayed list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.


6. If a command is not recognized, a message containing the correct usage of the command will be shown.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Basic Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add stu /n NAME`, `NAME` is a parameter that can be used as `add stu /n John Doe`.

* Parameters with `...` after them can be supplied by the user for zero, one or more times.<br>
  e.g. `[/t TAG...]` can be used as `/t Assignment1` or `/t Assignment1 Assignment2`(`Assignment1` and `Assignment2`
would be treated as two different tags. Refer to the [Tagging](#tagging) section for more information).

* Items in square brackets are optional.<br>
  e.g. `/n NAME [/p PHONE]` can be used as `/n John Doe /p 91234567` or as `/n John Doe`.

* Vertical bar (pipe) `|` is used to denote alternatives.<br>

* Pipe symbol and square brackets together `[|]` denote alternative items that are optional.<br>
  e.g. in `add [stu | ta] /n NAME`, `stu` and `ta` are alternatives, either exactly one or none of them should be used.
  Refer to the [Add](#adding-a-student-or-ta-add-stu-add-ta) command section for more information).

* Parameters can be supplied in any order.<br>
  e.g. if the command specifies `/n NAME /i ID`, `/i ID /n NAME` is also acceptable and has the same effect.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`)
will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span across
multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>


### Adding a Student or TA: `add stu`, `add ta`

Adds a Student/TA to the address book.

Format:
* To add a Student,<br>
  `add [stu] /n NAME /i ID /p PHONE /e EMAIL`

* To add a TA,<br>
  `add ta /n NAME /i ID /p PHONE /e EMAIL`
<div markdown="span" class="alert alert-primary">

:bulb: **Notes:**<br>

* Every person is saved either as a Student or TA. If the type of the person is not specified, the person will be
  saved as a Student by default.<br>
* Each person's ID is unique, so you cannot add 2 people with the same ID.<br>

</div>

Examples:
* `add stu /n Alex Yeoh /i A0777777L /p 87438807 /e alexyeoh@ex.com`
* `add ta /n Charlotte Oliveiro /i A2222222P /p 93210283 /e charlotte@ex.com`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [/n NAME] [/p PHONE] [/e EMAIL]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
  The index **must be a positive integer** 1, 2, 3, ...​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* A person's `type` (`stu` or `ta`) and `ID` cannot be edited.

Examples:
*  `edit 1 /p 91234567 /e johndoe@example.com` Edits the phone number and email address of the 1st person to be
   `91234567` and `johndoe@example.com` respectively.
*  `edit 2 /n Betsy Crower` Edits the name of the 2nd person to be `Betsy Crower`.

### Listing all persons : `list`

Shows a list of all persons in TrAcker (undo any filtering by [find](#locating-persons-find) command).

Format: `list`

### Locating persons: `find`

Filters all persons whose contact details contain each of the specified keywords 
under the specified flag and displays them as a list with index numbers.

Format: `find [stu | ta] [/n NAME] [/i ID] [/p PHONE] [/e EMAIL] [/t TAGS...]`

* At least one of the optional fields must be supplied.
* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords under each flag does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Subwords will be matched e.g. `Han` will match `Hans`
* Between optional fields supplied, the search filters for 
persons meeting criteria specified for ALL fields at the same time, (i.e. `AND` search).
    
    e.g. `find stu /n John /i 6Z` will find all Students who have both a name containing `John` and an ID containing `6Z`. 
* When performing search for the `TAGS` field (more information on tags [here](#tagging)):
    * For tutorial tags, subword matching is performed
    * For other types of tags, it performs full word matching
    * The search filters for persons meeting ANY criteria, (i.e. `OR` search).
    
    e.g. `find /t wed assignment1` will find all persons with a tutorial tag where `wed` is a subword
or an `assignment1` tag<br>
    e.g. `find stu /n John /t wed assignment1` will find all persons with a name containing `John` AND
either a tutorial tag where `wed` is a subword or an `assignment1` tag

Examples:
* `find ta` returns all TAs
  ![result for 'find ta'](images/findTaResult.png)

* `find /n John` returns `john` and `John Doe`


### Deleting persons : `delete`

Deletes the person(s) specified by their indices from the displayed person list. A popup will appear to confirm the 
deletion.

Format: `delete (all | INDEX [OTHER_INDICES...])`

* Deletes the person(s) at the specified `INDEX`.
* If `all` is used, all persons in the displayed list are deleted. The displayed list might not be the same as the full
list. For example, if the `delete all` command is used after a [`find NAME`](#locating-persons-find) command,
all contacts found by the `find NAME` command would be deleted but not those excluded from the displayed list.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the full contact list.
* `find /n Betsy` followed by `delete 1` deletes the 1st person in the results of the `find /n Betsy` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TrAcker data is saved in the hard disk automatically after each command that changes the data. There is no need to save manually.

### Editing the data file

TrAcker saves its data as a JSON file `[JAR file location]/data/addressbook.json` automatically. Advanced users are welcome to update data directly by editing that data file.
**While the app is running**, edits to the `addressbook.json` file will not be reflected in the UI. To view the changes, rerun the application.<br>
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file renders its format invalid, TrAcker will discard all existing data and start with an empty data file in the next run. Hence, it is recommended to have a backup of the data file before editing it. 

Furthermore, certain edits can cause TrAcker to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, please edit the data file only if you are confident that you can update it correctly.
</div>

--------------------------------------------------------------------------------------------------------------------

## Tagging

With TrAcker tags, you can conveniently track students' assignment completion status, their assigned tutorial groups as well as their tutorial attendance records. 
You are also able to track TA's assigned tutorial slots and their availability for other tutorial slots in case substitutions are needed.

TrAcker allows the use of three different types of tags : **Assignments, Attendance,** and **Tutorial** tags which can be attached to Students and TAs respectively.
The different tag types along with their corresponding tag statuses are described below.

### Tag Status

| Tag type   | Status                                                                                                                                                                                                                                                                                                                                                                                             |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Assignment | `cg` : <mark style="background-color: green">COMPLETE_GOOD</mark> (completed before deadline)<br/>`cb` : <mark style="background-color: orange">COMPLETE_BAD</mark> (completed after deadline)<br/>`ig` : <mark style="background-color: grey">INCOMPLETE_GOOD</mark> (incomplete before deadline)<br/>`ib` : <mark style="background-color:red">INCOMPLETE_BAD</mark> (incomplete after deadline) |
| Attendance | `p` : <mark style="background-color:  green">PRESENT</mark><br/>`a` : <mark style="background-color:red">ABSENT</mark><br/>`awr` : <mark style="background-color:orange">ABSENT_WITH_REASON</mark>                                                                                                                                                                                                 |
| Tutorial | `as` : <mark style="background-color: #3e7b91">ASSIGNED</mark><br/>`av` : <mark style="background-color: white">AVAILABLE</mark>                                                                                                                                                                                                                                                                   |

### Tag Name
Regardless of tag type, tag names must abide by the following constraints:
- must be alphanumeric
- no whitespace between words in the tag
- tags attached to each person must have unique names (each person can only have one tag of a specific name, regardless of tag types. But tags of the same name can be attached to different contacts)

Here are some recommended tag names for the various tag types.

| Tag type   | Examples of recommended tag names |
|------------|-----------------------------------|
| Assignment | `Assignment1` `v1.1Issues`        |
| Attendance | `Week1` `Week2`                   |
| Tutorial   | `TUE08` `WED10` `THU09`           |


### Marking a tag : `mark`

Updates the status of the specified tag with the specified status for the specified person(s). If the
tag specified does not yet exist for the person(s), a new tag with the tag name and tag status will be
created and attached to the person(s).

<div markdown="span" class="alert alert-primary">

:bulb: **Notes:**<br>

* The type of the tag(s) to be updated/created are specified through their tag status<br>
* If a tag is to be marked with the status `cg`, `cb`, `ig` or `ib`, it would be identified as an assignment tag and displayed together with other assignmetn tags in the UI. Similarly for attendance and tutorial tags.<br>
* If a specific person(s) already has a tag with the same tag name as the tag that is to be marked, but his existing tag has a different tag type as the type identified by new status from the mark command, his original tag would then be replaced by the tag with new type and status but the same tag name.

</div>

Format: `mark ( all | INDEX [OTHER_INDICES...] ) /t TAG [OTHER_TAGS...] /ts TAG_STATUS`

* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* When `all` is used, the command will apply to all persons in the displayed list.
* When multiple `TAG`s are specified, the same `TAG_STATUS` will be applied to all the tags.
* `TAG_STATUS` must be one of the [above specified values](#tag-status)

Examples:
* `mark 1 /t Assignment1 /ts cg` updates the `Assignment1` tag
to <mark style="background-color: green">COMPLETE_GOOD</mark> for the 1st person in the displayed list if they already
have the tag. The `Assignment1` tag of <mark style="background-color: green">COMPLETE_GOOD</mark> status would be added
to the contact if they previously did not have the tag.
* `mark 2 3 /t week1 week2 /ts awr` updates the `week1` and `week2` tags to
<mark style="background-color: orange">ABSENT_WITH_REASON</mark> for the 2nd and 3rd persons in the displayed list
if they already have the tag. Both tags with specified status would be added to the two contacts if any of them
previously did not have the tags.
* `mark all /t TUE08 /ts as` updates the `TUE08` tag to <mark style="background-color: #3e7b91">ASSIGNED</mark> to
assign every person in the displayed list to the tutorial group TUE08 if they already have the tag. The `TUE08` tag
with specified status would be added to any listed contact that previously did not have the tag.

<div markdown="block" class="alert alert-info">

**:information_source: Note:** For **Tutorial** tags, the tutorial name must be that of a valid Tutorial tag in the 
list of available tutorial sessions defined with the [tuttag](#adding-a-tutorial--tuttag-add) command.
For example, in the third example above, `TUE08` should be added as a tutorial tag first using [`tuttag add /t TUE08`](#adding-a-tutorial-tuttag-add).

</div>

### Adding a Tutorial: `tuttag add`

Adds the specified Tutorial tag to the list of valid Tutorial tags. Tutorial tags (identified by Tutorial tag names) not from the valid Tutorial tag list cannot be used.

Format: `tuttag add /t TAG`

Examples:

* `tuttag add /t TUE08` adds TUE08 as a valid Tutorial tag.

### Deleting a Tutorial: `tuttag del`

Deletes the specified Tutorial tag from the valid Tutorial tag list. If the specified tag does not exist in the list, no change would happen.

Warning: All persons with the Tutorial tag (identified by tag name, regardless of tag status) will also have the Tutorial tag removed should this command execute successfully.

Format: `tuttag del /t TAG`

Examples:

* `tuttag del /t WED09` deletes WED09 from the valid Tutorial tag list, and removes the WED09 Tutorial tag from all persons.

### Listing All Tutorials: `tuttag list`

Lists all valid Tutorial tags in TrAcker.

Format: `tuttag list`

### Removing a tag: `removetag`

Removes an individual tag of the specified tag name (regardless of tag type) from a person. If the specified tag does not exist, the person's tags would remain unchanged.

Format: `removetag (all | INDEX [OTHER_INDICES...]) /t TAG`

* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `removetag 1  /t Assignment1` removes the `Assignment1` tag from the 1st person in the displayed list.
* `removetag 2  3 /t Assignment2` removes the `Assignment2` tag from the 2nd and 3rd person in the displayed list.
* `removetag all /t Assignment3` removes the `Assignment3` tag from every person in the displayed list.


### Locating available TAs for a tutorial group: `available`

Filters and lists all TAs who are available for (and not assigned to) a specified tutorial session to serve as replacement TAs.

Format: `available /g TUTORIAL`

* The search is case-sensitive and must match the specified tutorial group exactly.

Examples:
* `available /g TUE08` returns all TAs who are available for tutorial group `TUE08`

### Viewing help : `help`

Prompts a popup containing the link to access the user guide.

![help message](images/helpMessage.png)

Format: `help`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the data file it creates with the file that 
contains the data of your previous TrAcker home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.<br>

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                        | Format, Examples                                                                                                                           |
|-------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                       | `add [stu &#124; ta] /n NAME /i ID /p PHONE /e EMAIL ​` <br> e.g., `add stu /n Alex Yeoh /i A0777777L /p 87438807 /e alexyeoh@example.com` |
| **List**                      | `list`                                                                                                                                     |
| **Edit**                      | `edit INDEX [/n NAME] [/p PHONE] [/e EMAIL] ​`<br> e.g.,`edit 1 /p 91234567 /e johndoe@example.com`                                        |
| **Find**                      | `find [stu &#124; ta] [/n NAME] [/i ID] [/p PHONE] [/e EMAIL] [/t TAGS...]`<br> e.g., `find /t wed assignment1`                            |
| **Delete**                    | `delete (all &#124; INDEX [OTHER_INDICES...])`<br> e.g., `delete 3`                                                                        |
| **Clear**                     | `clear`                                                                                                                                    |
| **Exit**                      | `exit`                                                                                                                                     |
| **Mark**                      | `mark (all &#124; INDEX [OTHER_INDICES...]) /t TAG [OTHER_TAGS...] /ts TAG_STATUS`<br> e.g., `mark 1 /t Assignment1 /ts cg`                |
| **Create Valid Tutorial Tag** | `tuttag add /t TAG`<br> e.g., `tuttag add /t TUE08`                                                                                        |
| **Delete Valid Tutorial Tag** | `tuttag del /t TAG`<br> e.g., `tuttag del /t WED09`                                                                                        |
| **List Valid Tutorial Tags**  | `tuttag list`                                                                                                                              |
| **Remove Tag**                | `removetag INDEX /t TAG`<br> e.g., `removetag 1 /t Assignment1`                                                                            |
| **Available**                 | `available /g TUTORIAL`<br> e.g., `available /g TUES08`                                                                                    |
| **Help**                      | `help`                                                                                                                                     |
