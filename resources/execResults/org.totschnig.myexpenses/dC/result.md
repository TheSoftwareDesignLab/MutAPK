
## Parameters provided via config file:

Name			| Value
------------------------|---------
apkPath 		| ./sAPKs/org.totschnig.myexpenses.apk
appName 		| org.totschnig.myexpenses
mutantsFolder 		| ./mutants/org.totschnig.myexpenses/dC/
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
16		| DIFFERENT_ACTIVITY_INTENT_DEFINITION
2648		| NULL_METHOD_CALL_ARGUMENT
13		| LENGTHY_GUI_CREATION
16		| INVALID_VIEW_FOCUS
10		| INVALID_INDEX_QUERY_PARAMETER
10		| INVALID_SQL_QUERY
0		| SDK_VERSION
6		| INVALID_FILE_PATH
1		| WRONG_MAIN_ACTIVITY
1		| INVALID_URI
23		| INVALID_KEY_INTENT_PUT_EXTRA
53		| FINDVIEWBYID_RETURNS_NULL
23		| NULL_VALUE_INTENT_PUT_EXTRA
53		| INVALID_ID_FINDVIEW
16		| NULL_INTENT
23		| CLOSING_NULL_CURSOR
16		| VIEW_COMPONENT_NOT_VISIBLE
2		| NULL_INPUT_STREAM
13		| INVALID_ACTIVITY_PATH
2		| INVALID_LABEL
2		| MISSING_PERMISSION_MANIFEST
13		| ACTIVITY_NOT_DEFINED
20		| LENGTHY_GUI_LISTENER
207		| WRONG_STRING_RESOURCE

Total Locations: 3187

--------------------------------------
```
