const Joi= require('joi');

module.exports = {
	validateParam : (schema, name) => {
		return (req, res, next)=>{
			// console.log('req.params', req.params);
			const result = Joi.validate({param: req['params'][name]},schema);
			if (result.error) {
				return res.status(400).json(result.error);
			}else{
				if(!req.value)
					req.value={};

				if(!req.value['params'])
					req.value['params']= {};

				req.value['params'][name] = result.value.param;
				next();
			}
		}
	},

	validateBody:(schema)=>{
		return (req, res, next) => {
			const result = Joi.validate(req.body, schema);

			if (result.error) {
				return res.status(400).json(result.error);
			}else{
				if(!req.value)
					req.value={};

				if(!req.value['body'])
					req.value['body']= {};

				req.value['body'] = result.value;
				next();
			}
		}
	},


	schemas: {

		daySchema: Joi.object().keys({
			month: Joi.string().required(),
			weekDay: Joi.string().required(),
			monthDay: Joi.number().required()

		}),
		dayOptionalSchema: Joi.object().keys({
			month: Joi.string(),
			weekDay: Joi.string(),
			monthDay: Joi.number()

		}),

		dayRemainderSchema: Joi.object().keys({
			title: Joi.string().required(),
			startTime: Joi.string().required(),
			endTime: Joi.string().required()

		}),

		remainderSchema: Joi.object().keys({
			dayRemainder: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required(),
			title: Joi.string().required(),
			startTime: Joi.string().required(),
			endTime: Joi.string().required()

		}),

		putRemainderSchema: Joi.object().keys({

			title: Joi.string().required(),
			startTime: Joi.string().required(),
			endTime: Joi.string().required()

		}),

		patchRemainderSchema: Joi.object().keys({
			title: Joi.string(),
			startTime: Joi.string(),
			endTime: Joi.string()

		}),


		idSchema : Joi.object().keys({
			param: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required()
		})
	}

}




