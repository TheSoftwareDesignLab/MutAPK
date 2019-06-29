# MutAPK
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
