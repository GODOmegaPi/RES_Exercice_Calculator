# RES - Exercice Calculator
Guillaume Laubscher et Alison Savary

## Protocol
### What transport protocol do we use?
We will use TCP.

### How does the client find the server (addresses and ports)?
To define
### Who speaks first?
The client will speak first to request a calculation.

### What is the sequence of messages exchanged by the client and the server?
client : I want a calculation computed.

server : What is your request ?

The client will send his request to the server.
 
The server will send the result and will ask if the client has another request.
 
The client will send another request or will close the connection.


### What happens when a message is received from the other party?
We need to parse the json.

If the receiver is the server, he does the calculation asked by the client and send a json back with the result.

If the receiver is the client, he parses the json and display the result.

### What is the syntax of the messages? How we generate and parse them?
The messages will be in JSON

Ex : ? 
```
client:
{
	"Calculation": ""
}

server:
{
	"Calculation": "",
	"Result": "",
	"Error": ""
}
``` 
### Who closes the connection and when?
The client closes the connection when he does not have any more request.

The server closes the connection after a timeout.