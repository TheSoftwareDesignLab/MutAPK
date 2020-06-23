
## Parameters provided via config file:

Name			| Value
------------------------|---------
apkPath 		| ./sAPKs/com.eleybourn.bookcatalogue.apk
appName 		| com.eleybourn.bookcatalogue
mutantsFolder 		| ./mutants/com.eleybourn.bookcatalogue/dC/
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
63		| CLOSING_NULL_CURSOR
0		| INVALID_COLOR
24		| LENGTHY_GUI_CREATION
31		| DIFFERENT_ACTIVITY_INTENT_DEFINITION
55		| INVALID_INDEX_QUERY_PARAMETER
139		| INVALID_ID_FINDVIEW
55		| INVALID_SQL_QUERY
8		| VIEW_COMPONENT_NOT_VISIBLE
0		| SDK_VERSION
88		| LENGTHY_GUI_LISTENER
1		| WRONG_MAIN_ACTIVITY
139		| FINDVIEWBYID_RETURNS_NULL
35		| INVALID_KEY_INTENT_PUT_EXTRA
31		| NULL_INTENT
38		| INVALID_FILE_PATH
22		| INVALID_ACTIVITY_PATH
6		| INVALID_LABEL
7599		| NULL_METHOD_CALL_ARGUMENT
4		| MISSING_PERMISSION_MANIFEST
18		| NULL_OUTPUT_STREAM
22		| ACTIVITY_NOT_DEFINED
12		| NULL_INPUT_STREAM
35		| NULL_VALUE_INTENT_PUT_EXTRA
268		| WRONG_STRING_RESOURCE
8		| INVALID_VIEW_FOCUS

Total Locations: 8701

--------------------------------------
```
