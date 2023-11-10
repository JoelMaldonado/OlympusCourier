import express from "express";
import cors from "cors";
import { db } from './mysql.js'
import dotenv from "dotenv"

import destinoRoutes from './routes/destino.routes.js';
import clienteRoutes from './routes/cliente.routes.js';
import usuarioRoutes from './routes/usuarios.routes.js';
import tipoPaquetesRoutes from './routes/tipo_paquetes.routes.js';
import repartoRoutes from './routes/repartos.routes.js';
import comprobantesRoutes from './routes/comprobantes.routes.js';
import consultasRoutes from './routes/consultas.routes.js';

dotenv.config()

const app = express();

const port = process.env.PORT || 3000

app.use(cors())
app.use(express.json())


app.use('/api', destinoRoutes);
app.use('/api', clienteRoutes);
app.use('/api', usuarioRoutes);
app.use('/api', tipoPaquetesRoutes);
app.use('/api', repartoRoutes);
app.use('/api', comprobantesRoutes);
app.use('/api', consultasRoutes);

async function startServer() {
    try {
        await db.connect();
        console.log('Conexión a la base de datos exitosa');
        app.listen(port, () => {
            console.log(`Servidor Express escuchando en el puerto ${port}`);
        });
    } catch (error) {
        console.error('Error al conectar a la base de datos:', error);
    }
}

startServer();