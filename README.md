# benchmark #

## Build & Run ##

```sh
$ cd benchmark
$ sbt container:start
```

default port is 8080

Run wrk against the server

```
wrk -t100 -c100 -d60s --latency http://127.0.0.1:8080/small_payload
wrk -t400 -c400 -d60s --latency http://127.0.0.1:8080/small_payload
wrk -t100 -c100 -d60s --latency http://127.0.0.1:8080/big_payload
wrk -t400 -c400 -d60s --latency http://127.0.0.1:8080/big_payload
```
