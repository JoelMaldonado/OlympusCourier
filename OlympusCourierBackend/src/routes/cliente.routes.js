import express from 'express';
import { getAllClientes, insertCliente, updateCliente, deleteCliente } from '../controllers/cliente.controllers.js';

const router = express.Router();

router.get('/clientes', getAllClientes);

router.post('/clientes', insertCliente);

router.put('/clientes/:id', updateCliente);

router.delete('/clientes/:id', deleteCliente);

export default router;