# NullBackendServiceReturn

* Toca dividir el procesamiento de este operador entre usar HttpResponse como variables y usarlo como parametro
* SMALI permite saber el tipo de datos usado en la respuesta de un método, por lo tanto se puede saber cuando se esta usando HttpResponse como parametro
* De la siguiente forma se ve un HttpResponse como variable:
```java
HttpResponse response = ...
```
```smali
.local v7, "response":Lorg/apache/http/HttpResponse;
```
```
(I_LOCAL v5 "response" Lorg/apache/http/HttpResponse;)
113
v5
187
"response"
194
Lorg/apache/http/HttpResponse;
15
```
