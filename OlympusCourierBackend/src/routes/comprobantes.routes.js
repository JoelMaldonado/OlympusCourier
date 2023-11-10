import express from 'express';
import { listarTodos, insertar, actualizar, eliminar } from '../controllers/comprobantes.controllers.js';

const router = express.Router();

router.get('/comprobantes', listarTodos);
router.post('/comprobantes', insertar);
router.put('/comprobantes/:id', actualizar);
router.delete('/comprobantes/:id', eliminar);

export default router;