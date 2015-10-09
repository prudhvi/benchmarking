# Finatra Hello World Example Application

To run

```
sbt run
```


default port is 8888

Run wrk against the server

```
wrk -t100 -c100 -d60s --latency http://127.0.0.1:8888/small_payload
wrk -t400 -c400 -d60s --latency http://127.0.0.1:8888/small_payload
wrk -t100 -c100 -d60s --latency http://127.0.0.1:8888/big_payload
wrk -t400 -c400 -d60s --latency http://127.0.0.1:8888/big_payload
```
