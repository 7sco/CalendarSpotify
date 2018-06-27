const Day= require('../models/day');
const Remainder= require('../models/remainder');


module.exports={

	//Validation Done
	index: async(req, res, next)=>{
	
		const days=await Day.find({});
		res.status(200).json(days);
		
	},

	//Validation Done
	newDay: async(req, res, next)=> {
		console.log('value', req.value.body);
		const newDay = new Day(req.value.body);
		const day= await newDay.save();
		res.status(201).json(day);
	},
	//Validation Done
	getDay:async(req, res, next)=> {
		const {dayId}= req.value.params;
		const day = await Day.findById(dayId);
		res.status(200).json(day);
	},

	replaceDay:async(req, res, next)=> {
		const {dayId}= req.value.params;
		const newDay = req.value.body;
		const result =await Day.findByIdAndUpdate(dayId, newDay);
		res.status(200).json({success: true});
	},

	 updateDay:async(req, res, next)=> {
		const {dayId}= req.value.params;
		const newDay = req.value.body;
		const result = await Day.findByIdAndUpdate(dayId, newDay);
		res.status(200).json({success: true});
	},

 	getDayRemainders:async(req, res, next)=> {
		const {dayId}= req.value.params;
		const day= await Day.findById(dayId).populate('remainder');
		console.log('day', day);
		res.status(200).json(day.remainder);
	},
	
	newDayRemainder:async(req, res, next)=> {
		const {dayId}= req.value.params;
		//Create new Car
		const newRemainder= new Remainder(req.value.body);
		//Get user
		const day= await Day.findById(dayId); 
		//Assign user as car seller
		newRemainder.dayRemainder=day;
		//save the car
		await newRemainder.save();
		//add Car to the users selling array
		day.remainder.push(newRemainder);
		//save the User
		await day.save();
		res.status(201).json(newRemainder);
	}
};

















