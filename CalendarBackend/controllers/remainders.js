
const Remainder= require('../models/remainder');
const Day= require('../models/day');

module.exports={
	
	index: async(req, res, next)=>{
	
		const remainders = await Remainder.find({});
		res.status(200).json(remainders);	
	},

	newRemainder: async(req, res, next)=> {
		console.log('remainder: ', req.value);
		const dayRemainder = await Day.findById(req.value.body.dayRemainder);
		const newRemainder = req.value.body;
		delete newRemainder.dayRemainder;
		const remaind = new Remainder (newRemainder);
		remaind.dayRemainder= dayRemainder;
		await remaind.save();
		dayRemainder.remainder.push(remaind);
		await dayRemainder.save();
		res.status(200).json(remaind);
	},


	getRemainder: async(req, res, next)=> {
		const remainder= await Remainder.findById(req.value.params.remainderId);
		res.status(200).json(remainder)
	},

	replaceRemainder: async(req, res, next)=> {
		const {remainderId} = req.value.params;
		console.log('remainderId: ', remainderId);
		console.log('body : ', req.value.body);
		const newRemainder = req.value.body;
		console.log('newRemainder: ', newRemainder);
		const result= await Remainder.findByIdAndUpdate(remainderId, newRemainder)
		console.log('result: ', result);
		res.status(200).json({success: true});
	},

	updateRemainder: async(req, res, next)=> {
		const {remainderId} = req.value.params;

		const newRemainder = req.value.body;
		
		const result= await Remainder.findByIdAndUpdate(remainderId, newRemainder)
	
		res.status(200).json({success: true});
	},

	deleteRemainder: async(req, res, next)=> {
		const {remainderId} = req.value.params;
		//Get a remainder
		const remaind= await Remainder.findById(remainderId);
		if(!remaind){
			return res.status(404).json({error: 'Remainder does not exist'});
		}
		console.log('remaind: ', remaind);
		const dayRemainderId= remaind.dayRemainder;
		console.log('dayReaminder: ', remaind.dayRemainder);
		//Get a dayRemainder
		const dayRemainder = await Day.findById(dayRemainderId); 
		//remove remainder
		await remaind.remove();
		//remove remainder from dayRemainder list
		dayRemainder.remainder.pull(remaind); 
		await dayRemainder.save();

		res.status(200).json({success: true});
	}	

}