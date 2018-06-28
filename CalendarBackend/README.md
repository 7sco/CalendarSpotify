# Calendar Backend Documentation
Calendar App, is an application that currently shows the month of June.
A user has the ability to add events on each day and delete events.
All the data is being store in a mongo DB backend server

## Endpoint
```https://calendarandroid.herokuapp.com/days```
- [Events](#events)
	- [Create events](#create-events)
	- [Delete events](#delete-events)
	- [Retrieve events](#retrieve-events)
	- [Update events](#update-events)
	


# Events

## Create events

	POST /events


### Parameters
Day : /days

| Name    | Type      | Description                          |
|---------|-----------|--------------------------------------|
| month			| 		String	|  <p>Day's month</p>							|
| weekDay			| 	String		|  <p>Name of Weekday</p>							|
| monthDay			| 		Number	|  <p>Number of day in month</p>|

Remainder : /days/:id/remainders

| Name    | Type      | Description                          |
|---------|-----------|--------------------------------------|
| title			| 		String	|  <p>Remainder Title</p>							|
| starttime			| 	String		|  <p>Start time of Remainder</p>							|
| endTime			| 		String	|  <p>End time of Remainder</p>|

## Delete events
	DELETE /days/:id </br>
  DELETE /remainders/:id</br>


## Retrieve events
	GET /days 
  GET /days/:id </br>
  GET /days/:id/remainders </br>
  GET /remainders/:id </br>
  
  
## Update events
	PUT /days/:id </br>
  PUT /remainders/:id </br>
  PATCH /days/:id </br>
  PATCH /remainders/:id </br>


