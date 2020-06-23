
## Parameters provided via config file:

Name			| Value
------------------------|---------
apkPath 		| ./sAPKs/com.nloko.android.syncmypix.apk
appName 		| com.nloko.android.syncmypix
mutantsFolder 		| ./mutants/com.nloko.android.syncmypix/dC/
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
22		| DIFFERENT_ACTIVITY_INTENT_DEFINITION
8		| LENGTHY_GUI_CREATION
3		| NULL_INPUT_STREAM
0		| INVALID_COLOR
38		| INVALID_ID_FINDVIEW
15		| LENGTHY_GUI_LISTENER
9		| NULL_VALUE_INTENT_PUT_EXTRA
9		| INVALID_ACTIVITY_PATH
6		| INVALID_LABEL
1		| INVALID_FILE_PATH
9		| INVALID_KEY_INTENT_PUT_EXTRA
8		| MISSING_PERMISSION_MANIFEST
3		| NULL_OUTPUT_STREAM
11		| VIEW_COMPONENT_NOT_VISIBLE
2630		| NULL_METHOD_CALL_ARGUMENT
11		| INVALID_VIEW_FOCUS
0		| SDK_VERSION
22		| NULL_INTENT
9		| ACTIVITY_NOT_DEFINED
2		| WRONG_MAIN_ACTIVITY
24		| CLOSING_NULL_CURSOR
90		| WRONG_STRING_RESOURCE
38		| FINDVIEWBYID_RETURNS_NULL

Total Locations: 2968

--------------------------------------
```
