
## Parameters provided via config file:

Name			| Value
------------------------|---------
apkPath 		| ./sAPKs/net.jaqpot.netcounter.apk
appName 		| net.jaqpot.netcounter
mutantsFolder 		| ./mutants/net.jaqpot.netcounter/dC/
extraPath 		| ./extra
operatorsDir 		| ./
multithread 		| false
ignoreDeadCode 		| false
selectionStrategy 	| all
----------------------------------

## Selected Mutation Operators:

Id 		| MutOperatorName
----------------|--------------
22 		| NullMethodCallArgument
23 		| ClosingNullCursor
24 		| InvalidIndexQueryParameter
25 		| InvalidSQLQuery
26 		| ViewComponentNotVisible
27 		| FindViewByIdReturnsNull
28 		| InvalidColor
29 		| InvalidViewFocus
31 		| InvalidIDFindView
32 		| InvalidFilePath
10 		| WrongStringResource
33 		| NullInputStream
12 		| SDKVersion
35 		| OOMLargeImage
13 		| LengthyBackEndService
36 		| LengthyGUIListener
14 		| LongConnectionTimeOut
37 		| NullOutputStream
15 		| BluetoothAdapterAlwaysEnabled
38 		| LengthyGUICreation
16 		| NullBluetoothAdapter
17 		| InvalidURI
18 		| NullGPSLocation
19 		| InvalidDate
1 		| ActivityNotDefined
2 		| DifferentActivityIntentDefinition
3 		| InvalidActivityPATH
4 		| InvalidKeyIntentPutExtra
5 		| InvalidLabel
6 		| NullIntent
7 		| NullValueIntentPutExtra
8 		| WrongMainActivity
9 		| MissingPermissionManifest
20 		| NullBackEndServiceReturn

Amount Selected Operators: 	34

-------------------------------------------

/home/estudiante/test
> Processing your APK...  
> Wow... that was an amazing APK to proccess!!! :D

--------------------------------------
## Amount of Potential Fault Locations per Mutation Operator

Amount Mutants	| Mutation Operator
----------------|---------------------
0		| INVALID_COLOR
6		| INVALID_VIEW_FOCUS
11		| DIFFERENT_ACTIVITY_INTENT_DEFINITION
17		| INVALID_ID_FINDVIEW
4		| LENGTHY_GUI_CREATION
1		| INVALID_KEY_INTENT_PUT_EXTRA
4		| INVALID_ACTIVITY_PATH
3		| INVALID_LABEL
11		| NULL_INTENT
5		| MISSING_PERMISSION_MANIFEST
6		| VIEW_COMPONENT_NOT_VISIBLE
17		| FINDVIEWBYID_RETURNS_NULL
1		| NULL_VALUE_INTENT_PUT_EXTRA
0		| SDK_VERSION
4		| ACTIVITY_NOT_DEFINED
1		| WRONG_MAIN_ACTIVITY
3		| INVALID_FILE_PATH
1375		| NULL_METHOD_CALL_ARGUMENT
86		| WRONG_STRING_RESOURCE
12		| CLOSING_NULL_CURSOR
3		| NULL_OUTPUT_STREAM

Total Locations: 1570

--------------------------------------
```
