ps aux | grep httpd
bishop            8574   0.0  0.0 407963472    512 s009  R+    6:30PM   0:00.00 grep httpd
lsof -i :8574
kill -9 8574

curl http://localhost/ -X POST -d "Message Body"



step1: 80 port をあけておく
step2: keep aliveのため、リクエストの終わりがわからない -> HTTPのリクエストの終わり判定
```
started: ServerSocket[addr=0.0.0.0/0.0.0.0,localport=80]
start >>>
POST / HTTP/1.1
Host: localhost
User-Agent: curl/7.79.1
Accept: */*
Content-Length: 12
Content-Type: application/x-www-form-urlencoded

<<< end
closing socket...
closing server...
```

step3: Message Bodyの判定 
