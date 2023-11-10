import express from 'express';
import { consultarDoc } from '../controllers/consultas.controller.js';

const router = express.Router();

router.post('/consultas', consultarDoc);

export default router;