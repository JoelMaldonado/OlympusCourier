import express from 'express';
import { listarTodos, insertar, actualizar, eliminar } from '../controllers/repartos.controllers.js';

const router = express.Router();

router.get('/repartos', listarTodos);

router.post('/repartos', insertar);

router.put('/repartos/:id', actualizar);

router.delete('/repartos/:id', eliminar);

export default router;