import express from 'express';
import { getAllDestinos, insertDestino, updateDestino, deleteDestino } from '../controllers/destino.controllers.js';

const router = express.Router();

router.get('/destinos', getAllDestinos);

router.post('/destinos', insertDestino);

router.put('/destinos/:id', updateDestino);

router.delete('/destinos/:id', deleteDestino);

export default router;