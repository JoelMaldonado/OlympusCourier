import express from 'express';
import { generarComprobante } from '../controllers/nubefact.controllers.js';

const router = express.Router();

router.post('/nubefact', generarComprobante);

export default router;