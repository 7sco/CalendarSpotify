const express = require ('express');
const logger = require('morgan');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const helmet = require('helmet');




mongoose.Promise= global.Promise;
mongoose.connect(process.env.MONGODB_URI||'mongodb://localhost/calendarproject');


const app= express();

app.use(helmet());

//Routes
const days= require('./routes/days');
const remainders= require('./routes/remainders');

//Middlewares
app.use(logger('dev'));
app.use(bodyParser.json());

//Routes

app.use('/days', days);
app.use('/remainders', remainders);


//Catch 404 errors and forward them to error handler
app.use((req, res, next)=>{
	const err= new Error ('Not Found');
	err.status=404;
	next(err);
});


//Errorr handler function
app.use((err, req, res, next)=>{
	const error=app.get('env')==='development'? err:{};
	const status= err.status || 500;
	//Respond the client
	res.status(status).json({
		error: {
			message: error.message
		}
	});
	//Respond to ourselves
	console.error(err);
});


//Start server
const port= process.env.PORT|| 3000;
app.listen(port, ()=>console.log(`Server is listening on port ${port}`));