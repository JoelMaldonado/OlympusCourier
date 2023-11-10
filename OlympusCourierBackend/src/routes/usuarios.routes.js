import express from 'express';
import { getAllUsuarios, insertUsuario, updateUsuario, deleteUsuario } from '../controllers/usuarios.controllers.js';

const router = express.Router();

router.get('/usuarios', getAllUsuarios);

router.post('/usuarios', insertUsuario);

router.put('/usuarios/:id', updateUsuario);

router.delete('/usuarios/:id', deleteUsuario);

export default router;