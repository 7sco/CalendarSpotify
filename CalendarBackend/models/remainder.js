const mongoose= require('mongoose');

const Schema= mongoose.Schema;

const remainderSchema= new Schema({
	title: String,
	startTime: String,
	endTime: String,
	dayRemainder: {
		type: Schema.Types.ObjectId,
		ref: 'day'
	}

});

const Reamainder= mongoose.model('remainder', remainderSchema);
module.exports= Reamainder;

