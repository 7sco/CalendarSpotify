const express = require('express');
const router = require('express-promise-router')();

const DaysController= require('../controllers/days');

const {validateParam, validateBody, schemas} = require('../helpers/routeHelpers');


router.route('/')
	.get(DaysController.index)
	.post(validateBody(schemas.daySchema),DaysController.newDay);

// //day/:dayId
router.route('/:dayId')
	.get(validateParam(schemas.idSchema,'dayId'), DaysController.getDay)
	.put([validateParam(schemas.idSchema, 'dayId'),
		validateBody(schemas.daySchema)],
		DaysController.replaceDay)
	.patch([validateParam(schemas.idSchema, 'dayId'),
		validateBody(schemas.dayOptionalSchema)],
		DaysController.updateDay);
	// .delete();


// /day/:dayId/reminders

router.route('/:dayId/remainders')
	.get(validateParam(schemas.idSchema,'dayId'),DaysController.getDayRemainders)
	.post([validateParam(schemas.idSchema, 'dayId')
		,validateBody(schemas.remainderSchema)]
		,DaysController.newDayRemainder);

module.exports= router;
