# Purpose

This project was created by The Sofware Design Lab at the Universidad de los Andes. The major goal of the MutAPK project is to enable mutation testing over android application by third-party services that provide on-the-cloud infraestructures. This project uses APK files instead of source code to generate mutants. It is based on MDroid+ project that generates mutants from source code. MutAPK provides the 38 Android-specific mutation operators founded by MDroid+ researchers that can be easily seeded into target application due to working with compiled files. 

# Video

<p align="center">
<iframe width="500" height="282" src="https://www.youtube.com/embed/Pa448bHXKxY" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></p>

# Publications

- _"MutAPK: Source-Codeless Mutant Generation for Android Apps"_, [Camilo Escobar-Velásquez](https://caev03.github.io), [Michael Osorio-Riaño](https://MichaelOsorio2017.github.io), and  [Mario Linares-Vásquez](https://profesores.virtual.uniandes.edu.co/mlinaresv/en/inicio-en/), _he 34th IEEE/ACM International Conference on Automated Software Engineering ([ASE'19](https://2019.ase-conferences.org/))_, Demonstrations Track, San Diego, CA, USA, November 11th - 15th, 2019, to appear 4 pages (53.7% Acceptance Rate) [[pdf](/assets/pdfs/escobar2019mutapk.pdf)][[bibtex](/assets/pdfs/escobar2019mutapk.bib)]

# Summary
MutAPK is a mutation analysis framework for Android applications over APK Files.
MutAPK implements 35 mutation operators specifically for Android apps, covering the following categories:
- Activity/Intents
- Android Programming
- Back-End Services
- Connectivity
- Data
- Database
- General Programming
- GUI
- I/O
- Non-Functional Requirements

The complete list of mutation operators and their specification is available at the [MutAPK website](http://thesoftwaredesignlab.github.io/MutAPK/).
Given an Android App APK, MutAPK first extracts the Potential Fault Profile (PFP) and then automatically seeds mutants generating mutated copies of the App.

# Compile
Download and compile MutAPK with the following commands:
```
git clone https://github.com/TheSoftwareDesignLab/MutAPK.git
cd MutAPK
mvn clean
mvn package
```
The generated runnable jar can be found in: ``MutAPK/target/MutAPK-0.0.1.jar``

# Usage
To run MutAPK use the following command, specifying the required arguments:
```
java -jar MutAPK-0.0.1.jar <APKPath> <AppPackage> <Output> <ExtraComponentFolder> <operatorsDir> <multithread> <amountMutants>?
```
### Arguments
Provide the following list of required arguments when running MutAPK:
1. ``APK path``: relative path of the apk to mutate;
2. ``AppPackage``: App main package name;
3. ``Output``: relative path of the folder where the mutantns will be created;
4. ``ExtraCompFolder``:  relative path of the extra component folder (``MutAPK/extra/``);
5. ``operatorsDir``: relative path to the folder containing the operators.properties.
6. ``multithread`` : true or false, specifying whether the mutant generation should be multithreaded or not.
7. ``amountOfMutants`` : Amount of mutants to be generated [OptionalParameter]

Mutation operators can be selected or deselected editing the ``operators.properties`` file. To deselect an operator, either comment (#) or delete the corresponding line.
### Example
```
cd MutAPK
java -jar target/MutAPK-1.0.0.jar foo.apk or.foo.app mutants/ extra/ . true
```

### Output
The output directory will contain a log file that summarise the mutant generation process and a folder for each generated mutant. 
The mutants folders are named with the corresponding mutant ID (i.e., numerical ID). The log file contains information about the mutation process as well as the type and location of each mutant generated.

# Mutation Operators

## Activity/Intents

> ___
### ActivityNotDefined <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-info" style="float: right;">Text</span>
___
**Description:**  
Delete an activity entry in the Manifest file  
**Code Example:**  
Before:  
```xml
1 < activity android:name=".ImportActivity"
           android:label="@string/title_import" />
```
After:  
```
1 
```
___

> ___
### DifferentActivityIntentDefinition <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Replace the Activity.class argument in an Intent instantiation.  
**Code Example:**  
Before:  
```
1 const-class v3, Lcom/fsck/k9/activity/MessageCompose;  
2 invoke-direct {v1, v2, v3}, Landroid/content/Intent;
  -><init>(Landroid/content/Context;Ljava/lang/Class;)V
```
After:
```
1 const-class v1, Lcom/fsck/k9/activity/Accounts; 
2 invoke-direct {v1, v2, v3}, Landroid/content/Intent;
    -><init>(Landroid/content/Context;Ljava/lang/Class;)V
```
___

> ___
### InvalidActivityName <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-info" style="float: right;">Text</span>
___
**Description:**  
Randomly insert typos in the path of an activity defined in the Manifest file.  
**Code Example:**  
Before:  
```xml
1 < activity android:name=".AboutActivity"
           android:label="@string/title_about" />
```
After:
```xml
1 < activity android:name=".AbutActivity"
           android:label="@string/title_about" />
```
___

> ___
### InvalidKeyIntentPutExtra <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Randomly generate a different key in an Intent.putExtra(key, value) call  
**Code Example:**  
Before:  
```
1 const-string v1, "message_decryption_result" 
2 invoke-virtual {v0, v1, p3}, Landroid/content/Intent;
    ->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)
    Landroid/content/Intent;
```
After:
```
1 const-string v1, "3a55422d7ac44b6aac475cc5122b992b"
2 invoke-virtual {v0, v1, p3}, Landroid/content/Intent;
    ->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)
    Landroid/content/Intent;
```
___

> ___
### InvalidLabel <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-info" style="float: right;">Text</span> 
___
**Description:**  
Replace the attribute "android:label" in the Manifest file with a random string  
**Code Example:**  
Before:  
```xml
1 < activity android:name=".VehicleActivity" 
            android:label="@string/title_vehicle" />
```
After:
```xml
1 < activity android:name=".VehicleActivity" 
            android:label="ecab6839856b426fbdae3e6e8c46c3" />
```
___

> ___
### NullIntent <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Replace an Intent instantiation with null  
**Code Example:**  
Before:  
```
1 new-instance v1, Landroid/content/Intent;
2 iget-object v2, p0, Lio/github/hidroh/materialistic/accounts/AccountAuthenticator;
    ->mContext:Landroid/content/Context;
3 const-class v3, Lio/github/hidroh/materialistic/LoginActivity;
4 invoke-direct {v1, v2, v3}, Landroid/content/Intent;
    -><init>(Landroid/content/Context;Ljava/lang/Class;)V
```
After:
```
1 const/4 v1, 0x0
```
___

> ___
### NullValueIntentPutExtra <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Replace the value argument in an Intent.putExtra(key, value) call with new Parcelable[0]  
**Code Example:**  
Before:  
```
1 invoke-virtual {v1, v2, v3}, Landroid/content/Intent;
    ->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;
```
After:
```
1 new-array v9, v9, [Landroid/os/Parcelable;
2 invoke-virtual {v1, v2, v9}, Landroid/content/Intent;
    ->putExtra(Ljava/lang/String;[Landroid/os/Parcelable;)
    Landroid/content/Intent;
```
___

> ___
### WrongMainActivity <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-info" style="float: right;">Text</span>
___
**Description:**  
Randomly replace the main activity definition with a different activity  
**Code Example:**  
Before:  
```xml
1 <activity android:name=".Mileage" 
            android:theme="@android:style/Theme.NoTitleBar">
2  <intent-filter>
3     <action android:name="android.intent.action.MAIN" />
4     <category android:name="android.intent.category.LAUNCHER" />
5   </intent-filter>
6 </activity>
7 ...
8 <activity android:name=".AboutActivity" 
            android:label="@string/title_about" />
```
After:
```xml
1 <activity android:name=".AboutActivity" 
            android:theme="@android:style/Theme.NoTitleBar">
2   <intent-filter>
3     <action android:name="android.intent.action.MAIN" />
4     <category android:name="android.intent.category.LAUNCHER" />
5   </intent-filter>
6 </activity>
7 ...
8 <activity android:name=".Mileage" 
            android:label="@string/title_about" />
```
___

## Android Programming

> ___
### MissingPermissionManifest <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-info" style="float: right;">Text</span>
___
**Description:**  
Selects and remove an &lt;uses-permission .../&gt; entry in the Manifest file  
**Code Example:**  
Before:  
```xml
1 <uses-permission 
    android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
After:
```
1 
```
___

> ___
### NullGPSLocation <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Inject a Null GPS location in the location services  
**Code Example:**  
Before:  
```
1 .local v0, "location":Landroid/location/Location;
```
After:
```
1 const/4 v0, 0x0               
2 .local v0, "location":Landroid/location/Location;
```
___

> ___
### SDKVersion <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-info" style="float: right;">Text</span>
___
**Description:**  
Randomly mutate the integer values in the SdkVersion-related attributes  
**Code Example:**  
Before:  
```xml
1 <uses-sdk android:minSdkVersion="4" 
            android:targetSdkVersion="10"/>
```
After:
```xml
1 <uses-sdk android:minSdkVersion="7" 
            android:targetSdkVersion="10"/>
```
___

> ___
### WrongStringResource <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-info" style="float: right;">Text</span>
___
**Description:**  
Select a &lt;string .../&gt; entry in /res/values/strings.xml file and mutate the string value  
**Code Example:**  
Before:  
```xml
1 < string name="update_finished_importing">
    Import finished!
  </ string>
```
After:
```xml
1 < string name="update_finished_importing">
    ecab6839856b426fbdae3e6e8c46c38c
  </ string>
```
___

## Connectivity

> ___
### BluetoothAdapterAlwaysEnabled <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Replace a BluethootAdapter.isEnabled() call with "true"  
**Code Example:**  
Before:  
```
1 invoke-virtual/range {v19 .. v19}, 
    Landroid/bluetooth/BluetoothAdapter;->isEnabled()Z
2 move-result v3
3 if-eqz v3, :cond_13
```
After:
```
1 
```
___

> ___
### NullBluetoothAdapter <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Replace a BluetoothAdapter instance with null  
**Code Example:**  
Before:  
```
1 invoke-static {}, Landroid/bluetooth/BluetoothAdapter;
    ->getDefaultAdapter()Landroid/bluetooth/BluetoothAdapter;
2 move-result-object v10
```
After:
```
1 invoke-static {}, Landroid/bluetooth/BluetoothAdapter;
    ->getDefaultAdapter()Landroid/bluetooth/BluetoothAdapter;
2 const/16 v10, 0x0
```
___

## Data

> ___
### InvalidURI <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
If URI are used internally, randomly mutate the URIs  
**Code Example:**  
Before:  
```
1 invoke-direct {v3, v0}, 
    Ljava/net/URI;-><init>(Ljava/lang/String;)V
```
After:
```
1 const-string v2, "7e2cd5897951414db37193f8018a753c"
2 invoke-direct {v3, v2}, Ljava/net/URI;
    -><init>(Ljava/lang/String;)V
```
___

## Database

> ___
### ClosingNullCursor <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Assign a cursor to null before it is closed  
**Code Example:**  
Before:  
```
1 invoke-interface {v3}, 
    Landroid/database/Cursor;->close()V
```
After:
```
1 const/4 v3, 0x0
2 invoke-interface {v3}, 
    Landroid/database/Cursor;->close()V
```
___

> ___
### InvalidIndexQueryParameter <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Changes the query parameter array for an empty array  
**Code Example:**  
Before:  
```
1 invoke-virtual {v5, v7, v4}, 
    Landroid/database/sqlite/SQLiteDatabase;->
    rawQuery(Ljava/lang/String;[Ljava/lang/String;)
    Landroid/database/Cursor;
```
After:
```
1 const/4 v1, 0x0
2 new-array v4, v1, [Ljava/lang/String;
3 invoke-virtual {v5, v7, v4}, 
    Landroid/database/sqlite/SQLiteDatabase;->
    rawQuery(Ljava/lang/String;[Ljava/lang/String;)
    Landroid/database/Cursor;
```
___

> ___
### InvalidSQLQuery <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Randomly mutate a SQL query  
**Code Example:**  
Before:  
```
1 invoke-virtual {v5, v7, v4}, 
    Landroid/database/sqlite/SQLiteDatabase;->
    rawQuery(Ljava/lang/String;[Ljava/lang/String;)
    Landroid/database/Cursor;
```
After:
```
1 const-string v7, "9e220601a5ff474caad7d5495b14af75"
2 invoke-virtual {v5, v7, v4}, 
    Landroid/database/sqlite/SQLiteDatabase;->
    rawQuery(Ljava/lang/String;[Ljava/lang/String;)
    Landroid/database/Cursor;
```
___

## General Programming

> ___
### InvalidDate <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Set a random Date to a date object  
**Code Example:**  
Before:  
```
1 invoke-direct {v6, v1, v2}, 
    Ljava/util/Date;-><init>(J)V
```
After:
```
1 const-wide v1, 0xeb34dd0L
2 invoke-direct {v6, v1, v2}, 
    Ljava/util/Date;-><init>(J)
```
___

> ___
### NullMethodCallArgument <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Set null to a method call argument  
**Code Example:**  
Before:  
```
1 invoke-direct {v0, v1, v5}, 
    Lcom/fsck/k9/Account$FolderMode;
    -><init>(Ljava/lang/String;I)V
```
After:
```
1 const/4 v1, 0x0
2 invoke-direct {v0, v1, v5}, 
    Lcom/fsck/k9/Account$FolderMode;
    -><init>(Ljava/lang/String;I)V
```
___

## GUI

> ___
### FindViewByIDReturnsNull <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Assign a variable (returned by Activity.findViewById) to null  
**Code Example:**  
Before:  
```
1 invoke-virtual {p0, p1}, 
    Lio/github/hidroh/materialistic/AboutActivity;
    ->findViewById(I)Landroid/view/View;
2 move-result-object v0
3 check-cast v0, Landroid/widget/TextView;
```
After:
```
1 const/4 v1, 0x0
```
___

> ___
### InvalidColor <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-info" style="float: right;">Text</span>
___
**Description:**  
Randomly change colors in layout files  
**Code Example:**  
Before:  
```xml
1 <item name="red" type="color">#FFFF4444</item>
```
After:
```xml
1 <item name="red" type="color">#FF669900</item>
```
___

> ___
### InvalidIDFindView <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Replace the id argument in an Activity.findViewById call  
**Code Example:**  
Before:  
```
1 const v5, 0x7f0d007d
2 invoke-virtual {v4, v5}, 
    Landroid/view/View;
    ->findViewById(I)Landroid/view/View;
```
After:
```
1 const/16 v5, 0x99abad00
2 invoke-virtual {v4, v5}, 
    Landroid/view/View;->
    findViewById(I)Landroid/view/View;
```
___

> ___
### InvalidViewFocus <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Randomly focus a GUI component  
**Code Example:**  
Before:  
```
1 invoke-virtual {p0, v1}, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->findViewById(I)Landroid/view/View;
2 move-result-object v1
3 check-cast v1, Landroid/widget/Button;
4 iput-object v1, p0, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->mSelection:Landroid/widget/Button;
```
After:
```
1 invoke-virtual {p0, v1}, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->findViewById(I)Landroid/view/View;
2 move-result-object v1
3 check-cast v1, Landroid/widget/Button;
4 iput-object v1, p0, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->mSelection:Landroid/widget/Button;
5 iget-object v1, p0, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->mSelection:Landroid/widget/Button;
6 invoke-virtual {v1}, 
    Landroid/widget/Button;->requestFocus()Z
```
___

> ___
### ViewComponentNotVisible <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Set visible attribute (from a View) to false  
**Code Example:**  
Before:  
```
1 invoke-virtual {p0, v1}, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->findViewById(I)Landroid/view/View;
2 move-result-object v1
3 check-cast v1, Landroid/widget/ImageButton;
4 iput-object v1, p0, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->mDeleteButton:Landroid/widget/ImageButton;
5 .line 58
6 iget-object v1, p0, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->mDeleteButton:Landroid/widget/ImageButton;
```
After:
```
1 invoke-virtual {p0, v1}, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->findViewById(I)Landroid/view/View;
2 move-result-object v1
3 check-cast v1, Landroid/widget/ImageButton;
4 iput-object v1, p0, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->mDeleteButton:Landroid/widget/ImageButton;
5 .line 58
6 iget-object v1, p0, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->mDeleteButton:Landroid/widget/ImageButton;
7 const/4 v9, 0x4
8 invoke-virtual {v1, v9}, 
    Lcom/fsck/k9/view/ClientCertificateSpinner;
    ->setVisibility(I)V
```
___

## I/O

> ___
### InvalidFilePath <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Randomly mutate paths to files  
**Code Example:**  
Before:  
```
1 invoke-direct {v0, v1}, 
    Ljava/io/File;-><init>(Ljava/lang/String;)V
```
After:
```
1 const-string v9, "e03044246cea40e195e920cd2eb9840f"
2 invoke-direct {v0, v9}, 
    Ljava/io/File;-><init>(Ljava/lang/String;)V
```
___

> ___
### NullInputStream <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Assign an input stream to null before it is closed  
**Code Example:**  
Before:  
```
1 invoke-virtual {v0}, Ljava/io/InputStream;->close()V
```
After:
```
1 const/4 v0, 0x0
2 invoke-virtual {v0}, Ljava/io/InputStream;->close()V
```
___

> ___
### NullOutputStream <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Assign an output stream to null before it is closed  
**Code Example:**  
Before:  
```
1 invoke-virtual {v0}, Ljava/io/OutputStream;->close()V
```
After:
```
1 const/4 v0, 0x0
2 invoke-virtual {v0}, Ljava/io/OutputStream;->close()V
```
___

## Non-Functional Requirements

> ___
### LengthyBackEndService <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Inject large delay right-after a call to a back-end service  
**Code Example:**  
Before:  
```
1 .local v14, "response":Lorg/apache/http/HttpResponse;
```
After:
```
1 .local v14, "response":Lorg/apache/http/HttpResponse;
2 invoke-direct/range {p0 .. p0}, 
    Lcom/fsck/k9/mail/store/webdav/WebDavFolder;->delay()V
3 ...
4 .method private delay()V
5    .locals 2
6    .line 332
7    const-wide/16 v0, 0x2710
8    :try_start_0
9    invoke-static {v0, v1}, Ljava/lang/Thread;->sleep(J)V
10   :try_end_0
11   .catch Ljava/lang/InterruptedException; 
       {:try_start_0 .. :try_end_0} :catch_0
12   .line 335
13   goto :goto_0
14   .line 333
15   :catch_0
16   move-exception v0
17   .line 334
18   .local v0, "e":Ljava/lang/InterruptedException;
19   invoke-virtual {v0}, 
       Ljava/lang/InterruptedException;->printStackTrace()V
20   .line 336
21   .end local v0    # "e":Ljava/lang/InterruptedException;
22   :goto_0
23   return-void
24 .end method
```
___

> ___
### LengthyGUICreation <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Insert a long delay (\ie Thread.sleep(..)) in the creation GUI thread  
**Code Example:**  
Before:  
```
1 .method public onCreate(Landroid/os/Bundle;)V
2 .locals 1
3 .param p1, "savedInstanceState"    # Landroid/os/Bundle;
4 .line 107
5 invoke-super {p0, p1}, Lcom/fsck/k9/activity/K9Activity;
    ->onCreate(Landroid/os/Bundle;)V
```
After:
```
1 .method public onCreate(Landroid/os/Bundle;)V
2 .locals 1
3 .param p1, "savedInstanceState"    # Landroid/os/Bundle;
4 .line 107
5 invoke-super {p0, p1}, Lcom/fsck/k9/activity/K9Activity;
    ->onCreate(Landroid/os/Bundle;)V
6 ...
7 .method private delay()V
8    .locals 2
9    .line 332
10   const-wide/16 v0, 0x2710
11   :try_start_0
12   invoke-static {v0, v1}, Ljava/lang/Thread;->sleep(J)V
13   :try_end_0
14   .catch Ljava/lang/InterruptedException; 
       {:try_start_0 .. :try_end_0} :catch_0
15   .line 335
16   goto :goto_0
17   .line 333
18   :catch_0
19   move-exception v0
20   .line 334
21   .local v0, "e":Ljava/lang/InterruptedException;
22   invoke-virtual {v0}, 
       Ljava/lang/InterruptedException;->printStackTrace()V
22   .line 336
23   .end local v0    # "e":Ljava/lang/InterruptedException;
24   :goto_0
25   return-void
26 .end method
```
___

> ___
### LengthyGUIListener <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Insert a long delay (i.e., Thread.sleep()) in a GUI Listener  
**Code Example:**  
Before:  
```
1 .method public onCreate(Landroid/os/Bundle;)V
2 .locals 1
3 .param p1, "v"    # Landroid/view/View;
```
After:
```
1 .method public onCreate(Landroid/os/Bundle;)V
2 .locals 1
3 .param p1, "v"    # Landroid/view/View;
4 ...
5 .method private delay()V
6    .locals 2
7    .line 332
8    const-wide/16 v0, 0x2710
9    :try_start_0
10   invoke-static {v0, v1}, Ljava/lang/Thread;->sleep(J)V
11   :try_end_0
12   .catch Ljava/lang/InterruptedException; 
       {:try_start_0 .. :try_end_0} :catch_0
13   .line 335
14   goto :goto_0
15   .line 333
16   :catch_0
17   move-exception v0
18   .line 334
19   .local v0, "e":Ljava/lang/InterruptedException;
20   invoke-virtual {v0}, 
       Ljava/lang/InterruptedException;->printStackTrace()V
21   .line 336
22   .end local v0    # "e":Ljava/lang/InterruptedException;
23   :goto_0
24   return-void
25 .end method
```
___

> ___
### LongConnectionTimeout <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Increase the time-out of connection to backend services  
**Code Example:**  
Before:  
```
1 invoke-static {v3, v4}, 
    Lorg/apache/http/params/HttpConnectionParams;
    ->setConnectionTimeout(Lorg/apache/http/params/HttpParams;I)V
```
After:
```
1 const v4, 0x186a0
2 invoke-static {v3, v4}, 
    Lorg/apache/http/params/HttpConnectionParams;
    ->setConnectionTimeout(Lorg/apache/http/params/HttpParams;I)V
```
___

> ___
### OOMLargeImage <span class="badge badge-pill badge-secondary" style="float: right;">MDroid+</span><span class="badge badge-pill badge-success" style="float: right;">AST</span>
___
**Description:**  
Increase the size of bitmaps by explicitly setting large dimensions  
**Code Example:**  
Before:  
```
1 invoke-static {v8, v5, v6, v7}, 
    Landroid/graphics/Bitmap;
    ->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)
    Landroid/graphics/Bitmap;
```
After:
```
1 const v6, 0x186a0
2 invoke-static {v8, v6, v6, v7}, 
    Landroid/graphics/Bitmap;
    ->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)
    Landroid/graphics/Bitmap;
```
___  
    
---
Hosted on GitHub Pages - Theme by [orderedlist](https://github.com/orderedlist)
