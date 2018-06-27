const router = require('express-promise-router')();

const RemaindersController= require('../controllers/remainders');
const {validateParam, validateBody, schemas} = require('../helpers/routeHelpers');


router.route('/')
	.get(RemaindersController.index)
	.post(validateBody(schemas.newRemainderSchema), RemaindersController.newRemainder);
	

router.route('/:remainderId')
.get(validateParam(schemas.idSchema, 'remainderId'),RemaindersController.getRemainder)
.put([validateParam(schemas.idSchema, 'remainderId')
	,validateBody(schemas.putRemainderSchema)]
	,RemaindersController.replaceRemainder)
.patch([validateParam(schemas.idSchema, 'remainderId'), validateBody(schemas.patchRemainderSchema)]
	,RemaindersController.updateRemainder)
.delete(validateParam(schemas.idSchema, 'remainderId')
	,RemaindersController.deleteRemainder);


module.exports= router; 