
## Parameters provided via config file:

Name			| Value
------------------------|---------
apkPath 		| ./sAPKs/com.evancharlton.mileage.apk
appName 		| com.evancharlton.mileage
mutantsFolder 		| ./mutants/com.evancharlton.mileage/dC/
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
12		| INVALID_DATE
25		| VIEW_COMPONENT_NOT_VISIBLE
0		| INVALID_COLOR
14		| NULL_INPUT_STREAM
14		| LENGTHY_GUI_LISTENER
30		| NULL_INTENT
51		| INVALID_ACTIVITY_PATH
50		| INVALID_LABEL
1		| NULL_GPS_LOCATION
104		| FINDVIEWBYID_RETURNS_NULL
26		| LENGTHY_GUI_CREATION
4		| MISSING_PERMISSION_MANIFEST
19		| INVALID_KEY_INTENT_PUT_EXTRA
0		| SDK_VERSION
17		| CLOSING_NULL_CURSOR
51		| ACTIVITY_NOT_DEFINED
1		| WRONG_MAIN_ACTIVITY
3690		| NULL_METHOD_CALL_ARGUMENT
19		| NULL_VALUE_INTENT_PUT_EXTRA
6		| INVALID_FILE_PATH
318		| WRONG_STRING_RESOURCE
104		| INVALID_ID_FINDVIEW
25		| INVALID_VIEW_FOCUS
30		| DIFFERENT_ACTIVITY_INTENT_DEFINITION

Total Locations: 4611

--------------------------------------
```
