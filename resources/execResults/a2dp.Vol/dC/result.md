
## Parameters provided via config file:

Name			| Value
------------------------|---------
apkPath 		| ./sAPKs/a2dp.Vol.apk
appName 		| a2dp.Vol
mutantsFolder 		| ./mutants/a2dp.Vol/dC/
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
64		| INVALID_ID_FINDVIEW
0		| INVALID_COLOR
2388		| NULL_METHOD_CALL_ARGUMENT
37		| VIEW_COMPONENT_NOT_VISIBLE
27		| INVALID_KEY_INTENT_PUT_EXTRA
28		| NULL_INTENT
64		| FINDVIEWBYID_RETURNS_NULL
3		| INVALID_SQL_QUERY
8		| LENGTHY_GUI_CREATION
37		| INVALID_VIEW_FOCUS
16		| LENGTHY_GUI_LISTENER
0		| SDK_VERSION
1		| WRONG_MAIN_ACTIVITY
8		| CLOSING_NULL_CURSOR
14		| NULL_INPUT_STREAM
28		| DIFFERENT_ACTIVITY_INTENT_DEFINITION
1		| BLUETOOTH_ADAPTER_ALWAYS_AVAILABLE
11		| INVALID_FILE_PATH
9		| INVALID_ACTIVITY_PATH
4		| INVALID_LABEL
1		| NULL_GPS_LOCATION
20		| MISSING_PERMISSION_MANIFEST
27		| NULL_VALUE_INTENT_PUT_EXTRA
3		| INVALID_INDEX_QUERY_PARAMETER
4		| NULL_OUTPUT_STREAM
9		| ACTIVITY_NOT_DEFINED
118		| WRONG_STRING_RESOURCE
7		| NULL_BLUETOOTH_ADAPTER

Total Locations: 2937

--------------------------------------
```
