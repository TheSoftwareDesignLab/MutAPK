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
(I_LOCAL v7 "response" Lorg/apache/http/HttpResponse;)
113
v7
187
"response"
194
Lorg/apache/http/HttpResponse;
15
```
* De la siguiente forma se ve un HttpResponse como parametro
```java
testAuthenticationResponse(httpClient.executeOverride(request, httpContext));
```
```smali
iget-object v8, v1, Lcom/fsck/k9/mail/store/webdav/WebDavStore;->httpContext:Lorg/apache/http/protocol/HttpContext;

invoke-virtual {v3, v4, v8}, Lcom/fsck/k9/mail/store/webdav/WebDavHttpClient;->executeOverride(Lorg/apache/http/client/methods/HttpUriRequest;Lorg/apache/http/protocol/HttpContext;)Lorg/apache/http/HttpResponse;

move-result-object v8

invoke-direct {v1, v8}, Lcom/fsck/k9/mail/store/webdav/WebDavStore;->testAuthenticationResponse(Lorg/apache/http/HttpResponse;)Z
```
```
(I_STATEMENT_FORMAT22c_FIELD iget-object v8 v1 Lcom/fsck/k9/mail/store/webdav/WebDavStore; httpContext Lorg/apache/http/protocol/HttpContext;)
148
iget-object
60
v8
187
v1
187
Lcom/fsck/k9/mail/store/webdav/WebDavStore;
15
httpContext
191
Lorg/apache/http/protocol/HttpContext;
15
(I_STATEMENT_FORMAT35c_METHOD invoke-virtual (I_REGISTER_LIST v3 v4 v8) Lcom/fsck/k9/mail/store/webdav/WebDavHttpClient; executeOverride (I_METHOD_PROTOTYPE (I_METHOD_RETURN_TYPE Lorg/apache/http/HttpResponse;) Lorg/apache/http/client/methods/HttpUriRequest; Lorg/apache/http/protocol/HttpContext;))
159
invoke-virtual
75
(I_REGISTER_LIST v3 v4 v8)
127
v3
187
v4
187
v8
187
Lcom/fsck/k9/mail/store/webdav/WebDavHttpClient;
15
executeOverride
191
(I_METHOD_PROTOTYPE (I_METHOD_RETURN_TYPE Lorg/apache/http/HttpResponse;) Lorg/apache/http/client/methods/HttpUriRequest; Lorg/apache/http/protocol/HttpContext;)
117
(I_METHOD_RETURN_TYPE Lorg/apache/http/HttpResponse;)
118
Lorg/apache/http/HttpResponse;
15
Lorg/apache/http/client/methods/HttpUriRequest;
15
Lorg/apache/http/protocol/HttpContext;
15
(I_STATEMENT_FORMAT11x move-result-object v8)
136
move-result-object
46
v8
187
(I_STATEMENT_FORMAT35c_METHOD invoke-direct (I_REGISTER_LIST v1 v8) Lcom/fsck/k9/mail/store/webdav/WebDavStore; testAuthenticationResponse (I_METHOD_PROTOTYPE (I_METHOD_RETURN_TYPE Z) Lorg/apache/http/HttpResponse;))
159
invoke-direct
75
(I_REGISTER_LIST v1 v8)
127
v1
187
v8
187
Lcom/fsck/k9/mail/store/webdav/WebDavStore;
15
testAuthenticationResponse
191
(I_METHOD_PROTOTYPE (I_METHOD_RETURN_TYPE Z) Lorg/apache/http/HttpResponse;)
117
(I_METHOD_RETURN_TYPE Z)
118
Z
185
Lorg/apache/http/HttpResponse;
15
```


# ClosingNullCursor

```
invoke-interface {v8}, Landroid/database/Cursor;->close()V
```

```
const/4 v8, 0x0

invoke-interface {v8}, Landroid/database/Cursor;->close()V
```