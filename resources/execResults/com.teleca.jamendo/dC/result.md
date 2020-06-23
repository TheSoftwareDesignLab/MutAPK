
## Parameters provided via config file:

Name			| Value
------------------------|---------
apkPath 		| ./sAPKs/com.teleca.jamendo.apk
appName 		| com.teleca.jamendo
mutantsFolder 		| ./mutants/com.teleca.jamendo/dC/
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
1		| INVALID_URI
26		| DIFFERENT_ACTIVITY_INTENT_DEFINITION
16		| INVALID_FILE_PATH
2		| NULL_BACKEND_SERVICE_RETURN
68		| INVALID_VIEW_FOCUS
114		| FINDVIEWBYID_RETURNS_NULL
0		| SDK_VERSION
1		| WRONG_MAIN_ACTIVITY
16		| LENGTHY_GUI_CREATION
32		| INVALID_KEY_INTENT_PUT_EXTRA
21		| LENGTHY_GUI_LISTENER
7		| NULL_INPUT_STREAM
32		| NULL_VALUE_INTENT_PUT_EXTRA
15		| INVALID_ACTIVITY_PATH
13		| INVALID_LABEL
114		| INVALID_ID_FINDVIEW
26		| NULL_INTENT
7		| MISSING_PERMISSION_MANIFEST
5		| CLOSING_NULL_CURSOR
15		| ACTIVITY_NOT_DEFINED
2992		| NULL_METHOD_CALL_ARGUMENT
1		| LENGTHY_BACKEND_SERVICE
68		| VIEW_COMPONENT_NOT_VISIBLE
5		| NULL_OUTPUT_STREAM
4		| WRONG_STRING_RESOURCE

Total Locations: 3601

--------------------------------------
```
