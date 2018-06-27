const mongoose= require('mongoose');

const Schema= mongoose.Schema;

const daySchema= new Schema({
	month: String,
	weekDay: String,
	monthDay: Number,
	remainder: [{
		type: Schema.Types.ObjectId,
		ref: 'remainder'
	}]

});


const Day= mongoose.model('day', daySchema);
module.exports= Day;

