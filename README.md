# akkaclusterarena

Minimal canary project aimed to test deployment environments for Akka Cluster 

Its an umbrella project of three:

* **Server**: Starts a cluster of one node which echoes any message sent by a cluster client to its `/user/echo_cave` path.
* **Client**: Starts and sends 5 text messages to all cluster client services with path `/user/echo_cave` and wait for their echoes for (500 ms each) yielding failure when they aren't received.
* **Common**: Echo messages shared message objects.

## Usage

### Server

Within the server machine:

1. `git clone git@github.com:pfcoperez/akkaclusterarena.git`
2. `cd akkaclusterarena`
3. `sbt server/run`  (Will listen to port 7891)

### Client

Within the client machine:

1. `git clone git@github.com:pfcoperez/akkaclusterarena.git`
2. `cd akkaclusterarena`
3. `sbt client/"run ip:port"`

The client can be invoked without providing a server thus trying to connect to `127.0.0.1:7891`

