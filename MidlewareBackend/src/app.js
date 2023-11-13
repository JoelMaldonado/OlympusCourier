import express from 'express';
import dotenv from 'dotenv';
import cors from 'cors';

import nubefactRoutes from './routes/nubefact.routes.js';

const app = express();
app.use(cors());
app.use(express.json());
dotenv.config();

const port = process.env.PORT || 3000;

app.get('/ping', (req, res) => res.send("Pong"));

app.use('/api', nubefactRoutes)


app.listen(port, () => {
    console.log('Servidor en el puerto', port);
});