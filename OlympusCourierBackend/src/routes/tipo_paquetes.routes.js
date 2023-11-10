import express from 'express';
import { listarTodos, insertar, actualizar, eliminar } from '../controllers/tipo_paquetes.controllers.js';

const router = express.Router();

router.get('/paquetes', listarTodos);

router.post('/paquetes', insertar);

router.put('/paquetes/:id', actualizar);

router.delete('/paquetes/:id', eliminar);

export default router;