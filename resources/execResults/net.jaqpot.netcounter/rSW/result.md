
## Parameters provided via config file:

Name			| Value
------------------------|---------
apkPath 		| ./sAPKs/net.jaqpot.netcounter.apk
appName 		| net.jaqpot.netcounter
mutantsFolder 		| ./mutants/net.jaqpot.netcounter/rSW/
extraPath 		| ./extra
operatorsDir 		| ./
multithread 		| false
ignoreDeadCode 		| true
selectionStrategy 	| representativeSubset
isRSPerOPerator 	| false
confidenceLevel 	| 95
marginError 		| 5
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
## Call Graph Results

Method Type			| Amount
----------------|---------
NotDefinedButCalled			| 186
CalledAndDefined			| 330
DeadCode			| 95

----------------------------------

> It took 36 miliseconds to remove dead code from APK analysis.
## Amount of Potential Fault Locations per Mutation Operator

Amount Mutants	| Mutation Operator
----------------|---------------------
4		| ACTIVITY_NOT_DEFINED
3		| INVALID_LABEL
11		| DIFFERENT_ACTIVITY_INTENT_DEFINITION
11		| INVALID_ID_FINDVIEW
3		| NULL_OUTPUT_STREAM
4		| LENGTHY_GUI_CREATION
6		| VIEW_COMPONENT_NOT_VISIBLE
3		| INVALID_FILE_PATH
86		| WRONG_STRING_RESOURCE
4		| INVALID_ACTIVITY_PATH
1179		| NULL_METHOD_CALL_ARGUMENT
8		| CLOSING_NULL_CURSOR
5		| MISSING_PERMISSION_MANIFEST
11		| NULL_INTENT
0		| SDK_VERSION
1		| WRONG_MAIN_ACTIVITY
1		| NULL_VALUE_INTENT_PUT_EXTRA
6		| INVALID_VIEW_FOCUS
1		| INVALID_KEY_INTENT_PUT_EXTRA
0		| INVALID_COLOR
11		| FINDVIEWBYID_RETURNS_NULL

Total Locations: 1358

--------------------------------------
Individual Sample Size		Mutation Operator 1
1				ACTIVITY_NOT_DEFINED
1				INVALID_LABEL
3				DIFFERENT_ACTIVITY_INTENT_DEFINITION
3				INVALID_ID_FINDVIEW
1				NULL_OUTPUT_STREAM
1				LENGTHY_GUI_CREATION
2				VIEW_COMPONENT_NOT_VISIBLE
1				INVALID_FILE_PATH
19				WRONG_STRING_RESOURCE
1				INVALID_ACTIVITY_PATH
261				NULL_METHOD_CALL_ARGUMENT
2				CLOSING_NULL_CURSOR
2				MISSING_PERMISSION_MANIFEST
3				NULL_INTENT
0				SDK_VERSION
1				WRONG_MAIN_ACTIVITY
1				NULL_VALUE_INTENT_PUT_EXTRA
2				INVALID_VIEW_FOCUS
1				INVALID_KEY_INTENT_PUT_EXTRA
0				INVALID_COLOR
3				FINDVIEWBYID_RETURNS_NULL
Total Sample size  309

## Mutation Process Log

```sh
0 HashCode: 1342523783
Creating folder for mutant 1
Copying app information into mutant 1 folder
Mutant: 1 - Type: ACTIVITY_NOT_DEFINED
Mutant 1 has survived the mutation process. Now its source code has been modified.
1 HashCode: 1758709509
Creating folder for mutant 2
Copying app information into mutant 2 folder
Mutant: 2 - Type: INVALID_LABEL
Mutant 2 has survived the mutation process. Now its source code has been modified.
2 HashCode: -1581627650
Creating folder for mutant 3
Copying app information into mutant 3 folder
Mutant: 3 - Type: DIFFERENT_ACTIVITY_INTENT_DEFINITION
Mutant 3 has survived the mutation process. Now its source code has been modified.
3 HashCode: 307615237
Creating folder for mutant 4
Copying app information into mutant 4 folder
Mutant: 4 - Type: DIFFERENT_ACTIVITY_INTENT_DEFINITION
Mutant 4 has survived the mutation process. Now its source code has been modified.
4 HashCode: 1397511915
Creating folder for mutant 5
Copying app information into mutant 5 folder
Mutant: 5 - Type: DIFFERENT_ACTIVITY_INTENT_DEFINITION
Mutant 5 has survived the mutation process. Now its source code has been modified.
5 HashCode: 428000180
Creating folder for mutant 6
Copying app information into mutant 6 folder
Mutant: 6 - Type: INVALID_ID_FINDVIEW
Mutant 6 has survived the mutation process. Now its source code has been modified.
6 HashCode: 279884349
Creating folder for mutant 7
Copying app information into mutant 7 folder
Mutant: 7 - Type: INVALID_ID_FINDVIEW
Mutant 7 has survived the mutation process. Now its source code has been modified.
7 HashCode: 1061829531
Creating folder for mutant 8
Copying app information into mutant 8 folder
Mutant: 8 - Type: INVALID_ID_FINDVIEW
Mutant 8 has survived the mutation process. Now its source code has been modified.
8 HashCode: -529244168
Creating folder for mutant 9
Copying app information into mutant 9 folder
Mutant: 9 - Type: NULL_OUTPUT_STREAM
Mutant 9 has survived the mutation process. Now its source code has been modified.
9 HashCode: -987199508
Creating folder for mutant 10
Copying app information into mutant 10 folder
Mutant: 10 - Type: LENGTHY_GUI_CREATION
Mutant 10 has survived the mutation process. Now its source code has been modified.
10 HashCode: -161115338
Creating folder for mutant 11
Copying app information into mutant 11 folder
Mutant: 11 - Type: VIEW_COMPONENT_NOT_VISIBLE
Mutant 11 has survived the mutation process. Now its source code has been modified.
11 HashCode: 1400883923
Creating folder for mutant 12
Copying app information into mutant 12 folder
Mutant: 12 - Type: VIEW_COMPONENT_NOT_VISIBLE
Mutant 12 has survived the mutation process. Now its source code has been modified.
12 HashCode: -1675247677
Creating folder for mutant 13
Copying app information into mutant 13 folder
Mutant: 13 - Type: INVALID_FILE_PATH
Mutant 13 has survived the mutation process. Now its source code has been modified.
13 HashCode: -1933659962
Creating folder for mutant 14
Copying app information into mutant 14 folder
Mutant: 14 - Type: WRONG_STRING_RESOURCE
Mutant 14 has survived the mutation process. Now its source code has been modified.
14 HashCode: 337961782
Creating folder for mutant 15
Copying app information into mutant 15 folder
Mutant: 15 - Type: WRONG_STRING_RESOURCE
Mutant 15 has survived the mutation process. Now its source code has been modified.
15 HashCode: 486953985
Creating folder for mutant 16
Copying app information into mutant 16 folder
Mutant: 16 - Type: WRONG_STRING_RESOURCE
Mutant 16 has survived the mutation process. Now its source code has been modified.
16 HashCode: -374461268
Creating folder for mutant 17
Copying app information into mutant 17 folder
Mutant: 17 - Type: WRONG_STRING_RESOURCE
Mutant 17 has survived the mutation process. Now its source code has been modified.
17 HashCode: 1715389135
Creating folder for mutant 18
Copying app information into mutant 18 folder
Mutant: 18 - Type: WRONG_STRING_RESOURCE
Mutant 18 has survived the mutation process. Now its source code has been modified.
18 HashCode: -1146967433
Creating folder for mutant 19
Copying app information into mutant 19 folder
Mutant: 19 - Type: WRONG_STRING_RESOURCE
Mutant 19 has survived the mutation process. Now its source code has been modified.
19 HashCode: -1173796214
Creating folder for mutant 20
Copying app information into mutant 20 folder
Mutant: 20 - Type: WRONG_STRING_RESOURCE
Mutant 20 has survived the mutation process. Now its source code has been modified.
20 HashCode: -1792000856
Creating folder for mutant 21
Copying app information into mutant 21 folder
Mutant: 21 - Type: WRONG_STRING_RESOURCE
Mutant 21 has survived the mutation process. Now its source code has been modified.
21 HashCode: 476264079
Creating folder for mutant 22
Copying app information into mutant 22 folder
Mutant: 22 - Type: WRONG_STRING_RESOURCE
Mutant 22 has survived the mutation process. Now its source code has been modified.
22 HashCode: -1190766246
Creating folder for mutant 23
Copying app information into mutant 23 folder
Mutant: 23 - Type: WRONG_STRING_RESOURCE
Mutant 23 has survived the mutation process. Now its source code has been modified.
23 HashCode: -1193667223
Creating folder for mutant 24
Copying app information into mutant 24 folder
Mutant: 24 - Type: WRONG_STRING_RESOURCE
Mutant 24 has survived the mutation process. Now its source code has been modified.
24 HashCode: -398272665
Creating folder for mutant 25
Copying app information into mutant 25 folder
Mutant: 25 - Type: WRONG_STRING_RESOURCE
Mutant 25 has survived the mutation process. Now its source code has been modified.
25 HashCode: 1111132690
Creating folder for mutant 26
Copying app information into mutant 26 folder
Mutant: 26 - Type: WRONG_STRING_RESOURCE
Mutant 26 has survived the mutation process. Now its source code has been modified.
26 HashCode: -637942478
Creating folder for mutant 27
Copying app information into mutant 27 folder
Mutant: 27 - Type: WRONG_STRING_RESOURCE
Mutant 27 has survived the mutation process. Now its source code has been modified.
27 HashCode: 1259260218
Creating folder for mutant 28
Copying app information into mutant 28 folder
Mutant: 28 - Type: WRONG_STRING_RESOURCE
Mutant 28 has survived the mutation process. Now its source code has been modified.
28 HashCode: 1967812162
Creating folder for mutant 29
Copying app information into mutant 29 folder
Mutant: 29 - Type: WRONG_STRING_RESOURCE
Mutant 29 has survived the mutation process. Now its source code has been modified.
29 HashCode: 1413098157
Creating folder for mutant 30
Copying app information into mutant 30 folder
Mutant: 30 - Type: WRONG_STRING_RESOURCE
Mutant 30 has survived the mutation process. Now its source code has been modified.
30 HashCode: -11029401
Creating folder for mutant 31
Copying app information into mutant 31 folder
Mutant: 31 - Type: WRONG_STRING_RESOURCE
Mutant 31 has survived the mutation process. Now its source code has been modified.
31 HashCode: 1764571777
Creating folder for mutant 32
Copying app information into mutant 32 folder
Mutant: 32 - Type: WRONG_STRING_RESOURCE
Mutant 32 has survived the mutation process. Now its source code has been modified.
32 HashCode: -1657172245
Creating folder for mutant 33
Copying app information into mutant 33 folder
Mutant: 33 - Type: INVALID_ACTIVITY_PATH
Mutant 33 has survived the mutation process. Now its source code has been modified.
33 HashCode: -1047327962
Creating folder for mutant 34
Copying app information into mutant 34 folder
Mutant: 34 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 34 has survived the mutation process. Now its source code has been modified.
34 HashCode: -1716452675
Creating folder for mutant 35
Copying app information into mutant 35 folder
Mutant: 35 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 35 has survived the mutation process. Now its source code has been modified.
35 HashCode: 528692801
Creating folder for mutant 36
Copying app information into mutant 36 folder
Mutant: 36 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 36 has survived the mutation process. Now its source code has been modified.
36 HashCode: 1772549227
Creating folder for mutant 37
Copying app information into mutant 37 folder
Mutant: 37 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 37 has survived the mutation process. Now its source code has been modified.
37 HashCode: -869023420
Creating folder for mutant 38
Copying app information into mutant 38 folder
Mutant: 38 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 38 has survived the mutation process. Now its source code has been modified.
38 HashCode: -1304999798
Creating folder for mutant 39
Copying app information into mutant 39 folder
Mutant: 39 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 39 has survived the mutation process. Now its source code has been modified.
39 HashCode: -123393407
Creating folder for mutant 40
Copying app information into mutant 40 folder
Mutant: 40 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 40 has survived the mutation process. Now its source code has been modified.
40 HashCode: -1569077788
Creating folder for mutant 41
Copying app information into mutant 41 folder
Mutant: 41 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 41 has survived the mutation process. Now its source code has been modified.
41 HashCode: -569226795
Creating folder for mutant 42
Copying app information into mutant 42 folder
Mutant: 42 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 42 has survived the mutation process. Now its source code has been modified.
42 HashCode: -114099866
Creating folder for mutant 43
Copying app information into mutant 43 folder
Mutant: 43 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 43 has survived the mutation process. Now its source code has been modified.
43 HashCode: 499827850
Creating folder for mutant 44
Copying app information into mutant 44 folder
Mutant: 44 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 44 has survived the mutation process. Now its source code has been modified.
44 HashCode: 500733392
Creating folder for mutant 45
Copying app information into mutant 45 folder
Mutant: 45 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 45 has survived the mutation process. Now its source code has been modified.
45 HashCode: 1810616292
Creating folder for mutant 46
Copying app information into mutant 46 folder
Mutant: 46 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 46 has survived the mutation process. Now its source code has been modified.
46 HashCode: 1212532878
Creating folder for mutant 47
Copying app information into mutant 47 folder
Mutant: 47 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 47 has survived the mutation process. Now its source code has been modified.
47 HashCode: -857699184
Creating folder for mutant 48
Copying app information into mutant 48 folder
Mutant: 48 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 48 has survived the mutation process. Now its source code has been modified.
48 HashCode: 1156131018
Creating folder for mutant 49
Copying app information into mutant 49 folder
Mutant: 49 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 49 has survived the mutation process. Now its source code has been modified.
49 HashCode: 454975095
Creating folder for mutant 50
Copying app information into mutant 50 folder
Mutant: 50 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 50 has survived the mutation process. Now its source code has been modified.
50 HashCode: -2003800577
Creating folder for mutant 51
Copying app information into mutant 51 folder
Mutant: 51 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 51 has survived the mutation process. Now its source code has been modified.
51 HashCode: -111534513
Creating folder for mutant 52
Copying app information into mutant 52 folder
Mutant: 52 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 52 has survived the mutation process. Now its source code has been modified.
52 HashCode: 1760501452
Creating folder for mutant 53
Copying app information into mutant 53 folder
Mutant: 53 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 53 has survived the mutation process. Now its source code has been modified.
53 HashCode: -1934213342
Creating folder for mutant 54
Copying app information into mutant 54 folder
Mutant: 54 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 54 has survived the mutation process. Now its source code has been modified.
54 HashCode: 1516160318
Creating folder for mutant 55
Copying app information into mutant 55 folder
Mutant: 55 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 55 has survived the mutation process. Now its source code has been modified.
55 HashCode: 1974570032
Creating folder for mutant 56
Copying app information into mutant 56 folder
Mutant: 56 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 56 has survived the mutation process. Now its source code has been modified.
56 HashCode: -1558499861
Creating folder for mutant 57
Copying app information into mutant 57 folder
Mutant: 57 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 57 has survived the mutation process. Now its source code has been modified.
57 HashCode: 334174910
Creating folder for mutant 58
Copying app information into mutant 58 folder
Mutant: 58 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 58 has survived the mutation process. Now its source code has been modified.
58 HashCode: 1250730117
Creating folder for mutant 59
Copying app information into mutant 59 folder
Mutant: 59 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 59 has survived the mutation process. Now its source code has been modified.
59 HashCode: 2051699119
Creating folder for mutant 60
Copying app information into mutant 60 folder
Mutant: 60 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 60 has survived the mutation process. Now its source code has been modified.
60 HashCode: -2016124717
Creating folder for mutant 61
Copying app information into mutant 61 folder
Mutant: 61 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 61 has survived the mutation process. Now its source code has been modified.
61 HashCode: -363564567
Creating folder for mutant 62
Copying app information into mutant 62 folder
Mutant: 62 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 62 has survived the mutation process. Now its source code has been modified.
62 HashCode: 47815146
Creating folder for mutant 63
Copying app information into mutant 63 folder
Mutant: 63 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 63 has survived the mutation process. Now its source code has been modified.
63 HashCode: -1841578417
Creating folder for mutant 64
Copying app information into mutant 64 folder
Mutant: 64 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 64 has survived the mutation process. Now its source code has been modified.
64 HashCode: -175096535
Creating folder for mutant 65
Copying app information into mutant 65 folder
Mutant: 65 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 65 has survived the mutation process. Now its source code has been modified.
65 HashCode: 2126506903
Creating folder for mutant 66
Copying app information into mutant 66 folder
Mutant: 66 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 66 has survived the mutation process. Now its source code has been modified.
66 HashCode: 412691512
Creating folder for mutant 67
Copying app information into mutant 67 folder
Mutant: 67 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 67 has survived the mutation process. Now its source code has been modified.
67 HashCode: -387426146
Creating folder for mutant 68
Copying app information into mutant 68 folder
Mutant: 68 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 68 has survived the mutation process. Now its source code has been modified.
68 HashCode: -1237997845
Creating folder for mutant 69
Copying app information into mutant 69 folder
Mutant: 69 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 69 has survived the mutation process. Now its source code has been modified.
69 HashCode: -444649922
Creating folder for mutant 70
Copying app information into mutant 70 folder
Mutant: 70 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 70 has survived the mutation process. Now its source code has been modified.
70 HashCode: 689552869
Creating folder for mutant 71
Copying app information into mutant 71 folder
Mutant: 71 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 71 has survived the mutation process. Now its source code has been modified.
71 HashCode: 1218993055
Creating folder for mutant 72
Copying app information into mutant 72 folder
Mutant: 72 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 72 has survived the mutation process. Now its source code has been modified.
72 HashCode: -495583229
Creating folder for mutant 73
Copying app information into mutant 73 folder
Mutant: 73 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 73 has survived the mutation process. Now its source code has been modified.
73 HashCode: 958262230
Creating folder for mutant 74
Copying app information into mutant 74 folder
Mutant: 74 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 74 has survived the mutation process. Now its source code has been modified.
74 HashCode: -1539558278
Creating folder for mutant 75
Copying app information into mutant 75 folder
Mutant: 75 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 75 has survived the mutation process. Now its source code has been modified.
75 HashCode: 2030620796
Creating folder for mutant 76
Copying app information into mutant 76 folder
Mutant: 76 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 76 has survived the mutation process. Now its source code has been modified.
76 HashCode: -620481655
Creating folder for mutant 77
Copying app information into mutant 77 folder
Mutant: 77 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 77 has survived the mutation process. Now its source code has been modified.
77 HashCode: -309178528
Creating folder for mutant 78
Copying app information into mutant 78 folder
Mutant: 78 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 78 has survived the mutation process. Now its source code has been modified.
78 HashCode: -2034936091
Creating folder for mutant 79
Copying app information into mutant 79 folder
Mutant: 79 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 79 has survived the mutation process. Now its source code has been modified.
79 HashCode: -1779823896
Creating folder for mutant 80
Copying app information into mutant 80 folder
Mutant: 80 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 80 has survived the mutation process. Now its source code has been modified.
80 HashCode: -30862162
Creating folder for mutant 81
Copying app information into mutant 81 folder
Mutant: 81 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 81 has survived the mutation process. Now its source code has been modified.
81 HashCode: 1119922067
Creating folder for mutant 82
Copying app information into mutant 82 folder
Mutant: 82 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 82 has survived the mutation process. Now its source code has been modified.
82 HashCode: -1295955809
Creating folder for mutant 83
Copying app information into mutant 83 folder
Mutant: 83 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 83 has survived the mutation process. Now its source code has been modified.
83 HashCode: -1245102426
Creating folder for mutant 84
Copying app information into mutant 84 folder
Mutant: 84 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 84 has survived the mutation process. Now its source code has been modified.
84 HashCode: 1254503832
Creating folder for mutant 85
Copying app information into mutant 85 folder
Mutant: 85 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 85 has survived the mutation process. Now its source code has been modified.
85 HashCode: -1678458583
Creating folder for mutant 86
Copying app information into mutant 86 folder
Mutant: 86 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 86 has survived the mutation process. Now its source code has been modified.
86 HashCode: 2022848306
Creating folder for mutant 87
Copying app information into mutant 87 folder
Mutant: 87 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 87 has survived the mutation process. Now its source code has been modified.
87 HashCode: -1245400387
Creating folder for mutant 88
Copying app information into mutant 88 folder
Mutant: 88 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 88 has survived the mutation process. Now its source code has been modified.
88 HashCode: 1957824061
Creating folder for mutant 89
Copying app information into mutant 89 folder
Mutant: 89 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 89 has survived the mutation process. Now its source code has been modified.
89 HashCode: -1833048751
Creating folder for mutant 90
Copying app information into mutant 90 folder
Mutant: 90 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 90 has survived the mutation process. Now its source code has been modified.
90 HashCode: -1315634943
Creating folder for mutant 91
Copying app information into mutant 91 folder
Mutant: 91 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 91 has survived the mutation process. Now its source code has been modified.
91 HashCode: 1662520128
Creating folder for mutant 92
Copying app information into mutant 92 folder
Mutant: 92 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 92 has survived the mutation process. Now its source code has been modified.
92 HashCode: -542879009
Creating folder for mutant 93
Copying app information into mutant 93 folder
Mutant: 93 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 93 has survived the mutation process. Now its source code has been modified.
93 HashCode: 1292247811
Creating folder for mutant 94
Copying app information into mutant 94 folder
Mutant: 94 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 94 has survived the mutation process. Now its source code has been modified.
94 HashCode: -678677349
Creating folder for mutant 95
Copying app information into mutant 95 folder
Mutant: 95 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 95 has survived the mutation process. Now its source code has been modified.
95 HashCode: 1065606336
Creating folder for mutant 96
Copying app information into mutant 96 folder
Mutant: 96 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 96 has survived the mutation process. Now its source code has been modified.
96 HashCode: -1111456811
Creating folder for mutant 97
Copying app information into mutant 97 folder
Mutant: 97 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 97 has survived the mutation process. Now its source code has been modified.
97 HashCode: -1233080079
Creating folder for mutant 98
Copying app information into mutant 98 folder
Mutant: 98 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 98 has survived the mutation process. Now its source code has been modified.
98 HashCode: 1764473794
Creating folder for mutant 99
Copying app information into mutant 99 folder
Mutant: 99 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 99 has survived the mutation process. Now its source code has been modified.
99 HashCode: -1419514469
Creating folder for mutant 100
Copying app information into mutant 100 folder
Mutant: 100 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 100 has survived the mutation process. Now its source code has been modified.
100 HashCode: -1759679453
Creating folder for mutant 101
Copying app information into mutant 101 folder
Mutant: 101 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 101 has survived the mutation process. Now its source code has been modified.
101 HashCode: 847238877
Creating folder for mutant 102
Copying app information into mutant 102 folder
Mutant: 102 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 102 has survived the mutation process. Now its source code has been modified.
102 HashCode: -1345620001
Creating folder for mutant 103
Copying app information into mutant 103 folder
Mutant: 103 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 103 has survived the mutation process. Now its source code has been modified.
103 HashCode: 974513778
Creating folder for mutant 104
Copying app information into mutant 104 folder
Mutant: 104 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 104 has survived the mutation process. Now its source code has been modified.
104 HashCode: 1362468755
Creating folder for mutant 105
Copying app information into mutant 105 folder
Mutant: 105 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 105 has survived the mutation process. Now its source code has been modified.
105 HashCode: 1265872927
Creating folder for mutant 106
Copying app information into mutant 106 folder
Mutant: 106 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 106 has survived the mutation process. Now its source code has been modified.
106 HashCode: -1464288274
Creating folder for mutant 107
Copying app information into mutant 107 folder
Mutant: 107 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 107 has survived the mutation process. Now its source code has been modified.
107 HashCode: -1173653657
Creating folder for mutant 108
Copying app information into mutant 108 folder
Mutant: 108 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 108 has survived the mutation process. Now its source code has been modified.
108 HashCode: 1421383557
Creating folder for mutant 109
Copying app information into mutant 109 folder
Mutant: 109 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 109 has survived the mutation process. Now its source code has been modified.
109 HashCode: 176035301
Creating folder for mutant 110
Copying app information into mutant 110 folder
Mutant: 110 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 110 has survived the mutation process. Now its source code has been modified.
110 HashCode: 1919911397
Creating folder for mutant 111
Copying app information into mutant 111 folder
Mutant: 111 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 111 has survived the mutation process. Now its source code has been modified.
111 HashCode: 302709953
Creating folder for mutant 112
Copying app information into mutant 112 folder
Mutant: 112 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 112 has survived the mutation process. Now its source code has been modified.
112 HashCode: 1189454053
Creating folder for mutant 113
Copying app information into mutant 113 folder
Mutant: 113 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 113 has survived the mutation process. Now its source code has been modified.
113 HashCode: 402407737
Creating folder for mutant 114
Copying app information into mutant 114 folder
Mutant: 114 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 114 has survived the mutation process. Now its source code has been modified.
114 HashCode: -1615035578
Creating folder for mutant 115
Copying app information into mutant 115 folder
Mutant: 115 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 115 has survived the mutation process. Now its source code has been modified.
115 HashCode: -405007072
Creating folder for mutant 116
Copying app information into mutant 116 folder
Mutant: 116 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 116 has survived the mutation process. Now its source code has been modified.
116 HashCode: 1400794335
Creating folder for mutant 117
Copying app information into mutant 117 folder
Mutant: 117 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 117 has survived the mutation process. Now its source code has been modified.
117 HashCode: 745262969
Creating folder for mutant 118
Copying app information into mutant 118 folder
Mutant: 118 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 118 has survived the mutation process. Now its source code has been modified.
118 HashCode: 2056758543
Creating folder for mutant 119
Copying app information into mutant 119 folder
Mutant: 119 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 119 has survived the mutation process. Now its source code has been modified.
119 HashCode: -846157289
Creating folder for mutant 120
Copying app information into mutant 120 folder
Mutant: 120 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 120 has survived the mutation process. Now its source code has been modified.
120 HashCode: 973690464
Creating folder for mutant 121
Copying app information into mutant 121 folder
Mutant: 121 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 121 has survived the mutation process. Now its source code has been modified.
121 HashCode: 851042445
Creating folder for mutant 122
Copying app information into mutant 122 folder
Mutant: 122 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 122 has survived the mutation process. Now its source code has been modified.
122 HashCode: -2134000608
Creating folder for mutant 123
Copying app information into mutant 123 folder
Mutant: 123 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 123 has survived the mutation process. Now its source code has been modified.
123 HashCode: -11232867
Creating folder for mutant 124
Copying app information into mutant 124 folder
Mutant: 124 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 124 has survived the mutation process. Now its source code has been modified.
124 HashCode: -870570384
Creating folder for mutant 125
Copying app information into mutant 125 folder
Mutant: 125 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 125 has survived the mutation process. Now its source code has been modified.
125 HashCode: 1195680884
Creating folder for mutant 126
Copying app information into mutant 126 folder
Mutant: 126 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 126 has survived the mutation process. Now its source code has been modified.
126 HashCode: -2075699767
Creating folder for mutant 127
Copying app information into mutant 127 folder
Mutant: 127 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 127 has survived the mutation process. Now its source code has been modified.
127 HashCode: 629623215
Creating folder for mutant 128
Copying app information into mutant 128 folder
Mutant: 128 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 128 has survived the mutation process. Now its source code has been modified.
128 HashCode: -1784409070
Creating folder for mutant 129
Copying app information into mutant 129 folder
Mutant: 129 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 129 has survived the mutation process. Now its source code has been modified.
129 HashCode: 1224232730
Creating folder for mutant 130
Copying app information into mutant 130 folder
Mutant: 130 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 130 has survived the mutation process. Now its source code has been modified.
130 HashCode: -1055108972
Creating folder for mutant 131
Copying app information into mutant 131 folder
Mutant: 131 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 131 has survived the mutation process. Now its source code has been modified.
131 HashCode: -1832080560
Creating folder for mutant 132
Copying app information into mutant 132 folder
Mutant: 132 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 132 has survived the mutation process. Now its source code has been modified.
132 HashCode: -1083766124
Creating folder for mutant 133
Copying app information into mutant 133 folder
Mutant: 133 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 133 has survived the mutation process. Now its source code has been modified.
133 HashCode: 472853527
Creating folder for mutant 134
Copying app information into mutant 134 folder
Mutant: 134 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 134 has survived the mutation process. Now its source code has been modified.
134 HashCode: -844927996
Creating folder for mutant 135
Copying app information into mutant 135 folder
Mutant: 135 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 135 has survived the mutation process. Now its source code has been modified.
135 HashCode: -2064155836
Creating folder for mutant 136
Copying app information into mutant 136 folder
Mutant: 136 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 136 has survived the mutation process. Now its source code has been modified.
136 HashCode: 2005269267
Creating folder for mutant 137
Copying app information into mutant 137 folder
Mutant: 137 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 137 has survived the mutation process. Now its source code has been modified.
137 HashCode: -1985443746
Creating folder for mutant 138
Copying app information into mutant 138 folder
Mutant: 138 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 138 has survived the mutation process. Now its source code has been modified.
138 HashCode: 143514446
Creating folder for mutant 139
Copying app information into mutant 139 folder
Mutant: 139 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 139 has survived the mutation process. Now its source code has been modified.
139 HashCode: 1772549227 duplicate: 1772549227
The mutant with id: 139 is duplicated with mutant with id: 36
Creating folder for mutant 140
Copying app information into mutant 140 folder
Mutant: 140 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 140 has survived the mutation process. Now its source code has been modified.
140 HashCode: -3322455
Creating folder for mutant 141
Copying app information into mutant 141 folder
Mutant: 141 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 141 has survived the mutation process. Now its source code has been modified.
141 HashCode: -2036321159
Creating folder for mutant 142
Copying app information into mutant 142 folder
Mutant: 142 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 142 has survived the mutation process. Now its source code has been modified.
142 HashCode: 546457762
Creating folder for mutant 143
Copying app information into mutant 143 folder
Mutant: 143 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 143 has survived the mutation process. Now its source code has been modified.
143 HashCode: -1015439965
Creating folder for mutant 144
Copying app information into mutant 144 folder
Mutant: 144 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 144 has survived the mutation process. Now its source code has been modified.
144 HashCode: 2136605868
Creating folder for mutant 145
Copying app information into mutant 145 folder
Mutant: 145 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 145 has survived the mutation process. Now its source code has been modified.
145 HashCode: 1927696709
Creating folder for mutant 146
Copying app information into mutant 146 folder
Mutant: 146 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 146 has survived the mutation process. Now its source code has been modified.
146 HashCode: -1276682563
Creating folder for mutant 147
Copying app information into mutant 147 folder
Mutant: 147 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 147 has survived the mutation process. Now its source code has been modified.
147 HashCode: 1631388197
Creating folder for mutant 148
Copying app information into mutant 148 folder
Mutant: 148 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 148 has survived the mutation process. Now its source code has been modified.
148 HashCode: 961404647
Creating folder for mutant 149
Copying app information into mutant 149 folder
Mutant: 149 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 149 has survived the mutation process. Now its source code has been modified.
149 HashCode: 589237678
Creating folder for mutant 150
Copying app information into mutant 150 folder
Mutant: 150 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 150 has survived the mutation process. Now its source code has been modified.
150 HashCode: -376848703
Creating folder for mutant 151
Copying app information into mutant 151 folder
Mutant: 151 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 151 has survived the mutation process. Now its source code has been modified.
151 HashCode: 549435491
Creating folder for mutant 152
Copying app information into mutant 152 folder
Mutant: 152 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 152 has survived the mutation process. Now its source code has been modified.
152 HashCode: 1059457720
Creating folder for mutant 153
Copying app information into mutant 153 folder
Mutant: 153 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 153 has survived the mutation process. Now its source code has been modified.
153 HashCode: -1093279551
Creating folder for mutant 154
Copying app information into mutant 154 folder
Mutant: 154 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 154 has survived the mutation process. Now its source code has been modified.
154 HashCode: 1909424588
Creating folder for mutant 155
Copying app information into mutant 155 folder
Mutant: 155 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 155 has survived the mutation process. Now its source code has been modified.
155 HashCode: -1682413094
Creating folder for mutant 156
Copying app information into mutant 156 folder
Mutant: 156 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 156 has survived the mutation process. Now its source code has been modified.
156 HashCode: -1988277104
Creating folder for mutant 157
Copying app information into mutant 157 folder
Mutant: 157 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 157 has survived the mutation process. Now its source code has been modified.
157 HashCode: -1784104347
Creating folder for mutant 158
Copying app information into mutant 158 folder
Mutant: 158 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 158 has survived the mutation process. Now its source code has been modified.
158 HashCode: -119892878
Creating folder for mutant 159
Copying app information into mutant 159 folder
Mutant: 159 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 159 has survived the mutation process. Now its source code has been modified.
159 HashCode: -1559320327
Creating folder for mutant 160
Copying app information into mutant 160 folder
Mutant: 160 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 160 has survived the mutation process. Now its source code has been modified.
160 HashCode: 1511176884
Creating folder for mutant 161
Copying app information into mutant 161 folder
Mutant: 161 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 161 has survived the mutation process. Now its source code has been modified.
161 HashCode: 1737830055
Creating folder for mutant 162
Copying app information into mutant 162 folder
Mutant: 162 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 162 has survived the mutation process. Now its source code has been modified.
162 HashCode: 739379982
Creating folder for mutant 163
Copying app information into mutant 163 folder
Mutant: 163 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 163 has survived the mutation process. Now its source code has been modified.
163 HashCode: -1684341221
Creating folder for mutant 164
Copying app information into mutant 164 folder
Mutant: 164 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 164 has survived the mutation process. Now its source code has been modified.
164 HashCode: 1033906869
Creating folder for mutant 165
Copying app information into mutant 165 folder
Mutant: 165 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 165 has survived the mutation process. Now its source code has been modified.
165 HashCode: -905404389
Creating folder for mutant 166
Copying app information into mutant 166 folder
Mutant: 166 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 166 has survived the mutation process. Now its source code has been modified.
166 HashCode: 1806408825
Creating folder for mutant 167
Copying app information into mutant 167 folder
Mutant: 167 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 167 has survived the mutation process. Now its source code has been modified.
167 HashCode: -266083475
Creating folder for mutant 168
Copying app information into mutant 168 folder
Mutant: 168 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 168 has survived the mutation process. Now its source code has been modified.
168 HashCode: 1802776570
Creating folder for mutant 169
Copying app information into mutant 169 folder
Mutant: 169 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 169 has survived the mutation process. Now its source code has been modified.
169 HashCode: -1306899605
Creating folder for mutant 170
Copying app information into mutant 170 folder
Mutant: 170 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 170 has survived the mutation process. Now its source code has been modified.
170 HashCode: 1752975654
Creating folder for mutant 171
Copying app information into mutant 171 folder
Mutant: 171 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 171 has survived the mutation process. Now its source code has been modified.
171 HashCode: -1099081350
Creating folder for mutant 172
Copying app information into mutant 172 folder
Mutant: 172 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 172 has survived the mutation process. Now its source code has been modified.
172 HashCode: -829406477
Creating folder for mutant 173
Copying app information into mutant 173 folder
Mutant: 173 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 173 has survived the mutation process. Now its source code has been modified.
173 HashCode: -315454639
Creating folder for mutant 174
Copying app information into mutant 174 folder
Mutant: 174 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 174 has survived the mutation process. Now its source code has been modified.
174 HashCode: 1114071662
Creating folder for mutant 175
Copying app information into mutant 175 folder
Mutant: 175 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 175 has survived the mutation process. Now its source code has been modified.
175 HashCode: -1064730175
Creating folder for mutant 176
Copying app information into mutant 176 folder
Mutant: 176 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 176 has survived the mutation process. Now its source code has been modified.
176 HashCode: -123557710
Creating folder for mutant 177
Copying app information into mutant 177 folder
Mutant: 177 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 177 has survived the mutation process. Now its source code has been modified.
177 HashCode: -1767000046
Creating folder for mutant 178
Copying app information into mutant 178 folder
Mutant: 178 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 178 has survived the mutation process. Now its source code has been modified.
178 HashCode: -1310584808
Creating folder for mutant 179
Copying app information into mutant 179 folder
Mutant: 179 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 179 has survived the mutation process. Now its source code has been modified.
179 HashCode: -2007239985
Creating folder for mutant 180
Copying app information into mutant 180 folder
Mutant: 180 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 180 has survived the mutation process. Now its source code has been modified.
180 HashCode: 1702075520
Creating folder for mutant 181
Copying app information into mutant 181 folder
Mutant: 181 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 181 has survived the mutation process. Now its source code has been modified.
181 HashCode: 794474364
Creating folder for mutant 182
Copying app information into mutant 182 folder
Mutant: 182 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 182 has survived the mutation process. Now its source code has been modified.
182 HashCode: -2067684811
Creating folder for mutant 183
Copying app information into mutant 183 folder
Mutant: 183 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 183 has survived the mutation process. Now its source code has been modified.
183 HashCode: 1142505914
Creating folder for mutant 184
Copying app information into mutant 184 folder
Mutant: 184 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 184 has survived the mutation process. Now its source code has been modified.
184 HashCode: -1186429626
Creating folder for mutant 185
Copying app information into mutant 185 folder
Mutant: 185 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 185 has survived the mutation process. Now its source code has been modified.
185 HashCode: -801851651
Creating folder for mutant 186
Copying app information into mutant 186 folder
Mutant: 186 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 186 has survived the mutation process. Now its source code has been modified.
186 HashCode: 134683067
Creating folder for mutant 187
Copying app information into mutant 187 folder
Mutant: 187 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 187 has survived the mutation process. Now its source code has been modified.
187 HashCode: -106061523
Creating folder for mutant 188
Copying app information into mutant 188 folder
Mutant: 188 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 188 has survived the mutation process. Now its source code has been modified.
188 HashCode: 1919853128
Creating folder for mutant 189
Copying app information into mutant 189 folder
Mutant: 189 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 189 has survived the mutation process. Now its source code has been modified.
189 HashCode: -1121576254
Creating folder for mutant 190
Copying app information into mutant 190 folder
Mutant: 190 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 190 has survived the mutation process. Now its source code has been modified.
190 HashCode: 954977656
Creating folder for mutant 191
Copying app information into mutant 191 folder
Mutant: 191 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 191 has survived the mutation process. Now its source code has been modified.
191 HashCode: 361595802
Creating folder for mutant 192
Copying app information into mutant 192 folder
Mutant: 192 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 192 has survived the mutation process. Now its source code has been modified.
192 HashCode: 908306940
Creating folder for mutant 193
Copying app information into mutant 193 folder
Mutant: 193 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 193 has survived the mutation process. Now its source code has been modified.
193 HashCode: 1044265392
Creating folder for mutant 194
Copying app information into mutant 194 folder
Mutant: 194 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 194 has survived the mutation process. Now its source code has been modified.
194 HashCode: -268256229
Creating folder for mutant 195
Copying app information into mutant 195 folder
Mutant: 195 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 195 has survived the mutation process. Now its source code has been modified.
195 HashCode: -932879213
Creating folder for mutant 196
Copying app information into mutant 196 folder
Mutant: 196 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 196 has survived the mutation process. Now its source code has been modified.
196 HashCode: 1574990449
Creating folder for mutant 197
Copying app information into mutant 197 folder
Mutant: 197 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 197 has survived the mutation process. Now its source code has been modified.
197 HashCode: 1289894468
Creating folder for mutant 198
Copying app information into mutant 198 folder
Mutant: 198 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 198 has survived the mutation process. Now its source code has been modified.
198 HashCode: -1390932458
Creating folder for mutant 199
Copying app information into mutant 199 folder
Mutant: 199 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 199 has survived the mutation process. Now its source code has been modified.
199 HashCode: 498150156
Creating folder for mutant 200
Copying app information into mutant 200 folder
Mutant: 200 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 200 has survived the mutation process. Now its source code has been modified.
200 HashCode: -1733834002
Creating folder for mutant 201
Copying app information into mutant 201 folder
Mutant: 201 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 201 has survived the mutation process. Now its source code has been modified.
201 HashCode: 1194556516
Creating folder for mutant 202
Copying app information into mutant 202 folder
Mutant: 202 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 202 has survived the mutation process. Now its source code has been modified.
202 HashCode: -510520577
Creating folder for mutant 203
Copying app information into mutant 203 folder
Mutant: 203 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 203 has survived the mutation process. Now its source code has been modified.
203 HashCode: -1150628999
Creating folder for mutant 204
Copying app information into mutant 204 folder
Mutant: 204 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 204 has survived the mutation process. Now its source code has been modified.
204 HashCode: -1843591229
Creating folder for mutant 205
Copying app information into mutant 205 folder
Mutant: 205 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 205 has survived the mutation process. Now its source code has been modified.
205 HashCode: -641084293
Creating folder for mutant 206
Copying app information into mutant 206 folder
Mutant: 206 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 206 has survived the mutation process. Now its source code has been modified.
206 HashCode: -1452579828
Creating folder for mutant 207
Copying app information into mutant 207 folder
Mutant: 207 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 207 has survived the mutation process. Now its source code has been modified.
207 HashCode: 1627372046
Creating folder for mutant 208
Copying app information into mutant 208 folder
Mutant: 208 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 208 has survived the mutation process. Now its source code has been modified.
208 HashCode: 1739203752
Creating folder for mutant 209
Copying app information into mutant 209 folder
Mutant: 209 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 209 has survived the mutation process. Now its source code has been modified.
209 HashCode: 1911023186
Creating folder for mutant 210
Copying app information into mutant 210 folder
Mutant: 210 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 210 has survived the mutation process. Now its source code has been modified.
210 HashCode: -1799370528
Creating folder for mutant 211
Copying app information into mutant 211 folder
Mutant: 211 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 211 has survived the mutation process. Now its source code has been modified.
211 HashCode: 1640361531
Creating folder for mutant 212
Copying app information into mutant 212 folder
Mutant: 212 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 212 has survived the mutation process. Now its source code has been modified.
212 HashCode: 1905383140
Creating folder for mutant 213
Copying app information into mutant 213 folder
Mutant: 213 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 213 has survived the mutation process. Now its source code has been modified.
213 HashCode: 1228622933
Creating folder for mutant 214
Copying app information into mutant 214 folder
Mutant: 214 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 214 has survived the mutation process. Now its source code has been modified.
214 HashCode: 1664047745
Creating folder for mutant 215
Copying app information into mutant 215 folder
Mutant: 215 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 215 has survived the mutation process. Now its source code has been modified.
215 HashCode: -1574951558
Creating folder for mutant 216
Copying app information into mutant 216 folder
Mutant: 216 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 216 has survived the mutation process. Now its source code has been modified.
216 HashCode: 1163493052
Creating folder for mutant 217
Copying app information into mutant 217 folder
Mutant: 217 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 217 has survived the mutation process. Now its source code has been modified.
217 HashCode: -1833048751 duplicate: -1833048751
The mutant with id: 217 is duplicated with mutant with id: 89
Creating folder for mutant 218
Copying app information into mutant 218 folder
Mutant: 218 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 218 has survived the mutation process. Now its source code has been modified.
218 HashCode: -651540370
Creating folder for mutant 219
Copying app information into mutant 219 folder
Mutant: 219 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 219 has survived the mutation process. Now its source code has been modified.
219 HashCode: -837381323
Creating folder for mutant 220
Copying app information into mutant 220 folder
Mutant: 220 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 220 has survived the mutation process. Now its source code has been modified.
220 HashCode: -2024908072
Creating folder for mutant 221
Copying app information into mutant 221 folder
Mutant: 221 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 221 has survived the mutation process. Now its source code has been modified.
221 HashCode: -1313553943
Creating folder for mutant 222
Copying app information into mutant 222 folder
Mutant: 222 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 222 has survived the mutation process. Now its source code has been modified.
222 HashCode: 44549280
Creating folder for mutant 223
Copying app information into mutant 223 folder
Mutant: 223 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 223 has survived the mutation process. Now its source code has been modified.
223 HashCode: -2039961189
Creating folder for mutant 224
Copying app information into mutant 224 folder
Mutant: 224 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 224 has survived the mutation process. Now its source code has been modified.
224 HashCode: 1288788329
Creating folder for mutant 225
Copying app information into mutant 225 folder
Mutant: 225 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 225 has survived the mutation process. Now its source code has been modified.
225 HashCode: 587174237
Creating folder for mutant 226
Copying app information into mutant 226 folder
Mutant: 226 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 226 has survived the mutation process. Now its source code has been modified.
226 HashCode: -401355493
Creating folder for mutant 227
Copying app information into mutant 227 folder
Mutant: 227 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 227 has survived the mutation process. Now its source code has been modified.
227 HashCode: 1441877017
Creating folder for mutant 228
Copying app information into mutant 228 folder
Mutant: 228 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 228 has survived the mutation process. Now its source code has been modified.
228 HashCode: -1470661533
Creating folder for mutant 229
Copying app information into mutant 229 folder
Mutant: 229 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 229 has survived the mutation process. Now its source code has been modified.
229 HashCode: -483776174
Creating folder for mutant 230
Copying app information into mutant 230 folder
Mutant: 230 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 230 has survived the mutation process. Now its source code has been modified.
230 HashCode: 1903544158
Creating folder for mutant 231
Copying app information into mutant 231 folder
Mutant: 231 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 231 has survived the mutation process. Now its source code has been modified.
231 HashCode: 1760487125
Creating folder for mutant 232
Copying app information into mutant 232 folder
Mutant: 232 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 232 has survived the mutation process. Now its source code has been modified.
232 HashCode: 310446668
Creating folder for mutant 233
Copying app information into mutant 233 folder
Mutant: 233 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 233 has survived the mutation process. Now its source code has been modified.
233 HashCode: -374144517
Creating folder for mutant 234
Copying app information into mutant 234 folder
Mutant: 234 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 234 has survived the mutation process. Now its source code has been modified.
234 HashCode: 1759212119
Creating folder for mutant 235
Copying app information into mutant 235 folder
Mutant: 235 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 235 has survived the mutation process. Now its source code has been modified.
235 HashCode: -1535780495
Creating folder for mutant 236
Copying app information into mutant 236 folder
Mutant: 236 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 236 has survived the mutation process. Now its source code has been modified.
236 HashCode: 1783871114
Creating folder for mutant 237
Copying app information into mutant 237 folder
Mutant: 237 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 237 has survived the mutation process. Now its source code has been modified.
237 HashCode: -1581878450
Creating folder for mutant 238
Copying app information into mutant 238 folder
Mutant: 238 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 238 has survived the mutation process. Now its source code has been modified.
238 HashCode: 694781207
Creating folder for mutant 239
Copying app information into mutant 239 folder
Mutant: 239 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 239 has survived the mutation process. Now its source code has been modified.
239 HashCode: -1402317759
Creating folder for mutant 240
Copying app information into mutant 240 folder
Mutant: 240 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 240 has survived the mutation process. Now its source code has been modified.
240 HashCode: -1255636500
Creating folder for mutant 241
Copying app information into mutant 241 folder
Mutant: 241 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 241 has survived the mutation process. Now its source code has been modified.
241 HashCode: -437723006
Creating folder for mutant 242
Copying app information into mutant 242 folder
Mutant: 242 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 242 has survived the mutation process. Now its source code has been modified.
242 HashCode: 1049196578
Creating folder for mutant 243
Copying app information into mutant 243 folder
Mutant: 243 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 243 has survived the mutation process. Now its source code has been modified.
243 HashCode: 337890524
Creating folder for mutant 244
Copying app information into mutant 244 folder
Mutant: 244 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 244 has survived the mutation process. Now its source code has been modified.
244 HashCode: 580758624
Creating folder for mutant 245
Copying app information into mutant 245 folder
Mutant: 245 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 245 has survived the mutation process. Now its source code has been modified.
245 HashCode: -2137290693
Creating folder for mutant 246
Copying app information into mutant 246 folder
Mutant: 246 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 246 has survived the mutation process. Now its source code has been modified.
246 HashCode: 64969456
Creating folder for mutant 247
Copying app information into mutant 247 folder
Mutant: 247 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 247 has survived the mutation process. Now its source code has been modified.
247 HashCode: -1122436321
Creating folder for mutant 248
Copying app information into mutant 248 folder
Mutant: 248 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 248 has survived the mutation process. Now its source code has been modified.
248 HashCode: -812027762
Creating folder for mutant 249
Copying app information into mutant 249 folder
Mutant: 249 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 249 has survived the mutation process. Now its source code has been modified.
249 HashCode: 2038923212
Creating folder for mutant 250
Copying app information into mutant 250 folder
Mutant: 250 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 250 has survived the mutation process. Now its source code has been modified.
250 HashCode: 1257273963
Creating folder for mutant 251
Copying app information into mutant 251 folder
Mutant: 251 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 251 has survived the mutation process. Now its source code has been modified.
251 HashCode: -926805247
Creating folder for mutant 252
Copying app information into mutant 252 folder
Mutant: 252 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 252 has survived the mutation process. Now its source code has been modified.
252 HashCode: -1593970508
Creating folder for mutant 253
Copying app information into mutant 253 folder
Mutant: 253 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 253 has survived the mutation process. Now its source code has been modified.
253 HashCode: -490897514
Creating folder for mutant 254
Copying app information into mutant 254 folder
Mutant: 254 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 254 has survived the mutation process. Now its source code has been modified.
254 HashCode: 1582723766
Creating folder for mutant 255
Copying app information into mutant 255 folder
Mutant: 255 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 255 has survived the mutation process. Now its source code has been modified.
255 HashCode: 1064949409
Creating folder for mutant 256
Copying app information into mutant 256 folder
Mutant: 256 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 256 has survived the mutation process. Now its source code has been modified.
256 HashCode: 644260133
Creating folder for mutant 257
Copying app information into mutant 257 folder
Mutant: 257 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 257 has survived the mutation process. Now its source code has been modified.
257 HashCode: 449702034
Creating folder for mutant 258
Copying app information into mutant 258 folder
Mutant: 258 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 258 has survived the mutation process. Now its source code has been modified.
258 HashCode: 2054659588
Creating folder for mutant 259
Copying app information into mutant 259 folder
Mutant: 259 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 259 has survived the mutation process. Now its source code has been modified.
259 HashCode: -879785104
Creating folder for mutant 260
Copying app information into mutant 260 folder
Mutant: 260 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 260 has survived the mutation process. Now its source code has been modified.
260 HashCode: -128863568
Creating folder for mutant 261
Copying app information into mutant 261 folder
Mutant: 261 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 261 has survived the mutation process. Now its source code has been modified.
261 HashCode: -504703877
Creating folder for mutant 262
Copying app information into mutant 262 folder
Mutant: 262 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 262 has survived the mutation process. Now its source code has been modified.
262 HashCode: 364322526
Creating folder for mutant 263
Copying app information into mutant 263 folder
Mutant: 263 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 263 has survived the mutation process. Now its source code has been modified.
263 HashCode: 1002421950
Creating folder for mutant 264
Copying app information into mutant 264 folder
Mutant: 264 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 264 has survived the mutation process. Now its source code has been modified.
264 HashCode: -1952054650
Creating folder for mutant 265
Copying app information into mutant 265 folder
Mutant: 265 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 265 has survived the mutation process. Now its source code has been modified.
265 HashCode: -1261544806
Creating folder for mutant 266
Copying app information into mutant 266 folder
Mutant: 266 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 266 has survived the mutation process. Now its source code has been modified.
266 HashCode: -144200443
Creating folder for mutant 267
Copying app information into mutant 267 folder
Mutant: 267 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 267 has survived the mutation process. Now its source code has been modified.
267 HashCode: -1599855072
Creating folder for mutant 268
Copying app information into mutant 268 folder
Mutant: 268 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 268 has survived the mutation process. Now its source code has been modified.
268 HashCode: 1739846969
Creating folder for mutant 269
Copying app information into mutant 269 folder
Mutant: 269 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 269 has survived the mutation process. Now its source code has been modified.
269 HashCode: 1692411667
Creating folder for mutant 270
Copying app information into mutant 270 folder
Mutant: 270 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 270 has survived the mutation process. Now its source code has been modified.
270 HashCode: -334070840
Creating folder for mutant 271
Copying app information into mutant 271 folder
Mutant: 271 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 271 has survived the mutation process. Now its source code has been modified.
271 HashCode: 63255593
Creating folder for mutant 272
Copying app information into mutant 272 folder
Mutant: 272 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 272 has survived the mutation process. Now its source code has been modified.
272 HashCode: 23965367
Creating folder for mutant 273
Copying app information into mutant 273 folder
Mutant: 273 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 273 has survived the mutation process. Now its source code has been modified.
273 HashCode: 1313891314
Creating folder for mutant 274
Copying app information into mutant 274 folder
Mutant: 274 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 274 has survived the mutation process. Now its source code has been modified.
274 HashCode: 1019625725
Creating folder for mutant 275
Copying app information into mutant 275 folder
Mutant: 275 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 275 has survived the mutation process. Now its source code has been modified.
275 HashCode: 786138670
Creating folder for mutant 276
Copying app information into mutant 276 folder
Mutant: 276 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 276 has survived the mutation process. Now its source code has been modified.
276 HashCode: 1158600733
Creating folder for mutant 277
Copying app information into mutant 277 folder
Mutant: 277 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 277 has survived the mutation process. Now its source code has been modified.
277 HashCode: -960436161
Creating folder for mutant 278
Copying app information into mutant 278 folder
Mutant: 278 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 278 has survived the mutation process. Now its source code has been modified.
278 HashCode: -1568234842
Creating folder for mutant 279
Copying app information into mutant 279 folder
Mutant: 279 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 279 has survived the mutation process. Now its source code has been modified.
279 HashCode: 1302482337
Creating folder for mutant 280
Copying app information into mutant 280 folder
Mutant: 280 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 280 has survived the mutation process. Now its source code has been modified.
280 HashCode: 774798308
Creating folder for mutant 281
Copying app information into mutant 281 folder
Mutant: 281 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 281 has survived the mutation process. Now its source code has been modified.
281 HashCode: -1937425485
Creating folder for mutant 282
Copying app information into mutant 282 folder
Mutant: 282 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 282 has survived the mutation process. Now its source code has been modified.
282 HashCode: 1426575164
Creating folder for mutant 283
Copying app information into mutant 283 folder
Mutant: 283 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 283 has survived the mutation process. Now its source code has been modified.
283 HashCode: 1802676937
Creating folder for mutant 284
Copying app information into mutant 284 folder
Mutant: 284 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 284 has survived the mutation process. Now its source code has been modified.
284 HashCode: 1310984351
Creating folder for mutant 285
Copying app information into mutant 285 folder
Mutant: 285 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 285 has survived the mutation process. Now its source code has been modified.
285 HashCode: 1194933655
Creating folder for mutant 286
Copying app information into mutant 286 folder
Mutant: 286 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 286 has survived the mutation process. Now its source code has been modified.
286 HashCode: -1563316058
Creating folder for mutant 287
Copying app information into mutant 287 folder
Mutant: 287 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 287 has survived the mutation process. Now its source code has been modified.
287 HashCode: 1241082080
Creating folder for mutant 288
Copying app information into mutant 288 folder
Mutant: 288 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 288 has survived the mutation process. Now its source code has been modified.
288 HashCode: -66226939
Creating folder for mutant 289
Copying app information into mutant 289 folder
Mutant: 289 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 289 has survived the mutation process. Now its source code has been modified.
289 HashCode: -496617046
Creating folder for mutant 290
Copying app information into mutant 290 folder
Mutant: 290 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 290 has survived the mutation process. Now its source code has been modified.
290 HashCode: 1616227948
Creating folder for mutant 291
Copying app information into mutant 291 folder
Mutant: 291 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 291 has survived the mutation process. Now its source code has been modified.
291 HashCode: 713021265
Creating folder for mutant 292
Copying app information into mutant 292 folder
Mutant: 292 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 292 has survived the mutation process. Now its source code has been modified.
292 HashCode: -216764555
Creating folder for mutant 293
Copying app information into mutant 293 folder
Mutant: 293 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 293 has survived the mutation process. Now its source code has been modified.
293 HashCode: -1308075773
Creating folder for mutant 294
Copying app information into mutant 294 folder
Mutant: 294 - Type: NULL_METHOD_CALL_ARGUMENT
Mutant 294 has survived the mutation process. Now its source code has been modified.
294 HashCode: 1002010452
Creating folder for mutant 295
Copying app information into mutant 295 folder
Mutant: 295 - Type: CLOSING_NULL_CURSOR
Mutant 295 has survived the mutation process. Now its source code has been modified.
295 HashCode: 1216187715
Creating folder for mutant 296
Copying app information into mutant 296 folder
Mutant: 296 - Type: CLOSING_NULL_CURSOR
Mutant 296 has survived the mutation process. Now its source code has been modified.
296 HashCode: -138077906
Creating folder for mutant 297
Copying app information into mutant 297 folder
Mutant: 297 - Type: MISSING_PERMISSION_MANIFEST
Mutant 297 has survived the mutation process. Now its source code has been modified.
297 HashCode: 1673887616
Creating folder for mutant 298
Copying app information into mutant 298 folder
Mutant: 298 - Type: MISSING_PERMISSION_MANIFEST
Mutant 298 has survived the mutation process. Now its source code has been modified.
298 HashCode: -2043552263
Creating folder for mutant 299
Copying app information into mutant 299 folder
Mutant: 299 - Type: NULL_INTENT
Mutant 299 has survived the mutation process. Now its source code has been modified.
299 HashCode: 811696307
Creating folder for mutant 300
Copying app information into mutant 300 folder
Mutant: 300 - Type: NULL_INTENT
Mutant 300 has survived the mutation process. Now its source code has been modified.
300 HashCode: -272266085
Creating folder for mutant 301
Copying app information into mutant 301 folder
Mutant: 301 - Type: NULL_INTENT
Mutant 301 has survived the mutation process. Now its source code has been modified.
301 HashCode: -602534427
Creating folder for mutant 302
Copying app information into mutant 302 folder
Mutant: 302 - Type: WRONG_MAIN_ACTIVITY
Mutant 302 has survived the mutation process. Now its source code has been modified.
302 HashCode: 238853202
Creating folder for mutant 303
Copying app information into mutant 303 folder
Mutant: 303 - Type: NULL_VALUE_INTENT_PUT_EXTRA
Mutant 303 has survived the mutation process. Now its source code has been modified.
303 HashCode: 935667351
Creating folder for mutant 304
Copying app information into mutant 304 folder
Mutant: 304 - Type: INVALID_VIEW_FOCUS
Mutant 304 has survived the mutation process. Now its source code has been modified.
304 HashCode: -752265914
Creating folder for mutant 305
Copying app information into mutant 305 folder
Mutant: 305 - Type: INVALID_VIEW_FOCUS
Mutant 305 has survived the mutation process. Now its source code has been modified.
305 HashCode: -189175668
Creating folder for mutant 306
Copying app information into mutant 306 folder
Mutant: 306 - Type: INVALID_KEY_INTENT_PUT_EXTRA
Mutant 306 has survived the mutation process. Now its source code has been modified.
306 HashCode: 601467070
Creating folder for mutant 307
Copying app information into mutant 307 folder
Mutant: 307 - Type: FINDVIEWBYID_RETURNS_NULL
Mutant 307 has survived the mutation process. Now its source code has been modified.
307 HashCode: 637932317
Creating folder for mutant 308
Copying app information into mutant 308 folder
Mutant: 308 - Type: FINDVIEWBYID_RETURNS_NULL
Mutant 308 has survived the mutation process. Now its source code has been modified.
308 HashCode: 411668586
Creating folder for mutant 309
Copying app information into mutant 309 folder
Mutant: 309 - Type: FINDVIEWBYID_RETURNS_NULL
Mutant 309 has survived the mutation process. Now its source code has been modified.
309 HashCode: 1091923692
------------------------------------------------------------------------------------
The maximum id is : 308
The length of hasmap is: 308
------------------------------------------------------------------------------------
```
